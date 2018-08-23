package com.fjsh.rpc.controller;

import com.fjsh.rpc.client.RpcProxy;
import com.fjsh.rpc.entity.User;
import com.fjsh.rpc.service.IHelloService;
import com.fjsh.rpc.statistic.points.StatisticStatusEnums;
import com.fjsh.rpc.statistic.service.IstatisticFacet;
import com.fjsh.rpc.statistic.service.impl.StatisticThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
public class HelloController {
    IstatisticFacet sIstatisticFacet = StatisticThread.getStatisticFacetService();

    @Autowired
    private RpcProxy rpcProxy;


    /**
     * 功能描述:hello
     * url:http://localhost:8080/fjsh-rpc-client/hello?name=fjsdfhd324
     *
     * @param:
     * @return:
     * @auther: dell
     * @date: 2018/8/6 16:02
     */
    @RequestMapping("/hello")
    public void hello(String name) {
        //获取service实例
        IHelloService service = rpcProxy.create(IHelloService.class);
        new Thread(new StatisticThread()).start();
        for (int i = 0; i < 1000; i++) {
            long start = System.currentTimeMillis();
            String result = service.hello(name);
            if (result.equals("hello" + name)) {

            } else {
                try {
                    throw new Exception();
                } catch ( Exception e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            //性能统计
            sIstatisticFacet.setFacet(StatisticStatusEnums.alltimeavg.getType(), StatisticStatusEnums.alltimeavg.getDescription(), 1l);
            sIstatisticFacet.setFacet(StatisticStatusEnums.alltime.getType(), StatisticStatusEnums.alltime.getDescription(), System.currentTimeMillis() - start);
        }
    }

    @RequestMapping("/getUser")
    public void getUser(String name) {
        //获取service实例
        IHelloService service = rpcProxy.create(IHelloService.class);
        System.out.println(service.getUser(name).toString());
    }

    @RequestMapping("/getUsers")
    public void getUsers(int size) {
        //获取service实例
        IHelloService service = rpcProxy.create(IHelloService.class);
        List<User> list = service.getUsers(size);
        for (User user : list) {
            System.out.println(user.toString());
        }
    }

    @RequestMapping("/updateUser")
    public void updateUser(String name) {
        //获取service实例
        IHelloService service = rpcProxy.create(IHelloService.class);
        User user = new User(name, new Date(), true);
        user = service.updateUser(user);
        System.out.println(user.toString());
    }
}
