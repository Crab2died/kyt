#!/bin/sh

#####################
# set env
#####################

export JAVA_HOME=/usr/local/jdk1.7.0_51
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

#####################
# start rest
#####################

ifrun=$(ps -ef | grep beye-quicklisten-rest | grep java)
if [ "$ifrun" != "" ];then
    echo "rest is running. do nothing"
else
    echo "rest is stopped, start it"
    nohup ./start.sh &
fi
