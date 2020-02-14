package com.netcrackerTask.backend.business.persistence;

import com.netcrackerTask.backend.business.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
   List<Account> getAccountsByGameId(Long id);
}
