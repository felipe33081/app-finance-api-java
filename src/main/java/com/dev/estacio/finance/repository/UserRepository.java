package com.dev.estacio.finance.repository;

import com.dev.estacio.finance.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.name = :name")
    User findByName(@Param("name") String name);

    @Query("""
           SELECT u
           FROM User u
           WHERE u.email = :email
             AND u.password = :password
           """)
    Optional<User> findUserByEmailAndPassword(@Param("email") String email,
                                              @Param("password") String password);

}
