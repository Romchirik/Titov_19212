#ifndef FIELDMODEL_H
#define FIELDMODEL_H

#include <algorithm>
#include <cstddef>
#include <cstring>

#include "field.h"
#include "rulehandler.h"

class FieldModel {
  public:
    FieldModel() = default;
    ~FieldModel() = default;

    bool update(RuleHandler &rule_handler);
    void reset();
    void resize(size_t new_w, size_t new_h);
    size_t getWidth();
    size_t getHeight();
    bool *operator[](size_t idx);
    bool if_draw(size_t x, size_t y);
    void setTemplateOrigin(size_t new_x, size_t new_y);
    bool applyTemplate();

  private:
    int countNeighbours(const size_t x, const size_t y);
    size_t getX(int raw_x);
    size_t getY(int raw_y);

  private:
    Field current_generation;
    Field next_generation;
    Field template_generation;

    size_t template_origin_x = 0;
    size_t template_origin_y = 0;
};

#endif // FIELDMODEL_H
