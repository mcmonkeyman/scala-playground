object URLExtractor {

  val Pattern = "http://www..*.com".r
  def unapply(url: String): Boolean = {
    url match {
      case Pattern() => true
      case _ => false
    }
  }
}

"http://www.fun.com" match {
  case URLExtractor() => println("match");
  case _ => println("other")
}

