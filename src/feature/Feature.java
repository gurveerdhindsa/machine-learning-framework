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
public abstract class Feature implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Sets the DistanceMetric of a Feature
	 * 
	 * @param otherFeature the Feature the user wishes to compare to
	 * @return true if same, false if different
	 */
	public abstract boolean equals(Feature otherFeature);

	/**
	 * Gets the distance between two Features
	 * 
	 * @param newFeature A Feature object
	 * @return int depending on the type of Feature
	 */
	public abstract int getDistance(Feature newFeature);

	/**
	 * Gets the DistanceMetric of a Feature
	 * 
	 * @param None
	 * @return a DistanceMetric depending on the type of Feature
	 */
	public abstract DistanceMetric getDistanceMetric();

	
	/**
	 * Sets the DistanceMetric of a Feature
	 * 
	 * @param distance the Distance metric of a Feature
	 * @return None
	 */
	public abstract void setDistanceMetric(DistanceMetric distance);
}