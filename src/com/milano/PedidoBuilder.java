package com.milano;

import com.milano.desconto.CalculadoraDescontoPrimeiraFaixa;
import com.milano.desconto.CalculadoraDescontoSegundaFaixa;
import com.milano.desconto.CalculadoraDescontoTerceiraFaixa;
import com.milano.desconto.CalculadoraFaixaDesconto;
import com.milano.desconto.SemDesconto;

public class PedidoBuilder {
	
	private Pedido instancia;
	
	public PedidoBuilder() {
		CalculadoraFaixaDesconto calculadoraFaixaDesconto = 
			new CalculadoraDescontoTerceiraFaixa(
				new CalculadoraDescontoSegundaFaixa(
					new CalculadoraDescontoPrimeiraFaixa(
						new SemDesconto(null))));
		
		this.instancia = new Pedido(calculadoraFaixaDesconto);
	}
	
	public PedidoBuilder comItem(double valorUnitario, int quantidade) {
		instancia.adicionarItem(new ItemPedido("Gerado", valorUnitario, quantidade));
		return this;
		
	}
	
	public Pedido construir() {
		return instancia;
	}
}
