package feature;

import java.io.Serializable;

import distance.DistanceMetric;

/**
 * Table class creates a table to store rows, columns and retrieve them as well
 * 
 * @param <T>
 * @author Abu Abdul
 * @author Gurveer Dhindsa
 * @author Rohan Katkar
 * @author AlyKhan Barolia
 * 
 * @version 1.0
 */
public class StringFeature extends Feature implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String value;
	private DistanceMetric distance;
	
	/**
	 * Constructor used to initialize values
	 * 
	 * @param value value of the StringFeature object
	 * @return None
	 */
	public StringFeature(String value)
	{
		this.value = value;
	}
	
	/**
	 * Default constructor
	 * 
	 * @param None
	 * @return None
	 */
	public StringFeature()
	{
		
	}
	
	/**
	 * Constructor used to initialize variables
	 * 
	 * @param value value of the StringFeature object
	 * @param metric the distance metric this object will use
	 * @return None
	 */
	public StringFeature(String value, DistanceMetric metric)
	{
		this.value = value;
		this.distance = metric;
	}
	
	/**
	 * Used to get the current value of the StringFeature object
	 * 
	 * @return the String value of the object
	 */
	public String getValue()
	{
		return this.value;
	}
	
	/**
	 * Used to set the value of a StringFeature object
	 * 
	 * @param value the value you wish to change it too
	 * @return None
	 */
	public void setValue(String value)
	{
		this.value = value;
	}
	
	/**
	 * Used to compare two features
	 * 
	 * @param feature1 the object you wish to compare it to
	 * @return 0 if the string values are the same, 1 if different
	 */
	public int compare(StringFeature feature1)
	{
		if(this.value.equals(feature1.getValue()))
		{
			return 0;
		}
		return 1;
	}
	
	/**
	 * Used to calculate the distance between two objects
	 * 
	 * @param newFeature the StringFeature you wish to compare it to
	 * @return the distance between the two StringFeature objects
	 */
	@Override
	public int getDistance(Feature newFeature)
	{
		return this.distance.calculate(this, newFeature);
	}
	
	/**
	 * Used to set the DistanceMetric to another metric scale
	 * 
	 * @param the DistanceMetric you wish to change it to
	 * @return None 
	 */
	@Override
	public void setDistanceMetric(DistanceMetric distance)
	{
		this.distance = distance;
	}
	
	/**
	 * Used to get the DistanceMetric currently being used by the object
	 * 
	 * @param None
	 * @return the DistanceMetric the object is set to
	 */
	@Override
	public DistanceMetric getDistanceMetric()
	{
		return this.distance;
	}
	
	/**
	 * Used to see if two string objects are the same
	 * 
	 * @param otherFeature the feature you wish to compare it with
	 * @return true if the are the same object, false if different
	 */
	@Override
	public boolean equals(Feature otherFeature)
	{
		if(this == otherFeature)
		{
			return true;
		}
		if(!(otherFeature instanceof StringFeature))
		{
			return false;
		}
		return this.value.equals(((StringFeature)otherFeature).getValue());
	}

	/**
	 * Used to print String values
	 * 
	 * @param None
	 * @return String value of the StringFeature object 
	 * 
	 */
	@Override
	public String toString() {
		return String.valueOf(value);
	}
}