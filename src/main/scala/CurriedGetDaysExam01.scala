import java.util.Calendar

import org.joda.time.format.DateTimeFormat
import org.joda.time.{DateTime, PeriodType, Period}

/**
 * Created by skplanet on 2016-03-21.
 */
object CurriedGetDaysExam01 {
  def main(args: Array[String]): Unit = {
    println("PERIOD-DAYS: " + getPeriod("20150328", "20160328", PeriodType.days)(creater)(_.getDays))
    println("PERIOD-MONTHS: " + getPeriod("20150328", "20160328", PeriodType.months)(creater)(_.getMonths))
  }

  def getPeriod(start: String, end: String, periodType: PeriodType)(creator: (String, String, PeriodType) => Period)(makePeriod: Period => Int): Int = {
    makePeriod(creator(start, end, periodType))
  }

  def creater(start: String, end: String, periodType: PeriodType): Period = {
    val startDate = DateTime.parse(start, DateTimeFormat.forPattern("yyyyMMdd")).getMillis;
    val endDate = DateTime.parse(end, DateTimeFormat.forPattern("yyyyMMdd")).getMillis;
    new Period(startDate, endDate, periodType)
  }

  def getPeriod2(start: String, end: String, periodType: PeriodType): Int = {
    val period = new Period(DateTime.parse(start, DateTimeFormat.forPattern("yyyyMMdd")).getMillis,
      DateTime.parse(end, DateTimeFormat.forPattern("yyyyMMdd")).getMillis,
      periodType
    )

    if(period.getPeriodType.equals(PeriodType.days())) {
      period.getDays //return
    } else if(period.getPeriodType.equals(PeriodType.months())) {
      period.getMonths //return
    } else {
      0
    }
  }
}
