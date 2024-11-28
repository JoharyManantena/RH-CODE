(function ($) {
    "use strict";

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
                        $("#idCandidature").val(response.idCandidature); // Associer l'ID à la modal
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
        const actionUrl = "/planifierEntretien"; // Endpoint pour enregistrer l'entretien
        const formData = $("#formPlanification").serialize();

        try {
            const response = await $.ajax({
                url: actionUrl,
                type: "POST",
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
                    $("#modalPlanification").modal("hide"); // Fermer la modal
                    window.location.href = `/candidatures`; // Redirection après la planification
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
})(jQuery);
