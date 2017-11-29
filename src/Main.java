import base.*;

/**
 * @author wangfujie
 */
public class Main {

    public static strictfp void main(String[] args) {
        //用逗号分割字符串“hello”
        System.out.println(String.join(",","hello".split("")));
        //测试类方法调用
        StringTest.codePointTest();
    }
}
