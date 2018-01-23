package feature;

import java.io.Serializable;

import distance.DistanceMetric;

/**
 * 
 * @author Abu Abdul
 * @author Gurveer Dhindsa
 * @author AlyKhan Barolia
 * @author Rohan Katkar
 *
 * @version 1.0
 */
public class DoubleFeature extends Feature implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double value;
	private DistanceMetric distance;
	
	/**
	 * Constructor to initialize value
	 * 
	 * @param value contains value of the DoubleFeature
	 * @return None
	 */
	public DoubleFeature(double value)
	{
		this.value = value;
	}
	
	/**
	 * Default Constructor
	 * 
	 * @param None
	 * @return None
	 */
	public DoubleFeature()
	{
		
	}
	
	/**
	 * Constructor to initialize variables
	 * 
	 * @param value of the DoubleFeature
	 * @param metric DistanceMetric being used for the ComplexFeature object
	 * @return None
	 */
	public DoubleFeature(double value, DistanceMetric metric)
	{
		this.value = value;
		this.distance = metric;
	}
	
	/**
	 * Gets the value of a DoubleFeature object
	 * 
	 * @param None
	 * @return value of the object
	 */
	public double getValue()
	{
		return this.value;
	}
	
	/**
	 * Sets the calculate of a DoubleFeature object
	 * 
	 * @param value the desired number to change the value to
	 * @return None
	 */
	public void setValue(double value)
	{
		this.value =value;
	}
	
	
	/**
	 * Calculate distances between two feature values
	 * 
	 * @param newFeature the Feature you wish to compare with
	 * @return distance between the two objects
	 */
	@Override
	public int getDistance(Feature newFeature)
	{
		return this.distance.calculate(this, newFeature);
	}
	
	/**
	 * Sets the DistanceMetric of a Feature
	 * 
	 * @param distance the DistanceMetric you wish to use
	 * @return None
	 */
	@Override
	public void setDistanceMetric(DistanceMetric distance)
	{
		this.distance = distance;
	}
	
	/**
	 * Gets the DistanceMetric of a DoubleFeature
	 * 
	 * @param None
	 * @return DistanceMetric the object is using
	 */
	@Override
	public DistanceMetric getDistanceMetric()
	{
		return this.distance;
	}
	
	/**
	 * Checks if two Features are the same
	 * 
	 * @param otherFeature holds the other DoubleFeature object
	 * @return true if same, false if different
	 */
	@Override
	public boolean equals(Feature otherFeature)
	{
		if(this==otherFeature)
		{
			return true;
		}
		if(!(otherFeature instanceof DoubleFeature))
		{
			return false;
		}
		return this.value == ((DoubleFeature)otherFeature).getValue();
	}

	/**
	 * Prints the value of the object
	 * 
	 * @param None
	 * @return the value of the object
	 */
	@Override
	public String toString() {
		return Double.toString(this.value);
	}
}