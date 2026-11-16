package net.fz.demoescqrsaxon.query.repository;

import net.fz.demoescqrsaxon.query.entites.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
