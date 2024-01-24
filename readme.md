# Galaxy

万游卡后端项目整合

## 目录说明

- [galaxy-app](galaxy-app)  
  App项目
- [galaxy-common](galaxy-common)  
  公共项目
- [galaxy-framework](galaxy-framework)  
  框架支持
    - [galaxy-framework-cornerstone](galaxy-framework%2Fgalaxy-framework-cornerstone)  
      基础支持
    - [galaxy-framework-dependency](galaxy-framework%2Fgalaxy-framework-dependency)  
      依赖控制
    - [galaxy-framework-mysql](galaxy-framework%2Fgalaxy-framework-mysql)  
      mysql支持
    - [galaxy-framework-redis](galaxy-framework%2Fgalaxy-framework-redis)  
      redis支持
    - [galaxy-framework-web](galaxy-framework%2Fgalaxy-framework-web)  
      web支持
- [galaxy-management](galaxy-management)
  管理端项目
- [style](style)
  代码样式控制

## 开发说明

### 组件

- Mysql
- Redis
- RocketMq
- EMQX(待定)

### 应用技术

- SpringBoot
- Mybatis
- Mybatis-plus
- Git
- Maven
- Lombok
- Redisson

### 开发须知

**开发前请阅读[codeStyle.md](style%2FcodeStyle.md)**

建议修改你的Code template为

``` java
/**
 * ${NAME}
 * @author [you name] create on ${DATE} ${TIME}
 */
```

**原则上全系统避免使用`Date`对象**  
请使用LocalDateTime和LocalDate替换  
与前台约定统一使用时间戳作为时间传递格式

**全局使用post请求方式**  
这应该是本系统强制性规范  
关于这个问题,知乎上曾经有一个回复,如果一个项目开发规程中,所有参与人员都是经验十分丰富,能力出众,封装思想很强的人,那我一定会上Restful风格  
写出来的接口绝对可以做到赏心悦目  
但是开发中如果团队中每个人对于封装,接口限界的设计思想不统一的话,强行使用Restful带来的后果就是前后端对接开发的过程中问题不断.  
至于为什么传统的get请求也不允许使用.单一的请求方式可以避免前后端很多的纠纷,你永远不必为了这个接口是get那个是post,而对方没有认真看文档导致405而头疼.
何况只使用post对你应该不是问题

**测试用例**  
不强制编写测试用例,你也知道大多数的业务是CRUD,编写测试用例的意义并不是很大.  
但是如果你编写了一个工具类或者复杂的算法类,或者第三方对接的类,那么编写测试用例还是十分必要的.  
对于证明你的代码没有问题.

**设计模式**  
关于设计模式,设计模式是一套关于面向对象的方法论,如果你能正确使用设计模式那再好不过了.  
正确的使用设计模式确实能大幅度降低代码的逻辑复杂度.  
但不要过分使用设计模式,场景相对简单的时候就不要强行使用了,除非你可以预见未来的扩展性.

**关于Mybatis-Plus**  
mybatis-plus有很多可以使用的东西,不建议复杂查询使用生成器编写,一个是生成器隐藏了实现细节,维护性不是很好.

### 包说明

| 包名         | 说明    |
|------------|-------|
| controller | 接口    |
| service    | 应用服务层 |
| biz        | 业务服务层 |
| mapper     | 持久层   |
| entity     | 数据模型  |

**关于service和biz**  
通常情况下两个层级是一样的,但是在某些业务场景比较复杂的时候,同一个service会引入各种不同的service,甚至十几个mapper  
这种情况下就可以通过引入biz层来聚合mapper层的操作以达到简化代码结构的操作,这是一种关于业务的封装想法.  
即biz层对内,面对一个业务操作需要同时操作很多个mapper的场景,service层对外,完成外部接口的应用操作.  
有一些项目还会区分出manager层用于处理缓存和持久化的操作关系,但我感觉这种操作过于臃肿了.  
biz层不是必须的,只是在你感觉业务逻辑复杂的时候,来简化逻辑的.

