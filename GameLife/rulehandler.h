#ifndef RULECONTROLLER_H
#define RULECONTROLLER_H

#include <QDebug>
#include <QString>
#include <algorithm>
#include <vector>

class RuleHandler {
  public:
    RuleHandler() = default;
    ~RuleHandler() = default;
    bool parseInputRule(const QString &text);
    bool isAlive(const size_t num_neighbours);
    bool isBorn(const size_t num_neighbours);
    QString &getStringRule();

  private:
    QString string_rule = "B3/S23";
    std::vector<size_t> born = {3};
    std::vector<size_t> survive = {2, 3};
};

#endif // RULECONTROLLER_H
