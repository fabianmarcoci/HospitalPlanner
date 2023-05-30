package org.example.HospitalPlanner.repository;

import org.example.HospitalPlanner.model.Doctor;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface DoctorRepository extends CrudRepository<Doctor,Integer> {

    @Override
    <S extends Doctor> S save(S var1);

    @Override
    <S extends Doctor> Iterable<S> saveAll(Iterable<S> var1);

    @Override
    Optional<Doctor> findById(Integer var1);

    @Override
    boolean existsById(Integer var1);

    @Override
    Iterable<Doctor> findAll();

    @Override
    Iterable<Doctor> findAllById(Iterable<Integer> var1);

    @Override
    long count();

    @Override
    void deleteById(Integer var1);

    @Override
    void delete(Doctor var1);

    @Override
    void deleteAllById(Iterable<? extends Integer> var1);

    @Override
    void deleteAll(Iterable<? extends Doctor> var1);
}
