#!/usr/bin/env bash
# echo "hello world"
# echo "--------"
:<<!
echo "what is your name?"
read person
echo "Hello,${person}"
!
# echo "--------"
# echo $$ # 查找当前执行shell脚本的进程

# echo "--------" 读取文件内容赋值给变量
#cd ./demo/
#log=$(cat log.txt)
#echo ${log}
# echo "--------"
:<<!
cd ./demo/
/bin/bash test.txt  # 执行txt文件中的脚本
!
# echo "-------------"
:<<$
variable="你好！"
#unset variable  # 删除变量
readonly variable # 只读变量无法被删除
echo "${variable}"
$
# echo "-------------"
:<<$
begin_time=$(date +%s) # 开始时间，注意括号内的格式不能写错，+和%必须在一起
sleep 10s   # 休眠时间
end_time=$(date +%s)  # 结束时间
run_time=$((end_time-begin_time))  # 执行时间
echo "begin_time:${begin_time}"
echo "end_time:${end_time}"
echo "run_time：${run_time}s"
$
# echo "-------------" 将参数传入test.sh文件中的$1和$2中作为参数
:<<!
cd ./demo/
. ./test.sh shell http://www.google.com
!
# echo "-------------" 运行函数文件
:<<!
cd ./demo/
/bin/bash test.sh
!
# echo "-------------"
:<<!
cd ./demo/
. ./test.sh shell java
!
# echo "-------------"  $?获取上个命令的执行状态
:<<!
cd ./demo/
bash ./test.sh 100  # 作为一个新进程运行
echo $?
!
# echo "-------------"  字符串的相关操作
:<<!
string="http://www.baidu.com"
echo ${#string}   # 获取字符串的长度
echo ${string: 4}  # 截取字符串索引为4的右边的字符串
!
# echo "-------------" 数组的相关操作(关联数组)
:<<!
declare -A cityArr
cityArr=([liubei]=shuguo [sunquan]=wuguo [caocao]=weiguo)
echo ${cityArr[caocao]}  # 获取指定key的value值
echo ${!cityArr[*]}  # 获取数组中所有的key值
echo ${cityArr[@]}   # 获取数组中所有的value值
for key in ${!cityArr[@]} ;
    do
       echo "${key} come from ${cityArr[$key]}"  # 获取key-value对
    done
!
# echo "-------------"
# type cd  # type可用于查看命令的类型，例如cd就是一个内建命令
# type seq # seq is /usr/bin/seq
# echo "-------------" read命令的用法
:<<!
read -p "enter some information" name age male  # 同时给多个变量赋值
echo "name：${name}"
echo "age：${age}"
echo "male：${male}"
!
:<<!
read -n 1 -p "enter a char" char  # 只输入一个字符
printf "\n"  # 换行
echo ${char}
!
# echo "-------------" -t 20是设置超时时间为20秒，-sp是表示输入密码时不显示出来，静默输入
:<<!
if
    read -t 20 -sp "Enter password in 20 seconds(once) > " pass1 && printf "\n" &&  #第一次输入密码
    read -t 20 -sp "Enter password in 20 seconds(again)> " pass2 && printf "\n" &&  #第二次输入密码
    [ ${pass1} == ${pass2} ]  #判断两次输入的密码是否相等
then
    echo "Valid password"
else
    echo "Invalid password"
fi
!
# echo "-------------" 数学运算(())可以做整数运算，再linux系统中可通过bc来做小数运算
:<<!
a=10
b=21
echo $((a-b))
!
# echo "-------------" if_elif_else_fi连续判断，比较数字的时候，使用(())的方式进行比较
:<<!
printf "判断人的生命状态 \n"
read age
if ((${age} < 3)); then
        echo "婴儿"
    elif ((${age}>3 && ${age}<6)); then
        echo "幼儿"
    else
        echo "少年"
fi
!
# echo "-------------" 方式二，比较字符串的时候，可以使用[]或者[[]]的方式进行比较，也可以使用test的方式进行比较
:<<!
read age
if test $age -le 2; then
    echo "婴儿"
elif test $age -ge 3 && test $age -le 8; then
    echo "幼儿"
elif [ $age -ge 9 ] && [ $age -le 17 ]; then
    echo "少年"
elif [ $age -ge 18 ] && [ $age -le 25 ]; then
    echo "成年"
elif test $age -ge 26 && test $age -le 40; then
    echo "青年"
elif test $age -ge 41 && [ $age -le 60 ]; then
    echo "中年"
else
    echo "老年"
fi
!
# echo "-------------" 使用(())来对数字做判断，使用[[]]来对字符串做判断
:<<!
read today
if ((${today}==1)); then
    echo "Monday"
    elif((${today}==2));then
    echo "Tuesday"
    elif((${today}==3));then
    echo "Wednesday"
    elif((${today}==4));then
    echo "Thursday"
    elif((${today}==5));then
    echo "Friday"
    elif((${today}==6));then
    echo "Saturday"
    else
    echo "Sunday"
fi
!
# 使用[]或者[[]]格式来进行判断的时候，一定要注意括号和变量，变量和比较符之间都要留有空格
:<<!
read today
echo ${today}
if [[ ${today} == "星期一" ]]; then
    echo "Monday"
    elif [[ ${today} == "星期二" ]];then
    echo "Tuesday"
    elif [[ ${today} == "星期三" ]];then
    echo "Wednesday"
    elif [[ ${today} == "星期四" ]];then
    echo "Thursday"
    elif [[ ${today} == "星期五" ]];then
    echo "Friday"
    elif [[ ${today} == "星期六" ]];then
    echo "Saturday"
    else
    echo "Sunday"
fi
!
# echo "-------------" 使用while_do_done来达到数字求和的目的
:<<!
sum=0
echo "请使用crtl+d的组合键来结束数据输入！"
while read n
   do
     ((sum+=n))
   done
echo "the sum is : ${sum}"
!
# echo "-------------" 使用python风格的for循环来求取0-100之间的偶数和,seq是linux中的命令，用来根据起始值，步长和终点值来产生一系列整数
:<<!
sum=0
for num in $(seq 0 2 100)
do
   ((sum+=num))
done
echo "0-100之间的偶数和为${sum}"
!
# echo "-------------" 使用select in和case in两种格式搭配使用，可以用来进行选择
:<<!
echo "what is your favorite OS?"
select name in "Windows" "MAC OS" "Linux" "Unix" "Android"
do
case ${name} in
    "window")
        echo "Windows是微软开发的个人电脑操作系统，它是闭源收费的。"
        break
        ;;
    "MAC OS")
        echo "Mac OS是苹果公司基于UNIX开发的一款图形界面操作系统，只能运行与苹果提供的硬件之上。"
        break
        ;;
    "Linux")
        echo "Linux是一个类UNIX操作系统，它开源免费，运行在各种服务器设备和嵌入式设备。"
        break
        ;;
    "Unix")
        echo "UNIX是操作系统的开山鼻祖，现在已经逐渐退出历史舞台，只应用在特殊场合。"
        break
        ;;
    "Android")
        echo "Android是由Google开发的手机操作系统，目前已经占据了70%的市场份额。"
        break
        ;;
    *)
        echo "输入错误，请重新输入"
    esac
