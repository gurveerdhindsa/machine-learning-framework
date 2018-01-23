package gui;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import distance.AbsoluteDistance;
import distance.BinaryDistance;
import distance.DistanceMetric;
import distance.EuclideanDistance;
import feature.ComplexFeature;
import feature.DoubleFeature;
import feature.Feature;
import feature.StringFeature;
import table.Knn;
import table.Precision;
import table.Row;
import table.Table;

import java.util.*;
/**
 * A class that provides the view for the Graphical user interface
 * 
 * @author Abu Abdul
 * @author Gurveer Dhindsa
 * @author AlyKhan Barolia
 * @author Rohan Katkar
 * 
 * @version 1.0
 */
public class GUIView extends JFrame {
	enum Prediction{REGRESSION, CLASSIFICATION};

	private static final long serialVersionUID = 1L;
	private GUIController controller;
	private int knnValue;

	JFrame frame;
	JFrame testCaseFrame;
	JTable jtable;
	JTable jtabletest;

	Table training;
	Table testTable;
	Row testRow;

	//GUI components
	JMenuBar menubar;
	JMenu tablemenu, simulate;
	JMenuItem exit, reset, start, predict, precisionItem, export, importFile;


	/**
	 * Constructor to initialize view and contents within the frame
	 * 
	 * @param None
	 * @return None
	 */
	public GUIView() {

		controller = new GUIController(this);

		frame = new JFrame("Simple Machine Learning Framework");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(400,300);

		menubar = new JMenuBar();  

		tablemenu = new JMenu("Table");
		simulate = new JMenu("Simulation");
		exit = new JMenuItem("Exit Program");
		reset = new JMenuItem ("Reset");
		start = new JMenuItem ("Start");
		predict = new JMenuItem ("Predict");
		precisionItem = new JMenuItem ("Precision");
		export = new JMenuItem ("Export");
		importFile = new JMenuItem ("Import");
		export.setEnabled(false);

		menubar.add(tablemenu);
		menubar.add(simulate);
		tablemenu.add(reset);
		tablemenu.add(exit);
		simulate.add(start);
		simulate.add(precisionItem);
		simulate.add(predict);
		simulate.add(export);
		simulate.add(importFile);

		exit.addActionListener(controller);
		reset.addActionListener(controller);
		start.addActionListener(controller);
		predict.addActionListener(controller);
		precisionItem.addActionListener(controller);
		export.addActionListener(controller);
		importFile.addActionListener(controller);

		//precisionItem.setEnabled(false);
		predict.setEnabled(false);

		frame.setJMenuBar (menubar);
		frame.setVisible(true);
	}

	/**
	 * Used to initialize columns in the table by prompting user
	 * 
	 * @param None
	 * @return None
	 */
	public void initColumns(){
		training = new Table();

		//Hide the 'Start' option in Simulator menu
		start.setEnabled(false);

		//Enable the 'Export' option in Simulator menu
		export.setEnabled(true);

		int n = 0;
		while (n == 0)
		{
			String input = JOptionPane.showInputDialog(frame, "Enter the name of the column");

			if (input== null){
				break;
			}
			Object[] rowTypePossibilities = {"String Feature", "Double Feature", "Complex Feature"};
			String l = (String) JOptionPane.showInputDialog(frame,"Please select the type of value \n" ,"Feature Type", JOptionPane.PLAIN_MESSAGE,
					null, rowTypePossibilities, "Select one");
			if (l==null){
				break;
			}

			Object[] distancepossibilities = {"Absolute", "Euclidean", "Binary"};
			String d = (String)JOptionPane.showInputDialog(frame,"Please select the Distance Metric \n","Distance Metric", JOptionPane.PLAIN_MESSAGE,
					null, distancepossibilities, "Select one");
			if (d==null){
				break;
			}

			training.insertColumn(input, l,d);

			JOptionPane.showMessageDialog(frame, "Column Sucessfully Created.");

			Object[] yn = {"Yes", "No"};
			n = JOptionPane.showOptionDialog(frame,"Would you like to add another Column ","Column",JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,null,yn,yn[1]);
			if (n!=0 && n!=1){
				break;
			}
			if (n==1)
				initRows();
		}
	}

