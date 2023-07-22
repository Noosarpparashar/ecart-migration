# Use the bitnami/spark base image
FROM bitnami/spark

# Set environment variables if needed (e.g., Spark properties)
ENV SPARK_MASTER=spark://spark-master:7077
ENV SPARK_EXECUTOR_MEMORY=2g

# Copy your Spark Scala application JAR
COPY target/scala-2.12/your-spark-app.jar /app/your-spark-app.jar

# Specify the entry point command
CMD spark-submit \
    --class com.its.ecartsales.framework.jobs.controllers.StreamKafkaConsumerEcartFactOrder1 \
    --master spark://spark-master:70 \
    --driver-memory 3g \
    --executor-memory 5g \
    --executor-cores 2
    /home/prasoon/IdeaProjects/ecart-migration/out/artifacts/ecart_migration_jar/ecart-migration.jar

spark-submit \
   --verbose
   ---master local[4] \
   --deploy-mode cluster \
   --driver-memory 8g \
   --executor-memory 16g \
   --executor-cores 2  \
   --class com.its.ecartsales.framework.jobs.controllers.StreamKafkaConsumerEcartFactOrder1 \
   /home/prasoon/IdeaProjects/ecart-migration/out/artifacts/ecart_migration_jar/ecart-migration.jar 80


   spark-submit \
     --class com.its.ecartsales.framework.jobs.controllers.StreamKafkaConsumerEcartFactOrder1 \
     --master local[8] \
     /home/prasoon/IdeaProjects/ecart-migration/out/artifacts/ecart_migration_jar/ecart-migration.jar \
     100