package com.managerworkspace.dto.request;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignContractRequest {

  @NotNull
  @PastOrPresent
  private final LocalDate dateOfSignature;

  @NotNull
  private final boolean isSigned;

}
