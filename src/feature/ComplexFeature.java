package feature;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class ComplexFeature extends Feature implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Feature>features;
	private DistanceMetric distance;
	private int no_ofChildFeatures;
	
	/**
	 * Constructor that initializes contents
	 * 
	 * @param distance Distance metric of the ComplexFeature object
	 * @return None 
	 */
	public ComplexFeature(DistanceMetric distance)
	{
		this.distance =distance;
		this.features = new ArrayList<>();
		this.no_ofChildFeatures =0;
	}

	/**
	 * Default Constructor
	 * 
	 * @param None
	 * @return None
	 */
	public ComplexFeature() {
		this.features = new ArrayList<>();
		this.no_ofChildFeatures =0;
	}

	/**
	 * Gets the type of a ComplexFeature
	 * 
	 * @param None
	 * @return a list of features
	 */
	public List<Feature> getChildFeatures()
	{
		return this.features;
	}
	
	/**
	 * Allows user to add a feature to an existing ComplexFeature
	 * 
	 * @param newFeature a new feature that wishes to be added
	 * @return None
	 */
	public void addChildFeature(Feature newFeature)
	{
		this.features.add(newFeature);
		this.no_ofChildFeatures++;
	}
	
	/**
	 * Get the type of Feature at a certain index
	 * 
	 * @param index the number that represents the index
	 * @return the Feature that exists at that index
	 */
	public Feature getChildFeature(int index)
	{
		return this.features.get(index);
	}
	
	/**
	 * Remove a Feature at a certain index
	 * 
	 * @param index the index that holds the Feature to be removed
	 * @return the Feature removed
	 */
	public Feature removeChildFeature(int index)
	{
		return this.features.remove(index);
	}
	
	/**
	 * Sets a DistanceMetric of a ComplexFeature
	 * 
	 * @param distance the DistanceMetric you wish to set the ComplexFeature to
	 * @return None
	 */
	@Override
	public void setDistanceMetric(DistanceMetric distance)
	{
		this.distance = distance;
	}
	
	/**
	 * Gets the DistanceMetric for a ComplexFeature
	 * 
	 * @param None
	 * @return the DistanceMetric of that ComplexFeature
	 */
	@Override
	public DistanceMetric getDistanceMetric()
	{
		return this.distance;
	}
	
	/**
	 * Gets the distance between a Feature
	 * 
	 * @param newFeature a Feature
	 * @return the distance between the two objects
	 */
	@Override
	public int getDistance(Feature newFeature)
	{
		return this.distance.calculate(this, newFeature);
		
	}
	
	/**
	 * Gives the user the number of children in a Feature
	 * 
	 * @param None
	 * @return number of children
	 */
	public int getSizeofChildFeatures()
	{
		return this.no_ofChildFeatures;
	}
	
	/**
	 * Checks if two Feature objects are the same
	 * 
	 * @param otherFeature a Feature you wish to compare with
	 * @return true if objects are the same and false if objects are different 
	 */
	@Override
	public boolean equals(Feature otherFeature)
	{
		boolean value = false;
		if(this == otherFeature)
		{
			return value = true;
		}
		if(!(otherFeature instanceof ComplexFeature))
		{
			return value;
		}
		ComplexFeature param = ((ComplexFeature)otherFeature);
		if(param.getSizeofChildFeatures()== this.no_ofChildFeatures)
		{
			for(int i=0; i < this.no_ofChildFeatures; i++)
			{
				if(param.getChildFeature(i).equals(this.features.get(i)) != true)
				{
					return value = false;
				}
			}
			value = true;
		}
		return value;
	}
	
	public String toString()
	{
		String value = "(";
		int i;
		for(i=0; i < this.no_ofChildFeatures-1; i++)
		{
			if(this.getChildFeature(i)==null)
			{
				value += " ";
			}
			else
			{
				value += this.getChildFeature(i).toString() + ",";
			}
			
		}
		if(this.getChildFeature(i) != null)
		{
			value += this.getChildFeature(i).toString() + ")";
		}
		else
		{
			value += " " + ")";
		}
		
		return value;
	}
	
	public void clear()
	{
		this.features.clear();
	}
}