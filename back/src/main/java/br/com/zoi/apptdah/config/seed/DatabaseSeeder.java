package br.com.zoi.apptdah.config.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.zoi.apptdah.model.Role;
import br.com.zoi.apptdah.model.RoleName;
import br.com.zoi.apptdah.repository.RoleRepository;
import br.com.zoi.apptdah.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Component
public class DatabaseSeeder {

	@Autowired
	public RoleRepository roleRepository;

	@Autowired
	public UsuarioRepository usuarioRepository;

	@EventListener
	public void seed(ContextRefreshedEvent event) {
		seedAdminRoleTable();
	}

	@Transactional
	public void seedAdminRoleTable() {

		if (roleRepository.count() == 0) {
			Role roleAdm = new Role();
			roleAdm.setNome(RoleName.ROLE_ADMIN.toString());
			roleRepository.save(roleAdm);

			Role roleUser = new Role();
			roleUser.setNome(RoleName.ROLE_USER.toString());
			roleRepository.save(roleUser);

//			if (usuarioRepository.count() == 0) {
//				Usuario admin = new Usuario();
//				admin.setEmail("jamesribeiro@gmail.com");
//				admin.setNome("James");
//				admin.setSobrenome("Ribeiro");
//				admin.setSenha("$2a$10$IGbm.HwIZ4a9BeYmwXrNleN/jzKVvoPSs0UeJka.7S6sC0FdqFIRy");
//				admin.setRoles(Set.of(roleAdm));
//				usuarioRepository.save(admin);
//
//			}
		}
	}
}
