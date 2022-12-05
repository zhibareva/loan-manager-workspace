package com.managerworkspace.dto.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ClientResponse {

  @NonNull
  private Long id;

  @NonNull
  private String firstName;

  @NonNull
  private String middleName;

  @NonNull
  private String lastName;

  @NonNull
  private String passportId;

  @NonNull
  private String placeOfResidence;

  @NonNull
  private String phoneNumber;

  @NonNull
  private LocalDate from;

  @NonNull
  private LocalDate to;

  @NonNull
  private String position;

  @NonNull
  private String companyName;

}
