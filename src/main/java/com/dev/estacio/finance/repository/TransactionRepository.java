package com.dev.estacio.finance.repository;

import com.dev.estacio.finance.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId")
    List<Transaction> findTransactionByUserId(@Param("userId") Long userId);

    @Query("""
           SELECT t
           FROM Transaction t
           WHERE t.user.id = :userId
             AND MONTH(t.date) = :mes
             AND YEAR(t.date) = :ano
           """)
    List<Transaction> findTransactionsByUserIdAndMonth(@Param("userId") Long userId,
                                                       @Param("mes") Integer mes,
                                                       @Param("ano") Integer ano);

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId AND MONTH(t.date) = :mes AND YEAR(t.date) = :ano")
    List<Transaction> findTransactionByUserIdAndMonthAndYear(@Param("userId") Long userId, @Param("mes") Integer mes, @Param("ano") Integer ano);
}
