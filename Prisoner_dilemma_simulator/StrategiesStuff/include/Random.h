#pragma once

#include <chrono>
#include <memory>
#include <random>

#include "Strategy.h"

class Random final : public Strategy {
   public:
    Random();

    ~Random() = default;

    bool makeDecision(const std::vector<bool> &prev_d1) override;

    bool makeDecision(const std::vector<bool> &prev_d1, const std::vector<bool> &prev_d2) override;

    void reset() override;

   private:
    std::default_random_engine engine;
    std::uniform_int_distribution<int> random_bool;
};