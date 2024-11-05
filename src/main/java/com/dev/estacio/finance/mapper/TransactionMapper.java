package com.dev.estacio.finance.mapper;

import com.dev.estacio.finance.model.Transaction;
import com.dev.estacio.finance.model.dto.request.TransactionRequest;
import com.dev.estacio.finance.model.dto.response.TransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransactionMapper {

    TransactionMapper instance = Mappers.getMapper(TransactionMapper.class);

    Transaction toEntity(TransactionRequest transactionRequest);

    @Mapping(source = "user.id", target = "userId")
    TransactionResponse toResponse(Transaction transaction);

    List<TransactionResponse> toResponseList(List<Transaction> transactions);

}
