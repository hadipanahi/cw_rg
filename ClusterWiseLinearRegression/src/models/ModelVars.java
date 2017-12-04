package models;

import java.io.IOException;

import dataSource.DataSource;
import ilog.concert.IloException;
import ilog.concert.IloNumVar;
import ilog.concert.IloNumVarType;
import ilog.cplex.IloCplex;

public class ModelVars {
	
	
	IloNumVar [][] z;
	IloNumVar [][] t;
	IloNumVar [][] beta;
	
//	public ModelVars() throws IOException, IloException {
//		// TODO Auto-generated constructor stub
//		super();
//		//GenVars(null);
//	}
	
	public ModelVars(/*IloCplex cplex, DataSource ds*/) throws IOException, IloException {
		// TODO Auto-generated constructor stub
		//super();
		//GenVars(cplex, ds);
	}
	
	
//	public DataSource getDataSource(){
//		return this.su;
//	}
		
	
public void GenVars(IloCplex cplex, DataSource ds) throws IloException{
		
		System.out.println("entered genVars");
		this.z = new IloNumVar[ds.entitySize][ds.clusterNo];
		this.t = new IloNumVar[ds.entitySize][ds.obsByHybCount];
		this.beta = new IloNumVar[ds.clusterNo][ds.independentVars];
		
		for(int i = 1; i <= ds.entitySize; i++){
			for(int k = 1; k <= ds.clusterNo; k++){
				this.z[i - 1][k - 1] = cplex.numVar(0, 1, IloNumVarType.Bool, "Z_".concat(Integer.toString(i).concat("_").concat(Integer.toString(k))));
			}
		}
		
		
		for(int i = 1; i <= ds.entitySize; i++){
			for(int l = 1; l <= ds.obsByHybCount; l++){
				this.t[i - 1][l - 1] = cplex.numVar(0, Double.MAX_VALUE, IloNumVarType.Float, "T_".concat(Integer.toString(i).concat("_").concat(Integer.toString(l))));
			}
		}
		
		for(int k = 1; k <= ds.clusterNo; k++){
			for(int j = 1; j <= ds.independentVars; j++){
				this.beta[k - 1][j - 1] = cplex.numVar(Double.MIN_VALUE, Double.MAX_VALUE, IloNumVarType.Float, "Beta_".concat(Integer.toString(k).concat("_").concat(Integer.toString(j))));
			}
		}
		
		
	}

	
	
//	public void GenVars(IloCplex cplex) throws IloException{
//		
//		z = new IloNumVar[this.entitySize][this.clusterNo];
//		t = new IloNumVar[this.entitySize][this.observationsNo];
//		beta = new IloNumVar[this.clusterNo][this.variableSize];
//		
//		for(int i = 1; i <= this.entitySize; i++){
//			for(int k = 1; k <= this.clusterNo; k++){
//				z[i][k] = cplex.numVar(0, 1, IloNumVarType.Bool);
//			}
//		}
//		
//		for(int i = 1; i <= this.entitySize; i++){
//			for(int l = 1; l <= this.observationsNo; l++){
//				t[i][l] = cplex.numVar(0, Double.MAX_VALUE, IloNumVarType.Bool);
//			}
//		}
//		
//		for(int k = 1; k <= this.entitySize; k++){
//			for(int j = 1; j <= this.observationsNo; j++){
//				beta[k][j] = cplex.numVar(Double.MIN_VALUE, Double.MAX_VALUE, IloNumVarType.Bool);
//			}
//		}
//		
//		
//	}
	
	
}
