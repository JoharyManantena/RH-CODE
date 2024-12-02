package com.rh.service;

import com.rh.model.HeuresSup;
import com.rh.repository.HeuresSupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HeuresSupService {

    @Autowired
    private HeuresSupRepository heuresSupRepository;

    // Méthode pour récupérer les heures supplémentaires d'un personnel donné pour un mois et une année
    public List<HeuresSup> getHeuresSupForPersonnelAndMonth(Integer idPersonnel, int year, int month) {
        Calendar calendar = Calendar.getInstance();

        // Début du mois
        calendar.set(year, month - 1, 1, 0, 0, 0);
        Date startDate = calendar.getTime();

        // Fin du mois
        calendar.set(year, month - 1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
        Date endDate = calendar.getTime();

        return heuresSupRepository.findByPersonnel_IdPersonnelAndDateHeureSupBetween(idPersonnel, startDate, endDate);
    }
}
