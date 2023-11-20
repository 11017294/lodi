package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import ${AddRequest.packageName}.${entity}${AddRequest.postfix};
import ${AddRequest.packageName}.${entity}${UpdateRequest.postfix};
import ${AddRequest.packageName}.${entity}${PageRequest.postfix};
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
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>()<#if table.serviceInterface>, ${table.serviceName}</#if> {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}><#if table.serviceInterface> implements ${table.serviceName}</#if> {

    @Override
    public Boolean insert${entity}(${entity}AddRequest addRequest) {
        ${entity} ${entityHump} = new ${entity}();
        BeanUtils.copyProperties(addRequest, ${entityHump});
        return updateById(${entityHump});
    }

    @Override
    public Boolean update${entity}(${entity}UpdateRequest updateRequest) {
        ${entity} ${entityHump} = new ${entity}();
        BeanUtils.copyProperties(updateRequest, ${entityHump});
        return updateById(${entityHump});
    }

    @Override
    public Boolean delete${entity}(Long id) {
        return removeById(id);
    }

    @Override
    public Page<${entity}> get${entity}Page(${entity}PageRequest pageRequest) {
        Page<${entity}> page = new Page<>(pageRequest.getCurrent(), pageRequest.getPageSize());
        LambdaQueryWrapper<${entity}> queryWrapper = buildQueryWrapper(pageRequest);
        return baseMapper.selectPage(page, queryWrapper);
    }

    private LambdaQueryWrapper<${entity}> buildQueryWrapper(${entity}PageRequest pageRequest) {
        return new LambdaQueryWrapper<${entity}>();
    }
}
</#if>
