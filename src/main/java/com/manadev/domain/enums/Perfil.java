package com.manadev.domain.enums;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"), CLIENTE(2, "ROLE_CLIENTE");

	private Integer code;
	private String descricao;

	private Perfil(Integer code, String descricao) {
		this.code = code;
		this.descricao = descricao;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer id) {
		if (id == null) {
			return null;
		}

		for (Perfil x : Perfil.values()) {
			if (id.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid code: " + id);
	}

}
