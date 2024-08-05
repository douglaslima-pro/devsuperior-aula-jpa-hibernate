package edu.douglaslima.repository;

import edu.douglaslima.domain.Pessoa;

public class PessoaRepository extends RepositoryImpl<Pessoa, Integer> {

	private static PessoaRepository singleton;
	
	private PessoaRepository() {}
	
	public static PessoaRepository getInstance() {
		if (singleton == null) {
			singleton = new PessoaRepository();
		}
		return singleton;
	}
	
}
