#pragma once

#include <iomanip>
#include <iostream>
#include <vector>

#include "../macro.h"

struct Round {
    std::vector<bool> decisions_hist0;
    std::vector<bool> decisions_hist1;
    std::vector<bool> decisions_hist2;

    std::vector<int> challenged;
    std::vector<int> score = {0, 0, 0};
    std::vector<std::string> names;
};

class Printer {
   public:
    void printResultComp(Round &round, int round_number);
    void printDetails(Round &interim_result);
    void printWinner(std::vector<std::string> &winners, bool draw);

   private:
    void printRoundResult(Round &round, size_t round_num, std::vector<std::string>);
};