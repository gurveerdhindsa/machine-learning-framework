package testing;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AbsoluteDistanceTest.class, BinaryDistanceTest.class, ComplexFeatureTest.class, DoubleFeatureTest.class,
		EuclideanDistanceTest.class, StringFeatureTest.class, ImportTest.class })
public class AllTests {

}
