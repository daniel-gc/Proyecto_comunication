package mx.pliis.comunicaciones.persistencia.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.pliis.comunicaciones.persistencia.hibernate.entity.PanicoEntity;

@Repository
public interface PanicoEntityRepository extends JpaRepository<PanicoEntity, Integer> {

}
