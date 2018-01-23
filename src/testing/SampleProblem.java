package testing;
import java.util.ArrayList;
import java.util.List;

import distance.AbsoluteDistance;
import distance.BinaryDistance;
import distance.DistanceMetric;
import distance.EuclideanDistance;
import feature.ComplexFeature;
import feature.DoubleFeature;
import feature.StringFeature;
import feature.Feature;
import table.Precision;
import table.Row;
import table.Table;

public class SampleProblem {
	static DistanceMetric da = new AbsoluteDistance();
	static DistanceMetric dc = new BinaryDistance();
	static StringFeature kick = new StringFeature("Kick",dc);
	static StringFeature dash = new StringFeature("Dash",dc);
	static StringFeature turn = new StringFeature("Turn",dc);
	static Table trainTable = new Table();
	static Table testTable = new Table();
	static DistanceMetric distance = new EuclideanDistance();
	
	public static double precisionValue()
	{
		setUpSampleTrain();
		setUpSampleTest();
		List<Feature> knownValues = new ArrayList<>();
		knownValues.add(kick);//1
		knownValues.add(kick);//2
		knownValues.add(kick);//3
		knownValues.add(turn);//4
		knownValues.add(turn);//5
		knownValues.add(turn);//6
		knownValues.add(turn);//7
		Precision a = new Precision();
		double f = a.checkPrecisionClassification(trainTable, testTable, 3, knownValues);
		return f;
	}
		
