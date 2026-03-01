package com.fizu.laporaja.services;

import com.fizu.laporaja.model.entity.User;
import com.fizu.laporaja.model.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void loadUserByUsername_UserFound_ReturnsUserDetails() {
        String nis = "123456789";
        // Constructor order: id, nis, name, password, birthDate, activated, activationKey, activationDate
        User user = new User("1", nis, "Test User", "password", null, true, null, null);

        when(userRepository.findByNis(nis)).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername(nis);

        assertNotNull(userDetails);
        assertEquals(nis, userDetails.getUsername());
        verify(userRepository, times(1)).findByNis(nis);
    }

    @Test
    void loadUserByUsername_UserNotFound_ThrowsException() {
        String nis = "999999999";
        when(userRepository.findByNis(nis)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(nis));

        verify(userRepository, times(1)).findByNis(nis);
    }
}
