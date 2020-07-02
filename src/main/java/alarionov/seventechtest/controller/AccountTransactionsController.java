package alarionov.seventechtest.controller;

import alarionov.seventechtest.dto.DepositRequest;
import alarionov.seventechtest.dto.TransferRequest;
import alarionov.seventechtest.dto.WithdrawalRequest;
import alarionov.seventechtest.exception.AccountNotFoundException;
import alarionov.seventechtest.exception.InsufficientFundsException;
import alarionov.seventechtest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("account-transactions")
public class AccountTransactionsController {

    private AccountService accountService;


    @PostMapping("deposits")
    public void deposit(@Valid DepositRequest req) {
        try {
            accountService.deposit(req.getAccountNumber(), req.getAmount());
        } catch (AccountNotFoundException ex) {
            ResponseStatusException e = getResponseStatusException(ex);
            throw e;
        }
    }

    @PostMapping("withdrawals")
    public void withdraw(@Valid WithdrawalRequest req) {
        try {
            accountService.withdraw(req.getAccountNumber(), req.getAmount());
        } catch (AccountNotFoundException ex) {
            throw getResponseStatusException(ex);
        } catch (InsufficientFundsException ex) {
            throw getResponseStatusException(ex);
        }
    }

    @PostMapping("transfers")
    public void transfer(@Valid TransferRequest req) {
        try {
            accountService.transfer(req.getAccNumberFrom(), req.getAccNumberTo(), req.getAmount());
        } catch (AccountNotFoundException ex) {
            throw getResponseStatusException(ex);
        } catch (InsufficientFundsException ex) {
            throw getResponseStatusException(ex);
        }
    }

    public AccountTransactionsController(@Autowired AccountService accountService) {
        this.accountService = accountService;
    }

    private ResponseStatusException getResponseStatusException(AccountNotFoundException ex) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Account " + ex.getAccountNumber() + " not found");
    }

    private ResponseStatusException getResponseStatusException(InsufficientFundsException ex) {
        return new ResponseStatusException(HttpStatus.CONFLICT, "Account " + ex.getAccountNumber() + " has insufficient funds");
    }
}
