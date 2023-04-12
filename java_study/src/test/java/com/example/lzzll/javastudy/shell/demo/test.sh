#!/usr/bin/env bash
# 直接运行脚本
:<<!
 echo "hello world"
 echo "http://www.baidu.com"
!
# 通过命令行传参运行脚本文件
:<<!
echo "Language:$1"
echo "Url:$2"
!
# 定义函数运行脚本文件
:<<!
function func(){
    echo "Language:$1"
    echo "Url:$2"
}
# 运行函数
func java www.baidu.com
!
# shell中特殊变量的含义
:<<!
echo "Process ID: $$"  # 当前脚本所运行的进程的id
echo "File Name: $0"  # 当前脚本的文件名(执行此文件的脚本文件名)
echo "First Parameter : $1"  # 传入的第一个参数
echo "Second Parameter : $2" # 传入的第二个参数
echo "All parameters 1: $@"  # 传入的所有参数，当使用""包裹时，$@将所有的参数看作独立的数据
for var in "$@"
 do
    echo "${var}"
 done
echo "All parameters 2: $*"  # 传入的所有参数，当使用""包裹时，$*将所有的参数看作一份数据
for var in "$*"
  do
    echo "${var}"
  done
echo "Total: $#"    # 所有参数的个数
!
# $? 可以获取上个命令的退出状态(0表示执行成功，1表示执行失败)或者是函数的返回值
:<<!
if [ "$1" -eq 100 ];  # 用于比较整数时，只能使用-eq或者-gt等方式，不能直接使用=号，并且两边都需要留有空格
  then
     exit 0  # 参数正确，正常退出
  else
     exit 1  # 参数错误，退出状态为1
fi
!
:<<!
if (($1==100));
    then
        exit 0
    else
        exit 1
fi
!
# 校验写入的文件是否成功 -w用来检测文件是否存在并且可写，-n选项用来检测字符串是否为空，需要运行再linux系统环境下
:<<!
read filename
read url
if test -w $filename && test -n $url
then
    echo $url > $filename
    echo "写入成功"
else
    echo "写入失败"
fi
!














