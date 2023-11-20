package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
<#if mapperAnnotationClass??>
 import ${mapperAnnotationClass.name};
</#if>

/**
* ${table.comment!} 数据层
*
* @author ${author}
* @createDate ${date}
*/
<#if mapperAnnotationClass??>
 @${mapperAnnotationClass.simpleName}
</#if>
<#if kotlin>
 interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
 public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

 }
</#if>
