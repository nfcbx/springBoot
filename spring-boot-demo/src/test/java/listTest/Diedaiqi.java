package listTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class Diedaiqi {
	
	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(1);
		
		for(Iterator<Integer> it = list.iterator(); it.hasNext();){
			System.out.println(it.next());
		}
		
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		
		hashMap.put(1, 2);
		hashMap.put(3, 4);
		hashMap.put(5, 6);
		hashMap.put(7, 8);
		
		for(Iterator<Entry<Integer, Integer>> it = hashMap.entrySet().iterator(); it.hasNext();){
			System.out.println(it.next());
		}
		
		
		
		
	}

}
