package alarionov.seventechtest.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TransferRequest {

    @NotNull
    String accNumberFrom;

    @NotNull
    String accNumberTo;

    long amount;
}
