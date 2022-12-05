package com.managerworkspace.dto.request;

import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ClientRequest {

  @NotEmpty
  @Pattern(regexp = "^[a-zA-Z\\s]+$")
  private final String firstName;

  @NotEmpty
  @Pattern(regexp = "^[a-zA-Z\\s]+$")
  private final String middleName;

  @NotEmpty
  @Pattern(regexp = "^[a-zA-Z\\s]+$")
  private final String lastName;

  @NotEmpty
  @Pattern(regexp = "^[0-9\\s]+$")
  private final String passportId;

  @NotEmpty
  @Pattern(regexp = "^[a-zA-Z\\s]+$")
  private final String placeOfResidence;

  @NotEmpty
  @Pattern(regexp = "^[0-9\\s]+$")
  private final String phoneNumber;

  @NonNull
  @Past
  private final LocalDate from;

  @NonNull
  @PastOrPresent
  private final LocalDate to;

  @NonNull
  private final String position;

  @NonNull
  private final String companyName;

  @NotNull
  private final double loanAmount;

}
