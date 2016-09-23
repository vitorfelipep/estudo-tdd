package com.milano;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.milano.desconto.CalculadoraDescontoPrimeiraFaixa;
import com.milano.desconto.CalculadoraDescontoSegundaFaixa;
import com.milano.desconto.CalculadoraDescontoTerceiraFaixa;
import com.milano.desconto.CalculadoraFaixaDesconto;
import com.milano.desconto.SemDesconto;

public class PedidoTest {

	private PedidoBuilder pedido;

	@Before
	public void setup() {
		pedido = new PedidoBuilder();
	}

	private void assertResumoPedido(double valorTotal, double desconto) {

		ResumoPedido resumo = pedido.construir().resumoPedido();

//		assertEquals(valorTotal, resumo.getValorTotal(), 0.0001);
//		assertEquals(desconto, resumo.getDesconto(), 0.0001);
		
		assertEquals(new ResumoPedido(valorTotal, desconto), resumo);
	}

	@Test
	public void deveCalcularValorTotalEDescontoParaPedidoVazio() throws Exception {
		assertResumoPedido(0.0, 0.0);
	}

	@Test
	public void deveCalcularResumoParaUmItemSemResumo() throws Exception {
		//pedido.adicionarItem(new ItemPedido("Sabonete", 5, 5));
		pedido.comItem(5.0, 5);
		assertResumoPedido(25.0, 0.0);
	}

	@Test
	public void deveCalcularResumoParaDoisItensSemDesconto() throws Exception {
//		pedido.adicionarItem(new ItemPedido("Sabonete", 3.0, 3));
//		pedido.adicionarItem(new ItemPedido("Pasta de dente", 7.0, 3));
		
		pedido.comItem( 3.0, 3)
				.comItem(7.0, 3);
		
		assertResumoPedido(30.0, 0.0);
	}

	@Test
	public void deveAplicarDescontoNa1aFaixa() throws Exception {
//		pedido.adicionarItem(new ItemPedido("Creme", 20.0, 20));
		
		pedido.comItem(20.0, 20); 
		
		assertResumoPedido(400.0, 16.0);
	}

	@Test
	public void deveAplicarDescontoNa2aFaixa() throws Exception {
//		pedido.adicionarItem(new ItemPedido("Shampoo", 15.0, 30));
//		pedido.adicionarItem(new ItemPedido("Óleo", 15.0, 30));
		
		pedido.comItem(15.0, 30)
				.comItem(15.0, 30);
		
		assertResumoPedido(900.0, 54.0);
	}

	@Test
	public void deveAplicarDescontoNa3aFaixa() throws Exception {
//		pedido.adicionarItem(new ItemPedido("Creme", 15.0, 30));
//		pedido.adicionarItem(new ItemPedido("Óleo", 15.0, 30));
//		pedido.adicionarItem(new ItemPedido("Shampoo", 10.0, 30));
		
		pedido.comItem(15.0, 30)
				.comItem(15.0, 30)
					.comItem(10.0, 30);


		assertResumoPedido(1200.0, 96.0);

	}
	
	@Test(expected = QuantidadeItensInvalidaException.class)
	public void naoAceitarPedidosComItensComQuantidadeNegativas() throws Exception {
		pedido.comItem(0.0, -10);
	}
}
