package com.managerworkspace.dto.response;

import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ContractResponse {

  @NonNull
  private final Long id;

  @NotEmpty
  private final String firstName;

  @NotEmpty
  private final String middleName;

  @NotEmpty
  private final String lastName;

  @NotNull
  private final boolean isApproved;

  @NotNull
  private final int periodInDays;

  @NotNull
  private final double approvedLoanAmount;

  private final LocalDate dateOfSignature;

  @NotNull
  private final boolean signed;
}
