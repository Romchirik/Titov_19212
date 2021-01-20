#pragma once

#include <memory>

#include "../../Factory/Factory.h"
#include "../../macro.h"
#include "Strategy.h"
class GoByMajority : public Strategy {
   public:
    GoByMajority() = default;

    ~GoByMajority() = default;

    bool makeDecision(const std::vector<bool> &prev_d1, const std::vector<bool> &prev_d2) override;

    bool makeDecision(const std::vector<bool> &prev_d1) override;

    void reset() override;

   private:
    int other_coop_counter = 0;
    int other_defections_counter = 0;
};
