package net.metrosystems.repository;

import org.springframework.data.repository.CrudRepository;

import net.metrosystems.domain.StudentGroup;

public interface StudentGroupRepository extends CrudRepository<StudentGroup, Integer> {

}
