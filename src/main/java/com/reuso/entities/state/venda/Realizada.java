package com.reuso.entities.state.venda;

import com.reuso.entities.Venda;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("REALIZADA")
public class Realizada extends EstadoVendaBase{
	
	@Override
    public void realizarVenda(Venda venda) {
    }

    @Override
    public void cancelarVenda(Venda venda) {
    }

    @Override
    public void extornarVenda(Venda venda) {
        venda.setEstadoVenda(new Extornada());
    }

    @Override
    public String getEstado() {
        return "Venda Realizada";
    }
}
