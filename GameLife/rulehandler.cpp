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

    bool tmp_alive[9] = {false, false, false, false, false,
                         false, false, false, false};
    bool tmp_born[9] = {false, false, false, false, false,
                        false, false, false, false};

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
                    tmp_born[i.digitValue()] = true;
                }
            }
        }
        if (survive_flag) {
            if (i.isDigit()) {
                if (i.digitValue() <= 8) {
                    tmp_alive[i.digitValue()] = true;
                }
            }
        }
        if (bad_read) {
            return false;
        }
    }
    for (int i = 0; i < 9; i++) {
        alive[i] = tmp_alive[i];
        born[i] = tmp_born[i];
    }

    string_rule = "b";
    for (int i = 0; i < 9; i++) {
        if (born[i]) {
            string_rule += QString::number(i);
        }
    }
    string_rule += "/s";
    for (int i = 0; i < 9; i++) {
        if (alive[i]) {
            string_rule += QString::number(i);
        }
    }
    return true;
}
