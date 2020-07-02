package alarionov.seventechtest.exception;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountNotFoundException extends Exception {

    @NonNull
    @Getter
    String accountNumber;
}
