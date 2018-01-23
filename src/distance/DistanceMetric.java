package distance;

import java.io.Serializable;

import feature.ComplexFeature;
import feature.DoubleFeature;
import feature.Feature;
import feature.StringFeature;

/**
 * 
 * @author Abu Abdul
 * @author Gurveer Dhindsa
 * @author AlyKhan Barolia
 * @author Rohan Katkar
 * 
 * @version 2.0
 */
public abstract  class DistanceMetric implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public abstract int calculateDoubleFeature(DoubleFeature feature1, DoubleFeature feature2);
	
	public abstract int calculateStringFeature(StringFeature feature1, StringFeature feature2);

	/**
	 * Signature of a method that calculates the distance between two features
	 * 
	 * @param feature1 the first Feature
	 * @param feature2 the second Feature
	 * @return integer contain value
	 */
	public int calculate(Feature feature1, Feature feature2)
	{
		if((feature1 instanceof DoubleFeature) && (feature2 instanceof DoubleFeature))
		{
			return calculateDoubleFeature((DoubleFeature)feature1, (DoubleFeature)feature2);
		}
		else if(feature1 instanceof ComplexFeature && feature2 instanceof ComplexFeature)
		{
			return calculateComplexFeature((ComplexFeature)feature1, (ComplexFeature)feature2);
		}
	    return calculateStringFeature((StringFeature)feature1, (StringFeature)feature2);
	}
	
	public int calculateComplexFeature(ComplexFeature feature1, ComplexFeature feature2)
	{
		int totalDistance =0;
		int size = Math.min(feature1.getSizeofChildFeatures(), feature2.getSizeofChildFeatures());
		
		for(int i=0; i < size; i++)
		{
			if(feature1.getChildFeature(i) == null || feature2.getChildFeature(i) == null)
			{
				totalDistance += 0;
			}
			else if(feature1.getChildFeature(i) instanceof DoubleFeature)
			{
				totalDistance += this.calculateDoubleFeature((DoubleFeature)feature1.getChildFeature(i),(DoubleFeature)feature2.getChildFeature(i));
			}
			else if(feature1.getChildFeature(i) instanceof ComplexFeature)
			{
				totalDistance += this.calculateComplexFeature((ComplexFeature)feature1.getChildFeature(i), (ComplexFeature)feature2.getChildFeature(i));
			}
			else
			{
				totalDistance += this.calculateStringFeature((StringFeature)feature1.getChildFeature(i), (StringFeature)feature2.getChildFeature(i));
			}
		}
		return (int) Math.sqrt(totalDistance);
		
	}
}