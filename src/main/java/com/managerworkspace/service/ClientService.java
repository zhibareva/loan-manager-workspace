package com.managerworkspace.service;

import com.managerworkspace.exception.ClientNotFoundException;
import com.managerworkspace.model.Client;
import java.time.LocalDate;
import java.util.List;

public interface ClientService {

  Client getClientById(String id) throws ClientNotFoundException;

  Client createClient(String firstName, String middleName, String lastName, String passportId, String placeOfResidence, String phoneNumber, LocalDate from, LocalDate to, String position,
      String companyName);

  List<Client> getClientByFilter(String phoneNumber, String lastName, String passportId);
}
