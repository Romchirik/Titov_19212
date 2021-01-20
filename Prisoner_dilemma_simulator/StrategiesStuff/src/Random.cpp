#include "../include/Random.h"

#include <random>

extern "C" std::unique_ptr<Strategy> create() {
    return std::make_unique<Random>();
}

bool Random::makeDecision(const std::vector<bool> &prev_d1, const std::vector<bool> &prev_d2) {
    return random_bool(engine);
}

Random::Random() {
    engine = std::default_random_engine(std::chrono::steady_clock::now().time_since_epoch().count());
    random_bool = std::uniform_int_distribution<int>(0, 1);
}

bool Random::makeDecision(const std::vector<bool> &prev_d1) {
    return random_bool(engine);
}

void Random::reset() {
    return;
}