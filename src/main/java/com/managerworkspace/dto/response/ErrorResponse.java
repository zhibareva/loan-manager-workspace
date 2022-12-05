package com.managerworkspace.dto.response;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ErrorResponse {

  @NotNull
  private final int status;

  @NonNull
  private final String error;

  @NonNull
  private final String message;
}