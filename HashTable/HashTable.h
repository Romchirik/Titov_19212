#pragma once

#include <iostream>
#include <memory>

template<class Key, class Value>
class DataBox {
public:
    bool is_dummy = false;
    bool is_free = true;
    Key key;
    Value value;

    DataBox() = default;

    DataBox(const Key &key_origin, const Value &value_origin) {
        is_dummy = false;
        is_free = false;
        key = key_origin;
        value = value_origin;
        std::shared_ptr<int> a;

    }

    void swap(DataBox &target) {
        std::swap(key, target.key);
        std::swap(value, target.value);
        std::swap(is_free, target.is_free);
    }

    friend bool operator==(const DataBox &a, const DataBox &b);

    friend bool operator!=(const DataBox &a, const DataBox &b);


};

template<typename Key, typename Value>
bool operator==(const DataBox<Key, Value> &a, const DataBox<Key, Value> &b) {
    return a.key == b.key && a.value == b.value && a.is_free == b.is_free && a.is_dummy == b.is_dummy;
}

template<typename Key, typename Value>
bool operator!=(const DataBox<Key, Value> &a, const DataBox<Key, Value> &b) {
    return !(a == b);
}

template<typename Key, typename Value>
class Iter : public std::iterator<std::input_iterator_tag, std::pair<Key, Value>> {
public:
    Iter() = delete;

    Iter(std::shared_ptr<bool> &v, DataBox<Key, Value> *p);

    Iter(const Iter &i);

    ~Iter() = default;

    Iter &operator=(const Iter &i);

    bool friend operator==(const Iter &i1, const Iter &i2) {
        if (i1.offset == i2.offset && i1.data_ptr == i2.data_ptr) {
            return true;
        }
        return false;
    }

    bool friend operator!=(const Iter &i1, const Iter &i2) {
        return !(i1 == i2);
    }

    Iter &operator++();

    Iter operator++(int);

    Iter &operator--();

    Iter operator--(int);

    std::pair<Key, Value> &operator*();

private:
    size_t offset = 0;
    DataBox<Key, Value> *data_ptr;
    std::shared_ptr<bool> is_valid;
};

template<typename Key, typename Value>
Iter<Key, Value>::Iter(std::shared_ptr<bool> &v, DataBox<Key, Value> *p): data_ptr(p),
                                                                          is_valid(v) {

}

template<typename Key, typename Value>
Iter<Key, Value>::Iter(const Iter &i):data_ptr(i.data_ptr),
                                      is_valid(i.is_valid),
                                      offset(i.offset) {

}

template<typename Key, typename Value>
Iter<Key, Value> &Iter<Key, Value>::operator=(const Iter<Key, Value> &i) {
    if (&i != this) {
        this->data_ptr = i.data_ptr;
        this->is_valid = i.is_valid;
        this->offset = i.offset;
    }
    return *this;
}

template<typename Key, typename Value>
std::pair<Key, Value> &Iter<Key, Value>::operator*() {
    if (*is_valid) {
        std::pair<Key, Value> p = std::make_pair<Key, Value>(std::move(data_ptr->key), std::move(data_ptr->value));
        return p;
    } else {
        throw std::runtime_error("invalid iterator");
    }

}

template<typename Key, typename Value>
Iter<Key, Value> &Iter<Key, Value>::operator++() {
    if (!is_valid.get()) {
        throw std::runtime_error("invalid iterator");
    }
    while (data_ptr->is_free || data_ptr->is_dummy) {
        offset++;
        data_ptr++;
    }
    return *this;
}

template<typename Key, typename Value>
Iter<Key, Value> Iter<Key, Value>::operator++(int) {
    Iter<Key, Value> tmp = *this;
    ++*this;
    return tmp;
}


template<typename Key, typename Value>
Iter<Key, Value> &Iter<Key, Value>::operator--() {
    if (!(*is_valid)) {
        throw std::runtime_error("invalid iterator");
    }
    while (data_ptr->is_free || data_ptr->is_dummy) {
        offset--;
        data_ptr--;
    }

    return *this;
}

template<typename Key, typename Value>
Iter<Key, Value> Iter<Key, Value>::operator--(int) {
    Iter<Key, Value> tmp = *this;
    --*this;
    return tmp;
}

template<typename Key, typename Value>
class HashTable {
public:
    HashTable();

    ~HashTable();

    HashTable(const HashTable &source);

    //Меняет местами this и переданную таблицу
    void swap(HashTable &target);

    HashTable &operator=(const HashTable &source);

    // Устанавливает таблицу в начальное состояние
    void clear();

    // Удаляет пару по ключу
    // Возвращает true если ключ есть в таблице, false если ключ отсутствует
    bool erase(const Key &key);

    bool insert(const Key &key, const Value &val);

