package com.managerworkspace.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contracts")
@Getter
@Setter
@NoArgsConstructor
public class Contract {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "loan_application_id", referencedColumnName = "id")
  private LoanApplication loanApplication;

  private LocalDate dateOfSignature;

  private boolean signed;

  public Contract(LoanApplication loanApplication, LocalDate dateOfSignature,
      boolean signed) {
    this.loanApplication = loanApplication;
    this.dateOfSignature = dateOfSignature;
    this.signed = signed;
  }

}
