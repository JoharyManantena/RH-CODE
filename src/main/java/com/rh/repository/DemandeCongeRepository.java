package  com.rh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rh.model.DemandeConge;

public interface DemandeCongeRepository extends JpaRepository<DemandeConge, Integer> {
    List<DemandeConge> findByPersonnelIdPersonnel(Integer idPersonnel);
    List<DemandeConge> findByStatut(String statut);

    @Query("SELECT d FROM DemandeConge d WHERE d.personnel.idPersonnel = :idPersonnel AND d.statut = :statut")
    List<DemandeConge> findByPersonnelIdAndStatut(@Param("idPersonnel") Integer idPersonnel, @Param("statut") String statut);

    @Modifying
    @Query(value = "CALL validate_demande_conge(:id_demande)", nativeQuery = true)
    void validerDemandeConge(@Param("id_demande") Integer idDemande);
}
