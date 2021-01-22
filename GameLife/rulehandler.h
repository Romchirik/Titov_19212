#ifndef RULECONTROLLER_H
#define RULECONTROLLER_H

#include <QDebug>
#include <QString>
#include <algorithm>
#include <cstring>

class RuleHandler {
  public:
    RuleHandler() = default;
    ~RuleHandler() = default;
    bool parseInputRule(const QString &text);
    bool isAlive(const size_t num_neighbours);
    bool isBorn(const size_t num_neighbours);
    QString &getStringRule();

  private:
    QString string_rule = "b3/s23";
    bool born[9] = {false, false, false, true, false,
                    false, false, false, false};
    bool alive[9] = {false, false, true,  true, false,
                     false, false, false, false};
};

#endif // RULECONTROLLER_H
