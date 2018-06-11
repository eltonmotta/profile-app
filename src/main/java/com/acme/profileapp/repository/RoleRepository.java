package com.acme.profileapp.repository;

import com.acme.profileapp.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, String> {
}
