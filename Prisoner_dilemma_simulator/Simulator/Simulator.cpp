#include "Simulator.h"

void Simulator::startCompetition(const std::vector<int> &now_challenging) {
    Round tmp_hist;
    tmp_hist.challenged = now_challenging;
    tmp_hist.names.push_back(strategies_names[now_challenging[0]]);
    tmp_hist.names.push_back(strategies_names[now_challenging[1]]);
    tmp_hist.names.push_back(strategies_names[now_challenging[2]]);
    for (int i = 0; i < num_steps; i++) {
        nextStep(now_challenging, tmp_hist);
    }
    printer.printResultComp(tmp_hist, -1);
    std::vector<std::string> tmp;
    auto max_score = std::max_element(tmp_hist.score.begin(), tmp_hist.score.end());
    for (int i = 0; i < tmp_hist.score.size(); i++) {
        if (*max_score == tmp_hist.score[i]) {
            tmp.push_back(strategies_names[tmp_hist.challenged[i]]);
        }
    }
    printer.printWinner(tmp);
    history.push_back(tmp_hist);
}

void Simulator::startCompetitionDet(const std::vector<int> &now_challenging) {
    Round tmp_hist;
    tmp_hist.challenged = now_challenging;
    tmp_hist.names.push_back(strategies_names[now_challenging[0]]);
    tmp_hist.names.push_back(strategies_names[now_challenging[1]]);
    tmp_hist.names.push_back(strategies_names[now_challenging[2]]);
    for (int i = 0; i < num_steps; i++) {
        std::string command;
        std::cin >> command;
        if (command == "quit") {
            history.push_back(tmp_hist);
            return;
        } else {
            nextStep(now_challenging, tmp_hist);
            printer.printDetails(tmp_hist);
        }
    }

    std::vector<std::string> winners;
    auto max_score = std::max_element(tmp_hist.score.begin(), tmp_hist.score.end());
    for (int i = 0; i < tmp_hist.score.size(); i++) {
        if (*max_score == tmp_hist.score[i]) {
            winners.push_back(strategies_names[tmp_hist.challenged[i]]);
        }
    }
    printer.printWinner(winners);
    history.push_back(tmp_hist);
}

void Simulator::startTournament() {
    std::vector<int> total_result(strategies.size(), 0);

    getTournamentTable(strategies.size());

    for (auto &i : tournament_table) {
        startCompetition(i);
        strategies[i[0]]->reset();
        strategies[i[1]]->reset();
        strategies[i[2]]->reset();
    }
    for (auto &i : history) {
        total_result[i.challenged[0]] += i.score[0];
        total_result[i.challenged[1]] += i.score[1];
        total_result[i.challenged[2]] += i.score[2];
    }

    auto max_score = std::max_element(total_result.begin(), total_result.end());
    for (int i = 0; i < total_result.size(); i++) {
        if (*max_score == total_result[i]) {
            winners.push_back(strategies_names[i]);
        }
    }
    printer.printResultTournament(strategies_names, total_result);
    printer.printWinner(winners);
}

void Simulator::setNumSteps(const unsigned int steps) {
    num_steps = steps;
}

Simulator::Simulator() {
    game_matrix.insert(std::make_pair<std::string, std::vector<int>>("CCC", {7, 7, 7}));
    game_matrix.insert(std::make_pair<std::string, std::vector<int>>("CCD", {3, 3, 9}));
    game_matrix.insert(std::make_pair<std::string, std::vector<int>>("CDC", {3, 9, 3}));
    game_matrix.insert(std::make_pair<std::string, std::vector<int>>("DCC", {9, 3, 3}));
    game_matrix.insert(std::make_pair<std::string, std::vector<int>>("CDD", {0, 5, 5}));
    game_matrix.insert(std::make_pair<std::string, std::vector<int>>("DCD", {5, 0, 5}));
    game_matrix.insert(std::make_pair<std::string, std::vector<int>>("DDC", {5, 5, 0}));
    game_matrix.insert(std::make_pair<std::string, std::vector<int>>("DDD", {1, 1, 1}));
}

void Simulator::setMode(char m) {
    mode = m;
}

bool Simulator::addStrategies(std::vector<std::string> &s) {
    bool strategy_not_found = false;
    for (auto &i : s) {
        strategies.push_back(factory.create(i));
        if (strategies.back() == nullptr) {
            strategy_not_found = true;
            error_message += "Strategy not found: " + i + "\n";
        } else {
            strategies_names.push_back(i);
        }
    }
    if (strategy_not_found)
        return false;
    return true;
}

void Simulator::nextStep(const std::vector<int> &now_challenging, Round &current_round) {
    bool d0, d1, d2;

    d0 = strategies[now_challenging[0]]->makeDecision(current_round.decisions_hist1,
                                                      current_round.decisions_hist2);
    d1 = strategies[now_challenging[1]]->makeDecision(current_round.decisions_hist0,
                                                      current_round.decisions_hist2);
    d2 = strategies[now_challenging[2]]->makeDecision(current_round.decisions_hist0,
                                                      current_round.decisions_hist1);

    current_round.decisions_hist0.push_back(d0);
    current_round.decisions_hist1.push_back(d1);
    current_round.decisions_hist2.push_back(d2);

    std::vector<int> tmp_score = getScore(d0, d1, d2);

    current_round.score[0] += tmp_score[0];
    current_round.score[1] += tmp_score[1];
    current_round.score[2] += tmp_score[2];
}

bool Simulator::startGame() {
    switch (mode) {
        case COMPETITION:
            startCompetition({0, 1, 2});
            break;
        case COMPETITION_DET:
            startCompetitionDet({0, 1, 2});

            break;
        case TOURNAMENT:
            startTournament();
            break;
        default:
            error_message += "Wrong game mode\n";
            return false;
    }
    return true;
}

std::vector<int> Simulator::getScore(const bool d0, const bool d1, const bool d2) {
    return game_matrix.at(decisionsToString(d0, d1, d2));
    ;
}

std::string Simulator::decisionsToString(const bool d0, const bool d1, const bool d2) {
    std::string tmp;
    if (d0)
        tmp += "D";
    else
        tmp += "C";
    if (d1)
        tmp += "D";
    else
        tmp += "C";
    if (d2)
        tmp += "D";
    else
        tmp += "C";
    return tmp;
}

bool Simulator::setDecisionsMatrix(const std::vector<std::pair<std::string, std::vector<int>>> &matrix) {
    const std::vector<std::string> test_tmp = {"DDD", "CDD", "DCD", "CCD", "DDC", "CDC", "DCC", "CCC"};
    game_matrix.clear();
    if (matrix.size() != 8) {
        return false;
    }

    for (const auto &i : test_tmp) {
        auto it = game_matrix.find(i);
        if (it == game_matrix.end()) {
            error_message = "Wrong game matrix";
            return false;
        }
    }
    return true;
}

void Simulator::subsetGenerator(const std::vector<int> &template_set, std::vector<int> subset, int i) {
    if (subset.size() == 3) {
        tournament_table.push_back(subset);
        return;
    } else {
        if (i < template_set.size()) {
            subsetGenerator(template_set, subset, i + 1);
            subset.push_back(template_set[i]);
            subsetGenerator(template_set, subset, i + 1);
        } else
            return;
    }
}

void Simulator::getTournamentTable(const unsigned int num_strategies) {
    std::vector<int> template_set;
    std::vector<int> subset;
    template_set.reserve(num_strategies);

    for (int i = 0; i < num_strategies; i++)
        template_set.push_back(i);
    subsetGenerator(template_set, subset, 0);
}
