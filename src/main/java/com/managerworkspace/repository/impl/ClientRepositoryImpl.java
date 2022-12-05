package com.managerworkspace.repository.impl;

import com.managerworkspace.model.Client;
import com.managerworkspace.repository.ClientRepository;
import com.managerworkspace.utils.DatabaseUtil;
import com.managerworkspace.utils.HibernateSessionFactoryUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class ClientRepositoryImpl implements ClientRepository {

  @Override
  public Optional<Client> findById(String id) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Client client = session.get(Client.class, id);
    session.close();
    return Optional.ofNullable(client);
  }

  @Override
  public List<Client> findAll() {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    return DatabaseUtil.loadAllData(Client.class, session);
  }

  @Override
  public Client delete(Client client) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction tx1 = session.beginTransaction();
    session.delete(client);
    tx1.commit();
    session.close();
    return client;
  }

  @Override
  public Client save(Client client) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.save(client);
    transaction.commit();
    session.close();
    return client;
  }

  @Override
  public List<Client> findByFilters(String phoneNumber, String lastName, String passportId) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

    List<Client> filteredClients;
    List<Client> buffer = new ArrayList<>(findAll());

    if (phoneNumber != null && !phoneNumber.isEmpty()) {
      filteredClients = DatabaseUtil.findByFilter(Client.class, session, "phoneNumber",
          phoneNumber);
      buffer = new ArrayList<>(filteredClients);
    }

    if (lastName != null && !lastName.isEmpty()) {
      filteredClients = DatabaseUtil.findByFilter(Client.class, session, "lastName", lastName);
      buffer = new ArrayList<>(filteredClients);
    }

    if (passportId != null && !passportId.isEmpty()) {
      filteredClients = DatabaseUtil.findByFilter(Client.class, session, "passportId", passportId);
      buffer = new ArrayList<>(filteredClients);
    }

    filteredClients = new ArrayList<>(buffer);
    session.close();
    return Collections.unmodifiableList(filteredClients);
  }
}
