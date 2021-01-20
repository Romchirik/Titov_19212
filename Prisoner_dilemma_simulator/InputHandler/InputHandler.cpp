
#include "InputHandler.h"

bool InputHandler::parseInput(int argc, char *argv[]) {
    bool foo_flag = true;
    std::vector<std::string> arguments;
    for (int i = 1; i < argc; i++) {
        arguments.push_back(argv[i]);
    }

    if (arguments.back() == "test") {
        testing = true;
        return true;
    }

    for (auto &i : arguments) {
        if (i.find("mode=") != std::string::npos) {
            i.erase(0, 5);
            if (i == "fast")
                mode = COMPETITION;
            else if (i == "detailed") {
                mode = COMPETITION_DET;
                foo_flag = false;
            } else if (i == "tournament")
                mode = TOURNAMENT;
            else
                return false;
        } else if (i.find("steps=") != std::string::npos) {
            i.erase(0, 6);
            try {
                num_steps = std::stoi(i);
            } catch (std::invalid_argument &e) {
                return false;
            }
        } else {
            strategies.push_back(i);
        }
    }
    if (strategies.size() < 3) {
        return false;
    }
    if (strategies.size() > 3 and foo_flag)
        mode = TOURNAMENT;
    return true;
}

void InputHandler::clear() {
    strategies.clear();
    num_steps = DEFAULT_STEPS;
    mode = COMPETITION_DET;
}
