#pragma once

#include "../macro.h"

#include <unordered_map>
#include <vector>
#include <string>

struct InputHandler {
    InputHandler() = default;

    ~InputHandler() = default;

    bool parseInput(int argc, char *argv[]);

    void clear();

    std::vector<std::string> strategies;
    char mode = COMPETITION_DET;
    int num_steps = DEFAULT_STEPS;
};
