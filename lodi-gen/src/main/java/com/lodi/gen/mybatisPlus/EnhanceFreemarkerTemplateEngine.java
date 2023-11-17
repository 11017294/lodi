package com.lodi.gen.mybatisPlus;

import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.*;

/**
 * @author MaybeBin
 * @createDate 2023-11-17
 */
public class EnhanceFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

    @Override
    protected void outputCustomFile(List<CustomFile> customFiles, TableInfo tableInfo, Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String lowerCase = decapitalizeFirstLetter(entityName);
        customFiles.forEach(time -> {
            StringBuilder sb = new StringBuilder();
            sb.append(time.getFilePath());
            sb.append(File.separator);
            sb.append(lowerCase);
            sb.append(File.separator);
            sb.append(entityName);
            sb.append(time.getFileName());
            sb.append(".java");

            Map map = (Map) objectMap.get("package");
            HashMap hashMap = new HashMap<>(map);
            hashMap.put(time.getFileName(), time.getPackageName() + "." + lowerCase);

            Map<Object, Object> objectObjectMap = Collections.unmodifiableMap(hashMap);
            objectMap.put("package", objectObjectMap);

            this.outputFile(new File(sb.toString()), objectMap, time.getTemplatePath(), true);
        });
    }

    public static String decapitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    //    @Override
    public Map<String, Object> getObjectMap2(ConfigBuilder config, TableInfo tableInfo) {
        // 获取实体类名字
        String entityName = tableInfo.getEntityName();
        // 获取object map
        Map<String, Object> objectMap = super.getObjectMap(config, tableInfo);
        // 获取Other的盘符路径
        String otherPath = (String) ((Map<String, Object>) objectMap.get("package")).get("Other");
        // 自定义枚举
        List<String> modelList = new ArrayList<>();
        // 循环
        modelList.forEach(it -> {
            // 转小写
            String var = it.toLowerCase();
            // 存入object map
            objectMap.put(var, otherPath + "." + var + "." + entityName + it);
        });
        // 自定义converter
        objectMap.put("commonConverter", "com.zhanghp.common.converter.CommonConverter");
        // converter utils
        objectMap.put("converterUtil", "com.zhanghp.common.converter.utils.ConverterUtil");
        // 分页
        objectMap.put("pageParent", "com.zhanghp.common.model.PageParent");
        // utils
        objectMap.put("objectUtils", "com.zhanghp.common.utils.ObjectUtils");
        // 返回结果封装
        objectMap.put("r", "com.zhanghp.common.response.R");
        return objectMap;
    }

}
