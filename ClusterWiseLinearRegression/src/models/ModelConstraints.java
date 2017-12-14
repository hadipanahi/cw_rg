package models;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataSource.DataSource;
import dataSource.ObsByItem;
import gurobi.GRB;
import gurobi.GRBException;
import gurobi.GRBLinExpr;
import gurobi.GRBModel;

public class ModelConstraints /*extends ModelVars*/{

	//	public ModelConstraints() throws IOException, IloException {
	//		super();
	//		// TODO Auto-generated constructor stub
	//
	//	}


	public ModelConstraints(GRBModel model, DataSource ds, ModelVars vars) throws GRBException {


		GRBLinExpr expr = new GRBLinExpr();


		//// constraint 2
		for(int i = 1; i<= ds.entitySize; i++){
			for(int k = 1; k <= ds.clusterNo; k++){
				for(ObsByItem l: ds.observationsByItem.get(i)  ){
					int lCounter = 1;								
					//System.out.println("adding constraint 2");	
					String consName = "cons2_";
					expr = new GRBLinExpr();

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

					model.addConstr(expr, GRB.GREATER_EQUAL, -ds.bigM + l.getSales(), consName.concat(Integer.toString(i).concat("_").concat(Integer.toString(k).concat("_").
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
						expr = new GRBLinExpr();

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

						model.addConstr(expr, GRB.GREATER_EQUAL, -ds.bigM - l.getSales(), consName.concat(Integer.toString(i).concat("_").concat(Integer.toString(k).concat("_").
								concat(Integer.toString(lCounter)))));	
						lCounter++;
					}
				}			
			}

		
		
		
		//// Cosntraint 4
		for(int i = 1; i<= ds.entitySize; i++){
			//System.out.println("adding constraint 4");	
			String consName = "cons4_";
			expr = new GRBLinExpr();
			for(int k = 1; k <= ds.clusterNo; k++){

				expr.addTerm(1, vars.z[i - 1][k - 1]);
			}

			model.addConstr(expr, GRB.EQUAL, 1, consName.concat(Integer.toString(i)));

		}


		//// Cosntraint 5

		for(int k = 1; k <= ds.clusterNo; k++){

			//System.out.println("adding constraint 5");	
			String consName = "cons5_";
			expr = new GRBLinExpr();
			for(int i = 1; i <= ds.entitySize; i++){

				expr.addTerm(1, vars.z[i - 1][k - 1]);
			}

			model.addConstr(expr, GRB.GREATER_EQUAL, ds.minClusterEntities, consName.concat(Integer.toString(k)));

		}
		
		
		/// Breaking Symmetry
		// this constraint implies sum of item indexes for a cluster with lower index should be less than the sum of item indexes for a cluster with
		// greater cluster index
		for(int k = 1; k < ds.clusterNo; k++){
			String consName = "SymBreak_";
			expr = new GRBLinExpr();
			for(int i = 1; i <= ds.entitySize; i++){

				expr.addTerm(i, vars.z[i - 1][k - 1]);
				expr.addTerm(-i, vars.z[i - 1][k]);
			}

			model.addConstr(expr, GRB.GREATER_EQUAL, 0, consName.concat(Integer.toString(k)));
		}




	}


}
