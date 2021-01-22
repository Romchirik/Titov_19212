#include "gamewidget.h"

void GameWidget::openFileAsTemplate() {}

void GameWidget::openFileAsProject() {
    stopGame();
    QString filepath = QFileDialog::getOpenFileName(
        nullptr, tr("Open Project"), "", tr("Golly generated files (*.rle)"));
    size_t ok = file_handler.parseFile(filepath);
    if (!ok) {
        field.setNewField(file_handler.getParsedField());
        rule_handler.parseInputRule(file_handler.getParsedRule());
    } else {
        switch (ok) {
        case BAD_READ:
            QMessageBox::warning(this, tr("open file"),
                                 tr("bad file encoding"));
            break;
        case ACCESS_DENIED:
            QMessageBox::warning(this, tr("open file"), tr("access denied"));

            break;
        case WRONG_FORMAT:
            QMessageBox::warning(this, tr("open file"), tr("access denied"));
            break;
        default:
            QMessageBox::warning(this, tr("open file"), tr("unknown error"));
            break;
        }
    }
}

void GameWidget::saveProject() {
    stopGame();
    QString filepath = QFileDialog::getSaveFileName(
        this, tr("Save Project"), "", tr("Golly generated rle (*.rle)"));
    file_handler.setModel(field.getFieldToSave());
    file_handler.setRule(rule_handler.getStringRule());
    int ok = file_handler.saveFile(filepath);
    if (ok) {
        switch (ok) {
        case ACCESS_DENIED:
            QMessageBox::warning(this, tr("save file"), tr("access denied"));
            break;
        default:
            QMessageBox::warning(this, tr("save file"), tr("unknown error"));
            break;
        }
    }
}

GameWidget::GameWidget(QWidget *parent) : QWidget(parent), timer(new QTimer()) {
    setMouseTracking(true);
    timer->setInterval(DEFAULT_TIMER_INTERVAL);
    connect(timer, SIGNAL(timeout()), this, SLOT(updateModel()));
}

void GameWidget::setRule(const QString &text) {
    rule_handler.parseInputRule(text);
}

GameWidget::~GameWidget() { delete timer; }

void GameWidget::setInterval(int interval) { timer->setInterval(interval); }

void GameWidget::startGame() {
    emit lockSettings(true);
    set_template = false;
    timer->start();
}

void GameWidget::resizeX(const QString &x) {
    bool ok;
    size_t new_w = x.toUInt(&ok);
    if (x.length() == 0) {
        return;
    }
    if (!ok) {
        QMessageBox::warning(this, tr("invalid argument"),
                             tr("invalid universe width"));
    } else if (new_w != field.getWidth()) {
        field.resize(new_w, field.getHeight());
    }
}

QString &GameWidget::getRule() { return rule_handler.getStringRule(); }

void GameWidget::resizeY(const QString &y) {
    if (y.length() == 0) {
        return;
    }
    bool ok;

    size_t new_h = y.toUInt(&ok);
    if (!ok) {
        QMessageBox::warning(this, tr("invalid argument"),
                             tr("invalid universe height"));

    } else if (new_h != field.getHeight()) {
        field.resize(field.getWidth(), new_h);
    }
}

void GameWidget::stopGame() {
    emit lockSettings(false);
    timer->stop();
}

void GameWidget::resetGame() {
    stopGame();
    field.reset();
    update();
}

void GameWidget::updateModel() {
    if (!field.update(rule_handler)) {
        stopGame();
        QMessageBox::information(this, tr("Game stopped"),
                                 tr("game lost sense :( \n"
                                    "Future generations will be the same"),
                                 QMessageBox::Ok);
    }
    update();
}

void GameWidget::paintEvent(QPaintEvent *) {
    QPainter painter(this);
    drawField(painter);
}
void GameWidget::mousePressEvent(QMouseEvent *e) {
    size_t x = e->x() / cell_size;
    size_t y = e->y() / cell_size;

    if (!(x > field.getWidth() || y > field.getHeight())) {

        switch (e->button()) {
        case Qt::LeftButton: {
            if (!set_template) {
                field[x][y] = true;
            } else {
                field.applyTemplate();
            }
            break;
        }
        case Qt::RightButton: {
            if (!set_template) {
                field[x][y] = false;
            } else {
                set_template = false;
            }

            break;
        }
        default:
            break;
        }
    }
    update();
}

void GameWidget::mouseMoveEvent(QMouseEvent *e) {
    size_t x = e->x() / cell_size;
    size_t y = e->y() / cell_size;
    if ((x < field.getWidth() && y < field.getHeight())) {

        if (e->buttons() & Qt::LeftButton && !set_template) {
            if (!field[x][y]) {
                field[x][y] = true;
            }
        }
        if (e->buttons() & Qt::RightButton && !set_template) {
            if (field[x][y]) {
                field[x][y] = false;
            }
        }
        field.setTemplateOrigin(x, y);
    }

    update();
}

void GameWidget::wheelEvent(QWheelEvent *e) {
    QPoint a = e->angleDelta();
    cell_size += a.ry() / 120;
    update();
}

void GameWidget::drawField(QPainter &painter) {

    // draw grid
    painter.setBrush(Qt::gray);
    for (size_t x = 0; x < field.getWidth() + 1; x++) {
        painter.drawLine(x * cell_size, 0, x * cell_size,
                         field.getHeight() * cell_size);
    }
    for (size_t y = 0; y < field.getHeight() + 1; y++) {
        painter.drawLine(0, y * cell_size, field.getWidth() * cell_size,
                         y * cell_size);
    }

    // draw cells
    painter.setBrush(Qt::black);
    for (size_t x = 0; x < field.getWidth(); x++) {
        for (size_t y = 0; y < field.getHeight(); y++) {
            if (field.if_draw(x, y)) {
                painter.drawRect(cell_size * x, cell_size * y, cell_size,
                                 cell_size);
            }
        }
    }
}
