package edu.ycp.cs320.lab02a_rweaver11.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.lab02a_rweaver11.controller.NumbersController;
import edu.ycp.cs320.lab02a_rweaver11.model.Numbers;

public class AddNumbersAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doRequest(req, resp);
	}

	private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get parameters
		
		Numbers model = new Numbers();
		
		model.setFirst(getDouble(req, "first"));
		model.setSecond(getDouble(req, "second"));
		model.setThird(getDouble(req, "third")); 
		
		// Check whether parameters are valid
		if (model.getFirst() == null || model.getSecond() == null || model.getThird() == null) {
			badRequest("Bad parameters", resp);
			return;
		}
		
		// Use a controller to process the request
		NumbersController controller = new NumbersController();
		controller.add(); 
		
		// Send back a response
		resp.setContentType("text/plain");
		resp.getWriter().println(model.getResult().toString());
	}

	private Double getDouble(HttpServletRequest req, String name) {
		String val = req.getParameter(name);
		if (val == null) {
			return null;
		}
		try {
			return Double.parseDouble(val);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private void badRequest(String message, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		resp.getWriter().println(message);
	}
}
