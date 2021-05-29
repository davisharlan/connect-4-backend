package utils

import components.BoardState

object BoardStateUtils {
  def applyMove(boardState: BoardState, move: Int): Option[BoardState] =
    if (boardState.board(move).last != 0) {
      None
    } else {
      val updatedBoard = boardState.board.updated(
        move,
        boardState
          .board(move)
          .updated(boardState.board(move).indexOf(0), boardState.turn))
      Some(
        BoardState(updatedBoard,
                   if (boardState.turn == 1) 2 else 1,
                   if (BoardUtils.isBoardFull(updatedBoard)) Some(0)
                   else BoardUtils.getWinner(updatedBoard)))
    }

}
