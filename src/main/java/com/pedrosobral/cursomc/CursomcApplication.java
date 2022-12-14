package com.pedrosobral.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pedrosobral.cursomc.domain.Categoria;
import com.pedrosobral.cursomc.domain.Cidade;
import com.pedrosobral.cursomc.domain.Cliente;
import com.pedrosobral.cursomc.domain.Endereco;
import com.pedrosobral.cursomc.domain.Estado;
import com.pedrosobral.cursomc.domain.ItemPedido;
import com.pedrosobral.cursomc.domain.Pagamento;
import com.pedrosobral.cursomc.domain.PagamentoComBoleto;
import com.pedrosobral.cursomc.domain.PagamentoComCartao;
import com.pedrosobral.cursomc.domain.Pedido;
import com.pedrosobral.cursomc.domain.Produto;
import com.pedrosobral.cursomc.domain.enums.EstadoPagamento;
import com.pedrosobral.cursomc.domain.enums.TipoCliente;
import com.pedrosobral.cursomc.repositories.CategoriaRepository;
import com.pedrosobral.cursomc.repositories.CidadeRepository;
import com.pedrosobral.cursomc.repositories.ClienteRepository;
import com.pedrosobral.cursomc.repositories.EnderecoRepository;
import com.pedrosobral.cursomc.repositories.EstadoRepository;
import com.pedrosobral.cursomc.repositories.ItemPedidoRepository;
import com.pedrosobral.cursomc.repositories.PagamentoRepository;
import com.pedrosobral.cursomc.repositories.PedidoRepository;
import com.pedrosobral.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepostory;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception{
		Categoria cat1 = new Categoria(null,"Inform??tica");
		Categoria cat2 = new Categoria(null,"Escrit??rio");
		Categoria cat3 = new Categoria(null,"Cama, mesa e banho");
		Categoria cat4 = new Categoria(null,"Eletr??nicos");
		Categoria cat5 = new Categoria(null,"Jardinagem");
		Categoria cat6 = new Categoria(null,"Decora????o");
		Categoria cat7 = new Categoria(null,"Perfumaria");
		Categoria cat8 = new Categoria(null,"Jogos de tabuleiro");
		Categoria cat9 = new Categoria(null,"Jogos eletr??nicos");		
		Categoria cat10 = new Categoria(null,"Brinquedos");
		
		
		
		Produto p1 = new Produto(null,"Computador",2000);
		Produto p2 = new Produto(null,"Impressora",800);
		Produto p3 = new Produto(null,"Mouse",80);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepostory.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado est1 = new Estado(null, "S??o Paulo");
		Estado est2 = new Estado(null, "Minas Gerais");
		
		Cidade cid1 = new Cidade(null,"Uberl??ndia",est2);
		Cidade cid2 = new Cidade(null,"S??o Paulo", est1);
		Cidade cid3 = new Cidade(null,"Campinas",est1);
		
		est1.getCidades().addAll(Arrays.asList(cid2,cid3));
		est2.getCidades().addAll(Arrays.asList(cid1));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		
		Cliente cli1 = new Cliente(null,"Maria Aparecida","maria@gmail.com","02003352532",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("999293921","234324213"));
		
		Endereco e1 = new Endereco(null,"Rua Flores","300","Apt 502","Jardim","98765432",cli1,cid1);
		
		Endereco e2 = new Endereco(null,"Rua A","30","Apt 702","Centro","93422453",cli1,cid2);
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1,e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1,e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,
				sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}
}
