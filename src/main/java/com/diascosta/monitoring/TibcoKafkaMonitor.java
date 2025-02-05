package com.diascosta.monitoring;

import java.util.UUID;
import java.util.Date;
import com.diascosta.utils.UUIDGenerator;
import com.diascosta.utils.TimeUtils;

public class TibcoKafkaMonitor {

    private static final String ELK_INDEX = "message-processing"; // Índice do ELK para mensagens

    /**
     * Registra a entrada da mensagem no ELK.
     *
     * @param uuid O UUID gerado para a mensagem.
     * @param entryTime A data e hora de entrada na fila.
     */
    public static void logMessageEntryToELK(String uuid, Date entryTime) {
        // Envia os dados de entrada para o ELK
        ElasticsearchClient.sendToElk(uuid, entryTime, null, 0);
    }

    /**
     * Atualiza o tempo de saída da mensagem no ELK e calcula o tempo total.
     *
     * @param uuid O UUID da mensagem.
     * @param exitTime A data e hora de saída da fila.
     */
    public static void logMessageExitToELK(String uuid, Date exitTime) {
        // Buscar o registro de entrada no ELK usando o UUID
        Date entryTime = ElasticsearchClient.getMessageEntryTime(uuid);

        if (entryTime != null) {
            // Calcular o tempo de permanência na fila
            long timeInQueue = TimeUtils.calculateTimeDelta(entryTime, exitTime);
            // Atualizar o registro no ELK com o tempo de saída
            ElasticsearchClient.updateMessageExitTime(uuid, exitTime, timeInQueue);
        }
    }
}
