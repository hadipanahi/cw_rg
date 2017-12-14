package models;

import dataSource.DataSource;
import gurobi.GRB;
import gurobi.GRBException;
import gurobi.GRBModel;
import gurobi.GRBQuadExpr;


public class ModelQPObj {

	
	public ModelQPObj(GRBModel model, DataSource ds, ModelVars vars) throws GRBException{
				
		GRBQuadExpr qpExpr = new GRBQuadExpr();
		
		for(int i = 1; i <= ds.entitySize; i++){
			for(int l = 1; l <= ds.obsByHybCount; l++){
				qpExpr.addTerm(1, vars.t[i - 1][l - 1], vars.t[i - 1][l - 1]);
			}
		}		
		
		model.setObjective(qpExpr, GRB.MINIMIZE);
		
		//System.out.println("**** Entered obj...");
		
	}
}
