package models;

import dataSource.DataSource;
import ilog.concert.IloCopyManager;
import ilog.concert.IloCopyable;
import ilog.concert.IloException;
import ilog.concert.IloNumVar;
import ilog.concert.IloQuadNumExpr;
import ilog.concert.IloQuadNumExprIterator;
import ilog.concert.IloCopyManager.Check;
import ilog.cplex.IloCplex;

public class ModelQPObj {

	
	public ModelQPObj(IloCplex cplex, DataSource ds, ModelVars vars) throws IloException{
		
		IloQuadNumExpr qpExpr = cplex.quadNumExpr();
		
		for(int i = 1; i <= ds.entitySize; i++){
			for(int l = 1; l <= ds.obsByHybCount; l++){
				qpExpr.addTerm(1, vars.t[i - 1][l - 1], vars.t[i - 1][l - 1]);
			}
		}		
		cplex.addMinimize(qpExpr);
		//System.out.println("**** Entered obj...");
		
	}
}
