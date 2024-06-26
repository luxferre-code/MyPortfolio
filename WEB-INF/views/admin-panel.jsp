<%@ page import="fr.valentinthuillier.portfolio.dao.*" %>
<%@ page import="fr.valentinthuillier.portfolio.dto.*" %>
<%@ page import="fr.valentinthuillier.portfolio.CV" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) { %>
        <script>window.alert("<%= error %>")</script>
    <% }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }
        .panel-container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .panel-container h2 {
            margin-top: 0;
        }
        .panel-container form {
            margin-bottom: 20px;
        }
        .panel-container form div {
            margin-bottom: 10px;
        }
        .panel-container form label {
            display: block;
            margin-bottom: 5px;
        }
        .panel-container form input, 
        .panel-container form textarea {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .panel-container form button {
            padding: 10px 15px;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .panel-container form button:hover {
            background-color: #0056b3;
        }
        #cv {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 10px;
            width: 80svw;
            height: auto;
            min-height: 100svh;
        }
    </style>
</head>
<body>
<div class="panel-container">
    <h1>Bienvenue sur le panel d'administration du site !</h1>
    <p>Vous pouvez ici gérer l'entièreté des fonctionnalités de personnalisation du site</p>
</div>

<div class="panel-container">
    <h2>Messages utilisateurs</h2>
    <%
        MessageDao messageDao = new MessageDao();
        Message[] messages = messageDao.findAllUnanswered();
        for (Message m : messages) {
    %>
    <div>
        <h3><%= m.getName() %> (<a href="mailto:<%= m.getMail() %>"><%= m.getMail() %></a>)</h3>
        <p><%= m.getMessage() %></p>
        <p><em>Répondu: <%= m.isRepondu() ? "Oui" : "Non" %></em></p>
        <%
            if (!m.isRepondu()) {
        %>
        <form action="markAsReplied" method="post" style="display:inline;">
            <input type="hidden" name="id" value="<%= m.getId() %>">
            <button type="submit">Marqué comme répondu !</button>
        </form>
        <%
            }
        %>
    </div>
    <%
        }
    %>
</div>

<div class="panel-container">
    <h2>Ajout d'étude</h2>
    <form action="addStudy" method="post">
        <div>
            <label for="name">Nom de l'étude</label>
            <input type="text" id="studyName" name="name" required>
        </div>
        <div>
            <label for="description">Description</label>
            <textarea id="studyDescription" name="description" required></textarea>
        </div>
        <div>
            <label for="lieu">Lieu</label>
            <input type="text" id="lieu" name="lieu" required>
        </div>
        <div>
            <label for="date_debut">Date de début</label>
            <input type="date" id="date_debut" name="date_debut" required>
        </div>
        <div>
            <label for="date_fin">Date de fin</label>
            <input type="date" id="date_fin" name="date_fin" required>
        </div>
        <button type="submit">Ajout</button>
    </form>
</div>

<div class="panel-container">
    <h2>Ajout d'expérience</h2>
    <form action="addExperience" method="post">
        <div>
            <label for="experienceName">Nom de l'expérience</label>
            <input type="text" id="experienceName" name="name" required>
        </div>
        <div>
            <label for="experienceDescription">Description</label>
            <textarea id="experienceDescription" name="description" required></textarea>
        </div>
        <div>
            <label for="entreprise">Entreprise</label>
            <input type="text" id="entreprise" name="entreprise" required>
        </div>
        <div>
            <label for="startDate">Date de début</label>
            <input type="date" id="startDate" name="startDate" required>
        </div>
        <div>
            <label for="endDate">Date de fin</label>
            <input type="date" id="endDate" name="endDate" required>
        </div>
        <button type="submit">Ajout</button>
    </form>
</div>

<div class="panel-container">
    <h2>Ajout d'un projet</h2>
    <form action="addProject" method="post" enctype="multipart/form-data">
        <div>
            <label for="projectName">Nom du projet</label>
            <input type="text" id="projectName" name="name" required>
        </div>
        <div>
            <label for="projectDescription">Description</label>
            <textarea id="projectDescription" name="description" required></textarea>
        </div>
        <div>
            <label for="projectImage">Image</label>
            <input type="file" id="projectImage" name="image" required>
        </div>
        <button type="submit">Ajout</button>
    </form>
</div>

<div class="panel-container">
    <h2>Ajout d'une compétence</h2>
    <form action="addCompetence" method="post">
        <div>
            <label for="competenceName">Nom de la compétence</label>
            <input type="text" id="competenceName" name="name" required>
        </div>
        <button type="submit">Ajout</button>
    </form>
</div>

<div class="panel-container">
    <h2>Ajout du CV</h2>
    <form action="addCV" method="post" enctype="multipart/form-data">
        <div>
            <label for="cvFile">Fichier CV</label>
            <input type="file" id="cvFile" name="cv" required>
        </div>
        <button type="submit">Ajout</button>
    </form>
</div>

<div class="panel-container">
    <h2>Gestion du CV</h2>
    <%= CV.toHTML() %>
</div>

<div class="panel-container">
    <h2>Gestion des études</h2>
    <%
        StudyDao studyDao = new StudyDao();
        Study[] studies = studyDao.findAll();
        for (Study s : studies) {
    %>
    <div>
        <h3><%= s.getName() %></h3>
        <p><%= s.getDescription() %></p>
        <p><em><%= s.getLieu() %></em></p>
        <p><em><%= s.getDates() %></em></p>
        <form action="deleteStudy" method="post" style="display:inline;">
            <input type="hidden" name="id" value="<%= s.getId() %>">
            <button type="submit">Supprimer</button>
        </form>
    </div>
    <%
        }
    %>
</div>

<div class="panel-container">
    <h2>Gestion des expériences</h2>
    <%
        JobsDao jobsDao = new JobsDao();
        Jobs[] jobs = jobsDao.findAll();
        for (Jobs j : jobs) {
    %>
    <div>
        <h3><%= j.getName() %></h3>
        <p><%= j.getDescription() %></p>
        <p><em><%= j.getEntreprise() %></em></p>
        <p><em><%= j.getStartDate() %> - <%= j.getEndDate() %></em></p>
        <form action="deleteExperience" method="post" style="display:inline;">
            <input type="hidden" name="id" value="<%= j.getId() %>">
            <button type="submit">Supprimer</button>
        </form>
    </div>
    <%
        }
    %>
</div>

<div class="panel-container">
    <h2>Gestion des projets</h2>
    <%
        ProjetDao projetDao = new ProjetDao();
        Projet[] projets = projetDao.findAll();
        for (Projet p : projets) {
    %>
    <div>
        <h3><%= p.getName() %></h3>
        <p><%= p.getDescription() %></p>
        <p><img src="<%= p.getImageURL() %>" alt="<%= p.getName() %> Image" width="100"></p>
        <form action="deleteProject" method="post" style="display:inline;">
            <input type="hidden" name="id" value="<%= p.getId() %>">
            <button type="submit">Supprimer</button>
        </form>
    </div>
    <%
        }
    %>
</div>

<div class="panel-container">
    <h2>Gestion des compétences</h2>
    <%
        CompetenceDao competenceDao = new CompetenceDao();
        Competence[] competences = competenceDao.findAll();
        for (Competence c : competences) {
    %>
    <div>
        <h3><%= c.getName() %></h3>
        <form action="deleteCompetence" method="post" style="display:inline;">
            <input type="hidden" name="id" value="<%= c.getId() %>">
            <button type="submit">Supprimer</button>
        </form>
    </div>
    <%
        }
    %>
</div>

</body>
</html>
