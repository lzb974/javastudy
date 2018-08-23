### 地址 https://blog.csdn.net/a925907195/article/details/76577489

### https://github.com/a925907195/Springboot-Netty-Protostuff-ZooKeeper/tree/master/fjsh-parent
### 说明

项目整体工程
整体工程目录如下：
fjsh-parent:此模块是整体项目父工程，所有子工程依赖父工程以及父工程中的jar包，可以防止jar包冲突以及版本问题。

fjsh-rpc-client:负责client端对服务的调用及访问。RpcProxy负责业务端调用远程服务的参数封装。ServiceDiscovery负责服务发现以及对服务的监听。

fjsh-rpc-common：是此框架中的通用工具包，包括编解码以及序列化等工作。

fjsh-rpc-server:此模块负责服务端的服务注册，心跳检查以及注册中心失败后的服务重连等。

fjsh-rpc-statistic:此模块是工程中的性能统计模块，是开发中的辅助模块，可以通过此模块来监听请求流程来快速定位工程中的性能等问题。





下图是一个RPC项目的各模块关系图。
server端基于springboot启动，启动后根据配置信息自动去zk注册自身节点信息。

信息注册中如果有业务目录节点则直接添加数据节点，如果没有业务目录节点则新建永久目录节点。

sever端注册成功后，启动zk定时心跳监听，如果注册中心down掉后继续提供client服务，server会不断重试，直到注册中心reactive，注册中心恢复后如果session没有过期则继续使用数据节点提供服务，如果session过期后会重新新建zk数据节点并通过监听机制通知client端。

client端可以基于tomcat、jetty或者runnable-jar启动，client启动后会去zk上拉取服务节点信息并保存到本地。

每次服务请求，client随机从服务节点中选择一个提供服务。

client会监听zk节点信息变化，如果zk注册节点信息有变化，会通知client进行更改，如果zk注册节点连接失败，client会通过保留到本地的服务节点继续提供服务，并且根据zk watch事件进行尝试重连，直到链接成功。

单次请求进行阻塞，数据返回后进行通知并设置超时时间，超时后请求抛弃。

在SerializationUtil中用户可以根据自己的需要定制化序列化方式，此处使用protostuff进行传输数据的编解码操作。


个人总结
1：client为客户端技术框架；
2：server为服务端技术框架；
3：使用tomcat插件客户端运行client-demo；
4: 使用tomcat插件服务端运行service-impl；
5：