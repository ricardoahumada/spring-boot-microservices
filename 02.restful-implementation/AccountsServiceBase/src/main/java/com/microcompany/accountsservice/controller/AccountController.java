package com.microcompany.accountsservice.controller;

import com.microcompany.accountsservice.model.Account;
import com.microcompany.accountsservice.payload.ApiResponse;
import com.microcompany.accountsservice.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    //Create Account
    @PostMapping
    public ResponseEntity<Account> createAccount(
            @RequestBody Account account
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.create(account));
    }

    // Get single Account Details
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(
            @PathVariable Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccount(id));
    }

    // Get Accounts using Customer ID

    // Get all Accounts
    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    // update account
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(
            @RequestBody Account account,
            @PathVariable @Min(1) Long id
    ) {
        account.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountService.updateAccount(id, account));
    }

    // Add Money
    @PutMapping("/addmoney/{id}")
    public ResponseEntity<Account> addMoney(
            @PathVariable Long id,
            @RequestParam int amount,
            @RequestParam Long ownerId
    ) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountService.addBalance(id, amount, ownerId));
    }

    // withdraw Money
    @PutMapping("/withdraw/{id}")
    public ResponseEntity<Account> withdraw(
            @PathVariable Long id,
            @RequestParam int amount,
            @RequestParam Long ownerId
    ) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountService.withdrawBalance(id, amount, ownerId));
    }


    // Delete Account

    @DeleteMapping("/{id}")
    public ApiResponse deleteAccount(
            @PathVariable @Min(1) Long id
    ) {
        this.accountService.delete(id);
        return new ApiResponse("Account is Successfully Deleted", true);
    }

    // Delete Account using ownerId
    @DeleteMapping("user/{ownerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ApiResponse deleteAccountByUserId(
            @PathVariable Long ownerId
    ) {
        this.accountService.deleteAccountsUsingOwnerId(ownerId);
        return new ApiResponse(" Accounts with given userId is deleted Successfully", true);

    }

}
