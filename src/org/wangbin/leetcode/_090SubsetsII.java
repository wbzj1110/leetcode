package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 * 
 * Note: Elements in a subset must be in non-descending order. The solution set must not contain
 * duplicate subsets. For example, If nums = [1,2,2], a solution is:
 * 
 * [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
 * 
 * @author wb
 * @date 2015-8-25 下午10:19:23
 */
public class _090SubsetsII {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 2, 2};
        Util.showListInListInteger(new _090SubsetsII().subsetsWithDup2(nums));

    }

    /**
     * 先排序，然后循环迭代 但这样明显感觉慢，因为循环加递归了
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.EMPTY_LIST;
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> item = new ArrayList<Integer>();

        Arrays.sort(nums);
        for (int len = 1; len <= nums.length; len++) {
            // 可能出现的长度为1,2,3、、、、
            helper(nums, 0, len, result, item);
        }
        result.add(new ArrayList<Integer>());
        return result;
    }

    private void helper(int[] nums, int pos, int len, List<List<Integer>> result, List<Integer> item) {
        if (item.size() == len) {
            result.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            if (i != pos && nums[i] == nums[i - 1]) {// 去除重复元素1,2,2,2,2这种
                continue;
            }
            item.add(nums[i]);
            helper(nums, i + 1, len, result, item);
            item.remove(item.size() - 1);
        }
    }

    /**
     * 还是先排序，然后直接递归即可。
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.EMPTY_LIST;
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> item = new ArrayList<Integer>();

        Arrays.sort(nums);
        // 可能出现的长度为1,2,3、、、、
        helper(nums, 0, result, item);
        return result;
    }

    private void helper(int[] nums, int pos, List<List<Integer>> result, List<Integer> item) {
        // TODO Auto-generated method stub
        result.add(new ArrayList<Integer>(item));
        for (int i = pos; i < nums.length; i++) {
            if ( i != pos && nums[i] == nums[i - 1]) {// 去除重复元素1,2,2,2,2这种
                continue;
            }    
            item.add(nums[i]);
            helper(nums, i+1, result, item);
            item.remove(item.size() - 1);
        }
    }
}
