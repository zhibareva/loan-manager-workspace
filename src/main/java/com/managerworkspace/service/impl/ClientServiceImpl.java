package com.managerworkspace.service.impl;

import com.managerworkspace.exception.ClientNotFoundException;
import com.managerworkspace.model.Client;
import com.managerworkspace.repository.ClientRepository;
import com.managerworkspace.service.ClientService;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

  @Autowired
  private final ClientRepository clientRepository;

  @Override
  public Client createClient(String firstName, String middleName, String lastName,
      String passportId, String placeOfResidence, String phoneNumber, LocalDate from, LocalDate to,
      String position, String companyName) {
    Client client = new Client(
        firstName,
        middleName,
        lastName,
        passportId,
        placeOfResidence,
        phoneNumber,
        from,
        to,
        position,
        companyName
    );
    return clientRepository.save(client);
  }

  @Override
  public Client getClientById(String id) throws ClientNotFoundException {
    return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("There is no such client"));
  }

  @Override
  public List<Client> getClientByFilter(String phoneNumber, String lastName, String passportId) {
    return clientRepository.findByFilters(phoneNumber, lastName, passportId);
  }
}
