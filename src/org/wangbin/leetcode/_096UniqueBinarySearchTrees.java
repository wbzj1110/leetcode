package org.wangbin.leetcode;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * 
 * For example, Given n = 3, there are a total of 5 unique BST's.
 * 
 * 1 3 3 2 1 \ / / / \ \ 3 2 1 1 3 2 / / \ \ 2 1 2 3
 * 
 * @author wb
 * @date 2015-9-15 ����9:43:59
 */
public class _096UniqueBinarySearchTrees {
    public static void main(String[] args) {

    }

    /**
     * ���ᡣ���������ο������ <a>http://www.cnblogs.com/springfor/p/3884009.html</a>
     * ���ݿ���������C0Cn+1����Ϊleetcode����Ĳ�����n������Ϊ�˱���������������ʽд��Ct+1,��ʼֵΪC0 = 1��
     * 
     * ԭʼ�ĵ���ʽ�ǣ� Ct+1 += Ci*Ct-i (0<= i <=t) ���������num=t+1����ôt=num-1
     * 
     * ����ԭʼ����ʽ�������滻�ã�Cnum += Ci*Cnum-1-i (0<= i <=num-1) ��num��ȡֵ��Χ��[1, n]��ΪC0��֪��
     * 
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if (n == 0 || n == 1){
            return 1;
        }
        int[] C = new int[n + 1];
        C[0] = 1;
        // ����ʽ��Ct+1 += Ci*Ct-i(0<= i <= t)
        // ��num = t+1
        // �� t = num-1;
        // ��˵���ʽ��Ϊ��
        // Cnum += Ci*Cnum-1-i(0<=i<=num-1, 1<=num<=n)
        // C0 = 1

        for (int num = 1; num <= n; num++) {
            for (int i = 0; i <= num - 1; i++) {
                C[num] += C[i] * C[(num - 1) - i];
            }
        }
        return C[n];
    }

}
