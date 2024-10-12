package com.reuso.entities.state.venda;

import com.reuso.entities.Venda;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CANCELADA")
public class Cancelada extends EstadoVendaBase{

	@Override
    public void realizarVenda(Venda venda) {
    }

    @Override
    public void cancelarVenda(Venda venda) {
    }

    @Override
    public void extornarVenda(Venda venda) {
    }

    @Override
    public String getEstado() {
        return "Venda Cancelada";
    }

}
