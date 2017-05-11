#!/bin/sh

kill -9 `ps -ef | grep kyt-0.0.1-SNAPSHOT | grep java | awk '{print $2}'`
