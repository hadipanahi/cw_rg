package columnGeneration;

import java.io.IOException;

import dataSource.DataSource;
import dataSource.ObsByItem;
import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.cplex.IloCplex;


public class SPConstraints {

	public SPConstraints(IloCplex cplex, DataSource ds, SPVars vars) throws IOException, IloException {


		IloLinearNumExpr expr = cplex.linearNumExpr();


		//// constraint 11
		for(int i = 1; i<= ds.entitySize; i++){
			for(ObsByItem l: ds.observationsByItem.get(i)  ){
				int lCounter = 1;								
				//System.out.println("adding constraint 11");	
				String consName = "cons11_";
				expr = cplex.linearNumExpr();

				expr.addTerm(1, vars.t[i - 1][lCounter - 1]);
				expr.addTerm(-ds.bigM, vars.z[i - 1]);
				for(int j = 1; j<= ds.independentVars; j++){

					switch(j){
					case 1: expr.addTerm(i, vars.beta[j - 1]);
					break;

					case 2: expr.addTerm(l.getWeek(), vars.beta[j - 1]);
					break;

					case 3: expr.addTerm(l.getDiscount(), vars.beta[j - 1]);
					break;

					}
				}

				cplex.addGe(expr, -ds.bigM + l.getSales(), consName.concat(Integer.toString(i).concat("_").concat(Integer.toString(lCounter))));	
				lCounter++;
			}

		}


	//// constraint 12
			for(int i = 1; i<= ds.entitySize; i++){
				for(ObsByItem l: ds.observationsByItem.get(i)  ){
					int lCounter = 1;								
					//System.out.println("adding constraint 12");	
					String consName = "cons12_";
					expr = cplex.linearNumExpr();

					expr.addTerm(1, vars.t[i - 1][lCounter - 1]);
					expr.addTerm(-ds.bigM, vars.z[i - 1]);
					for(int j = 1; j<= ds.independentVars; j++){

						switch(j){
						case 1: expr.addTerm(-i, vars.beta[j - 1]);
						break;

						case 2: expr.addTerm(-l.getWeek(), vars.beta[j - 1]);
						break;

						case 3: expr.addTerm(-l.getDiscount(), vars.beta[j - 1]);
						break;

						}
					}

					cplex.addGe(expr, -ds.bigM - l.getSales(), consName.concat(Integer.toString(i).concat("_").concat(Integer.toString(lCounter))));	
					lCounter++;
				}

			}






		//// Cosntraint 3

		//System.out.println("adding constraint 13");	
		String consName = "cons13";
		expr = cplex.linearNumExpr();
		for(int i = 1; i <= ds.entitySize; i++){

			expr.addTerm(1, vars.z[i - 1]);
		}
		cplex.addGe(expr, ds.minClusterEntities, consName);


	}


}
