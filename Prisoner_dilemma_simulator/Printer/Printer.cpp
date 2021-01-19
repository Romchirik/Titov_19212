#include "Printer.h"

void Printer::printResultComp(Round &result, int round_number) {
    if (round_number < 0) {
        std::cout << "Score: " << std::endl;
    } else {
        std::cout << "Round " << round_number << ": " << std::endl;
    }
    std::cout << "    " << std::setw(20) << std::left << result.names[0]
              << std::right << result.score[0]
              << std::endl;
    std::cout << "    " << std::setw(20) << std::left << result.names[1]
              << std::right << result.score[1]
              << std::endl;
    std::cout << "    " << std::setw(20) << std::left << result.names[2]
              << std::right << result.score[2]
              << std::endl;
}

void Printer::printDetails(Round &interim_result) {
    std::cout << std::setw(25);
    std::cout << std::left << interim_result.names[0];
    std::cout << std::setw(11);
    if (interim_result.decisions_hist0.back() == COOPERATE)
        std::cout << std::right << "COOPERATING";
    else
        std::cout << std::right << "DEFECTING";
    std::cout << " " << interim_result.score[0] << std::endl;

    std::cout << std::setw(25);
    std::cout << std::left << interim_result.names[1];
    std::cout << std::setw(11);
    if (interim_result.decisions_hist1.back() == COOPERATE)
        std::cout << std::right << "COOPERATING";
    else
        std::cout << std::right << "DEFECTING";
    std::cout << " " << interim_result.score[1] << std::endl;

    std::cout << std::setw(25);
    std::cout << std::left << interim_result.names[2];
    std::cout << std::setw(11);
    if (interim_result.decisions_hist2.back() == COOPERATE)
        std::cout << std::right << "COOPERATING";
    else
        std::cout << std::right << "DEFECTING";
    std::cout << " " << interim_result.score[2] << std::endl;
}

void Printer::printWinner(std::vector<std::string> &winners, bool draw) {
    if (draw) {
        std::cout << "Draw: ";
        for (auto &i : winners) {
            std::cout << i << ", ";
        }
        std::cout << std::endl;
    } else {
        std::cout << "Winner: " << winners[0] << std::endl;
    }
}