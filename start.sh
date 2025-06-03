#!/bin/bash

# 设置项目根目录
PROJECT_ROOT=$(cd $(dirname $0); pwd)

# 设置Java选项
JAVA_OPTS="-Xms256m -Xmx512m"

# 设置日志目录
LOG_DIR="$PROJECT_ROOT/logs"

# 创建日志目录（如果不存在）
if [ ! -d "$LOG_DIR" ]; then
  echo "创建日志目录: $LOG_DIR"
  mkdir -p "$LOG_DIR"
fi

# 设置配置文件路径（如果需要指定配置文件）
# CONFIG_PATH="$PROJECT_ROOT/config/application.yml"

# 检查ape-user.jar是否存在
if [ ! -f "$PROJECT_ROOT/ape-user/target/ape-user.jar" ]; then
  echo "ape-user.jar不存在，开始构建项目..."
  cd $PROJECT_ROOT
  mvn clean package -Dmaven.test.skip=true
fi

# 启动应用
echo "正在启动应用..."
cd $PROJECT_ROOT
java $JAVA_OPTS -Dlogging.file.path=$LOG_DIR -jar "$PROJECT_ROOT/ape-user/target/ape-user.jar"

# 如果需要指定配置文件，取消下面这行的注释
# java $JAVA_OPTS -Dlogging.file.path=$LOG_DIR -jar "$PROJECT_ROOT/ape-user/target/ape-user.jar" --spring.config.location=$CONFIG_PATH