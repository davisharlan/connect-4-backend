package server

import cats.effect._
import cats.implicits._
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.server.blaze._
import algorithms.RandomMove.applyRandomMove
import components.BoardState
import components.requests.ApplyMoveRequest
import org.http4s.server.middleware.{CORS, CORSConfig}
import utils.BoardStateUtils.applyMove

object HTTPServer extends IOApp {

  implicit val applyMoveRequestDecoder = jsonOf[IO, ApplyMoveRequest]
  implicit val boardStateDecoder = jsonOf[IO, BoardState]

  val httpService = HttpRoutes
    .of[IO] {
      case GET -> Root / "health" => Ok()

      case req @ POST -> Root / "random" => for {
        boardState <- req.as[BoardState]
        resp <- Ok(applyRandomMove(boardState).asJson)
      } yield resp

      case req @ POST -> Root / "applyMove" =>
        for {
          applyMoveRequest <- req.as[ApplyMoveRequest]
          resp <- Ok(applyMove(applyMoveRequest.boardState,
                               applyMoveRequest.move).asJson)
        } yield resp
    }
    .orNotFound

  val originConfig = CORSConfig(
    anyOrigin = true,
    allowCredentials = false,
    maxAge = 86400)

  val corsService = CORS(httpService, originConfig)

  def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO]
      .bindHttp(8081, "0.0.0.0")
      .withHttpApp(corsService)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
}
