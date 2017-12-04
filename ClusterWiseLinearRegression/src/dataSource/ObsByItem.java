package dataSource;

public class ObsByItem {
	
	private int week;
	private double sales;
	private double discount;
	
	public ObsByItem(int week, double sales, double discount){
		
		this.week = week;
		this.sales = sales;
		this.discount = discount;		
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
