package be.intec.repositories;

import be.intec.models.User;
import be.intec.models.security.PasswordReset;

import java.util.Date;
import java.util.stream.Stream;

public interface PasswordResetRepository {

    PasswordReset findByToken(String token);

    PasswordReset findByUser(User user);

    Stream<PasswordReset> findAllByExpiryDateLessThan(Date now);

}
