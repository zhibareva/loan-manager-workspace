package com.managerworkspace.repository.impl;

import com.managerworkspace.model.LoanApplication;
import com.managerworkspace.repository.LoanApplicationRepository;
import com.managerworkspace.utils.HibernateSessionFactoryUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class LoanApplicationRepositoryImpl implements LoanApplicationRepository {

  @Override
  public LoanApplication findById(String id) {
    return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(LoanApplication.class, id);
  }

  @Override
  public List<LoanApplication> findAll() {
    return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from LoanApplication").list();
  }

  @Override
  public LoanApplication delete(LoanApplication loanApplication) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction tx1 = session.beginTransaction();
    session.delete(loanApplication);
    tx1.commit();
    session.close();
    return loanApplication;
  }

  @Override
  public LoanApplication save(LoanApplication loanApplication) {
    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();
    session.save(loanApplication);
    transaction.commit();
    session.close();
    return loanApplication;
  }
}
