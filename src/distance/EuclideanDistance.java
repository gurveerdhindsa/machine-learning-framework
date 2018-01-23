package distance;

import java.io.Serializable;

import feature.DoubleFeature;
import feature.StringFeature;

/**
 * 
 * @author Abu Abdul
 * @author Gurveer Dhindsa
 * @author AlyKhan Barolia
 * @author Rohan Katkar
 *
 * @version 1.0
 */
public class EuclideanDistance extends DistanceMetric implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 * 
	 * @param None
	 * @return None
	 */
	public EuclideanDistance()
	{
		
	}
	
	/**
	 * Calculates the Euclidean distance between two DoubleFeature objects
	 * 
	 * @param feature1 a DoubleFeature object
	 * @param feature2 another DoubleFeature object
	 * @return distance between two DoubleFeature objects.
	 */
	public int calculateDoubleFeature(DoubleFeature feature1, DoubleFeature feature2)
	{
		return (int) Math.pow((Math.abs(feature1.getValue() - feature2.getValue())), 2);
	}
	
	/**
	 * Calculates the Euclidean distance between two StringFeature objects
	 * 
	 * @param feature1 a StringFeature object
	 * @param feature2 another StringFeature object
	 * @return the distance between two StringFeature objects
	 */
	public int calculateStringFeature(StringFeature feature1, StringFeature feature2)
	{
		return (int) Math.pow(feature1.compare(feature2), 2);
	}
}