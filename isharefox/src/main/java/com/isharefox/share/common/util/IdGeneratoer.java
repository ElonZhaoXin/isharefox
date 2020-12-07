package com.isharefox.share.common.util;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class IdGeneratoer{

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        //取不百万级别，无重复 32*32*32*32= 1,048,576
        int total = 32 * 32 * 32 * 32;
        for(int x = 0; x < total; x++) {
            boolean noRepeat = set.add(format10To32(String.valueOf(x)));
            Assert.isTrue(noRepeat, "重复值：" + x + " , 32进制数:" + format10To32(String.valueOf(x)));
        }
        System.out.println("未发现重复值:" + total);


        System.out.println(format32To10("xxx3"));
    }

    /**
     * @param num  32位数
     * @return 32位数 + 1
     */
    public static String increment32Num(String num) {
        if (!StringUtils.hasLength(num)) {
            //默认值
            return "zzz1";
        }
        int incrementNum = Integer.valueOf(IdGeneratoer.format32To10(num)) + 1;
        return IdGeneratoer.format10To32(String.valueOf(incrementNum));
    }
    /**
     * 十进制转三十二进制(0~9 a~b~c~u-v)   无wxyz
     * @param num
     * @return
     */
    public static String format10To32(String num) {
        String str = new java.math.BigInteger(num, 10).toString(32);
        if (str.length() < 2) {
            return "zzz" + str;
        } else if (str.length() < 3) {
            return "zzz" + str;
        } else if (str.length() < 4) {
            return "x" + str;
        }
        return str;
    }

    public static String format32To10(String num) {
        String x = num.replaceAll("[z]","");
        return new java.math.BigInteger(x, 32).toString(10);
    }
}
