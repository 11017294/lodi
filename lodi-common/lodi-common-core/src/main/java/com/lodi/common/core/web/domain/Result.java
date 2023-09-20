package com.lodi.common.core.web.domain;

import lombok.Data;

/**
 * 通用返回类
 * @author MaybeBin
 * @createDate 2023-09-19
 */
@Data
public class Result<T> {

    private int code;
    private T data;
    private String message;

    public Result(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

}
