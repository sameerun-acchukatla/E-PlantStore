package be.intec.services.Impl;

import be.intec.models.User;
import be.intec.repositories.UserRepository;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserSecurityServiceTest {
    UserRepository userRepository = mock(UserRepository.class);
    private UserSecurityService userSecurityService = new UserSecurityService(userRepository);

    @Test
    public void testLoadUserByUsername() {
        User user = new User();
        when(userRepository.findByUsername("Sameerun")).thenReturn(user);

        UserDetails userDetails = userSecurityService.loadUserByUsername("Sameerun");

        assertThat(userDetails).isEqualTo(user);
    }
    @Test
    public void testExceptionIsThrownWhenUserIsNotFound(){
        when(userRepository.findByUsername("Sameerun")).thenReturn(null);
        assertThatThrownBy(() -> userSecurityService.loadUserByUsername("Sameerun"))
                .isInstanceOf(UsernameNotFoundException.class).hasMessage("Username not found");

    }
}