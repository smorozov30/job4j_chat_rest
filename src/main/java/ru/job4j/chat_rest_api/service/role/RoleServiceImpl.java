package ru.job4j.chat_rest_api.service.role;

import org.springframework.stereotype.Service;
import ru.job4j.chat_rest_api.domian.Role;
import ru.job4j.chat_rest_api.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> findAllRoles() {
        return (List<Role>) repository.findAll();
    }

    @Override
    public Role findRoleById(int id) {
        return repository.findById(id).orElse(new Role());
    }

    @Override
    public Role saveRole(Role role) {
        return repository.save(role);
    }

    @Override
    public void deleteRole(Role role) {
        repository.delete(role);
    }
}
