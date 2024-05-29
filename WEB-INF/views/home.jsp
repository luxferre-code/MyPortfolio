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
        <title>Portfolio</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&family=Roboto+Slab:wght@700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
        <link rel="stylesheet" href="./css/main.css">
    </head>
    <body>
        <header id="home">
            <nav>
                <ul>
                    <li><a href="#home">Accueil</a></li>
                    <li><a href="#gallery">Galerie</a></li>
                    <li><a href="#projects">Projets</a></li>
                    <li><a href="#skills">Compétences</a></li>
                    <li><a href="#contact">Contact</a></li>
                </ul>
            </nav>
            <div class="hero" data-aos="fade-in" data-aos-once="false">
                <div class="hero-overlay"></div>
                <div class="hero-content">
                    <h1>~ Valentin THUILLIER ~</h1>
                    <p>Etudiant en informatique & Sapeur Pompier Volontaire</p>
                    <p>Lille | Disponible dès Juillet !</p>
                </div>
            </div>
        </header>
    
        <section id="gallery" data-aos="fade-up" data-aos-once="false">
            <h2>Galerie Photo</h2>
            <div class="photo-grid">
                <img src="image1.jpg" alt="Photo 1">
                <img src="image2.jpg" alt="Photo 2">
                <!-- Ajoutez plus d'images ici -->
            </div>
        </section>
    
        <section id="projects" data-aos="fade-up" data-aos-once="false">
            <h2>Projets</h2>
            <div class="project-list">
                <div class="project-item" data-aos="fade-up" data-aos-delay="100" data-aos-once="false">
                    <h3>Projet 1</h3>
                    <p>Description courte du projet.</p>
                    <a href="#">En savoir plus</a>
                </div>
                <div class="project-item" data-aos="fade-up" data-aos-delay="200" data-aos-once="false">
                    <h3>Projet 2</h3>
                    <p>Description courte du projet.</p>
                    <a href="#">En savoir plus</a>
                </div>
                <!-- Ajoutez plus de projets ici -->
            </div>
        </section>
    
        <section id="skills" data-aos="fade-up" data-aos-once="false">
            <h2>Compétences et Centres d'intérêt</h2>
            <div class="skills-interests">
                <div class="skills" data-aos="fade-right" data-aos-once="false">
                    <h3>Compétences</h3>
                    <ul>
                        <li>Compétence 1</li>
                        <li>Compétence 2</li>
                        <!-- Ajoutez plus de compétences ici -->
                    </ul>
                </div>
                <div class="interests" data-aos="fade-left" data-aos-once="false">
                    <h3>Centres d'intérêt</h3>
                    <ul>
                        <li>Intérêt 1</li>
                        <li>Intérêt 2</li>
                        <!-- Ajoutez plus de centres d'intérêt ici -->
                    </ul>
                </div>
            </div>
        </section>
    
        <section id="contact" data-aos="fade-up" data-aos-once="false">
            <h2>Contact</h2>
            <form action="submit_form.php" method="post">
                <label for="name">Nom</label>
                <input type="text" id="name" name="name" required>
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
                <label for="message">Message</label>
                <textarea id="message" name="message" required></textarea>
                <button type="submit">Envoyer</button>
            </form>
            <div class="social-links">
                <a href="#">LinkedIn</a>
                <a href="#">GitHub</a>
                <!-- Ajoutez plus de liens sociaux ici -->
            </div>
        </section>
    
        <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js"></script>
        <script src="./js/scripts.js"></script>
    </body>
    </html>