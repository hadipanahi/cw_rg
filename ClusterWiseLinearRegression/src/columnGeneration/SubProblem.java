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

public class SubProblem {


	public IloCplex SPCplex = null;
	public DataSource ds;
	public SPVars vars;
	public SPConstraints constraints;
	public SPObj obj;
	public int modelIndex;
	public double [] duals;


	public SubProblem() throws IloException{
		if(this.SPCplex == null){
			this.SPCplex = new IloCplex();
			System.out.println("SP Cplex generated... ");
		}
	}

	public IloCplex getSPCplex(){
		return this.SPCplex;
	}

	public void buildSP(DataSource ds, double [] duals) throws IOException, IloException{

		//this.ds = new DataSource();
		//ds.populateData();
		ds.dataReport();

		if(this.SPCplex == null)
			System.out.println("cplex is null in SP class in buildmodel method");

		this.vars = new SPVars(this.SPCplex , ds);

		this.constraints = new SPConstraints(this.SPCplex, ds, this.vars);
		this.obj = new SPObj(this.SPCplex, ds, this.vars, duals);

	}



	public void exportSPLP() throws IloException{
		this.SPCplex.exportModel("SPmodel.lp");
	}

	public void endSPCplex(){
		this.SPCplex.end();
	}



}
