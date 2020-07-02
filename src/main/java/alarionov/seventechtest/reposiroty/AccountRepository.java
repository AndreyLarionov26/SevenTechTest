package alarionov.seventechtest.reposiroty;

import alarionov.seventechtest.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);
}
