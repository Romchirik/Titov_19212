#ifndef FILEPARSER_H
#define FILEPARSER_H

#include "fieldmodel.h"
#include <QDebug>
#include <QFile>
#include <QFileInfo>
#include <QString>
#include <QStringList>

#define OK 0
#define ACCESS_DENIED 1
#define BAD_READ 2
#define WRONG_FORMAT 3

class FileHandler {
  public:
    FileHandler() = default;
    ~FileHandler() = default;
    size_t parseFile(QString &filepath);
    size_t saveFile(QString &filepath);

    void setRule(QString &r);
    void setModel(FieldModel &m);
    QString &getParsedRule();
    FieldModel &getParsedModel();
    void reset();

  private:
    size_t parseSettings(QString &line);

  private:
    QString rule;
    FieldModel model;
};

#endif // FILEPARSER_H
