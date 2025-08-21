# y25_kafka_with_sb_and_gql
Year 2025: Learning Kafka with Spring Boot and GraphQL
Install a Kafka with ZooKeeper from below link:
-> https://kafka.apache.org/downloads

List of commands:
zookeeper-server-start.bat ..\..\config\zookeeper.properties

kafka-server-start.bat ..\..\config\server.properties

kafka-topics.bat --create --topic my-kafka-prac --bootstrap-server localhost:9092 --replication-factor 1 --partitions 3

kafka-console-producer.bat --broker-list localhost:9092 --topic my-kafka-prac

kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic my-kafka-prac --from-beginning

To list all available topics in your Kafka cluster:
kafka-topics.bat --list --bootstrap-server localhost:9092

Describe a specific topic:
kafka-topics.bat --describe --topic <topic_name> --bootstrap-server localhost:9092
**Result**:
PS C:\kafka_2.13-3.9.1\bin\windows> .\kafka-topics.bat --describe --topic my-topic --bootstrap-server localhost:9092
Topic: my-topic TopicId: _9a2SOehTO2IZxzdA2em9Q PartitionCount: 3       ReplicationFactor: 1    Configs:
        Topic: my-topic Partition: 0    Leader: 0       Replicas: 0     Isr: 0  Elr: N/A        LastKnownElr: N/A
        Topic: my-topic Partition: 1    Leader: 0       Replicas: 0     Isr: 0  Elr: N/A        LastKnownElr: N/A
        Topic: my-topic Partition: 2    Leader: 0       Replicas: 0     Isr: 0  Elr: N/A        LastKnownElr: N/A


        
