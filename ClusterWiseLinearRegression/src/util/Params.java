package util;

public class Params {

	public String parameter;
	public double  doubleValue;
	public int intValue;
	public boolean boolValue;
	
	public Params(String parameter, double doubleValue){
		this.parameter = parameter;
		this.doubleValue = doubleValue;
	}
	public Params(String parameter, int intValue){
		this.parameter = parameter;
		this.intValue = intValue;
	}
	public Params(String parameter, boolean boolValue){
		this.parameter = parameter;
		this.boolValue = boolValue;
	}
	
}
