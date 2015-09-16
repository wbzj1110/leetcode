package org.wangbin.leetcode;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * 
 * Recover the tree without changing its structure.
 * 
 * Note: A solution using O(n) space is pretty straight forward. Could you devise a constant space
 * solution? confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 * 
 * @author wb
 * @date 2015-9-16 上午9:56:11
 */
public class _099RecoverBinarySearchTree {
    public static void main(String[] args) {

    }

    /**
     * 这里只需要一个全局变量记录错误的2个节点即可， 只不过有个修改逻辑，就是需要每次替换错误序列的第二个。 为啥是第二个来，比如1，4，3,2,5,6
     * 主要一画图就明白了。当然严格的数学证明最简单的就是反证法了
     * 
     * @param root
     */
    TreeNode pre = null;
    TreeNode first = null;
    TreeNode second = null;

    public void recoverTree(TreeNode root) {
        // 不存在root为null的情况
        inOrder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inOrder(TreeNode root) {
        // TODO Auto-generated method stub
        if (root == null) {
            return;
        }
        inOrder(root.left);
        if (pre == null) {
            pre = root;// pre的初始化
        } else {
            if (pre.val > root.val) {// 有问题了
                if (first == null) {
                    first = pre;// 找到第一个逆序点了
                    second = root;
                } else {
                    second = root;//第二次找到逆序点，更新second
                }
            }
            pre = root;
        }
        inOrder(root.right);
    }
}
