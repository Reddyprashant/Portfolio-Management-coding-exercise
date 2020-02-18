package com.fidelity.portfoliomanagement.dao;

import com.fidelity.portfoliomanagement.model.User;
import org.springframework.data.repository.CrudRepository;


public interface AccountDetailsRepository extends CrudRepository<User, String> {


}
