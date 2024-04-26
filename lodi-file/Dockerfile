# 基础镜像
FROM java:8
# 设置时区
ENV LANG="en_US.utf8"
# 指定工作目录
WORKDIR /app

# 将 jar 包添加到工作目录
ADD target/lodi-file-1.0.0.jar lodi-file.jar

# 暴露端口
EXPOSE 9521

# 启动命令
ENTRYPOINT ["java","-jar","/app/lodi-file.jar","--spring.profiles.active=prod"]