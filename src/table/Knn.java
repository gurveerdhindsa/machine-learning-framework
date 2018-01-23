package table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import feature.DoubleFeature;
import feature.Feature;

/**
 * 
 * @author Abu Abdul
 * @author Gurveer Dhindsa
 * @author AlyKhan Barolia
 * @author Rohan Katkar
 * 
 * @version 2.0
 */
public class Knn{
	private Table table;
	private Row testRow;
	private Map <Row, Double> neighbors;
	private List<Row> minNeighbors;
	private List<ArrayList<Double>> notNormalizedDistances;
	private List<ArrayList<Double>> normalizedDistances = new ArrayList<>();
	private List<Double> distanceSum = new ArrayList<>();
	private Double [] maxDistances;
	private int colIndex;
	private int k;
	private static final int DEFAULT_K = 1;
	
	/**
	 * Constructor to initialize
	 * 
	 * @param None
	 * @return None
	 */
	public Knn ()
	{
		this(DEFAULT_K);
	}
	
	/**
	 * Constructor to initialize values
	 * 
	 * @param kValue the number of neighbors the user wishes to have
	 * @return None
	 */
	public Knn(int kValue)
	{
		minNeighbors = new ArrayList<>();
		neighbors = new HashMap<>();
		notNormalizedDistances = new ArrayList<>();
		normalizedDistances = new ArrayList<>();
		this.k = kValue;
	}
	
	/**
	 * Predicts values based on regression approach
	 * 
	 * @param table The table with the values he wishes to perform this operation on
	 * @param testRow the row the user wishes to test
	 * @param colIndex index of the column in the table
	 * @return a Feature representing the predicted values
	 */
	public Feature predictRegression(Table table, Row testRow, int colIndex)
	{
		this.table = table;
		this.maxDistances = new Double[table.getNumOfColumns()];
		this.testRow = testRow;
		this.colIndex = colIndex;
		
		this.populateMaxDistances();
		this.findNeighbors();

		this.findKNeigbors();
		return this.regression();
	}
	
	public List<Feature>predictRegression(Table training, Table test, int colIndex)
	{
		List<Feature>results = new ArrayList<>();
		for(int i=0; i<test.getNumOfRows(); i++)
		{
			results.add(this.predictRegression(training, test.getRow(i), colIndex));
		}
		return results;
	}
	public List<Feature> predictClassification(Table training, Table test, int colIndex)
	{
		List<Feature>results = new ArrayList<>();
		for(int i=0; i <test.getNumOfRows(); i++)
		{
			results.add(this.predictClassification(training, test.getRow(i), colIndex));
		}
		return results;
	}
	/**
	 * Predicts values based on classifacaton approach
	 * 
	 * @param table The table with the values he wishes to perform this operation on
	 * @param testRow the row the user wishes to test
	 * @param colIndex index of the column in the table
	 * @return a Feature representing the predicted values
	 */
	public Feature predictClassification(Table table, Row testRow, int colIndex)
	{
		this.colIndex = colIndex;
		this.table = table;
		maxDistances = new Double[table.getNumOfColumns()];
		this.testRow = testRow;
		
		
		this.populateMaxDistances();
		this.findNeighbors();
	
		this.findKNeigbors();
		return this.classification();
	
	}
	
	/**
	 * Used to fill empty cells in the array
	 * 
	 * @param None
	 * @return None
	 */
	private void populateMaxDistances()
	{
		for(int i=0; i < this.maxDistances.length; i++)
		{
			this.maxDistances[i] =0.0;
		}
	}
	
	/**
	 * Used to find the neighboring values
	 * 
	 * @param None
	 * @return None
	 */
	private void findNeighbors()
	{
		this.neighbors = new HashMap<>();
		//Traverse through rows
		for (int i = 0; i < table.getNumOfRows(); i++)
		{
			compareWithTest(testRow, table.getRow(i));
		}
		
		//Find max distance in each column
		findMaxDistances();
		
		//Normalize the calculated distances
		normalizeDistances();
		
		//Add up all the distances
		sumDistances();
		
		int count = 0;
		for (Row row : table.getRows())
		{
			neighbors.put(row, distanceSum.get(count));
			count++;
		}
		
	}
	
