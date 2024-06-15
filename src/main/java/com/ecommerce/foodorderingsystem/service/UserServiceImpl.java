package com.ecommerce.foodorderingsystem.service;

import com.ecommerce.foodorderingsystem.model.Role;
import com.ecommerce.foodorderingsystem.model.User;
import com.ecommerce.foodorderingsystem.repository.RoleRepository;
import com.ecommerce.foodorderingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final UserRolesService userRolesService;

    private final RoleRepository roleRepository;

    @Override
    public void save(String firstName, String lastName, String email, String password, long roleId) throws BadRequestException {
        User user = User.builder()
                .firstName(firstName.toLowerCase())
                .lastName(lastName.toLowerCase())
                .email(email.toLowerCase())
                .password(password)
                .build();

        Optional<Role> role = roleRepository.findById(roleId);
        if(role.isEmpty()){
            throw new BadRequestException("Role is not present");
        }

        userRepository.save(user);

        userRolesService.addUserRoles(user,role.get());
    }

    @Override
    public void loginUser(String email, String password) throws BadRequestException {

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()){
            throw new BadRequestException("Email does not exist");
        }

        if(!user.get().getPassword().equals(password)){
            throw new BadRequestException("email or password is incorrect");
        }

        user.get().setLoggedIn(true);

        userRepository.save(user.get());
    }

    @Override
    public void logoutUser(String email) throws BadRequestException {

        Optional<User> user = userRepository.findByEmail(email);

        if(!user.get().isLoggedIn()){
            throw new BadRequestException("please login");
        }

        user.get().setLoggedIn(false);

        userRepository.save(user.get());

    }

}
