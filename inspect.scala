import scala.reflect.runtime.universe.{ reify, showCode }
showCode(reify("a" * 2).tree)
showCode(reify(for { i <- 1 to 3; j <- Option(4) } yield i + j).tree)
