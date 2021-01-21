#include <random>
#include <string>

#include "HashTable.h"
#include "gtest/gtest.h"

const std::string keys[] = {"Polina", "Grisha", "Sasha", "Artyom", "Natasha", "Seryoga", "Masha", "Rita"};
const std::string data[] = {"Popova", "Karavayev", "Shapovalov", "Kolesnik", "Kokunina", "Dorn", "Malakhova",
                            "Leontyeva"};

TEST(HashTableTest, insert_eraise_simple) {
    auto table = HashTable<std::string, std::string>();

    for (int i = 0; i < 8; i++) {
        EXPECT_EQ(table.insert(keys[i], data[i]), true);
    }
    for (auto &key : keys) {
        EXPECT_EQ(table.contains(key), true);
        table.erase(key);
    }
    for (auto &key : keys) {
        EXPECT_EQ(table.contains(key), false);
    }
    ASSERT_TRUE(table.empty());
}

TEST(HashTableTest, at_key_fits_value_simple) {
    auto table = HashTable<std::string, std::string>();

    for (int i = 0; i < 8; i++) {
        table.insert(keys[i], data[i]);
    }
    for (int i = 0; i < 8; i++) {
        ASSERT_EQ(table.at(keys[i]), data[i]);
    }
}

TEST(HashTableTest, at_any_throw_simple) {
    auto table = HashTable<std::string, std::string>();

    for (int i = 0; i < 8; i++) {
        table.insert(keys[i], data[i]);
    }

    ASSERT_NO_THROW(table.at(keys[5]));

    for (auto &key : keys) {
        table.erase(key);
    }

    ASSERT_ANY_THROW(table.at(keys[5]));
}

TEST(HashTableTest, equal_operator_test) {
    auto table1 = HashTable<std::string, std::string>();
    auto table2 = HashTable<std::string, std::string>();

    constexpr static size_t key_length = 10;
    constexpr static size_t value_length = 100;

    for (int i = 0; i < 8; i++) {
        table1.insert(keys[i], data[i]);
        table2.insert(keys[7 - i], data[7 - i]);
    }

    ASSERT_TRUE(table1 == table2);
    table1.erase("Seryoga");
    ASSERT_FALSE(table1 == table2);
    table1.insert("Seryoga", "Dorn");
    ASSERT_TRUE(table1 == table2);
    table1.clear();
    ASSERT_FALSE(table1 == table2);
}

TEST(HashTableTest, square_brackets_operator_test) {
    auto table = HashTable<std::string, std::string>();

    for (int i = 0; i < 8; i++) {
        table.insert(keys[i], data[i]);
    }

    for (const auto &key : keys) ASSERT_NO_THROW(table[key]);
    ASSERT_NO_THROW(table["Random"]);
}

TEST(HashTableTest, simple_iterator_test) {
    auto table = HashTable<std::string, std::string>();

    auto iter1 = table.begin();
    auto iter2 = table.begin();
    EXPECT_EQ(iter1, iter2);
    iter1++;
    EXPECT_TRUE(iter1 == iter2);
    iter1--;
    EXPECT_TRUE(iter1 == iter2);
}

TEST(HashTableTest, walkthrough_iterator_test) {
    auto table = HashTable<std::string, std::string>();

    for (int i = 0; i < 8; i++) {
        table.insert(keys[i], data[i]);
    }
    auto iter1 = table.begin();
    while (iter1 != table.end()) {
        auto node = *iter1;
        iter1++;
    }
}

TEST(HashTableTest, throw_test) {
    auto table = HashTable<std::string, std::string>();
    auto it = table.begin();
    for (int i = 0; i < 8; i++) {
        table.insert(keys[i], data[i]);
    }
    ASSERT_ANY_THROW(it++);
}