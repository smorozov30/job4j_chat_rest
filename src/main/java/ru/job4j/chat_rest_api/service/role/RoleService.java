package ru.job4j.chat_rest_api.service.role;

import ru.job4j.chat_rest_api.domian.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRoles();

    Role findRoleById(int id);

    Role saveRole(Role role);

    void deleteRole(Role role);
}
