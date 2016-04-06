case class User(
  id: Int,
  firstName: String,
  lastName: String,
  age: Int,
  gender: Option[String])

object UserRepository {
  private val users = Map(1 -> User(1, "John", "Doe", 32, Some("male")),
                          2 -> User(2, "Johanna", "Doe", 30, None))
  def findById(id: Int): Option[User] = users.get(id)
  def findAll = users.values
}


val user1 = UserRepository.findById(1)
if (user1.isDefined) {
  println(user1.get.firstName)
} // will print "John"

val user = User(2, "Johanna", "Doe", 30, None)
println("Gender: " + user.gender.getOrElse("not specified")) // will print "not specified"


user.gender match {
  case Some(gender) => println("Gender: " + gender)
  case None => println("Gender: not specified")
}

UserRepository.findById(2).foreach(user => println(user.firstName)) // prints "Johanna"

val age = UserRepository.findById(1).map(_.age) // age is Some(32)

val gender1 = UserRepository.findById(1).flatMap(_.gender) // gender is Some("male")
val gender2 = UserRepository.findById(2).flatMap(_.gender) // gender is None
val gender3 = UserRepository.findById(3).flatMap(_.gender) // gender is None

//Filter
UserRepository.findById(1).filter(_.age > 30) // Some(user), because age is > 30

//For comprehension
for {
  user <- UserRepository.findById(1)
  gender <- user.gender
} yield gender // results in Some("male")


for {
  User(_, _, _, _, Some(gender)) <- UserRepository.findAll
} yield gender


// Chaining options
case class Resource(content: String)
val resourceFromConfigDir: Option[Resource] = None
val resourceFromClasspath: Option[Resource] = Some(Resource("I was found on the classpath"))
val resource = resourceFromConfigDir orElse resourceFromClasspath
