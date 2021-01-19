#pragma once

#include <memory>

#include "Strategy.h"
#include "../../macro.h"

class ToughTitForTat : public Strategy {
public:
    ToughTitForTat() = default;

    ~ToughTitForTat() = default;

    bool makeDecision(const std::vector<bool> &prev_d1) override;

    bool makeDecision(const std::vector<bool> &prev_d1, const std::vector<bool> &prev_d2) override;
};

