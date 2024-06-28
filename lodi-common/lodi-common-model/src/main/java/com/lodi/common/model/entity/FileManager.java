package com.lodi.common.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lodi.common.mybatis.domain.BaseEntity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文件管理
 *
 * @author MaybeBin
 * @createDate 2024-06-27
 */
@Data
@Accessors(chain = true)
@TableName("t_file_manager")
public class FileManager extends BaseEntity<FileManager> {

    private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 父ID目录关联
    */
    private Long parentId;

    /**
    * 文件名称
    */
    private String fileName;

    /**
    * 文件地址
    */
    private String filePath;

    /**
    * 文件类型：0-目录 1-文件
    */
    private Integer fileType;

    /**
    * 文件大小
    */
    private Long fileSize;

    /**
    * 文件内容哈希
    */
    private String fileHash;

    /**
    * 创建人
    */
    private Long createdBy;

    /**
    * 更新人
    */
    private Long updatedBy;
}
