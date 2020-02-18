package com.fidelity.portfoliomanagement.controller;


import com.fidelity.portfoliomanagement.model.User;
import com.fidelity.portfoliomanagement.service.IPortfolioManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.Map;
import java.util.TreeMap;

@RestController
@Api(description = "Poftolio Model related endpoints")
public class PortfolioController {



    private Map<String, Object> map = new TreeMap<String, Object>();

    @Autowired
    private IPortfolioManagementService iPortfolioManagementService;

    @PostMapping(path = "/account", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Find Portfolio Model by User details",
            notes = "Return a Portfolio Model or throws 400")
    public ResponseEntity<Map> saveAccount(@RequestHeader HttpHeaders requestHeaders, @RequestBody User user) {
        try {
            User userDetails = iPortfolioManagementService.saveAccountDetails(user);
            map.put("Account Id", userDetails.getId());
            map.put("First Name", userDetails.getFirstName());
            map.put("Last Name", userDetails.getLastName());
            map.put("Model Type", userDetails.getUserPortfolio().getModelName());
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.put("message: ", ex.getMessage());
            return new ResponseEntity<Map>(map, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(path = "/account/{id}", produces = "application/json")
    @ApiOperation(value = "Find User details and Portfolio Model of User by Account Id",
            notes = "Return a Portfolio Model  and User details or throws 404")
    public ResponseEntity<Object> getPortfolioModelDetails(@PathVariable(value = "id") String id) {
        try {
            return new ResponseEntity<Object>(iPortfolioManagementService.portfolioModelDetails(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
