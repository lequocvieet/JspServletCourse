package com.laptrinhjavaweb.sort;

public class Sorter {

	private String thisSortName;
	private String thisSortBy;
	
	public Sorter(String sortName,String sortBy) {
		this.thisSortName=sortName;
		this.thisSortBy=sortBy;
	}

	public String getSortBy() {
		return thisSortBy;
	}

	public void setSortBy(String thisSortBy) {
		this.thisSortBy = thisSortBy;
	}

	public String getSortName() {
		return thisSortName;
	}

	public void setSortName(String thisSortName) {
		this.thisSortName = thisSortName;
	}
	
	
}
