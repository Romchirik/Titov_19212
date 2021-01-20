#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include "gamewidget.h"
#include <QDebug>
#include <QFile>
#include <QFileDialog>
#include <QMainWindow>
#include <QTextStream>

#include "filehandler.h"

QT_BEGIN_NAMESPACE
namespace Ui {
class MainWindow;
}
QT_END_NAMESPACE

class MainWindow : public QMainWindow {
    Q_OBJECT

  public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

  public slots:
    void sizeChanged(size_t width, size_t height);
    void textChanged();
    void lockSettings(bool f);
  private slots:
    void on_actionOpen_triggered();

    void on_actionOpen_as_template_triggered();

    void on_actionSave_as_triggered();

  private:
    FileHandler f_handler;
    GameWidget *game;
    Ui::MainWindow *ui;
};
#endif // MAINWINDOW_H
