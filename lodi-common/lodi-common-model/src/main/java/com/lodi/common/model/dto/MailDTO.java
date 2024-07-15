package com.lodi.common.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 邮件 TDO
 *
 * @author MaybeBin
 * @createDate 2024-07-11
 */
@Data
public class MailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主题
     */
    String subject;

    /**
     * 接收者
     */
    String receiver;

    /**
     * 内容
     */
    String content;

}
