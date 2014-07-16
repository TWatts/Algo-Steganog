package edu.neumont.sql;

public interface DataSource {
	// implementation should have a constructor that takes a file location, e.g.
	// public MyDataSource(String fileLocation)
	
	/**
	 * Perform a standard SQL query on the dataset loaded into this
	 * data source.
	 * 
	 * @param query
	 * @return - A 2D array of results.  The first row of results should be
	 * the names of the columns.
	 * 
	 */
	Object[][] executeQuery(String query);
}
