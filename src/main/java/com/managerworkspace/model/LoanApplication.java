package com.managerworkspace.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "loan_applications")
@Getter
@Slf4j
@NoArgsConstructor
public class LoanApplication {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "client_id", referencedColumnName = "id")
  private Client clientId;

  @Column(name = "loan_amount")
  private double loanAmount;

  public LoanApplication(Client clientId, double loanAmount) {
    this.clientId = clientId;
    this.loanAmount = loanAmount;
  }
}
