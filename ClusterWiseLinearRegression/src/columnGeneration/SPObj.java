package columnGeneration;

import dataSource.DataSource;
import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloQuadNumExpr;
import ilog.cplex.IloCplex;
import models.ModelVars;

public class SPObj {
	
	
	
	public SPObj(IloCplex cplex, DataSource ds, SPVars vars, double [] duals) throws IloException{

		
		/// Adding the quadratic term of the objective function
		IloQuadNumExpr qpExpr = cplex.quadNumExpr();
		for(int i = 1; i <= ds.entitySize; i++){
			for(int l = 1; l <= ds.obsByHybCount; l++){
				qpExpr.addTerm(1, vars.t[i - 1][l - 1], vars.t[i - 1][l - 1]);
			}
		}		
				
		
		///// Adding the linear term of the objective function
		IloLinearNumExpr expr = cplex.linearNumExpr();
		for(int i = 1; i <= ds.entitySize; i++){
			expr.addTerm(-duals[i - 1], vars.z[i - 1]);			
		}
		
		
		//// Adding both linear and quadratic terms
		cplex.addMinimize(cplex.sum(qpExpr, expr));
		
		
		
		
		//System.out.println("**** Entered obj...");

	}
}
