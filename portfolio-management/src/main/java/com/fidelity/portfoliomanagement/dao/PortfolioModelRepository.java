package com.fidelity.portfoliomanagement.dao;

import com.fidelity.portfoliomanagement.model.PortfolioModel;
import org.springframework.data.repository.CrudRepository;

public interface PortfolioModelRepository extends CrudRepository<PortfolioModel, String> {
}
