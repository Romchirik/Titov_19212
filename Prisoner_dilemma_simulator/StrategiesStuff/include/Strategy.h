#pragma once

#include <memory>
#include <vector>

class Strategy {
   public:
    Strategy() = default;

    virtual ~Strategy() = default;

    virtual bool makeDecision(const std::vector<bool> &prev_d1) = 0;

    virtual bool makeDecision(const std::vector<bool> &prev_d1, const std::vector<bool> &prev_d2) = 0;

    virtual void reset() = 0;
};
