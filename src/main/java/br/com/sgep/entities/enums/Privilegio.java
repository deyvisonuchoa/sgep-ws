package br.com.sgep.entities.enums;

public enum Privilegio {

	ADMIN_GERAL(0), GESTOR(1), SUB_GESTOR(2), FUNCIONARIO(3);

	private int codigo;

	private Privilegio(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public static Privilegio valueOf(int codigo) {
		for (Privilegio privilegio : Privilegio.values()) {
			if (privilegio.getCodigo() == codigo) {
				return privilegio;
			}
		}
		//throw new IllegalArgumentException("Invalid Order Status code!");
		return null;
	}
}
