package com.diascosta.monitoring;

import java.util.Date;

public class ElasticsearchClient {

    public static void sendToElk(String uuid, Date entryTime, Date exitTime, long timeInQueue) {
        // Lógica para enviar o registro de entrada no ELK (com UUID e data de entrada)
    }

    public static Date getMessageEntryTime(String uuid) {
        // Lógica para buscar o tempo de entrada no ELK
        return new Date(); // Exemplo de retorno, deve implementar a consulta real ao ELK
    }

    public static void updateMessageExitTime(String uuid, Date exitTime, long timeInQueue) {
        // Lógica para atualizar o tempo de saída e o tempo na fila no ELK
    }
}
