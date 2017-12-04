package columnGeneration;

import java.io.IOException;
import java.util.List;

import dataSource.DataSource;
import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.cplex.IloCplex;
import models.ModelVars;

public class MPConstraints {


	public MPConstraints(){

	}

	public MPConstraints(IloCplex cplex, DataSource ds, MPVars vars, List<Column> allColumns) throws IOException, IloException {


		int columnCount = allColumns.size();
		IloLinearNumExpr expr = cplex.linearNumExpr();

		//// constraint 7
		for(int i = 1; i <= columnCount; i++){

			expr.addTerm(1, vars.z[i - 1]);		
		}
		cplex.addEq(expr, ds.clusterNo, "const_7_");


		//// constraint 8
		for(int i = 1; i <= ds.entitySize; i++){

			expr = cplex.linearNumExpr();
			for(int s = 1; s <= columnCount; s++){				
				
				expr.addTerm(allColumns.get(s - 1).getItem(i), vars.z[i - 1]);				
			}
			
			cplex.addEq(expr, 1, "const_8_".concat(Integer.toString(i)));
		}
	}
	
	

}
