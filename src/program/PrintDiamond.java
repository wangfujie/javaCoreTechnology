package program;

/**
 * @author wangfujie
 * @date 2018-10-26 14:04
 * @description 打印菱形
 */
public class PrintDiamond {

    public static void main(String[] args) {
        // 输出5行的菱形
        print(5);
    }

    public static void print(int size) {
        // 计算菱形最长列的大小
        size = size*2 - 1;
        //上半部分
        for (int i = 0; i < size / 2 + 1; i++) {
            System.out.println(i);
            for (int j = size / 2 + 1; j > i + 1; j--) {
                // 输出左上角位置的空白
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                // 输出菱形上半部边缘
                System.out.print("*");
            }
            System.out.println(); // 换行
        }
        //下半部分
        for (int i = size / 2 + 1; i < size; i++) {
            for (int j = 0; j < i - size / 2; j++) {
                // 输出菱形左下角空白
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * size - 1 - 2 * i; j++) {
                // 输出菱形下半部边缘
                System.out.print("*");
            }
            System.out.println(); // 换行
        }
    }
}
