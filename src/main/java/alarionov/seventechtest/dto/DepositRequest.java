package alarionov.seventechtest.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepositRequest {

    @NotNull
    String accountNumber;

    long amount;
}
