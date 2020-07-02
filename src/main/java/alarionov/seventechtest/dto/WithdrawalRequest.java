package alarionov.seventechtest.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WithdrawalRequest {

    @NotNull
    String accountNumber;

    long amount;
}
