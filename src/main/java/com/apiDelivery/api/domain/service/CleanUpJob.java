package com.apiDelivery.api.domain.service;

import com.apiDelivery.api.domain.model.CleanUp;
import com.apiDelivery.api.domain.repository.CleanUpRepository;
import com.apiDelivery.api.domain.repository.EstadoRepository;
import com.apiDelivery.api.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CleanUpJob {

    @Autowired
    private CleanUpRepository cleanUpRepository;

    @Autowired
    private RestauranteRepository restauranteRepository; // Repositório do Restaurante

    @Autowired
    private EstadoRepository estadoRepository;

    @Scheduled(cron = "0 42 21 * * *")
    public void cleanUpTables() {
        System.out.println("Método cleanUpTables() foi chamado!");
        List<CleanUp> cleanUpList = cleanUpRepository.findByActiveTrue();
        for (CleanUp cleanUp : cleanUpList) {
            System.out.println("PEgouuuuuuuuu");
            if (needsCleaning(cleanUp)) {
                cleanTable(cleanUp);
            }
        }
    }

    private boolean needsCleaning(CleanUp cleanUp) {
        char periodicity = cleanUp.getPeriodicity();
        LocalDateTime lastExecutionDateTime = cleanUp.getLastExecutionDate(); // Data/hora da última execução
        LocalDateTime currentDateTime = LocalDateTime.now(); // Data/hora atual

        // Verifica se a última execução ocorreu antes da data/hora atual
        boolean lastExecutionBeforeNow = lastExecutionDateTime.isBefore(currentDateTime);

        // Log das datas para fins de depuração
        System.out.println("Last execution date/time: " + lastExecutionDateTime);
        System.out.println("Current date/time: " + currentDateTime);

        switch (periodicity) {
            case 'D':
                // Limpeza diária: limpa se a última execução foi antes da data/hora atual
                return lastExecutionBeforeNow;
            case 'M':
                // Limpeza mensal: limpa se a diferença em meses entre a última execução e a data/hora atual for maior ou igual a 1
                long monthsSinceLastExecution = ChronoUnit.MONTHS.between(lastExecutionDateTime, currentDateTime);
                return monthsSinceLastExecution >= 1;
            case 'S':
                // Limpeza semanal: limpa se a diferença em semanas entre a última execução e a data/hora atual for maior ou igual a 1
                long weeksSinceLastExecution = ChronoUnit.WEEKS.between(lastExecutionDateTime, currentDateTime);
                return weeksSinceLastExecution >= 1;
            case 'H':
                // Limpeza horária: limpa se a diferença em horas entre a última execução e a data/hora atual for maior ou igual a 1
                long hoursSinceLastExecution = ChronoUnit.HOURS.between(lastExecutionDateTime, currentDateTime);
                return hoursSinceLastExecution >= 1;
            default:
                throw new IllegalArgumentException("Invalid periodicity: " + periodicity);
        }
    }
    public void cleanTable(CleanUp cleanUp) {
        String tableName = cleanUp.getTableName();
        boolean isActive = isRestauranteActive();

        if (isActive) {
            cleanRestauranteData();
        }
    }

    private boolean isRestauranteActive() {
        // Você pode implementar a lógica para verificar se há pelo menos um restaurante ativo no banco de dados
        // Aqui, por exemplo, retornamos verdadeiro para fins de demonstração
        return true;
    }

    private void cleanRestauranteData() {
        estadoRepository.deleteAll();
    }
}