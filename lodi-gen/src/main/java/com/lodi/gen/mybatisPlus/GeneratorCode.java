package com.lodi.gen.mybatisPlus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.lodi.common.mybatis.mapper.BaseMapper;
import com.lodi.common.mybatis.service.BaseService;
import com.lodi.common.mybatis.service.impl.BaseServiceImpl;
import com.lodi.common.mybatis.domain.BaseEntity;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Version 1.0
 * @Message: 代码生成器
 */
public class GeneratorCode {

    private static final String AUTHOR = "MaybeBin";
    private static final String URL = "jdbc:mysql://localhost:3306/lodi?characterEncoding=utf8&serverTimezone=UTC";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "333";

    private static final String projectUrl = "E:\\myCode\\lodi";
    private static final String packageUrl = projectUrl + "\\lodi-xo\\src\\main";

    public static void execute(String tableName) {
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author(AUTHOR) // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .enableSpringdoc()
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir() //禁止打开输出目录
                            .outputDir(packageUrl + "\\java")
                    ; // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    // 类型转换
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        return DbColumnType.INTEGER;
                    }
                    if (typeCode == Types.TINYINT) {
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);
                }))
                // 自定义类型
                .injectionConfig(consumer -> {
                    consumer.customFile(injectionConfig());
                })
                .packageConfig(builder -> {
                    Map<OutputFile, String> pathInfo = new HashMap<>();
                    pathInfo.put(OutputFile.entity, projectUrl + "\\lodi-common\\lodi-common-model\\src\\main\\java\\com\\lodi\\common\\model\\entity");
                    pathInfo.put(OutputFile.controller, projectUrl + "\\lodi-admin\\src\\main\\java\\com\\lodi\\admin\\controller");
                    pathInfo.put(OutputFile.xml, packageUrl + "\\resources\\mapper");
                    pathInfo.put(OutputFile.mapper, packageUrl + "\\java\\com\\lodi\\xo\\mapper");
                    pathInfo.put(OutputFile.service, packageUrl + "\\java\\com\\lodi\\xo\\service");
                    pathInfo.put(OutputFile.serviceImpl, packageUrl + "\\java\\com\\lodi\\xo\\service\\impl");
                    builder
                            .parent("com.lodi") // 设置父包名
                            .entity("common.model.entity")
                            .controller("admin.controller")
                            .mapper("xo.mapper")
                            .service("xo.service")
                            .serviceImpl("xo.service.impl")
                            .pathInfo(pathInfo)
                    ;
                })
                // 模板配置
                .templateConfig(builder -> {
                    builder.disable(TemplateType.XML, TemplateType.MAPPER,
                            TemplateType.SERVICE, TemplateType.SERVICE_IMPL
//                            ,
//                            TemplateType.CONTROLLER
                    );
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName) // 设置需要生成的表名
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .entityBuilder().superClass(BaseEntity.class)   // 设置父类
                            .enableChainModel() // 开启链式模型
                            .enableFileOverride() // 启用文件覆盖
                            .enableLombok() // 开启Lombok
                            .addSuperEntityColumns("CREATE_TIME", "UPDATE_TIME", "IS_DELETE")   // 父类公共字段
                            .serviceBuilder().superServiceClass(BaseService.class)  // 设置父类
                            .superServiceImplClass(BaseServiceImpl.class)   // 设置父类
                            .formatServiceFileName("%sService") // 设置文件名格式
                            .mapperBuilder().superClass(BaseMapper.class)
                            .enableBaseColumnList().enableBaseResultMap()
                            .controllerBuilder().enableRestStyle();  // 设置父类
                })
                .templateEngine(new EnhanceFreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    /**
     * 自定义类型
     *
     * @return
     */
    private static List<CustomFile> injectionConfig() {
        List<CustomFile> list = new ArrayList<>();
        list.add(new CustomFile.Builder()
                .fileName(FileEnum.AddRequest.toString())
                .filePath(projectUrl + "\\lodi-common\\lodi-common-model\\src\\main\\java\\com\\lodi\\common\\model\\request")
                .packageName("com.lodi.common.model.request")
                .templatePath("/templates/addRequest.java.ftl")
                .build());
        list.add(new CustomFile.Builder()
                .fileName(FileEnum.PageRequest.toString())
                .filePath(projectUrl + "\\lodi-common\\lodi-common-model\\src\\main\\java\\com\\lodi\\common\\model\\request")
                .packageName("com.lodi.common.model.request")
                .templatePath("/templates/pageRequest.java.ftl")
                .build());
        list.add(new CustomFile.Builder()
                .fileName(FileEnum.UpdateRequest.toString())
                .filePath(projectUrl + "\\lodi-common\\lodi-common-model\\src\\main\\java\\com\\lodi\\common\\model\\request")
                .packageName("com.lodi.common.model.request")
                .templatePath("/templates/updateRequest.java.ftl")
                .build());
        list.add(new CustomFile.Builder()
                .fileName(FileEnum.VO.toString())
                .filePath(projectUrl + "\\lodi-common\\lodi-common-model\\src\\main\\java\\com\\lodi\\common\\model\\vo")
                .packageName("com.lodi.common.model.vo")
                .templatePath("/templates/vo.java.ftl")
                .build());
        list.add(new CustomFile.Builder()
                .fileName(FileEnum.Convert.toString())
                .filePath(projectUrl + "\\lodi-common\\lodi-common-model\\src\\main\\java\\com\\lodi\\common\\model\\convert")
                .packageName("com.lodi.common.model.convert")
                .templatePath("/templates/convert.java.ftl")
                .build());
        return list;
    }

}
