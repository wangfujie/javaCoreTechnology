package base;

import net.sourceforge.pinyin4j.PinyinHelper;
import java.util.Date;

/**
 * 字符串
 * @author wangfujie
 */
public class StringTest {

    public static void main(String[] args) {
        codePointTest();
    }

    /**
     * 码点与代码单元
     */
    public static void codePointTest(){
        String greeting = "hello";
        int cpCount = greeting.codePointCount(0, greeting.length() - 1);
        System.out.println("码点数量：" + cpCount);
        int i = greeting.length() - 2;
        int index = greeting.offsetByCodePoints(0, i);
        int cp = greeting.codePointAt(index);
        System.out.println("获得第i个码点下标：" + index);
        System.out.println("获得第i个码点：" + cp);
    }

    /**
     * String的静态方法字符串格式化
     */
    public static void formatTest(){
        //格式化时间类
        String year = String.format("%tY",new Date());
        String month = String.format("%tm",new Date());
        String day = String.format("%td",new Date());
        //用%后接索引（从1开始）以$结束，代表后面的第几个参数
        String time = String.format("%1$tY年%1$tm月%1$td日",new Date());
        //可以用<符号代表沿用上一个参数
        String time2 = String.format("%tY年%<tm月%<td日",new Date());
        System.out.println("年：" +  year + "，月：" + month + "，日：" + day);
        System.out.println(time);
        System.out.println(time2);
        System.out.println(new Date());
    }

    /**
     * 将汉字转换成拼音
     * @param hanyu
     * @return
     * @throws Exception
     */
    public static String hanyu2py(String hanyu) throws Exception{
        if (hanyu == null || "".equals(hanyu)) {
            throw new Exception("空值无法转成拼音");
        }
        char[] charArray = hanyu.toCharArray();
        StringBuilder pinyin = new StringBuilder();
        for(int i=0; i<charArray.length; i++){
            if(Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")){
//                System.out.println(charArray[i]);
                String[] arr = PinyinHelper.toTongyongPinyinStringArray(charArray[i]);
                pinyin.append(arr[0]);
            }else{
                pinyin.append(charArray[i]);
            }
        }
        return pinyin.toString();
    }
}