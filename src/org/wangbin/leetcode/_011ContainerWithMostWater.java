package org.wangbin.leetcode;

/**
 * i, j�ֱ��ͷβ��ʼ��������� area = min(height[j], height[i]) * (j-i)����height[i] <
 * height[j]ʱ����ʱ��� area = height[i] * (j-i); ����height[i]�Ƕ̰壬���ܸ�˭��ϣ����ܴﵽ��������ȡ����
 * j-i������ʱj-i�ľ��������ģ���ˣ��������Ϊ��iΪ��߽����������Ȼ��++i��ͬ���j�ı仯����Ϊ����i,
 * j������һ���Ƕ̰壬ÿ���Ƕ̰�ľͷ����仯����˸�������������� O(n)
 * 
 * @author wb
 * @version 2015-7-28 ����9:10:15
 */
public class _011ContainerWithMostWater {
	public static void main(String[] args) {
		
		int []ints = new int []{1,2,3,4,5,6};
		System.out.println(new _011ContainerWithMostWater().maxArea2(ints));
	}
	public int maxArea(int[] height) {
		int  i ,j;
		int maxArea = 0;
		i =0;
		j = height.length-1;
		int area=0;
		while(i <j ){
			if(height[i]<height[j]){
				area = height[i]*(j-i);
				i++;
			}else{
				area = height[j]*(j-i);
				j--;
			}
			maxArea = Math.max(maxArea, area);
		}
		
		return maxArea;
	}
	public int maxArea2(int[] height) {
        if(height == null || height.length == 0)
            return 0;
        
        int low = 0, high = height.length -1 ;  
        int max = 0;  
        while (low < high) {
         int area = (high-low)*Math.min(height[low], height[high]);
         
         max = Math.max(max, area);  
         if (height[low] < height[high])  
             low++;  
         else  
             high--;  
        }  
            return max;  
    }
}
