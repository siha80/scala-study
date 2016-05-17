import org.joda.time.PeriodType

val period = CurriedGetDaysExam01.creater("20150328", "20160328", PeriodType.days())


def factorial(n: Int): Int = {
  def loop(acc: Int, n:Int) : Int =
    if(n == 0) acc else loop(acc * n, n - 1)
  loop(1, n)
}

factorial(4)

import math.abs

val tolerance = 0.0001
def isCloseEnough(x: Double, y: Double) =
  abs((x - y) / x) / x < tolerance

def fixedPoint(f: Double => Double)(firstGuess:Double) = {
  def iterate(guess: Double) : Double = {
    println("Guess = " + guess)
    val next = f(guess)
    if(isCloseEnough(guess,next)) next
    else iterate(next)
  }
  iterate(firstGuess)
}

def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2
def sqrt(x: Double) = fixedPoint(averageDamp(y => x / y))(1)
sqrt(2)

