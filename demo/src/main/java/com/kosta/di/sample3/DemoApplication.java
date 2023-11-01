package com.kosta.di.sample3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans3.xml");
		Employee bean = context.getBean("employee", Employee.class);
		bean.info();
	}

}
