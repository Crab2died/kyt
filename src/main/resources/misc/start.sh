#!/bin/sh

#端口号
HTTP_PORT=1234
#上下文
CONTEXT_PATH=/

# resolve links - $0 may be a softlink
PRG="$0"

while [ -h "$PRG" ] ; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done
 
PRGDIR=`dirname "$PRG"`

# set customer vars
if [ -r "$PRGDIR/setenv.sh" ]; then
  . "$PRGDIR/setenv.sh"
fi

# workspace path
WEB_DIR=$PRGDIR/webapp
LIB_DIR=$PRGDIR/lib
JAR_FILE=$LIB_DIR/kyt-0.0.1-SNAPSHOT.jar

# executable
java -Djava.ext.dirs=$LIB_DIR \
-Dfile.encoding=UTF-8 \
-jar $JAR_FILE \
--port=$HTTP_PORT \
--address=$ADDRESS \
--webapp=$WEB_DIR
