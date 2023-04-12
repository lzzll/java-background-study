package com.example.lzzll.javastudy.poi.utils;//package com.example.lzzll.poi.utils;
//
//import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
//import org.junit.Test;
//
//import javax.swing.filechooser.FileSystemView;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class TestPOI {
//
//	@Test
//	public void testExport() throws Exception{
//		ExportExcel export = new ExportExcel();
//
//		List<String[]> resource = new ArrayList<String[]>();
//		resource.add(new String[]{"文本","地址","描述","是否是父菜单","父菜单ID"});
//		resource.add(new String[]{"CRM系统管理", "null", "CRM系统顶级菜单", "T", "0"});
//		resource.add(new String[]{"系统管理", "null", "系统管理", "T", "1"});
//		resource.add(new String[]{"客户管理", "null", "客户管理", "T", "1"});
//		resource.add(new String[]{"合同管理", "null", "合同管理", "T", "1"});
//		resource.add(new String[]{"个人管理", "null", "个人管理", "T", "1"});
//		resource.add(new String[]{"用户维护", "/user", "用户维护", "F", "2"});
//		resource.add(new String[]{"用户查询", "/user", "用户查询", "F", "2"});
//		resource.add(new String[]{"修改用户密码", "/user", "修改用户密码", "F", "2"});
//		resource.add(new String[]{"客户维护", "/cus", "客户维护", "F", "3"});
//		resource.add(new String[]{"客户查询", "/cus", "客户查询", "F", "3"});
//		resource.add(new String[]{"合同维护", "/contract", "合同维护", "F", "4"});
//		resource.add(new String[]{"合同查询", "/contract", "合同查询", "F", "4"});
//		resource.add(new String[]{"查询个人信息", "/self", "查询个人信息", "F", "5"});
//		resource.add(new String[]{"修改个人信息", "/self", "修改个人信息", "F", "5"});
//		resource.add(new String[]{"修改个人密码", "/self", "修改个人密码", "F", "5"});
//
//		String filePath = FileSystemView.getFileSystemView().getHomeDirectory().getPath();
//		System.out.println(filePath);
//		OutputStream outputStream = new FileOutputStream(filePath+"/exportFile.xls");
//
//		export.exportExcel(resource, outputStream);
//
//		outputStream.flush();
//		outputStream.close();
//	}
//
//	@Test
//	public void testParse() throws Exception{
//		ParseExcel parser = new ParseExcel();
//		String filePath = FileSystemView.getFileSystemView().getHomeDirectory().getPath();
//		InputStream inputStream = new FileInputStream(filePath+"/exportFile.xls");
//		String suffix = "xls";
//		int startRow = 0;
//
//		List<String[]> result =
//				parser.parseExcel(inputStream, suffix, startRow);
//
//		for(String[] ss : result){
//			System.out.println(Arrays.toString(ss));
//		}
//	}
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
