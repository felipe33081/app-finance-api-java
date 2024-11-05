package com.dev.estacio.finance.api.rest;

import com.dev.estacio.finance.model.dto.request.TransactionRequest;
import com.dev.estacio.finance.model.dto.response.ExpensesByCategoryResponse;
import com.dev.estacio.finance.model.dto.response.MonthlyBalanceResponse;
import com.dev.estacio.finance.model.dto.response.TransactionResponse;
import com.dev.estacio.finance.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.*;

@RestController
@AllArgsConstructor
@RequestMapping("/transacoes")
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    public ResponseEntity<TransactionResponse> save(@Valid @RequestBody TransactionRequest transaction) {
        return status(CREATED).body(service.save(transaction));
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<TransactionResponse>> findTransactionsByUserId(@PathVariable("userId") Long userId) {
        return ok(service.findTransactionsByUserId(userId));
    }

    @GetMapping("/usuario/{userId}/saldo")
    public ResponseEntity<MonthlyBalanceResponse> getMonthlyBalance(
            @PathVariable("userId") Long userId,
            @RequestParam("mes") Integer mes,
            @RequestParam("ano") Integer ano) {
        return ok(service.calculateMonthlyBalance(userId, mes, ano));
    }

    @GetMapping("/usuario/{userId}/despesas-categoria")
    public ResponseEntity<List<ExpensesByCategoryResponse>> getExpensesByCategory(
            @PathVariable("userId") Long userId,
            @RequestParam("mes") Integer mes,
            @RequestParam("ano") Integer ano) {
        return ok(service.getExpensesByCategory(userId, mes, ano));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> update(@PathVariable("id") Long transactionId,
                                                      @Valid @RequestBody TransactionRequest transaction) {
        return ok(service.update(transactionId, transaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return noContent().build();
    }
}
