package org.wangbin.leetcode;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * 
 * For example, this binary tree is symmetric:
 * 
 * 1 / \ 2 2 / \ / \ 3 4 4 3 But the following is not: 1 / \ 2 2 \ \ 3 3 Note: Bonus points if you
 * could solve it both recursively and iteratively.
 * 
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 * 
 * @author wb
 * @date 2015-9-16 上午9:12:55
 */
public class _101SymmetricTree {
    public static void main(String[] args) {

    }
    /**
     * 递归判断左右子树是否相等
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        return isSymmetricTree(root.left,root.right);
    }
    private boolean isSymmetricTree(TreeNode p, TreeNode q) {
        if(p==null && q==null){
            return true;
        }
        if(p==null || q==null){
            return false;
        }
        return  (p.val==q.val)&&(isSymmetricTree(p.left, q.right))
                &&(isSymmetricTree(p.right, q.left));
    }
}
