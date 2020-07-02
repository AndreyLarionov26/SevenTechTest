package alarionov.seventechtest.service;

import alarionov.seventechtest.exception.AccountNotFoundException;
import alarionov.seventechtest.exception.InsufficientFundsException;
import alarionov.seventechtest.reposiroty.AccountRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepo;

    @Override
    public void deposit(@NonNull String accNumber, long amount) throws AccountNotFoundException {
        var account = accountRepo.findByAccountNumber(accNumber).orElseThrow(() -> new AccountNotFoundException(accNumber));
        account.deposit(amount);
        accountRepo.save(account);
    }

    @Override
    public void withdraw(@NonNull String accNumber, long amount) throws AccountNotFoundException, InsufficientFundsException {
        var account = accountRepo.findByAccountNumber(accNumber).orElseThrow(() -> new AccountNotFoundException(accNumber));
        if (!account.hasSufficientFunds(amount)) {
            throw new InsufficientFundsException(account.getAccountNumber());
        } else {
            account.withdraw(amount);
            accountRepo.save(account);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transfer(@NonNull String accNumberFrom, @NonNull String accNumberTo, long amount) throws AccountNotFoundException, InsufficientFundsException {
        var accFrom = accountRepo.findByAccountNumber(accNumberFrom).orElseThrow(() -> new AccountNotFoundException(accNumberFrom));
        var accTo = accountRepo.findByAccountNumber(accNumberTo).orElseThrow(() -> new AccountNotFoundException(accNumberTo));
        if (!accFrom.hasSufficientFunds(amount)) {
            throw new InsufficientFundsException(accFrom.getAccountNumber());
        } else {
            accFrom.withdraw(amount);
            accTo.deposit(amount);
        }
    }


    public AccountServiceImpl(@Autowired AccountRepository accountRepository) {
        this.accountRepo = accountRepository;
    }
}
