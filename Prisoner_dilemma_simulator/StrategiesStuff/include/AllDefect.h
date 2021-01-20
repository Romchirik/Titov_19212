#pragma once

#include <memory>

#include "../../Factory/Factory.h"
#include "../../macro.h"
#include "Strategy.h"

class AllDefect final : public Strategy {
   public:
    AllDefect() = default;

    ~AllDefect() override = default;

    bool makeDecision(const std::vector<bool> &prev_d1);

    bool makeDecision(const std::vector<bool> &prev_d1, const std::vector<bool> &prev_d2);

    void reset() override;
};
