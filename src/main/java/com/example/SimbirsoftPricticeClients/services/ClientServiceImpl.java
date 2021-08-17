package com.example.SimbirsoftPricticeClients.services;

import com.example.SimbirsoftPricticeClients.entities.*;
import com.example.SimbirsoftPricticeClients.mappers.ClientMapper;
import com.example.SimbirsoftPricticeClients.dto.ClientRequestDto;
import com.example.SimbirsoftPricticeClients.dto.ClientResponseDto;
import com.example.SimbirsoftPricticeClients.dto.PaymentProjectRequestDto;
import com.example.SimbirsoftPricticeClients.exceptions.NotEnoughMoneyException;
import com.example.SimbirsoftPricticeClients.exceptions.NotFoundException;
import com.example.SimbirsoftPricticeClients.repos.ClientRepository;
import com.example.SimbirsoftPricticeClients.repos.OperationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {
    private static final String MINUS = "Оплата проекта %s";
    private static final String PLUS = "Внесение средств";


    private final ClientRepository clientRepository;
    private final OperationRepository operationRepository;
    private final ClientMapper mapper;
    private final Random random = new Random();

    public ClientServiceImpl(ClientRepository repository, OperationRepository operationRepository, ClientMapper mapper) {
        this.clientRepository = repository;
        this.operationRepository = operationRepository;
        this.mapper = mapper;
    }

    @Override
    public String createClient(ClientRequestDto client) {
        Client _client = new Client();
        _client.setName(client.getName());
        _client.setUuid(client.getUuid());
        _client.setBankAccount(generateBankAccount());
        clientRepository.save(_client);
        return "Клиент успешно создан в сервесе оплаты";
    }

    @Override
    public ClientResponseDto readClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Клиент с id = %d не найден", id)));
        return mapper.entityToDto(client);
    }

    @Override
    @Transactional
    public ClientResponseDto depositMoney(Long id, String amount) {
        BigDecimal _amount = new BigDecimal(amount);
        Client _client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Клиент с id = %d не найден", id)));
        BankAccount _bankAccount = _client.getBankAccount();
        _bankAccount.setBalance(_bankAccount.getBalance().add(_amount));

        Operation _operation = new Operation();
        _operation.setType(Operation.TypeOperation.PLUS);
        _operation.setAmount(_amount);
        _operation.setData(new Date());
        _operation.setDescription(PLUS);
        _operation.setClient(_client);
        _operation = operationRepository.save(_operation);

        _client.getOperations().add(_operation);
        return mapper.entityToDto(_client);
    }


    @Override
    @Transactional
    public String payProject(PaymentProjectRequestDto history) {
        UUID uuid = history.getUuid();
        Client _client = clientRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException(String.format("Клиент с uuid = %d не найден", uuid)));
        BankAccount _bankAccount = _client.getBankAccount();
        BigDecimal balance = _bankAccount.getBalance();
        BigDecimal _amount = history.getPrice();
        if (balance.compareTo(_amount) < 0) {
            throw  new NotEnoughMoneyException("У клиента не достаточно средств на счете для оплаты проекта");
        }
        _bankAccount.setBalance(balance.subtract(_amount));

        Operation _operation = new Operation();
        _operation.setType(Operation.TypeOperation.MINUS);
        _operation.setAmount(_amount);
        _operation.setData(new Date());
        _operation.setDescription(String.format(MINUS, history.getName()));
        _operation.setClient(_client);
        _operation = operationRepository.save(_operation);

        _client.getOperations().add(_operation);
        return "Произведено списание средств со счета клиента за работу по проекту";
    }

    private BankAccount generateBankAccount() {
        String number;
        do {
            long l = (long)(random.nextDouble() * 1000_0000_0000_0000L);
            number = String.format("1%015d", l);
        } while (clientRepository.findByBankAccountNumber(number).isPresent());

        BankAccount bankAccount = new BankAccount();
        bankAccount.setNumber(number);
        bankAccount.setBalance(new BigDecimal("0.00"));
        return bankAccount;
    }
}
