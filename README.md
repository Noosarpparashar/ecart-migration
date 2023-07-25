# Consume Data from Kafka | Load to AWS S3 | Containerize Multi-Node Spark Cluster with Docker

This project demonstrates how to create a data lake and containerize a multi-node Spark cluster using Docker and run your Spark code within the containers.
<p>Using this code, we read data from the Kafka topic in JSON format. The JSON data is then parsed using Spark SQL's json_tuple function to create a DataFrame with relevant columns. The processed data is written to a Parquet file format. The output is appended to a specific path in the S3 bucket, created based on the current timestamp.


1. Clone this repository to your local machine:

```bash
git clone https://github.com/Noosarpparashar/ecart-migration.git
cd ecart-migration
```

2. Start the Spark cluster using Docker Compose:

```bash
docker-compose up
```

3. Access the master node container:

```bash
docker exec -it <master_node_container_name> bash
```
(Note: Replace `<master_node_container_name>` with the actual name of your master node container.)

4. Create a folder named `myjars` inside the container:

```bash
mkdir myjars
```

5. Copy the jar from your local machine to the `myjars` folder inside the container:

```bash
docker cp /path/to/local/jar/file <master_node_container_name>:/opt/bitnami/spark/myjars
```
(Note: Replace `/path/to/local/jar/file` with the actual path to your jar file, and `<master_node_container_name>` with the name of your master node container.)

6. Exit the container:

```bash
exit
```

7. Run the Spark Submit command from your local machine:

```bash
docker exec -it <master_node_container_name> bash -c "cd myjars && spark-submit --master local[*] --class com.its.ecartsales.framework.jobs.controllers.StreamKafkaConsumerEcartFactOrder1 ecart-migration.jar"
```
(Note: Replace `<master_node_container_name>` with the name of your master node container.)

Your Spark code will now be executed within the containerized Spark cluster.

Feel free to modify the project structure and Spark code as needed for your specific use case. Happy Spark containerization and data processing!

## License

This project is licensed under the [MIT License](LICENSE).
```