	public static void setUpSampleTest()
	{
		testTable.insertColumn("Ball","","");
		testTable.insertColumn("Goal","","");
		testTable.insertColumn("Flags","","");
		testTable.insertColumn("Action", "","");
		
		Row testRow1 = new Row();
		ComplexFeature testRow1_ball = new ComplexFeature(distance);
		testRow1_ball.addChildFeature(new DoubleFeature(2,da));
		testRow1_ball.addChildFeature(new DoubleFeature(22,da));
		
		testRow1.addValue(testRow1_ball);
		
		ComplexFeature testRow1_goal = new ComplexFeature(distance);
		testRow1_goal.addChildFeature(new DoubleFeature(63.8,da));
		testRow1_goal.addChildFeature(new DoubleFeature(-2,da));
		
		testRow1.addValue(testRow1_goal);
		
		testRow1.addValue(null);
		//action
		testRow1.addValue(null);
		//table
		testTable.insertRow(testRow1);
		
		Row testRow2 = new Row();
		ComplexFeature testRow2_ball = new ComplexFeature(distance);
		testRow2_ball.addChildFeature(new DoubleFeature(2,da));
		testRow2_ball.addChildFeature(new DoubleFeature(22,da));
		
		testRow2.addValue(testRow2_ball);
		
		ComplexFeature testRow2_goal = new ComplexFeature(distance);
		testRow2_goal.addChildFeature(new DoubleFeature(63.8,da));
		testRow2_goal.addChildFeature(new DoubleFeature(-2,da));
		
		testRow2.addValue(testRow2_goal);
		//flags
		testRow2.addValue(null);
		//action
		testRow2.addValue(null);
		
		testTable.insertRow(testRow2);
		
		Row testRow3 = new Row();
		
		ComplexFeature testRow3_ball = new ComplexFeature(distance);
		testRow3_ball.addChildFeature(new DoubleFeature(1.9,da));
		testRow3_ball.addChildFeature(new DoubleFeature(-167,da));
		
		testRow3.addValue(testRow3_ball);
		
		ComplexFeature testRow3_goal = new ComplexFeature(distance);
		testRow3_goal.addChildFeature(new DoubleFeature(63.8,da));
		testRow3_goal.addChildFeature(new DoubleFeature(31,da));
		
		testRow3.addValue(testRow3_goal);
		
		ComplexFeature testRow3_flags = new ComplexFeature(distance);
		
		ComplexFeature testRow3_FCT  = new ComplexFeature(distance);
		testRow3_FCT.addChildFeature(new DoubleFeature(39.1,da));
		testRow3_FCT.addChildFeature(new DoubleFeature(-41,da));
		
		testRow3_flags.addChildFeature(testRow3_FCT);
		testRow3_flags.addChildFeature(null);
		
		testRow3.addValue(testRow3_flags);
		//action
		testRow3.addValue(null);
		
		testTable.insertRow(testRow3);
		
		Row testRow4 = new Row();
		ComplexFeature testRow4_ball = new ComplexFeature(distance);
		testRow4_ball.addChildFeature(new DoubleFeature(14.5,da));
		testRow4_ball.addChildFeature(new DoubleFeature(0,da));
		
		testRow4.addValue(testRow4_ball);
		
		ComplexFeature testRow4_goal = new ComplexFeature(distance);
		testRow4_goal.addChildFeature(new DoubleFeature(32.8,da));
		testRow4_goal.addChildFeature(new DoubleFeature(5,da));
		
		testRow4.addValue(testRow4_goal);
		
		testRow4.addValue(null);
		//action
		testRow4.addValue(null);
		
		testTable.insertRow(testRow4);
		
		Row testRow5 = new Row();
		//ball
		testRow5.addValue(null);
		//goal
		ComplexFeature testRow5_goal = new ComplexFeature(distance);
		testRow5_goal.addChildFeature(new DoubleFeature(55.6,da));
		testRow5_goal.addChildFeature(new DoubleFeature(2,da));
		
		testRow5.addValue(testRow5_goal);
		//flags
		testRow5.addValue(null);
		//action
		testRow5.addValue(null);
		
		testTable.insertRow(testRow5);
		
		
		
		Row testRow6 = new Row();
		
		ComplexFeature testRow6_ball = new ComplexFeature(distance);
		testRow6_ball.addChildFeature(new DoubleFeature(41.4,da));
		testRow6_ball.addChildFeature(new DoubleFeature(2,da));
		
		testRow6.addValue(testRow6_ball);
		
		ComplexFeature testRow6_goal = new ComplexFeature(distance);
		testRow6_goal.addChildFeature(new DoubleFeature(95.6,da));
		testRow6_goal.addChildFeature(new DoubleFeature(1,da));
		
		testRow6.addValue(testRow6_goal);
		
		ComplexFeature testRow6_flags = new ComplexFeature(distance);
		
		ComplexFeature testRow6_FCT = new ComplexFeature(distance);
		testRow6_FCT.addChildFeature(new DoubleFeature(55.1,da));
		testRow6_FCT.addChildFeature(new DoubleFeature(-38,da));
		testRow6_flags.addChildFeature(testRow6_FCT);
		
		ComplexFeature testRow6_FCB = new ComplexFeature(distance);
		testRow6_FCB.addChildFeature(new DoubleFeature(55.1,da));
		testRow6_FCB.addChildFeature(new DoubleFeature(40,da));
		testRow6_flags.addChildFeature(testRow6_FCB);
		
		testRow6.addValue(testRow6_flags);
		testRow6.addValue(null);
		
		//table insert 
		testTable.insertRow(testRow6);
		
		Row testRow7 = new Row();
		ComplexFeature testRow7_ball = new ComplexFeature(distance);
		testRow7_ball.addChildFeature(new DoubleFeature(19.2,da));
		testRow7_ball.addChildFeature(new DoubleFeature(11,da));
		
		testRow7.addValue(testRow7_ball);
		
		ComplexFeature testRow7_goal = new ComplexFeature(distance);
		testRow7_goal.addChildFeature(new DoubleFeature(85.8,da));
		testRow7_goal.addChildFeature(new DoubleFeature(12,da));
		
		testRow7.addValue(testRow7_goal);
		
		ComplexFeature testRow7_flags = new ComplexFeature(distance);
		
		ComplexFeature testRow7_FCT = new ComplexFeature(distance);
		testRow7_FCT.addChildFeature(new DoubleFeature(47.5,da));
		testRow7_FCT.addChildFeature(new DoubleFeature(-34,da));
		
		testRow7_flags.addChildFeature(testRow7_FCT);
		testRow7_flags.addChildFeature(null);
		
		testRow7.addValue(testRow7_flags);
		
		//action
		testRow7.addValue(null);
		
		testTable.insertRow(testRow7);
		
	
	}
	public static void setUpSampleTrain()
	{
		trainTable.insertColumn("Ball","","");
		trainTable.insertColumn("Goal","","");
		trainTable.insertColumn("Flags","","");
		trainTable.insertColumn("Action", "","");
		
		Row firstRow = new Row();
		
		ComplexFeature firstrow_ball  = new ComplexFeature(distance);
		firstrow_ball.addChildFeature(new DoubleFeature(1.9,da));
		firstrow_ball.addChildFeature(new DoubleFeature(-167,da));
		
		firstRow.addValue(firstrow_ball);
		
		ComplexFeature firstRow_goal = new ComplexFeature(distance);
		firstRow_goal.addChildFeature(new DoubleFeature(63.8,da));
		firstRow_goal.addChildFeature(new DoubleFeature(31,da));
		
		firstRow.addValue(firstRow_goal);
		
		ComplexFeature firstRow_flags = new ComplexFeature(distance);
		ComplexFeature firstRow_FCT = new ComplexFeature(distance);
		firstRow_FCT.addChildFeature(new DoubleFeature(39.1,da));
		firstRow_FCT.addChildFeature(new DoubleFeature(-41,da));
		firstRow_flags.addChildFeature(firstRow_FCT);
		
		// FCB
		firstRow_flags.addChildFeature(null);
		firstRow.addValue(firstRow_flags);
		
		//action
		firstRow.addValue(kick);
		trainTable.insertRow(firstRow);
		
		Row secondRow = new Row();
		
		ComplexFeature secondRow_ball= new ComplexFeature(distance);
		secondRow_ball.addChildFeature(new DoubleFeature(1.9,da));
		secondRow_ball.addChildFeature(new DoubleFeature(50,da));
		
		secondRow.addValue(secondRow_ball);
		
		ComplexFeature secondRow_goal = new ComplexFeature(distance);
		secondRow_goal.addChildFeature(new DoubleFeature(63.8,da));
		secondRow_goal.addChildFeature(new DoubleFeature(31,da));
		
		secondRow.addValue(secondRow_goal);
		
		ComplexFeature secondRow_flags = new ComplexFeature(distance);
		ComplexFeature secondRow_FCT = new ComplexFeature(distance);
		secondRow_FCT.addChildFeature(new DoubleFeature(39.1,da));
		secondRow_FCT.addChildFeature(new DoubleFeature(-41,da));
		secondRow_flags.addChildFeature(secondRow_FCT);
		
		//FCB
		secondRow_flags.addChildFeature(null);
		secondRow.addValue(secondRow_flags);
		
		//action
		secondRow.addValue(kick);
		
		trainTable.insertRow(secondRow);
		
		Row thirdRow = new Row();
		
		ComplexFeature thirdRow_ball = new ComplexFeature(distance);
		thirdRow_ball.addChildFeature(new DoubleFeature(1.8,da));
		thirdRow_ball.addChildFeature(new DoubleFeature(2,da));
		
		thirdRow.addValue(thirdRow_ball);
		
		ComplexFeature thirdRow_goal = new ComplexFeature(distance);
		thirdRow_goal.addChildFeature(new DoubleFeature(61.9,da));
		thirdRow_goal.addChildFeature(new DoubleFeature(-4,da));
		
		thirdRow.addValue(thirdRow_goal);
		//flags
		thirdRow.addValue(null);
		//action
		thirdRow.addValue(kick);
		
		trainTable.insertRow(thirdRow);
		
		Row fourthRow = new Row();
		
		ComplexFeature fourthRow_ball = new ComplexFeature(distance);
		fourthRow_ball.addChildFeature(new DoubleFeature(1.8,da));
		fourthRow_ball.addChildFeature(new DoubleFeature(-85,da));
		
		fourthRow.addValue(fourthRow_ball);
		
		ComplexFeature fourthRow_goal = new ComplexFeature(distance);
		fourthRow_goal.addChildFeature(new DoubleFeature(53.5,da));
		fourthRow_goal.addChildFeature(new DoubleFeature(17,da));
		
		fourthRow.addValue(fourthRow_goal);
		
		fourthRow.addValue(null);
		
		fourthRow.addValue(kick);
		
		trainTable.insertRow(fourthRow);
		
		
		Row fifthRow = new Row();
		
		ComplexFeature fifthRow_ball = new ComplexFeature(distance);
		fifthRow_ball.addChildFeature(new DoubleFeature(19.2,da));
		fifthRow_ball.addChildFeature(new DoubleFeature(1,da));
		
		fifthRow.addValue(fifthRow_ball);
		
		ComplexFeature fifthRow_goal = new ComplexFeature(distance);
		fifthRow_goal.addChildFeature(new DoubleFeature(24.6,da));
		fifthRow_goal.addChildFeature(new DoubleFeature(-17,da));
		
		fifthRow.addValue(fifthRow_goal);
		//flags
		fifthRow.addValue(null);
		//action
		fifthRow.addValue(dash);
		
		trainTable.insertRow(fifthRow);
		
		//row six
		Row sixthRow = new Row();
		
		ComplexFeature sixthRow_ball = new ComplexFeature(distance);
		sixthRow_ball.addChildFeature(new DoubleFeature(15.9,da));
		sixthRow_ball.addChildFeature(new DoubleFeature(1,da));
		
		sixthRow.addValue(sixthRow_ball);
		
		ComplexFeature sixthRow_goal = new ComplexFeature(distance);
		sixthRow_goal.addChildFeature(new DoubleFeature(22.3,da));
		sixthRow_goal.addChildFeature(new DoubleFeature(-18,da));
		
		sixthRow.addValue(sixthRow_goal);
		sixthRow.addValue(null);
		sixthRow.addValue(dash);
		
		trainTable.insertRow(sixthRow);
		
		Row sevenRow = new Row();
		ComplexFeature sevenRow_ball = new ComplexFeature(distance);
		sevenRow_ball.addChildFeature(new DoubleFeature(14.5,da));
		sevenRow_ball.addChildFeature(new DoubleFeature(1,da));
		
		sevenRow.addValue(sevenRow_ball);
		
		ComplexFeature sevenRow_gaol = new ComplexFeature(distance);
		sevenRow_gaol.addChildFeature(new DoubleFeature(20.7,da));
		sevenRow_gaol.addChildFeature(new DoubleFeature(-20,da));
		
		sevenRow.addValue(sevenRow_gaol);
		
		sevenRow.addValue(null);
		sevenRow.addValue(dash);
		
		trainTable.insertRow(sevenRow);
		
		Row eightRow = new Row();
		
		ComplexFeature eightRow_ball = new ComplexFeature(distance);
		eightRow_ball.addChildFeature(new DoubleFeature(11,da));
		eightRow_ball.addChildFeature(new DoubleFeature(1,da));
		
		eightRow.addValue(eightRow_ball);
		eightRow.addValue(null);
		
		ComplexFeature eightRow_flags = new ComplexFeature(distance);
		ComplexFeature eightRow_FCT =new ComplexFeature(distance);
		eightRow_FCT.addChildFeature(new DoubleFeature(44.8,da));
		eightRow_FCT.addChildFeature(new DoubleFeature(-5,da));
		
		eightRow_flags.addChildFeature(eightRow_FCT);
		eightRow_flags.addChildFeature(null);
		
		eightRow.addValue(eightRow_flags);
		eightRow.addValue(dash);
		
		trainTable.insertRow(eightRow);
		
		Row ninthRow = new Row();
		ComplexFeature ninthRow_ball = new ComplexFeature(distance);
		ninthRow_ball.addChildFeature(new DoubleFeature(10,da));
		ninthRow_ball.addChildFeature(new DoubleFeature(1,da));
		
		ninthRow.addValue(ninthRow_ball);
		
		ComplexFeature ninthRow_goal = new ComplexFeature(distance);
		ninthRow_goal.addChildFeature(new DoubleFeature(61.3,da));
		ninthRow_goal.addChildFeature(new DoubleFeature(-31,da));
		
		ninthRow.addValue(ninthRow_goal);
		
		ComplexFeature ninthRow_flags = new ComplexFeature(distance);
		
		ninthRow_flags.addChildFeature(null);
		
		ComplexFeature ninthRow_FCB = new ComplexFeature(distance);
		ninthRow_FCB.addChildFeature(new DoubleFeature(41.4,da));
		ninthRow_FCB.addChildFeature(new DoubleFeature(43,da));
		
		ninthRow_flags.addChildFeature(ninthRow_FCB);
		ninthRow.addValue(ninthRow_flags);
		ninthRow.addValue(dash);
		
		trainTable.insertRow(ninthRow);
		
		Row tenRow = new Row();
		
		ComplexFeature tenRow_ball = new ComplexFeature(distance);
		tenRow_ball.addChildFeature(new DoubleFeature(45.7,da));
		tenRow_ball.addChildFeature(new DoubleFeature(1,da));
		
		tenRow.addValue(tenRow_ball);
		
		ComplexFeature tenRow_goal = new ComplexFeature(distance);
		tenRow_goal.addChildFeature(new DoubleFeature(96.6,da));
		tenRow_goal.addChildFeature(new DoubleFeature(2,da));
		
		tenRow.addValue(tenRow_goal);
		
		ComplexFeature tenRow_flags = new ComplexFeature(distance);
		ComplexFeature tenRow_FCT = new ComplexFeature(distance);
		
		tenRow_FCT.addChildFeature(new DoubleFeature(55.6,da));
		tenRow_FCT.addChildFeature(new DoubleFeature(-37,da));
		
		tenRow_flags.addChildFeature(tenRow_FCT);
		
		ComplexFeature tenRow_FCB = new ComplexFeature(distance);
		tenRow_FCB.addChildFeature(new DoubleFeature(55.6,da));
		tenRow_FCB.addChildFeature(new DoubleFeature(40));
		
		tenRow_flags.addChildFeature(tenRow_FCB);
		tenRow.addValue(tenRow_flags);
		
		tenRow.addValue(dash);
		trainTable.insertRow(tenRow);
		
		Row elevenRow = new Row();
		
		ComplexFeature elevenRow_ball = new ComplexFeature(distance);
		elevenRow_ball.addChildFeature(new DoubleFeature(50.4,da));
		elevenRow_ball.addChildFeature(new DoubleFeature(-1,da));
		
		elevenRow.addValue(elevenRow_ball);
		
		ComplexFeature elevenRow_goal = new ComplexFeature(distance);
		elevenRow_goal.addChildFeature(new DoubleFeature(101.5,da));
		elevenRow_goal.addChildFeature(new DoubleFeature(14,da));
		
		elevenRow.addValue(elevenRow_goal);
		
		ComplexFeature elevenRow_flags = new ComplexFeature(distance);
		
		ComplexFeature elevenRow_FCT = new ComplexFeature(distance);
		elevenRow_FCT.addChildFeature(new DoubleFeature(75.4,da));
		elevenRow_FCT.addChildFeature(new DoubleFeature(-24,da));
		elevenRow_flags.addChildFeature(elevenRow_FCT);
		
		ComplexFeature elevenRow_FCB = new ComplexFeature(distance);
		elevenRow_FCB.addChildFeature(new DoubleFeature(46.2,da));
		elevenRow_FCB.addChildFeature(new DoubleFeature(40,da));
		elevenRow_flags.addChildFeature(elevenRow_FCB);
		
		elevenRow.addValue(elevenRow_flags);
		elevenRow.addValue(turn);
		
		trainTable.insertRow(elevenRow);
		
		
		Row twelveRow = new Row();
		
		ComplexFeature twelveRow_ball = new ComplexFeature(distance);
		twelveRow_ball.addChildFeature(new DoubleFeature(41.4,da));
		twelveRow_ball.addChildFeature(new DoubleFeature(0,da));
		
		twelveRow.addValue(twelveRow_ball);
		
		ComplexFeature twelveRow_goal = new ComplexFeature(distance);
		twelveRow_goal.addChildFeature(new DoubleFeature(90.1,da));
		twelveRow_goal.addChildFeature(new DoubleFeature(18,da));

		twelveRow.addValue(twelveRow_goal);
		
		ComplexFeature twelveRow_flags = new ComplexFeature(distance);
		
		ComplexFeature twelveRow_FCT = new ComplexFeature(distance);
		twelveRow_FCT.addChildFeature(new DoubleFeature(65.1,da));
		twelveRow_FCT.addChildFeature(new DoubleFeature(-27,da));
		
		twelveRow_flags.addChildFeature(twelveRow_FCT);
		twelveRow_flags.addChildFeature(null);
		
		twelveRow.addValue(twelveRow_flags);
		twelveRow.addValue(turn);
		
		trainTable.insertRow(twelveRow);
		
		Row thirteenRow = new Row();
		
		ComplexFeature thirteenRow_ball = new ComplexFeature(distance);
		thirteenRow_ball.addChildFeature(new DoubleFeature(14.5,da));
		thirteenRow_ball.addChildFeature(new DoubleFeature(15,da));
		
		thirteenRow.addValue(thirteenRow_ball);
		
		ComplexFeature thirteenRow_goal = new ComplexFeature(distance);
		thirteenRow_goal.addChildFeature(new DoubleFeature(60.1,da));
		thirteenRow_goal.addChildFeature(new DoubleFeature(27,da));
		
		thirteenRow.addValue(thirteenRow_goal);
		thirteenRow.addValue(null);
		thirteenRow.addValue(turn);
		
		trainTable.insertRow(thirteenRow);
		
		Row fourteenRow = new Row();
		ComplexFeature fourteenRow_ball = new ComplexFeature(distance);
		fourteenRow_ball.addChildFeature(new DoubleFeature(41.4,da));
		fourteenRow_ball.addChildFeature(new DoubleFeature(3,da));
		
		fourteenRow.addValue(fourteenRow_ball);
		
		ComplexFeature fourteenRow_goal = new ComplexFeature(distance);
		fourteenRow_goal.addChildFeature(new DoubleFeature(94.7,da));
		fourteenRow_goal.addChildFeature(new DoubleFeature(4,da));
		
		fourteenRow.addValue(fourteenRow_goal);
		
		ComplexFeature fourteenRow_flags = new ComplexFeature(distance);
		ComplexFeature fourteenRow_FCT = new ComplexFeature(distance);
		fourteenRow_FCT.addChildFeature(new DoubleFeature(55.1,da));
		fourteenRow_FCT.addChildFeature(new DoubleFeature(-36,da));
		
		fourteenRow_flags.addChildFeature(fourteenRow_FCT);
		
		ComplexFeature fourteenRow_FCB = new ComplexFeature(distance);
		fourteenRow_FCB.addChildFeature(new DoubleFeature(53.5,da));
		fourteenRow_FCB.addChildFeature(new DoubleFeature(43,da));
		
		fourteenRow_flags.addChildFeature(fourteenRow_FCB);
		
		fourteenRow.addValue(fourteenRow_flags);
		fourteenRow.addValue(turn);
		
		trainTable.insertRow(fourteenRow);
		
		Row fiftheenRow = new Row();
		ComplexFeature fiftheenRow_ball = new ComplexFeature(distance);
		fiftheenRow_ball.addChildFeature(new DoubleFeature(23.2,da));
		fiftheenRow_ball.addChildFeature(new DoubleFeature(0,da));
		
		fiftheenRow.addValue(fiftheenRow_ball);
		
		ComplexFeature fiftheenRow_goal = new ComplexFeature(distance);
		fiftheenRow_goal.addChildFeature(new DoubleFeature(76.9,da));
		fiftheenRow_goal.addChildFeature(new DoubleFeature(2,da));
		
		fiftheenRow.addValue(fiftheenRow_goal);
		
		fiftheenRow.addValue(null);
		fiftheenRow.addValue(turn);
		
		trainTable.insertRow(fiftheenRow);
		
		Row sixteenRow =new Row();
		ComplexFeature sixteenRow_ball = new ComplexFeature(distance);
		sixteenRow_ball.addChildFeature(new DoubleFeature(12,da));
		sixteenRow_ball.addChildFeature(new DoubleFeature(24,da));
		
		sixteenRow.addValue(sixteenRow_ball);
		sixteenRow.addValue(null);
		ComplexFeature sixteenRow_flags = new ComplexFeature(distance);
		sixteenRow_flags.addChildFeature(null);
		ComplexFeature sixteenRow_FCB = new ComplexFeature(distance);
		sixteenRow_FCB.addChildFeature(new DoubleFeature(42.7,da));
		sixteenRow_FCB.addChildFeature(new DoubleFeature(-40,da));
		sixteenRow_flags.addChildFeature(sixteenRow_FCB);
		
		sixteenRow.addValue(sixteenRow_flags);
		sixteenRow.addValue(turn);
		
		trainTable.insertRow(sixteenRow);
	}
}