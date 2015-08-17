package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example, Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
 * 
 * @author wb
 * @date 2015-8-8 下午3:47:40
 */
public class _056MergeIntervals {
	public static void main(String[] args) {
		Interval i1 = new Interval(1, 3);
		Interval i2 = new Interval(2, 6);
		Interval i3 = new Interval(8, 10);
		Interval i4 = new Interval(15, 18);
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(i2);
		intervals.add(i1);
		intervals.add(i3);
		intervals.add(i4);

		intervals = new ArrayList<Interval>();
		intervals.add(new Interval(1, 10));
		intervals.add(new Interval(2, 3));

		intervals = new ArrayList<Interval>();
		intervals.add(new Interval(1, 4));
		intervals.add(new Interval(4, 5));

		intervals = new ArrayList<Interval>();
		intervals.add(new Interval(1, 4));
		intervals.add(new Interval(0, 2));
		intervals.add(new Interval(3, 5));

		intervals = new _056MergeIntervals().merge(intervals);
		for (Interval i : intervals) {
			System.out.println(i);
		}

	}

	private static Comparator<Interval> com = null;
	static {
		com = new Comparator<Interval>() {

			@Override
			public int compare(Interval o1, Interval o2) {
				// TODO Auto-generated method stub
				return o1.start - o2.start;
			}
		};
	}
	/**
	 * 思路，用一个指标last 一直比较着是否要合并的，合并的那么就一直new这last。
	 * 直到不合并的时候再add到我们的结果里边。
	 * @param intervals
	 * @return
	 */
	public List<Interval> merge(List<Interval> intervals) {
		if (intervals == null || intervals.size() == 0) {
			return Collections.EMPTY_LIST;
		}

		Collections.sort(intervals, com);
		List<Interval> result = new ArrayList<Interval>(intervals.size());
		Interval last = intervals.get(0);
		Iterator<Interval> itor = intervals.iterator();
		Interval cur = null;
		while(itor.hasNext()){
			cur = itor.next();
			if(cur.start>last.end){
				result.add(last);
				last =cur;
			}else{
				//有重合需要继续合并
				int s  = last.start;
				int e = Math.max(last.end, cur.end);
				last = new Interval(s, e);
			}
		}
		//最后需要吧last加上
		result.add(last);
		return result;
	}
}
