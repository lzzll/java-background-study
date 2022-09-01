package com.example.lzzll.shiro.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShiroConfig {

	/**
	 * 无需认证就可以访问
	 */
	private final static String ANON = "anon";
	/**
	 * 必须认证了才能访问
	 */
	private final static String AUTHC = "authc";
	/**
	 * 拥有对某个资源的权限才能访问
	 */
	private final static String PERMS = "perms";

	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 过滤器
//		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
		// 可以将自定义的过滤器注入shiroFilter中
//		filters.put("authc", new CustomFormAuthenticationFilter());

		// 拦截器
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
		// 配置不会被拦截的链接 顺序判断
		filterChainDefinitionMap.put("/static/**", ANON);
		filterChainDefinitionMap.put("/user/register", ANON);
		//配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		// 配置role路径的资源需要做权限认证
		filterChainDefinitionMap.put("/role", PERMS);
		//<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		//<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", AUTHC);
		// 前后端不分离时写法，对应HomeController中的login接口，如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 前后端分离时如何自定义登录接口
//		shiroFilterFactoryBean.setLoginUrl("/user/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");

		//未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * 凭证匹配器
	 * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
	 * ）
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
		return hashedCredentialsMatcher;
	}

	@Bean
	public MyShiroRealm myShiroRealm(){
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}


	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		return securityManager;
	}

	/**
	 *  开启shiro aop注解支持.
	 *  使用代理方式;所以需要开启代码支持;
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean(name="simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver
	createSimpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
		mappings.setProperty("UnauthorizedException","403");
		r.setExceptionMappings(mappings);  // None by default
		r.setDefaultErrorView("error");    // No default
		r.setExceptionAttribute("ex");     // Default is "exception"
		//r.setWarnLogCategory("example.MvcLogger");     // No default
		return r;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "%5B%7B%22id%22%3A1002215%2C%22levelSort%22%3A%221%22%2C%22question%22%3A%7B%22questionId%22%3A%225423f9ae-246e-477f-9e40-4f2ea30cf8e8%22%2C%22batchId%22%3A8206%2C%22questionStructure%22%3A%2217%22%2C%22questionType%22%3A%221712%22%2C%22isTitleTopicQuoted%22%3A0%2C%22stageSubject%22%3A%221313%22%2C%22belongSingle%22%3A%22%7B%7D%22%2C%22belongType%22%3A3%2C%22belongPage%22%3A0%2C%22sourceType%22%3A6%2C%22sourceSystem%22%3A4%2C%22score%22%3A0%2C%22context%22%3A%22%7B%5C%22stem%5C%22%3A+%5C%22%E9%A2%84%E5%A4%84%E7%90%86%E7%B3%BB%E7%BB%9F%E5%BD%95%E7%9A%84%E5%90%8C%E4%B8%80%E7%9F%A5%E8%AF%86%E7%82%B9%E8%AF%95%E9%A2%98%5C%22%7D%22%2C%22tags%22%3A%22%7B%5C%22attrs%5C%22%3A+%7B%5C%22100186%5C%22%3A+%5C%224%5C%22%2C+%5C%22100190%5C%22%3A+%5B%5C%2212%5C%22%5D%2C+%5C%22100198%5C%22%3A+%7B%5C%22master%5C%22%3A+%5B%5C%2213111111%5C%22%2C+%5C%2213111112%5C%22%2C+%5C%221411111111%5C%22%5D%7D%7D%7D%22%2C%22isOnebymore%22%3A0%2C%22isRepeat%22%3A0%2C%22repeatFullId%22%3A0%2C%22isAnalysisReplaced%22%3A0%2C%22questionSort%22%3A1%2C%22createrGuid%22%3A121003640%2C%22companyId%22%3A100007%2C%22deptId%22%3A1000033%2C%22questionState%22%3A13%2C%22preBatchId%22%3A6486%2C%22id%22%3A1002215%2C%22isCanPointQues%22%3A0%7D%2C%22explain%22%3A%5B%7B%22attrId%22%3A322735%2C%22mainId%22%3A1002215%2C%22sortNum%22%3A1%2C%22questionId%22%3A%225423f9ae-246e-477f-9e40-4f2ea30cf8e8%22%2C%22isAttrsQuoted%22%3A0%7D%5D%7D%2C%7B%22id%22%3A1002227%2C%22levelSort%22%3A%221.1%22%2C%22question%22%3A%7B%22questionId%22%3A%2224f7c21c-8630-4c90-901d-db9625fd3981%22%2C%22batchId%22%3A8206%2C%22questionStructure%22%3A%2211%22%2C%22questionType%22%3A%222323%22%2C%22isTitleTopicQuoted%22%3A0%2C%22stageSubject%22%3A%221313%22%2C%22belongType%22%3A3%2C%22belongPage%22%3A0%2C%22sourceType%22%3A6%2C%22sourceSystem%22%3A4%2C%22score%22%3A0%2C%22context%22%3A%22%7B%5C%22stem%5C%22%3A+%5C%22%E7%AC%AC%E4%B8%80%E5%B0%8F%E9%A2%98%E9%A2%98%E5%B9%B2%5C%22%2C+%5C%22options%5C%22%3A+%5B%5C%22aaa%5C%22%2C+%5C%22bbb%5C%22%2C+%5C%22ccc%5C%22%2C+%5C%22ddd%5C%22%5D%7D%22%2C%22isOnebymore%22%3A0%2C%22isRepeat%22%3A0%2C%22repeatFullId%22%3A0%2C%22isAnalysisReplaced%22%3A0%2C%22parentQuestionId%22%3A%225423f9ae-246e-477f-9e40-4f2ea30cf8e8%22%2C%22questionSort%22%3A1%2C%22createrGuid%22%3A121010966%2C%22deptId%22%3A1000033%2C%22questionState%22%3A0%2C%22id%22%3A1002227%7D%2C%22explain%22%3A%5B%7B%22attrId%22%3A322747%2C%22mainId%22%3A1002227%2C%22answers%22%3A%22%5B%5B%5C%22A%5C%22%5D%5D%22%2C%22attrs%22%3A%22%7B%5C%22100186%5C%22%3A+%5C%224%5C%22%2C+%5C%22100190%5C%22%3A+%5B%5C%2212%5C%22%5D%2C+%5C%22100198%5C%22%3A+%7B%5C%22master%5C%22%3A+%5B%5C%2213111111%5C%22%2C+%5C%2213111112%5C%22%2C+%5C%221411111111%5C%22%5D%7D%7D%22%2C%22sortNum%22%3A1%2C%22questionId%22%3A%2224f7c21c-8630-4c90-901d-db9625fd3981%22%2C%22isAttrsQuoted%22%3A0%7D%5D%2C%22children%22%3A%5B%5D%7D%2C%7B%22id%22%3A1002228%2C%22levelSort%22%3A%221.2%22%2C%22question%22%3A%7B%22questionId%22%3A%220f60a819-4d80-4b3d-950a-947871911ddb%22%2C%22batchId%22%3A8206%2C%22questionStructure%22%3A%2211%22%2C%22questionType%22%3A%222323%22%2C%22isTitleTopicQuoted%22%3A0%2C%22stageSubject%22%3A%221313%22%2C%22belongType%22%3A3%2C%22belongPage%22%3A0%2C%22sourceType%22%3A6%2C%22sourceSystem%22%3A4%2C%22score%22%3A0%2C%22context%22%3A%22%7B%5C%22stem%5C%22%3A+%5C%22%E7%AC%AC%E4%BA%8C%E5%B0%8F%E9%A2%98%E9%A2%98%E5%B9%B2%5C%22%2C+%5C%22options%5C%22%3A+%5B%5C%22111%5C%22%2C+%5C%22222%5C%22%2C+%5C%22333%5C%22%2C+%5C%22444%5C%22%5D%7D%22%2C%22isOnebymore%22%3A0%2C%22isRepeat%22%3A0%2C%22repeatFullId%22%3A0%2C%22isAnalysisReplaced%22%3A0%2C%22parentQuestionId%22%3A%225423f9ae-246e-477f-9e40-4f2ea30cf8e8%22%2C%22questionSort%22%3A2%2C%22createrGuid%22%3A121010966%2C%22deptId%22%3A1000033%2C%22questionState%22%3A0%2C%22id%22%3A1002228%7D%2C%22explain%22%3A%5B%7B%22attrId%22%3A322748%2C%22mainId%22%3A1002228%2C%22answers%22%3A%22%5B%5B%5C%22B%5C%22%5D%5D%22%2C%22attrs%22%3A%22%7B%5C%22100186%5C%22%3A+%5C%224%5C%22%2C+%5C%22100190%5C%22%3A+%5B%5C%2212%5C%22%5D%2C+%5C%22100198%5C%22%3A+%7B%5C%22master%5C%22%3A+%5B%5C%2213111111%5C%22%2C+%5C%2213111112%5C%22%2C+%5C%221411111111%5C%22%5D%7D%7D%22%2C%22sortNum%22%3A1%2C%22questionId%22%3A%220f60a819-4d80-4b3d-950a-947871911ddb%22%2C%22isAttrsQuoted%22%3A0%7D%5D%2C%22children%22%3A%5B%5D%7D%5D";
		String decode = URLDecoder.decode(str);
		System.out.println(decode);

		String str1 = "[\n" +
				"    {\n" +
				"        \"currentTimeMillis\":1658135487551,\n" +
				"        \"explain\":[\n" +
				"            {\n" +
				"                \"analysis\":\"&lt;p&gt;因为&lt;img class=\\\"math-tex\\\" latexdata=\\\"(x%203)\\\" style=\\\"vertical-align:-5.88px\\\" src=\\\"http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/latex2svg/041f561f2e25a42279b37d2d773a76f8.svg\\\" /&gt;&lt;i&gt;&lt;img class=\\\"math-tex\\\" latexdata=\\\"%3D%7Bx%7D%20\\\" style=\\\"vertical-align:-1.98px\\\" src=\\\"http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/latex2svg/dc15edacbedc7efb82b51290e9231778.svg\\\" /&gt;&lt;/i&gt;3,所以&lt;img class=\\\"math-tex\\\" latexdata=\\\"(x%203)\\\" style=\\\"vertical-align:-5.88px\\\" src=\\\"http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/latex2svg/041f561f2e25a42279b37d2d773a76f8.svg\\\" /&gt;&lt;i&gt;-&lt;/i&gt;&lt;img class=\\\"math-tex\\\" latexdata=\\\"(x%203)(2x%205)\\\" style=\\\"vertical-align:-5.88px\\\" src=\\\"http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/latex2svg/f2f0156e5072720972a6c8b357c2b772.svg\\\" /&gt;&lt;i&gt;=&lt;/i&gt;0,则&lt;img class=\\\"math-tex\\\" latexdata=\\\"x%203\\\" style=\\\"vertical-align:-1.98px\\\" src=\\\"http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/latex2svg/3d34dae8f8a5e5cfe821ea51f4a4d3d0.svg\\\" /&gt;&lt;i&gt;=&lt;/i&gt;0,所以&lt;img class=\\\"math-tex\\\" latexdata=\\\"2x%205\\\" style=\\\"vertical-align:-1.98px\\\" src=\\\"http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/latex2svg/1b4b7453d4ea0412da2b1c67ccb2e614.svg\\\" /&gt;&lt;i&gt;=&lt;/i&gt;0或&lt;img class=\\\"math-tex\\\" latexdata=\\\"x_%7B1%7D\\\" style=\\\"vertical-align:-3.93px\\\" src=\\\"http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/latex2svg/f9a3b8e9e501458e8face47cae8826de.svg\\\" /&gt;&lt;i&gt;=&lt;/i&gt;0,解得&lt;img class=\\\"math-tex\\\" latexdata=\\\"%3D-\\\" style=\\\"vertical-align:-2.96px\\\" src=\\\"http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/latex2svg/838978f13655c6a279a2b27b71da411d.svg\\\" /&gt;&lt;i&gt;&lt;img class=\\\"math-tex\\\" latexdata=\\\"x_%7B2%7D\\\" style=\\\"vertical-align:-3.93px\\\" src=\\\"http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/latex2svg/8f43fce8dbdf3c4f8d0ac91f0de1d43d.svg\\\" /&gt;&lt;/i&gt;3,$$x_{2}$$&lt;i&gt;&lt;img class=\\\"math-tex\\\" latexdata=\\\"x_%7B2%7D\\\" style=\\\"vertical-align:-3.93px\\\" src=\\\"http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/latex2svg/8f43fce8dbdf3c4f8d0ac91f0de1d43d.svg\\\" /&gt;&lt;/i&gt;2.5&lt;i&gt;.&lt;/i&gt;&lt;/p&gt;\",\n" +
				"                \"answers\":\"[[\\\"&lt;img class=\\\\\\\"math-tex\\\\\\\" latexdata=\\\\\\\"%5Cmathit%7B%20%7B%5Cmathit%7Bx%7D%7D%7D_1\\\\\\\" style=\\\\\\\"vertical-align:-3.93px\\\\\\\" src=\\\\\\\"http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/latex2svg/15acbb1b2accf541c23550224f71c057.svg\\\\\\\" /&gt;&lt;i&gt;&lt;img class=\\\\\\\"math-tex\\\\\\\" latexdata=\\\\\\\"%3D-\\\\\\\" style=\\\\\\\"vertical-align:-2.96px\\\\\\\" src=\\\\\\\"http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/latex2svg/838978f13655c6a279a2b27b71da411d.svg\\\\\\\" /&gt;&lt;/i&gt;3,&lt;img class=\\\\\\\"math-tex\\\\\\\" latexdata=\\\\\\\"x_%7B2%7D\\\\\\\" style=\\\\\\\"vertical-align:-3.93px\\\\\\\" src=\\\\\\\"http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/latex2svg/8f43fce8dbdf3c4f8d0ac91f0de1d43d.svg\\\\\\\" /&gt;&lt;i&gt;&lt;img class=\\\\\\\"math-tex\\\\\\\" latexdata=\\\\\\\"%3D-\\\\\\\" style=\\\\\\\"vertical-align:-2.96px\\\\\\\" src=\\\\\\\"http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/latex2svg/838978f13655c6a279a2b27b71da411d.svg\\\\\\\" /&gt;&lt;/i&gt;2&lt;i&gt;.&lt;/i&gt;5\\\"]]\",\n" +
				"                \"questionId\":\"33f7c60e-229b-489f-9ef7-162a8cbdc1f7\",\n" +
				"                \"sortNum\":1\n" +
				"            }\n" +
				"        ],\n" +
				"        \"levelSort\":\"1\",\n" +
				"        \"question\":{\n" +
				"            \"batchId\":0,\n" +
				"            \"belongType\":3,\n" +
				"            \"companyId\":100007,\n" +
				"            \"context\":\"{\\\"stem\\\":\\\"&lt;p&gt;方程&lt;img class=\\\\\\\"math-tex\\\\\\\" latexdata=\\\\\\\"2(x%203)%5E%7B2%7D\\\\\\\" style=\\\\\\\"vertical-align:-5.88px\\\\\\\" src=\\\\\\\"http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/latex2svg/1f50de84ff4e100cd6df7eae4ff8fe44.svg\\\\\\\" /&gt;&lt;i&gt;&lt;img class=\\\\\\\"math-tex\\\\\\\" latexdata=\\\\\\\"%3D%7Bx%7D%20\\\\\\\" style=\\\\\\\"vertical-align:-1.98px\\\\\\\" src=\\\\\\\"http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/latex2svg/dc15edacbedc7efb82b51290e9231778.svg\\\\\\\" /&gt;&lt;/i&gt;3的解为&lt;span data-label=\\\\\\\"blank\\\\\\\" data-blank-length=\\\\\\\"2\\\\\\\"&gt;&lt;/span&gt;.&lt;/p&gt;\\\"}\",\n" +
				"            \"createrGuid\":121025833,\n" +
				"            \"deptId\":1000227,\n" +
				"            \"isAnalysisReplaced\":0,\n" +
				"            \"isOnebymore\":0,\n" +
				"            \"isRepeat\":0,\n" +
				"            \"questionFromCode\":\"15\",\n" +
				"            \"questionId\":\"33f7c60e-229b-489f-9ef7-162a8cbdc1f7\",\n" +
				"            \"questionState\":1,\n" +
				"            \"questionStructure\":\"1315\",\n" +
				"            \"questionType\":\"13\",\n" +
				"            \"repeatFullId\":0,\n" +
				"            \"score\":0,\n" +
				"            \"sourceSystem\":4,\n" +
				"            \"sourceType\":6,\n" +
				"            \"stageSubject\":\"1311\"\n" +
				"        }\n" +
				"    }\n" +
				"]";
		String encode = URLEncoder.encode(str,"UTF-8");
		System.out.println(encode);
	}
}