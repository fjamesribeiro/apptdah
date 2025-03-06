package br.com.zoi.apptdah.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleName {

	ROLE_ADMIN, ROLE_USER;

	@Override
	public String toString() {
		return this.name();
	}

}
