package annotation;

import java.lang.annotation.Annotation;

/**
 * 反射获取注解
 *
 * @author wangfj
 */
@MyAnnotation(value = "注解类")
public class Demo01 {

    @MyAnnotation(value = "注解字段")
    private String name;

    @MyAnnotation(value = "注解方法")
    public static void method(){

    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class clazz = Class.forName("annotation.Demo01");
        //获得类的所有注解
        Annotation[] annotations = clazz.getAnnotations();

        //获取类指定的注解
        MyAnnotation myAnnotationType = (MyAnnotation) clazz.getAnnotation(MyAnnotation.class);
        System.out.println(myAnnotationType.value());

        //获取指定属性的指定注解
        MyAnnotation myAnnotationField = clazz.getDeclaredField("name").getAnnotation(MyAnnotation.class);
        System.out.println(myAnnotationField.value());

        //获取指定方法的指定注解
        MyAnnotation myAnnotationMethod = clazz.getMethod("method").getAnnotation(MyAnnotation.class);
        System.out.println(myAnnotationMethod.value());

    }
}
