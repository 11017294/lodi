# 基础镜像
FROM java:8
# 设置时区
ENV LANG="en_US.utf8"
# 指定工作目录
WORKDIR /app

# 将 jar 包添加到工作目录
ADD target/lodi-gateway-1.0.0.jar lodi-gateway.jar

# 暴露端口
EXPOSE 8080

# 启动命令
ENTRYPOINT ["java","-jar","/app/lodi-gateway.jar","--spring.profiles.active=prod"]