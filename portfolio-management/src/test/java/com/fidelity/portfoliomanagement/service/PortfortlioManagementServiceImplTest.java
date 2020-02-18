package com.fidelity.portfoliomanagement.service;

import com.fidelity.portfoliomanagement.dao.AccountDetailsRepository;
import com.fidelity.portfoliomanagement.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.math.BigDecimal;
import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PortfortlioManagementServiceImplTest {

    @TestConfiguration
    static class PortfortlioManagementServiceImplTestConfiguration {

        @Bean
        public IPortfolioManagementService iPortfolioManagementService() {
            return new PortfortlioManagementServiceImpl();
        }
    }


    @Autowired
    private IPortfolioManagementService iPortfolioManagementService;

    @MockBean
    private AccountDetailsRepository accountDetailsRepository;

    @Test
    void saveAccount() {
        // Given an account
        User user = createAccount();

        Mockito.when(accountDetailsRepository.save(user))
                .then(invocationOnMock -> {
                    user.setId("asdeqw");
                    return user;
                });
        // when saveAccountdetails is invoked
        User newUser = iPortfolioManagementService.saveAccountDetails(user);
        // Then verify that the id is not null
        assertNotNull(newUser.getId(), "new user id should exist");

        // Then verify that the id is same as the mock id
        assertEquals("asdeqw", newUser.getId(), "user id should be same");

        // Then verify that the repository.save() was called 1 times
        Mockito.verify(accountDetailsRepository, Mockito.times(1))
                .save(user);
    }

    private User createAccount() {
        User user = new User();
        user.setId(null);
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setDateOfBirth(LocalDate.of(1980, 02, 02));
        user.setAsset(BigDecimal.valueOf(10000));
        return user;
    }


}