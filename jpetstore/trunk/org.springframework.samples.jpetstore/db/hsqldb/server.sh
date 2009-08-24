cd `dirname $0`
mvn -e -Ddb.file=./jpetstore exec:java
