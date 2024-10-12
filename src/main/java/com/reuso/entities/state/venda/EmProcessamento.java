package com.reuso.entities.state.venda;

import com.reuso.entities.Venda;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EM_PROCESSAMENTO")
public class EmProcessamento extends EstadoVendaBase {

	@Override
	public void realizarVenda(Venda venda) {
		venda.setEstadoVenda(new Realizada());
	}

	@Override
	public void cancelarVenda(Venda venda) {
		venda.setEstadoVenda(new Cancelada());
	}

	@Override
	public void extornarVenda(Venda venda) {
	}

	@Override
	public String getEstado() {
		return "Venda Em Processamento";
	}

}
