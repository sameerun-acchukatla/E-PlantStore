package be.intec.repositories;

import be.intec.models.UserPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentRepository extends JpaRepository<UserPayment,Long> {


}
