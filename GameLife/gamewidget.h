#ifndef GAMEWIDGET_H
#define GAMEWIDGET_H

#include <QMessageBox>
#include <QMouseEvent>
#include <QPainter>
#include <QTimer>
#include <QWidget>

#include "fieldmodel.h"
#include "rulehandler.h"

class GameWidget : public QWidget {
    Q_OBJECT
  public:
    explicit GameWidget(QWidget *parent = nullptr);
    ~GameWidget();

    QString &getRule();
    void setTemplate(FieldModel &new_t);
    void setModel(FieldModel &new_m);

  protected:
    void wheelEvent(QWheelEvent *e);
    void paintEvent(QPaintEvent *e);
    void mousePressEvent(QMouseEvent *e);
    void mouseReleaseEvent(QMouseEvent *e);
    void mouseMoveEvent(QMouseEvent *e);

  public slots:
    FieldModel &getModel();
    void resizeX(const QString &x);
    void resizeY(const QString &y);
    void setRule(const QString &text);
    void setInterval(int interval);
    void startGame();
    void stopGame();
    void resetGame();
    void updateGame();

  signals:
    void ruleChanged(QString &rule);
    void sizeChanged(size_t width, size_t height);
    void lockSettings(bool ok);

  private:
    void insertTemplate();
    void updateField();
    void drawField(QPainter &painter);

    size_t getX(int row_x);
    size_t getY(int row_y);
    int countNeighbours(const size_t x, const size_t y);

  private:
    QTimer *timer;
    RuleHandler rule_handler;
    size_t num_generations = 0;

    FieldModel next_generation;
    FieldModel current_generation;
    FieldModel template_generation;
    int origin_x = 0;
    int origin_y = 0;

    size_t cell_size = 20;

    size_t template_x = 0;
    size_t template_y = 0;
    bool set_template = false;
    bool game_stop_flag;
    bool mouse_pressed = false;
    static constexpr size_t DEFAULT_TIMER_INTERVAL = 200;
};

#endif // GAMEWIDGET_H
