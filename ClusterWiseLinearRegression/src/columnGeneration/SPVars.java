package columnGeneration;

import dataSource.DataSource;
import ilog.concert.IloException;
import ilog.concert.IloNumVar;
import ilog.concert.IloNumVarType;
import ilog.cplex.IloCplex;

public class SPVars {
	IloNumVar [] z;
	IloNumVar [][] t;
	IloNumVar [] beta;

	public SPVars(){

	}

	public SPVars(IloCplex cplex, DataSource ds) throws IloException{

		System.out.println("entered genVars");
		this.z = new IloNumVar[ds.entitySize];
		this.t = new IloNumVar[ds.entitySize][ds.obsByHybCount];
		this.beta = new IloNumVar[ds.independentVars];


		for(int i = 1; i <= ds.entitySize; i++){

			this.z[i - 1] = cplex.numVar(0, 1, IloNumVarType.Bool, "Z_".concat(Integer.toString(i)));
		}


		for(int i = 1; i <= ds.entitySize; i++){
			for(int l = 1; l <= ds.obsByHybCount; l++){
				this.t[i - 1][l - 1] = cplex.numVar(0, Double.MAX_VALUE, IloNumVarType.Float, "T_".concat(Integer.toString(i).concat("_").concat(Integer.toString(l))));
			}
		}


		for(int j = 1; j <= ds.independentVars; j++){
			this.beta[j - 1] = cplex.numVar(Double.MIN_VALUE, Double.MAX_VALUE, IloNumVarType.Float, "Beta_".concat(Integer.toString(j)));
		}



	}
}
