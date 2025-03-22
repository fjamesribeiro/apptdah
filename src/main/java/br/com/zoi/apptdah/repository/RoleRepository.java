package br.com.zoi.apptdah.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zoi.apptdah.model.Role;
import br.com.zoi.apptdah.model.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	public Optional<Role> findByNome(RoleEnum nome);

}