	/**
	 * Used to initialize rows in the table by prompting the user
	 * 
	 * @param None
	 * @return None
	 */
	public void initRows(){
		int addAnotherRow = 0;
		loop:
			while (addAnotherRow == 0)
			{
				List <Feature> rowData = new ArrayList<>();

				for (int j=0; j<training.getNumOfColumns();j++){
					int currentRow = training.getNumOfRows()+1;
					String feature = training.getColumn(j).getFeature();
					String metric = training.getColumn(j).getMetric();
					DistanceMetric distanceMetric;

					//Absolute distance
					if (metric.equals("Absolute"))
						distanceMetric = new AbsoluteDistance();
					//Euclidean distance
					else if (metric.equals("Euclidean"))
						distanceMetric = new EuclideanDistance();
					//Binary distance
					else
						distanceMetric = new BinaryDistance();
					String input = "";

					if (!feature.equals("Complex Feature")) {
						input = JOptionPane.showInputDialog(frame, "Row #" + currentRow + "\nEnter the data value for " + training.getColumn(j).getName() + " (" + feature + ")");
					}

					if (input==null){
						break loop;
					}

					//String Feature
					if (feature.equals("String Feature"))
					{
						StringFeature stringFeature = new StringFeature (input);
						stringFeature.setDistanceMetric(distanceMetric);
						rowData.add(stringFeature);
					}
					//Double Feature
					else if (feature.equals("Double Feature"))
					{
						DoubleFeature doubleFeature = new DoubleFeature(Double.parseDouble(input));
						doubleFeature.setDistanceMetric(distanceMetric);
						rowData.add(doubleFeature);
					}
					//Complex Feature
					else 
					{
						ComplexFeature complexFeature = new ComplexFeature(distanceMetric);
						input = JOptionPane.showInputDialog(frame, "enter maximum number of features this column would normally contain,for this current observation "
								+ "the value of the feature is null or not available press cancel for that");
						int max = Integer.parseInt(input);
						while (max != 0)
						{
							Object[] rowTypePossibilities = {"String Feature", "Double Feature", "Complex Feature"};
							String childFeature = (String)JOptionPane.showInputDialog(frame,"Please select the type of the value contained in the complex feature.\n" ,"Feature Type", JOptionPane.PLAIN_MESSAGE,
									null, rowTypePossibilities, "Select one");

							if (childFeature==null){
								break;
							}
							input = JOptionPane.showInputDialog(frame, "Row #" + currentRow + "\nEnter the data value for, if you selected complex feature. you will have to reenter value later " + training.getColumn(j).getName() + " (" + feature + ")");
							//String feature in complex
							if(input.equals(""))
							{
								complexFeature.addChildFeature(null);
								max--;
							}
							else
							{
								if (childFeature.equals("String Feature")){
									complexFeature.addChildFeature(new StringFeature(input));
									max--;
								}

								//Double feature in complex
								else if (childFeature.equals("Double Feature")){
									complexFeature.addChildFeature(new DoubleFeature(Double.parseDouble(input)));
									max--;
								}
								//parent complex going into child complex
								else
								{
									ComplexFeature complexChildFeature = new ComplexFeature(distanceMetric);
									boolean addAnotherComplex = true;

									while (addAnotherComplex == true)
									{
										Object[] rowTypePossibilitiesChild = {"String Feature", "Double Feature"};
										String childComplexFeature = (String)JOptionPane.showInputDialog(frame,"Please select the type of complex feature (to be inside another complex feature).\n" ,"Feature Type", JOptionPane.PLAIN_MESSAGE,
												null, rowTypePossibilitiesChild, "Select one");

										input = JOptionPane.showInputDialog(frame, "Row #" + currentRow + "\nEnter the data value for the first element of this complex " + training.getColumn(j).getName() + " (" + feature + ")");

										if (childComplexFeature==null){
											break;
										}
										//String feature in complex
										if (childComplexFeature.equals("String Feature")){
											complexChildFeature.addChildFeature(new StringFeature(input));
										}

										//Double feature in complex
										else if (childComplexFeature.equals("Double Feature")){
											complexChildFeature.addChildFeature(new DoubleFeature(Double.parseDouble(input)));

										}

										int addAnotherChildComplex = JOptionPane.showOptionDialog(null, "Add another feature to this complex feature? (Child complex feature)", "Complex Feature", JOptionPane.OK_CANCEL_OPTION, 
												JOptionPane.INFORMATION_MESSAGE, 
												null, 
												new String[]{"Add", "Done"},
												"default");

										if (addAnotherChildComplex == 1)
										{
											//complexChildFeature.addChildFeature(null);
											addAnotherComplex = false;
										}


									}//end of child while loop
									complexFeature.addChildFeature(complexChildFeature);
									max--;
								}//else of child complex

							}//else of empty


						}
						rowData.add(complexFeature);
					}

				}

				training.insertRow(rowData);
				Object[] yn = {"Yes", "No"};
				addAnotherRow = JOptionPane.showOptionDialog(frame,"Would you like to add another row? ","Row",JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,null,yn,yn[1]);

				if (addAnotherRow!=1 && addAnotherRow!=0){
					break;
				}
			}
		//Not adding any more rows
		if (addAnotherRow == 1) {
			jtable = new JTable(training.getDataTable());
			jtable.setCellSelectionEnabled(true);
			jtable.getModel().addTableModelListener(new TableListener (jtable,training));
			jtable.setVisible(true);
			JScrollPane scroll = new JScrollPane(jtable);
			frame.add(scroll, BorderLayout.CENTER);
			frame.revalidate();

			//Create a test row
			initializeTestFrame();
		}
	}



