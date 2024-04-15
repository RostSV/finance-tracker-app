package sk.posam.fsa.moneymate.mapper;

import org.springframework.stereotype.Component;
import sk.posam.fsa.moneymate.domain.transaction.Transaction;
import sk.posam.fsa.moneymate.domain.transaction.TransactionType;
import sk.posam.fsa.moneymate.rest.dto.TransactionDto;
import sk.posam.fsa.moneymate.rest.dto.TransactionTypeDto;

import java.time.ZoneOffset;
@Component
public class TransactionMapper {

    private final CurrencyMapper currencyMapper;
    private final CategoryMapper categoryMapper;
    private final AccountMapper accountMapper;

    public TransactionMapper(CurrencyMapper currencyMapper, CategoryMapper categoryMapper, AccountMapper accountMapper) {
        this.currencyMapper = currencyMapper;
        this.categoryMapper = categoryMapper;
        this.accountMapper = accountMapper;
    }

    public TransactionDto toDto(Transaction transaction) {

        if(transaction == null){
            return null;
        }

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setDate(transaction.getDate().atOffset(ZoneOffset.UTC));
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setDescription(transaction.getDescription());
        transactionDto.setCategory(categoryMapper.toDto(transaction.getCategory()));
        transactionDto.setType(TransactionTypeDto.valueOf(transaction.getType().name()));
        transactionDto.setCurrency(currencyMapper.toDto(transaction.getCurrency()));
        transactionDto.setAssignedTo(accountMapper.toAccountDto(transaction.getAssignedTo()));


        return transactionDto;
    }

    public Transaction toEntity(TransactionDto transactionDto) {

        if (transactionDto == null) {
            return null;
        }

        Transaction transaction = new Transaction();
        transaction.setId(transactionDto.getId());
        transaction.setDate(transactionDto.getDate() != null ? transactionDto.getDate().toLocalDateTime() : null);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setCategory(categoryMapper.toEntity(transactionDto.getCategory()));
        transaction.setType(TransactionType.valueOf(transactionDto.getType().name()));
        transaction.setCurrency(currencyMapper.toEntity(transactionDto.getCurrency()));
        transaction.setAssignedTo(accountMapper.toEntity(transactionDto.getAssignedTo()));

        return transaction;
    }

}
