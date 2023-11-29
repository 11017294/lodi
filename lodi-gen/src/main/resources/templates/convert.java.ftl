package ${Convert.packageName};

import ${page};
import com.lodi.common.model.entity.${entity};
import com.lodi.common.model.vo.${entity}VO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * ${table.comment!} Convert
 *
 * @author ${author}
 * @createDate ${date}
 */
@Mapper
public interface ${entity}Convert {

    ${entity}Convert INSTANCE = Mappers.getMapper(${entity}Convert.class);

    ${entity} toEntity(${entity}AddRequest addRequest);

    ${entity} toEntity(${entity}UpdateRequest updateRequest);

    ${entity}VO toVO(${entity} ${entityHump});
    
    List<${entity}VO> toVO(List<${entity}> ${entityHump}List); 
    
    Page<${entity}VO> toVO(Page<${entity}> ${entityHump}Page);
}