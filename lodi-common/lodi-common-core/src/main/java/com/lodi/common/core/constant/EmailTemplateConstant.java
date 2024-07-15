package com.lodi.common.core.constant;

/**
 * 邮件常量
 *
 * @author MaybeBin
 * @createDate 2024-07-10
 */
public interface EmailTemplateConstant {

    /**
     * 验证码
     */
    String AUTH_CODE_TEMPLATE = "<!DOCTYPE html>\n" +
            "                <html lang=\"en\">\n" +
            "                <head>\n" +
            "                    <meta charset=\"UTF-8\">\n" +
            "                    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "                    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "                    <title>Email Template</title>\n" +
            "                    <style>\n" +
            "                        .body {\n" +
            "                            font-size: 14px;\n" +
            "                            padding: 15px;\n" +
            "                            line-height: 1.7;\n" +
            "                        }\n" +
            "\n" +
            "                        .qmbox h1, .qmbox h2, .qmbox h3 {\n" +
            "                            color: #00785a;\n" +
            "                        }\n" +
            "\n" +
            "                        .qmbox p {\n" +
            "                            margin: 0;\n" +
            "                            color: #333;\n" +
            "                            font-size: 16px;\n" +
            "                        }\n" +
            "\n" +
            "                        .qmbox .eo-link, .qmbox .eo-link:hover {\n" +
            "                            color: #0576b9;\n" +
            "                            text-decoration: none;\n" +
            "                            cursor: pointer;\n" +
            "                        }\n" +
            "\n" +
            "                        .qmbox .eo-link:hover {\n" +
            "                            color: #3498db;\n" +
            "                        }\n" +
            "\n" +
            "                        .qmbox .eo-p-link {\n" +
            "                            display: block;\n" +
            "                            margin-top: 20px;\n" +
            "                            color: #009cff;\n" +
            "                            text-decoration: underline;\n" +
            "                        }\n" +
            "\n" +
            "                        .mailContentContainer {\n" +
            "                            max-width: 800px;\n" +
            "                            margin: 20px auto 0 auto;\n" +
            "                            opacity: 1;\n" +
            "                            border-collapse: collapse;\n" +
            "                            border: 1px solid #e5e5e5;\n" +
            "                            box-shadow: 0 10px 15px rgba(0, 0, 0, 0.05);\n" +
            "                            text-align: left;\n" +
            "                            font-size: 14px;\n" +
            "                        }\n" +
            "\n" +
            "                        .mailContentContainer img {\n" +
            "                            padding: 15px 15px 15px 30px;\n" +
            "                            width: 50px;\n" +
            "                        }\n" +
            "\n" +
            "                        .mailContentContainer .p-code p {\n" +
            "                            color: #253858;\n" +
            "                            text-align: center;\n" +
            "                            line-height: 1.75em;\n" +
            "                            background-color: #f2f2f2;\n" +
            "                            min-width: 200px;\n" +
            "                            margin: 0 auto;\n" +
            "                            font-size: 28px;\n" +
            "                            border-radius: 5px;\n" +
            "                            border: 1px solid #d9d9d9;\n" +
            "                            font-weight: bold;\n" +
            "                        }\n" +
            "\n" +
            "                        .p-code {\n" +
            "                            padding: 0 30px;\n" +
            "                        }\n" +
            "\n" +
            "                        .p-intro {\n" +
            "                            padding: 30px;\n" +
            "                        }\n" +
            "\n" +
            "                        .p-news {\n" +
            "                            padding: 0 30px 30px;\n" +
            "                        }\n" +
            "                    </style>\n" +
            "                </head>\n" +
            "\n" +
            "                <body>\n" +
            "                    <div id=\"contentDiv\" class=\"body\">\n" +
            "                        <div id=\"qm_con_body\">\n" +
            "                            <div class=\"mailContentContainer qmbox qm_con_body_content qqmail_webmail_only\">\n" +
            "                                <table cellpadding=\"0\" cellspacing=\"0\">\n" +
            "                                    <tbody>\n" +
            "                                        <tr style=\"background-color: #f8f8f8;\">\n" +
            "                                            <td style=\"display: flex; justify-content: flex-start; align-items: flex-end;\">\n" +
            "                                                <img src=\"http://picture.bigchen.icu/favicon.ico\">\n" +
            "                                                <h4>Lodi</h4>\n" +
            "                                            </td>\n" +
            "                                        </tr>\n" +
            "                                        <tr>\n" +
            "                                            <td class=\"p-intro\">\n" +
            "                                                <h1>验证您的邮箱地址</h1>\n" +
            "                                                <p>感谢您使用 Lodi.</p>\n" +
            "                                                <p>以下是您的邮箱验证码，请将它输入到 Lodi 的邮箱验证码输入框中:</p>\n" +
            "                                            </td>\n" +
            "                                        </tr>\n" +
            "                                        <tr>\n" +
            "                                            <td class=\"p-code\">\n" +
            "                                                <p>\n" +
            "                                                   %S\n" +
            "                                                </p>\n" +
            "                                            </td>\n" +
            "                                        </tr>\n" +
            "                                        <tr>\n" +
            "                                            <td class=\"p-intro\">\n" +
            "                                                <p>这一封邮件包括一些您的私密的 Lodi 账号信息，请不要回复或转发它，以免带来不必要的信息泄露风险。</p>\n" +
            "                                            </td>\n" +
            "                                        </tr>\n" +
            "                                        <tr>\n" +
            "                                            <td class=\"p-intro\">\n" +
            "                                                <hr>\n" +
            "                                                <p style=\"text-align: center;\">Lodi</p>\n" +
            "                                            </td>\n" +
            "                                        </tr>\n" +
            "                                    </tbody>\n" +
            "                                </table>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </body>\n" +
            "                </html>";


}
