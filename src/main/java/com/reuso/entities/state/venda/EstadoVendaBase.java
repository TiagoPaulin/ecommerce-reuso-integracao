package com.reuso.entities.state.venda;

import java.util.List;
import java.util.Objects;

import com.reuso.entities.Anuncio;
import com.reuso.entities.Venda;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_estado_venda")
public abstract class EstadoVendaBase implements EstadoVenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "estadoVenda")
	private List<Venda> vendas;
	
	public EstadoVendaBase() {
	}
	
	public EstadoVendaBase(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadoVendaBase other = (EstadoVendaBase) obj;
		return Objects.equals(id, other.id);
	}
}
