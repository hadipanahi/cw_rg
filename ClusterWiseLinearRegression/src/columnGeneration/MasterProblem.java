package columnGeneration;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import dataSource.DataSource;
import ilog.concert.IloException;
import ilog.concert.IloRange;
import ilog.cplex.IloCplex;
//import ilog.cplex.IloCplex.UnknownObjectException;
import models.ModelConstraints;
import models.ModelQPObj;

public class MasterProblem {

	public List<Column> allColumns;


	public IloCplex MPCplex = null;
	public DataSource ds;
	public MPVars vars;
	public MPConstraints constraints;
	public MPObj obj;
	public int modelIndex;
	public double [] duals;


	public MasterProblem() throws IloException{
		if(this.MPCplex == null){
			this.MPCplex = new IloCplex();
			System.out.println("MP Cplex generated... ");
		}
	}

	public IloCplex getMPCplex(){
		return this.MPCplex;
	}

	public void buildMP(DataSource ds) throws IOException, IloException{

		//this.ds = new DataSource();
		//ds.populateData();
		ds.dataReport();

		if(this.MPCplex == null)
			System.out.println("cplex is null in MP class in buildmodel method");

		this.vars = new MPVars(this.MPCplex, this.allColumns.size());
		this.constraints = new MPConstraints(this.MPCplex, ds, this.vars, this.allColumns);
		this.obj = new MPObj(this.MPCplex, this.allColumns, this.vars, ds);
		
	}


//	public void buildMP() throws IOException, IloException{
//
//		this.ds = new DataSource();
//		//ds.populateData();
//		this.ds.dataReport();
//
//		if(this.MPCplex == null)
//			System.out.println("cplex is null in MP class in buildmodel method");
//
//		this.vars = new MPVars(this.MPCplex, this.allColumns.size());//(this.cplex, this.ds);
//
//		this.constraints = new MPConstraints(this.MPCplex, this.ds, this.vars, this.allColumns);
//		//this.obj = new ModelQPObj(this.MPCplex, this.ds, this.vars);
//
//	}


	public void exportMPLP() throws IloException{
		this.MPCplex.exportModel("MPmodel.lp");
	}
	
	public void endMPCplex(){
		this.MPCplex.end();
	}

	
	public double [] getMPDuals(DataSource ds) throws IloException{
		
		this.duals = new double[ds.entitySize];
		Iterator it = this.MPCplex.rangeIterator();
		while (it.hasNext()) {
			  IloRange r = (IloRange) it.next();
			  String consName = r.getName();
			  int index = Integer.parseInt(consName.substring(8));			  
			  this.duals[index - 1] = this.MPCplex.getDual(r);			  
		}				
		return this.duals;
	}



}
