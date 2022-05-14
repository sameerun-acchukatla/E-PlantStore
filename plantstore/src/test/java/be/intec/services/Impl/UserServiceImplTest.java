package be.intec.services.Impl;

import be.intec.models.*;
import be.intec.models.security.Role;
import be.intec.models.security.UserRole;
import be.intec.repositories.RoleRepository;
import be.intec.repositories.UserPaymentRepository;
import be.intec.repositories.UserRepository;
import be.intec.repositories.UserShippingRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private UserRepository userRepository=  mock(UserRepository.class);
    private RoleRepository roleRepository=  mock(RoleRepository.class);
    private UserPaymentRepository userPaymentRepository = mock(UserPaymentRepository.class);
    private UserShippingRepository userShippingRepository = mock(UserShippingRepository.class);

    private UserServiceImpl userService = new UserServiceImpl(userRepository, roleRepository, userPaymentRepository,userShippingRepository);

    User user = new User();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testFindByIdReturnsValidUser() {

        // test setup
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // test call/method
        User actualUser = userService.findById(1L);

        // assert results
        assertEquals(user,actualUser);
    }

    @Test
    public void testFindByIdReturnsNull() {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
        User actualUser = userService.findById(1L);
        assertEquals(null,actualUser);
    }

    @Test
    public void testFindByUsername(){
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        User actualUser = userService.findByUsername("sameerun");
        assertEquals(user,actualUser);
    }
    @Test
    public void testFindByEmail(){
        when(userRepository.findByEmail("sameerun.shaikh@gmail.com")).thenReturn(user);
        User actualUser = userService.findByEmail("sameerun.shaikh@gmail.com");
        assertEquals(user,actualUser);
    }

    @Test
    public void testSaveUser(){
        when(userRepository.save(any(User.class))).thenReturn(user);
        User savedUser = userService.save(user);
        assertEquals(user,savedUser);
    }
    @Test
    public void testCreateUserReturnsExistingUser() throws Exception {
        user.setUsername("sameerun");
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        User existingUser = userService.createUser(user, null);
        assertEquals(user,existingUser);
    }

    @Test
    public void testCreateUserCreatesNewUser() throws Exception {
        UserRole userRole = new UserRole();
        Role role = new Role();
        userRole.setRole(role);

        when(userRepository.findByUsername(anyString())).thenReturn(null);
       // when(roleRepository.save(role)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        User newUser = userService.createUser(this.user, Set.of(userRole));
        assertEquals(user,newUser);
        verify(roleRepository).save(role);
        ShoppingCart shoppingCart = newUser.getShoppingCart();
        assertNotNull(shoppingCart);
        assertEquals(shoppingCart.getUser(),newUser);
        List<UserRole> userRoles = user.getUserRoles();
        assertNotNull(userRoles);
        assertEquals(1,userRoles.size());
        assertEquals(userRole,userRoles.get(0));
        assertNotNull(user.getUserShippingList());
        assertNotNull(user.getUserPaymentList());
    }

    @Test
    public void testUpdateUserBilling(){
        UserBilling userBilling = new UserBilling();
        UserPayment userPayment = new UserPayment();
        User newUser = new User();
        newUser.setUserPaymentList(new ArrayList<>());

        userService.updateUserBilling(userBilling,userPayment,newUser);

        assertThat(userPayment.getUser()).isSameAs(newUser);
        assertThat(userPayment.getUserBilling()).isSameAs(userBilling);
        assertThat(userPayment.isDefaultPayment()).isTrue();
        assertThat(userBilling.getUserPayment()).isSameAs(userPayment);
        assertThat(newUser.getUserPaymentList()).hasSize(1);
        assertThat(newUser.getUserPaymentList().get(0)).isSameAs(userPayment);
        verify(userRepository).save(newUser);
    }

    @Test
    public void testUpdateUserShipping(){
        UserShipping userShipping = new UserShipping();
        User newUser = new User();
        newUser.setUserShippingList(new ArrayList<>());

        userService.updateUserShipping(userShipping,newUser);

        assertThat(userShipping.getUser()).isSameAs(newUser);
        assertThat(userShipping.isUserShippingDefault()).isTrue();
        assertThat(newUser.getUserShippingList()).hasSize(1);
        assertThat(newUser.getUserShippingList().get(0)).isSameAs(userShipping);
        verify(userRepository).save(newUser);

    }

    @Test
    public void testSetUserDefaultPayment(){
        User newUser = new User();
        List<UserPayment> userPaymentList = new ArrayList<>();
        UserPayment userPayment1 = new UserPayment();
        userPayment1.setId(2L);
        userPaymentList.add(userPayment1);
        UserPayment userPayment2 = new UserPayment();
        userPayment2.setId(1L);
        userPaymentList.add(userPayment2);


        when(userPaymentRepository.findAll()).thenReturn(userPaymentList);

        userService.setUserDefaultPayment(2L,newUser);

        assertThat(userPayment1.isDefaultPayment()).isTrue();
        verify(userPaymentRepository).save(userPayment1);

        assertThat(userPayment2.isDefaultPayment()).isFalse();
        verify(userPaymentRepository).save(userPayment2);

    }

    @Test
    public void testSetUserDefaultShipping(){
        User user = new User();
        UserShipping userShipping1 = new UserShipping();
        userShipping1.setId(1L);
        UserShipping userShipping2 = new UserShipping();
        userShipping2.setId(2L);
        List<UserShipping> userShippingList = List.of(userShipping1,userShipping2);

        when(userShippingRepository.findAll()).thenReturn(userShippingList);

        userService.setUserDefaultShipping(1L,user);

        assertThat(userShipping1.isUserShippingDefault()).isTrue();
        verify(userShippingRepository).save(userShipping1);
        assertThat(userShipping2.isUserShippingDefault()).isFalse();
        verify(userShippingRepository).save(userShipping2);

    }

}