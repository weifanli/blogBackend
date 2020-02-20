package com.liweifan;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class SpringContextMVC implements EnvironmentAware,ErrorPageRegistrar,WebMvcConfigurer  {

	private Environment env;

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}
	
	@Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/modules/**").addResourceLocations("/static/modules/").setCachePeriod(0);
	}
	/**
	 * 视图解析器
	 * @author 创建人：weifanl
	 * @date:  创建日期：2019年11月19日 下午1:08:33
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix(env.getProperty("spring.mvc.view.prefix"));
		internalResourceViewResolver.setSuffix(env.getProperty("spring.mvc.view.suffix"));
		return internalResourceViewResolver;
	}
	/**
	 * 根据不同的错误显示不同的页面
	 */
	@Override
    public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {
		//按错误的类型显示错误的网页
		//注意：后面配置的路径是一个@RequestMapping，对应controller的一个方法，具体页面为对应方法返回的页面
		ErrorPage e403 = new ErrorPage(HttpStatus.FORBIDDEN, "/error/page403");
        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/page404");
        ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/page500");
        //还可以自定义异常
        //ErrorPage argsException = new ErrorPage(IllegalArgumentException.class, "/error/page404");
        errorPageRegistry.addErrorPages(e403,e404, e500);
    }
	
/*	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		
	}
	

	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		
	}
	*/
	

}
