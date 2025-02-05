package com.diascosta.monitoring;

import javax.jms.*;
import java.util.Date;

public class TibcoConsumerListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            String uuid = message.getStringProperty("UUID");
            Date exitTime = new Date();

            // Atualizar o tempo de saída e o tempo de permanência na fila
            TibcoKafkaMonitor.logMessageExitToELK(uuid, exitTime);

            // Lógica para processamento pós-consumo da mensagem
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
