package com.liweifan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * 
 * @author weifanl
 * @date:  2019年6月9日 下午9:08:12
 */
@SpringBootApplication
@ComponentScan(value="com.liweifan")
public class Application {
	public static void main(String[] args) {
		//第一种启动方法
		//SpringApplication.run(Application.class, args);
		
		//第二种启动方法
		SpringApplication springApplication = new SpringApplication(Application.class);
		//使用这个对象设置一些启动设置
		/*springApplication.setBanner(new Banner() {
			@Override
			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
				out.print("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				out.print("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			}
		});*/
		springApplication.setBannerMode(Mode.CONSOLE);//关闭启动图标
		springApplication.run(args);
		
	}
}