	public void initializeTestFrame()
	{
		testCaseFrame = new JFrame("Training Example");
		testCaseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		testCaseFrame.setSize(frame.getSize());
		testCaseFrame.setLocation(frame.getX() + frame.getWidth(), frame.getY());
		testCaseFrame.setVisible(true);

		testTable = new Table(training.getColumns());
		initTestCase(this.testCaseFrame,this.testTable);

	}

	/**
	 * Used to initialize test rows using prompts
	 * 
	 * @param None
	 * @return None
	 */
	public void initTestCase(JFrame testCaseFrame, Table testTable) {
		testRow = new Row();

		int addAnotherTest;

		do
		{
			//Clear existing records in the row
			testRow.removeAllValues();

			for (int j=0; j<training.getNumOfColumns();j++){
				String feature = training.getColumn(j).getFeature();
				String metric = training.getColumn(j).getMetric();
				DistanceMetric distanceMetric;

				//Absolute distance
				if (metric.equals("Absolute"))
					distanceMetric = new AbsoluteDistance();
				//Euclidean distance
				else if (metric.equals("Euclidean"))
					distanceMetric = new EuclideanDistance();
				//Binary distance
				else
					distanceMetric = new BinaryDistance();
				String input = "";
				if (!feature.equals("Complex Feature")) {
					input = JOptionPane.showInputDialog(frame, "Test case \nEnter a value for " + training.getColumn(j).getName());
				}
				if (input==null){
					break;
				}

				//String Feature
				if (feature.equals("String Feature"))
				{
					StringFeature stringFeature = new StringFeature (input);
					stringFeature.setDistanceMetric(distanceMetric);
					testRow.addValue(stringFeature);
				}

				//Double Feature
				else if (feature.equals("Double Feature"))
				{
					DoubleFeature doubleFeature = new DoubleFeature(Double.parseDouble(input));
					doubleFeature.setDistanceMetric(distanceMetric);
					testRow.addValue(doubleFeature);

				}

				else {


					//Complex Feature
					ComplexFeature complexFeature = new ComplexFeature(distanceMetric);
					input = JOptionPane.showInputDialog(frame, "enter maximum number of features this column would normally contain,for this current observation "
							+ "the value of the feature is null or not available press cancel for that");
					int max = Integer.parseInt(input);
					while (max != 0)
					{
						Object[] rowTypePossibilities = {"String Feature", "Double Feature", "Complex Feature"};
						String childFeature = (String)JOptionPane.showInputDialog(frame,"Please select the type of the value contained in the complex feature.\n" ,"Feature Type", JOptionPane.PLAIN_MESSAGE,
								null, rowTypePossibilities, "Select one");

						if (childFeature==null){
							break;
						}
						input = JOptionPane.showInputDialog(frame, "Test case \nEnter a value for " + training.getColumn(j).getName());
						//String feature in complex
						if(input.equals(""))
						{
							complexFeature.addChildFeature(null);
							max--;
						}
						else
						{
							if (childFeature.equals("String Feature")){
								complexFeature.addChildFeature(new StringFeature(input));
								max--;
							}

							//Double feature in complex
							else if (childFeature.equals("Double Feature")){
								complexFeature.addChildFeature(new DoubleFeature(Double.parseDouble(input)));
								max--;
							}
							//parent complex going into child complex
							else
							{
								ComplexFeature complexChildFeature = new ComplexFeature(distanceMetric);
								boolean addAnotherComplex = true;

								while (addAnotherComplex == true)
								{
									Object[] rowTypePossibilitiesChild = {"String Feature", "Double Feature"};
									String childComplexFeature = (String)JOptionPane.showInputDialog(frame,"Please select the type of complex feature (to be inside another complex feature).\n" ,"Feature Type", JOptionPane.PLAIN_MESSAGE,
											null, rowTypePossibilitiesChild, "Select one");

									input = JOptionPane.showInputDialog(frame, "Test case \nEnter a value for " + training.getColumn(j).getName());

									if (childComplexFeature==null){
										break;
									}
									//String feature in complex
									if (childComplexFeature.equals("String Feature")){
										complexChildFeature.addChildFeature(new StringFeature(input));
									}

									//Double feature in complex
									else if (childComplexFeature.equals("Double Feature")){
										complexChildFeature.addChildFeature(new DoubleFeature(Double.parseDouble(input)));

									}

									int addAnotherChildComplex = JOptionPane.showOptionDialog(null, "Add another feature to this complex feature? (Child complex feature)", "Complex Feature", JOptionPane.OK_CANCEL_OPTION, 
											JOptionPane.INFORMATION_MESSAGE, 
											null, 
											new String[]{"Add", "Done"},
											"default");

									if (addAnotherChildComplex == 1)
									{
										//complexChildFeature.addChildFeature(null);
										addAnotherComplex = false;
									}


								}//end of child while loop
								complexFeature.addChildFeature(complexChildFeature);
								max--;
							}//else of child complex

						}//else of empty
					}
					testRow.addValue(complexFeature);
				}



			}
			testTable.insertRow(testRow);
			addAnotherTest = JOptionPane.showOptionDialog(null, "Would you like to add another test case?", "Test case", JOptionPane.OK_CANCEL_OPTION, 
					JOptionPane.INFORMATION_MESSAGE, 
					null, 
					new String[]{"Add", "Done"},
					"default");

		} while (addAnotherTest == 0);

		int dialog = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter prediction column index "));
		testTable.setPredictionColumn(dialog);


		jtabletest = new JTable(testTable.getDataTable());
		jtabletest.setVisible(true);
		JScrollPane scroll2 = new JScrollPane(jtabletest);
		testCaseFrame.add(scroll2, BorderLayout.CENTER);
		testCaseFrame.revalidate();

		//Determine the KNN
		initKNN();
	}

