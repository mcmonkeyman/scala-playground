case class Customer(age: Int)
class Cigarettes
case class UnderAgeException(message: String) extends Exception(message)
def buyCigarettes(customer: Customer): Cigarettes = {
  if (customer.age < 16)
    throw UnderAgeException(s"Customer must be older than 16 but was ${customer.age}")
  else new Cigarettes
}



val youngCustomer = Customer(15)
try {
  buyCigarettes(youngCustomer)
  "Yo, here are your cancer sticks! Happy smokin'!"
} catch {
    case UnderAgeException(msg) => msg
}

import scala.util.Try
import java.net.URL
def parseURL(url: String): Try[URL] = Try(new URL(url))

val url = parseURL("garbage") getOrElse new URL("http://duckduckgo.com")

parseURL("http://danielwestheide.com").map(_.getProtocol)
parseURL("garbage").map(_.getProtocol)

import java.io.InputStream
def inputStreamForURL(url: String) = parseURL(url).map { u =>
  Try(u.openConnection()).map(conn => Try(conn.getInputStream))
}

def inputStreamForURL2(url: String): Try[InputStream] = parseURL(url).flatMap { u =>
  Try(u.openConnection()).flatMap(conn => Try(conn.getInputStream))
}

inputStreamForURL("http://www.google.com")
inputStreamForURL2("http://www.google.com")
 


import scala.io.Source
def getURLContent(url: String): Try[Iterator[String]] =
  for {
    url <- parseURL(url)
    connection <- Try(url.openConnection())
    is <- Try(connection.getInputStream)
    source = Source.fromInputStream(is)
  } yield source.getLines()


import scala.util.Success
import scala.util.Failure
getURLContent("http://danielwestheide.com/foobar") match {
  case Success(lines) => lines.foreach(println)
  case Failure(ex) => println(s"Problem rendering URL content: ${ex.getMessage}")
}

import java.net.MalformedURLException
import java.io.FileNotFoundException
val content = getURLContent("garbage") recover {
  case e: FileNotFoundException => Iterator("Requested page does not exist")
  case e: MalformedURLException => Iterator("Please make sure to enter a valid URL")
  case _ => Iterator("An unexpected error has occurred. We are so sorry!")
}
