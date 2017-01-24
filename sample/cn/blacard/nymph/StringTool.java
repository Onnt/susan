package cn.blacard.nymph;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTool {
	/**
	 * 去除字符中的所有空白符
	 * 
	 * @author Blacard
	 * @create 2017年1月24日 下午2:49:23
	 * @param str
	 * @return
	 */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
