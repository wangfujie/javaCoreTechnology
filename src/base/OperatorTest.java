package base;

/**
 * 运算符
 * @author wangfujie
 */
public class OperatorTest {

    public static void main(String[] args) {
        indexOperatorTest();
    }

    /**
     * 位运算符
     */
    public static void indexOperatorTest(){
        int fourthBitFromRight = (4 & 0b1000) / 0b1000;
        System.out.println(fourthBitFromRight);

    }
}
