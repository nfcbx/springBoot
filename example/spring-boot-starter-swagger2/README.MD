
# 编写Starter的步骤

1. 新建Maven项目，在项目的POM文件中定义使用的依赖
2. 新建配置类，写好配置项和默认的配置值，指明配置项前缀
3. 新建自动装配类，使用@Configuration和@Bean来进行自动装配
4. 新建spring.factories文件，指定Starter的自动装配类


## 1. 新建Maven项目，在项目的POM文件中定义使用的依赖

添加依赖

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```


## 2. 新建配置类，写好配置项和默认的配置值，指明配置项前缀

SwaggerProperties

## 3. 新建自动装配类，使用@Configuration和@Bean来进行自动装配

SwaggerAutoConfiguration

## 4、新建spring.factories文件，指定Starter的自动装配类

- spring.factories文件位于resources/META-INF目录下，需要手动创建;
- org.springframework.boot.autoconfigure.EnableAutoConfiguration后面的类名说明了自动装配类，如果有多个 ，则用逗号分开;
- 使用者应用（SpringBoot）在启动的时候，会通过org.springframework.core.io.support.SpringFactoriesLoader读取classpath下每个Starter的spring.factories文件，加载自动装配类进行Bean的自动装配；











