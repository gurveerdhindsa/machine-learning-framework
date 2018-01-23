package table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import feature.Feature;

/**
 * The Row class creates an arraylist for all the rows;
 * adding,removing,retrieving DataValues
 * 
 * @author Abu Abdul
 * @author Rohan Katkar
 * @author AlyKhan Barolia
 * @author Gurveer Dhindsa
 * 
 * @version 2.0
 */

public class Row implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Feature> values;
	
	/**
	 * Constructor for Row class, initalizes an arraylist for each row
	 * @param none
	 * @return none
	 */
	public Row()
	{
		this.values = new ArrayList<>();
	}
	
	/**
	 * Constructor passing values of type DataValue that is generic
	 * @param values
	 * @return none
	 */
	public Row(List<Feature> values)
	{
		this.values = new ArrayList<>();
		for(Feature somevalue : values)
		{
			this.values.add(somevalue);
		}
	}
	
	/**
	 * addvalue method add values of type DataValue to the row
	 * @param value
	 * @return none
	 */
	public void addValue(Feature value)
	{
		this.values.add(value);
	}
	
	/**
	 * Sets value of a Feature given the index
	 * 
	 * @param value The value you wish to change it to
	 * @param index where the feature is located
	 * @return None
	 */
	public void setValue(Feature value, int index)
	{
		this.values.set(index,value);
	}
	
	/**
	 * getValue method retrieves the value of the row based on the index passed
	 * @param index
	 * @return value in the row
	 */
	public Feature getValue(int index)
	{
		return this.values.get(index);
	}
	
	/**
	 * removeValue method removes a value from a row based on the index in the arraylist
	 * that is passed
	 * @param index
	 * @return the value removed
	 */
	public Feature removeValue(int index)
	{
		return this.values.remove(index);
	}
	
	/**
	 * getValues method retrieves all values in the row
	 * @param none
	 * @return values
	 */
	public List<Feature> getValues()
	{
		return this.values;
	}
	
	public void removeAllValues()
	{
		values.clear();
	}
}