done
!
# echo "-------------" 可以使用break和continue关键字来跳出循环，通过它后面指定的数字，可以表示跳出几层循环，例如"break 2"表示跳出内外两层循环
:<<!
i=0
while ((++i)); do  #外层循环
    if((i>4)); then
        break  #跳出外层循环
    fi
    j=0;
    while ((++j)); do  #内层循环
        if((j>4)); then
            break  #跳出内层循环
        fi
        printf "%-4d" $((i*j))
    done
    printf "\n"
done
!
# echo "-------------"  $?获取函数的返回值
:<<!
function add(){
    return $(($1+$2)) # return 关键字用来表示函数的退出状态，而不是函数的返回值
}
add 100 21
echo $?
!
# echo "-------------" 函数的定义和执行，以及参数和返回值的获取
:<<!
function getsum(){
    sum=0
    for var in $@

    do
      ((sum+=var))
    done

    echo ${sum}   # 使用echo返回返回值时原样返回
#    return ${sum}  # 使用return返回返回值时有大小限制，不能超过255，一般只用来返回退出状态
}
# 调用函数，并获取其返回值
returnSum=$(getsum 2 4 6 8 10)
echo "returnSum:${returnSum}"
# 也可以直接获取，不用变量
echo "$(getsum 2 4 6)"
# 获取函数的执行结果
echo $?
!
# echo "-------------" 输入输出重定向
# 输出重定向
:<<!
sum=521
echo ${sum} >>./demo/log.txt  # 将标准输出文件重定向到log文件中，可以用来保存正确和错误的日志信息
!
:<<!
for var in "monday" "tuesday" "wednesday" "thursday" "friday"
do
    echo "${var}" >>./demo/log.txt  # 循环将日志保存到log.txt中
done
!
# 输入重定向 -w统计单词数，-l统计行数，-c统计字节数
:<<!
cat ./demo/log.txt
wc -l <./demo/log.txt  # 统计log.txt文件中的行数
wc -c <./demo/log.txt
!
































