package com.lodi.gen.mybatisPlus;

import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MaybeBin
 * @createDate 2023-11-17
 */
public class EnhanceFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

    @Override
    protected void outputCustomFile(List<CustomFile> customFiles, TableInfo tableInfo, Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        customFiles.forEach(time -> {
            StringBuilder sb = new StringBuilder();
            sb.append(time.getFilePath());
            sb.append(File.separator);
            sb.append(entityName);
            sb.append(time.getFileName());
            sb.append(".java");

            this.outputFile(new File(sb.toString()), objectMap, time.getTemplatePath(), true);
        });
    }

    public static String decapitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    @Override
    public Map<String, Object> getObjectMap(ConfigBuilder config, TableInfo tableInfo) {
        // 获取实体类名字
        String entityName = tableInfo.getEntityName();
        // 修改request路径和包
        InjectionConfig injectionConfig = config.getInjectionConfig();
        Map<String, Object> hashMap = new HashMap<>();
        List<CustomFile> customFiles = injectionConfig.getCustomFiles();
        for (int i = 0; i < customFiles.size(); i++) {
            CustomFile oldCustomFile = customFiles.get(i);
            if (!"Request".equals(oldCustomFile.getFileName())) {
                continue;
            }
            String entityNameHump = decapitalizeFirstLetter(entityName);
            CustomFile customFile = new CustomFile.Builder()
                    .fileName(oldCustomFile.getFileName())
                    .templatePath(oldCustomFile.getTemplatePath())
                    .filePath(oldCustomFile.getFilePath() + File.separator + entityNameHump)
                    .packageName(oldCustomFile.getPackageName() + "." + entityNameHump)
                    .build();
            customFiles.set(i, customFile);
            // 得到包名
            HashMap<String, Object> requestMap = new HashMap<>();
            requestMap.put("packageName", customFile.getPackageName());
            requestMap.put("postfix", "Request");
            hashMap.put("request", requestMap);
        }

        // 获取object map
        Map<String, Object> objectMap = super.getObjectMap(config, tableInfo);

        // 返回结果封装
        objectMap.put("result", "com.lodi.common.core.web.domain.Result");
        objectMap.putAll(hashMap);
        return objectMap;
    }

}
