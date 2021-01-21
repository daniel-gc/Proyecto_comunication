package mx.pliis.comunicaciones.persistencia.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.pliis.comunicaciones.persistencia.hibernate.entity.TipoPanicoEntity;

@Repository
public interface TipoPanicoEntityRepository extends JpaRepository<TipoPanicoEntity, Integer> {
	public TipoPanicoEntity findByCodigo(String codigo);
}
