#include "field.h"

Field::Field() {
    field = new bool[width * height];
    std::memset(field, false, sizeof(bool) * width * height);
}

Field::Field(size_t w, size_t h) : width(w), height(h) {
    field = new bool[w * h];
    std::memset(field, false, sizeof(bool) * width * height);
}

Field::Field(const Field &a) {
    this->resize_reset(a.width, a.height);
    memcpy(this->field, a.field, sizeof(bool) * width * height);
}

Field::~Field() { delete[] field; }

void Field::reset() {
    std::memset(field, false, sizeof(bool) * width * height);
}

void Field::resize_reset(size_t new_w, size_t new_h) {
    delete[] field;
    field = new bool[new_w * new_h];
    memset(field, false, sizeof(bool) * new_w * new_h);
    width = new_w;
    height = new_h;
}

void Field::swap(Field &m) {
    bool *tmp = this->field;
    this->field = m.field;
    m.field = tmp;
    std::swap(this->width, m.width);
    std::swap(this->height, m.height);
}

size_t Field::getWidht() { return width; }

size_t Field::getHeight() { return height; }

bool *Field::operator[](size_t idx) { return field + height * idx; }

Field &Field::operator=(const Field &a) {
    if (this != &a) {
        this->resize_reset(a.width, a.height);
        memcpy(this->field, a.field, sizeof(bool) * width * height);
    } else {
        return *this;
    }
    return *this;
}
bool operator==(const Field &m1, const Field &m2) {
    if (m1.height != m2.height || m1.width != m2.width) {
        return false;
    } else {
        for (size_t i = 0; i < m1.width * m1.height; i++) {
            if (m1.field[i] != m2.field[i]) {
                return false;
            }
        }
    }
    return true;
}