	/**
	 * Used to initialize Knn values
	 * 
	 * @param None
	 * @return None
	 */
	public void initKNN()
	{
		knnValue = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter the number of nearest neighbors to search for (k>0):"));
		predict.setEnabled(true);
		JOptionPane.showMessageDialog(frame, "Initialization complete! Select Predict from the Simulation menu to begin.");
	}

	/**
	 * Used to initialize the prediction values based on user's choice using prompts
	 * 
	 * @param None
	 * @return None
	 */
	public void initPrediction()
	{
		Object[] distancepossibilities = {"Regression", "Classification"};
		String predictionType = (String)JOptionPane.showInputDialog(frame,"Please select the type of prediction \n","Prediction", JOptionPane.PLAIN_MESSAGE,
				null, distancepossibilities, "Select one");

		Knn knn = new Knn (knnValue);

		List <Feature> result = new ArrayList<>();
		if (predictionType.equals("Regression"))
		{
			for (int i = 0; i < testTable.getNumOfRows(); i++)
			{
				result.add(knn.predictRegression(training, testTable.getRow(i), testTable.getPredictionIndex()));
			}
		}
		else
		{
			for (int i = 0; i < testTable.getNumOfRows(); i++)
			{
				result.add(knn.predictClassification(training, testTable.getRow(i), testTable.getPredictionIndex()));
			}
		}

		for (int j = 0; j < result.size(); j++)
		{
			testTable.updateValueAt(result.get(j),j,testTable.getPredictionIndex());
		}
		//JOptionPane.showMessageDialog(frame, "The predicted value for column " + training.getColumnName(testTable.getPredictionIndex()) + " is: " + result.toString());

		precisionItem.setEnabled(true);
	}

