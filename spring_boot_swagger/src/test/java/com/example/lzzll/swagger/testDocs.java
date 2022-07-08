package com.example.lzzll.swagger;

import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.nio.file.Paths;

@SpringBootTest(classes = SwaggerApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class testDocs
{
	// 群组名称
	 final String group = "my";


	/**
	 * 生成md格式文档
	 * @throws Exception
	 */
	@Test
	public void generateMarkdownFile() throws Exception {
		Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
				// 指定文件格式为markdown
				.withMarkupLanguage(MarkupLanguage.ASCIIDOC)
				// 指定语言为中文
				.withOutputLanguage(Language.ZH)
				// 生成参数示例
				.withGeneratedExamples()
				// 生成请求体
				.withFlatBody()
				.withoutInlineSchema()
				.build();
		// 获取接口数据的url，请求方式是get，若没有分组则不需要group参数
		URL apiUrl = new URL("http://localhost:8000/v2/api-docs?group="+group);

		// 指定文件名称
		String markdownFileName = "src/docs/markdown/generated/api";

		// 指定获取接口数据的url
		Swagger2MarkupConverter.from(apiUrl)
				// 指定配置信息
				.withConfig(config)

				.build()
				//指定生成目录下生成指定文件
				.toFile(Paths.get(markdownFileName));
	}
}
