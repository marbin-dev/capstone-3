package org.yearup.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yearup.service.ProfileService;
import org.yearup.service.UserService;

@RestController
@RequestMapping("/profile")
@PreAuthorize("hasRole('ROLE_USER')")
@CrossOrigin
public class ProfileController {

    private ProfileService profileService;
    private UserService userService;

    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }
}
