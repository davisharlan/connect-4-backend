package components.requests

import components.BoardState

final case class ApplyMoveRequest(boardState: BoardState, move: Int)
