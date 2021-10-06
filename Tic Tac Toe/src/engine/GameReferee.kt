package engine

class GameReferee(var paper: Paper, var gameState: GameState = GameState.GAME_PLAYING, var playerTurn: Player, var players: Array<Player>) {
    fun isGameOver(): SymbolShape? {
        if (hasThreeSameIndices(paper.roundSymbolsOnBoard)) return SymbolShape.ROUND
        if (hasThreeSameRows(paper.roundSymbolsOnBoard)) return SymbolShape.ROUND
        if (hasThreeSameColumns(paper.roundSymbolsOnBoard)) return SymbolShape.ROUND
        if (hasThreeSum2Indices(paper.roundSymbolsOnBoard)) return SymbolShape.ROUND

        if (hasThreeSameIndices(paper.crossSymbolsOnBoard)) return SymbolShape.CROSS
        if (hasThreeSameRows(paper.crossSymbolsOnBoard)) return SymbolShape.CROSS
        if (hasThreeSameColumns(paper.crossSymbolsOnBoard)) return SymbolShape.CROSS
        if (hasThreeSum2Indices(paper.crossSymbolsOnBoard)) return SymbolShape.CROSS
        return null
    }

    fun isDraw(): Boolean {
        if ((paper.crossSymbolsOnBoard.size + paper.roundSymbolsOnBoard.size) == 9 && isGameOver() == null) return true
        return false
    }

    fun hasThreeSameIndices(symbols: ArrayList<Symbol>): Boolean {
        var sameIndicesCount: Short = 0
        for (symbol in symbols) {
            if (symbol.row == symbol.column) sameIndicesCount++
        }
        return sameIndicesCount == 3.toShort()
    }

    fun hasThreeSameRows(symbols: ArrayList<Symbol>): Boolean {
        var indexZeroCount: Short = 0
        var indexOneCount: Short = 0
        var indexTwoCount: Short = 0
        for (symbol in symbols) {
            when (symbol.row) {
                0 -> indexZeroCount++
                1 -> indexOneCount++
                2 -> indexTwoCount++
            }
        }
        return (indexZeroCount == 3.toShort() || indexOneCount == 3.toShort() || indexTwoCount == 3.toShort())
    }

    fun hasThreeSameColumns(symbols: ArrayList<Symbol>): Boolean {
        var indexZeroCount: Short = 0
        var indexOneCount: Short = 0
        var indexTwoCount: Short = 0
        for (symbol in symbols) {
            when (symbol.column) {
                0 -> indexZeroCount++
                1 -> indexOneCount++
                2 -> indexTwoCount++
            }
        }
        return (indexZeroCount == 3.toShort() || indexOneCount == 3.toShort() || indexTwoCount == 3.toShort())
    }

    fun hasThreeSum2Indices(symbols: ArrayList<Symbol>): Boolean {
        var sum2Indices: Short = 0
        for (symbol in symbols) {
            if (symbol.row + symbol.column == 2) sum2Indices++
        }
        return sum2Indices == 3.toShort()
    }

    fun switchTurn() {
        if (playerTurn.playerNumber == 1) playerTurn = players[1]
        else if (playerTurn.playerNumber == 2) playerTurn = players[0]
    }

}