<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un Besoin de Recrutement</title>
</head>
<body>
    <h1>Ajouter un Besoin de Recrutement</h1>

    <%-- Section pour récupérer la liste des diplômes depuis le modèle --%>
    <% 
        java.util.List<com.rh.model.Diplome> diplomes = (java.util.List<com.rh.model.Diplome>) request.getAttribute("diplomes");
    %>

    <form action="/besoin-recrutement/ajouter" method="post">
        <label for="idDepartement">Département :</label>
        <input type="number" id="idDepartement" name="idDepartement" required><br>

        <label for="dateDemande">Date de la demande :</label>
        <input type="date" id="dateDemande" name="dateDemande" required>


        <label for="anneesExperience">Années d'expérience :</label>
        <input type="number" id="anneesExperience" name="anneesExperience"><br>

        <label for="idDiplome">Diplôme :</label>
        <select id="idDiplome" name="idDiplome">
            <% if (diplomes != null) { %>
                <% for (com.rh.model.Diplome diplome : diplomes) { %>
                    <option value="<%= diplome.getIdDiplome() %>"><%= diplome.getLibelle() %></option>
                <% } %>
            <% } %>
        </select><br>

        <button type="submit">Ajouter</button>
    </form>
</body>
</html>
