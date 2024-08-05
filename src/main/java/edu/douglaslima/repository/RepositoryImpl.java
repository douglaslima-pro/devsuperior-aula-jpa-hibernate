package edu.douglaslima.repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.NoSuchElementException;
import java.util.Optional;

import edu.douglaslima.config.EntityManagerProvider;
import jakarta.persistence.EntityManager;


public abstract class RepositoryImpl<T, ID> implements Repository<T, ID> {
	
	/**
	 * <p>
	 * Retorna um array de {@code Type} que representa todos os <strong>argumentos de tipo</strong> da classe parametrizada.
	 * </p>
	 * <p>
	 * Necessário para obter informações de tipo sobre os <strong>argumentos de tipo</strong> da classe parametrizada,
	 * como, por exemplo: obter um objeto {@code Class<?>} que representa um tipo genérico.
	 * </p>
	 * @return um array de {@code Type}
	 */
	private Type[] getTypeArguments() {
		/*
		 * A classe ParameterizedType é usada para obter os argumentos de tipo de uma classe genérica.
		 * Quando um objeto do tipo ParameterizedType é criado, todos os seus argumentos de tipo são criados
		 * de forma recursiva e podem ser obtidos pelo método getActualTypeArguments().
		 */
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		return type.getActualTypeArguments();
	}

	@Override
	public void save(T t) {
		EntityManager em = EntityManagerProvider.getInstance()
				.getEntityManagerFactory()
				.createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Optional<T> findById(ID id) {
		EntityManager em = EntityManagerProvider.getInstance()
				.getEntityManagerFactory()
				.createEntityManager();
		@SuppressWarnings("unchecked")
		Optional<T> t = Optional.ofNullable(em.find((Class<T>) getTypeArguments()[0], id));
		em.close();
		return t;
	}

	@Override
	public void deleteById(ID id) throws NoSuchElementException {
		EntityManager em = EntityManagerProvider.getInstance()
				.getEntityManagerFactory()
				.createEntityManager();
		@SuppressWarnings("unchecked")
		T t = em.find((Class<T>) getTypeArguments()[0], id);
		if (t == null) {
			throw new NoSuchElementException(String.format("Objeto com o ID %s não existe!", id.toString()));
		}
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
		em.close();
	}

}
