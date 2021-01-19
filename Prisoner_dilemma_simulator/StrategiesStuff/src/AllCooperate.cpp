#include "../include/AllCooperate.h"

extern "C" std::unique_ptr<Strategy> create()
{
    return std::make_unique<AllCooperate>();
}

bool AllCooperate::makeDecision(const std::vector<bool> &prev_d1, const std::vector<bool> &prev_d2)
{
    return COOPERATE;
}

bool AllCooperate::makeDecision(const std::vector<bool> &prev_d1)
{
    return COOPERATE;
}
