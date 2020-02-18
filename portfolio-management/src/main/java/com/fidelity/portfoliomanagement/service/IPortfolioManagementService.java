package com.fidelity.portfoliomanagement.service;

import com.fidelity.portfoliomanagement.model.User;

public interface IPortfolioManagementService {

    public User saveAccountDetails(User user);

    public User portfolioModelDetails(String id);
}
