package util;

import java.util.HashMap;
import java.util.Map;
import util.Status;

public class Mensagem {
	
	private String operacao;
	private Status status;
	
	Map<String, Object> params;
	
	public Mensagem(){
	}
	
	public Mensagem(String operacao){
		this.operacao = operacao;
		params = new HashMap<>();
	}
	
	public void setStatus(Status status){
		this.status = status;
	}
	
	public Status getStatus(){
		return status;
	}
	
	public void setParam(String chave, Object valor){
		params.put(chave, valor);
	}
	
	public Object getParam(String chave){
		return params.get(chave);
	}
	
	public String getOperacao(){
		return operacao;
	}
	
	@Override
	public String toString(){
		String msg = "Operação: " + operacao;
		msg += "\nStatus: " + status;
		
		msg += "\nParâmetros:\n";
		for (String p : params.keySet()) {
			msg += "\n" + p + ": " + params.get(p);
		}
		
		return msg;
	}
}