#include <dlfcn.h>

#include <iostream>
#include <memory>
#include <string>

#include "Factory/Factory.h"
#include "InputHandler/InputHandler.h"
#include "Simulator/Simulator.h"
#include "macro.h"

Factory factory;

void *load_strategy_dll(std::string &strategy_name) {
    std::unique_ptr<Strategy> (*creator)();
    std::string filename = "./../StrategiesStuff/build/";
    filename += strategy_name + ".so";

    dlerror();

    void *handle = dlopen(filename.c_str(), RTLD_LAZY);

    if (!handle) {
        std::cerr << "Cannot load strategy: " << strategy_name << std::endl;
        exit(1);
    }

    creator = (std::unique_ptr<Strategy>(*)())dlsym(handle, "create");
    const char *dlsym_error = dlerror();
    if (dlsym_error) {
        std::cerr << "Cannot load symbol: " << dlsym_error << '\n';
        dlclose(handle);
        return nullptr;
    }

    factory.addCreator(strategy_name, creator);
    return handle;
}

int main(int argc, char *argv[]) {
    std::vector<void *> libs;
    InputHandler handler;
    Simulator game = Simulator();
    // if (!handler.parseInput(argc, argv)) {
    //     std::cout << "bad input, input pattern:\n"
    //               << DEFAULT_INPUT_PATTERN;
    //     return 0;
    // }
    handler.mode = COMPETITION_DET;
    handler.num_steps = 10;

    handler.strategies.push_back("random");
    handler.strategies.push_back("random");
    handler.strategies.push_back("random");

    for (auto &i : handler.strategies) {
        void *tmp = load_strategy_dll(i);
        if (!tmp) {
            for (auto &j : libs) {
                dlclose(j);
            }
            return 0;
        }
        libs.push_back(tmp);
    }

    if (!game.addStrategies(handler.strategies)) {
        std::cout << game.error_message;
        return 0;
    }

    game.setMode(handler.mode);
    game.setNumSteps(handler.num_steps);
    game.startGame();

    return 0;
}