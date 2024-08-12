package com.example.lab10.Controller;

import com.example.lab10.Api.ApiResponse;
import com.example.lab10.Model.User;
import com.example.lab10.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getall")
    public ResponseEntity getAllUsers() {
        if (userService.getAllUsers()!=null){
            return ResponseEntity.ok(userService.getAllUsers());
        }
        return ResponseEntity.status(400).body("No users found in DB");
    }

//    Get all Users: Retrieves a list of all Users.done
//2. Add a new User: Adds a new user to the system.done
//3. Update a User: Updates an existing user’s information.
//4. Delete a User: Deletes a user from the system. Note: Verify that the user exists. done

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            userService.addUser(user);
            return ResponseEntity.status(201).body(new ApiResponse("User added successfully"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("User not found"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            if (userService.updateUser(user,id)){
                return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
            }
            return ResponseEntity.status(404).body(new ApiResponse("User not found"));
        }
    }
}
