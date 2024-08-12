package com.example.lab10.Service;

import com.example.lab10.Model.User;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
//    Get all Users: Retrieves a list of all Users.done
//2. Add a new User: Adds a new user to the system.done
//3. Update a User: Updates an existing user’s information.done
//4. Delete a User: Deletes a user from the system. Note: Verify that the user exists. done
    public List<User> getAllUsers() {
        return userRepository.findAll().isEmpty()?null:userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public boolean updateUser(User user,Integer id) {
        if (userRepository.existsById(id)){
            User oldUser = userRepository.getById(id);
            oldUser.setName(user.getName());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            oldUser.setRole(user.getRole());
            userRepository.save(oldUser);
            return true;
        }
        return false;
    }

    public boolean deleteUser(Integer id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
