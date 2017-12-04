package dataSource;

public class ObsByItemByWeek {
	
	private double sales;
	private double discount;
	
	public ObsByItemByWeek(double sales, double discount){
		
		this.sales = sales;
		this.discount = discount;		
	}	
		
	public double getSales(){
		return this.sales;
	}
	
	public double getDiscount(){
		return this.discount;
	}
}
