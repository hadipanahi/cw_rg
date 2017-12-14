package execute;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.neos.client.NeosClient;
import org.neos.client.NeosJobXml;

import dataSource.DataSource;
import ilog.concert.IloException;
import models.OriginalModel;

public class RunOriginalModel {

	private static final String HOST="neos-server.org";
	   private static final String PORT="3333";

	
	public static void main(String[] args) throws IOException, IloException {
		// TODO Auto-generated method stub
		
		 NeosClient client = new NeosClient(HOST, PORT);
		 /* create NeosJobXml object exJob with problem type nco for nonlinearly */
	      /* constrained optimization, KNITRO for the solver, GAMS for the input */
	      NeosJobXml job = new NeosJobXml("miqp", "CPLEX", "LP");
	      
	      
	      
		 
		OriginalModel originalModel = new OriginalModel();
		originalModel.buildModel();
//		DataSource dataSource = new DataSource();
//		dataSource.populateData();
//		dataSource.dataReport();
		originalModel.exportLP();
		String modelString = null;
		modelString = "C:\\Users\\hadipanahi\\git\\clusterwiseregression\\ClusterWiseLinearRegression\\model.lp";
		job.addParam("model", modelString);
		//originalModel.solve();
		
		
		
		
	}

}
