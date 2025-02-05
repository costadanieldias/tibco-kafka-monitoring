package com.diascosta.monitoring;

import javax.jms.*;
import java.util.Date;

public class TibcoProducerListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        String uuid = UUIDGenerator.generateUUID();
        Date entryTime = new Date();

        // Registrar o UUID e a data de entrada no ELK
        TibcoKafkaMonitor.logMessageEntryToELK(uuid, entryTime);

        // Lógica de envio da mensagem para a fila Tibco EMS
        try {
            // message.setStringProperty("UUID", uuid); // Exemplo de envio do UUID para a mensagem
            // producer.send(message); // Exemplo de envio da mensagem
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
