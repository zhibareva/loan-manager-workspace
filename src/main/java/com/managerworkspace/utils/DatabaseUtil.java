package com.managerworkspace.utils;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

public class DatabaseUtil {

  private DatabaseUtil() {
  }

  public static <T> List<T> loadAllData(Class<T> type, Session session) {
    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<T> criteria = builder.createQuery(type);
    criteria.from(type);
    return session.createQuery(criteria).getResultList();
  }

  public static <T> List<T> findByFilter(Class<T> type, Session session, String filter, Object value) {
    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<T> criteria = builder.createQuery(type);
    Root<T> clientRoot = criteria.from(type);
    criteria.where(builder.equal(clientRoot.get(filter), value));
    return session.createQuery(criteria).getResultList();
  }


}
