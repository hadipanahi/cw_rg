package util;

import java.util.List;

public interface MathUtilityFuncs {

	
	public default int[] ListToArray(List<Integer> itemsList){
		
		int [] array = new int[itemsList.size()];
		for(int i = 0; i < itemsList.size(); i++){
			array[i] = itemsList.get(i);
		}
		
		return array;
		
	}
}
