package JuanJose.ForoHub.service.Permission;

import JuanJose.ForoHub.exception.ResourceAlreadyExistException;
import JuanJose.ForoHub.model.Permission;
import JuanJose.ForoHub.repository.PermissionRepository;
import JuanJose.ForoHub.service.Validations.AbstractEntityValidator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class PermissionValidator extends AbstractEntityValidator <Permission> {

    private final PermissionRepository permissionRepository;

    public PermissionValidator(PermissionRepository permissionRepository) {
        super(permissionRepository);
        this.permissionRepository=permissionRepository;
    }

    public void validatePermissionExistsByName(String name){
        if(permissionRepository.existsByName(name)){
            throw  new ResourceAlreadyExistException("A Permission with the name " + name + "already exists.");
        }
    }
}