    // Проверяет наличие элемента в таблице.
    // Возвращает true в случае успешного удаления, false иначе.
    bool contains(const Key &key) const;

    Value &operator[](const Key &key) const;

    // Возвращает значение по ключу. Бросает исключение при неудаче.
    Value &at(const Key &key);

    // Возвращает значение по ключу. Бросает исключение при неудаче.
    const Value &at(const Key &key) const;

    // Возвращает количество элементов в таблице
    size_t size() const;

    // Возвращает true если таблица пустая, false в другом случае
    bool empty() const;

    friend bool operator==(const HashTable &a, const HashTable &b) {
        if (a.capacity != b.capacity || a.totalNodesIn != b.totalNodesIn || a.totalNodesIn != b.totalNodesIn) {
            return false;
        }

        for (int i = 0; i < a.capacity; i++) {
            if (!a.data[i].is_free) {
                if (a.data[i].value != b[a.data[i].key]) { return false; }

            }
        }
        return true;
    }

    friend bool operator!=(const HashTable &a, const HashTable &b) {
        return !(a == b);
    }

    Iter<Key, Value> end();

    Iter<Key, Value> begin();

private:

private:
    constexpr static size_t DEFAULT_CAPACITY = 10;
    constexpr static double MAX_LOAD_FACTOR = 0.72;
    constexpr static int growth_factor = 2;
    size_t totalNodesIn;
    size_t capacity;
    struct DataBox<Key, Value> *data = nullptr;
    std::shared_ptr<bool> iterators_valid;
    std::hash<Key> hashF;

private:

    // Увеличавает размер таблицы в growth_factor раз
    bool resize();

    // Вставляет пару ключ-значение в массив data. В случае успешной вставки возвращает true, false иначе
    bool _insert(DataBox<Key, Value> *data, const Key &key, const Value &value);

    // Изменяет хеш таким образом, чтобы он помещался в границы индексов массива data
    size_t getIdx(const Key &key) const;

    Value &find_value(const Key &key);
};

template<typename Key, typename Value>
Iter<Key, Value> HashTable<Key, Value>::end() {

    return Iter<Key, Value>(iterators_valid, data + capacity);
}

template<typename Key, typename Value>
Iter<Key, Value> HashTable<Key, Value>::begin() {
    return Iter<Key, Value>(iterators_valid, data);
}

template<typename KEY_T, typename VALUE_T>
size_t HashTable<KEY_T, VALUE_T>::getIdx(KEY_T const &key) const {
    return hashF(key) % capacity;
}

template<typename KEY_T, typename VALUE_T>
HashTable<KEY_T, VALUE_T>::HashTable():capacity(DEFAULT_CAPACITY),
                                       totalNodesIn(0),
                                       data(new DataBox<KEY_T, VALUE_T>[DEFAULT_CAPACITY]),
                                       iterators_valid(std::make_shared<bool>(true)) {}

template<typename KEY_T, typename VALUE_T>
HashTable<KEY_T, VALUE_T>::~HashTable() {
    *iterators_valid = false;
    delete[] data;
}

template<typename KEY_T, typename VALUE_T>
HashTable<KEY_T, VALUE_T>::HashTable(const HashTable &source): capacity(source.capacity),
                                                               totalNodesIn(source.totalNodesIn) {
    data = new DataBox<KEY_T, VALUE_T>[source.capacity];
    std::copy(source.data, source.data + source.capacity, data);
}

template<typename KEY_T, typename VALUE_T>
void HashTable<KEY_T, VALUE_T>::swap(HashTable &target) {
    std::swap(capacity, target.capacity);
    std::swap(totalNodesIn, target.totalNodesIn);
    std::swap(data, target.data);
    std::swap(hashF, target.hashF);
}

template<typename KEY_T, typename VALUE_T>
HashTable<KEY_T, VALUE_T> &HashTable<KEY_T, VALUE_T>::operator=(const HashTable &source) {
    if (&source != this) {
        if (capacity != source.capacity) {
            capacity = source.capacity;
            deleteData(data);
            data = new struct DataBox<KEY_T, VALUE_T>[source.capacity];
        }
        totalNodesIn = source.totalNodesIn;
        std::copy(source.data, source.data + source.capacity, data);
        *iterators_valid = false;
        iterators_valid = std::make_shared<bool>(true);
    }
    return *this;
}

template<typename KEY_T, typename VALUE_T>
void HashTable<KEY_T, VALUE_T>::clear() {
    capacity = DEFAULT_CAPACITY;
    totalNodesIn = 0;

    *iterators_valid = false;
    iterators_valid = std::make_shared<bool>(true);
    delete[] data;
    data = new DataBox<KEY_T, VALUE_T>[DEFAULT_CAPACITY];
}

