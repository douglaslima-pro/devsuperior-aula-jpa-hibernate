package edu.douglaslima;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import edu.douglaslima.config.EntityManagerProvider;
import edu.douglaslima.domain.Pessoa;
import edu.douglaslima.repository.PessoaRepository;

public class App {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcao = 0;
		
		do {
			System.out.println("""
					--- Sistema de Cadastro de Pessoas ---

					1. Cadastrar
					2. Pesquisar pelo ID
					3. Excluir pelo ID
					4. Sair

					""");
			System.out.print("Digite o número da sua opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine(); // limpa o buffer de entrada
			System.out.println();
			
			switch (opcao) {
			case 1: {
				System.out.print("- Insira o nome: ");
				String nome = scanner.nextLine();
				System.out.print("- Insira o e-mail: ");
				String email = scanner.next();
				Pessoa p = new Pessoa(nome, email);
				PessoaRepository.getInstance()
					.save(p);
				System.out.println("~ Pessoa cadastrada com sucesso!");
				break;
			}
			case 2: {
				System.out.print("- Digite o ID: ");
				Integer id = scanner.nextInt();
				Optional<Pessoa> p = PessoaRepository.getInstance()
					.findById(id);
				if (p.isEmpty()) {
					System.out.println(String.format("Não existe pessoa com o ID %s!", id));
					break;
				}
				System.out.println(p.get());
				break;
			}
			case 3:
				System.out.print("- Digite o ID: ");
				Integer id = scanner.nextInt();
				try {
					PessoaRepository.getInstance()
						.deleteById(id);
					System.out.println("Pessoa excluída com sucesso!");
				} catch (NoSuchElementException e) {
					System.out.println(String.format("Não existe pessoa com o ID %s!", id));
				}
			case 4:
				break;
			default:
				System.out.println("Opção inválida!");
			}
			System.out.println();
			
		} while (opcao != 4);
		
		System.out.println("Programa encerrado.");
		// fecha os recursos
		scanner.close();
		EntityManagerProvider.closeEntityManagerFactory();
	}

}
