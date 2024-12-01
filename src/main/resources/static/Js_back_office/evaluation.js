(function ($) {
    "use strict";

    // Soumission du formulaire d'évaluation
    $(".form-evaluer").on("submit", async function (event) {
        event.preventDefault();

        const actionUrl = $(this).attr("action");
        const formData = $(this).serialize();

        try {
            const response = await $.ajax({
                url: actionUrl,
                type: "POST",
                data: formData,
                dataType: "json"
            });

            if (response.status === "success") {
                const idCandidature = response.idCandidature;

                if (response.estPlanifier && response.entretienFini) {
                    // Si l'entretien est déjà planifié
                    Swal.fire({
                        title: 'Évaluation soumise avec succès !',
                        text: `Entretien terminé avec note totale de ${response.noteTotale}, Voulez-vous accepter ce candidat ?`,
                        icon: 'success',
                        showCancelButton: true,
                        confirmButtonText: 'Accepter',
                        cancelButtonText: 'Rejeter',
                        reverseButtons: true
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.href = `/accepter?idCandidature=${idCandidature}`;
                        } else {
                            window.location.href = `/rejeter?idCandidature=${idCandidature}`;
                        }
                    });
                } else {
                    // Si l'entretien n'est pas encore planifié
                    Swal.fire({
                        title: 'Évaluation soumise avec succès !',
                        text: "Souhaitez-vous planifier un entretien pour ce candidat ?",
                        icon: 'success',
                        showCancelButton: true,
                        confirmButtonText: 'Oui, planifier',
                        cancelButtonText: 'Non, plus tard',
                        reverseButtons: true
                    }).then((result) => {
                        if (result.isConfirmed) {
                            // Afficher la modal pour la planification
                            $("#idCandidature").val(idCandidature);
                            $("#modalPlanification").modal("show");
                        } else {
                            Swal.fire(
                                'Planification annulée',
                                'Vous pouvez revenir pour planifier un entretien plus tard.',
                                'info'
                            ).then(() => {
                                window.location.href = `/candidatures`;
                            });
                        }
                    });
                }
            } else {
                Swal.fire({
                    icon: "error",
                    title: "Erreur !",
                    text: response.message,
                    confirmButtonText: "Ressayer"
                });
            }
        } catch (error) {
            Swal.fire({
                icon: "error",
                title: "Erreur lors de la soumission",
                text: "Une erreur est survenue. Veuillez réessayer.",
                confirmButtonText: "OK"
            });
            console.error("Erreur : ", error);
        }
    });

    // Soumission du formulaire de planification
    $("#submitPlanification").on("click", async function () {
        const actionUrl = "/planifierEntretien";
        const formData = $("#formPlanification").serialize();

        try {
            const response = await $.ajax({
                url: actionUrl,
                type: "GET",
                data: formData,
                dataType: "json"
            });

            if (response.status === "success") {
                Swal.fire({
                    icon: "success",
                    title: "Entretien planifié avec succès !",
                    text: "L'entretien a été enregistré.",
                    confirmButtonText: "OK"
                }).then(() => {
                    $("#modalPlanification").modal("hide");
                    window.location.href = `/candidatures`;
                });
            } else {
                Swal.fire({
                    icon: "error",
                    title: "Erreur !",
                    text: response.message,
                    confirmButtonText: "Ressayer"
                });
            }
        } catch (error) {
            Swal.fire({
                icon: "error",
                title: "Erreur lors de la planification",
                text: "Une erreur est survenue. Veuillez réessayer.",
                confirmButtonText: "OK"
            });
            console.error("Erreur : ", error);
        }
    });

    // Évaluation automatique
    $("#autoEvaluate").on("click", async function () {
        try {
            Swal.fire({
                title: "Évaluation automatique en cours...",
                text: "Veuillez patienter",
                icon: "info",
                showConfirmButton: false,
            }).then(() => {
                Swal.fire({
                    title: "Succès",
                    text: "Évaluation automatique effectuée avec succès.",
                    icon: "success",
                    confirmButtonText: "OK"
                }).then(() => {
                    // Attente du clic sur "OK" avant de rediriger
                    window.location.href = "/candidatures";  // Redirection après avoir cliqué sur "OK"
                });
            });
            
        } catch (error) {
            Swal.fire({
                icon: "error",
                title: "Erreur lors de l'évaluation automatique",
                text: "Une erreur est survenue. Veuillez réessayer.",
                confirmButtonText: "OK"
            });
            console.error("Erreur : ", error);
        }
    });

})(jQuery);
