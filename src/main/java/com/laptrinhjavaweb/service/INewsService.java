package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface INewsService {
List<NewsModel> findByCategoryId(long categoryId);
NewsModel save(NewsModel newsModel);
NewsModel update(NewsModel updateNews);
void delete(long[] ids);
List<NewsModel> findAll(Pageble pageble);
int getTotalItem();

}
