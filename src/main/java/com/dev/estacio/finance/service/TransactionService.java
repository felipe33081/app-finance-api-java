package com.dev.estacio.finance.service;

import com.dev.estacio.finance.mapper.TransactionMapper;
import com.dev.estacio.finance.model.Transaction;
import com.dev.estacio.finance.model.dto.request.TransactionRequest;
import com.dev.estacio.finance.model.dto.response.ExpensesByCategoryResponse;
import com.dev.estacio.finance.model.dto.response.MonthlyBalanceResponse;
import com.dev.estacio.finance.model.dto.response.TransactionResponse;
import com.dev.estacio.finance.model.enums.Category;
import com.dev.estacio.finance.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.dev.estacio.finance.model.enums.AccountType.EXPENSES;
import static com.dev.estacio.finance.model.enums.AccountType.INCOME;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;
    private final UserService userService;

    @Transactional
    public TransactionResponse save(TransactionRequest transactionRequest) {
        var user = userService.findById(transactionRequest.userId());
        var transaction = TransactionMapper.instance.toEntity(transactionRequest);
        transaction.setUser(user);
        return TransactionMapper.instance.toResponse(repository.save(transaction));
    }

    public List<TransactionResponse> findTransactionsByUserId(Long userId) {
        return TransactionMapper.instance.toResponseList(repository.findTransactionByUserId(userId));
    }

    @Transactional
    public TransactionResponse update(Long transactionId, TransactionRequest transactionRequest) {
        var transaction = TransactionMapper.instance.toEntity(transactionRequest);
        transaction.setId(transactionId);
        return TransactionMapper.instance.toResponse(repository.save(transaction));
    }

    @Transactional
    public void delete(Long transactionId) {
        repository.deleteById(transactionId);
    }

    public MonthlyBalanceResponse calculateMonthlyBalance(Long userId, Integer mes, Integer ano) {
        List<Transaction> transactions = repository.findTransactionsByUserIdAndMonth(userId, mes, ano);

        Double totalIncome = transactions.stream()
                .filter(transaction -> transaction.getAccountType() == INCOME)
                .mapToDouble(Transaction::getAmount)
                .sum();

        Double totalExpenses = transactions.stream()
                .filter(transaction -> transaction.getAccountType() == EXPENSES)
                .mapToDouble(Transaction::getAmount)
                .sum();

        Double totalBalance = totalIncome - totalExpenses;

        return new MonthlyBalanceResponse(totalBalance, totalIncome, totalExpenses);
    }

    public List<ExpensesByCategoryResponse> getExpensesByCategory(Long userId, int mes, int ano) {
        List<Transaction> transactions = repository.findTransactionsByUserIdAndMonth(userId, mes, ano);

        Map<Category, Double> expensesByCategory = new HashMap<>();

        for (Category category : Category.values()) {
            if (category != Category.SALARY) {
                expensesByCategory.put(category, 0.0);
            }
        }

        transactions.stream()
                .filter(transaction -> transaction.getAccountType() == EXPENSES)
                .forEach(transaction -> expensesByCategory.merge(transaction.getCategory(), transaction.getAmount(), Double::sum));

        return expensesByCategory.entrySet().stream()
                .map(entry -> new ExpensesByCategoryResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
