def getBashArguments(args: Array[String]): Unit = {
  val Array(directory,script) = args.map(File(_).path)
  println("Executing '%s' in directory '%s'".format(script, directory))
}

def simpleFileManipulations(): Unit = {
  val result  = "ls -al".!!
  println(result)
  ()
}

def simpleChaining(): Unit = {
  "ls" #| "grep .scala" #&& "scalac *.scala" #|| "echo nothing found" lines
}

// This uses ! to get the exit code
def fileExists(name: String) = Seq("test", "-f", name).! == 0

// This "fire-and-forgets" the method, which can be lazily read through
// a Stream[String]
def simpleSourceFilesAt(baseDir: String): Stream[String] = {
  val cmd = Seq("find", baseDir, "-name", "*.scala", "-type", "f")
  cmd.lines
}

// This "fire-and-forgets" the method, which can be lazily read through
// a Stream[String], and accumulates all errors on a StringBuffer
def sourceFilesAt(baseDir: String): (Stream[String], StringBuffer) = {
  val buffer = new StringBuffer()
  val cmd = Seq("find", baseDir, "-name", "*.scala", "-type", "f")
  val lines = cmd lines_! ProcessLogger(buffer append _)
  (lines, buffer)
}

def backupIfNew(fileUrl: String) = {

  val currentFileName = "./current.file"
  val tempFileName = "./tmp.file"

  //saveUrlToFile(fileUrl, tempFileName)
  val currentFile: File = File(currentFileName)
  val tempFile: File =  File(tempFileName)

  if(isNotTheSame(currentFile, tempFile)) {
    println("not the same")
   // Make the new file 
    //saveUrlToFile(fileUrl, currentFileName)
  } else {
    println("the same")
  }
}

def isNotTheSame(fileA: File, fileB: File ) = {
  !isSame(fileA, fileB)
}

def isSame(fileA: File, fileB: File) = {
  fileA === fileB
}

def ckSum(file: File): Tuple2[String, String] = {
 val pattern = "(\\d+) (\\d+).*".r
 val result: String = Seq("ckSum", file.name)!!
 val cleanResult: String = result.trim.stripLineEnd.trim
 val pattern(a, b) = cleanResult 
 (a, b)
}

//
  // private def compare(fileA: File, fileB: File) = {
  //   md5(fileA) == md5(fileB)
  // }
  //
  // private def md5(file: File) = {
  //   val rawResult = s"md5 ${file.pathAsString}".!!
  //   val processedResult = rawResult.split(" ").last
  //   println(processedResult)
  //   processedResult
  // }


// ## exec ./scalas $0 $@
// SCRIPT="$(cd "${0%/*}" 2>/dev/null; echo "$PWD"/"${0##*/}")"
// DIR=`dirname "${SCRIPT}"}`
