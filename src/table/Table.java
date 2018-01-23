package table;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import feature.Feature;
import feature.StringFeature;

/**
 * Table class creates a table to store rows, columns and retrieve them as well
 * 
 * @param <T>
 * @author Abu Abdul
 * @author Gurveer Dhindsa
 * @author Rohan Katkar
 * @author AlyKhan Barolia
 * 
 * @version 2.0
 */

public class Table implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List <Column> columns;
	private List <Row> rows;
	private DefaultTableModel tableModel;
	private int predictionIndex;
	
	/**
	 * Table constructor creates a list of type Columns
	 */
	public Table()
	{
		columns = new ArrayList<Column>();
		rows = new ArrayList<Row>();
		this.tableModel = new DefaultTableModel();
	}
	
	/**
	 * Table constructor being passed column instances
	 * @param columns
	 */
	public Table(List<Column>columns)
	{
		super();
		this.tableModel = new DefaultTableModel();
		this.columns = new ArrayList<>(columns);
		updateTableModelColumns();
		rows = new ArrayList<Row>();
	}
	
	/**
	 * Used to get the rows stored in a table
	 * 
	 * @param None
	 * @return rows all the rows that exist in a list
	 */
	public List<Row> getRows()
	{
		return rows;
	}
	
	/**
	 * Used to get the columns stored in a table
	 * 
	 * @param None
	 * @return columns all the columns that exist in a list
	 */
	public List<Column> getColumns()
	{
		return columns;
	}
	
	/**
	 * insertColumn method inserts columns into the table
	 * @param columnName
	 * @param distanceMetric
	 * @param columnType
	 * @return none
	 */
	public void insertColumn (String columnName, String feature, String metric)
	{
		this.columns.add(new Column(columnName,feature, metric));
		this.tableModel.addColumn(new Column(columnName, feature, metric).getName());
	}
	

	/**
	 * insertRow inserts a row into column
	 * @param rowData
	 * @return none
	 */
	public void insertRow (List <Feature> rowData)
	{
		rows.add(new Row (rowData));
		String [] newRow = new String[rowData.size()];
		for(int i =0; i < newRow.length; i++)
		{
			newRow[i] = rowData.get(i).toString();
		}
		this.tableModel.addRow(newRow);
	}
	
	/**
	 * Used to insert a rows data into the list
	 * 
	 * @param rowData data of the row to be added to the list
	 * @return None
	 */
	public void insertRow(Row rowData)
	{
		rows.add(rowData);
		String [] newRow = new String[rowData.getValues().size()];
		for(int i=0; i < newRow.length; i++)
		{
			if (rowData.getValue(i) == null)
			{
				String invalid = "";
				newRow[i] = invalid;
			}
			else
				newRow[i] = rowData.getValue(i).toString();
		}
		this.tableModel.addRow(newRow);
	}
	
	
	/**
	 * getColumn of type Column method return a column at a specific index
	 * @param index
	 * @return column of the index specified
	 */
	public Column getColumn (int index)
	{
		return columns.get(index);
	}
	
	/*
	 * Return a Column object by it's name
	 */
	
	/**
	 * getColumn of type Column returns a column object by its name
	 * @param name
	 * @return null
	 */
	public Column getColumn (String name)
	{
		for (int i = 0; i < columns.size(); i++)
		{
			if (columns.get(i).getName().equals(name))
			{
				return columns.get(i);
			}
		}
		return null;
	}
	
	/**
	 * isDuplicateColumnName returns a boolean if a column name already exists
	 * @param name
	 * @return boolean, true if there is a column name that already exists
	 * else it is false
	 */
	public boolean isDuplicateColumnName (String name)
	{
		for (Column column : columns)
		{
			if (column.getName().equalsIgnoreCase(name))
			{
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * getNumOfColumns retruns the number of columns in the table
	 * @param none
	 * @return numColumns, number of columns
	 */
	public int getNumOfColumns()
	{
		return columns.size();
	}
	
	
	/**
	 * getNumofRows gets the number of rows in the table
	 * @param none
	 * @return numRows, number of rows
	 */
	public int getNumOfRows()
	{
		return rows.size();
	}

	/*
	 * Returns the names of all columns
	 */
	/**
	 * getColumnNames returns the names of all columns
	 * @param none
	 * @return columnNames, the names
	 */
	public List<String> getColumnNames()
	{
		List<String> columnNames = new ArrayList<>();
		
		for (Column column : columns)
		{
			columnNames.add(column.getName());
		}
		
		return columnNames;
	}
	
	/**
	 * Used to get the Column name in a table
	 * 
	 * @param index the index in the list where the column is located
	 * @return a String containing the name of the column
	 */
	public String getColumnName (int index)
	{
		return columns.get(index).getName();
	}
	
	
	/**
	 * getColumnIndex returns the index for a specific column name
	 * and returns null if the column name is not found
	 * @param name
	 * @return -1
	 */
	public int getColumnIndex(String name)
	{
		int index = 0;
		
		for (Column column : columns)
		{
			if (column.getName().equalsIgnoreCase(name))
				return index;
			index++;
		}
		return -1;
	}

	/**
	 * Used to get the value of a certain cell at a specific position
	 * 
	 * @param row the row number its located in
	 * @param col the column number its located in
	 * @return a String with the value
	 */
	public String getValueAt (int row, int col)
	{
		for (int i = 0; i < rows.size(); i++)
		{
			if (i == row)
			{
				StringFeature value = (StringFeature)rows.get(i).getValue(col);
				return value.getValue();
			}
		}
		return null;
	}
	
	/**
	 * Used to update the value of a cell at a certain position
	 * 
	 * @param value the value the user wishes to change to
	 * @param row the row index to where the value to be changed exists
	 * @param col the column index to where the value to be changed exists
	 * @return None
	 */
	public void updateValueAt (Feature value, int row, int col)
	{
		rows.get(row).setValue(value, col);
		tableModel.setValueAt(value, row, col);
	}
	
	/**
	 * Used to set the prediction column
	 * 
	 * @param index the index where the prediction column is
	 * @return None
	 */
	public void setPredictionColumn(int index)
	{
		predictionIndex = index;
	}
	
	/**
	 * Used to get the prediction index in a table
	 * 
	 * @param None
	 * @return a number representing the prediction index
	 */
	public int getPredictionIndex() {
		return predictionIndex;
	}

	/**
	 * getRow of type Row retrieves the row at a certain index
	 * @param index
	 * @return the row at the specified index
	 */
	public Row getRow(int index)
	{
		return rows.get(index);
	}

	/**
	 * Used to get the existing table model being used
	 * 
	 * @param None
	 * @return a TableModel representing the existing table model
	 */
	public TableModel getDataTable() {
		return this.tableModel;
	}
	
	/**
	 * Used to update columns in a table
	 * 
	 * @param None
	 * @return None
	 */
	public void updateTableModelColumns()
	{
		for (Column column : columns)
		{
			tableModel.addColumn(column.getName());
		}
	}
	
	public void clearAllRows()
	{
		this.rows.clear();
	}
	
	public static void exportTable(String fileName, Table export) throws IOException
	{
		try
		{
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(export);
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static Table importTable (String fileName) 
	{
		Table importedTable = null;
		try
		{
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			importedTable = (Table)in.readObject();
			in.close();
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("The file " + fileName + "was not found" );
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return importedTable;
	}
}