package com.example.lzzll.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class FreemarkerApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	public void genFile() throws Exception {
		// 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
		Configuration configuration = new Configuration(Configuration.getVersion());
		// 第二步：设置模板文件所在的路径。
		File path = new File(ResourceUtils.getURL("classpath:").getPath());
		configuration.setDirectoryForTemplateLoading(new File(path.getAbsolutePath(), "templates/"));
		// 第三步：设置模板文件使用的字符集。一般就是utf-8.
		configuration.setDefaultEncoding("utf-8");
		// 第四步：加载一个模板，创建一个模板对象。
		Template template = configuration.getTemplate("test.ftl");
		// 第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
		Map dataModel = new HashMap<>();
		// 向数据集中添加数据
        // 普通文本
//		dataModel.put("name", "this is my first freemarker test.test can insert html");

        // html文本
//        dataModel.put("name", "<html><body><p style='margin-top:0pt;margin-bottom:0pt;font-size:105pt;line-height:22.72pt;font-weight:normal'><span style='font-family:Times new roman;'>test html</span></p></body></html>");

        // 包含图片和mml公式的html文本
//        dataModel.put("name", "<p style='margin-top:0pt;margin-bottom:0pt;font-size:12pt;line-height:22.72pt;font-weight:normal'><span style='font-family:宋体;'>1.如图，点<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
//                "  <mi>B</mi>\n" +
//                "</math>，<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
//                "  <mi>C</mi>\n" +
//                "</math>把<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
//                "  <mover>\n" +
//                "    <mrow>\n" +
//                "      <mi>A</mi>\n" +
//                "      <mi>D</mi>\n" +
//                "    </mrow>\n" +
//                "    <mo>&#x2322;<!-- ⌢ --></mo>\n" +
//                "  </mover>\n" +
//                "</math>分成三等份，<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
//                "  <mi>E</mi>\n" +
//                "  <mi>D</mi>\n" +
//                "</math>是⊙<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
//                "  <mi>O</mi>\n" +
//                "</math>的切线，过点<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
//                "  <mi>B</mi>\n" +
//                "</math>，<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
//                "  <mi>C</mi>\n" +
//                "</math>分别作半径的垂线段，已知<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
//                "  <mi mathvariant=\"normal\">&#x2220;<!-- ∠ --></mi>\n" +
//                "  <mi>E</mi>\n" +
//                "  <mo>=</mo>\n" +
//                "  <msup>\n" +
//                "    <mn>45</mn>\n" +
//                "    <mo>&#x2218;<!-- ∘ --></mo>\n" +
//                "  </msup>\n" +
//                "</math>，半径<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
//                "  <mi>O</mi>\n" +
//                "  <mi>D</mi>\n" +
//                "  <mo>=</mo>\n" +
//                "  <mn>1</mn>\n" +
//                "</math>，则图中阴影部分的面积是<u>&nbsp;&nbsp; &nbsp;&nbsp;</u>．\n" +
//                "</br><img src=\"http://qp-tiku.oss-cn-beijing.aliyuncs.com/ImageFile/d9baea8cac1c11e892ee00163e2ea669/03c344c8e9e3264941be41732a708a52.png\"></br></span></p>");

        // 包含表格的html文本
        dataModel.put("name","<p style='margin-top:0pt;margin-bottom:0pt;font-size:12pt;line-height:22.72pt;font-weight:normal'><span style='font-family:宋体;'>1.兴华粮食中转站仓库在<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mn>9</mn>\n" +
        "</math>月<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mn>1</mn>\n" +
        "</math>日至<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mn>9</mn>\n" +
        "</math>月<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mn>10</mn>\n" +
        "</math>日的时间内运进、运出粮食情况如下表(运进记为“+”，运出记为“-”)：<table border=\"1\" cellspacing=\"0\" cellpadding=\"0\" style=\"width: 450px\"><tbody><tr><td style=\"text-align: center;\">日期</td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mn>1</mn>\n" +
        "</math>日</td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mn>2</mn>\n" +
        "</math>日</td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mn>3</mn>\n" +
        "</math>日</td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mn>4</mn>\n" +
        "</math>日</td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mn>5</mn>\n" +
        "</math>日</td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mn>6</mn>\n" +
        "</math>日</td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mn>7</mn>\n" +
        "</math>日</td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mn>8</mn>\n" +
        "</math>日</td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mn>9</mn>\n" +
        "</math>日</td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mn>10</mn>\n" +
        "</math>日</td></tr><tr><td style=\"text-align: center;\">运进、</br>运出</br>情况</br>(吨)</td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mo>+</mo>\n" +
        "  <mn>1050</mn>\n" +
        "</math></td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mo>&#x2212;<!-- − --></mo>\n" +
        "  <mn>500</mn>\n" +
        "</math></td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mo>+</mo>\n" +
        "  <mn>2300</mn>\n" +
        "</math></td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mo>&#x2212;<!-- − --></mo>\n" +
        "  <mn>80</mn>\n" +
        "</math></td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mo>&#x2212;<!-- − --></mo>\n" +
        "  <mn>150</mn>\n" +
        "</math></td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mo>&#x2212;<!-- − --></mo>\n" +
        "  <mn>320</mn>\n" +
        "</math></td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mo>+</mo>\n" +
        "  <mn>600</mn>\n" +
        "</math></td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mo>&#x2212;<!-- − --></mo>\n" +
        "  <mn>360</mn>\n" +
        "</math></td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mo>+</mo>\n" +
        "  <mn>500</mn>\n" +
        "</math></td><td style=\"text-align: center;\"><math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mo>&#x2212;<!-- − --></mo>\n" +
        "  <mn>210</mn>\n" +
        "</math></td></tr></tbody></table> 在<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mn>9</mn>\n" +
        "</math>月<math xmlns=\"http://www.w3.org/1998/Math/MathML\" display=\"block\" >\n" +
        "  <mn>1</mn>\n" +
        "</math>日前仓库内没有粮食.</br></span></p>");

		// 第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
		Writer out = new FileWriter(new File("C:\\Users\\lf\\Desktop\\test1.docx"));
		// 第七步：调用模板对象的process方法输出文件。
		template.process(dataModel, out);
		// 第八步：关闭流。
		out.close();
	}
}
