package sk.posam.fsa.moneymate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.posam.fsa.moneymate.domain.Account;
import sk.posam.fsa.moneymate.domain.service.AccountActionsService;
import sk.posam.fsa.moneymate.domain.transaction.Transaction;
import sk.posam.fsa.moneymate.mapper.AccountMapper;
import sk.posam.fsa.moneymate.mapper.TransactionMapper;
import sk.posam.fsa.moneymate.rest.api.AccountsApi;
import sk.posam.fsa.moneymate.rest.dto.AccountDto;
import sk.posam.fsa.moneymate.rest.dto.TransactionDto;
import sk.posam.fsa.moneymate.security.CurrentUserDetailService;

import java.util.Collections;
import java.util.List;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class AccountActionsRestController implements AccountsApi {

    private final AccountMapper accountMapper;
    private final TransactionMapper transactionMapper;
    private final AccountActionsService accountActionsService;
    private final CurrentUserDetailService currentUserDetailService;

    public AccountActionsRestController(AccountActionsService accountActionsService,
                                        AccountMapper accountMapper, TransactionMapper transactionMapper,
                                        CurrentUserDetailService currentUserDetailService1) {
        this.accountActionsService = accountActionsService;
        this.accountMapper = accountMapper;
        this.transactionMapper = transactionMapper;
        this.currentUserDetailService = currentUserDetailService1;
    }

    @Override
    public ResponseEntity<Void> addTransaction(Long accountId, TransactionDto transactionDto) {

        accountActionsService.createTransaction(accountId,transactionMapper.toEntity(transactionDto));
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<Void> deleteTransactionById(Long accountId, Long transactionId) {
        return null;
    }

    @Override
    public ResponseEntity<AccountDto> getAccount(Long accountId) {
        Account account = accountActionsService.findAccountById(accountId);
        return ResponseEntity.ok().body(accountMapper.toAccountDto(account));
    }

    @Override
    public ResponseEntity<List<TransactionDto>> listTransactions() {
        List<Transaction> transactions = accountActionsService
                .findTransactionsByUser(currentUserDetailService.getFullCurrentUser());

        return transactions != null ? ResponseEntity.ok()
                .body(transactions.stream()
                        .map(transactionMapper::toDto)
                        .toList())
                : ResponseEntity.ok().body(Collections.emptyList());
    }

    @Override
    public ResponseEntity<List<TransactionDto>> listTransactionsByAccount(Long accountId) {
        List<Transaction> transactions = accountActionsService.findTransactionsByAccount(accountId);

        return transactions != null ? ResponseEntity.ok()
                .body(transactions.stream()
                        .map(transactionMapper::toDto)
                        .toList())
                : ResponseEntity.ok().body(Collections.emptyList());
    }

    @Override
    public ResponseEntity<Void> updateTransaction(Long accountId, TransactionDto transactionDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> createAccount(AccountDto accountDto) {
        Account account = accountMapper.toEntity(accountDto);
        account.setCreatedBy(currentUserDetailService.getFullCurrentUser());
        accountActionsService.createAccount(account);
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<Void> deleteAccount(Long accountId) {
        accountActionsService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<List<AccountDto>> listAccounts() {
        List<Account> accounts = accountActionsService.findAccountsByUser(currentUserDetailService.getFullCurrentUser());

        return accounts != null ? ResponseEntity.ok()
                .body(accounts.stream()
                        .map(accountMapper::toAccountDto)
                        .toList())
                : ResponseEntity.ok().body(Collections.emptyList());
    }

    @Override
    public ResponseEntity<Void> updateAccount(AccountDto accountDto) {
        accountActionsService.updateAccount(accountMapper.toEntity(accountDto));
        return ResponseEntity.ok().build();
    }
}
