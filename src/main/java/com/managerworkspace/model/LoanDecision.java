package com.managerworkspace.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loan_decision")
@Getter
@NoArgsConstructor
public class LoanDecision {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "loan_application_id", referencedColumnName = "id")
  private LoanApplication loanApplication;

  @Column(name = "is_approved")
  private boolean approvedStatus;

  @Column(name = "period")
  private int periodInDays;

  @Column(name = "approved_loan_amount")
  private double approvedLoanAmount;

  public LoanDecision(LoanApplication loanApplication, boolean approvedStatus, int periodInDays,
      double approvedLoanAmount) {
    this.loanApplication = loanApplication;
    this.approvedStatus = approvedStatus;
    this.periodInDays = periodInDays;
    this.approvedLoanAmount = approvedLoanAmount;
  }
}
