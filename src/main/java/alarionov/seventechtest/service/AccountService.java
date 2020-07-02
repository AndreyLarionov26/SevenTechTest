package alarionov.seventechtest.service;

import alarionov.seventechtest.exception.AccountNotFoundException;
import alarionov.seventechtest.exception.InsufficientFundsException;
import lombok.NonNull;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface AccountService {

    void deposit(@NonNull String accNumber, long amount) throws AccountNotFoundException;

    void withdraw(@NonNull String accNumber, long amount) throws AccountNotFoundException, InsufficientFundsException;

    @Transactional(propagation = Propagation.REQUIRED)
    void transfer(@NonNull String accNumberFrom, @NonNull String accNumberTo, long amount) throws AccountNotFoundException, InsufficientFundsException;
}
