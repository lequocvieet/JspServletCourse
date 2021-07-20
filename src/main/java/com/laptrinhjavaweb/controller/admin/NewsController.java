package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;
 


@WebServlet(urlPatterns= {"/admin-news"})

public class NewsController extends HttpServlet {
 
	 @Inject
	 private INewsService newsService;
	private static final long serialVersionUID = 2686801510274002166L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse respone ) throws ServletException,IOException {
    	FormUtil formUtil=new FormUtil();
    	NewsModel model=formUtil.toModel(NewsModel.class, request);
    	
    	Pageble pageble=new PageRequest(model.getPage(), model.getMaxItemPerPage(), 
    			new Sorter(model.getSortName(), model.getSortBy()));
    	
    	//offset specify which row to start
    	
    	request.setAttribute(SystemConstant.MODEL, model);// tranfer data in model to Constant MODEL 
        // so in list.jsp can use Model

    	model.setListResult(newsService.findAll(pageble));  //get all list news and stored it in to model
    	                                                   // so in view you can show list
    	model.setTotalItem(newsService.getTotalItem());
    	model.setTotalPage((int)Math.ceil((double)model.getTotalItem()/model.getMaxItemPerPage()));
    	RequestDispatcher rd=request.getRequestDispatcher("views/admin/news/list.jsp");
    	rd.forward(request, respone);
    	
    }
	
 protected void doPost (HttpServletRequest request, HttpServletResponse respone ) throws ServletException,IOException {
    	
    	
    }

}
