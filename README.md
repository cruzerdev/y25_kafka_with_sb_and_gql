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

If you are running Kafka on Kraft mode:
After creating the server.properties file. Run these below commands:
1. Only once per new cluster
.\bin\windows\kafka-storage.bat random-uuid
2. Format storage directory(Formatting metadata directory /tmp/kraft-combined-logs with metadata.version 3.9-IV0.)
.\bin\windows\kafka-storage.bat format -t <Paste generated UUID here> -c .\config\kraft\server.properties
3. Start the broker in cluster now:
.\bin\windows\kafka-server-start.bat .\config\kraft\server.properties
4. Create a new topic:
   .\bin\windows\kafka-topics.bat --create --topic kraft-mode-practice --bootstrap-server localhost:9092
5. List all topics:
   .\bin\windows\kafka-topics.bat  --bootstrap-server localhost:9092 --list
6. Describe the Topic with partition:
    .\bin\windows\kafka-topics.bat  --bootstrap-server localhost:9092 --describe
<table> Topic: kraft-mode-practice      TopicId: AGR9LkUDRYSFzwl2BYBfSg PartitionCount: 1       ReplicationFactor: 1    Configs: segment.bytes=1073741824
        Topic: kraft-mode-practice      Partition: 0    Leader: 1       Replicas: 1     Isr: 1  Elr:    LastKnownElr:
</table>
7. Get the cluster ID:
.\bin\windows\kafka-metadata-quorum.bat --bootstrap-server localhost:9092 describe --status
<table>
ClusterId:              Tl9e7iy3TsSaFI_IjzMO5Q
LeaderId:               1
LeaderEpoch:            1
HighWatermark:          900
MaxFollowerLag:         0
MaxFollowerLagTimeMs:   0
CurrentVoters:          [{"id": 1, "directoryId": null, "endpoints": ["CONTROLLER://localhost:9093"]}]
</table>



        
