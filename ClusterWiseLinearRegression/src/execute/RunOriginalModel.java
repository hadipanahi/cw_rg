package execute;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import dataSource.DataSource;
import gurobi.GRB;
import gurobi.GRBException;
import models.CallbackInfo;
import models.OriginalModel;
import util.Params;

public class RunOriginalModel {


	public static void main(String[] args) throws IOException, GRBException {
		// TODO Auto-generated method stub



		/// run for 15, 20, 25 SKU's
		String [] entitySizes = {"15", "20", "25"};
		String path;// "./data/clustreg_I";//"./data/clustreg_I15_L52_seed";
		BufferedWriter bw = null;
		FileWriter fw = null;
		String outputFile = "output.txt";
		fw = new FileWriter(outputFile);
		bw = new BufferedWriter(fw);
		bw.write("problem,binaries,vars,constraints,obj Q terms,pool size,time,mipgap,bound,obj");
		bw.write("\n");

		CallbackInfo cb   = new CallbackInfo();
		for(String s: entitySizes){
			for(int file = 1; file <= 1; file++){
				for(int p = 0; p <= 0; p++){
					path = "./data/clustreg_I";
					path = path.concat(s).concat("_L52_seed").concat(Integer.toString(file).concat(".txt"));
					System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@ " + path);

					Params params = new Params("Symmetry", p);					
					OriginalModel originalModel = new OriginalModel();
					originalModel.buildModel(path, params);
					String lpName= "clustreg_I".concat("_L52_seed").concat(Integer.toString(file).concat("_param_").
							concat(Integer.toString(p)).concat(".lp"));
					originalModel.exportLP(lpName);
					originalModel.modelStatReport();
					//originalModel.getGRBModel().setCallback(cb);
					originalModel.solve();
					
					
					String output = "";
					
					
					output = output.concat(lpName).concat(",").concat(Integer.toString(p)).concat(",").
					concat( Integer.toString( originalModel.getGRBModel().get(GRB.IntAttr.NumBinVars)) )
					.concat(",").concat( Integer.toString( originalModel.getGRBModel().get(GRB.IntAttr.NumVars)) ).concat(",").
					concat( Integer.toString( originalModel.getGRBModel().get(GRB.IntAttr.NumConstrs)) ).concat(",").
					concat( Integer.toString( originalModel.getGRBModel().get(GRB.IntAttr.NumQNZs)) ).concat(",").
					concat( Integer.toString( originalModel.getGRBModel().get(GRB.IntAttr.SolCount)) ).concat(",").					
					concat( Double.toString( originalModel.getGRBModel().get(GRB.DoubleAttr.Runtime)) ).concat(",").
					concat( Double.toString( originalModel.getGRBModel().get(GRB.DoubleAttr. MIPGap)) ).concat(",").
					concat( Double.toString( originalModel.getGRBModel().get(GRB.DoubleAttr.ObjBound)) ).concat(",").
					concat( Double.toString( originalModel.getGRBModel().get(GRB.DoubleAttr.ObjVal)) ).concat(",");
					
					//originalModel.getGRBModel().relax().optimize();					
//					output = output.concat( Double.toString( originalModel.getGRBModel().get(GRB.DoubleAttr.ObjBound)) ).concat(",").
//					concat( Double.toString( originalModel.getGRBModel().get(GRB.DoubleAttr.ObjVal)) ).concat(",");
					bw.write("\n");				 
					
					bw.write(output);
					
				}
			}
		}
		
		if (bw != null)
			bw.close();

		if (fw != null)
			fw.close();
		
		
		
		/* one single run
		OriginalModel originalModel = new OriginalModel();
		originalModel.buildModel();
//		DataSource dataSource = new DataSource();
//		dataSource.populateData();
//		dataSource.dataReport();
		originalModel.exportLP();
		originalModel.modelStatReport();		
		originalModel.solve();
		 */



	}



}
