package models;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataSource.DataSource;
import dataSource.ObsByItem;
import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumExpr;
import ilog.cplex.IloCplex;

public class ModelConstraints /*extends ModelVars*/{

	//	public ModelConstraints() throws IOException, IloException {
	//		super();
	//		// TODO Auto-generated constructor stub
	//
	//	}


	public ModelConstraints(IloCplex cplex, DataSource ds, ModelVars vars) throws IOException, IloException {


		IloLinearNumExpr expr = cplex.linearNumExpr();


		//// constraint 2
		for(int i = 1; i<= ds.entitySize; i++){
			for(int k = 1; k <= ds.clusterNo; k++){
				for(ObsByItem l: ds.observationsByItem.get(i)  ){
					int lCounter = 1;								
					//System.out.println("adding constraint 2");	
					String consName = "cons2_";
					expr = cplex.linearNumExpr();

					expr.addTerm(1, vars.t[i-1][lCounter-1]);
					expr.addTerm(-ds.bigM, vars.z[i-1][k-1]);
					for(int j = 1; j<= ds.independentVars; j++){

						switch(j){
						case 1: expr.addTerm(i, vars.beta[k - 1][j - 1]);
						break;

						case 2: expr.addTerm(l.getWeek(), vars.beta[k - 1][j - 1]);
						break;

						case 3: expr.addTerm(l.getDiscount(), vars.beta[k - 1][j - 1]);
						break;

						}
					}

					cplex.addGe(expr, -ds.bigM + l.getSales(), consName.concat(Integer.toString(i).concat("_").concat(Integer.toString(k).concat("_").
							concat(Integer.toString(lCounter)))));	
					lCounter++;
				}
			}			
		}


		
		
		
	//// constraint 3
			for(int i = 1; i<= ds.entitySize; i++){
				for(int k = 1; k <= ds.clusterNo; k++){
					for(ObsByItem l: ds.observationsByItem.get(i)  ){
						int lCounter = 1;								
						//System.out.println("adding constraint 3");	
						String consName = "cons3_";
						expr = cplex.linearNumExpr();

						expr.addTerm(1, vars.t[i-1][lCounter-1]);
						expr.addTerm(-ds.bigM, vars.z[i-1][k-1]);
						for(int j = 1; j<= ds.independentVars; j++){

							switch(j){
							case 1: expr.addTerm(-i, vars.beta[k - 1][j - 1]);
							break;

							case 2: expr.addTerm(-l.getWeek(), vars.beta[k - 1][j - 1]);
							break;

							case 3: expr.addTerm(-l.getDiscount(), vars.beta[k - 1][j - 1]);
							break;

							}
						}

						cplex.addGe(expr, -ds.bigM - l.getSales(), consName.concat(Integer.toString(i).concat("_").concat(Integer.toString(k).concat("_").
								concat(Integer.toString(lCounter)))));	
						lCounter++;
					}
				}			
			}

		
		
		
		//// Cosntraint 4
		for(int i = 1; i<= ds.entitySize; i++){
			//System.out.println("adding constraint 4");	
			String consName = "cons4_";
			expr = cplex.linearNumExpr();
			for(int k = 1; k <= ds.clusterNo; k++){

				expr.addTerm(1, vars.z[i - 1][k - 1]);
			}

			cplex.addEq(expr, 1, consName.concat(Integer.toString(i)));

		}


		//// Cosntraint 5

		for(int k = 1; k <= ds.clusterNo; k++){

			//System.out.println("adding constraint 5");	
			String consName = "cons5_";
			expr = cplex.linearNumExpr();
			for(int i = 1; i <= ds.entitySize; i++){

				expr.addTerm(1, vars.z[i - 1][k - 1]);
			}

			cplex.addGe(expr, ds.minClusterEntities, consName.concat(Integer.toString(k)));

		}




	}


}
