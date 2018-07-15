package br.com.softplan.sienge.exercicio2;

import java.util.Iterator;
import java.util.List;

public class GeradorObservacao {
	
	public String geraObservacao(List<Integer> codigos) {
		return  (codigos == null || codigos.isEmpty()) ? "" : this.geraMensagemObservacao(codigos);
	}
	
	private String geraMensagemObservacao(List<Integer> codigos) {
		return new StringBuilder(this.getMensagemInicial(codigos))
				.append(this.getCodigosComSeparador(codigos))
				.append(".")
				.toString();
	}
	
	private String getMensagemInicial(List<Integer> codigos) {
		return String.format("Fatura %s de simples remessa: ", codigos.size() > 1 ? "das notas fiscais" : "da nota fiscal");
	}
	
	private String getCodigosComSeparador(List<Integer> codigos) {
		StringBuilder strCodigos = new StringBuilder();		
		for (Iterator<Integer> iterator = codigos.iterator(); iterator.hasNext();) {
			Integer codigo = iterator.next();
			if (strCodigos.length() == 0) {
				strCodigos.append(String.valueOf(codigo));
			} else if (iterator.hasNext()) {
				strCodigos.append(", ").append(String.valueOf(codigo));
			} else {
				strCodigos.append(" e ").append(String.valueOf(codigo));
			}
		}
		return strCodigos.toString();
	}		
}
