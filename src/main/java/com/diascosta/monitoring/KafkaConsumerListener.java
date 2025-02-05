package com.diascosta.monitoring;

import org.apache.kafka.clients.consumer.*;
import java.util.Date;
import org.apache.kafka.common.header.Header;

public class KafkaConsumerListener implements ConsumerInterceptor<String, String> {

    @Override
    public ConsumerRecord<String, String> onConsume(ConsumerRecord<String, String> record) {
        String uuid = new String(record.headers().lastHeader("UUID").value());
        Date exitTime = new Date();

        // Atualizar o tempo de saída e o tempo de permanência na fila
        TibcoKafkaMonitor.logMessageExitToELK(uuid, exitTime);

        return record;
    }

    @Override
    public void close() {}

    @Override
    public void configure(Map<String, ?> configs) {}
}
