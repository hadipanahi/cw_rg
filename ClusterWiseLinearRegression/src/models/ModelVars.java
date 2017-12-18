package models;

import java.io.IOException;

import dataSource.DataSource;
import gurobi.GRB;
import gurobi.GRBException;
import gurobi.GRBModel;
import gurobi.GRBVar;

public class ModelVars {


	GRBVar [][] z;
	GRBVar [][] t;
	GRBVar [][] beta;
	GRBVar [] q;

	//	public ModelVars() throws IOException, IloException {
	//		// TODO Auto-generated constructor stub
	//		super();
	//		//GenVars(null);
	//	}

	public ModelVars(/*IloCplex cplex, DataSource ds*/)  {
		// TODO Auto-generated constructor stub
		//super();
		//GenVars(cplex, ds);
	}


	//	public DataSource getDataSource(){
	//		return this.su;
	//	}


	public void GenVars(GRBModel model, DataSource ds) throws GRBException{

		System.out.println("entered genVars");
		this.z = new GRBVar[ds.entitySize][ds.clusterNo];
		this.t = new GRBVar[ds.entitySize][ds.obsByHybCount];
		this.beta = new GRBVar[ds.clusterNo][ds.independentVars];
		this.q = new GRBVar[ds.clusterNo];

		for(int i = 1; i <= ds.entitySize; i++){
			for(int k = 1; k <= ds.clusterNo; k++){				
				this.z[i - 1][k - 1] = model.addVar(0, 1, 0, GRB.BINARY, "Z_".concat(Integer.toString(i).concat("_").concat(Integer.toString(k))));
			}
		}


		for(int i = 1; i <= ds.entitySize; i++){
			for(int l = 1; l <= ds.obsByHybCount; l++){
				this.t[i - 1][l - 1] = model.addVar(0, Double.MAX_VALUE, 0, GRB.CONTINUOUS, "T_".concat(Integer.toString(i).concat("_").concat(Integer.toString(l))));
			}
		}

		for(int k = 1; k <= ds.clusterNo; k++){
			for(int j = 1; j <= ds.independentVars; j++){
				this.beta[k - 1][j - 1] = model.addVar(Double.MIN_VALUE, Double.MAX_VALUE, 0, GRB.CONTINUOUS, "Beta_".concat(Integer.toString(k).concat("_").concat(Integer.toString(j))));
			}
		}

		/// Added for second symmetry
		for(int k = 1; k <= ds.clusterNo; k++){
			this.q[k - 1] = model.addVar(1, ds.entitySize, 0, GRB.CONTINUOUS, "q_".concat(Integer.toString(k)));
		}



	}

}
