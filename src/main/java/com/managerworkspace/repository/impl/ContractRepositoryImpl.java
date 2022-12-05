package com.managerworkspace.repository.impl;

import com.managerworkspace.model.Contract;
import com.managerworkspace.repository.ContractRepository;
import com.managerworkspace.utils.DatabaseUtil;
import com.managerworkspace.utils.HibernateSessionFactoryUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class ContractRepositoryImpl implements ContractRepository {

  @Override
  public Contract findById(String id) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Contract contract = session.get(Contract.class, id);
    session.close();
    return contract;
  }

  @Override
  public Contract findByLoanId(Long id) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    Contract contract = DatabaseUtil.findByFilter(Contract.class, session, "loanApplication",
        id).get(0);
    transaction.commit();
    session.close();
    return contract;
  }

  @Override
  public List<Contract> findAll() {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    return DatabaseUtil.loadAllData(Contract.class, session);
  }

  @Override
  public Contract delete(Contract contract) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction tx1 = session.beginTransaction();
    session.delete(contract);
    tx1.commit();
    session.close();
    return contract;
  }

  @Override
  public Contract save(Contract contract) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.save(contract);
    transaction.commit();
    session.close();
    return contract;
  }

  @Override
  public Contract update(Contract contract) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.update(contract);
    transaction.commit();
    session.close();
    return contract;
  }

  @Override
  public List<Contract> findByFilter(String isSigned) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

    List<Contract> filteredContracts;
    List<Contract> buffer = new ArrayList<>(findAll());

    if (isSigned != null) {
      filteredContracts = DatabaseUtil.findByFilter(Contract.class, session, "signed",
          Boolean.valueOf(isSigned));
      buffer = new ArrayList<>(filteredContracts);
    }

    filteredContracts = new ArrayList<>(buffer);
    session.close();
    return Collections.unmodifiableList(filteredContracts);

  }
}
