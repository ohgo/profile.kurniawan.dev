package dev.kurniawan.profile.api;

import dev.kurniawan.profile.domains.Profile;
import dev.kurniawan.profile.domains.ProfileService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
@RequestMapping(path = "/profile", produces = "application/json")
public class ProfileController {
    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping()
    public Profile profile() {
        return profileService.getProfile();
    }
}