package net.metrosystems.repository;

import org.springframework.data.repository.CrudRepository;

import net.metrosystems.domain.Grade;

public interface GradeRepository extends CrudRepository<Grade, Integer> {

}
