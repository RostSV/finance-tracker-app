package sk.posam.fsa.moneymate.mapper;

import org.springframework.stereotype.Component;
import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.AccountType;
import sk.posam.fsa.moneymate.rest.dto.AccountDto;
import sk.posam.fsa.moneymate.rest.dto.AccountTypeDto;
@Component
public class AccountMapper {

    private final UserMapper userMapper;
    private final CurrencyMapper currencyMapper;

    public AccountMapper(UserMapper userMapper, CurrencyMapper currencyMapper) {
        this.userMapper = userMapper;
        this.currencyMapper = currencyMapper;
    }

    public Account toEntity(AccountDto accountDto) {

        if (accountDto == null) {
            return null;
        }

        Account entity = new Account();
        entity.setId(accountDto.getId());
        entity.setName(accountDto.getName());
        entity.setDescription(accountDto.getDescription());
        entity.setBalance(accountDto.getBalance());
        entity.setType(AccountType.valueOf(accountDto.getType().name()));
        entity.setCurrency(currencyMapper.toEntity(accountDto.getCurrency()));
        entity.setCreatedBy(userMapper.toUserEntity(accountDto.getCreatedBy()));

        return entity;
    }

    public AccountDto toAccountDto(Account account) {
        if (account == null) {
            return null;
        }

        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setName(account.getName());
        accountDto.setDescription(account.getDescription());
        accountDto.setBalance(account.getBalance());
        accountDto.setType(AccountTypeDto.valueOf(account.getType().name()));
        accountDto.setCurrency(currencyMapper.toDto(account.getCurrency()));
        accountDto.setCreatedBy(userMapper.toUserDto(account.getCreatedBy()));

        return accountDto;
    }
}
