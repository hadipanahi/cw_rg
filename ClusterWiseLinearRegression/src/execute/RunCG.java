package execute;

import java.io.IOException;

import columnGeneration.ColumnGeneration;
import dataSource.DataSource;
import ilog.concert.IloException;
import models.OriginalModel;

public class RunCG {

	public static void main(String[] args) throws IOException, IloException {
		// TODO Auto-generated method stub
		
		
		DataSource dataSource = new DataSource();
		ColumnGeneration CG = new ColumnGeneration(dataSource);
		
		
		
		
		

	}

}
