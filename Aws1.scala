/*
 * https://github.com/seratch/AWScala
 */

object Aws1 {

  implicit val region: awscala.Region = Region0.US_EAST_1
  implicit val s3 = S3()


  val bucketNameOne = "reonomy-eoin-development"
  val bucketNameTwo = "eoin-dev-test-two"
  val buckets: Seq[Bucket] = s3.buckets
  val bucket = buckets.filter(b => b.name == bucketNameTwo).head

  val dir = bucket.get("temp/")
  val file = File("/Users/eoconnor/code/plissken/data/losangeles/assessor-publicily-owned-parcels/2016-04-20T14:10:15.869-0400.backup")


  // val listObjects = s3.listObjects("")
  // val files = s3.listObjects("")
  def run = {
    s3.ls(bucket, "").map {
      case Left(directoryPrefix) => directoryPrefix
      case Right(s3ObjectSummary) => s3ObjectSummary.getKey
    }
    // bucket.put( "sample/" + f.name, f.toJava)
  }

  def run2 = {
    s3.ls(bucket, "")
  }

  def put = {
    val r = bucket.putObject("test.txt", file.toJava)
    r
  }

  def create = {
    s3.createBucket(bucketNameTwo)
  }

  def dirs(filter: String = "") = s3.ls(bucket, filter).map{ ei => ei.left.toOption}.flatten.toList
  def files(filter: String = "")  = s3.ls(bucket, filter).map{ ei => ei.right.toOption}.flatten.toList
  
  def getMostRecentlyCollectedFile = {
    val theFiles = files("")
    val s = theFiles match {
      case (children: List[S3ObjectSummary]) if children.isEmpty => None
      case _ => {
        val result = theFiles.reduce{
          (a, b) => if(a.getLastModified.after(b.getLastModified)) a else b
        }
        s3.get(bucket, result.getKey)
      }
    }
    val tmp: JFile = JFile.createTempFile("s3test", "");
    val in = s.get.getObjectContent()
    Files.copy(in, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING )
    tmp
  }

  def getMostRecentlyCollectedFile2 = {
      val theFiles = files("")
      theFiles match {
        case (children: List[S3ObjectSummary]) if children.isEmpty => None
        case _ => {
          val result = theFiles.reduce{
            (a, b) => if(a.getLastModified.after(b.getLastModified)) a else b
          }
          s3.get(bucket, result.getKey).map{ i =>

            val tmp: JFile = JFile.createTempFile("s3test", "");
            val in = i.getObjectContent
            Files.copy(in, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING )
            tmp
          }
        }
      }
    }

}

