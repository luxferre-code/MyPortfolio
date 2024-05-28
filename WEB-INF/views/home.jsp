<%@ page import="java.util.Calendar" %>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    String lang = session.getAttribute("lang") == null ? "fr" : (String) session.getAttribute("lang");
%>
<html lang="<%= lang %>">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Valentin THUILLIER - Développeur back-end</title>
</head>
<body>
<header>
    <h1>Valentin THUILLIER</h1>
    <h2>Etudiant en développement informatique</h2>
</header>

<nav>
    <ul>
        <li><a href="#about-me">A propos de moi</a></li>
        <li><a href="#skills">Compétences</a></li>
        <li><a href="#projects">Projets</a></li>
        <li><a href="#contact">Contact</a></li>
    </ul>
</nav>

<section id="about-me">
    <h3>A propos de moi</h3>
    <p>
        Hey ! Content de te voir ici. Je me présente, je m'appelle Valentin THUILLIER j'ai <%= Calendar.getInstance().get(Calendar.YEAR) - 2004 %> ans
        et je suis actuellement étudiant en développement informatique.
    </p>
</section>

<section id="skills">
    <h3>Compétences</h3>
    <p>Liste de mes compétences...</p>
</section>

<section id="projects">
    <h3>Projets</h3>
    <p>Liste de mes projets...</p>
</section>

<section id="contact">
    <h3>Contact</h3>
    <p>Formulaire de contact...</p>
</section>
</body>
</html>
