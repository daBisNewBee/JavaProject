package offer.online

import java.util.*
import kotlin.Comparator

/**
 *
 * 会议室 II
 *
 * 给你一个会议时间安排的数组 intervals ，
 * 每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，
 * 为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，
 * 才能满足这些会议安排。

 *
 * 小顶堆！！用下一个会议的开始时间和上一个会议的结束时间比较，很难想到~~~
 *
 */

fun minMeetingRooms(intervals: Array<IntArray>): Int {
    if(intervals.isEmpty()) return 0
    var priority = PriorityQueue<Int>()
    println(intervals)
    Arrays.sort(intervals, Comparator<IntArray>{a, b -> a[0] - b[0]})
    println(intervals)

    priority.add(intervals[0][1])
    for(i in 1 until intervals.size) {
        if(intervals[i][0] >= priority.peek()) {
            priority.poll()
        }
        priority.add(intervals[i][1])
    }
    return priority.size
}

fun main() {
    var data = Array(3){IntArray(3)}
//    data[0] = intArrayOf(5,6)
//    data[1] = intArrayOf(1,3)
//    data[2] = intArrayOf(9,10)
    data[0] = intArrayOf(1,5)
    data[1] = intArrayOf(8,9)
    data[2] = intArrayOf(8,9)


    println(minMeetingRooms(data))
}