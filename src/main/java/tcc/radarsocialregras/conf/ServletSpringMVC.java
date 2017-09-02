package tcc.radarsocialregras.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import tcc.radarsocialregras.controller.FeedController;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{SecurityConfiguration.class,AppWebConfiguration.class,JPAConfiguration.class};
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{};
	}
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
}
