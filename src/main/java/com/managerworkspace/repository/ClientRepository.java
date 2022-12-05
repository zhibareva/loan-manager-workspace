package com.managerworkspace.repository;

import com.managerworkspace.model.Client;
import java.util.List;
import java.util.Optional;

public interface ClientRepository {

  Optional<Client> findById(String id);

  List<Client> findAll();

  Client delete(Client client);

  Client save(Client client);

  List<Client> findByFilters(String phoneNumber, String lastName, String passportId);
}
