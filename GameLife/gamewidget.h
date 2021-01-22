#ifndef GAMEWIDGET_H
#define GAMEWIDGET_H

#include "field.h"
#include "fieldmodel.h"
#include "filehandler.h"
#include "rulehandler.h"

#include <QFileDialog>
#include <QMessageBox>
#include <QMouseEvent>
#include <QPainter>
#include <QTimer>
#include <QWidget>

class GameWidget : public QWidget {
    Q_OBJECT
  public:
    explicit GameWidget(QWidget *parent = nullptr);
    ~GameWidget();

    QString &getRule();

  protected:
    void wheelEvent(QWheelEvent *e);
    void paintEvent(QPaintEvent *e);
    void mousePressEvent(QMouseEvent *e);
    void mouseMoveEvent(QMouseEvent *e);

  public slots:
    void resizeX(const QString &x);
    void resizeY(const QString &y);
    void setRule(const QString &text);
    void setInterval(int interval);
    void startGame();
    void stopGame();
    void resetGame();
    void openFileAsTemplate();
    void openFileAsProject();
    void saveProject();
    void updateModel();

  signals:
    void ruleChanged(QString &rule);
    void sizeChanged(size_t width, size_t height);
    void lockSettings(bool ok);

  private:
    void drawField(QPainter &painter);

    size_t getX(int row_x);
    size_t getY(int row_y);
    int countNeighbours(const size_t x, const size_t y);

  private:
    QTimer *timer;
    RuleHandler rule_handler;
    FileHandler file_handler;
    size_t num_generations = 0;

    FieldModel field;

    int origin_x = 0;
    int origin_y = 0;

    size_t cell_size = 20;

    size_t template_x = 0;
    size_t template_y = 0;
    bool set_template = false;
    bool game_stop_flag;
    static constexpr size_t DEFAULT_TIMER_INTERVAL = 200;
};

#endif // GAMEWIDGET_H
