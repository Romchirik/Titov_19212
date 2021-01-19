#pragma once

#include <functional>
#include <memory>
#include <unordered_map>

#include "../StrategiesStuff/include/Strategy.h"

class Factory {
   public:
    Factory() = default;
    ~Factory() = default;

    std::unique_ptr<Strategy> create(const std::string& id);

    void addCreator(const std::string& id,
                    const std::function<std::unique_ptr<Strategy>()>& c);

   private:
    std::unordered_map<std::string, std::function<std::unique_ptr<Strategy>()>>
        creators;
};