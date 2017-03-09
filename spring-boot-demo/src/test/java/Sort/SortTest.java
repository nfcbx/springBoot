package Sort;

import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SortTest {

	public static void main(String[] args) {
		
		Random random = new Random();
		
		int[] arr = new int[10];
		
		for(int i=0; i<arr.length; i++){
			arr[i] = random.nextInt(50);
		}
		
//		冒泡(arr); // 冒泡排序
		
//		charu(arr);  // 插入排序
		
		xuanze(arr); // 选择排序
		
	}
	
	
	/**
	 * 基本思想：在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整，
	 * 让较大的数往下沉，较小的往上冒。
	 * 
	 * 即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
	 * @param arr
	 */
	public static void 冒泡(int[] arr){
		System.out.print("排序前：");
		System.out.println(printArray(arr));
		System.out.println();
		
		for(int i=0; i<arr.length - 1; i++){
			
			for(int j=0; j<arr.length - 1 - i; j++){
				
				if (arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
				System.out.println(printArray(arr));
			}
			System.out.println(i);
			
		}
		
		System.out.print("排序后：");
		System.out.println(printArray(arr));
	}
	
	
	/**
	 * 插入
	 * @param arr
	 * 基本思想：在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排好顺序的，
	 * 现在要把第n个数插到前面的有序数中，使得这n个数也是排好顺序的。
	 * 如此反复循环，直到全部排好顺序。
	 */
	public static void charu(int[] arr){
		System.out.print("排序前：");
		System.out.println(printArray(arr));
		System.out.println();
		
		int temp = 0;
		for(int i=1; i<arr.length; i++){
			
			temp = arr[i];
			
			int j = i-1;
			
			for(; j>=0 && temp < arr[j]; j--){
				arr[j+1] = arr[j]; //将大于temp的值整体后移一个单位
				// 拿arr[j] 的值去逐个比较，因为temp的之前的数组肯定是排好序的
			}
			
			
			arr[j+1] = temp;
			
			System.out.println(printArray(arr));
		}
		
		System.out.print("排序后：");
		System.out.println(printArray(arr));
	}
	
	/**
	 * 简单选择排序
	 * 基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
	 * 然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
	 */
	public static void xuanze(int[] arr) {
		System.out.print("排序前：");
		System.out.println(printArray(arr));
		System.out.println();
		
		for (int i = 0; i < arr.length; i++) {
			
			int temp = arr[i];
			int position = i;
			
			for(int j=i+1; j<arr.length; j++){
//				遍历剩余的数，挨个对比，如果发现有比temp小的数，则替换temp，最后temp就是剩余里面最小的数
				if (arr[j] < temp) {
					temp = arr[j];
					position = j;
				}
				
			}
			
			arr[position] = arr[i];
			arr[i] = temp;
			System.out.println(printArray(arr));
		}
		
		System.out.print("排序后：");
		System.out.println(printArray(arr));
	}
	

	
	public static String printArray(int[] arr) {
		StringBuffer str = new StringBuffer();
		for (int i : arr) {
			str.append(i + " , ");
		}
		return str.toString();
	}
}
