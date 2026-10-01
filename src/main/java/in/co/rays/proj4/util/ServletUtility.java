package in.co.rays.proj4.util;

import java.io.IOException;
import java.util.List;

import in.co.rays.proj4.bean.BaseBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletUtility {

	public static void forward(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	public static void redirect(String page, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.sendRedirect(page);
	}

	public static void handleException(Exception e, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		e.printStackTrace();
		request.setAttribute("exception", e);
		response.sendRedirect(ORSView.ERROR_CTL);
	}

	public static String getMessage(String key, HttpServletRequest request) {
		String val = (String) request.getAttribute(key);
		if (DataValidator.isNull(val))
			val = "";
		return val;
	}

	public static void setMessage(String key, String msg, HttpServletRequest request) {
		if (DataValidator.isNotNull(key) && DataValidator.isNotNull(msg)) {
			request.setAttribute(key, msg);
		}
	}

	public static void setErrorMessage(String msg, HttpServletRequest request) {
		setMessage(BaseCtl.MSG_ERROR, msg, request);
		setMessage(BaseCtl.HAS_ERROR, "true", request);
		setMessage(BaseCtl.MESSAGE, msg, request);
	}

	public static String getErrorMessage(HttpServletRequest request) {
		String msg = getMessage(BaseCtl.MSG_ERROR, request);
		if (DataValidator.isNull(msg)) {
			msg = request.getParameter(BaseCtl.MSG_ERROR);
		}
		return DataUtility.getStringData(msg);
	}

	public static String getErrorMessage(String key, HttpServletRequest request) {
		return getMessage(key, request);
	}

	public static void setSuccessMessage(String msg, HttpServletRequest request) {
		setMessage(BaseCtl.MSG_SUCCESS, msg, request);
		setMessage(BaseCtl.HAS_ERROR, "false", request);
		setMessage(BaseCtl.MESSAGE, msg, request);
	}

	public static String getSuccessMessage(HttpServletRequest request) {
		String msg = getMessage(BaseCtl.MSG_SUCCESS, request);
		if (DataValidator.isNull(msg)) {
			msg = request.getParameter(BaseCtl.MSG_SUCCESS);
		}
		return DataUtility.getStringData(msg);
	}

	public static void setBean(BaseBean bean, HttpServletRequest request) {
		request.setAttribute("bean", bean);
	}

	public static BaseBean getBean(HttpServletRequest request) {
		return (BaseBean) request.getAttribute("bean");
	}

	public static String getParameter(String property, HttpServletRequest request) {
		String val = request.getParameter(property);
		if (DataValidator.isNull(val))
			val = "";
		return val;
	}

	public static void setList(List list, HttpServletRequest request) {
		request.setAttribute("list", list);
	}

	public static List getList(HttpServletRequest request) {
		return (List) request.getAttribute("list");
	}

	public static void setPageNo(int pageNo, HttpServletRequest request) {
		request.setAttribute("pageNo", pageNo);
	}

	public static int getPageNo(HttpServletRequest request) {
		return (int) request.getAttribute("pageNo");
	}

	public static void setPageSize(int pageSize, HttpServletRequest request) {
		request.setAttribute("pageSize", pageSize);
	}

	public static int getPageSize(HttpServletRequest request) {
		return (int) request.getAttribute("pageSize");
	}

	public static void main(String[] args) {
		Integer val = null;
		int i = val;
	}

}
