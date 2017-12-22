package api.rest;

import api.entity.UserEntity;
import api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserEndpoint {

    @Autowired
    UserRepository repository;

    @PostMapping("/create")
    public UserEntity create(@RequestBody UserEntity entity) {
        return repository.insert(entity);
    }

    @GetMapping
    public UserEntity get(@RequestParam String userId) {
        return repository.findOne(userId);
    }

    @GetMapping("/all")
    public List<UserEntity> getAll() {
        return repository.findAll();
    }

    @DeleteMapping
    public UserEntity delete(@RequestParam String userId) {
        UserEntity entity = repository.findOne(userId);
        repository.delete(userId);
        return entity;
    }

}
