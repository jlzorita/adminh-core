package edu.uoc.tfg.core.infrastructure.repository.jpa;

public interface DomainTranslatable<T> {

    T toDomain();

}
