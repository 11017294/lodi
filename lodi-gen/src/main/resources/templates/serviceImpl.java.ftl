package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import ${AddRequest.packageName}.${entity}${AddRequest.postfix};
import ${AddRequest.packageName}.${entity}${UpdateRequest.postfix};
import ${AddRequest.packageName}.${entity}${PageRequest.postfix};
import ${Convert.packageName}.${entity}Convert;
import ${page};
<#if table.serviceInterface>
import ${package.Service}.${table.serviceName};
</#if>
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
 * ${table.comment!} 服务层实现
 *
 * @author ${author}
 * @createDate ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>()<#if table.serviceInterface>, ${table.serviceName}</#if> {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}><#if table.serviceInterface> implements ${table.serviceName}</#if> {

    @Override
    public Boolean insert${entity}(${entity}AddRequest addRequest) {
        ${entity} ${entityHump} = ${entity}Convert.INSTANCE.toEntity(addRequest);
        return save(${entityHump});
    }

    @Override
    public Boolean update${entity}(${entity}UpdateRequest updateRequest) {
        ${entity} ${entityHump} = ${entity}Convert.INSTANCE.toEntity(updateRequest);
        return updateById(${entityHump});
    }

    @Override
    public Boolean delete${entity}(Long id) {
        return removeById(id);
    }

    @Override
    public Page<${entity}> get${entity}Page(${entity}PageRequest pageRequest) {
        Page<${entity}> page = new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<${entity}> queryWrapper = buildQueryWrapper(pageRequest);
        return baseMapper.selectPage(page, queryWrapper);
    }

    private LambdaQueryWrapper<${entity}> buildQueryWrapper(${entity}PageRequest pageRequest) {
        return new LambdaQueryWrapper<${entity}>();
    }
}
</#if>
