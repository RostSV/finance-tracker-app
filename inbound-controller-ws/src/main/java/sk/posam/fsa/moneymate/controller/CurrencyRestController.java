package sk.posam.fsa.moneymate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.posam.fsa.moneymate.domain.Currency;
import sk.posam.fsa.moneymate.domain.service.CurrencyFacade;
import sk.posam.fsa.moneymate.mapper.CurrencyMapper;
import sk.posam.fsa.moneymate.rest.api.CurrenciesApi;
import sk.posam.fsa.moneymate.rest.dto.CurrencyDto;
import sk.posam.fsa.moneymate.security.CurrentUserDetailService;

import java.util.Collections;
import java.util.List;

//RestController for Currency version 1.0
@RestController
@RequestMapping("/api/v1")
public class CurrencyRestController implements CurrenciesApi {

    private final CurrencyFacade currencyService;
    private final CurrentUserDetailService currentUserDetailService;
    private final CurrencyMapper currencyMapper;


    public CurrencyRestController(CurrencyFacade currencyService,
                                  CurrentUserDetailService currentUserDetailService, CurrencyMapper currencyMapper) {
        this.currencyService = currencyService;
        this.currentUserDetailService = currentUserDetailService;
        this.currencyMapper = currencyMapper;
    }


    @Override
    public ResponseEntity<Void> createCurrency(CurrencyDto currencyDto) {

        currencyService.create(currencyMapper.toEntity(currencyDto));

        return ResponseEntity.ok().build();

    }

    @Override
    public ResponseEntity<List<CurrencyDto>> listCurrencies() {

        if (!currentUserDetailService.isAdmin()) {
            return ResponseEntity.status(403).build();
        }

        List<Currency> allCurrencies = currencyService.findAll();

        return allCurrencies != null ? ResponseEntity.ok()
                .body(allCurrencies.stream()
                        .map(currencyMapper::toDto)
                        .toList())
                : ResponseEntity.ok().body(Collections.emptyList());
    }
}
