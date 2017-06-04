package com.iflytek.hmreader.code.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Freemarker 模版引擎类
 * Created: zhangj on 2016/10/28
 * @versionv 1.0
 */
public class FreemarkerTool {
	public static Configuration cfg = new Configuration();
	static{
		cfg.setEncoding(Locale.CHINA, "utf-8");
		try {
			cfg.setDirectoryForTemplateLoading(new File("src\\main\\resources\\template").getAbsoluteFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cfg.setObjectWrapper(new DefaultObjectWrapper());
	}
	/**
	 * 配置引擎参数
	 * @param code 编码
	 * @param path 路径
	 * @return Configuration
	 * @throws IOException
	 */
	public static Configuration getConfiguration (String code ,String path) throws IOException {
		Configuration cfg = new Configuration();
		cfg.setEncoding(Locale.CHINA, code);
		cfg.setDirectoryForTemplateLoading(new File(path));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
        return cfg;
	}
	
	/**
	 * 加载模板输出文件
	 * @param pack
	 * @param fileName
	 * @param path
	 * @param map
	 * @param template
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void process (String code ,String pack ,String fileName, String path, Map<String, Object> map, Template template) throws IOException, TemplateException {
		//生成输出到文件 
		String root = genPackStr(path,pack);
		File fileDir = new File(root);
		// 创建文件夹，不存在则创建
		FileUtils.forceMkdir(fileDir);
		// 指定生成输出的文件
		File file = new File(fileDir + "/"+fileName);
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), code));
		template.process(map, writer);
		writer.close();
	}
	
	/**
	 * 加载模板输出字符串

	 * @param map
	 * @param template
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void process (Map<String, Object> map, Template template) throws IOException, TemplateException {
		StringWriter result = new StringWriter();  
	    template.process(map, result);  
		String fileValue = result.toString();
		System.out.println(fileValue);
		result.close();
	}
	
	public static void process (String pack ,String fileName, Map<String, Object> map, Template template) throws IOException, TemplateException {
		process( "utf-8",pack ,fileName, "src", map, template);
		// 生成输出到控制台 
			/*Writer out = new OutputStreamWriter(System.out);
			template.process(map, out);
			out.flush();*/
	}
	
	
	/**
	 * 根据包名获取对应的路径名
	 * @param root 根路径
	 * @param pack 包名
	 * @return
	 */
	public static String genPackStr(String root,String pack){
		String result = root;
		String [] dirs = pack.split("\\.");
		for(String dir : dirs){
			result += "/"+dir;
		}
		return result;
	}
}
