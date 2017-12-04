package dataSource;

public class Observation {
		
	private int item;
	private int week;
	private double sales;
	private double discount;
	
	public Observation(int item, int week, double sales, double discount){
		
		this.item = item;
		this.week = week;
		this.sales = sales;
		this.discount = discount;		
	}
	
	public int getItem(){
		return this.item;
	}	
	
	public int getWeek(){
		return this.week;
	}
	
	public double getSales(){
		return this.sales;
	}
	
	public double getDiscount(){
		return this.discount;
	}
	
}
