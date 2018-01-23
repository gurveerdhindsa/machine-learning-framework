package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import distance.AbsoluteDistance;
import distance.BinaryDistance;
import distance.DistanceMetric;
import distance.EuclideanDistance;
import feature.DoubleFeature;
import feature.Feature;
import feature.StringFeature;

public class EuclideanDistanceTest {
	private Feature value1, value2, value3,value4,value5,value6;
	private DistanceMetric metric;
	
	@Before
	public void setUp() throws Exception {
		this.value1 = new DoubleFeature(4.0, this.metric);
		this.value2 = new DoubleFeature(4.0, this.metric);
		this.value3 = new DoubleFeature(3.0, this.metric);
		this.value4 = new DoubleFeature(4.0, this.metric);
		this.metric = new EuclideanDistance();
		this.value5 = new StringFeature("ABC",this.metric);
		this.value6 = new StringFeature("ABC",this.metric);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testcalculateDoubleFeature() {
		assertEquals(this.metric.calculate(value1, value2), 0);
		assertEquals(this.metric.calculate(value3, value4), 1);
	}
		
	@Test
	public void testcalculateStringFeature(){
		assertEquals(this.metric.calculate(value5, value6),0);
	}
}