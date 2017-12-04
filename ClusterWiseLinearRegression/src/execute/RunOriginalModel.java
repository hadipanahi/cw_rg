package execute;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import dataSource.DataSource;
import ilog.concert.IloException;
import models.OriginalModel;

public class RunOriginalModel {


	
	public static void main(String[] args) throws IOException, IloException {
		// TODO Auto-generated method stub
		
		
		OriginalModel originalModel = new OriginalModel();
		originalModel.buildModel();
//		DataSource dataSource = new DataSource();
//		dataSource.populateData();
//		dataSource.dataReport();
		originalModel.exportLP();
		
		
		
	}

}
