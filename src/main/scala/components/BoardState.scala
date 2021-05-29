package components

final case class BoardState(board: List[List[Int]], turn: Int, winner: Option[Int])
