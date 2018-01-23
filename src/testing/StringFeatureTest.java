package testing;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import distance.BinaryDistance;
import distance.DistanceMetric;
import feature.StringFeature;

public class StringFeatureTest {
	private StringFeature feature1, feature2, feature3;
	private DistanceMetric test;
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.feature1 = new StringFeature("MyName");
		this.feature2 = new StringFeature("MyName");
		this.feature3 = new StringFeature("rohan");
		this.test = new BinaryDistance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDistanceMetric() {
		this.feature1.setDistanceMetric(test);
		assertEquals(this.feature1.getDistanceMetric(), this.test);
	}

	@Test
	public void testGetValue() {
		assertTrue(this.feature1.getValue().equals("MyName"));
	}

	@Test
	public void testSetValue() {
		this.feature3.setValue("string");
		assertTrue(this.feature3.getValue().equals("string"));
	}

	@Test
	public void testCompare() {
		assertEquals(this.feature1.compare(feature2),0);
	}
}