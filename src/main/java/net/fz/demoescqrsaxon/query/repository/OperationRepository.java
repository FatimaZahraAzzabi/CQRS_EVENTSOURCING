package net.fz.demoescqrsaxon.query.repository;

import net.fz.demoescqrsaxon.query.entites.Account;
import net.fz.demoescqrsaxon.query.entites.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<AccountOperation, Long> {
    List<AccountOperation> findByAccountId(String id);
}
