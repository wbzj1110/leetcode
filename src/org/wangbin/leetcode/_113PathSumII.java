package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given
 * sum.
 * 
 * For example: Given the below binary tree and sum = 22, 5 / \ 4 8 / / \ 11 13 4 / \ / \ 7 2 5 1
 * return [ [5,4,11,2], [5,8,4,5] ]
 * 
 * @author wb
 * @date 2015-9-21 ÉÏÎç8:36:39
 */
public class _113PathSumII {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(4);
        node.right = new TreeNode(8);
        node.right.left = new TreeNode(13);
        node.right.right = new TreeNode(4);
        node.right.right.right = new TreeNode(1);
        node.right.right.left = new TreeNode(5);
        node.left.left = new TreeNode(11);
        node.left.left.left=new TreeNode(7);
        node.left.left.right=new TreeNode(2);
        List<List<Integer>> result = new _113PathSumII().pathSum(node, 22);
        Util.showListInListInteger(result);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root==null){
            return Collections.EMPTY_LIST;
        }
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        List<Integer> temp = new LinkedList<Integer>();
        helper(root,sum,result,temp);
        return result;
    }

    private void helper(TreeNode root, int sum, List<List<Integer>> result, List<Integer> temp) {
        // TODO Auto-generated method stub
        if(root==null){
            return;
        }
        temp.add(root.val);
        sum -= root.val;
        if(root.left==null && root.right==null){
            if(sum==0){
                result.add(new ArrayList<Integer>(temp));
            }
        }else{
            if(root.left!=null){
                helper(root.left, sum, result, temp);
            }
            if(root.right!=null){
                helper(root.right, sum, result, temp);
            }
        }
        temp.remove(temp.size()-1);
    }
}
