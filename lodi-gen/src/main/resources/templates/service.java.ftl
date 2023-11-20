package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import ${AddRequest.packageName}.${entity}${AddRequest.postfix};
import ${AddRequest.packageName}.${entity}${UpdateRequest.postfix};
import ${AddRequest.packageName}.${entity}${PageRequest.postfix};
import ${page};

/**
 * ${table.comment!} 服务层
 *
 * @author ${author}
 * @createDate ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     * 新增${table.comment}
     * @param addRequest 新增请求体
     * @return
     */
    Boolean insert${entity}(${entity}AddRequest addRequest);

    /**
     * 更新${table.comment}
     * @param updateRequest 更新请求体
     * @return
     */
    Boolean update${entity}(${entity}UpdateRequest updateRequest);

    /**
     * 删除${table.comment}
     * @param id ${table.comment}ID
     * @return
     */
    Boolean delete${entity}(Long id);

    /**
     * 获取${table.comment}
     * @param pageRequest 分页查询请求体
     * @return
     */
    Page<${entity}> get${entity}Page(${entity}PageRequest pageRequest);
}
</#if>
