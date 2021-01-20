#include "rulehandler.h"

bool RuleHandler::isAlive(const size_t num_neighbours) {
    for (auto &i : survive) {
        if (i == num_neighbours) {
            return true;
        }
    }
    return false;
}

bool RuleHandler::isBorn(const size_t num_neighbours) {
    for (auto &i : born) {
        if (i == num_neighbours) {
            return true;
        }
    }
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
                if (std::find(born_tmp.begin(), born_tmp.end(),
                              i.digitValue()) == born_tmp.end()) {
                    born_tmp.push_back(i.digitValue());
                }
            }
        }
        if (survive_flag) {
            if (i.isDigit()) {
                if (std::find(survive_tmp.begin(), survive_tmp.end(),
                              i.digitValue()) == survive_tmp.end()) {
                    survive_tmp.push_back(i.digitValue());
                }
            }
        }
        if (bad_read) {
            return false;
        }
    }
    if (survive_tmp.empty() || born_tmp.empty()) {
        return false;
    }
    survive = survive_tmp;
    born = born_tmp;
    std::sort(survive.begin(), survive.end());
    std::sort(born.begin(), born.end());
    string_rule.clear();
    string_rule += "B";
    for (auto i : born) {
        string_rule += QString::number(i);
    }
    string_rule += "/S";
    for (auto i : survive) {
        string_rule += QString::number(i);
    }
    return true;
}
