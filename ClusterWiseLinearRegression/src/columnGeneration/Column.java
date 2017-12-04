package columnGeneration;

import java.util.ArrayList;
import java.util.List;

import util.MathUtilityFuncs;

public class Column implements MathUtilityFuncs{

	public int [] column;
	public List<Integer> items;
	public int arrayOfItems;
	
	
	
	public Column(int entitySize){
		this.column = new int[entitySize];
	}
	
	public int [] getColumn(){
		
		return this.column;		
	}
	
	public Column(int entitySize, int[] column){
		
		this.column = new int[entitySize];		
		for(int i = 0; i < entitySize; i++){
			this.column[i] = column[i];
		}
	}
	
	public int getItem(int index){
		return this.column[index - 1];
	}
	
	public List<Integer> getColumnItems(){
		
		int itemCounter = 0;
		this.items = new ArrayList<Integer>();
		
		for(int i = 0; i < this.column.length; i++){
			if(this.column[i] > 0 && this.column[i] == 1){
				itemCounter++;
				this.items.add(i + 1);				
			}
		}		
		return this.getColumnItems();
	}
	
	public int[] getArrayofItems(){
		
		return ListToArray(this.items);
	}
	
}
