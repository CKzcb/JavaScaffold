# day2

### CommandLineRunner和PostStruct的顺序

@PostConstruct 方法：
在 Bean 的生命周期中，当 Bean 完成依赖注入后，@PostConstruct 注解的方法会被立即调用。每个 Bean 的 @PostConstruct
方法在其初始化阶段执行。

CommandLineRunner 的 run 方法：
在所有 Bean 初始化完成，且应用上下文完全准备好之后（即在 ApplicationReadyEvent 发布后），CommandLineRunner 的 run 方法才会被调用。

## ApplicationContext

### ApplicationContext 的核心作用

Bean 管理

负责创建、配置和管理应用中所有的 Bean（通过 XML、注解或 Java 配置定义）。
自动解决 Bean 之间的依赖关系（依赖注入）。
资源访问

提供统一的资源加载接口（如读取文件、URL、类路径资源等）。
例如：context.getResource("classpath:config.xml")。
事件传播

支持事件驱动模型，允许 Bean 之间通过事件（ApplicationEvent）通信。
例如：容器启动事件、Bean 初始化完成事件。
国际化支持

支持多语言消息（通过 MessageSource 接口）。
环境抽象

管理应用的环境配置（如配置文件、系统属性、环境变量等）。
AOP 与事务

集成面向切面编程（AOP）和声明式事务管理。

### ApplicationContext 的生命周期

初始化
加载配置（XML/注解/Java Config）。
创建并初始化所有单例 Bean（调用 @PostConstruct 方法）。
运行
处理请求（如 Web 应用中的 HTTP 请求）。
触发事件（如 ContextRefreshedEvent）。
销毁
调用 context.close() 时，销毁所有 Bean（执行 @PreDestroy 方法）。

## ApplicationContextAware

与 @Autowired 的区别
方式 特点
ApplicationContextAware 显式实现接口，由 Spring 自动注入 ApplicationContext。
@Autowired 通过注解直接注入 ApplicationContext，无需实现接口（更简洁）。
代码对比：

// 方式1：实现接口
@Component
public class MyBean implements ApplicationContextAware { ... }

// 方式2：使用 @Autowired
@Component
public class MyBean {
@Autowired
private ApplicationContext context;
}

用于处理对所有的bean处理

## redis分布式锁

