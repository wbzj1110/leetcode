package org.wangbin.leetcode;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * 
 * Integers in each row are sorted from left to right. The first integer of each
 * row is greater than the last integer of the previous row. For example,
 * 
 * Consider the following matrix:
 * 
 * [ [1, 3, 5, 7], [10, 11, 16, 20], [23, 30, 34, 50] ] Given target = 3, return
 * true.
 * 
 * @author wb
 * @date 2015-8-15 ����10:24:03
 */
public class _074Searcha2DMatrix {
	public static void main(String[] args) {
		int[][] ints = new int[][] { { 1, 3, 5, 7 }, { 10, 11, 16, 20 },
				{ 23, 30, 34, 50 } };
		System.out.println(new _074Searcha2DMatrix().searchMatrix(ints, 3));
	}

	/**
	 * ��2��������Ȼ����2����������û�ˡ���
	 * 
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		int low = 0;
		int high = matrix.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (matrix[mid][0] == target) {
				return true;
			} else if (matrix[mid][0] > target) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		if (high < 0) {
			return false;
		}
		int row = high; // ����while������ʱ��lowָ���ֵ�϶���target�󣬶�highָ���ֵ�϶���targetС
		low = 0;
		high = matrix[0].length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (matrix[row][mid] == target) {
				return true;
			} else if (matrix[row][mid] > target) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return false;
	}
	/**
	 * ......���и�������2ά�����1ά������������2�֣������и������������ȷ�ϡ��������£�
	 * ��Ȼ���⿴���Ǿ������⣬���Ǳ���������Ŀ�ؼ���Ϊ��һ����ԭ�򣬿����ҵ���each row are
	 * sorted��ÿһ�а���˳��Ҳ��sorted��ͬʱҲ�����鱣�档
	 * 
	 * ���Ǳ�����ѵ������ν�2D����ת����1D��Ȼ�����ö��ֲ��ҷ���������⡣ת�����ص������ÿ�����λ�ã��ھ����ʾ�У�����ϰ���ã�i��j��
	 * ����ʾһ���㣬��������а�������ʹ��low high mid��ָ����Ҫ��λ�á�Ϊ�˽�����⣬��һ��������Ҫ�����������˳������һ���ߡ�
	 * 
	 * �����е������ҿ��Խ���ת��Ϊ��
	 * 
	 * position: 0 1 2 3 4 5 6 7 8 9 10 11
	 * 
	 * values: 1 3 5 7 10 11 16 20 23 30 34 50
	 * 
	 * row: 0 0 0 0 1 1 1 1 2 2 2 2
	 * 
	 * column: 0 1 2 3 0 1 2 3 0 1 2 3
	 * 
	 * 
	 * 
	 * ���У�����rows=3������columns=4
	 * 
	 * 
	 * 
	 * ���ϣ�������ǽ�2D����ת����1������Ķ�Ӧ�����Զ��ڶ��ֲ��ҷ��ĳ�ʼֵΪ��low=0��high=rows*columns-1���ܹ���ֵ�ĸ�����
	 * ��Ϊ��0��ʼ���Լ�1������Ϊ���ܹ�������given 2D
	 * matrix�ҵ���Ҫ�ȶԵ�ֵ�����ǻ�����Ҫȷ��������������ͨ���ϱ���Կ�����������position
	 * /columns����������position%columns,
	 * ����һ�������ܺ����׵���ԭ�����ж�λ������Ҫ��ֵ��ʣ�������Ľ���˼·��������ֲ��ҷ�һģһ���ˡ�
	 * 
	 * ʱ�临�Ӷ�O(log(rows*columns))
	 * ������������=O(log(rows)+log(colums))
	 */
	public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix.length==0||matrix[0].length==0||matrix==null)
            return false;
            
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int low = 0;
        int high = rows*cols-1;
        
        while(low<=high){
            int mid = (low+high)/2;
            int midValue = matrix[mid/cols][mid%cols];
            if(midValue == target)
                return true;
            else if(midValue < target)
                low = mid+1;
            else
                high = mid-1;
        }
        return false;
    }
}
