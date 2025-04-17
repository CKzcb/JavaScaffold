# Day1

###         

在 Maven 的 pom.xml 中，<dependencies> 和 <dependencyManagement> 是两个不同的标签，分别用于管理依赖的实际引入和版本声明

1. <dependencies>：直接声明依赖
   作用：在 <dependencies> 中声明的依赖会直接引入到当前项目中，成为项目编译、测试、运行的组成部分。
   特点：
   依赖会被立即下载并添加到项目的类路径中。
   如果未指定版本号，且未通过父 POM 或 <dependencyManagement> 管理版本，Maven 会报错。
   所有子模块（如果是多模块项目）默认会继承这些依赖。
2. <dependencyManagement>：统一管理依赖版本
   作用：仅在 <dependencyManagement> 中声明依赖的版本号和作用域（Scope），但不会直接引入依赖。它的目的是统一管理多模块项目中的依赖版本，确保所有子模块使用相同的版本。
   特点：
   依赖不会自动引入到项目中，子模块需要显式声明依赖（但无需指定版本）。
   通常用在父 POM 中，为所有子模块提供统一的依赖版本。
   支持通过 <scope>import</scope> 导入其他 BOM（Bill of Materials）的依赖管理（如 Spring Boot 的 spring-boot-dependencies）。

| 标签         | 作用                                   |
|------------|--------------------------------------|
| groupId    | 标识依赖所属组织（Spring Boot 官方组件）。          |
| artifactId | 指定 BOM 名称（spring-boot-dependencies）。 |
| version    | 明确 Spring Boot 的版本（3.1.5）。           
| type       | 声明依赖类型为 POM 文件（非 JAR）。               
| scope      | 通过 import 将 BOM 中的版本规则导入当前项目。        
