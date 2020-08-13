package com.manadev.domain.enums;

public enum EstadoDePagamento {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELAD(3, "Cancelado");

	private Integer code;
	private String descricao;

	private EstadoDePagamento(Integer code, String descricao) {
		this.code = code;
		this.descricao = descricao;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoDePagamento toEnum(Integer id) {
		if (id == null) {
			return null;
		}

		for (EstadoDePagamento x : EstadoDePagamento.values()) {
			if (id.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid code: " + id);
	}

}
