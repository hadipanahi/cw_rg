package columnGeneration;

import java.io.IOException;

import dataSource.DataSource;
import ilog.concert.IloException;

public class ColumnGeneration {

	MasterProblem MP;
	SubProblem SP;



	public ColumnGeneration(DataSource ds) throws IloException, IOException{

		/// Parameterize this later
		int kMax = 0;
		int kCounter = 1;

		while (kCounter < kMax){

			initialize();
			solveMaster(ds);

			// get duals and pass them to SP
			double [] duals = this.MP.getMPDuals(ds);
			
			solveSP(ds, duals);

			// if Zj-Cj < 0 then add column, else stop

			updateStabilizationParams();			

		}		
	}

	public void initialize(){

	}

	public void solveMaster(DataSource ds) throws IloException, IOException{
		this.MP = new MasterProblem();
		this.MP.buildMP(ds);
		this.MP.getMPCplex().solve();
		System.out.println("status of MP solve: " + this.MP.getMPCplex().getStatus());
		
	}

	public void solveSP(DataSource ds, double [] duals) throws IloException, IOException{
		
		this.SP = new SubProblem();
		this.SP.buildSP(ds, duals);
		this.SP.getSPCplex().solve();
		System.out.println("status of SP solve: " + this.MP.getMPCplex().getStatus());
	}

	public void updateStabilizationParams(){

	}


}
