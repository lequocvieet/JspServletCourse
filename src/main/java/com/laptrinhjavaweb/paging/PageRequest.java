package com.laptrinhjavaweb.paging;

import com.laptrinhjavaweb.sort.Sorter;

public class PageRequest implements Pageble {
    
	private Integer thisPage;
	private Integer thisMaxItemPerPage;
	private Sorter thisSorter;
	public  PageRequest(Integer page, Integer maxItemPerPage,Sorter sorter) {
		this.thisPage=page;
		this.thisMaxItemPerPage=maxItemPerPage;
		this.thisSorter=sorter;
	}
	
	
	@Override
	public Integer getPage() {
		
		return this.thisPage;
	}

	@Override
	public Integer getOffset() {
	    
		if(this.thisPage !=null  &&   this.thisMaxItemPerPage !=null ) {
		return (thisPage -1) *thisMaxItemPerPage;
		}
			return null;
		
	}

	@Override
	public Integer getLimit() {
	
		return this.thisMaxItemPerPage;
	}


	@Override
	public Sorter getSorter() {
		if(this.thisSorter != null) {
			return this.thisSorter;
		}
		return null;
	}

	


	
}
