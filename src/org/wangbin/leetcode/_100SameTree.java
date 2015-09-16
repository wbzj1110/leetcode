package org.wangbin.leetcode;

/**
 * Given two binary trees, write a function to check if they are equal or not.
 * 
 * Two binary trees are considered equal if they are structurally identical and the nodes have the
 * same value.
 * 
 * @author wb
 * @date 2015-9-16 上午9:06:22
 */
public class _100SameTree {
    public static void main(String[] args) {

    }
    /**
     * 判断2个bst是否相等，比较每个节点是否相等即可
     * 即：根节点相等，左右子树也相等
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null){
            return true;
        }
        if(p==null||q==null){
            return false;
        }
        return p.val==q.val && isSameTree(p.left, q.left)
                &&isSameTree(p.right, q.right);
    }


}
