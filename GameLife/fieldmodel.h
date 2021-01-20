#ifndef FIELDMODEL_H
#define FIELDMODEL_H

#include <algorithm>
#include <cstddef>
#include <cstring>

class FieldModel {
  public:
    FieldModel();
    FieldModel(size_t width, size_t height);
    ~FieldModel();
    FieldModel(const FieldModel &a);
    FieldModel(FieldModel &&a) = delete;

    void clear();
    void reset();
    void resize(size_t new_w, size_t new_h);
    void swap(FieldModel &m);
    size_t getWidht();
    size_t getHeight();
    bool *operator[](size_t idx);
    FieldModel &operator=(const FieldModel &a);
    FieldModel &operator=(const FieldModel &&a);
    bool friend operator==(const FieldModel &m1, const FieldModel &m2);

  private:
    static constexpr size_t DEFAULT_WIDTH = 50;
    static constexpr size_t DEFAULT_HEIGHT = 25;
    bool *field;
    size_t width = DEFAULT_WIDTH;
    size_t height = DEFAULT_HEIGHT;
};

#endif // FIELDMODEL_H
