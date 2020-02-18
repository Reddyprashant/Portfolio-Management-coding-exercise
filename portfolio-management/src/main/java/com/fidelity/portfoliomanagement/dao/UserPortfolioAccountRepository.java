package com.fidelity.portfoliomanagement.dao;

import com.fidelity.portfoliomanagement.model.UserPortfolioAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserPortfolioAccountRepository extends CrudRepository<UserPortfolioAccount, String> {
}
