package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import table.Table;

public class ImportTest {
	private Table imported;
	private Table export;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		imported = new Table();
		imported.insertColumn("Name", "String", "Binary");
		Table.exportTable("nFile.ser", imported);
		export = Table.importTable("nFile.ser");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(imported.getColumn(0).getName().equals(export.getColumn(0).getName()));
	}
}