package org.java.app.db.repo;

import org.java.app.db.pojo.Offerta;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OffertaRepo extends JpaRepository<Offerta, Integer> {

}
