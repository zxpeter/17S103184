package tools;

public class StringUtil {
	// 拆分出协议的头部分 跟用户相关的协议 ：
	// 协议码|文件名|hash值|文件大小
	// 查询所有的文件的协议是：
	// 协议码|

	// ----------合成字符串(把一个对象的所有属性合成一个字符串)
	/**
	 * 获得StateCode，协议码
	 * 
	 * @param s
	 * @return
	 */
	public static String getStateCode(String s) {
		if (s.contains("|")) {
			int i = s.indexOf('|');
			s = s.substring(0, i);
			return s;
		} else {
			return "UNKNOW";
		}

	}

	/**
	 * 获得StateCode，协议码
	 * 
	 * @param s
	 * @return
	 */
	public static String getADDInfo(String s) {

		return s;
	}

}
