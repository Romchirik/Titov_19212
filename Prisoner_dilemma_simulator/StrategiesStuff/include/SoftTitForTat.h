#pragma once

#include <memory>

#include "Strategy.h"
#include "../../macro.h"


class SoftTitForTat : public Strategy {
public:
    SoftTitForTat() = default;

    ~SoftTitForTat() = default;

    bool makeDecision(const std::vector<bool> &prev_d1) override;

    bool makeDecision(const std::vector<bool> &prev_d1, const std::vector<bool> &prev_d2) override;
};
