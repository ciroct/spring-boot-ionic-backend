package com.ciro.cursomc;

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
import com.ciro.cursomc.domain.Produto;
import com.ciro.cursomc.domain.enums.TipoCliente;
import com.ciro.cursomc.repositories.CategoriaRepository;
import com.ciro.cursomc.repositories.CidadeRepository;
import com.ciro.cursomc.repositories.ClienteRepository;
import com.ciro.cursomc.repositories.EnderecoRepository;
import com.ciro.cursomc.repositories.EstadoRepository;
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
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 500.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
				
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2));
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
		
	}

}
