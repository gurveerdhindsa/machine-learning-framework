package testing;
import static org.junit.Assert.*;


import org.junit.Test;

import distance.AbsoluteDistance;
import distance.DistanceMetric;
import feature.DoubleFeature;
import feature.Feature;

public class DoubleFeatureTest {
	protected Feature value1, value2, value3;
	protected DistanceMetric metric;
	
	protected void setUp()
	{
		this.metric = new AbsoluteDistance();
		this.value1 = new DoubleFeature(1.0, this.metric);
		this.value2 = new DoubleFeature(2.0, this.metric);
		this.value3 = new DoubleFeature(3.0, this.metric);
	}
	
	@Test
	public void testgetMetric() {
		setUp();
		assertEquals(this.metric,this.value1.getDistanceMetric());
	}
	
	@Test
	public void testgetValue()
	{
		setUp();
		assertTrue(((DoubleFeature) this.value3).getValue() == 3.0);
	}
	
	@Test
	public void testgetDistance()
	{
		setUp();
		assertTrue(this.value1.getDistance(value2) == 1);
	}
}