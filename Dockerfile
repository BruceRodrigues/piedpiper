FROM java:8-alpine

ADD app/target/piedpiper.jar exec/

CMD java -jar exec/piedpiper.jar

