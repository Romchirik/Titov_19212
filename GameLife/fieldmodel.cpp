#include "fieldmodel.h"

FieldModel::FieldModel() {
    field = new bool[width * height];
    std::memset(field, false, sizeof(bool) * width * height);
}

FieldModel::FieldModel(size_t w, size_t h) : width(w), height(h) {
    field = new bool[w * h];
    std::memset(field, false, sizeof(bool) * width * height);
}

FieldModel::FieldModel(const FieldModel &a) {
    this->resize(a.width, a.height);
    memcpy(this->field, a.field, sizeof(bool) * width * height);
}

FieldModel::~FieldModel() { delete[] field; }

void FieldModel::reset() {
    std::memset(field, false, sizeof(bool) * width * height);
}

void FieldModel::resize(size_t new_w, size_t new_h) {
    delete[] field;
    field = new bool[new_w * new_h];
    memset(field, false, sizeof(bool) * new_w * new_h);
    width = new_w;
    height = new_h;
}

void FieldModel::swap(FieldModel &m) {
    bool *tmp = this->field;
    this->field = m.field;
    m.field = tmp;
    std::swap(this->width, m.width);
    std::swap(this->height, m.height);
}

size_t FieldModel::getWidht() { return width; }

size_t FieldModel::getHeight() { return height; }

bool *FieldModel::operator[](size_t idx) { return field + height * idx; }

FieldModel &FieldModel::operator=(const FieldModel &a) {
    if (this != &a) {
        this->resize(a.width, a.height);
        memcpy(this->field, a.field, sizeof(bool) * width * height);
    } else {
        return *this;
    }
    return *this;
}
bool operator==(const FieldModel &m1, const FieldModel &m2) {
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
