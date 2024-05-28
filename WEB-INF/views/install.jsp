<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Installation du Serveur</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .install-container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        .install-container h2 {
            margin-bottom: 20px;
        }
        .install-container input[type="text"],
        .install-container input[type="password"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .install-container input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .install-container input[type="submit"]:hover {
            background-color: #45a049;
        }
        .error {
            color: red;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="install-container">
    <h2>Installation du Serveur</h2>
    <form action="<%= request.getContextPath() %>/install" method="post">
        <% if (request.getAttribute("error") != null) { %>
        <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>
        <input type="text" name="ip" placeholder="IP de la base de données" required>
        <input type="text" name="port" placeholder="Port de la base de données" required>
        <input type="text" name="database" placeholder="Nom de la base de données" required>
        <input type="text" name="login" placeholder="Login" required>
        <input type="password" name="password" placeholder="Mot de passe" required>
        <input type="text" name="adminUsername" placeholder="Nom d'utilisateur admin" required>
        <input type="password" name="adminPassword" placeholder="Mot de passe admin" required>
        <input type="submit" value="Installer">
    </form>
</div>
</body>
</html>
