package com.managerworkspace.repository.impl;

import com.managerworkspace.model.LoanDecision;
import com.managerworkspace.repository.LoanDecisionRepository;
import com.managerworkspace.utils.DatabaseUtil;
import com.managerworkspace.utils.HibernateSessionFactoryUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class LoanDecisionRepositoryImpl implements LoanDecisionRepository {

  @Override
  public LoanDecision save(LoanDecision loanDecision) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.save(loanDecision);
    transaction.commit();
    session.close();
    return loanDecision;
  }

  @Override
  public List<LoanDecision> findAll() {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    return DatabaseUtil.loadAllData(LoanDecision.class, session);
  }

  @Override
  public Optional<LoanDecision> findByLoanId(Long id) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    LoanDecision contract = DatabaseUtil.findByFilter(LoanDecision.class, session, "loanApplication",
        id).get(0);
    transaction.commit();
    session.close();
    return Optional.ofNullable(contract);
  }

  @Override
  public List<LoanDecision> findByFilter(String approvedStatus) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

    List<LoanDecision> filteredLoanDecisions;
    List<LoanDecision> buffer = new ArrayList<>(findAll());

    if (approvedStatus != null) {
      filteredLoanDecisions = DatabaseUtil.findByFilter(LoanDecision.class, session,
          "approvedStatus",
          Boolean.valueOf(approvedStatus));
      buffer = new ArrayList<>(filteredLoanDecisions);
    }

    filteredLoanDecisions = new ArrayList<>(buffer);
    session.close();
    return Collections.unmodifiableList(filteredLoanDecisions);
  }
}
