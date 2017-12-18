package models;

import java.io.IOException;

import dataSource.DataSource;
//import execute.originalModel;
import gurobi.GRB;
import gurobi.GRBEnv;
import gurobi.GRBException;
import gurobi.GRBModel;

public class OriginalModel {

	//public IloCplex cplex = null;
	public DataSource ds;
	public ModelVars vars;
	public ModelConstraints constraints;
	public ModelQPObj obj;
	
	public GRBEnv env = null;
	GRBModel model = null;
	
	
//	public OriginalModel() throws IloException{
//		if(this.cplex == null){
//			this.cplex = new IloCplex();
//			System.out.println("Cplex generated... ");
//		}
//	}
	
	public OriginalModel() throws GRBException {
		if(this.model == null){
			this.env = new GRBEnv();
			this.model = new GRBModel(this.env);
			
			System.out.println("Gurobi generated... ");
		}
	}
	
	public GRBModel getGRBModel(){
		return this.model;
	}
	
//	public IloCplex getCplex(){
//		return this.cplex;
//	}
	
	public void buildModel() throws IOException, GRBException{
		
		this.ds = new DataSource();
		//ds.populateData();
		this.ds.dataReport();
		
//		if(this.cplex == null)
//			System.out.println("cplex is null in origianlmodel class in buildmodel method");
		
		if(this.model == null)
			System.out.println("Gurobi model is null in origianlmodel class in buildmodel method");
		
		
		this.vars = new ModelVars();
		this.vars.GenVars(this.model, this.ds);
		this.constraints = new ModelConstraints(this.model, this.ds, this.vars);
		this.obj = new ModelQPObj(this.model, this.ds, this.vars);
		
	}
	
//	public void exportLP() throws IloException{
//		this.cplex.exportModel("model.lp");
//	}
	
	public void exportLP() throws GRBException {
		this.model.write("GRBModel.lp");
	}
	
	public void solve() throws GRBException{
		this.model.optimize();;
	}
	
	public void modelStatReport() throws GRBException{
		
		System.out.println("#########################");
		System.out.println("# of constrainst: " + this.model.get(GRB.IntAttr.NumConstrs));
		System.out.println("# of binaries: " + this.model.get(GRB.IntAttr.NumBinVars));
		System.out.println("# of vars: " + this.model.get(GRB.IntAttr.NumVars));
		
		System.out.println("#########################");
	}
	
}
