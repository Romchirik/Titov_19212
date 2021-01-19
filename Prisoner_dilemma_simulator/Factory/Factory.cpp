#include "Factory.h"

#include <iostream>

std::unique_ptr<Strategy> Factory::create(const std::string &id) {
    auto it = creators.find(id);
    if (it != creators.end()) {
        return it->second();
    } else {
        return std::unique_ptr<Strategy>(nullptr);
    }
}

void Factory::addCreator(const std::string &id,
                         const std::function<std::unique_ptr<Strategy>()> &c) {
    creators.insert(std::make_pair(id, c));
}
