package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.service.impl.NewsService;
import com.laptrinhjavaweb.utils.HttpUtil;

 @WebServlet(urlPatterns = {"/api-admin-new"})
public class NewsAPI extends HttpServlet {

	private static final long serialVersionUID = 5644404405623586930L;
    
	@Inject
	private INewsService newsService;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse respone)
			throws ServletException, IOException {
		ObjectMapper mapper= new ObjectMapper();
		request.setCharacterEncoding("UTF-8");//client gui xuong server
		respone.setContentType("application/json");//server gui len client
		NewsModel newsModel=  HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		newsModel=newsService.save(newsModel);
		mapper.writeValue(respone.getOutputStream(), newsModel);
		
		}

	protected void doPut(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {

		ObjectMapper mapper =new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		respone.setContentType("application/json");
		NewsModel updateNews=HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		updateNews=newsService.update(updateNews);
		mapper.writeValue(respone.getOutputStream(),updateNews);
		
		
		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse respone)
			throws ServletException, IOException {
		ObjectMapper mapper =new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		respone.setContentType("application/json");
		NewsModel newsModel=HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		 newsService.delete(newsModel.getIds());
		 mapper.writeValue(respone.getOutputStream(), "{}");

	}

}
