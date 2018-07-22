package test;

import org.junit.Before;
import org.junit.Test;

import tools.StringUtil;

public class UtilsTest {

	@Before
	public void setUp() throws Exception {
		// 初始化数据
	}

	@Test
	public void testGetStateCode() {
		// System.out.println("成功！");
		String request = "ADD|1.txt|78787|23";
		String result = StringUtil.getStateCode(request);
		System.out.println(result);
	}

	@Test
	public void testGetADDInfo() {
		String request = "ADD|1.txt|78787|23";
		String result = StringUtil.getADDInfo(request);
		System.out.println(result);
	}

	@Test
	public void testGetList() {
		String request = "LIST|kg";
		String result = StringUtil.getList(request);
		System.out.println(result);
	}

	@Test
	public void testGetDeleteInfo() {
		String request = "DELETE|1.txt";
		String result = StringUtil.getDeleteInfo(request);
		System.out.println(result);
	}

	@Test
	public void testGetERRORInfo() {
		String request = "ERROR|kdjfk";
		String result = StringUtil.getERRORInfo(request);
		System.out.println(result);
	}

}
