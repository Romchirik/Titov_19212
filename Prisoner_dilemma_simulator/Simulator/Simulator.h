#pragma once

#include <gtest/gtest.h>

#include <algorithm>
#include <bitset>
#include <iostream>
#include <string>
#include <vector>

#include "../Factory/Factory.h"
#include "../Printer/Printer.h"
#include "../StrategiesStuff/include/Strategy.h"
#include "../macro.h"

extern Factory factory;

class Simulator {
   public:
    Simulator();

    ~Simulator() = default;

    void setNumSteps(unsigned int steps);

    bool setDecisionsMatrix(const std::vector<std::pair<std::string, std::vector<int>>> &matrix);

    void setMode(char m);

    bool addStrategies(std::vector<std::string> &s);

    bool startGame();

    void getTournamentTable(unsigned int num_strategies);

   private:
    void startCompetition(const std::vector<int> &now_challenging);

    void startCompetitionDet(const std::vector<int> &now_challenging);

    void startTournament();

    void nextStep(const std::vector<int> &now_challenging, Round &current_round);

    std::string decisionsToString(bool d0, bool d1, bool d2);

    void subsetGenerator(const std::vector<int> &template_set, std::vector<int> tmp, int i);

    std::vector<int> getScore(const bool d0, const bool d1, const bool d2);

   public:
    std::string error_message;

   private:
    Printer printer;
    unsigned int num_steps = 0;
    char mode = COMPETITION;
    std::vector<Round> history;
    std::vector<std::string> strategies_names;
    std::vector<std::shared_ptr<Strategy>> strategies;
    std::unordered_map<std::string, std::vector<int>> game_matrix;
    std::vector<std::string> winners;
    std::vector<std::vector<int>> tournament_table;

   private:
    FRIEND_TEST(Simulator_test, subsets_test);
    FRIEND_TEST(Simulator_test, default_secision_matrix_test);
    FRIEND_TEST(Simulator, history_push_test);
};