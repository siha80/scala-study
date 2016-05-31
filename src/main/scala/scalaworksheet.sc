def maxnumber(data: List[Int]): Int = data.sortWith((a, b) => a.toString.concat(b.toString) > b.toString.concat(a.toString)).mkString.toInt

maxnumber(List(23, 2))
maxnumber(List(21, 2))
maxnumber(List(50, 9, 2, 1))
maxnumber(List(1, 2, 3, 4, 5, 12, 32))
