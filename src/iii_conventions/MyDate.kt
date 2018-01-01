package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {

    override fun compareTo(other: MyDate): Int = when {
        year != other.year   -> year - other.year
        month != other.month -> month - other.month
        else                 -> dayOfMonth - other.dayOfMonth
    }

}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.plus(interval: TimeInterval) = this.addTimeIntervals(interval, 1)

operator fun MyDate.plus(repeatedInterval: RepeatedTimeInterval) =
    this.addTimeIntervals(repeatedInterval.interval, repeatedInterval.occurences)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(n: Int) = RepeatedTimeInterval(this, n)


class DateRange(val start: MyDate, val endInclusive: MyDate): Iterable<MyDate> {

    // Note - converting this to a range check as suggested by intellij will result
    // in a stack overflow presumably due to the rangeTo extenstion function above.
    operator fun contains(d: MyDate): Boolean = d >= start && d <= endInclusive

    override fun iterator(): Iterator<MyDate> = DateIterator(DateRange(start, endInclusive))

}

class DateIterator(val dateRange: DateRange): Iterator<MyDate> {

    private var current = dateRange.start

    override fun hasNext(): Boolean = current <= dateRange.endInclusive

    override fun next(): MyDate {
        val nextDate = current
        current = current.nextDay()
        return nextDate
    }

}

class RepeatedTimeInterval(val interval: TimeInterval, val occurences: Int)
