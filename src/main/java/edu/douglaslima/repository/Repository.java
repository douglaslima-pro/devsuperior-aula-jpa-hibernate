package edu.douglaslima.repository;

import java.util.Optional;

public interface Repository<T, ID> {
	
	void save(T t);
	
	Optional<T> findById(ID id);
	
	void deleteById(ID id);
	
}