template<typename KEY_T, typename VALUE_T>
bool HashTable<KEY_T, VALUE_T>::erase(const KEY_T &key) {
    size_t idx = getIdx(key);
    size_t start_idx = idx;
    do {
        if (data[idx].key == key && !data[idx].is_dummy) {
            data[idx].is_dummy = true;
            totalNodesIn--;
            return true;
        }
        idx++;
        idx %= capacity;
    } while (start_idx != idx && !data[idx].is_free);
    return false;
}

template<typename KEY_T, typename VALUE_T>
bool HashTable<KEY_T, VALUE_T>::insert(const KEY_T &key, const VALUE_T &value) {
    if ((totalNodesIn + 1) / static_cast<double>(capacity) > MAX_LOAD_FACTOR) {
        if (!resize()) { return false; }
    }
    if (_insert(data, key, value)) {
        totalNodesIn++;
        return true;
    }
    return false;
}

template<typename KEY_T, typename VALUE_T>
bool HashTable<KEY_T, VALUE_T>::contains(const KEY_T &key) const {
    size_t tmp_idx = getIdx(key);
    size_t start_idx = tmp_idx;
    do {
        if (key == data[tmp_idx].key) {
            if (data[tmp_idx].is_dummy) { return false; }
            else { return true; }

        }
        tmp_idx++;
        tmp_idx %= capacity;
    } while (tmp_idx != start_idx && data[tmp_idx].is_free == false);
    return false;
}

template<typename KEY_T, typename VALUE_T>
VALUE_T &HashTable<KEY_T, VALUE_T>::find_value(const KEY_T &key) {
    size_t tmp_idx = getIdx(key);
    unsigned int start_idx = tmp_idx;
    do {
        if (key == data[tmp_idx].key) {
            if (data[tmp_idx].is_dummy) { throw std::runtime_error("No value found"); }
            else { return data[tmp_idx].value; }
        }
        tmp_idx++;
        tmp_idx %= capacity;
    } while (tmp_idx != start_idx && !data[tmp_idx].is_free);
    throw std::runtime_error("No value fits key");
}

template<typename KEY_T, typename VALUE_T>
VALUE_T &HashTable<KEY_T, VALUE_T>::at(const KEY_T &key) {
    return find_value(key);
}

template<typename KEY_T, typename VALUE_T>
const VALUE_T &HashTable<KEY_T, VALUE_T>::at(const KEY_T &key) const {
    return static_cast<const VALUE_T>(find_value(key));
}

template<typename KEY_T, typename VALUE_T>
size_t HashTable<KEY_T, VALUE_T>::size() const {
    return totalNodesIn;
}

template<typename KEY_T, typename VALUE_T>
bool HashTable<KEY_T, VALUE_T>::empty() const {
    return totalNodesIn == 0;
}

template<typename KEY_T, typename VALUE_T>
bool HashTable<KEY_T, VALUE_T>::_insert(DataBox<KEY_T, VALUE_T> *dataArr, const KEY_T &key, const VALUE_T &value) {
    size_t idx = getIdx(key);
    size_t start_idx = idx;

    do {
        if ((dataArr[idx].key == key && !dataArr[idx].is_free) && !dataArr[idx].is_dummy) {
            return false;
        }
        if (dataArr[idx].is_free || dataArr[idx].is_dummy) {
            dataArr[idx].is_free = false;
            dataArr[idx].key = key;
            dataArr[idx].value = value;
            return true;
        }
        idx++;
        idx %= capacity;
    } while (start_idx != idx);
    return false;

}

template<typename KEY_T, typename VALUE_T>
bool HashTable<KEY_T, VALUE_T>::resize() {
    auto tmp_data = new DataBox<KEY_T, VALUE_T>[capacity * growth_factor];
    capacity *= growth_factor;
    for (int i = 0; i < capacity / growth_factor; i++) {
        if (!data[i].is_free) {
            if (!_insert(tmp_data, data[i].key, data[i].value)) {
                capacity /= growth_factor;
                delete[] tmp_data;
                return false;
            }
        }
    }
    delete[] data;
    data = tmp_data;
    *iterators_valid = false;
    iterators_valid = std::make_shared<bool>(true);
    return true;
}

template<typename KEY_T, typename VALUE_T>
VALUE_T &HashTable<KEY_T, VALUE_T>::operator[](const KEY_T &key) const {
    unsigned int tmp_idx = getIdx(key);
    unsigned int start_idx = tmp_idx;
    do {
        if (key == data[tmp_idx].key) {
            return data[tmp_idx].value;
        }
        tmp_idx++;
        tmp_idx %= capacity;
    } while (tmp_idx != start_idx && data[tmp_idx].is_free == false);
    return data[tmp_idx].value;
}



