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
	 * ���StateCode��Э����
	 * 
	 * @param s
	 * @return
	 */
	public static String getADDInfo(String s) {

		return s;
	}

}
