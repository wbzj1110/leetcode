package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from
 * left to right, level by level from leaf to root).
 * 
 * For example: Given binary tree {3,9,20,#,#,15,7}, 3 / \ 9 20 / \ 15 7 return its bottom-up level
 * order traversal as: [ [15,7], [9,20], [3] ] confused what "{1,#,2,3}" means? > read more on how
 * binary tree is serialized on OJ.
 * 
 * @author wb
 * @date 2015-9-19 上午8:40:17
 */
public class _107BinaryTreeLevelOrderTraversalII {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);
        List<List<Integer>> result = new _107BinaryTreeLevelOrderTraversalII().levelOrderBottom(node);
        Util.showListInListInteger(result);
    }
    /**
     * 囧 好水的题目。。。。
     * 放入最后结果的时候利用LinkedList的特性即可
     * result.add(0, temp);即在开头插入结果
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root==null){
            return Collections.EMPTY_LIST;
        }
        List<List<Integer>>result = new LinkedList<List<Integer>>();
        List<Integer> temp = new LinkedList<Integer>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int curLevel = 1;
        int nextLevel = 0;
        while(!queue.isEmpty()){
            TreeNode tempTree = queue.poll();
            temp.add(tempTree.val);
            curLevel--;
            if(tempTree.left!=null){
                nextLevel++;
                queue.add(tempTree.left);
            }
            if(tempTree.right!=null){
                nextLevel++;
                queue.add(tempTree.right);
            }
            if(curLevel==0){
                curLevel = nextLevel;
                nextLevel = 0;
                result.add(0, temp);
                temp=new LinkedList<Integer>();
            }
            
        }
        return result;
    }
}
