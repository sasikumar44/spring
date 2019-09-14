package com.sgic.spring.project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SaveServlet extends HttpServlet {
	
	SaveServlet(){ 
		
		
	}

	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String name = req.getParameter("projectName");
		String desc = req.getParameter("projectDescription");
	
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServlet().getServletContext());
		// ProjectDao dao = (ProjectDao)ctx.getBean("pdao");

		Project p = (Project) ctx.getBean("proj");
		p.setProjectName(name);
		p.setProjectDescription(desc);

		ProjectDao dao = (ProjectDao) ctx.getBean("pdao");

		int status = dao.saveProject(p);
		if (status > 0) {
			out.print("<p>Record saved successfully!</p>");
			req.getRequestDispatcher("project.jsp").include(req, resp);

		} else {
			out.print("Sorry! unable to save record!");
		}

		out.close();

	}

	private GenericServlet getServlet() {

		return null;
	}

}
