package table;
import java.util.ArrayList;
import java.util.List;

import feature.DoubleFeature;
import feature.Feature;

public class Precision {
				
	public Precision()
	{
		
	}
	
	public double checkPrecisionClassification(Table training, Table test, int colIndex, List<Feature>known)
	{
		List<Feature>results = new ArrayList<>();
		List<Feature>knownValues = known;
		double count =0.0;
		Knn predict = new Knn(3);
		results = predict.predictClassification(training, test, colIndex);
		for(int i=0; i < knownValues.size(); i++)
		{
			if(results.get(i).equals(knownValues.get(i)))
			{
				count++;
			}
		}
			
		
		return (count/knownValues.size()) * 100;
	}
	public double checkPrecisionClassification(Table Training, Table test, int colIndex)
	{
		List<Feature>knownValues = populateClassification(test,colIndex);
		Knn predict = new Knn(3);
		List<Feature>results = predict.predictClassification(Training, test, colIndex);
		double count =0.0;
		for(int i=0; i < knownValues.size(); i++)
		{
			if(results.get(i).equals(knownValues.get(i)))
			{
				count++;
			}
		}
		return (count/knownValues.size()) * 100;
	}
	
	public double checkPrecisionRegression(Table training, Table test, int colIndex)
	{
		List<Double>knownValues = populateRegression(test,colIndex);
		List<Feature>results = new ArrayList<>();
		List<Double>resultsDouble;
		double count =0.0;
		Knn predict = new Knn();
		results = predict.predictRegression(training, test, colIndex);
		resultsDouble = getValues(results);
		
		for(int i=0; i < knownValues.size(); i++)
		{
			double difference = Math.abs(knownValues.get(i) - resultsDouble.get(i));
			count += difference;
		}
		
		return (count/knownValues.size()) * 100;
		
	}
	
	private List<Double> getValues(List<Feature>features)
	{
		List<Double>finalValues = new ArrayList<>();
		for(int i=0; i <features.size(); i++)
		{
			DoubleFeature feature = (DoubleFeature)features.get(i);
			finalValues.add(feature.getValue());
		}
		return finalValues;
	}
	private  List<Double>populateRegression(Table test, int colIndex)
	{
		List<Double>values = new ArrayList<>();
		for(int i=0; i < test.getNumOfRows(); i++)
		{
			DoubleFeature feature = (DoubleFeature)test.getRow(i).getValue(colIndex);
			values.add(feature.getValue());
		}
		return values;
	}
	
	@SuppressWarnings("unused")
	private List<Feature> populateClassification(Table test, int colIndex)
	{
		List<Feature>values = new ArrayList<>();
		for(int i=0; i <test.getNumOfRows(); i++)
		{
			values.add(test.getRow(i).getValue(colIndex));
		}
		return values;
	}
}