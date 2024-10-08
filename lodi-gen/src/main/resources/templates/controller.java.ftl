package ${package.Controller};

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${AddRequest.packageName}.${entity}${AddRequest.postfix};
import ${AddRequest.packageName}.${entity}${UpdateRequest.postfix};
import ${AddRequest.packageName}.${entity}${PageRequest.postfix};
import ${Convert.packageName}.${entity}Convert;
import ${VO.packageName}.${entity}${VO.postfix};
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import lombok.AllArgsConstructor;
<#if springdoc>
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
</#if>
import ${page};
import ${result};
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.model.request.IdRequest;
import org.springframework.web.bind.annotation.*;
import static com.lodi.common.core.enums.ErrorCode.NOT_FOUND_ERROR;

/**
 * @author ${author}
 * @createDate ${date}
 */
<#if springdoc>
@Tag(name = "${table.comment}", description = "${table.comment}")
</#if>
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@AllArgsConstructor
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">${package.ModuleName}/</#if><#if controllerMappingHyphenStyle>${controllerMappingHyphen}/<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    private final ${table.serviceName} ${table.entityPath}Service;

<#if springdoc>
    @Operation(summary = "获取${table.comment}分页")
</#if>
    @GetMapping("page")
    public Result<Page<${entity}VO>> get${entity}Page(@ParameterObject ${entity}PageRequest pageRequest) {
        Page<${entity}> ${table.entityPath}Page = ${table.entityPath}Service.get${entity}Page(pageRequest);
        return Result.success(${entity}Convert.INSTANCE.toVO(${table.entityPath}Page));
    }

    @Operation(summary = "获取${table.comment}")
    @GetMapping("get")
    public Result<${entity}VO> get${entity}(@ParameterObject IdRequest request) {
        ${entity} ${entityHump} = ${table.entityPath}Service.getById(request.getId());
        if (${entityHump} == null) {
            throw new BusinessException(NOT_FOUND_ERROR);
        }
        return Result.success(${entity}Convert.INSTANCE.toVO(${entityHump}));
    }

    @Operation(summary = "新增${table.comment}")
    @PostMapping("add")
    public Result<Boolean> add${entity}(@RequestBody ${entity}AddRequest addRequest) {
        return Result.success(${table.entityPath}Service.insert${entity}(addRequest));
    }

    @Operation(summary = "更新${table.comment}")
    @PutMapping("update")
    public Result<Boolean> update${entity}(@RequestBody ${entity}UpdateRequest updateRequest) {
        return Result.success(${table.entityPath}Service.update${entity}(updateRequest));
    }

    @Operation(summary = "删除${table.comment}")
    @DeleteMapping("delete")
    public Result<Boolean> delete${entity}(@RequestBody IdRequest request) {
        return Result.success(${table.entityPath}Service.delete${entity}(request.getId()));
    }
}
</#if>
