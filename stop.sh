#!/bin/bash

# 设置项目根目录
PROJECT_ROOT=$(cd $(dirname $0); pwd)

# 应用名称
APP_NAME="ape-user.jar"

# 查找应用进程ID
PID=$(ps -ef | grep "${APP_NAME}" | grep -v grep | awk '{print $2}')

if [ -z "$PID" ]; then
  echo "应用 ${APP_NAME} 未运行"
  exit 0
fi

# 停止应用
echo "正在停止应用 ${APP_NAME}，进程ID: $PID"
kill -15 $PID

# 等待应用停止
for i in {1..30}; do
  sleep 1
  if ! ps -p $PID > /dev/null; then
    echo "应用已成功停止"
    exit 0
  fi
  echo "等待应用停止，已等待 $i 秒..."
done

# 如果应用未能在30秒内停止，强制终止
echo "应用未能在30秒内停止，正在强制终止..."
kill -9 $PID

# 检查应用是否已终止
if ! ps -p $PID > /dev/null; then
  echo "应用已被强制终止"
  exit 0
else
  echo "无法终止应用，请手动检查进程"
  exit 1
fi