	public void initPrecision()
	{
		Object[] distancepossibilities = {"Regression", "Classification"};
		String predictionType = (String)JOptionPane.showInputDialog(frame,"Please select the type of prediction \n","Prediction", JOptionPane.PLAIN_MESSAGE,
				null, distancepossibilities, "Select one");

		if(predictionType.equals("Classification"))
		{
			Precision sample = new Precision();
			JOptionPane.showMessageDialog(frame, "Precision rate for sample data given is " + sample.checkPrecisionClassification(training, testTable, testTable.getPredictionIndex()));
		}
		else
		{
			Precision sample = new Precision();
			JOptionPane.showMessageDialog(frame, "Precision rate for sample data given is " + sample.checkPrecisionRegression(training, testTable, testTable.getPredictionIndex()));
		}

	}

	/**
	 * Used to start the simulator
	 * 
	 * @param None
	 * @return None
	 */
	public void start() {
		initColumns();
	}

	/**
	 * Used to update the table after a value has been added or edited
	 * 
	 * @param model the TableModel of the table
	 * @return None
	 */
	public void updateJTable(DefaultTableModel model)
	{
		this.jtable.setModel(model);
	}


	public void exporttoFile() {
		System.out.println("EXPORT CALLED");
		String traininput = JOptionPane.showInputDialog(frame, "enter name of file to store trainig table without the extension");
		traininput += ".xsd";
		String testInput = JOptionPane.showInputDialog(frame, "enter name of file to store test table without the extension");
		testInput +=".xsd";
		try {
			Table.exportTable(traininput, training);
			Table.exportTable(testInput, testTable);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public void importFile() {
		String traininput = JOptionPane.showInputDialog(frame, "enter name of file to store trainig table without the extension");
		traininput += ".xsd";
		String testInput = JOptionPane.showInputDialog(frame, "enter name of file to store test table without the extension");
		testInput +=".xsd";

		training = Table.importTable(traininput);
		testTable = Table.importTable(testInput);

		jtable = new JTable(training.getDataTable());
		jtable.setCellSelectionEnabled(true);
		jtable.getModel().addTableModelListener(new TableListener (jtable,training));
		jtable.setVisible(true);
		JScrollPane scroll = new JScrollPane(jtable);
		frame.add(scroll, BorderLayout.CENTER);
		frame.revalidate();

		testCaseFrame = new JFrame("Training Example");
		testCaseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		testCaseFrame.setSize(frame.getSize());
		testCaseFrame.setLocation(frame.getX() + frame.getWidth(), frame.getY());
		testCaseFrame.setVisible(true);

		jtabletest = new JTable(testTable.getDataTable());
		jtabletest.setVisible(true);
		JScrollPane scroll2 = new JScrollPane(jtabletest);
		testCaseFrame.add(scroll2, BorderLayout.CENTER);
		testCaseFrame.revalidate();

		initTestCase(this.testCaseFrame,this.testTable);	

		this.initKNN();
	}

	/**
	 * Main program to initialize GUI
	 * 
	 * @param args
	 * @return None
	 */
	public static void main(String args[]){
		new GUIView();
	}
}