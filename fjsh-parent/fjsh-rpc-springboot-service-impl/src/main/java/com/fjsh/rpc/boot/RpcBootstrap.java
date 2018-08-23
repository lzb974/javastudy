package com.fjsh.rpc.boot;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author fujiansheng@58ganji.com 
 */
public class RpcBootstrap {

    /**
     *
     * 功能描述: 服务端执行此类或使用maven tomcat7:run
     *
     * @param:
     * @return:
     * @auther: dell
     * @date: 2018/8/6 16:05
     */
    public static void main(String[] arg){
        new ClassPathXmlApplicationContext("classpath:spring.xml");
    }
}
