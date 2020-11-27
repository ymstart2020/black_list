package com.atguigu.util

import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.codehaus.jackson.map.deser.std.StringDeserializer

/**
 * @author ymstart
 * @create 2020-11-25 14:07
 */
object MyKafkaUtil {
  private val properties: Properties = PropertiesUtil.load("config.properties")
  private val brokers: String = properties.getProperty("kafka.broker.list")

  def getKafkaStream(topic: String, ssc: StreamingContext):InputDStream[ConsumerRecord[String, String]] = {
    val kafkaParam = Map(
      "bootstrap.servers" -> brokers,
      "key.deserizlizer" -> classOf[StringDeserializer],
      "value.deserizlizer" -> classOf[StringDeserializer],
      "group.id" -> "commerce-comsumer-group"
    )
    val dStream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](Array(topic), kafkaParam)
    )
    dStream
  }


}
