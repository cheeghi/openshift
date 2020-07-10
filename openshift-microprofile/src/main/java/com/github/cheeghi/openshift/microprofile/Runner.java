package com.github.cheeghi.openshift.microprofile;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class Runner {

	public static void main(String[] args) throws LifecycleException {
		Tomcat tomcat = new Tomcat();

		int webPort = 8080;
		tomcat.setPort(webPort);
		tomcat.getConnector();
		String contextPath = "";
		String appBase = ".";
		Context context = tomcat.addWebapp(contextPath, new File(appBase).getAbsolutePath());
		Tomcat.addServlet(context, "default-servlet", new HttpServlet() {
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				resp.getWriter().print("Server is running");
				resp.setStatus(200);
			}
		});
		context.addServletMappingDecoded("/*", "default-servlet");
		tomcat.start();
	}

}
