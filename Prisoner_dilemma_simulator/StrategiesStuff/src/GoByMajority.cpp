#include "../include/GoByMajority.h"

extern "C" std::unique_ptr<Strategy> create() {
    return std::make_unique<GoByMajority>();
}

bool GoByMajority::makeDecision(const std::vector<bool> &prev_d1) {
    if (prev_d1.empty()) {
        return COOPERATE;
    } else {
        if (prev_d1.back() == COOPERATE) {
            other_coop_counter++;
        } else {
            other_defections_counter++;
        }
        if (other_coop_counter < other_defections_counter) {
            return DEFECT;
        } else {
            return COOPERATE;
        }
    }
}

bool GoByMajority::makeDecision(const std::vector<bool> &prev_d1, const std::vector<bool> &prev_d2) {
    return false;
}

void GoByMajority::reset() {
    other_coop_counter = 0;
    other_defections_counter = 0;
    return;
}
