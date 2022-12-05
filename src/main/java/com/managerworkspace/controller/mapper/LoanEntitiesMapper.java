package com.managerworkspace.controller.mapper;

import com.managerworkspace.dto.response.LoanDecisionResponse;
import com.managerworkspace.model.LoanDecision;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface LoanEntitiesMapper {

  @Mappings({
      @Mapping(target = "id", source = "loanDecision.id"),
      @Mapping(target = "firstName", source = "loanDecision.loanApplication.clientId.firstName"),
      @Mapping(target = "middleName", source = "loanDecision.loanApplication.clientId.middleName"),
      @Mapping(target = "lastName", source = "loanDecision.loanApplication.clientId.lastName"),
      @Mapping(target = "isApproved", source = "loanDecision.approvedStatus"),
      @Mapping(target = "periodInDays", source = "loanDecision.periodInDays"),
      @Mapping(target = "approvedLoanAmount", source = "loanDecision.approvedLoanAmount"),

  })
  LoanDecisionResponse toResponse(LoanDecision loanDecision);
}

