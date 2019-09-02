package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  自定义注解
 *  Target：标识注解可以使用得位置（Method方法，Field字段,Type类。。。）
 *  Retention：保留策略，表示需要在什么级别保存该注释信息，用于描述注解得生命周期
 *      SOURCE：在源文件中有效  CLASS：在class文件中有效   RUNTIME：在运行时有效（可以被反射机制读取）
 * @author wangfj
 */
@Target(value = {ElementType.METHOD,ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    //类型 参数名() default 默认值
    String value() default "";

}
