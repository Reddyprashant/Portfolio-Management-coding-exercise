package com.fidelity.portfoliomanagement.service;

import com.fidelity.portfoliomanagement.dao.AccountDetailsRepository;
import com.fidelity.portfoliomanagement.dao.PortfolioModelRepository;
import com.fidelity.portfoliomanagement.dao.UserPortfolioAccountRepository;
import com.fidelity.portfoliomanagement.exception.BadRequestException;
import com.fidelity.portfoliomanagement.model.PortfolioModel;
import com.fidelity.portfoliomanagement.model.User;
import com.fidelity.portfoliomanagement.model.UserPortfolioAccount;
import com.fidelity.portfoliomanagement.utility.PortfolioUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class PortfortlioManagementServiceImpl implements IPortfolioManagementService {

    @Autowired
    AccountDetailsRepository accountDetailsRepository;

    @Autowired
    PortfolioModelRepository portfolioModelRepository;

    @Autowired
    UserPortfolioAccountRepository userPortfolioAccountRepository;

    /* This API returns the appropriate Portfolio Model of the input user based on the users age*/
    @Override
    public User saveAccountDetails(User user) {
        UserPortfolioAccount userPortfolio = user.getUserPortfolio();
        if (!user.getDateOfBirth().isAfter(LocalDate.now())) {
            try {
                int age = PortfolioUtility.calculateAge(user.getDateOfBirth());

                Iterable<PortfolioModel> portfolioModel = portfolioModelRepository.findAll();

                for (PortfolioModel portfolioModel1 : portfolioModel) {
                    if (age >= portfolioModel1.getMinAge() && age <= portfolioModel1.getMaxAge()) {
                        String modelName = portfolioModel1.getModelName();
                        userPortfolio.setModelName(modelName);
                        userPortfolio.setEquity((user.getAsset().multiply(new BigDecimal(portfolioModel1.getEquity() / 100.0))).setScale(5, RoundingMode.FLOOR));
                        userPortfolio.setBonds((user.getAsset().multiply(new BigDecimal(portfolioModel1.getBonds() / 100.0))).setScale(5, RoundingMode.FLOOR));
                        userPortfolio.setCash((user.getAsset().multiply(new BigDecimal(portfolioModel1.getCash() / 100.0))).setScale(5, RoundingMode.FLOOR));
                    }
                }

                userPortfolioAccountRepository.save(userPortfolio);
                accountDetailsRepository.save(user);
            } catch (Exception ex) {
                throw new BadRequestException("Please enter the all the required details of the user");
            }
            return user;
        } else {
            throw new BadRequestException("Please enter the correct Date Of Birth in MM-dd-yyyy format");
        }

    }


    /* This API accepts accountId and finds the user with that accountId in database and returns the User if present else throws NOT FOUND error*/
    @Override
    public User portfolioModelDetails(String id) {

        Optional<User> user = accountDetailsRepository.findById(id);
        if (!user.isPresent()) {
            throw new ResourceAccessException("User Account with id " + id + " doesn't exist.");
        }
        return user.get();
    }
}
