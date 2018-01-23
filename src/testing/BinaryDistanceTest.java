package testing;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import distance.BinaryDistance;
import distance.DistanceMetric;
import feature.Feature;
import feature.StringFeature;

public class BinaryDistanceTest {
	private DistanceMetric metric;
	private Feature feature1, feature2;
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.feature1 = new StringFeature("name");
		this.feature2 = new StringFeature("none");
		this.metric = new BinaryDistance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCalculate() {
		assertEquals(this.metric.calculate(feature1, feature2),1);
		
	}
}