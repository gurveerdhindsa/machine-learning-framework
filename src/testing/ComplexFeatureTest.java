package testing;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import distance.DistanceMetric;
import distance.EuclideanDistance;
import feature.ComplexFeature;
import feature.DoubleFeature;
import feature.Feature;

public class ComplexFeatureTest {
	private ComplexFeature feature1;
	private Feature childFeature1;
	private DistanceMetric metric;
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.metric = new EuclideanDistance();
		this.feature1 = new ComplexFeature();
		this.childFeature1 = new DoubleFeature(3.0);
		this.feature1.setDistanceMetric(metric);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSizeofChildFeatures() {
		assertEquals(this.feature1.getSizeofChildFeatures(),0);
	}
	@Test
	public void testAddChildFeature() {
		this.feature1.addChildFeature(childFeature1);
		assertEquals(this.feature1.getSizeofChildFeatures(),1);
	}
}
