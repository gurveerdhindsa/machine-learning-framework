package distance;

import java.io.Serializable;

import feature.DoubleFeature;
import feature.StringFeature;

/**
 * A class that calculates the binary distance using the DistanceMetric interface
 * 
 * @author Abu Abdul
 * @author Gurveer Dhindsa
 * @author AlyKhan Barolia
 * @author Rohan Katkar
 *
 * @version 1.0
 */
public class BinaryDistance extends DistanceMetric implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 * 
	 * @param None
	 */
	public BinaryDistance()
	{
		
	}

	/**
	 * Calculates the distance between two DoubleFeature objects
	 * 
	 * @param feature1 a DoubleFeature object
	 * @param feature2 a DoubleFeature object
	 * @return 0 if the objects are the same, return 1 if different
	 */
	public int calculateDoubleFeature(DoubleFeature feature1, DoubleFeature feature2) {
		
		if(feature1.getValue() == feature2.getValue())
		{
			return 0;
		}
		return 1;
	}
	
	/**
	 * Calculates distance between two StringFeature objects
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