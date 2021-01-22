#include "fieldmodel.h"

void FieldModel::setNewField(Field &new_field) {
    current_generation = new_field;
    next_generation.resize_reset(new_field.getWidht(), new_field.getHeight());
}

Field &FieldModel::getFieldToSave() { return current_generation; }

bool FieldModel::applyTemplate() { return false; }

bool *FieldModel::operator[](size_t idx) { return current_generation[idx]; }

void FieldModel::setTemplateOrigin(size_t new_x, size_t new_y) {
    template_origin_x = new_x;
    template_origin_y = new_y;
}

bool FieldModel::if_draw(size_t x, size_t y) {
    bool tmp = current_generation[x][y];
    if (x >= template_origin_x &&
        x < template_origin_x + template_generation.getWidht() &&
        x >= template_origin_y &&
        x < template_origin_y + template_generation.getHeight()) {
        tmp = tmp ||
              template_generation[x - template_origin_x][y - template_origin_y];
    }
    return tmp;
}

size_t FieldModel::getWidth() { return current_generation.getWidht(); }
size_t FieldModel::getHeight() { return current_generation.getHeight(); }

void FieldModel::reset() {
    current_generation.reset();
    next_generation.reset();
    template_generation.reset();
}
void FieldModel::resize(size_t new_w, size_t new_h) {
    if (new_h != current_generation.getHeight() ||
        new_w != current_generation.getWidht()) {
        current_generation.resize_reset(new_w, new_h);
        next_generation.resize_reset(new_w, new_h);
    }
}

bool FieldModel::update(RuleHandler &rule_handler) {
    bool game_continue_flag = false;
    for (size_t x = 0; x < current_generation.getWidht(); x++) {
        for (size_t y = 0; y < current_generation.getHeight(); y++) {
            size_t num_neighbours = countNeighbours(x, y);
            if (current_generation[x][y]) {
                if (rule_handler.isAlive(num_neighbours)) {
                    next_generation[x][y] = true;
                } else {
                    next_generation[x][y] = false;
                }
            } else {
                if (rule_handler.isBorn(num_neighbours)) {
                    next_generation[x][y] = true;
                } else {
                    next_generation[x][y] = false;
                }
            }
            if ((current_generation[x][y] != next_generation[x][y]) &&
                !game_continue_flag) {
                game_continue_flag = true;
            }
        }
    }
    current_generation.swap(next_generation);
    return game_continue_flag;
}

inline size_t FieldModel::getX(int raw_x) {
    return (raw_x < 0) ? current_generation.getWidht() + raw_x
                       : raw_x % current_generation.getWidht();
}

inline size_t FieldModel::getY(int raw_y) {
    return (raw_y < 0) ? current_generation.getWidht() + raw_y
                       : raw_y % current_generation.getHeight();
    ;
}
int FieldModel::countNeighbours(const size_t x, const size_t y) {
    int num_neighbours = 0;

    // up-left
    num_neighbours += current_generation[getX(x + 1)][getY(y + 1)];
    // left
    num_neighbours += current_generation[getX(x + 1)][getY(y)];

    // down-left
    num_neighbours += current_generation[getX(x + 1)][getY(y - 1)];

    // down
    num_neighbours += current_generation[getX(x)][getY(y - 1)];

    // down-right
    num_neighbours += current_generation[getX(x - 1)][getY(y - 1)];

    // right
    num_neighbours += current_generation[getX(x - 1)][getY(y)];

    // up-right
    num_neighbours += current_generation[getX(x - 1)][getY(y + 1)];

    // up
    num_neighbours += current_generation[getX(x)][getY(y + 1)];

    return num_neighbours;
}
