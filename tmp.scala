
def directoryMatchesDb(directory:String, dbSettings: Seq[String]): Boolean = {
  directory.toLowerCase match {
   case "coredata" if (!dbSettings.contains("wellspring") && !("coredata" == "coredata")) => false
   case "source" if (!dbSettings.contains("source") && !("source" == "source")) => false
   case _ => true
  }
}


directoryMatchesDb("coredata",List(""))
