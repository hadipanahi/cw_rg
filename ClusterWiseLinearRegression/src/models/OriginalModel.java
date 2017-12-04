package models;

import java.io.IOException;

import dataSource.DataSource;
import ilog.concert.IloException;
import ilog.cplex.IloCplex;

public class OriginalModel {

	public IloCplex cplex = null;
	public DataSource ds;
	public ModelVars vars;
	public ModelConstraints constraints;
	public ModelQPObj obj;
	
	
	public OriginalModel() throws IloException{
		if(this.cplex == null){
			this.cplex = new IloCplex();
			System.out.println("Cplex generated... ");
		}
	}
	
	public IloCplex getCplex(){
		return this.cplex;
	}
	
	public void buildModel() throws IOException, IloException{
		
		this.ds = new DataSource();
		//ds.populateData();
		this.ds.dataReport();
		
		if(this.cplex == null)
			System.out.println("cplex is null in origianlmodel class in buildmodel method");
		
		this.vars = new ModelVars();//(this.cplex, this.ds);
		this.vars.GenVars(this.cplex, this.ds);
		this.constraints = new ModelConstraints(this.cplex, this.ds, this.vars);
		this.obj = new ModelQPObj(this.cplex, this.ds, this.vars);
		
	}
	
	public void exportLP() throws IloException{
		this.cplex.exportModel("model.lp");
	}
	
	
	
}
