package in.co.rays.proj4.controller;

import java.io.IOException;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.model.BaseModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class BaseListCtl<B extends BaseBean, M extends BaseModel<BaseBean>> extends BaseCtl<B, M> {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
