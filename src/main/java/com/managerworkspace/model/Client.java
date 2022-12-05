package com.managerworkspace.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "middle_name")
  private String middleName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "passport_id")
  private String passportId;

  @Column(name = "place_of_residence")
  private String placeOfResidence;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "from_date")
  private LocalDate from;

  @Column(name = "to_date")
  private LocalDate to;

  @Column(name = "position")
  private String position;

  @Column(name = "company_name")
  private String companyName;

  public Client(String firstName, String middleName, String lastName, String passportId,
      String placeOfResidence, String phoneNumber, LocalDate from, LocalDate to,
      String position, String companyName) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.passportId = passportId;
    this.placeOfResidence = placeOfResidence;
    this.phoneNumber = phoneNumber;
    this.from = from;
    this.to = to;
    this.position = position;
    this.companyName = companyName;
  }

}
