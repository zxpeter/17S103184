package tools;

public class StringUtil {
	// ��ֳ�Э���ͷ���� ���û���ص�Э�� ��
	// Э����|�ļ���|hashֵ|�ļ���С
	// ��ѯ���е��ļ���Э���ǣ�
	// Э����|

	// ----------�ϳ��ַ���(��һ��������������Ժϳ�һ���ַ���)
	/**
	 * ���StateCode��Э����
	 * 
	 * @param s
	 * @return String
	 */
	public static String getStateCode(String s) {
		int i = s.indexOf('|');
		s = s.substring(0, i);
		return s;
	}

	/**
	 * ���StateCode��Э����֮�����Ϣ
	 * 
	 * @param s
	 * @return String
	 */
	public static String getADDInfo(String s) {
		int i = s.indexOf('|');
		s = s.substring(i + 1, s.length());
		return s;
	}

	/**
	 * ���StateCode��Э����֮�����Ϣ
	 * 
	 * @param s
	 * @return String
	 */
	public static String getList(String s) {
		int i = s.indexOf('|');
		s = s.substring(i + 1, s.length());
		return s;
	}

	/**
	 * ���StateCode��Э����֮�����Ϣ
	 * 
	 * @param s
	 * @return String
	 */
	public static String getDeleteInfo(String s) {
		int i = s.indexOf('|');
		s = s.substring(i + 1, s.length());
		return s;
	}

	/**
	 * ���StateCode��Э����֮�����Ϣ
	 * 
	 * @param s
	 * @return String
	 */
	public static String getERRORInfo(String s) {
		int i = s.indexOf('|');
		s = s.substring(i + 1, s.length());
		return s;
	}

}
