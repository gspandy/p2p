package com.platform.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class CreateCodeUtil {

	// ---------------------------------------------------------
	static String ProjectPath = "D:/workspace/publish";// 项目目录
	
	static String table;
	static String fields;
	// ---------------------------------------------------------
	
	// 在sqlmap.xml中，添加SqlMap文件
	// 在system.xml中，添加扫描目录
	// 普通长度：128	fields：1024		内容：10240
	
	/*
	 * ===========================更新日志===========================
	 * 2012.03.04
	 * Spring2.5 + Struts2.2 + Ibatis2.3 + Freemarker2.3 + Ehcache1.6 + Spring注解 + Strust注解
	 * 2012.03.18
	 * 增加UserRight.RESULT
	 * 2012.03.30
	 * 包名解析
	 * 2012.04.02
	 * 单独生成action和ftl文件夹
	 * 2012.04.25
	 * 文件结构调整
	 * 2014.06.10
	 * 生成完整AdminAction
	 * 2015.08.11
	 * 简化Dao
	 */
	
	static String AppName, FilePath, ClassName, Package, MetaPath;
	static String includeTypes = "Long,String,Date,Byte,Integer,Double";

	static Map<String, String> fieldMap = new HashMap<String, String>();

	public static void main(String[] args) throws Exception {
		String[] tabless = new String[1];
		String[] fieldss = new String[1];
		
		tabless[0] = "cslc_systemlog";
		fieldss[0] = "createtime:Date,content:String,category:Byte,accountid:Long";
		
		for(int i = 0; i < tabless.length; i ++){
			table = tabless[i];
			fields = fieldss[i];
			
			System.out.println("开始创建");
			try {
				init();
			} catch (Exception e) {
				System.out.println("初始化错误,请重试..");
				return;
			}

			createSqlMap();
			createEntity();
			createDao();
//			createDaoWithCache();
			createSql();
			
//			createAdminAction();
//			createAppAction();
//			createFtlFolder();
			
			System.out.println("创建完毕");
			System.out.println(FilePath);
		}
		
		return;
	}

	static void init() {
		fieldMap = new HashMap<String, String>();
		
		// 表
		String t = "";
		String[] str = table.split("_");
		if (str != null) {
			int i = 0;
			for (String s : str) {
				if (i > 0) {
					t += s.replaceFirst(s.substring(0, 1), s.substring(0, 1).toUpperCase());
				} else {
					Package = s;
				}
				i++;
			}
			ClassName = t;
		}
		AppName = str[0];
		FilePath = ProjectPath + "/src/com/" + AppName + "/dao/" + ClassName.toLowerCase();
		Package = FilePath.substring(FilePath.indexOf("/com/") + 1).replace("/", ".");
		MetaPath = Package + "." + ClassName;

		// 字段
		String[] str2 = fields.split(",");
		if (str2 != null) {
			for (String s : str2) {
				String t3 = "";
				String t4 = "";
				String[] str3 = s.split(":")[0].split("_");
				if (str3 != null) {
					int i = 0;
					for (String s2 : str3) {
						if (i == 0) {
							t3 += s2;
						} else {
							t3 += s2.replaceFirst(s2.substring(0, 1), s2.substring(0, 1).toUpperCase());
						}
						t4 += s2.replaceFirst(s2.substring(0, 1), s2.substring(0, 1).toUpperCase());
						i++;
					}
					fieldMap.put(s.split(":")[0], s.split(":")[1] + ":" + t3 + ":" + t4);
				}
			}
		}

		// 校验类型
		for (Entry<String, String> e : fieldMap.entrySet()) {
			boolean exist = false;
			String[] arr = includeTypes.split(",");
			for (String s : arr) {
				if (s.equals(getType(e.getValue()))) {
					exist = true;
					break;
				}
			}
			if (exist) {
				continue;
			} else {
				System.out.println("只允许" + includeTypes + ",请重试..");
				System.exit(0);
				break;
			}
		}
	}

	static String getType(String s) {
		return s.split(":")[0];
	}

	static String getField(String s) {
		return s.split(":")[1];
	}

	static String getSetterName(String s) {
		return "set" + s.split(":")[2];
	}

	static String getGetterName(String s) {
		return "get" + s.split(":")[2];
	}

	static String getDBType(Entry<String, String> e) {
		String type = "";
		if ("Byte".equals(getType(e.getValue()))) {
			type = "TINYINT";
		} else if ("Integer".equals(getType(e.getValue()))) {
			type = "INT";
		} else if ("Long".equals(getType(e.getValue()))) {
			type = "BIGINT";
		} else if ("String".equals(getType(e.getValue()))) {
			type = "VARCHAR";
		} else if ("Date".equals(getType(e.getValue()))) {
			type = "DATETIME";
		} else if ("Double".equals(getType(e.getValue()))) {
			type = "DECIMAL";
		}
		return type;
	}

	static String getSQLType(Entry<String, String> e) {
		String s = "";
		if ("Byte".equals(getType(e.getValue()))) {
			s = "tinyint(2)";
		} else if ("Integer".equals(getType(e.getValue()))) {
			s = "int(10)";
		} else if ("Long".equals(getType(e.getValue()))) {
			s = "bigint(40)";
		} else if ("String".equals(getType(e.getValue()))) {
			s = "varchar(20)";
		} else if ("Date".equals(getType(e.getValue()))) {
			s = "datetime";
		} else if ("Double".equals(getType(e.getValue()))) {
			s = "decimal(16,2)";
		}
		return s;
	}

	static void createFile(String filename, StringBuffer sb) throws IOException {
		createTargetFile(FilePath + "/" + filename, sb);
	}

	static void createTargetFile(String filepath, StringBuffer sb) throws IOException {
		File f = new File(filepath);
		new File(f.getParent()).mkdirs();
		FileWriter fw = new FileWriter(f);
		if(sb != null){
			fw.write(sb.toString());
		}
		fw.close();
	}

	static void createEntity() throws IOException {
		StringBuffer sb = new StringBuffer();

		sb.append("package " + Package + ";\n");
		sb.append("\n");
		sb.append("import java.util.Date;\n");
		sb.append("import com.platform.base.BaseEntity;\n");
		sb.append("\n");
		sb.append("public class " + ClassName + " extends BaseEntity {\n");
		sb.append("\n");
		for (Entry<String, String> e : fieldMap.entrySet()) {
			if (!"fields".equals(getField(e.getValue()))) {
				sb.append("	private " + getType(e.getValue()) + " " + getField(e.getValue()) + ";\n");
			}
		}
		sb.append("\n");

		for (Entry<String, String> e : fieldMap.entrySet()) {
			if (!"fields".equals(getField(e.getValue()))) {
				sb.append("	public " + getType(e.getValue()) + " " + getGetterName(e.getValue()) + "(){\n");
				sb.append("		return " + getField(e.getValue()) + ";\n");
				sb.append("	}\n");
				sb.append("\n");
				sb.append("	public void " + getSetterName(e.getValue()) + "(" + getType(e.getValue()) + " " + getField(e.getValue()) + ") {\n");
				sb.append("		this." + getField(e.getValue()) + " = " + getField(e.getValue()) + ";\n");
				sb.append("	}\n");
				sb.append("\n");
			}
		}
		sb.append("}");

		createFile(ClassName + ".java", sb);
	}

	static void createDao() throws IOException {
		StringBuffer sb = new StringBuffer();

		String temp = ClassName.replaceFirst(ClassName.substring(0, 1), ClassName.substring(0, 1).toLowerCase());
		sb.append("package " + Package + ";\n");
		sb.append("\n");
		sb.append("import java.util.List;\n");
		sb.append("import java.util.Map;\n");
		sb.append("import com.platform.base.MysqlBaseDao;\n");
		sb.append("import org.springframework.stereotype.Service;\n");
		sb.append("\n");
		sb.append("@Service(\"" + temp + "Dao\")\n");
		sb.append("public class " + ClassName + "Dao extends MysqlBaseDao {\n");
		sb.append("\n");

		sb.append("	public " + ClassName + " selectById(Long id) {\n");
		sb.append("		Object meta = queryForObject(\"" + ClassName + ".selectById\", id);\n");
		sb.append("		if(meta != null){\n");
		sb.append("			return (" + ClassName + ") meta;\n");
		sb.append("		}\n");
		sb.append("		return null;\n");
		sb.append("	}\n");
		sb.append("\n");

		sb.append("	public Long insert(" + ClassName + " meta) {\n");
		sb.append("		return insert(\"" + ClassName + ".insert\", meta);\n");
		sb.append("	}\n");
		sb.append("\n");

		sb.append("	public boolean delete(Long id) {\n");
		sb.append("		return delete(\"" + ClassName + ".delete\", id);\n");
		sb.append("	}\n");
		sb.append("\n");

		sb.append("	public boolean update(" + ClassName + " meta) {\n");
		sb.append("		return update(\"" + ClassName + ".update\", meta);\n");
		sb.append("	}\n");
		sb.append("\n");

		sb.append("	public List<" + ClassName + "> select(Map<String, Object> map) {\n");
		sb.append("		return (List<" + ClassName + ">) queryForList(\"" + ClassName + ".select\", map);\n");
		sb.append("	}\n");
		sb.append("\n");

		sb.append("	public long selectCount(Map<String, Object> map) {\n");
		sb.append("		return (Long) queryForObject(\"" + ClassName + ".selectCount\", map);\n");
		sb.append("	}\n");
		sb.append("\n");
		
		sb.append("	public double selectSum(Map<String, Object> map) {\n");
		sb.append("		return (Double) queryForObject(\"" + ClassName + ".selectSum\", map);\n");
		sb.append("	}\n");
		sb.append("\n");
		
		sb.append("}\n");
		sb.append("\n");

		createFile(ClassName + "Dao.java", sb);
	}
	
	static void createDaoWithCache() throws IOException {
		StringBuffer sb = new StringBuffer();
		
		String temp = ClassName.replaceFirst(ClassName.substring(0, 1), ClassName.substring(0, 1).toLowerCase());
		sb.append("package " + Package + ";\n");
		sb.append("\n");
		sb.append("import java.util.List;\n");
		sb.append("import java.util.Map;\n");
		sb.append("import com.platform.base.MysqlBaseDao;\n");
		sb.append("import org.springframework.stereotype.Service;\n");
		sb.append("\n");
		sb.append("@Service(\"" + temp + "Dao\")\n");
		sb.append("public class " + ClassName + "Dao extends MysqlBaseDao {\n");
		sb.append("\n");
		
		sb.append("	public " + ClassName + " selectById(Long id) {\n");
		sb.append("		" + ClassName + " meta = (" + ClassName + ") memcacheUtil.get(\"" + ClassName.toLowerCase() + "_\" + id);\n");
		sb.append("		if(meta == null){\n");
		sb.append("			meta =  (" + ClassName + ") queryForObject(\"" + ClassName + ".selectById\", id);\n");
		sb.append("			memcacheUtil.put(\"" + ClassName.toLowerCase() + "_\" + id, meta);\n");
		sb.append("		}\n");
		sb.append("		return meta;\n");
		sb.append("	}\n");
		sb.append("\n");
		
		sb.append("	public Long insert(" + ClassName + " meta) {\n");
		sb.append("		return insert(\"" + ClassName + ".insert\", meta);\n");
		sb.append("	}\n");
		sb.append("\n");
		
		sb.append("	public boolean delete(Long id) {\n");
		sb.append("		memcacheUtil.remove(\"" + ClassName.toLowerCase() + "_\" + id);\n");
		sb.append("		return delete(\"" + ClassName + ".delete\", id);\n");
		sb.append("	}\n");
		sb.append("\n");
		
		sb.append("	public boolean update(" + ClassName + " meta) {\n");
		sb.append("		memcacheUtil.remove(\"" + ClassName.toLowerCase() + "_\" + meta.getId());\n");
		sb.append("		return update(\"" + ClassName + ".update\", meta);\n");
		sb.append("	}\n");
		sb.append("\n");
		
		sb.append("	public List<" + ClassName + "> select(Map<String, Object> map) {\n");
		sb.append("		return (List<" + ClassName + ">) queryForList(\"" + ClassName + ".select\", map);\n");
		sb.append("	}\n");
		sb.append("\n");
		
		sb.append("	public long selectCount(Map<String, Object> map) {\n");
		sb.append("		return (Long) queryForObject(\"" + ClassName + ".selectCount\", map);\n");
		sb.append("	}\n");
		sb.append("\n");

		sb.append("	public double selectSum(Map<String, Object> map) {\n");
		sb.append("		Object result = queryForObject(\"Accountasset.selectSum\", map);\n");
		sb.append("		if(result != null){\n");
		sb.append("			return (Double) result;\n");
		sb.append("		}\n");
		sb.append("		return 0;\n");
		sb.append("	}\n");
		sb.append("\n");
		
		sb.append("}\n");
		sb.append("\n");
		
		createFile(ClassName + "Dao.java", sb);
	}

	static void createSqlMap() throws IOException {
		StringBuffer sb = new StringBuffer();

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<!DOCTYPE sqlMap PUBLIC \"-//ibatis.apache.org//DTD SQL Map 2.0//EN\" \"http://ibatis.apache.org/dtd/sql-map-2.dtd\">\n");
		sb.append("\n");
		sb.append("<sqlMap>\n");
		sb.append("\n");
		sb.append("	<typeAlias alias=\"" + ClassName + "\" type=\"" + MetaPath + "\" />\n");
		sb.append("\n");

		sb.append("	<sql id=\"" + ClassName + ".RESULT\">\n");
		StringBuffer sbs = new StringBuffer();
		for (Entry<String, String> e : fieldMap.entrySet()) {
			if (e.getKey().equals(getField(e.getValue()))) {
				sbs.append("," + e.getKey());
			} else {
				sbs.append("," + e.getKey() + " as " + getField(e.getValue()));
			}
		}
		sb.append("		" + sbs.substring(1) + "\n");
		sb.append("	</sql>\n");
		sb.append("\n");

		sb.append("	<sql id=\"" + ClassName + ".WHERE\">\n");
		sb.append("		<dynamic prepend=\"where\">\n");
		for (Entry<String, String> e : fieldMap.entrySet()) {
			sb.append("			<isNotEmpty prepend=\"and\" property=\"" + getField(e.getValue()) + "\"> " + e.getKey() + " = #" + getField(e.getValue()) + ":" + getDBType(e) + "# </isNotEmpty>\n");
		}
		sb.append("		</dynamic>\n");
		sb.append("	</sql>\n");
		sb.append("\n");

		sb.append("	<select id=\"" + ClassName + ".selectById\" parameterClass=\"java.lang.Long\" resultClass=\"" + ClassName + "\">\n");
		sb.append("		select <include refid=\"" + ClassName + ".RESULT\" /> from " + ClassName.toLowerCase() + " where id = #id:BIGINT#\n");
		sb.append("	</select>\n");
		sb.append("\n");

		sb.append("	<insert id=\"" + ClassName + ".insert\" parameterClass=\"" + ClassName + "\">\n");
		sb.append("		insert into " + ClassName.toLowerCase() + "\n");
		sb.append("		<dynamic prepend=\"(\">\n");
		for (Entry<String, String> e : fieldMap.entrySet()) {
			if(!"id".equals(getField(e.getValue()))){
				sb.append("			<isNotNull prepend=\",\" property=\"" + getField(e.getValue()) + "\"> " + e.getKey() + " </isNotNull>\n");
			}
		}
		sb.append("			)\n");
		sb.append("		</dynamic>\n");
		sb.append("		values\n");
		sb.append("		<dynamic prepend=\"(\">\n");
		for (Entry<String, String> e : fieldMap.entrySet()) {
			if(!"id".equals(getField(e.getValue()))){
				sb.append("			<isNotNull prepend=\",\" property=\"" + getField(e.getValue()) + "\"> #" + getField(e.getValue()) + ":" + getDBType(e) + "# </isNotNull>\n");
			}
		}
		sb.append("			)\n");
		sb.append("		</dynamic>\n");
		sb.append("		<selectKey resultClass=\"java.lang.Long\" keyProperty=\"id\">\n");
		sb.append("			<![CDATA[SELECT LAST_INSERT_ID() AS ID ]]>\n");
		sb.append("		</selectKey>\n");
		sb.append("	</insert>\n");
		sb.append("\n");

		sb.append("	<delete id=\"" + ClassName + ".delete\" parameterClass=\"java.lang.Long\">\n");
		sb.append("		delete from " + ClassName.toLowerCase() + " where id = #id:BIGINT#\n");
		sb.append("	</delete>\n");
		sb.append("\n");

		sb.append("	<update id=\"" + ClassName + ".update\" parameterClass=\"" + ClassName + "\">\n");
		sb.append("		update " + ClassName.toLowerCase() + "\n");
		sb.append("		<dynamic prepend=\"set\">\n");
		for (Entry<String, String> e : fieldMap.entrySet()) {
			if (!"id".equals(e.getKey()) && !"createtime".equals(e.getKey())) {
				sb.append("			<isNotNull prepend=\",\" property=\"" + getField(e.getValue()) + "\"> " + e.getKey() + " = #" + getField(e.getValue()) + ":" + getDBType(e) + "# </isNotNull>\n");
			}
		}
		sb.append("		</dynamic>\n");
		sb.append("		WHERE id = #id:BIGINT#\n");
		sb.append("	</update>\n");
		sb.append("\n");

		sb.append("	<select id=\"" + ClassName + ".select\" parameterClass=\"java.util.Map\" resultClass=\"" + ClassName + "\">\n");
		sb.append("		select <include refid=\"" + ClassName + ".RESULT\" /> from " + ClassName.toLowerCase() + " <include refid=\"" + ClassName + ".WHERE\" />\n");
		sb.append("		<isNotNull prepend=\"order\" property=\"orderBy\"> by $orderBy$ </isNotNull>\n");
		sb.append("		<isNotNull prepend=\"limit\" property=\"size\"> #first#,#size# </isNotNull>\n");
		sb.append("	</select>\n");
		sb.append("\n");

		sb.append("	<select id=\"" + ClassName + ".selectCount\" parameterClass=\"java.util.Map\" resultClass=\"java.lang.Long\">\n");
		sb.append("		select count(*) from " + ClassName.toLowerCase() + " <include refid=\"" + ClassName + ".WHERE\" />\n");
		sb.append("	</select>\n");
		sb.append("\n");
		
		sb.append("	<select id=\"" + ClassName + ".selectSum\" parameterClass=\"java.util.Map\" resultClass=\"java.lang.Double\">\n");
		sb.append("		select sum($field$) as sumField from " + ClassName.toLowerCase() + " <include refid=\"" + ClassName + ".WHERE\" />\n");
		sb.append("	</select>\n");
		sb.append("\n");

		sb.append("</sqlMap>\n");

		createFile(ClassName + ".xml", sb);
	}

	/**
	 * Action必须放到action目录下才能被struts2的注解识别到
	 */
	static void createAdminAction() throws IOException {
		StringBuffer sb = new StringBuffer();

		sb.append("package " + "com.action" + ";\n");
		sb.append("\n");
		sb.append("import java.util.HashMap;\n");
		sb.append("import java.util.List;\n");
		sb.append("import java.util.Map;\n");
		sb.append("\n");
		sb.append("import javax.annotation.Resource;\n");
		sb.append("\n");
		sb.append("import org.apache.struts2.convention.annotation.Action;\n");
		sb.append("import org.apache.struts2.convention.annotation.Namespace;\n");
		sb.append("import org.apache.struts2.convention.annotation.ParentPackage;\n");
		sb.append("import com.platform.base.BaseAction;\n");
		sb.append("import com.platform.constant.SystemConstant;\n");
		sb.append("import com.platform.util.PageUtil;\n");
		sb.append("import com."+AppName.toLowerCase()+".dao."+ClassName.toLowerCase()+"."+ClassName+";\n");
		sb.append("import com."+AppName.toLowerCase()+".dao."+ClassName.toLowerCase()+"."+ClassName+"Dao;\n");
		sb.append("\n");
		sb.append("@ParentPackage(\"web\")\n");
		sb.append("@Namespace(\"/admin" + ClassName.toLowerCase() + "\")\n");
		sb.append("public class Admin" + ClassName + "Action extends BaseAction {\n");
		sb.append("\n");
		
		sb.append("	@Resource\n");
		sb.append("	private "+ClassName+"Dao "+ClassName.toLowerCase()+"Dao;\n");
		sb.append("\n");
		sb.append("	private "+ClassName+" "+ClassName.toLowerCase()+";\n");
		sb.append("\n");
		sb.append("	public "+ClassName+" get"+ClassName+"() {\n");
		sb.append("		return "+ClassName.toLowerCase()+";\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	public void set"+ClassName+"("+ClassName+" "+ClassName.toLowerCase()+") {\n");
		sb.append("		this."+ClassName.toLowerCase()+" = "+ClassName.toLowerCase()+";\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	@Action(\"select\")\n");
		sb.append("	public String select() {\n");
		sb.append("		Map<String, Object> map = new HashMap<String, Object>();\n");
		sb.append("		PageUtil.getMap(map, getParameter(\"currentPage\"), "+ClassName.toLowerCase()+"Dao.selectCount(map));\n");
		sb.append("		List<"+ClassName+"> list = "+ClassName.toLowerCase()+"Dao.select(map);\n");
		sb.append("		request.setAttribute(\"map\", map);\n");
		sb.append("		request.setAttribute(\"list\", list);\n");
		sb.append("		return layout(null, \""+ClassName.toLowerCase()+"\", \"site title\",\"/"+ClassName.toLowerCase()+"/select.ftl\", SystemConstant.LAYOUT_ADMIN);\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	@Action(\"add\")\n");
		sb.append("	public String add() {\n");
		sb.append("		long id = Long.parseLong(getParameter(\"id\"));\n");
		sb.append("		"+ClassName+" meta = "+ClassName.toLowerCase()+"Dao.selectById(id);\n");
		sb.append("		request.setAttribute(\""+ClassName.toLowerCase()+"\", meta);\n");
		sb.append("		return layout(null, \""+ClassName.toLowerCase()+"\", \"site title\",\"/"+ClassName.toLowerCase()+"/add.ftl\", SystemConstant.LAYOUT_ADMIN);\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	@Action(\"insert\")\n");
		sb.append("	public String insert() {\n");
		sb.append("		if ("+ClassName.toLowerCase()+".getId() == null) {\n");
		sb.append("			"+ClassName.toLowerCase()+"Dao.insert("+ClassName.toLowerCase()+");\n");
		sb.append("		} else {\n");
		sb.append("			"+ClassName.toLowerCase()+"Dao.update("+ClassName.toLowerCase()+");\n");
		sb.append("		}\n");
		sb.append("		return redirect(\"/admin"+ClassName.toLowerCase()+"/select.html\");\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("	@Action(\"delete\")\n");
		sb.append("	public String delete() {\n");
		sb.append("		"+ClassName.toLowerCase()+"Dao.delete(Long.parseLong(getParameter(\"id\")));\n");
		sb.append("		return redirect(\"/admin"+ClassName.toLowerCase()+"/select.html\");\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("\n");
		sb.append("}\n");
		sb.append("\n");

		createTargetFile(ProjectPath + "/src/com/action/Admin" + ClassName + "Action.java", sb);
	}
	
	static void createAppAction() throws IOException {
		StringBuffer sb = new StringBuffer();

		sb.append("package " + "com.action;\n");
		sb.append("\n");
		sb.append("import java.util.HashMap;\n");
		sb.append("import java.util.List;\n");
		sb.append("import java.util.Map;\n");
		sb.append("\n");
		sb.append("import javax.annotation.Resource;\n");
		sb.append("\n");
		sb.append("import org.apache.struts2.convention.annotation.Action;\n");
		sb.append("import org.apache.struts2.convention.annotation.Namespace;\n");
		sb.append("import org.apache.struts2.convention.annotation.ParentPackage;\n");
		sb.append("import com.platform.base.BaseAction;\n");
		sb.append("import com.platform.util.PageUtil;\n");
		sb.append("import com."+AppName.toLowerCase()+".dao."+ClassName.toLowerCase()+"."+ClassName+";\n");
		sb.append("import com."+AppName.toLowerCase()+".dao."+ClassName.toLowerCase()+"."+ClassName+"Dao;\n");
		sb.append("\n");
		sb.append("@ParentPackage(\"app\")\n");
		sb.append("@Namespace(\"/" + ClassName.toLowerCase() + "\")\n");
		sb.append("public class " + ClassName + "Action extends BaseAction {\n");
		sb.append("\n");
		
		sb.append("	@Resource\n");
		sb.append("	private "+ClassName+"Dao "+ClassName.toLowerCase()+"Dao;\n");
		sb.append("\n");
		
		sb.append("	@Action(\"select\")\n");
		sb.append("	public void select() {\n");
		sb.append("		Map<String, Object> map = new HashMap<String, Object>();\n");
		sb.append("		PageUtil.getMap(map, getParameter(\"currentPage\"), "+ClassName.toLowerCase()+"Dao.selectCount(map), \"10\");\n");
		sb.append("		List<"+ClassName+"> list = "+ClassName.toLowerCase()+"Dao.select(map);\n");
		sb.append("		print(map);\n");
		sb.append("	}\n");
		sb.append("\n");
		sb.append("}\n");
		sb.append("\n");

		createTargetFile(ProjectPath + "/src/com/action/" + ClassName + "Action.java", sb);
	}

	static void createSql() throws IOException {
		StringBuffer sb = new StringBuffer();

		sb.append(table);
		sb.append("\n");
		sb.append(fields);
		sb.append("\n");
		sb.append("\n");

		sb.append("<sqlMap resource=\"" + MetaPath.replace(".", "/") + ".xml\" />");
		sb.append("\n");
		sb.append("\n");
		
		sb.append("CREATE TABLE `" + ClassName.toLowerCase() + "` (\n");
		for (Entry<String, String> e : fieldMap.entrySet()) {
			if ("id".equals(e.getKey())) {
				sb.append("  `id` " + getSQLType(e) + " NOT NULL AUTO_INCREMENT,\n");
			} else {
				sb.append("  `" + e.getKey() + "` " + getSQLType(e) + " default NULL,\n");
			}
		}
		if (fieldMap.get("id") != null) {
			sb.append("  PRIMARY KEY  (`id`)\n");
		} else {
			sb = new StringBuffer(sb.substring(0, sb.length() - 2) + "\n");
		}
		sb.append(") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;");

		createFile(ClassName + ".sql", sb);
	}

	static void createFtlFolder() throws IOException {
		createTargetFile(ProjectPath + "/WebRoot/" + ClassName.toLowerCase() + "/add.ftl", null);
		createTargetFile(ProjectPath + "/WebRoot/" + ClassName.toLowerCase() + "/select.ftl", null);
	}

}
