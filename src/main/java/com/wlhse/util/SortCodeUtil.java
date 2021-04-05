package com.wlhse.util;

import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Component
public class SortCodeUtil {
    public String getMaxCodeString(String strCode) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(strCode.length());
        numberFormat.setGroupingUsed(false);
        return numberFormat.format(Long.valueOf(strCode) + 1);
    }

    public String getNoNumberString(String servletPath) {
        char[] chars = servletPath.toCharArray();
        int length = chars.length;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (Character.isDigit(chars[i])) {
                length--;
            } else {
                break;
            }
        }
        return servletPath.substring(0, length);
    }

    public String next(String code) {
        char[] chars = code.toCharArray();
        boolean next = false;
        for (int i = code.length() - 1; i >= 0; i--) {
            if (chars[i] == 'Z' || (next && chars[i] == 'Y')) {
                chars[i] = '0';
                next = true;
            } else {
                if (chars[i] == '9' || (next && chars[i] == '8'))
                    chars[i] = 'A';
                else
                    chars[i] = (char) (chars[i] + 1);
                next = false;
            }
            if (!next)
                break;
        }
        return String.valueOf(chars);
    }

    //获取最大的code
    //list中所有code长度一样
    public String getMaxCode(List<String> strCode) {
        Collections.sort(strCode);
        return strCode.get(strCode.size()-1);
    }

}
