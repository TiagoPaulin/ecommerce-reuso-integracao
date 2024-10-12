package com.reuso.entities.state.venda;

import com.reuso.entities.Venda;

public interface EstadoVenda {
    void realizarVenda(Venda venda);
    void cancelarVenda(Venda venda);
    void extornarVenda(Venda venda);
    String getEstado();
}
