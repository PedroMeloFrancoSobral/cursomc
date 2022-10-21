package com.pedrosobral.cursomc;

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
import com.pedrosobral.cursomc.domain.Produto;
import com.pedrosobral.cursomc.domain.enums.TipoCliente;
import com.pedrosobral.cursomc.repositories.CategoriaRepository;
import com.pedrosobral.cursomc.repositories.CidadeRepository;
import com.pedrosobral.cursomc.repositories.ClienteRepository;
import com.pedrosobral.cursomc.repositories.EnderecoRepository;
import com.pedrosobral.cursomc.repositories.EstadoRepository;
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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception{
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		Produto p1 = new Produto(null,"Computador",2000);
		Produto p2 = new Produto(null,"Impressora",800);
		Produto p3 = new Produto(null,"Mouse",80);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepostory.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Minas Gerais");
		
		Cidade cid1 = new Cidade(null,"Uberlândia",est2);
		Cidade cid2 = new Cidade(null,"São Paulo", est1);
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
	}
}
