package br.com.tiagopedroso.livrariaonline.dao;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.com.tiagopedroso.livrariaonline.model.Autor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AutorDao implements DaoContract<Autor>{
	
	private Connection conexao;

	@Override
	public void salvar(Autor novoAutor) {
		try {
			final var sql = new StringBuilder();
			
			if (novoAutor.getId() == null) {				
				sql
					.append("INSERT INTO autor ")
					.append("	(nome, email, data_nascimento) ")
					.append("VALUES ")
					.append("	(?, ?, ?) ")
				;
				
				final var preparedStatement = conexao.prepareStatement(sql.toString());
				preparedStatement.setString(1, novoAutor.getNome());
				preparedStatement.setString(2, novoAutor.getEmail());
				preparedStatement.setDate(3, Date.valueOf(novoAutor.getDataNascimento()));
				
				preparedStatement.execute();
			} else {
				sql
					.append("UPDATE autor ")
					.append("SET ")
					.append("    nome = ?, ")
					.append("    email = ?, ")
					.append("    data_nascimento = ? ")
					.append("WHERE ")
					.append("    id_autor = ? ")
				;
				
				final var preparedStatement = conexao.prepareStatement(sql.toString());
				preparedStatement.setString(1, novoAutor.getNome());
				preparedStatement.setString(2, novoAutor.getEmail());
				preparedStatement.setDate(3, Date.valueOf(novoAutor.getDataNascimento()));
				preparedStatement.setLong(4, novoAutor.getId());
				
				preparedStatement.execute();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}

	@Override
	public Autor procurarPorId(Long id) {
		try {
			final var sql = new StringBuilder();
			sql
				.append("SELECT ")
				.append("    * ")
				.append("FROM ")
				.append("    autor ")
				.append("WHERE ")
				.append("    id_autor = ? ")
			;
			
			final var preparedStatement = conexao.prepareStatement(sql.toString());
			preparedStatement.setLong(1, id);
			
			final var resultSet = preparedStatement.executeQuery();
			
			Autor autor = null;
			
			while (resultSet.next()) {
				autor = Autor
						.builder()
						.id(resultSet.getLong("id_autor"))
						.nome(resultSet.getString("nome"))
						.email(resultSet.getString("email"))
						.dataNascimento(resultSet.getDate("data_nascimento").toLocalDate())
						.build()
				;				
			}
			
			return autor;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Autor> listar() {
		try {
			final String sql = "SELECT * FROM autor";
			final var preparedStatement = conexao.prepareStatement(sql.toString());
			final var resultSet = preparedStatement.executeQuery();
			
			List<Autor> listaAutor = new ArrayList<>();
			
			while (resultSet.next()) {
				final var novoAutor = Autor
						.builder()
						.id(resultSet.getLong("id_autor"))
						.nome(resultSet.getString("nome"))
						.email(resultSet.getString("email"))
						.dataNascimento(resultSet.getDate("data_nascimento").toLocalDate())
						.build()
				;
				
				listaAutor.add(novoAutor);
			}
			
			return listaAutor;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void remover(Autor autor) {
		try {
			final var sql = "DELETE FROM autor WHERE id_autor = ?";
			
			final var preparedStatement = conexao.prepareStatement(sql);
			preparedStatement.setLong(1, autor.getId());
			
			preparedStatement.execute();			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}

}
