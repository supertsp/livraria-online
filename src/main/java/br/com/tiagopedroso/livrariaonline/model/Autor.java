package br.com.tiagopedroso.livrariaonline.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Autor {

	private Long id;
	private String nome;
	private String email;
	private LocalDate dataNascimento;
	
}
