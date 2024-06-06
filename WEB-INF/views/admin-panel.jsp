<%@ page import="fr.valentinthuillier.portfolio.dao.*" %>
<%@ page import="fr.valentinthuillier.portfolio.dto.*" %>
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
    </style>
</head>
<body>
<div class="panel-container">
    <h1>Welcome to Admin Panel</h1>
    <p>Here you can manage your application...</p>
</div>

<div class="panel-container">
    <h2>Messages from Contacts</h2>
    <%
        MessageDao messageDao = new MessageDao();
        Message[] messages = messageDao.findAllUnanswered();
        for (Message m : messages) {
    %>
    <div>
        <h3><%= m.getName() %> (<a href="mailto:<%= m.getMail() %>"><%= m.getMail() %></a>)</h3>
        <p><%= m.getMessage() %></p>
        <p><em>Replied: <%= m.isRepondu() ? "Yes" : "No" %></em></p>
        <%
            if (!m.isRepondu()) {
        %>
        <form action="markAsReplied" method="post" style="display:inline;">
            <input type="hidden" name="id" value="<%= m.getId() %>">
            <button type="submit">Mark as Replied</button>
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
    <h2>Add Experience</h2>
    <form action="addExperience" method="post">
        <div>
            <label for="experienceName">Experience Name</label>
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
        <button type="submit">Add Experience</button>
    </form>
</div>

<div class="panel-container">
    <h2>Add Project</h2>
    <form action="addProject" method="post" enctype="multipart/form-data">
        <div>
            <label for="projectName">Project Name</label>
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
        <button type="submit">Add Project</button>
    </form>
</div>

<div class="panel-container">
    <h2>Add Competence</h2>
    <form action="addCompetence" method="post">
        <div>
            <label for="competenceName">Competence Name</label>
            <input type="text" id="competenceName" name="name" required>
        </div>
        <button type="submit">Add Competence</button>
    </form>
</div>

<div class="panel-container">
    <h2>Manage Experiences</h2>
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
            <button type="submit">Delete</button>
        </form>
    </div>
    <%
        }
    %>
</div>

<div class="panel-container">
    <h2>Manage Projects</h2>
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
            <button type="submit">Delete</button>
        </form>
    </div>
    <%
        }
    %>
</div>

<div class="panel-container">
    <h2>Manage Competences</h2>
    <%
        CompetenceDao competenceDao = new CompetenceDao();
        Competence[] competences = competenceDao.findAll();
        for (Competence c : competences) {
    %>
    <div>
        <h3><%= c.getName() %></h3>
        <form action="deleteCompetence" method="post" style="display:inline;">
            <input type="hidden" name="id" value="<%= c.getId() %>">
            <button type="submit">Delete</button>
        </form>
    </div>
    <%
        }
    %>
</div>

</body>
</html>
