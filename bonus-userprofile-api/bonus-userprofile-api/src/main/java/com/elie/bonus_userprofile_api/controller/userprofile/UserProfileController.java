package com.elie.bonus_userprofile_api.controller.userprofile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elie.bonus_userprofile_api.model.userprofile.ApiResponse;
import com.elie.bonus_userprofile_api.model.userprofile.UserProfile;


@RestController
@RequestMapping("/api/userprofiles")
public class UserProfileController {


    private List<UserProfile> users = new ArrayList<>();

    // Sample data
    public UserProfileController() {
        users.add(new UserProfile(1L, "nshimye_elie", "elie@example.com", "Elie Nshimyumuremyi", 25, "Rwanda", "Loves coding", true));
        users.add(new UserProfile(2L, "ngonga_elisa", "elisa@example.com", "Elissa Ngonga", 30, "UG", "Designer", false));
        users.add(new UserProfile(3L, "uwase_nadia", "nadia@example.com", "Nadia Uwase", 28, "Kenya", "Photographer", true));
    }

    // GET all users
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAllUsers() {
        return ResponseEntity.ok(new ApiResponse<>(true, "All user profiles fetched", users));
    }

    // GET user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserById(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                return ResponseEntity.ok(new ApiResponse<>(true, "User profile fetched", user));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }

    // POST create user
    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> createUser(@RequestBody UserProfile user) {
        users.add(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "User profile created successfully", user));
    }

    // PUT update user
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> updateUser(@PathVariable Long userId,
                                                               @RequestBody UserProfile updatedUser) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setUsername(updatedUser.getUsername());
                user.setEmail(updatedUser.getEmail());
                user.setFullName(updatedUser.getFullName());
                user.setAge(updatedUser.getAge());
                user.setCountry(updatedUser.getCountry());
                user.setBio(updatedUser.getBio());
                user.setActive(updatedUser.isActive());
                return ResponseEntity.ok(new ApiResponse<>(true, "User profile updated", user));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }

    // DELETE user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                users.remove(user);
                return ResponseEntity.ok(new ApiResponse<>(true, "User profile deleted", null));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }

    // PATCH activate/deactivate user
    @PatchMapping("/{userId}/toggle")
    public ResponseEntity<ApiResponse<UserProfile>> toggleActive(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(!user.isActive());
                String msg = user.isActive() ? "User activated" : "User deactivated";
                return ResponseEntity.ok(new ApiResponse<>(true, msg, user));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }

    // GET search by username
    @GetMapping("/search/username")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByUsername(@RequestParam String username) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : users) {
            if (user.getUsername().toLowerCase().contains(username.toLowerCase())) {
                result.add(user);
            }
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "Search results", result));
    }

    // GET search by country
    @GetMapping("/search/country")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByCountry(@RequestParam String country) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : users) {
            if (user.getCountry().equalsIgnoreCase(country)) {
                result.add(user);
            }
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "Search results", result));
    }

    // GET search by age range
    @GetMapping("/search/age")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByAgeRange(@RequestParam int min, @RequestParam int max) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : users) {
            if (user.getAge() >= min && user.getAge() <= max) {
                result.add(user);
            }
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "Search results", result));
    }

}
