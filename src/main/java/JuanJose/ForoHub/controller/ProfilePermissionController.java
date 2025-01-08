package JuanJose.ForoHub.controller;

import JuanJose.ForoHub.dto.ProfilePermission.CreateProfilePermissionDTO;
import JuanJose.ForoHub.dto.ProfilePermission.DetailsResponseProfilePermissionDTO;
import JuanJose.ForoHub.service.ProfilePermission.ProfilePermissionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProfilePermissionController {

    private final ProfilePermissionService ppService;

    public ProfilePermissionController (ProfilePermissionService ppService){
        this.ppService=ppService;
    }

    @PostMapping("/profile-permission")
    public ResponseEntity<DetailsResponseProfilePermissionDTO> createProfilePermission (@RequestBody @Valid CreateProfilePermissionDTO data){
        DetailsResponseProfilePermissionDTO response = ppService.addPermissions(data);
        return ResponseEntity.ok(response);
    }


}
