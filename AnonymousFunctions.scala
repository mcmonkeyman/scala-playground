val songTitles = List("The White Hare", "Childe the Hunter", "Take no Rogues")

// Song titles
songTitles.map(t => t.toLowerCase)
songTitles.map(_.toLowerCase)


// Word frequencies
val wordFrequencies = ("habitual", 6) :: ("and", 56) :: ("consuetudinary", 2) :: ("additionally", 27) :: ("homely", 5) :: ("society", 13) :: Nil

def wordsWithoutOutliers(wordFrequencies: Seq[(String, Int)]): Seq[String] =
  wordFrequencies.filter(wf => wf._2 > 3 && wf._2 < 25).map(_._1)
  wordsWithoutOutliers(wordFrequencies) // List("habitual", "homely", "society")

def wordsWithoutOutliers2(wordFrequencies: Seq[(String, Int)]): Seq[String] =
  wordFrequencies.filter { case (_, f) => f > 3 && f < 25 } map { case (w, _) => w }
  wordsWithoutOutliers2(wordFrequencies) // List("habitual", "homely", "society")



val predicate: ((String, Int)) => Boolean = { case (_, f) => f > 3 && f < 25 }
val transformFn: ((String, Int)) => String = { case (w, _) => w }


val pf: PartialFunction[(String, Int), String] = {
  case (word, freq) if freq > 3 && freq < 25 => word
}

wordFrequencies.collect(pf)

def wordsWithoutOutliers3(wordFrequencies: Seq[(String, Int)]): Seq[String] =
  wordFrequencies.collect { case (word, freq) if freq > 3 && freq < 25 => word }
