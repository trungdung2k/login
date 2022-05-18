package com.example.finalexam.repository;

import com.example.finalexam.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> , CustomAccountRepository {

    Optional<Account> findByIdAndDeletedFalse(Long id);

    boolean existsByUserNameAndIdNot( String userName , Long id);


    Account findByUserName(String userName);

    boolean existsAccountByUserName (String userName);
}
