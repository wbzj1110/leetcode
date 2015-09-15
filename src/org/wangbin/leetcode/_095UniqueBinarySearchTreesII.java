package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 * 
 * For example, Given n = 3, your program should return all 5 unique BST's shown below.
 * 
 * 1 3 3 2 1 \ / / / \ \ 3 2 1 1 3 2 / / \ \ 2 1 2 3 confused what "{1,#,2,3}" means? > read more on
 * how binary tree is serialized on OJ.
 * 
 * 
 * OJ's Binary Tree Serialization: The serialization of a binary tree follows a level order
 * traversal, where '#' signifies a path terminator where no node exists below.
 * 
 * Here's an example: 1 / \ 2 3 / 4 \ 5 The above binary tree is serialized as
 * "{1,2,3,#,#,4,#,#,5}".
 * 
 * @author wb
 * @date 2015-9-15 上午8:51:39
 */
public class _095UniqueBinarySearchTreesII {
    public static void main(String[] args) {
        List<TreeNode> result = new _095UniqueBinarySearchTreesII().generateTrees(3);
        for(TreeNode tree:result){
            System.out.println(tree);
        }
    }

    /**
     * 这道题目的依据是bst的定义： 当数组为 1，2，3，4，.. i，.. n时，基于以下原则的BST建树具有唯一性： 以i为根节点的树，其左子树由[1, i-1]构成，
     * 其右子树由[i+1, n]构成。
     * 递归1、、、n
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        return generator(1,n);//从1作为root开始，到n作为root结束
    }

    private List<TreeNode> generator(int left, int right) {
        // TODO Auto-generated method stub
        List<TreeNode> res = new ArrayList<TreeNode>();
        if (left > right){
            res.add(null);//没有结点了
            return res;
        }
        for(int i = left;i <= right ;i++){
            //以i作为根节点，左子树由[1,i-1]构成
            List<TreeNode> lefts = generator(left, i-1);
            //右子树由[i+1, n]构成
            List<TreeNode> rights = generator(i+1,right);
            //然后合成一棵树
            for(int m = 0;m < lefts.size();m++){
                for(int n = 0; n < rights.size();n++){
                    TreeNode root = new TreeNode(i);
                    root.left = lefts.get(m);
                    root.right = rights.get(n);
                    res.add(root);
                }
            }
        }
        return res;
    }
}