	/**
	 * Used to compare the test row with a row in the table
	 * 
	 * @param testRow the test row created by the user
	 * @param tableRow the row that exists in the table
	 */
	private void compareWithTest( Row testRow,Row tableRow)
	{
		ArrayList<Double> rowDistances = new ArrayList<>();
		
		//Traverse through columns and get distance between each columns of test row and train row
		for (int j = 0; j < table.getNumOfColumns(); j++)
		{
			
			if((testRow.getValue(j) != null)&& (tableRow.getValue(j)!= null))
			{
				rowDistances.add((double) testRow.getValue(j).getDistance(tableRow.getValue(j)));
			}
			else
			{
				rowDistances.add(0.0);
			}
		}
		
		notNormalizedDistances.add(rowDistances);
	}
	
	/**
	 * Used to find the distances in the array
	 * 
	 * @param None
	 * @return None
	 */
	private void findMaxDistances()
	{

		for (ArrayList<Double> row : notNormalizedDistances)
		{
			for(int i=0; i < this.table.getNumOfColumns(); i++)
			{
				if(row.get(i) > this.maxDistances[i])
				{
					this.maxDistances[i] = row.get(i);
				}
			}
		}
		//Check if any values max distances are = 0
		for (int i = 0; i < maxDistances.length; i++)
		{
			if (maxDistances[i] == 0)
			{
				maxDistances[i] = 1.0;
			}
		}
	}
	
	/**
	 * Used to normalize the distance after a distance calculation
	 * 
	 * @param None
	 * @return None
	 */
	private void normalizeDistances ()
	{
		for (ArrayList<Double> row : notNormalizedDistances)
		{
			int index = 0;
			ArrayList <Double> normalized = new ArrayList<>();
			
			for (Double value : row)
			{
				normalized.add(value/maxDistances[index]);
				index++;
			}
			normalizedDistances.add(normalized);
		}
	}
	
	/**
	 * Used to add all the distances in the array
	 * 
	 * @param None
	 * @return None
	 */
	private void sumDistances()
	{
		for (ArrayList<Double> row : normalizedDistances)
		{
			double sum = 0;
			for (Double value : row)
			{
				sum += value;
			}
			
			distanceSum.add(sum);
		}
	}
	
	/**
	 * Used to find number of neighbors
	 * 
	 * @param None
	 * @return None
	 */
	private void findKNeigbors()
	{
		//so predict can be called on one knn instance multiple times
		this.minNeighbors = new ArrayList<>();
		int kTemp = k;
		while (kTemp != 0)
		{
			Map.Entry <Row, Double> minEntry = null;
			for (Map.Entry<Row, Double> neighbor : neighbors.entrySet())
			{
				if (minEntry == null || minEntry.getValue() > neighbor.getValue())
				{
					minEntry = neighbor;
				}
			}
			minNeighbors.add(minEntry.getKey());
			neighbors.remove(minEntry.getKey());
			kTemp--;
		}
	}
	
	/**
	 * Used to define how a regression approach should operate
	 * 
	 * @param None
	 * @return a Feature after regression on the object is called
	 */
	private Feature regression()
	{
		double value = 0;
		for(Row row : this.minNeighbors)
		{
			value += ((DoubleFeature)row.getValue(this.colIndex)).getValue();
		}
		value = value/ this.k;
		return (new DoubleFeature(value));
	}
	
	/**
	 * Used to define how a classification approach should operate
	 * 
	 * @param None
	 * @return a Feature after classification on the object is called
	 */
	private Feature classification()
	{
		Feature predictedFeature = null;
		List<Feature>columnValuesofNeighbors = new ArrayList<>();
		for(Row neighbor : minNeighbors)
		{
			Feature value = neighbor.getValue(this.colIndex);
			columnValuesofNeighbors.add(value);
		}
		int frequency;
		int highestfrequency=0;
		for(Feature value : columnValuesofNeighbors)
		{
			frequency = Collections.frequency(columnValuesofNeighbors, value);
			if(frequency > highestfrequency)
			{
				highestfrequency = frequency;
				predictedFeature = value;
			}
		}
		return predictedFeature;
	}
}