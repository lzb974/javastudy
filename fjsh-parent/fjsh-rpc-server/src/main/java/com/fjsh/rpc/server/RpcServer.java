package com.fjsh.rpc.server;

import com.fjsh.rpc.annotation.RpcService;
import com.fjsh.rpc.common.RpcDecoder;
import com.fjsh.rpc.common.RpcEncoder;
import com.fjsh.rpc.common.RpcRequest;
import com.fjsh.rpc.common.RpcResponse;
import com.fjsh.rpc.utils.IpUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述: 该类为server端的启动核心类，实现了sprintboot启动接口
 *
 * @param:
 * @return:
 * @auther: dell
 * @date: 2018/8/6 16:17
 */
public class RpcServer implements ApplicationContextAware, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcServer.class);

    private String serverAddress;//当前服务地址信息
    private String serverport;//服务端口号
    private ServiceRegistry serviceRegistry;//service注册 使用zookeeper当注册中心

    private Map<String, Object> handlerMap = new HashMap<String, Object>(); // 存放接口名与服务对象之间的映射关系

    public RpcServer(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public RpcServer(String serverport, ServiceRegistry serviceRegistry) {
        this.serverAddress = IpUtil.getip();
        this.serverport = serverport;
        this.serviceRegistry = serviceRegistry;
    }

    public RpcServer(String serverAddress, String serverport, ServiceRegistry serviceRegistry) {
        this.serverAddress = serverAddress;
        this.serverport = serverport;
        this.serviceRegistry = serviceRegistry;
    }

    /**
     * 功能描述:接口实现处理RPC标签的类放入handlerMap
     *
     * @param:
     * @return:
     * @auther: dell
     * @date: 2018/8/6 16:26
     */
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        Map<String, Object> serviceBeanMap = ctx.getBeansWithAnnotation(RpcService.class); // 获取所有带有 RpcService 注解的 Spring Bean
        if (MapUtils.isNotEmpty(serviceBeanMap)) {
            for (Object serviceBean : serviceBeanMap.values()) {
                String interfaceName = serviceBean.getClass().getAnnotation(RpcService.class).value().getName();
                handlerMap.put(interfaceName, serviceBean);
            }
        }
    }

    /**
     * 功能描述:启动实例后启动netty server RpcHandler处理核心业务
     *
     * @param:
     * @return:
     * @auther: dell
     * @date: 2018/8/6 16:28
     */
    public void afterPropertiesSet() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel channel) throws Exception {
                            ((Channel) channel).pipeline()
                                    .addLast(new RpcDecoder(RpcRequest.class)) // 将 RPC 请求进行解码（为了处理请求）
                                    .addLast(new RpcEncoder(RpcResponse.class)) // 将 RPC 响应进行编码（为了返回响应）
                                    .addLast(new RpcHandler(handlerMap)); // 处理 RPC 请求
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

//            String[] array = serverAddress.split(":");
//            String host = array[0];
//            int port = Integer.parseInt(array[1]);

            String host = serverAddress;
            int port = Integer.parseInt(serverport);
            //配置完成，绑定server，并通过sync同步方法阻塞直到绑定成功
            ChannelFuture future = bootstrap.bind(host, port).sync();
            LOGGER.debug("server started on port {}", port);

            if (serviceRegistry != null) {
                serviceRegistry.register(host + ":" + port); // 注册服务地址
            }
            // 等待服务器 socket 关闭 。在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            future.channel().closeFuture().sync();//应用程序会一直等待，直到channel关闭
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
