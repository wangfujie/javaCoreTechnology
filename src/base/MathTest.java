package base;

/**
 * 数学函数
 * @author wangfujie
 */
public class MathTest {

    /**
     * 求平方根
     */
    public static void sqrtTest(){
        double x = 4;
        double y = Math.sqrt(x);
        System.out.println(y);
    }

    /**
     * 幂运算
     */
    public static void powTest(){
        double x = 4;
        double y = Math.pow(x, 2);
        System.out.println(y);
    }

    /**
     * 取模
     */
    public static void floorModTest(){
        System.out.println(Math.floorMod(-12, -5));
    }

    /**
     * Math还提供了一些常用的三角函数：
     * Math.sin
     * Math.cos
     * Math.tan
     * Math.atan
     * Math.atan2
     * 还有指数函数和它的反函数--自然对数 以及以10为底的对数：
     * Math.exp
     * Math.log
     * Math.log10
     * 最后Math还提供了两个用于标识π和常量e的近似值
     * Math.PI
     * Math.E
     */

    /**
     * 在源文件的顶部加上静态导入，就可以不必在数学方法和常量名前加前缀“Math”了
     * import static java.lang.Math.*;
     */
}
