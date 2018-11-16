package cn.com.jonpad.weibotopsummary;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  MyBatisPlus代码生成器
 * @author Jon Chan
 * @date 2018/11/16 22:50
 */
public class MyBatisPlusCodeGenerator {
	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/src/main/java")
				.setAuthor("Jon Chan")
				.setOpen(false)
				// .setSwagger2(true)
				.setDateType(DateType.TIME_PACK)
				.setMapperName("%sDao")
				.setXmlName("%sDao")
				.setServiceName("%sService")
				.setServiceImplName("%sServiceImpl")
				.setControllerName("%sController")
				.setIdType(IdType.AUTO);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT")
				.setDbType(DbType.MYSQL)
				.setDriverName("com.mysql.cj.jdbc.Driver")
				.setUsername("root")
				.setPassword("123456");
		mpg.setDataSource(dsc);
		// dsc.setSchemaName("public");

		// 包配置
		PackageConfig pc = new PackageConfig();
		//pc.setModuleName(scanner("模块名"));// 设置了全类名可以不用设置模块名
		pc.setParent("cn.com.jonpad.weibotopsummary");
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};
		List<FileOutConfig> focList = new ArrayList<>();
		focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
						+ "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
		mpg.setTemplate(new TemplateConfig().setXml(null));

		// 策略配置
		StrategyConfig strategy = new StrategyConfig()
				.setNaming(NamingStrategy.underline_to_camel)
				.setColumnNaming(NamingStrategy.underline_to_camel)
				.setSuperEntityClass("cn.com.jonpad.weibotopsummary.entities.BaseEntity")
				.setEntityLombokModel(true)
				.setRestControllerStyle(true)
				.setSuperControllerClass("cn.com.jonpad.weibotopsummary.controller.BaseController")
				.setInclude(scanner("表名"))
				.setSuperEntityColumns("id")
				.entityTableFieldAnnotationEnable(true)
				.setControllerMappingHyphenStyle(true)
				.setTablePrefix(StringUtils.isEmpty(pc.getModuleName()) || "null".equals(pc.getModuleName().trim())?"": pc.getModuleName()+ "_");
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}
}
