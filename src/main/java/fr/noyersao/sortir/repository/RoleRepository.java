package fr.noyersao.sortir.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.noyersao.sortir.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>  {

}
