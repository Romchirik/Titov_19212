cmake_minimum_required(VERSION 3.16)
project(Prisoner_dilemma_simulator)

set(CMAKE_CXX_STANDARD 14)

link_directories(StrategiesStuff/)

add_subdirectory(googletest)
include_directories(${gtest_SOURCE_DIR}/include ${gtest_SOURCE_DIR})

add_executable(Prisoner_dilemma_simulator 
        main.cpp
        tests.cpp
        Factory/Factory.cpp
        Simulator/Simulator.cpp
        InputHandler/InputHandler.cpp
        Printer/Printer.cpp
        )

target_link_libraries(Prisoner_dilemma_simulator -ldl)
target_link_libraries(Prisoner_dilemma_simulator gtest gtest_main)