**关于dto和vo,bo,do等**  
数据模型包装分类确实十分繁杂,每个系统有每个系统不同的规范和想法.
咱们系统中在使用上,并不想设计的过分复杂.
仅vo用于封装请求实体,dto用来作为传输对象和返回实体,do作为数据库操作对象.
如果未来需要拆分微服务的时候设计简单一点也方便迁移.

### 常用对象

#### 返回

相关目录:[result](galaxy-framework%2Fgalaxy-framework-web%2Fsrc%2Fmain%2Fjava%2Fcom%2Fyidian%2Fgalaxy%2Fweb%2Fentity%2Fresult)

[Result.java](galaxy-framework%2Fgalaxy-framework-web%2Fsrc%2Fmain%2Fjava%2Fcom%2Fyidian%2Fgalaxy%2Fweb%2Fentity%2Fresult%2FResult.java)
全局返回对象  
[PageResult.java](galaxy-framework%2Fgalaxy-framework-web%2Fsrc%2Fmain%2Fjava%2Fcom%2Fyidian%2Fgalaxy%2Fweb%2Fentity%2Fresult%2FPageResult.java)
分页返回对象,但依旧需要使用
[Result.java](galaxy-framework%2Fgalaxy-framework-web%2Fsrc%2Fmain%2Fjava%2Fcom%2Fyidian%2Fgalaxy%2Fweb%2Fentity%2Fresult%2FResult.java)
进行包裹

#### 请求

请求参数校验使用`spring-boot-starter-validation`
请使用`javax.validation`相关注解  
分页请求继承[PageRequest.java](galaxy-framework%2Fgalaxy-framework-web%2Fsrc%2Fmain%2Fjava%2Fcom%2Fyidian%2Fgalaxy%2Fweb%2Fentity%2Frequest%2FPageRequest.java)  
分页排序请求继承[SortPageRequest.java](galaxy-framework%2Fgalaxy-framework-web%2Fsrc%2Fmain%2Fjava%2Fcom%2Fyidian%2Fgalaxy%2Fweb%2Fentity%2Frequest%2FSortPageRequest.java)  
排序的具体操作详见注释

### 文档说明

文档使用Swagger3注解配合使用ApiFox自动生成文档.
请稍微学习一下Swagger3的注解

## 模块说明

### 用户模块

可以使用[PublicApi](galaxy-framework%2Fgalaxy-framework-web%2Fsrc%2Fmain%2Fjava%2Fcom%2Fyidian%2Fgalaxy%2Fweb%2Fano%2FPublicApi.java)
注解跳过登录验证

用户信息保存在[UserHolder](galaxy-framework%2Fgalaxy-framework-web%2Fsrc%2Fmain%2Fjava%2Fcom%2Fyidian%2Fgalaxy%2Fweb%2Fsession%2FUserHolder.java)

登录Token使用JWT

### 权限说明

(待补充)

### 异常说明

自定义异常需要实现[ExceptionWrapper.java](galaxy-framework%2Fgalaxy-framework-web%2Fsrc%2Fmain%2Fjava%2Fcom%2Fyidian%2Fgalaxy%2Fweb%2Fexception%2FExceptionWrapper.java)
接口

常规异常可直接按BusinessException抛出

如需单独定义异常枚举  
Code定义需要遵守

- 系统级错误1-100
- 用户及权限相关错误100+
- 业务错误1000+

### Redis使用说明

详见[RedisSupport.java](galaxy-framework%2Fgalaxy-framework-redis%2Fsrc%2Fmain%2Fjava%2Fcom%2Fyidian%2Fgalaxy%2Fredis%2Fsupport%2FRedisSupport.java)

系统引入了Redisson

RedisKey请务必定义常量或者使用项目中的RedisKey枚举定义

原则上不定义不过期的key,除非业务场景需要

