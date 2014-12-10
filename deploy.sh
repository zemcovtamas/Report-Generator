mvn clean install
cp target/tp-rtt-api-1.0-SNAPSHOT-jar-with-dependencies.jar tp-rtt-api.jar
scp tp-rtt-api.jar sw_mini:/home/chaarlee/
rm tp-rtt-api.jar
