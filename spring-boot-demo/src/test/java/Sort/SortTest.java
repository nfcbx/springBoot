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
		
//		冒泡(arr);
		
		charu(arr);
		
	}
	
	
	
	public static void 冒泡(int[] arr){
		System.out.print("排序前：");
		for (int i : arr) {
			System.out.print(i + " , ");
		}
		System.out.println();
		
		for(int i=0; i<arr.length - 1; i++){
			
			for(int j=0; j<arr.length - 1 - i; j++){
				
				if (arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
				
			}
			
		}
		
		System.out.print("排序后：");
		for (int i : arr) {
			System.out.print(i + " , ");
		}
	}
	
	
	
	public static void charu(int[] arr){
		System.out.print("排序前：");
		for (int i : arr) {
			System.out.print(i + " , ");
		}
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
		for (int i : arr) {
			System.out.print(i + " , ");
		}
	}
	
	

	
	public static String printArray(int[] arr) {
		StringBuffer str = new StringBuffer();
		for (int i : arr) {
			str.append(i + " , ");
		}
		return str.toString();
	}
}
