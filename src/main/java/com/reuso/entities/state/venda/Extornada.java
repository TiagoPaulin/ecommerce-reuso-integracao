package com.reuso.entities.state.venda;

import com.reuso.entities.Venda;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EXTORNADA")
public class Extornada extends EstadoVendaBase{
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
        return "Venda Extornada";
    }
}
