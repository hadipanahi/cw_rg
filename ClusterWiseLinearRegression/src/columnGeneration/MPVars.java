package columnGeneration;

import ilog.concert.IloException;
import ilog.concert.IloNumVar;
import ilog.concert.IloNumVarType;
import ilog.cplex.IloCplex;

public class MPVars {

	IloNumVar [] z;

	public MPVars(){

	}

	public MPVars(IloCplex cplex, int columnCount) throws IloException{
				
		this.z = new IloNumVar[columnCount];
		for(int i = 1; i <= columnCount; i++){
			
			this.z[i - 1] = cplex.numVar(0, 1, IloNumVarType.Bool, "MP_Z_".concat(Integer.toString(i)));			
		}
	}

}
