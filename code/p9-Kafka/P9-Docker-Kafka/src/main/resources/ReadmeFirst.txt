How to create this demo application:
1. Start Docker
2. Create a docker-compose.yml file to design and deploy the environment: Zookeeper, Kafka, Kafka-Tools.
3. Deploy the environment by executing: docker-compose up -d
4. Ensure the deployment is successful and all components are up and running: docker ps
5. Get into Kafka-Tools for access to its CLI: docker exec -ti kafka bash
6. Inside CLI, create a Kafka topic: kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 2 --topic to-do-list
7. Check it is created: kafka-topics --list --bootstrap-server localhost:9092
8. Get details about it: kafka-topics --describe --bootstrap-server localhost:9092 --topic to-do-list
9. Send messages to the topic by use of kafka-console-producer. A message is a key:value pair:
    kafka-console-producer --broker-list localhost:9092 --topic to-do-list --property "parse.key=true" --property "key.separator=:"
10. Type in for example the following messages:
    >1:Read the instructions, listen to explanations
    >2:Understand the instructions, ask questions, if needed
    >3:Follow the instructions
    >4:Move on to examining and consuming the messages
    > Ctrl/C for end
11. Read the topic with kafka-console-consumer:
    kafka-console-consumer --bootstrap-server localhost:9092 --from-beginning --topic to-do-list --property "print.key=true"
12. To read from a specified partition only, e.g. either 0 or 1 in this example:
    kafka-console-consumer --bootstrap-server localhost:9092  --from-beginning --topic to-do-list --property print.key=true --partition 0
13. To read records starting from a specified offset (now it says ''from beginning'', change it to either a number or 'earliest', 'latest', etc.):
    kafka-console-consumer --bootstrap-server localhost:9092  --topic to-do-list --property print.key=true --partition 1 --offset 2
14. Stop any console consumers with a CTRL+C
15. Close the shell with a CTRL+D
16. To shut down the docker container, use the command: docker-compose down



