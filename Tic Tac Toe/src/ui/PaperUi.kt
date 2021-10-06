package ui

import engine.*
import javafx.application.Application
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.layout.GridPane.*
import java.io.File


class PaperUi : Application() {
    private val player1 = Player(playerNumber = 1)
    private val player2 = Player(playerNumber = 2)
    private val players = arrayOf(player1, player2)
    private val gameReferee = GameReferee(paper = Paper(), players = players, playerTurn = player1)

    private val gridPane = GridPane()
    private var scene: Scene? = null
    override fun start(primaryStage: Stage?) {
        gridPane.columnConstraints.addAll(ColumnConstraints(150.0), ColumnConstraints(150.0), ColumnConstraints(150.0))
        gridPane.rowConstraints.addAll(RowConstraints(150.0), RowConstraints(150.0), RowConstraints(150.0))
        for (i in 0..2) {
            for (j in 0..2) {
                val button = Button()
                button.prefWidth = 150.0
                button.prefHeight = 150.0
                button.setOnAction {
                    if (gameReferee.isGameOver() != null) {
                        Alert(Alert.AlertType.INFORMATION, "Game is Over", ButtonType.OK).showAndWait()
                    } else {
                        if (gameReferee.playerTurn.playerNumber == 1) {
                            gameReferee.paper.roundSymbolsOnBoard.add(Symbol(i, j, SymbolShape.ROUND))
                            loadSymbolO(i, j)
                        } else {
                            gameReferee.paper.crossSymbolsOnBoard.add(Symbol(i, j, SymbolShape.CROSS))
                            loadSymbolX(i, j)
                        }
                        button.isDisable = true
                        if (gameReferee.isGameOver() != null) Alert(Alert.AlertType.INFORMATION, "Player ${gameReferee.playerTurn.playerNumber} wins", ButtonType.OK).showAndWait()
                        else if (gameReferee.isDraw()) Alert(Alert.AlertType.INFORMATION, "Game Draw", ButtonType.OK).showAndWait()
                        gameReferee.switchTurn()
                    }

                }
                gridPane.add(button, j, i)
            }
        }
        scene = Scene(gridPane, 450.0, 450.0)

        primaryStage!!.title = "Tic Tac Toe"
        primaryStage.scene = scene
        primaryStage.show()
    }

    private fun loadSymbolO(row: Int, column: Int) {
        gridPane.children.map { child ->
            if (getRowIndex(child) == row && getColumnIndex(child) == column) {
                val img = Image(File("./assets/images/O.png").toURI().toString())
                val view = ImageView(img)
                view.fitWidth = 120.0
                view.fitHeight = 120.0
                (child as Button).graphic = view
            }
        }
    }

    private fun loadSymbolX(row: Int, column: Int) {
        gridPane.children.map { child ->
            if (getRowIndex(child) == row && getColumnIndex(child) == column) {
                val img = Image(File("./assets/images/X.png").toURI().toString())
                val view = ImageView(img)
                view.fitWidth = 120.0
                view.fitHeight = 120.0
                (child as Button).graphic = view
            }
        }
    }

}