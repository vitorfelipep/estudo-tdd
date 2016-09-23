package com.milano;

import java.util.ArrayList;
import java.util.List;

import com.milano.desconto.CalculadoraFaixaDesconto;

public class Pedido {

	private List<ItemPedido> itens = new ArrayList<>();

	private CalculadoraFaixaDesconto calculadoraFaixaDesconto;

	public Pedido(CalculadoraFaixaDesconto calculadoraFaixaDesconto) {
		this.calculadoraFaixaDesconto = calculadoraFaixaDesconto;
	}

	public void adicionarItem(ItemPedido itemPedido) {
		validarQuantidadeItens(itemPedido);
		itens.add(itemPedido);
	}

	private void validarQuantidadeItens(ItemPedido itemPedido) {
		if(itemPedido.getQuantidade() < 0)
			throw new QuantidadeItensInvalidaException();
	}

	public ResumoPedido resumoPedido() {
		double valorTotal = valorTotal();
		double desconto = calculadoraFaixaDesconto.desconto(valorTotal);
		
		System.out.println(desconto);
		
		return new ResumoPedido(valorTotal, desconto);

	}

	public double valorTotal() {
		double valorTotal = 0;
		for (ItemPedido i : itens) {
			valorTotal += i.getValorUnitario() * i.getQuantidade();
		}
		return valorTotal;
	}
}
