package columnGeneration;

import java.io.IOException;
import java.util.List;

import dataSource.DataSource;
import dataSource.ObsByItem;
import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumExpr;
import ilog.concert.IloQuadNumExpr;
import ilog.cplex.IloCplex;
import models.ModelConstraints;
import models.ModelQPObj;
import models.ModelVars;
import util.MathUtilityFuncs;
import util.MultipleLinearRegression;

public class MPObj implements MathUtilityFuncs{



	public MPObj(IloCplex cplex, List<Column> allColumns, MPVars vars, DataSource ds) throws IloException{

		IloLinearNumExpr expr = cplex.linearNumExpr();

		for(int s = 1; s <= allColumns.size(); s++){

			expr.addTerm(getClusterCost(ds, allColumns.get(s - 1)), vars.z[s - 1]);
		}		
		cplex.addMinimize(expr);
		//System.out.println("**** Entered obj...");

	}
	
	public double getClusterCost(DataSource ds, Column col){
		
		int [] items = col.ListToArray(col.getColumnItems());
		
		int obsCount = 0;
		for(int i: items){			
			
			obsCount = obsCount + ds.observationsByItem.get(i).size();			
		}
		
		double x[][] = new double[obsCount][1 + ds.independentVars];
		double y[] = new double[obsCount];		
		
		int counter = 0;
		for(int i: items){			
			for(ObsByItem o: ds.observationsByItem.get(i)){				
				
				x[counter][0] = 1;
				x[counter][1] = i;
				x[counter][2] = o.getWeek();
				x[counter][3] = o.getDiscount();				
				
				y[counter] = o.getSales();
				counter++;
			}		
		}
		
		
		/// uses Jama package to calculate the SSE		
		MultipleLinearRegression regression = new MultipleLinearRegression(x, y);
			
		return regression.getSSE();
	}
	
	


}
