package com.example.SimbirsoftPricticeClients.mappers;

import com.example.SimbirsoftPricticeClients.dto.ClientResponseDto;
import com.example.SimbirsoftPricticeClients.dto.OperationResponseDto;
import com.example.SimbirsoftPricticeClients.entities.Client;
import com.example.SimbirsoftPricticeClients.entities.Operation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "operations", source = "operations", qualifiedByName = "mappingOperationsEntityToOperationsDto")
    ClientResponseDto entityToDto(Client client);

    @Named("mappingOperationsEntityToOperationsDto")
    default List<OperationResponseDto> mappingOperationsEntityToOperationsDto(Set<Operation> operations) {
        if (operations == null) {
            return null;
        }
        List<OperationResponseDto> _operations = new ArrayList<>();
        for (Operation operation : operations) {
            OperationResponseDto _operation = new OperationResponseDto();
            _operation.setData(operation.getData());
            _operation.setAmount(operation.getAmount());
            _operation.setType(operation.getType().getDescription());
            _operation.setDescription(operation.getDescription());
            _operations.add(_operation);
        }
        Collections.sort(_operations);
        return _operations;
    }
}
