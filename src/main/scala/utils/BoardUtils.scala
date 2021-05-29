package utils

object BoardUtils {
  def getColumns(board: List[List[Int]]): List[List[Int]] = board
  def getRows(board: List[List[Int]]): List[List[Int]] =
    List
      .range(0, board.head.length)
      .map(i => {
        List.range(0, board.length).map(j => board(j)(i))
      })
  def getDiagonal(board: List[List[Int]],
                  i: Int,
                  j: Int,
                  up: Boolean): List[Int] = up match {
    case true =>
      List
        .range(0, scala.math.min(i + 1, board.length - j))
        .map(k => board(j + k)(i - k))
    case false =>
      List
        .range(0, scala.math.min(board.head.length - i, board.length - j))
        .map(k => board(j + k)(i + k))
  }
  def getDiagonals(board: List[List[Int]]): List[List[Int]] =
    (List
      .range(0, board.head.length)
      .map(i => getDiagonal(board, i, 0, true)) ++ List
      .range(1, board.length)
      .map(j => getDiagonal(board, board.head.length - 1, j, true)) ++ List
      .range(0, board.length)
      .map(j => getDiagonal(board, 0, j, false)) ++ List
      .range(1, board.head.length)
      .map(i => getDiagonal(board, i, 0, false))).filter(_.length > 3)
  def getWinner(board: List[List[Int]]): Option[Int] =
    (getColumns(board) ++ getRows(board) ++ getDiagonals(board))
      .map(
        set =>
          set
            .sliding(4)
            .find(subset => subset.head != 0 && subset.forall(_ == subset.head))
            .map(_.head)
      )
      .reduce(_ orElse _)
  def isBoardFull(board: List[List[Int]]): Boolean = !board.flatten.contains(0)
}
