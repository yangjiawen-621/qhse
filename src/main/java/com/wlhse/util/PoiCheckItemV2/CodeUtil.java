package com.wlhse.util.PoiCheckItemV2;

import java.text.NumberFormat;
import java.util.List;

public class CodeUtil {

    /**
     * @Description: 对code自增
     * @Param: [code]
     * @return: java.lang.String
     * @Author: hg
     * @Date: 2019/6/4
     */
    public static String codeInc(String code) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(code.length());
        numberFormat.setGroupingUsed(false);
        return numberFormat.format(Integer.parseInt(code) + 1);
    }

    /**
     * @Description:寻找子节点最大code
     * @Param: [list, parent, step]
     * @return: java.lang.String
     * @Author: hg
     * @Date: 2019/6/5
     */
    public static String maxCode(List<String> list, String parent, int step) {
        String max = "";
        for (String str : list) {
            if (null == parent) {
                if (str.length() == step && str.compareTo(max) > 0)
                    max = str;
                continue;
            }
            if (str.length() == parent.length() + step && str.substring(0, parent.length()).equals(parent) && str.compareTo(max) > 0)
                max = str;
        }
        return max;
    }

    /**
     * @Description:寻找最大父节点
     * @Param: [list, step]
     * @return: java.lang.String
     * @Author: hg
     * @Date: 2019/6/5
     */
    public static String maxCode(List<String> list, int step) {
        return maxCode(list, null, step);
    }
}
