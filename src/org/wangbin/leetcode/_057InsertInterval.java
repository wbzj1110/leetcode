package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the
 * intervals (merge if necessary).
 * 
 * You may assume that the intervals were initially sorted according to their
 * start times.
 * 
 * Example 1: Given intervals [1,3],[6,9], insert and merge [2,5] in as
 * [1,5],[6,9].
 * 
 * Example 2: Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in
 * as [1,2],[3,10],[12,16].
 * 
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * 没啥难度~~做出56之后 一个逻辑。
 * 
 * @author wb
 * @date 2015-8-8 下午8:51:15
 */
public class _057InsertInterval {
	public static void main(String[] args) {
		Interval i1 = new Interval(1, 2);
		Interval i2 = new Interval(3, 5);
		Interval i3 = new Interval(6, 7);
		Interval i4 = new Interval(8, 10);
		Interval i5 = new Interval(12, 16);
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(i2);
		intervals.add(i1);
		intervals.add(i3);
		intervals.add(i4);
		intervals.add(i5);
		Interval newInterval = new Interval(4, 9);
		intervals = new _057InsertInterval().insert(intervals, newInterval);
		for (Interval i : intervals) {
			System.out.println(i);
		}
		
	}

	/**
	 * 这道题不仅要insert newInterval同时还要保证能够merge。那么就分情况讨论。
	 * 
	 * 遍历每一个已给出的interval，
	 * 
	 * 当当前的interval的end小于newInterval的start时，说明新的区间在当前遍历到的区间的后面，并且没有重叠，
	 * 所以res添加当前的interval；
	 * 
	 * 当当前的interval的start大于newInterval的end时，说明新的区间比当前遍历到的区间要前面，并且也没有重叠，
	 * 所以把newInterval添加到res里，并更新newInterval为当前的interval；
	 * 
	 * 当当前的interval与newInterval有重叠时，merge interval并更新新的newInterval为merge后的。
	 * 
	 * @param intervals
	 * @param newInterval
	 * @return
	 */
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> res = new ArrayList<Interval>();

		for (Interval each : intervals) {
			if (each.end < newInterval.start) {
				res.add(each);
			} else if (each.start > newInterval.end) {
				res.add(newInterval);
				newInterval = each;
			} else if (each.end >= newInterval.start
					|| each.start <= newInterval.end) {
				int nstart = Math.min(each.start, newInterval.start);
				int nend = Math.max(newInterval.end, each.end);
				newInterval = new Interval(nstart, nend);
			}
		}

		res.add(newInterval);

		return res;
	}
}
