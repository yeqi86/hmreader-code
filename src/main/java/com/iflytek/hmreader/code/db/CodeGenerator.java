package com.iflytek.hmreader.code.db;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iflytek.hmreader.code.utils.FreemarkerTool;
import com.iflytek.hmreader.code.utils.JdbcBean;
import com.iflytek.hmreader.code.utils.JdbcTool;
import freemarker.template.Template;


public class CodeGenerator {

	/**
	 * 代码工厂实例
	 * @param args
	 */
	public static void main(String[] args) {
		
		String dataBase = "***";     // 数据库名
		String username = "***";          // 数据库用户名
		String password = "****";          // 数据库密码
		String tableName = "*****"; // 表名
		String packPrefix = "com.iflytek.hmreader"; // 包名
		String dev = "cms";
		try {
			// 获取数据 pojo
			List<JdbcBean> properties = JdbcTool.readData(dataBase, username, password, tableName);
			 //获取模板文件 
			Template template = FreemarkerTool.cfg.getTemplate("po.ftl");
			String pack = packPrefix+".pojo";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("class", JdbcTool.getClassName(tableName));   //TbSysMenu
			map.put("packPrefix", packPrefix);   //com.wj.model包名
			map.put("dev", dev);//项目路径
			map.put("properties", properties);                    //数据库的字段
			map.put("Created", "qiye2");
			map.put("tableName", tableName);
			map.put("nowDate", new Date());	
			map.put("details", "功能介绍");	
			String fileName = JdbcTool.getClassName(tableName)+"Po.java";
			 FreemarkerTool.process(pack, fileName, map, template);
			 
			 //获取模板文件 vo
			 template = FreemarkerTool.cfg.getTemplate("vo.ftl");
			 pack = packPrefix+".vo";
			 fileName = JdbcTool.getClassName(tableName)+"Vo.java";
			 FreemarkerTool.process(pack, fileName, map, template);
			 
			 //获取模板文件 dto
			 template = FreemarkerTool.cfg.getTemplate("dto.ftl");
			 pack = packPrefix+".dto";
			 fileName = JdbcTool.getClassName(tableName)+"Dto.java";
			 FreemarkerTool.process(pack, fileName, map, template);

			//service
			 pack = packPrefix+".service";
			 fileName = JdbcTool.getClassName(tableName)+"Service.java";
			 template = FreemarkerTool.cfg.getTemplate("service.ftl");
			 FreemarkerTool.process(pack, fileName, map, template);

			//service.impl
			 pack = packPrefix+".service.impl";
			 fileName = JdbcTool.getClassName(tableName)+"ServiceImpl.java";
			 template = FreemarkerTool.cfg.getTemplate("serviceImpl.ftl");
			 FreemarkerTool.process(pack, fileName, map, template);

			//controller
			 pack = packPrefix+".controller";
			 fileName = JdbcTool.getClassName(tableName)+"Controller.java";
			 template = FreemarkerTool.cfg.getTemplate("controller.ftl");
			 FreemarkerTool.process(pack, fileName, map, template);

			//map
			 pack = packPrefix+".map";
			 fileName = JdbcTool.getClassName(tableName)+"Mapper.java";
			 template = FreemarkerTool.cfg.getTemplate("mapper.ftl");
			 FreemarkerTool.process(pack, fileName, map, template);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
