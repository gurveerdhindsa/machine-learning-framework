package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import feature.DoubleFeature;
import feature.Feature;
import feature.StringFeature;
import table.Precision;
import table.Table; 

/**
 * @author Abu Abdul
 * @author Gurveer Dhindsa
 * @author AlyKhan Barolia
 * @author Rohan Katkar
 * 
 * @version 1.0
 */
public class GUIController implements ActionListener {

	public GUIView gui;

	/**
	 * Constructor to initialize values
	 * 
	 * @param g the view of the GUI
	 * @param m the model of the GUI
	 * @return None
	 */
	public GUIController (GUIView g) {
		gui = g;
	}
	
	/**
	 * Default constructor
	 * 
	 * @param None
	 * @return None
	 */
	public GUIController() {
	}

	/**
	 * Method that listens to GUI interactions
	 * 
	 * @param e the event that occurred
	 * @return None
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Reset"){
			reset();
		}
		else if (e.getActionCommand()=="Start"){
			gui.start();
		}
		else if (e.getActionCommand()=="Predict") {
			gui.initPrediction();
		}
		else if (e.getActionCommand() == "Precision") {
			gui.initPrecision();
		}
		else if (e.getActionCommand()=="Exit Program") {
			Object[] yn = {"Yes", "No"};
			int n = JOptionPane.showOptionDialog(gui.frame,"Are you sure you would like to quit? ","Terminate the Program",JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,null,yn,yn[1]);
			if (n==0){
				System.exit(0);
			}
		}
		
		else if (e.getActionCommand() == "Precision")
		{
			Precision precision1 = new Precision();
		}
		
		else if (e.getActionCommand().equals("Export"))
		{
			gui.exporttoFile();
		}
		
		else if (e.getActionCommand() == "Import")
		{
			gui.importFile();
		}
	}

	/**
	 * Resets the GUI
	 * 
	 * @param None
	 * @return None
	 */
	public void reset() {
		gui.frame.setVisible(false);
		
		try{
			gui.testCaseFrame.setVisible(false);
		}
		catch (NullPointerException e){}
		new GUIView();
	}
}

/**
 * @author Abu Abdul
 * @author Gurveer Dhindsa
 * @author AlyKhan Barolia
 * @author Rohan Katkar
 * 
 * @version 1.0
 *
 */
class TableListener implements TableModelListener {
	private JTable jtable;
	private Table table;

	/**
	 * Constructor to initialize values
	 * 
	 * @param jtable the table used
	 * @param table the model of the table
	 */
	public TableListener(JTable jtable, Table table)
	{
		this.jtable = jtable;
		this.table = table;
	}

	/**
	 * Listens to a change made in the table
	 * 
	 * @param e the event that occurs in the table
	 * @return None
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		if (jtable.isEditing())
		{
			//An edit occurred
			//Store the old edit
			int selectedRow = jtable.getSelectedRow();
			int selectedCol = jtable.getSelectedColumn();
			
			String oldEdit = table.getValueAt(selectedRow,selectedCol);
			String newEdit = (String) jtable.getValueAt(selectedRow, selectedCol);
			
			//Determine type of variable newEdit (String, Double, Complex)
			//Instantiate a feature object of that type
			
			Feature feature = null;
			
			if (table.getColumn(selectedCol).getFeature().equals("String Feature"))
				feature = new StringFeature(newEdit);
			else if (table.getColumn(selectedCol).getFeature().equals("Double Feature"))
				feature = new DoubleFeature(Double.parseDouble(newEdit));
			else
				//feature = new ComplexFeature();
			
			table.updateValueAt(feature, selectedRow, selectedCol);
			
			jtable.setModel(table.getDataTable());
		}
	}
}