package com.iflytek.hmreader.code.frame;

import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.iflytek.hmreader.code.utils.FreemarkerTool;
import com.iflytek.hmreader.code.utils.JdbcBean;
import com.iflytek.hmreader.code.utils.JdbcTool;
import freemarker.template.Template;
import freemarker.template.TemplateException;




public class GridbagFrame extends JFrame {
	private JLabel jlStatus,jlDataBase,jlUsername,jlPassword,jlTableName,jlPackPrefix,jlCreated,jlPath;
	private JTextField jtfDataBase = new JTextField(),
			jtfUsername = new JTextField(),
			jtfPassword = new JTextField(),
			jtfTableName = new JTextField(),
			jtfPackPrefix = new JTextField(),
			jtfCreated = new JTextField(),
			jtfPath = new JTextField() ;
	private JScrollPane jspStatus = new JScrollPane();
	private JTextArea tStatusInfo = new JTextArea();
	private JButton jb = new JButton("执行");
	
	public GridbagFrame()
	{
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("代码生成器");
		setResizable(false);
		jtfDataBase.setColumns(8);
		jtfCreated.setColumns(8);
		jlStatus = new JLabel("运行结果：");
		jlDataBase = new JLabel("mysql库名为：");
		jlUsername = new JLabel("数据库用户名：");
		jlPassword = new JLabel("数据库密码：");
		jlTableName = new JLabel("数据库表名：");
		jlPackPrefix = new JLabel("项目包名：");
		jlCreated = new JLabel("创建人：");
		jlPath = new JLabel("文件存储路径：");
		tStatusInfo.setColumns(20);
		tStatusInfo.setRows(5);
		jspStatus.setViewportView(tStatusInfo);
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbActionPerformed(evt);
			}
		});
		add(jlStatus, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1).setInsets(5));
		add(jspStatus, new GBC(0, 1, 4, 1).setFill(GBC.BOTH).setWeight(1, 1).setInsets(5));
		
		add(jlDataBase, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1).setInsets(5));
		add(jtfDataBase, new GBC(1, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1).setInsets(5));
		add(jlCreated, new GBC(2, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1).setInsets(5));
		add(jtfCreated, new GBC(3, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1).setInsets(5));
		
		add(jlUsername, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1).setInsets(5));
		add(jtfUsername, new GBC(1, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1).setInsets(5));
		add(jlPassword, new GBC(2, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1).setInsets(5));
		add(jtfPassword, new GBC(3, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1).setInsets(5));
		
		add(jlTableName, new GBC(0, 4, 1, 1).setFill(GBC.BOTH).setWeight(1, 1).setInsets(5));
		add(jtfTableName, new GBC(1, 4, 1, 1).setFill(GBC.BOTH).setWeight(1, 1).setInsets(5));
		add(jlPackPrefix, new GBC(2, 4, 1, 1).setFill(GBC.BOTH).setWeight(1, 1).setInsets(5));
		add(jtfPackPrefix, new GBC(3, 4, 1, 1).setFill(GBC.BOTH).setWeight(1, 1).setInsets(5));
		
		add(jlPath, new GBC(0, 5, 1, 1).setFill(GBC.BOTH).setWeight(1, 1).setInsets(5));
		add(jtfPath, new GBC(1, 5, 1, 1).setFill(GBC.BOTH).setWeight(1, 1).setInsets(5));
		add(jb, new GBC(2, 5, 2, 1).setFill(GBC.CENTER).setWeight(1, 1).setInsets(5));
		
	}
	
	private void jbActionPerformed(java.awt.event.ActionEvent evt) {
		String dataBase = jtfDataBase.getText().trim();
		if (dataBase == null ||"".equals(dataBase)) {
			JOptionPane.showMessageDialog(null, "mysql库名不能为空" ,"提示", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String username = jtfUsername.getText().trim();
		if (username == null ||"".equals(username)) {
			JOptionPane.showMessageDialog(null, "数据库用户名不能为空" ,"提示", JOptionPane.WARNING_MESSAGE);
			return;
		}
		String password = jtfPassword.getText().trim();
		if (password == null ||"".equals(password)) {
			JOptionPane.showMessageDialog(null, "数据库密码不能为空" ,"提示", JOptionPane.WARNING_MESSAGE);
			return;
		}
		String tableName = jtfTableName.getText().trim();
		if (tableName == null ||"".equals(tableName)) {
			JOptionPane.showMessageDialog(null, "数据库表名不能为空" ,"提示", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String path = jtfPath.getText().trim();
		if (path == null ||"".equals(path)) {
			JOptionPane.showMessageDialog(null, "文件生成目录不能为空" ,"提示", JOptionPane.WARNING_MESSAGE);
			return;
		}
		String created = jtfCreated.getText().trim();
		if (created == null ||"".equals(created)) {
			created = "Administrator";
		}
		String packPrefix = jtfPackPrefix.getText().trim();
		if (packPrefix == null ||"".equals(packPrefix)) {
			packPrefix ="team";
		}
		List<JdbcBean> properties = JdbcTool.readData(dataBase, username, password, tableName);
		if(properties == null || properties.size() == 0) {
			tStatusInfo.append("数据库读取异常，可能是库名、表名、用户名或者密码错误\n");
		}
		tStatusInfo.append("数据库读取成功\n");
		Template template = null;
		try {
			template = FreemarkerTool.cfg.getTemplate("model.ftl");
		} catch (IOException e) {
			tStatusInfo.append("读取模板失败\n");
			e.printStackTrace();
			return;
		}
		String pack = packPrefix+".domain";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("class", JdbcTool.getClassName(tableName).substring(2));   //TbSysMenu
		map.put("packPrefix", packPrefix);                    //com.wj.model
		map.put("properties", properties);                    //数据库的字段
		map.put("Created", created);
		map.put("tableName", tableName);
		map.put("nowDate", new Date());	
		String fileName = JdbcTool.getClassName(tableName).substring(2)+".java";
		 try {
			FreemarkerTool.process("utf-8",pack, fileName,path, map, template);
		} catch (Exception e) {
			tStatusInfo.append("实体对象生成失败\n");
			return;
		}
		tStatusInfo.append("实体对象生成成功\n");
		
		 pack = packPrefix+".dao";
		 fileName = JdbcTool.getClassName(tableName).substring(2)+"Dao.java";
		 try {
			template = FreemarkerTool.cfg.getTemplate("dao.ftl");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			tStatusInfo.append("读取模板失败\n");
			e1.printStackTrace();
		}
		 try {
				FreemarkerTool.process("utf-8",pack, fileName,path, map, template);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			tStatusInfo.append("dao对象生成失败\n");
			e.printStackTrace();
		} 
		 tStatusInfo.append("dao对象生成成功\n");
		 
		 pack = packPrefix+".service";
		 fileName = JdbcTool.getClassName(tableName).substring(2)+"Service.java";
		 try {
			template = FreemarkerTool.cfg.getTemplate("service.ftl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			tStatusInfo.append("读取模板失败\n");
			e.printStackTrace();
		}
		 try {
			 FreemarkerTool.process("utf-8",pack, fileName,path, map, template);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			tStatusInfo.append("service对象生成失败\n");
			e.printStackTrace();
		} 
		 tStatusInfo.append("service对象生成成功\n");
		 
		 pack = packPrefix+".service.impl";
		 fileName = JdbcTool.getClassName(tableName).substring(2)+"ServiceImpl.java";
		 try {
			template = FreemarkerTool.cfg.getTemplate("serviceImpl.ftl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			tStatusInfo.append("读取模板失败\n");
			e.printStackTrace();
		}
		 try {
			FreemarkerTool.process("utf-8",pack, fileName,path, map, template);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			tStatusInfo.append("serviceImpl对象生成失败\n");
			e.printStackTrace();
		} 
		 tStatusInfo.append("serviceImpl对象生成成功\n");
		 
		 pack = packPrefix+".controller.admin";
		 fileName = JdbcTool.getClassName(tableName).substring(2)+"Controller.java";
		 try {
			template = FreemarkerTool.cfg.getTemplate("controller.ftl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			tStatusInfo.append("读取模板失败\n");
			e.printStackTrace();
		}
		 try {
			FreemarkerTool.process("utf-8",pack, fileName,path, map, template);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			tStatusInfo.append("controller对象生成失败\n");
			e.printStackTrace();
		}
		 tStatusInfo.append("controller对象生成成功\n");
		 
		 pack = packPrefix+"jsp";
		 fileName = "index.jsp";
		 try {
			template = FreemarkerTool.cfg.getTemplate("index.ftl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			tStatusInfo.append("读取模板失败\n");
			e.printStackTrace();
		}
		 try {
			FreemarkerTool.process("utf-8",pack, fileName,path, map, template);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			tStatusInfo.append("index.jsp对象生成失败\n");
			e.printStackTrace();
		}
		 tStatusInfo.append("index.jsp对象生成成功\n");
		 
		 pack = packPrefix+"jsp";
		 fileName = "form.jsp";
		 try {
			template = FreemarkerTool.cfg.getTemplate("form.ftl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			tStatusInfo.append("读取模板失败\n");
			e.printStackTrace();
		}
		 try {
			FreemarkerTool.process("utf-8",pack, fileName,path, map, template);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			tStatusInfo.append("form.jsp对象生成失败\n");
			e.printStackTrace();
		} 
		 tStatusInfo.append("form.jsp对象生成成功\n");
		 
		 JdbcBean jdbcBean = null;
			String id = null;
			for(int i = 0 ; i<properties.size() ;i ++) {
				jdbcBean = properties.get(i);
				id = jdbcBean.getFieldNameReal();
				if("id".equals(id)) {
					properties.remove(i);
					break;
				}
			}
			map.put("properties", properties);   
			map.put("JdbcId", jdbcBean);   
		 pack = packPrefix+".map";
		 fileName = JdbcTool.getClassName(tableName).substring(2)+"Mapper.xml";
		 try {
			template = FreemarkerTool.cfg.getTemplate("mapper.ftl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			tStatusInfo.append("读取模板失败\n");
			e.printStackTrace();
		}
		 try {
			FreemarkerTool.process("utf-8",pack, fileName,path, map, template);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			tStatusInfo.append("Mapper.xml对象生成失败\n");
			e.printStackTrace();
		}
		 tStatusInfo.append("Mapper.xml对象生成成功\n");
		 tStatusInfo.append("结束");
	}
	
	public static void main(String[] args){
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GridbagFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GridbagFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GridbagFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GridbagFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new GridbagFrame();
				frame.pack();
				frame.setSize(500,360);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			}
		});
	}

}
