package base;

import java.util.Arrays;

/**
 * 文件输入与输出
 * @author wangfujie
 */
public class ArrayTest {

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        int[] b = a;
        int[] c = Arrays.copyOf(a, a.length);
        b[2] = 333;
        c[2] = 123;
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(c));
    }


}