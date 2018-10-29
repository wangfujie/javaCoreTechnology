package program;

/**
 * @author wangfujie
 * @date 2018-10-29 10:30
 * @description 杨辉三角
 */
public class YangHuiTriangle {

    public static void main(String[] args) {
        printYangHuiTriangle(11);
    }

    /**
     * 打印杨辉三角
     * @param rows
     */
    static void printYangHuiTriangle(int rows){
        //循环层，顶层为0层，最后一层为n+1层
        for(int n = 0 ; n < rows; n++) {
            int number = 1;
            //打印空格字符串
            System.out.format("%"+(rows-n)*2+"s","");
            //循环打印数字，第n行有n个数字
            for(int m = 0 ; m <= n; m++) {
                //打印边缘为1
                System.out.format("%4d",number);
                //计算打印的数字
                number = number * (n - m) / (m + 1);
            }
            //换行
            System.out.println();
        }
    }
}
