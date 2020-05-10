package com.cinematica.framework.util;

import java.math.BigDecimal;

public class Utils {

	public static StringBuffer verificarSeCampoEstaNulo(Object entidade,String mensagem) {
		StringBuffer msg = new StringBuffer();

		if (entidade instanceof BigDecimal) {
			if (entidade.equals(BigDecimal.ZERO)) {
				msg.append(mensagem);
			}
		}
		if (entidade == null || entidade.equals("")) {
			msg.append(mensagem);
		}
		return msg;
	}
}
