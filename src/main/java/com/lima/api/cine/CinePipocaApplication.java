package com.lima.api.cine;

import com.lima.api.cine.enun.FormaPagamento;
import com.lima.api.cine.enun.IdiomaFilme;
import com.lima.api.cine.model.Cliente;
import com.lima.api.cine.model.Filme;
import com.lima.api.cine.model.Sala;
import com.lima.api.cine.model.Sessao;
import com.lima.api.cine.repository.ClienteRepository;
import com.lima.api.cine.repository.FilmeRepository;
import com.lima.api.cine.repository.SessaoRepository;
import com.lima.api.cine.service.IngressoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

// TODO: Aplicar um formatador/lint exemplo checkstyle ou spotless
// TODO: O projeto utiliza bastante manutenção de estados. Check a possibilidade de aplicar o pattern GOF state

@SpringBootApplication
public class CinePipocaApplication implements CommandLineRunner {

	@Autowired
	FilmeRepository filmeRepository;

	@Autowired
	SessaoRepository sessaoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	IngressoService ingressoService;

	public static void main(String[] args) {
		SpringApplication.run(CinePipocaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Filme filme = new Filme("O auto da compadecida 2", IdiomaFilme.DUBLADO, "2h");
		Sala sala = new Sala("A");
		LocalDateTime agora = LocalDateTime.now();
		LocalDateTime dataHoraInicio = agora.withHour(16).withMinute(0).withSecond(0).withNano(0);
		LocalDateTime dataHoraFim = dataHoraInicio.plus(2, ChronoUnit.HOURS);

		//filmeRepository.save(filme);
		//filme = filmeRepository.findById(1L).get();

		//Sessao sessao = new Sessao(dataHoraInicio, dataHoraFim, filme, sala, new BigDecimal("40.00"));
		//sessaoRepository.save(sessao);

		Cliente cliente = new Cliente("João");
		clienteRepository.save(cliente);

		Sessao sessao = sessaoRepository.findById(1L).get();
		ingressoService.comprar(cliente, sessao, 4, FormaPagamento.PIX);
	}
}
