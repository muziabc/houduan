#!/bin/bash
cd `dirname $0`

# 项目构建操作....
echo "使用 Docker-Maven 打包项目"
docker run --rm \
   -v ~/.m2:/root/.m2 \
   -v $PROJECT_HOME:/usr/src/mymaven \
   -w /usr/src/mymaven \
   maven:3.3.3-jdk-8 mvn clean package -U

mv $PROJECT_HOME/*-provider/target/*-provider-*.jar $PROJECT_HOME/*-provider/target/demo.jar

echo "构建镜像"
docker build -t $APP_NAME:v$VERSION .
