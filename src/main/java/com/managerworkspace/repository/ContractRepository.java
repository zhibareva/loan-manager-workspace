package com.managerworkspace.repository;

import com.managerworkspace.model.Contract;
import java.util.List;

public interface ContractRepository {

  Contract findById(String id);

  Contract findByLoanId(Long id);

  List<Contract> findAll();

  Contract delete(Contract contract);

  Contract save(Contract contract);

  Contract update(Contract contract);

  List<Contract> findByFilter(String isSigned);
}
