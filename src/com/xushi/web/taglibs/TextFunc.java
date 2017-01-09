package com.xushi.web.taglibs;

/**
 * 文本函数
 * @author liangli
 *
 */
public class TextFunc {

	/** 需转义字符 */
    private static final String[] ESCAPES;
    static {
        int size = '>' + 1; // '>' is the largest escaped value
        ESCAPES = new String[size];
        ESCAPES['<'] = "&lt;";
        ESCAPES['>'] = "&gt;";
        ESCAPES['&'] = "&amp;";
        ESCAPES['\''] = "&#039;";
        ESCAPES['"'] = "&#034;";
    }

    private static String getEscape(char c) {
    	
        if (c < ESCAPES.length) {
            return ESCAPES[c];
        } else {
            return null;
        }
    }

	/**
	 * 文本长度（中文字符=2）
	 * @param text
	 * @return
	 */
	public static int textLength(String text) {
		
		if (null == text || text.length() < 1) return 0;
		
		int nLen = 0;
		char c;
		for (int i=text.length()-1; i>=0; i--) {
			
			c = text.charAt(i);
			if (0x0000 == (0xFF00 & c))
				nLen ++;
			else
				nLen += 2;
		}
		return nLen;
	}
	
	/**
	 * 文本子串，最后是中文字符时允许多1
	 * @param text
	 * @param max 长度（中文字符=2）
	 * @return
	 */
	public static String textSubset(String text, int max) {
		
		if (null == text || max < 1 || text.length() < (max / 2)) return text;
		
		int nLen = 0;
		char c;
		int nCount = text.length();
		StringBuilder sb = new StringBuilder(max < nCount ? max : nCount);
		for (int i=0; i<nCount; i++) {
			
			c = text.charAt(i);
			if (0x0000 == (0xFF00 & c))
				nLen ++;
			else
				nLen += 2;
			sb.append(c);
			
			if (nLen >= max) break;
		}
		return sb.toString();
	}
	
	/**
	 * 文本截短，超出后缀...
	 * @param text
	 * @param max
	 * @return
	 */
	public static String textEllipsis(String text, int max) {
		
		if (null == text || max < 1 || text.length() < (max / 2)) return text;
		
		int nLen = 0;
		char c;
		int nCount = text.length();
		StringBuilder sb = new StringBuilder(max < nCount ? max : nCount);
		int nTruncated = 0;
		for (int i = 0; i < nCount; i++) {
			
			c = text.charAt(i);
			if (0x0000 == (0xFF00 & c))
				nLen++;
			else
				nLen += 2;
			sb.append(c);
			if (nLen > max) {
				
				// 超出了，需要截短4个
				nTruncated = 4;
				break;
			}
			else if (nLen == max && 1 + i < nCount) {

				// 还有超长了，截短3个
				nTruncated = 3;
				break;
			}
		}
		
		if (nTruncated > 0 && sb.length() > nTruncated) {

			// 截短，再加上省略号
			nCount = sb.length() - 1;
			while (nTruncated > 0) {
				
				c = sb.charAt(nCount);
				if (0x0000 == (0xFF00 & c))
					nTruncated --;
				else
					nTruncated -= 2;
				
				if (nTruncated >= 0) {
					
					// 如果最后一个是中文，保留
					sb.deleteCharAt(nCount);
					nCount--;
				}
			}
			sb.append("...");
		}
		return sb.toString();
	}
	
    /**
     * XML编码
     * @param input
     * @return
     */
    public static String x(String input) {
    	
    	if (null == input || input.length() < 1) return "";

    	// 计算长度
    	int nSize = input.length();
        int nLen = 0;
        for (int i = 0; i < input.length(); i++) {
        	
            char c = input.charAt(i);
            String escape = getEscape(c);
            if (escape != null) {
            	nLen += escape.length();
            } else {
            	nLen += 1;
            }
        }

        // 不需要转换
        if (nLen == nSize) return input;

        StringBuilder buf = new StringBuilder(nLen);
        for (int i = 0; i < nSize; i++) {
        	
            char c = input.charAt(i);
            String escape = getEscape(c);
            if (escape != null)
                buf.append(escape);
            else
                buf.append(c);
        }
        return buf.toString();
    }
	
	public static void main(String[] args) {
		
		String strText = "This测试,.。，?<>&'\"";
		System.out.println(strText + " length:" + strText.length());
		System.out.println("textLength: " + textLength(strText));
		System.out.println("textSubset 11: " + textSubset(strText, 11));
		System.out.println("textSubset 12: " + textSubset(strText, 12));
		System.out.println("textEllipsis 10: " + textEllipsis(strText, 10));
		System.out.println("textEllipsis 11: " + textEllipsis(strText, 11));
		System.out.println("x 11: " + x(strText));
	}
}
