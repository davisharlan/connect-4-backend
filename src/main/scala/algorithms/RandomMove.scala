package algorithms

import components.BoardState

import scala.annotation.tailrec
import scala.util.Random

import utils.BoardStateUtils.applyMove

object RandomMove {
  def getMove(): Int = new Random().nextInt(7)
  @tailrec
  def applyRandomMove(boardState: BoardState): BoardState =
    applyMove(boardState, getMove) match {
      case Some(newBoardState) => newBoardState
      case _       => applyRandomMove(boardState)
    }
}
