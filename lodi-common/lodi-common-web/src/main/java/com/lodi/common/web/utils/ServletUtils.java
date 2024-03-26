package com.lodi.common.web.utils;

import com.google.gson.Gson;
import com.lodi.common.core.constant.HttpConstants;
import com.lodi.common.core.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static com.lodi.common.core.constant.CommonConstant.EMPTY;
import static com.lodi.common.core.constant.CommonConstant.UTF8;

/**
 * 客户端工具类
 *
 * @author MaybeBin
 * @createDate 2023-10-20
 */
@Slf4j
public class ServletUtils {

    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    public static String getHeader(HttpServletRequest request, String name) {
        String value = request.getHeader(name);
        if (StringUtils.isBlank(value)) {
            return EMPTY;
        }
        return urlDecode(value);
    }

    // region request、response

    public static HttpServletRequest getRequest() {
        try {
            return getRequestAttributes().getRequest();
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("获取Request失败", e);
            }
            return null;
        }
    }

    public static HttpServletResponse getResponse() {
        try {
            return getRequestAttributes().getResponse();
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("获取Response失败", e);
            }
            return null;
        }
    }

    public static ServletRequestAttributes getRequestAttributes() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            return (ServletRequestAttributes) requestAttributes;
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("获取RequestAttributes失败", e);
            }
            return null;
        }
    }

    /**
     * 设置web模型响应
     *
     * @param response HttpServletResponse
     * @param result   统一返回对象
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
     *
     * @param str 内容
     * @return 编码后的内容
     */
    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, UTF8);
        } catch (UnsupportedEncodingException e) {
            return EMPTY;
        }
    }

    /**
     * 内容解码
     *
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

    // endregion

}
