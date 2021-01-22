#include "rulehandler.h"

bool RuleHandler::isAlive(const size_t num_neighbours) {
    if (alive[num_neighbours])
        return true;
    return false;
}

bool RuleHandler::isBorn(const size_t num_neighbours) {
    if (born[num_neighbours])
        return true;
    return false;
}

QString &RuleHandler::getStringRule() { return string_rule; }

bool RuleHandler::parseInputRule(const QString &text) {
    bool born_flag = false;
    bool survive_flag = false;
    bool bad_read = false;

    std::vector<size_t> survive_tmp;
    std::vector<size_t> born_tmp;
    for (auto &i : text) {
        if (i == 'B' || i == 'b') {
            born_flag = true;
            continue;
        }
        if (i == 'S' || i == 's') {
            survive_flag = true;
            continue;
        }
        if (i == '\0' || i == '/') {
            survive_flag = false;
            born_flag = false;
            continue;
        }
        if (born_flag) {
            if (i.isDigit()) {
                if (i.digitValue() <= 8) {
                    alive[i.digitValue()] = true;
                }
            }
        }
        if (survive_flag) {
            if (i.isDigit()) {
                if (i.digitValue() <= 8) {
                    born[i.digitValue()] = true;
                }
            }
        }
        if (bad_read) {
            return false;
        }
    }
    return true;
}
