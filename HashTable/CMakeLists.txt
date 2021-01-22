cmake_minimum_required(VERSION 3.16)

project(HashTable)

set(CMAKE_CXX_STANDARD 14)
add_compile_options(-g)
add_subdirectory(googletest)

add_executable(tests main.cpp HashTable.h tests.cpp)

target_link_libraries(tests gtest gtest_main)
