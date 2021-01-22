#include "mainwindow.h"
#include "gamewidget.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent), game(new GameWidget(this)), ui(new Ui::MainWindow) {

    ui->setupUi(this);
    connect(ui->ruleController, SIGNAL(editingFinished()), this,
            SLOT(textChanged()));
    connect(ui->startButton, SIGNAL(clicked()), game, SLOT(startGame()));
    connect(ui->stopButton, SIGNAL(clicked()), game, SLOT(stopGame()));
    connect(ui->resetButton, SIGNAL(clicked()), game, SLOT(resetGame()));
    connect(ui->speedController, SIGNAL(valueChanged(int)), game,
            SLOT(setInterval(int)));

    connect(ui->sizeXController, SIGNAL(textChanged(const QString &)), game,
            SLOT(resizeX(const QString &)));
    connect(ui->sizeYContorller, SIGNAL(textChanged(const QString &)), game,
            SLOT(resizeY(const QString)));

    connect(game, SIGNAL(sizeChanged(size_t, size_t)), this,
            SLOT(sizeChanged(size_t, size_t)));
    connect(game, SIGNAL(lockSettings(bool)), this, SLOT(lockSettings(bool)));
    ui->fieldLayout->addWidget(game, 1);
    game->setFocus();
}

void MainWindow::lockSettings(bool f) {
    ui->sizeXController->setReadOnly(f);
    ui->sizeYContorller->setReadOnly(f);
    ui->ruleController->setReadOnly(f);
}

void MainWindow::textChanged() {
    QString text = ui->ruleController->text();
    game->setRule(text);
    ui->ruleController->setText(game->getRule());
}

MainWindow::~MainWindow() {
    delete game;
    delete ui;
}

void MainWindow::sizeChanged(size_t width, size_t height) {
    ui->sizeXController->setText(QString::number(width));
    ui->sizeYContorller->setText(QString::number(height));
    update();
}

void MainWindow::on_actionOpen_triggered() {
    game->openFileAsProject();
    //    game->stopGame();
    //    QString filepath = QFileDialog::getOpenFileName(
    //        this, tr("Open Project"), "", tr("Golly generated files
    //        (*.rle)"));
    //    size_t ok = f_handler.parseFile(filepath);
    //    if (!ok) {
    //        game->setModel(f_handler.getParsedModel());
    //        game->setRule(f_handler.getParsedRule());
    //    }
}

void MainWindow::on_actionOpen_as_template_triggered() {
    game->openFileAsTemplate();
    //    game->stopGame();
    //    QString filepath = QFileDialog::getOpenFileName(
    //        this, tr("Open Template"), "", tr("Golly generated files
    //        (*.rle)"));
    //    size_t ok = f_handler.parseFile(filepath);
    //    if (!ok) {
    //        game->setTemplate(f_handler.getParsedModel());
    //    } else {
    //        switch (ok) {
    //        case BAD_READ: {
    //            QMessageBox::warning(this, tr("GameLife"), tr("Can't save
    //            file"),
    //                                 QMessageBox::Ok);
    //            break;
    //        }
    //        case ACCESS_DENIED: {
    //            QMessageBox::warning(this, tr("GameLife"), tr("Access
    //            denied"),
    //                                 QMessageBox::Ok);
    //            break;
    //        }
    //        case WRONG_FORMAT: {
    //            QMessageBox::warning(this, tr("GameLife"), tr("Wrong file
    //            format"),
    //                                 QMessageBox::Ok);
    //            break;
    //        }
    //        }
    //    }
}

void MainWindow::on_actionSave_as_triggered() {
    game->saveProject();
    //    game->stopGame();
    //    QString filepath = QFileDialog::getSaveFileName(
    //        this, tr("Save Project"), "", tr("Golly generated rle (*.rle)"));
    //    f_handler.setModel(game->getModel());
    //    f_handler.setRule(game->getRule());
    //    bool ok = f_handler.saveFile(filepath);
    //    if (ok) {
    //        QMessageBox::warning(this, tr("GameLife"), tr("Can't save file"),
    //                             QMessageBox::Ok);
    //    }
}
