package testing;

import distance.AbsoluteDistance;
import distance.BinaryDistance;
import distance.DistanceMetric;
import distance.EuclideanDistance;
import feature.ComplexFeature;
import feature.DoubleFeature;
import feature.Feature;
import feature.StringFeature;
import table.Knn;
import table.Row;
import table.Table;

public class TestClass {

	public static void main(String[]args)
	{
		DistanceMetric da = new AbsoluteDistance();
		DistanceMetric dc = new BinaryDistance();
		DoubleFeature a = new DoubleFeature(2.0,da);
		Feature b = new DoubleFeature(0.6,da);
		Feature c = new DoubleFeature(7.0,da);
		Feature d = new DoubleFeature(8.0,da);
		Feature e = new DoubleFeature(9.0,da);
		Feature f = new DoubleFeature(0.2,da);
		Feature g = new DoubleFeature(4.5,da);
		Feature h = new DoubleFeature(3.5,da);
		Feature i = new DoubleFeature(2.1,da);
		
		StringFeature as = new StringFeature("Ike",dc);
		StringFeature bs = new StringFeature("Hercules",dc);
		StringFeature cs = new StringFeature("Patton",dc);
		StringFeature ds = new StringFeature("Bradley",dc);
		StringFeature es = new StringFeature("Monty",dc);
		StringFeature fs = new StringFeature("Easy",dc);
		StringFeature gs = new StringFeature("Rommel",dc);
		StringFeature hs = new StringFeature("Lieke",dc);
		StringFeature is = new StringFeature("Auchinleck",dc);
		
		ComplexFeature ca = new ComplexFeature();
		ComplexFeature cb = new ComplexFeature();
		ComplexFeature cc = new ComplexFeature();

		DistanceMetric db = new EuclideanDistance();
		
		ca.addChildFeature(a);
		ca.addChildFeature(as);
		ca.addChildFeature(b);
		ca.setDistanceMetric(db);
		
		Row firstrow = new Row();
		firstrow.addValue(ca);
		firstrow.addValue(bs);
		firstrow.addValue(g);
		
		cb.addChildFeature(c);
		cb.addChildFeature(cs);
		cb.addChildFeature(d);
		cb.setDistanceMetric(db);
		
		Row secondrow = new Row();
		secondrow.addValue(cb);
		secondrow.addValue(ds);
		secondrow.addValue(h);
		
		Row thirdrow = new Row();
		thirdrow.addValue(ca);
		thirdrow.addValue(ds);
		thirdrow.addValue(e);
		
		cc.addChildFeature(e);
		cc.addChildFeature(es);
		cc.addChildFeature(f);
		cc.setDistanceMetric(db);
		
		Row testRow = new Row();
		testRow.addValue(cc);
		testRow.addValue(null);
		testRow.addValue(i);
		
		Table firstTable = new Table();
		firstTable.insertRow(firstrow);
		firstTable.insertRow(secondrow);
		firstTable.insertRow(thirdrow);
		
		ca.getDistance(cb);
		
		Knn one = new Knn(3);
		
		one.predictClassification(firstTable, testRow, 1);
	}
}
