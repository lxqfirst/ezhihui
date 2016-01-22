package com.ezhi.www.service;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lxq on 16/1/22.
 */
public class BaseTest {

    protected ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    }
}
