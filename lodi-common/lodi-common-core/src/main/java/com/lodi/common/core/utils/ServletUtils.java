package com.lodi.common.core.utils;

import com.google.gson.Gson;
import com.lodi.common.core.constant.HttpConstants;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.web.domain.Result;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static com.lodi.common.core.constant.CommonConstant.EMPTY;
import static com.lodi.common.core.constant.CommonConstant.UTF8;


/**
 * 客户端工具类
 * @author MaybeBin
 * @createDate 2023-10-20
 */
public class ServletUtils {

    /**
     * 设置web模型响应
     *
     * @param response HttpServletResponse
     * @param result 统一返回对象
     * @throws IOException
     */
    public static void webResponseWriter(HttpServletResponse response, Result result) throws IOException {
        response.setContentType(HttpConstants.APPLICATION_JSON_UTF8);
        Gson gson = new Gson();
        String res = gson.toJson(result);
        response.getWriter().write(res);
    }

    // endregion

    // region 编码相关

    /**
     * 内容编码
     * @param str 内容
     * @return 编码后的内容
     */
    public static String urlEncode(String str){
        try {
            return URLEncoder.encode(str, UTF8);
        } catch (UnsupportedEncodingException e) {
            return EMPTY;
        }
    }

    /**
     * 内容解码
     * @param str 内容
     * @return 解码后的内容
     */
    public static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, UTF8);
        } catch (UnsupportedEncodingException e) {
            return EMPTY;
        }
    }

    // region 设置webflux模型响应

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param errorCode 错误码对象
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, ErrorCode errorCode) {
        return webFluxResponseWriter(response, HttpStatus.OK, errorCode.getMessage(), errorCode.getCode());
    }

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param code 响应状态码
     * @param value 响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, Object value, int code) {
        return webFluxResponseWriter(response, HttpStatus.OK, value, code);
    }

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param status http状态码
     * @param code 响应状态码
     * @param value 响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, HttpStatus status, Object value, int code) {
        return webFluxResponseWriter(response, MediaType.APPLICATION_JSON_VALUE, status, value, code);
    }

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param contentType content-type
     * @param status http状态码
     * @param code 响应状态码
     * @param value 响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, String contentType, HttpStatus status, Object value, int code) {
        response.setStatusCode(status);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, contentType);
        Result<?> result = Result.error(code, value.toString());
        Gson gson = new Gson();
        DataBuffer dataBuffer = response.bufferFactory().wrap(gson.toJson(result).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

    // endregion

}
