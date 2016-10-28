package corp.adentis.wc;

import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Application implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.setConfigLocation("corp.adentis.wc.configurations");
		Dynamic appServlet = servletContext.addServlet("appServlet", new DispatcherServlet(applicationContext));
		
		appServlet.setLoadOnStartup(1);
		appServlet.addMapping("/");
		
	}

}
