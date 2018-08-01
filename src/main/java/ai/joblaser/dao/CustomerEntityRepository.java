package ai.joblaser.dao;

import ai.joblaser.domain.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface CustomerEntityRepository extends CrudRepository<CustomerEntity, Long> {

    @Query("SELECT c FROM CustomerEntity c where c.customerId = :customerId")
    CustomerEntity findCustomerByID(@Param("customerId") String customerId);


    @Query("SELECT c FROM CustomerEntity c where c.userName = :userName")
    CustomerEntity findCustomerByUserName(@Param("userName") String userName);
}
