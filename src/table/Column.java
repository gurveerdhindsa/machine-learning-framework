package table;

import java.io.Serializable;

/**
 * 
 * This class is responsible for creating, and manipulating column elements in a table.
 * 
 * @author Abu Abdul
 * @author Gurveer Dhindsa
 * @author Rohan Katkar
 * @author AlyKhan Barolia
 *
 * @version 2.0
 */
public class Column implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String feature;
	private String metric;
	
	/**
	 * A constructor that initializes the name of the column, the type of metric 
	 * system used (from the distance class) and the type of value that was chosen from the 
	 * possible enums.
	 * 
	 * @param name 			the name of the column
	 * @param typeOfMetric 	A paramter that is either EUCLIDEAN, ABSOLUTE or BINARY
	 * @param typeOfValue 	A parameter that is either CONTINUOUS OR BINARY
	 * 
	 */
	public Column (String name, String feature, String metric)
	{
		this.name = name;
		this.feature = feature;
		this.metric = metric;
	}

	/**
	 * A method that gets and returns the name of a column.
	 * 
	 * @return a string that is the name of the column
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * A method that allows the caller to manipulate the column name of a table
	 * 
	 * @param name 	the new desired name for the column
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the String value of a Feature
	 * 
	 * @param None
	 * @return a String with the name of the feature
	 */
	public String getFeature() {
		return feature;
	}

	/**
	 * Sets the string of a Feature to a desired value
	 * 
	 * @param feature the String you wish to set the object to
	 * @return None
	 */
	public void setFeature(String feature) {
		this.feature = feature;
	}

	/**
	 * Gets the metric being used on a Feature
	 * 
	 * @param None
	 * @return metric a String that returns the metric
	 */
	public String getMetric() {
		return metric;
	}

	/**
	 * Sets a metric of a feature to a desired metric
	 * 
	 * @param metric the new desired metric to be used on the called object
	 * @return None
	 */
	public void setMetric(String metric) {
		this.metric = metric;
	}
}