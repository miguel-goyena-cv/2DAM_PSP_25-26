package org.cuatrovientos.dam.psp.Manzanos4V;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class RecolectorManzanas {

	private static final String KAFKA_SERVER_IP_PORT = "127.0.0.1:9092";
	private static final String MANZANAS_TOPIC = "recolector-manzanas";

	public static void main(String[] args) {

		KafkaProducer<String, String> productor = null;

		try {

			Properties props = new Properties();
			props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER_IP_PORT);
			props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
			props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

			productor = new KafkaProducer<>(props);

			productor.send(new ProducerRecord<>(MANZANAS_TOPIC, "45 KG - Manzanas Rojas"));

		} catch (Exception e) {
			System.err.println("Excepcion general en el Recolector de Manzanas: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (productor != null) {
				productor.flush();
				productor.close();
			}
		}
	}

}
