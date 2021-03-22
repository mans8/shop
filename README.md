# shop
**购物商城模块：**

- 商品服务shop-product
- 仓储服务shop-ware
- 订单服务shop-order
- 优惠券服务shop-coupon
- 会员服务shop-member
- 购物车服务shop-cart
- 检索服务shop-search
- 授权认证服务shop-auth-server
- 接入第三方服务shop-third-party
- 网关服务shop-gateway
- 公共依赖shop-common

***

**技术搭配方案**：

- SpringCloud Alibaba - Nacos：注册中心（服务发现/注册）
- SpringCloud Alibaba - Nacos：配置中心（动态配置管理）
- SpringCloud - Ribbon：负载均衡
- SpringCloud - Feign：声明式http客户端（调用远程服务）
- SpringCloud Alibaba - Sentinel：服务容错（限流、降级、熔断）
- SpringCloud - Gateway：API网关（webflux编程模式）
- SpringCloud - Sleuth：调用链监控
- SpringCloud Alibaba - Seata：原Fescar，即分布式事务解决方案

[SpringCloudAlibaba版本说明](https://github.com/alibaba/spring-cloud-alibaba/wiki/版本说明)

[Nacos下载，1.1.4版本稳定](https://github.com/alibaba/nacos/releases)

***

**结构：**
1.创建项目时使用初始化器导入web和openfeign
2.com.hgx.shop.xxx 

**脚手架：** https://gitee.com/renrenio（码云：人人开源）

***

**Feign：**

Feign是一个声明式的http客户端，目的是使远程调用更加简单。Feign提供了http请求的模板，**通过编写简单的接口和插入注解**，就可以定义好http请求的参数、格式、地址等信息。

Feign整合了**Ribbon（负载均衡）**和**Hystrix（服务熔断）**，可以让我们不再需要显式地使用这两个组件。

SpringCloudFeign在NetflixFeign的基础上扩展了对SpringMVC注解的支持，在其实现下，我们只需要创建一个接口并用注解的方式来配置它，即可完成对服务提供方的接口绑定。简化了SpringCloudRibbon自行封装服务调客户端的开发量。

1. 引入openFeign
2. 编写一个接 口，声明接口的每一个方法都是调用哪个远程服务的哪个请求
3. 开启远程调用功能，主类注解@EnableFeignClient(basePackage = "com.hgx.shop.xxx.feign")

****

**Nacos做配置中心：**

1. 引入依赖

2. 在resource下创建一个bootstrap.properties，加入

   > spring.application.name=shop-coupon
   > spring.cloud.nacos.config.server-addr=127.0.0.1:8848

3. 在nacos配置中，添加ID为当前应用名（如shop-coupon.properties）属性文件

4. 动态刷新配置@RefreshScope，获取配置的值@Value({xxx.xxx.xxx})

**细节：**

1. 命名空间：配置隔离，默认放在public

> 环境隔离，开发、测试、生产
> 服务隔离，只加载自己命名空间下的所有配置

2. 配置集：所有的配置的集合
3. 配置集ID（Data ID）：类似文件名
4. 配置分组：默认所有的配置集都属于DEFAULT_GROUP，比如双十一用一组，618用一组

**总结：每个微服务创建自己的命名空间，使用配置分组区分环境，如dev、test、prod**

> 一个配置集可以拆分多个配置文件，加载多配置集，任何配置都可以放在配置中心
>
> 在bootstrap.properties中说明加载哪些配置文件，如：
>
> spring.cloud.nacos.config.ext-config[0].data-id=datasource.yml
>
> spring.cloud.nacos.config.ext-config[0].group=dev
>
> spring.cloud.nacos.config.ext-config[0].refresh=true
>
> spring.cloud.nacos.config.ext-config[1].data-id=mybatis.yml
>
> spring.cloud.nacos.config.ext-config[1].group=dev
>
> spring.cloud.nacos.config.ext-config[1].refresh=true
>

***

**SpringCloud Gateway做API网关：**

[Gateway文档，断言和过滤器规则](https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.1.5.RELEASE/single/spring-cloud-gateway.html)

网关是所有请求流量的入口，常用功能包括路由转发、权限校验、限流控制、日志输出等，能实时感知服务的上线和下线。spring gateway是spring cloud的第二代网关框架，取代了zuul网关。

满足某种断言，带你去某个地方。如：

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: test_route
          uri: https://www.baidu.com
          predicates:
            - Query=url,baidu

        - id: qq_route
          uri: https://www.qq.com
          predicates:
            - Query=url,qq
```

***

**Vue模块化开发**

```shell
#全局安装webpack
npm install webpack -g
#全局安装vue脚手架
npm install -g @vue/cli-init
#进入项目目录下，初始化vue项目
vue init webpack appname
#确认信息时选择runtime+complier
#确认信息时选择安装vue-router
#运行项目
cd appname
npm run dev
```

***

**整合Element-UI**

[Element-UI中文官方文档](https://element.eleme.cn/#/zh-CN/component/installation)

```
cd appname
npm i element-ui


```

