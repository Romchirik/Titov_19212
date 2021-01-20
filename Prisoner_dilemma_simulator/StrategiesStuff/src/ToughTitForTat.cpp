#include "../include/ToughTitForTat.h"

extern "C" std::unique_ptr<Strategy> create() {
    return std::make_unique<ToughTitForTat>();
}

bool ToughTitForTat::makeDecision(const std::vector<bool> &prev_d1, const std::vector<bool> &prev_d2) {
    if (prev_d1.empty())
        return COOPERATE;
    else {
        bool d1 = prev_d1.back();
        bool d2 = prev_d2.back();

        if (d1 == DEFECT or d2 == DEFECT)
            return DEFECT;
        else
            return COOPERATE;
    }
}

bool ToughTitForTat::makeDecision(const std::vector<bool> &prev_d1) {
    if (prev_d1.empty())
        return COOPERATE;
    else {
        bool d1 = prev_d1.back();
        if (d1 == DEFECT)
            return DEFECT;
        else
            return COOPERATE;
    }
}

void ToughTitForTat::reset() {
    return;
}