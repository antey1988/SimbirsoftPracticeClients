package com.example.SimbirsoftPricticeClients.services;

import com.example.SimbirsoftPricticeClients.dto.ClientRequestDto;
import com.example.SimbirsoftPricticeClients.dto.ClientResponseDto;
import com.example.SimbirsoftPricticeClients.dto.PaymentProjectRequestDto;

public interface ClientService {
    /**
     * Метод для создания клиента через сторонние сервисы
     * @param client объект, содержащий имя клиента и уникальный идентификатор, для возможности в дальнейшем производить операции с счетами клиента из строннего сервиса
     * @return ответ об успешном создании клиента
     */
    String createClient(ClientRequestDto client);

    /**
     * Метод извлечения информации, предоставляется самому клиенту для проверки состояния счета и операциях по нему
     * @param id
     * @return полная информация: имя клиента, номер лицевого счета и баланс, список всех операций по счету
     */
    ClientResponseDto readClient(Long id);

    /**
     * Метод пополнения баланса лицевого счета
     * @param id
     * @param amount сумма пополнения
     * @return обновленная инфомация о клиенте
     */
    ClientResponseDto depositMoney(Long id, String amount);

    /**
     * Метод списания средств с лицевого счета, используется сторонним сервисом при открытии проекта
     * @param history передаваемая инфомация: uuid клиента, наименование проекта и стоимост проекта
     * @return ответ об успешном списании средств
     */
    String payProject(PaymentProjectRequestDto history);
}
