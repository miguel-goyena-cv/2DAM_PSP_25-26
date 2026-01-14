package org.cuatrovientos.dam.psp.Manzanos4V;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class EmpaquetadorManzanas {

	private static final String KAFKA_SERVER_IP_PORT = "127.0.0.1:9092";
	private static final String MANZANAS_TOPIC = "recolector-manzanas";
	private static final String CONSUMER_GROUP = "empaquetadora_sanadrian";

	public static void main(String[] args) {
		KafkaConsumer<String, String> consumidor = null;
		boolean empaquetadorAbierta = true; // Abrimos la empaquetadora
		try {
			// Definimos las propiedades
			Properties props = new Properties();
			props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER_IP_PORT);
			props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
			props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
			props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP);
			props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

			// Creamos el consumidor
			consumidor = new KafkaConsumer<>(props);
			consumidor.subscribe(Collections.singleton(MANZANAS_TOPIC));
			System.out.println("Suscrito al TOPIC: "+MANZANAS_TOPIC);
			// Consumimos, mientras la empquetadora abierta
			while (empaquetadorAbierta) {
				ConsumerRecords<String, String> mensajes = consumidor.poll(Duration.ofSeconds(1));
				for (ConsumerRecord<String, String> mensaje : mensajes) {
					System.out.println("Llega mensaje: "+mensaje.value());
				}
			}
		} catch (Exception e) {
			System.err.println("Excepcion general en el Recolector de Manzanas: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (consumidor != null) {
				consumidor.close();
			}
		}

	}
}
