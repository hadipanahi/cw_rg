package dataSource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import ilog.cplex.IloCplex;

public class DataSource {

	private final String path = "./data/clustreg_I200_L52_seed";
	public final int bigM = 1000000;
	public final int clusterNo = 3;// K
	public final int obsByHybWeekCount = 1;
	public final int obsByHybCount = 52 * 1;//L
	public final int minClusterEntities = 5; //n
	public final int independentVars = 3; // J	
	
	public int entitySize; //I	
	public List<Observation> observations = new ArrayList<Observation>();
	public HashMap<Integer, List<ObsByItem>> observationsByItem = new HashMap<Integer, List<ObsByItem>>();
	public Table<Integer, Integer, ObsByItemByWeek> observationsByItemByWeek = HashBasedTable.create();
	public List<Integer> entities = new ArrayList<Integer>();
	public List<Integer> weeks = new ArrayList<Integer>();

	public List<Integer> getWeeks(){
		return this.weeks;
	}

	public List<Integer> getEntities(){
		return this.entities;
	}

	public DataSource() throws IOException{
		populateData();
	}
	
	public void populateData() throws IOException{

		for(int i = 1; i <= this.obsByHybWeekCount; i++){	

			//System.out.println("-------------------------------- Observation: " + i);
			String updatedPath = this.path.concat(Integer.toString(i).concat(".txt"));
			BufferedReader in = new BufferedReader(new FileReader(updatedPath));
			String line;
			int lineNo = 1;
			while((line = in.readLine()) != null)
			{			
				String[] values = line.split("\t");
				//System.out.print("item: " + values[0]);
				//System.out.print(" week: " + values[1]);
				//System.out.print(" sales: " + values[2]);
				//System.out.print(" discount: " + values[3]);
				//System.out.println();

				if(lineNo > 1){

					updateEntities(Integer.parseInt(values[0]));
					updateWeeks(Integer.parseInt(values[1]));				


					///// Populating observation array list 
					Observation o = new Observation(Integer.parseInt(values[0]), Integer.parseInt(values[1]), 
							Double.parseDouble(values[2]), Double.parseDouble(values[3]));
					observations.add(o);

					///// Populating observations by item
					if(observationsByItem.containsKey(Integer.parseInt(values[0]))){

						ObsByItem oi = new ObsByItem(Integer.parseInt(values[1]), 
								Double.parseDouble(values[2]), Double.parseDouble(values[3]));
						observationsByItem.get(Integer.parseInt(values[0])).add(oi);			

					}
					else{

						observationsByItem.put(Integer.parseInt(values[0]), new ArrayList<ObsByItem>());
						ObsByItem oi = new ObsByItem(Integer.parseInt(values[1]), 
								Double.parseDouble(values[2]), Double.parseDouble(values[3]));
						observationsByItem.get(Integer.parseInt(values[0])).add(oi);
					}


					//// Populating observations by item and week
					ObsByItemByWeek oiw = new ObsByItemByWeek(Double.parseDouble(values[2]), 
							Double.parseDouble(values[3]));
					observationsByItemByWeek.put(Integer.parseInt(values[0]), Integer.parseInt(values[1]), oiw);
				}
				lineNo++;		

			}
		}
		this.entitySize = entities.size();
		
		
	
	}

	public void dataReport(){
		System.out.println("observations size: " + observations.size());
		System.out.println("observationsByItem size: " + observationsByItem.size());
		System.out.println("observationsByItemByWeek: " + observationsByItemByWeek.size());
		System.out.println("entity list size: " + entities.size());
		System.out.println("week list size: " + weeks.size());

	}


	public void updateWeeks(Integer w){
		if(!weeks.contains(w)){
			weeks.add(w);
		}
	}	

	public void updateEntities(Integer e){
		if(!entities.contains(e)){
			entities.add(e);
		}
	}



}
