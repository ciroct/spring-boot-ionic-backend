package com.ciro.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ciro.cursomc.domain.Categoria;
import com.ciro.cursomc.domain.Cidade;
import com.ciro.cursomc.domain.Cliente;
import com.ciro.cursomc.domain.Endereco;
import com.ciro.cursomc.domain.Estado;
import com.ciro.cursomc.domain.ItemPedido;
import com.ciro.cursomc.domain.Pagamento;
import com.ciro.cursomc.domain.PagamentoComBoleto;
import com.ciro.cursomc.domain.PagamentoComCartao;
import com.ciro.cursomc.domain.Pedido;
import com.ciro.cursomc.domain.Produto;
import com.ciro.cursomc.domain.enums.EstadoPagamento;
import com.ciro.cursomc.domain.enums.TipoCliente;
import com.ciro.cursomc.repositories.CategoriaRepository;
import com.ciro.cursomc.repositories.CidadeRepository;
import com.ciro.cursomc.repositories.ClienteRepository;
import com.ciro.cursomc.repositories.EnderecoRepository;
import com.ciro.cursomc.repositories.EstadoRepository;
import com.ciro.cursomc.repositories.ItemPedidoRepository;
import com.ciro.cursomc.repositories.PagamentoRepository;
import com.ciro.cursomc.repositories.PedidoRepository;
import com.ciro.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private ProdutoRepository produtoRepo;
	@Autowired
	private EstadoRepository estadoRepo;
	@Autowired
	private CidadeRepository cidadeRepo;
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private EnderecoRepository enderecoRepo;
	@Autowired
	private PedidoRepository pedidoRepo;
	@Autowired
	private PagamentoRepository pagamentoRepo;
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 500.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
				
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepo.saveAll(Arrays.asList(p1, p2, p3));

		Estado uf1 = new Estado(null, "Minas Gerais");
		Estado uf2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", uf1);
		Cidade c2 = new Cidade(null, "São Paulo", uf2);
		Cidade c3 = new Cidade(null, "Santos", uf2);
		
		uf1.getCidades().addAll(Arrays.asList(c1));
		uf2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepo.saveAll(Arrays.asList(uf1, uf2));
		cidadeRepo.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "434985445377", TipoCliente.PESSOAFISICA);
		
		Endereco e1 = new Endereco(null, "Rua das Flores", "300", "Sala 800", "Centro", "38777012", cli1, c1);
		Endereco e2 = new Endereco(null, "Av. Mattos", "105", "Ap. 209", "Jardins", "38778912", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e2, e2));
		cli1.getTelefones().addAll(Arrays.asList("23488348", "983238383"));
		
		clienteRepo.saveAll(Arrays.asList(cli1));
		enderecoRepo.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("21/12/2019 11:50"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("24/12/2019 15:00"), cli1, e2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("30/01/2020 00:00"), null);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepo.saveAll(Arrays.asList(pgto1, pgto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.0);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, 500.0);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().add(ip3);
		
		p1.getItens().add(ip1);
		p2.getItens().add(ip3);
		p3.getItens().add(ip2);
		
		itemPedidoRepo.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}
