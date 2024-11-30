package com.rh.service.BackOffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rh.model.BackOffice.Candidat;
import com.rh.model.BackOffice.Candidature;
import com.rh.model.BackOffice.Notification;
import com.rh.model.BackOffice.StatutNotification;
import com.rh.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;


    public void envoyerNotification(Candidature candidature, String typeNotification) {
        String message;
        if ("Acceptation".equals(typeNotification)) {
            message = "Félicitations, vous avez été accepté pour ce poste.";
        } else if ("Rejet".equals(typeNotification)) {
            message = "Désolé, votre candidature a été rejetée.";
        } else {
            message = "Statut de la candidature modifié.";
        }

        // Récupérer l'ID du candidat
        Candidat candidat = candidature.getCandidat();
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setTypeNotification(typeNotification);
        notification.setCandidat(candidat); // Associer à l'ID du candidat
        notification.setStatut(StatutNotification.NON_LUE); // Statut initialisé à "Non lue"

        // Sauvegarder la notification dans la base de données
        notificationRepository.save(notification);
    }

    public Notification envoyerNotification(Notification notification ){
        return this.notificationRepository.save(notification);
    }
}
