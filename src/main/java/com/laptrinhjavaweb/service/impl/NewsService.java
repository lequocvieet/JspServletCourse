package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.dao.impl.NewsDAO;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.INewsService;

public class NewsService implements INewsService {
    
	@Inject
	private INewsDAO newsDao; //replace constructor to create new INewsDAO 
	
	
	@Override
	public List<NewsModel> findByCategoryId(long categoryId) {
		
		return newsDao.findByCategoryId(categoryId);
	}


	@Override
	public NewsModel save(NewsModel newsModel) {
	    newsModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
	    newsModel.setCreatedBy("");
		Long newId=newsDao.save(newsModel); 
	     
		return newsDao.findOne(newId);
	}


	@Override
	public NewsModel update(NewsModel updateNews) {
		NewsModel oldNews=newsDao.findOne(updateNews.getId());
		updateNews.setCreatedDate(oldNews.getCreatedDate());
		updateNews.setCreatedBy(oldNews.getCreatedBy());
		updateNews.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		updateNews.setModifiedBy("");
		newsDao.update(updateNews);
		
		return newsDao.findOne(updateNews.getId());
		
		
	}


	@Override
	public void delete(long[] ids) {
		for(long id: ids) {
			//delete comment table because constrained by foreign key
			newsDao.delete(id);
		}
		
	}


	


	@Override
	public int getTotalItem() {
		
		return newsDao.getTotalItem();
	}


	@Override
	public List<NewsModel> findAll(Pageble pageble) {
		return newsDao.findAll(pageble);
	}


	
	
}
