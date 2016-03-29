val songTitles = List("The White Hare", "Childe the Hunter", "Take no Rogues")

// Song titles
songTitles.map(t => t.toLowerCase)
songTitles.map(_.toLowerCase)


// Word frequencies
val wordFrequencies = ("habitual", 6) :: ("and", 56) :: ("consuetudinary", 2) :: ("additionally", 27) :: ("homely", 5) :: ("society", 13) :: Nil

def wordsWithoutOutliers(wordFrequencies: Seq[(String, Int)]): Seq[String] =
  wordFrequencies.filter(wf => wf._2 > 3 && wf._2 < 25).map(_._1)
  wordsWithoutOutliers(wordFrequencies) // List("habitual", "homely", "society")
