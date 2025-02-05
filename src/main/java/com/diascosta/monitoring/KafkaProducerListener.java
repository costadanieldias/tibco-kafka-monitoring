package com.diascosta.monitoring;

import org.apache.kafka.clients.producer.*;
import java.util.Date;

public class KafkaProducerListener implements ProducerInterceptor<String, String> {

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        String uuid = UUIDGenerator.generateUUID();
        Date entryTime = new Date();

        // Registrar o UUID e a data de entrada no ELK
        TibcoKafkaMonitor.logMessageEntryToELK(uuid, entryTime);

        // Adicionar o UUID ao tópico Kafka
        record.headers().add("UUID", uuid.getBytes());
        return record;
    }

    @Override
    public void close() {}

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {}

    @Override
    public void configure(Map<String, ?> configs) {}
}
