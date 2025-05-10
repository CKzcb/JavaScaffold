# 脚手架

### CommandLineRunner和PostStruct的顺序

@PostConstruct 方法：
在 Bean 的生命周期中，当 Bean 完成依赖注入后，@PostConstruct 注解的方法会被立即调用。每个 Bean 的 @PostConstruct
方法在其初始化阶段执行。

CommandLineRunner 的 run 方法：
在所有 Bean 初始化完成，且应用上下文完全准备好之后（即在 ApplicationReadyEvent 发布后），CommandLineRunner 的 run 方法才会被调用。




