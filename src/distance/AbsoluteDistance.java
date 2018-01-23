package distance;

import java.io.Serializable;

import feature.DoubleFeature;
import feature.StringFeature;

/**
 * A class that calculates the absolute distance using the DistanceMetric interface
 * 
 * @author Abu Abdul
 * @author Gurveer Dhindsa
 * @author AlyKhan Barolia
 * @author Rohan Katkar
 * 
 * @version 1.0
 */
public class AbsoluteDistance  extends DistanceMetric implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AbsoluteDistance()
	{
		
	}
	/**
	 * Calculates the Absolute distance between two DoubleFeature objects
	 * 
	 * @param feature1 a DoubleFeature object
	 * @param feature2 another DoubleFeature object
	 * @return the distance between two DoubleFeature objects
	 */
	public int calculateDoubleFeature(DoubleFeature feature1, DoubleFeature feature2) {
		
		return (int) Math.abs(feature1.getValue() - feature2.getValue()); //simply return the absolute value of the difference
	}
	
	/**
	 * Compares to StringFeature objects
	 * 
	 * @param feature1 a StringFeature object
	 * @param feature2 another StringFeature object
	 * @return distance between two StringFeature objects
	 */
	public int calculateStringFeature(StringFeature feature1, StringFeature feature2)
	{
		return feature1.compare(feature2);
	}
}
