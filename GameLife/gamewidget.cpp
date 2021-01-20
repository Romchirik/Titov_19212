#include "gamewidget.h"

void GameWidget::setModel(FieldModel &new_m) {
    current_generation = new_m;
    next_generation.resize(new_m.getWidht(), new_m.getHeight());
    emit sizeChanged(current_generation.getWidht(),
                     current_generation.getHeight());
    update();
}

void GameWidget::setTemplate(FieldModel &new_t) {
    set_template = true;
    template_generation = new_t;
    update();
}
GameWidget::GameWidget(QWidget *parent) : QWidget(parent), timer(new QTimer()) {
    setMouseTracking(true);
    timer->setInterval(DEFAULT_TIMER_INTERVAL);
    connect(timer, SIGNAL(timeout()), this, SLOT(updateGame()));
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
    } else if (new_w != current_generation.getWidht()) {
        current_generation.resize(new_w, current_generation.getHeight());
        next_generation.resize(new_w, current_generation.getHeight());
    }
}

FieldModel &GameWidget::getModel() { return current_generation; }

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

    } else if (new_h != current_generation.getHeight()) {
        current_generation.resize(current_generation.getWidht(), new_h);
        next_generation.resize(next_generation.getWidht(), new_h);
    }
}

void GameWidget::stopGame() {
    emit lockSettings(false);
    timer->stop();
}

void GameWidget::updateGame() { updateField(); }

void GameWidget::resetGame() {
    stopGame();
    current_generation.reset();
    next_generation.reset();
    update();
}

void GameWidget::updateField() {

    for (size_t x = 0; x < current_generation.getWidht(); x++) {
        for (size_t y = 0; y < current_generation.getHeight(); y++) {
            size_t num_neighbours = countNeighbours(x, y);
            if (current_generation[x][y]) {
                if (rule_handler.isAlive(num_neighbours)) {
                    next_generation[x][y] = true;
                } else {
                    next_generation[x][y] = false;
                }
            } else {
                if (rule_handler.isBorn(num_neighbours)) {
                    next_generation[x][y] = true;
                } else {
                    next_generation[x][y] = false;
                }
            }
        }
    }
    if (current_generation == next_generation) {
        stopGame();
        QMessageBox::information(this, tr("Game stopped"),
                                 tr("game lost sense :( \n"
                                    "Future generations will be the same"),
                                 QMessageBox::Ok);
    }
    current_generation.swap(next_generation);
    update();
}

void GameWidget::paintEvent(QPaintEvent *) {
    QPainter painter(this);
    drawField(painter);
}
void GameWidget::insertTemplate() {
    for (size_t x = 0; x < template_generation.getWidht(); x++) {
        for (size_t y = 0; y < template_generation.getWidht(); y++) {
            current_generation[x + template_x][y + template_y] =
                template_generation[x][y];
        }
    }
}
void GameWidget::mousePressEvent(QMouseEvent *e) {
    mouse_pressed = true;
    size_t x = e->x() / cell_size;
    size_t y = e->y() / cell_size;

    if (!(x > current_generation.getWidht() ||
          y > current_generation.getHeight())) {

        switch (e->button()) {
        case Qt::LeftButton: {
            if (!set_template) {
                current_generation[x][y] = true;
            } else {
                insertTemplate();
            }

            break;
        }
        case Qt::RightButton: {
            if (!set_template) {
                current_generation[x][y] = false;
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

void GameWidget::mouseReleaseEvent(QMouseEvent *e) { mouse_pressed = false; }

void GameWidget::mouseMoveEvent(QMouseEvent *e) {
    size_t x = e->x() / cell_size;
    size_t y = e->y() / cell_size;
    if ((x < current_generation.getWidht() &&
         y < current_generation.getHeight())) {

        if (e->buttons() & Qt::LeftButton && !set_template) {
            if (!current_generation[x][y]) {
                current_generation[x][y] = true;
            }
        }
        if (e->buttons() & Qt::RightButton && !set_template) {
            if (current_generation[x][y]) {
                current_generation[x][y] = false;
            }
        }
        if (y <=
            current_generation.getHeight() - template_generation.getHeight()) {
            template_y = y;
        }
        if (x <=
            current_generation.getWidht() - template_generation.getWidht()) {
            template_x = x;
        }

        if (e->buttons() & Qt::MiddleButton) {
        }
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
    for (size_t x = 0; x < current_generation.getWidht() + 1; x++) {
        painter.drawLine(x * cell_size, 0, x * cell_size,
                         current_generation.getHeight() * cell_size);
    }
    for (size_t y = 0; y < current_generation.getHeight() + 1; y++) {
        painter.drawLine(0, y * cell_size,
                         current_generation.getWidht() * cell_size,
                         y * cell_size);
    }

    // draw cells
    painter.setBrush(Qt::black);
    for (size_t x = 0; x < current_generation.getWidht(); x++) {
        for (size_t y = 0; y < current_generation.getHeight(); y++) {
            if (current_generation[x][y]) {
                painter.drawRect(cell_size * x, cell_size * y, cell_size,
                                 cell_size);
            }
        }
    }
    // draw template
    if (set_template) {
        for (size_t x = 0; x < template_generation.getWidht(); x++) {
            for (size_t y = 0; y < template_generation.getHeight(); y++) {
                if (template_generation[x][y]) {
                    painter.drawRect(cell_size * (x + template_x),
                                     cell_size * (y + template_y), cell_size,
                                     cell_size);
                }
            }
        }
    }
}

inline size_t GameWidget::getX(int row_x) {
    if (row_x < 0) {
        return current_generation.getWidht() + row_x;
    } else {
        return row_x % current_generation.getWidht();
    }
}

inline size_t GameWidget::getY(int row_y) {
    if (row_y < 0) {
        return current_generation.getHeight() + row_y;
    } else {
        return row_y % current_generation.getHeight();
    }
}
int GameWidget::countNeighbours(const size_t x, const size_t y) {
    int num_neighbours = 0;

    // up-left
    num_neighbours += current_generation[getX(x + 1)][getY(y + 1)];
    // left
    num_neighbours += current_generation[getX(x + 1)][getY(y)];

    // down-left
    num_neighbours += current_generation[getX(x + 1)][getY(y - 1)];

    // down
    num_neighbours += current_generation[getX(x)][getY(y - 1)];

    // down-right
    num_neighbours += current_generation[getX(x - 1)][getY(y - 1)];

    // right
    num_neighbours += current_generation[getX(x - 1)][getY(y)];

    // up-right
    num_neighbours += current_generation[getX(x - 1)][getY(y + 1)];

    // up
    num_neighbours += current_generation[getX(x)][getY(y + 1)];

    return num_neighbours;
}
