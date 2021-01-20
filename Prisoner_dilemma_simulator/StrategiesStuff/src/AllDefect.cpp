#include "../include/AllDefect.h"

extern "C" std::unique_ptr<Strategy> create() {
    return std::make_unique<AllDefect>();
}

bool AllDefect::makeDecision(const std::vector<bool> &prev_d1) {
    return DEFECT;
}

bool AllDefect::makeDecision(const std::vector<bool> &prev_d1, const std::vector<bool> &prev_d2) {
    return DEFECT;
}

void AllDefect::reset() {
    return;
}