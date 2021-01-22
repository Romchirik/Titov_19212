#ifndef FIELD_H
#define FIELD_H

#include <algorithm>
#include <cstddef>
#include <cstring>

class Field {
  public:
    Field();
    Field(size_t width, size_t height);
    ~Field();
    Field(const Field &a);
    Field(Field &&a) = delete;

    void clear();
    void reset();
    void resize_reset(size_t new_w, size_t new_h);
    void swap(Field &m);
    size_t getWidht();
    size_t getHeight();
    bool *operator[](size_t idx);
    Field &operator=(const Field &a);
    Field &operator=(const Field &&a);
    bool friend operator==(const Field &m1, const Field &m2);

  private:
    static constexpr size_t DEFAULT_WIDTH = 50;
    static constexpr size_t DEFAULT_HEIGHT = 25;
    bool *field;
    size_t width = DEFAULT_WIDTH;
    size_t height = DEFAULT_HEIGHT;
};

#endif // FIELD_H
