package com.lodi.common.core.utils;

import com.google.gson.Gson;
import com.lodi.common.core.constant.HttpConstants;
import com.lodi.common.core.web.domain.Result;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 客户端工具类
 * @author MaybeBin
 * @createDate 2023-10-20
 */
public class ServletUtils {

    public static void webResponseWriter(HttpServletResponse response, Result result) throws IOException {
        response.setContentType(HttpConstants.APPLICATION_JSON_UTF8);
        Gson gson = new Gson();
        String res = gson.toJson(result);
        response.getWriter().write(res);
    }

}
