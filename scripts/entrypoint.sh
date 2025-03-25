#!/bin/bash
#set -x  # 调试模式，可以启用以打印每行脚本的执行情况

# 设置默认的等待时间间隔，默认值为2秒
: "${SLEEP_SECOND:=2}"
# 设置默认的超时时间，默认值为60秒
: "${TIMEOUT:=60}"

# 定义一个函数，用于检查指定主机和端口是否正在监听
wait_for() {
    local host="$1"
    local port="$2"
    local timeout="$TIMEOUT"
    local start_time
    local current_time
    local elapsed_time
    start_time=$(date +%s)
    # 等待指定时间，直到主机和端口都开始监听
    echo "等待 $host 监听端口 $port..."

    while ! nc -z "$host" "$port"; do
        # 计算已过去的时间
        current_time=$(date +%s)
        elapsed_time=$((current_time - start_time))

        # 如果已超时，则退出循环并打印错误信息
        if [ "$elapsed_time" -ge "$timeout" ]; then
            echo "等待超时，未能等待 $host:$port 启动"
            return 1
        fi

        echo "等待依赖服务 $host:$port 启动..."
        sleep "$SLEEP_SECOND"
    done
}

# 声明两个变量：DEPENDS（依赖项），CMD（要执行的命令）
declare DEPENDS
declare CMD

# 使用getopts解析命令行参数
# `-d` 表示依赖服务，以 "host:port" 的格式传入，多个服务用逗号分隔
# `-c` 表示命令，指定在依赖服务可用后要执行的命令
while getopts "d:c:" arg
do
    case "$arg" in
        d)
            DEPENDS="$OPTARG"  # 获取依赖服务
            ;;
        c)
            CMD="$OPTARG"  # 获取要执行的命令
            ;;
        ?)
            echo "未知参数"
            exit 1  # 遇到未知参数时退出
            ;;
    esac
done

# 如果有依赖服务，才进行等待
if [ -n "$DEPENDS" ]; then
    for var in ${DEPENDS//,/ }
    do
        host=${var%:*}  # 获取主机部分
        port=${var#*:}  # 获取端口部分
        if ! wait_for "$host" "$port"; then
            echo "依赖服务 $host:$port 启动失败"
            exit 1
        fi
    done
else
    echo "没有依赖服务，跳过等待"
fi

# 当所有依赖服务都启动后，或者没有依赖时，执行指定的命令
if [ -n "$CMD" ]; then
    echo "开始执行命令: $CMD"
    eval "$CMD"
else
    echo "未指定要执行的命令"
fi
