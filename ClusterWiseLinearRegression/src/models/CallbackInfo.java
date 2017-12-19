package models;

import gurobi.GRB;
import gurobi.GRBCallback;
import gurobi.GRBException;

public class CallbackInfo extends GRBCallback {

	public double lb;
	public double ub;
	
	@Override
	protected void callback() {
		// TODO Auto-generated method stub
		if(where == GRB.CB_MIP){
			
			try {
				double nodecnt = getDoubleInfo(GRB.CB_MIP_NODCNT);
				double objbst  = getDoubleInfo(GRB.CB_MIP_OBJBST);
		        double objbnd  = getDoubleInfo(GRB.CB_MIP_OBJBND);
		        int    solcnt  = getIntInfo(GRB.CB_MIP_SOLCNT);
		        if(nodecnt == 0)
		        	System.out.println("***** node 0: " + nodecnt);
		        
		        if(nodecnt == 1)
		        	System.out.println("***** node 1: " + nodecnt);
			} catch (GRBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		}
	}

}
