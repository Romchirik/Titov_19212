#include <gtest/gtest.h>

#include <algorithm>

#include "Simulator/Simulator.h"
#include "StrategiesStuff/include/Strategy.h"
#include "macro.h"

extern Factory factory;

TEST(Strategies_testing, all_defect) {
    std::unique_ptr<Strategy> test = factory.create("all-defect");

    ASSERT_EQ((*test).makeDecision({COOPERATE}, {COOPERATE}), DEFECT);
    ASSERT_EQ((*test).makeDecision({COOPERATE}, {DEFECT}), DEFECT);
    ASSERT_EQ((*test).makeDecision({DEFECT}, {COOPERATE}), DEFECT);
    ASSERT_EQ((*test).makeDecision({DEFECT}, {DEFECT}), DEFECT);
}

TEST(Simulator_test, subsets_test) {
    std::vector<std::vector<int>> table = {
        {1, 2, 3},
        {0, 2, 3},
        {0, 1, 3},
        {0, 1, 2},
    };
    std::vector<int> template_set = {0, 1, 2, 3};
    std::vector<int> subset;
    Simulator testing;
    testing.subsetGenerator(template_set, subset, 0);
    ASSERT_NE(testing.tournament_table.end(), std::find(testing.tournament_table.begin(), testing.tournament_table.end(), table[0]));
    ASSERT_NE(testing.tournament_table.end(), std::find(testing.tournament_table.begin(), testing.tournament_table.end(), table[1]));
    ASSERT_NE(testing.tournament_table.end(), std::find(testing.tournament_table.begin(), testing.tournament_table.end(), table[2]));
    ASSERT_NE(testing.tournament_table.end(), std::find(testing.tournament_table.begin(), testing.tournament_table.end(), table[3]));
}

TEST(Simulator_test, default_secision_matrix_test) {
    std::vector<std::vector<int>> table = {
        {7, 7, 7},
        {3, 3, 9},
        {3, 9, 3},
        {9, 3, 3},
        {0, 5, 5},
        {5, 0, 5},
        {5, 5, 0},
        {1, 1, 1}};

    Simulator testing;
    ASSERT_EQ(testing.getScore(COOPERATE, COOPERATE, COOPERATE), table[0]);
    ASSERT_EQ(testing.getScore(COOPERATE, COOPERATE, DEFECT), table[1]);
    ASSERT_EQ(testing.getScore(COOPERATE, DEFECT, COOPERATE), table[2]);
    ASSERT_EQ(testing.getScore(DEFECT, COOPERATE, COOPERATE), table[3]);
    ASSERT_EQ(testing.getScore(COOPERATE, DEFECT, DEFECT), table[4]);
    ASSERT_EQ(testing.getScore(DEFECT, COOPERATE, DEFECT), table[5]);
    ASSERT_EQ(testing.getScore(DEFECT, DEFECT, COOPERATE), table[6]);
    ASSERT_EQ(testing.getScore(DEFECT, DEFECT, DEFECT), table[7]);
}

TEST(Simulator_test, wrong_mode_test) {
    Simulator testing;
    testing.setMode(48);
    ASSERT_NO_THROW(testing.startGame());
    ASSERT_EQ(testing.error_message, "Wrong game mode\n");
}

TEST(Simulator, history_push_test) {
    std::vector<std::string> a = {"random", "random", "random"};
    Simulator testing;
    testing.addStrategies(a);
    testing.setMode(COMPETITION);
    testing.startGame();
    ASSERT_NO_THROW(testing.history[0]);
}
