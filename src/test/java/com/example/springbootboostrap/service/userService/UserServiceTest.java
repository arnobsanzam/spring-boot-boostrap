package com.example.springbootboostrap.service.userService;

import com.example.springbootboostrap.appenum.RoleType;
import com.example.springbootboostrap.entity.Role;
import com.example.springbootboostrap.entity.User;
import com.example.springbootboostrap.repository.UserRepository;
import com.example.springbootboostrap.service.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setId(1001L);
        user.setUsername("test-user");
        user.setPassword("test-password");

        Role role = new Role();
        role.setId(1001L);
        role.setRoleName(RoleType.ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        assertNotNull(user);
        assertEquals("test-user", user.getUsername());
        assertEquals("test-password", user.getPassword());
        assertEquals(RoleType.ROLE_USER, user.getRoles().iterator().next().getRoleName());
    }
}

