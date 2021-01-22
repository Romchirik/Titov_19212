#include "filehandler.h"

inline QChar getStateReversed(bool a) { return (a) ? 'o' : 'b'; }

inline bool getState(QChar a) { return (a == 'o') ? true : false; }

size_t FileHandler::saveFile(QString &filepath) {

    QFile file(filepath);
    QString raw_field;
    QString header;
    if (!file.open(QIODevice::WriteOnly | QIODevice::Text))
        return ACCESS_DENIED;

    QTextStream out(&file);
    for (size_t y = 0; y < model.getHeight(); y++) {
        size_t cells_counter = 1;
        bool last_state = model[0][y];
        for (size_t x = 1; x < model.getWidht(); x++) {
            if (model[x][y] == last_state) {
                cells_counter++;
            } else {
                if (cells_counter > 1) {
                    raw_field += QString::number(cells_counter);
                }
                raw_field += getStateReversed(last_state);
                last_state = model[x][y];
                cells_counter = 1;
            }
        }
        if (cells_counter > 1) {
            raw_field += QString::number(cells_counter);
        }
        raw_field += getStateReversed(last_state);
        raw_field += "$";
    }

    header += "x = ";
    header += QString::number(model.getWidht());
    header += ", ";
    header += "y = ";
    header += QString::number(model.getHeight());
    header += ", ";
    header += "rule = ";
    header += rule;

    out << header << '\n';

    size_t out_symbols_counter = 0;
    for (auto &i : raw_field) {
        out << i;
        out_symbols_counter++;
        if (out_symbols_counter == 70) {
            out << '\n';
        }
    }

    return OK;
}

size_t FileHandler::parseSettings(QString &line) {
    size_t width = 0;
    size_t height = 0;
    bool ok;

    QStringList pieces = line.split(",");
    if (pieces.length() != 3) {
        return BAD_READ;
    }
    pieces[0].remove("x = ");
    pieces[1].remove(" y = ");
    pieces[2].remove(" rule = ");

    width = pieces[0].toUInt(&ok);
    if (!ok) {
        return BAD_READ;
    }
    height = pieces[1].toUInt(&ok);
    if (!ok) {
        return BAD_READ;
    }
    model.resize_reset(width, height);
    rule = pieces[2];
    return OK;
}

size_t FileHandler::parseFile(QString &filepath) {
    size_t current_line = 0;
    size_t current_column = 0;
    QFile file(filepath);
    QString row_field("");

    if (QFileInfo(filepath).completeSuffix() != "rle") {
        return WRONG_FORMAT;
    }

    if (!file.open(QIODevice::ReadOnly | QIODevice::Text)) {
        return ACCESS_DENIED;
    }

    QTextStream data_stream(&file);

    while (!data_stream.atEnd()) {
        QString line = data_stream.readLine();
        if (line[0] == '#') {
            continue;
        } else if (line[0] == 'x') {
            if (!parseSettings(line)) {
                continue;
            } else {
                return BAD_READ;
            }
        } else {
            row_field += line;
        }
    }

    QString num_cells;
    auto iter = row_field.begin();
    while (iter != row_field.end()) {
        if ((*iter).isDigit()) {
            num_cells += *iter;
        } else if ((*iter).isLetter()) {
            if (num_cells.length() == 0) {
                model[current_column][current_line] = getState(*iter);
                current_column++;
            } else {
                bool ok;
                size_t counter = num_cells.toUInt(&ok);
                if (ok) {
                    for (size_t i = 0; i < counter; i++) {
                        model[current_column][current_line] = getState(*iter);
                        current_column++;
                    }
                }
                num_cells.clear();
            }
        } else if (*iter == '$') {
            if (num_cells.length() != 0) {
                bool ok;
                size_t num_lines_to_skip = num_cells.toUInt(&ok);

                if (ok) {
                    current_line += num_lines_to_skip;
                } else {
                    return BAD_READ;
                }
            } else {
                current_line++;
            }
            current_column = 0;
            num_cells.clear();
        } else if (*iter == '!') {
            break;
        } else {
            return BAD_READ;
        }
        iter++;
    }
    return OK;
}

void FileHandler::setRule(QString &r) { rule = r; }

void FileHandler::setModel(Field &m) { model = m; }

QString &FileHandler::getParsedRule() { return rule; }

Field &FileHandler::getParsedField() { return model; }

void FileHandler::reset() { model.reset(); }
