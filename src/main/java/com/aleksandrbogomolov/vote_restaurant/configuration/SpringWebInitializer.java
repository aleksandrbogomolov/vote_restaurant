package com.aleksandrbogomolov.vote_restaurant.configuration;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.Filter;

public class SpringWebInitializer extends AbstractDispatcherServletInitializer {

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringWebConfiguration.class);
        return context;
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        DelegatingFilterProxy proxy = new DelegatingFilterProxy("springSecurityFilterChain");
        return new Filter[]{proxy, encodingFilter};
    }


//    May be change on this with extends WebMvcConfigurerAdapter
//        @Override
//        public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
//        applicationContext.register(SpringWebConfiguration.class);
//        applicationContext.setServletContext(servletContext);
//        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("dispatcher", new DispatcherServlet(applicationContext));
//        servletRegistration.setLoadOnStartup(1);
//        servletRegistration.addMapping("/");
//
//        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
//        encodingFilter.setEncoding("UTF-8");
//        encodingFilter.setForceEncoding(true);
//        FilterRegistration.Dynamic filter = servletContext.addFilter("characterEncodingFilter", encodingFilter);
//        filter.addMappingForUrlPatterns(null, false, "/*");
//    }
}
