FROM java:8
EXPOSE 8080
ADD /target/BitcoinPriceIndex-1.0-SNAPSHOT-jar-with-dependencies.jar BitcoinPriceIndex-1.0-SNAPSHOT-jar-with-dependencies.jar
ENTRYPOINT ["java","-jar","BitcoinPriceIndex-1.0-SNAPSHOT-jar-with-dependencies.jar"]