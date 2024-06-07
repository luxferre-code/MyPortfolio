<%@ page import="java.util.Calendar" %>
<%@ page import="fr.valentinthuillier.portfolio.dao.*" %>
<%@ page import="fr.valentinthuillier.portfolio.dto.*" %>
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
        <link rel="icon" type="image/png" href="contents/icons/lettre-v.png">
        <script src="js/icon.js" defer></script>
        <script src="js/scripts.js" defer></script>
        <script src="js/burger.js" defer></script>
    </head>
    <body>
        <header id="home">
            <nav>
                <div class="menu-toggle">
                    <div class="bar1"></div>
                    <div class="bar2"></div>
                    <div class="bar3"></div>
                </div>
                <ul>
                    <li><a href="#home">Accueil</a></li>
                    <li><a href="#about">À propos de moi</a></li>
                    <li><a href="#projects">Projets</a></li>
                    <li><a href="#experience">Expériences professionnelles</a></li>
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

        <section id="about" data-aos="fade-up" data-aos-once="false">
            <div class="about-container">
                <div class="about-text">
                    <h2>À propos de moi</h2>
                    <div class="about-photo">
                        <img src="./contents/me.png" alt="C'est moi :)">
                    </div>
                    <p>
                        Bonjour, je m'appelle Valentin Thuillier et je suis sapeur-pompier volontaire à Oignies depuis quatre mois. J'interviens sur des missions de secours à personne. Je suis fier de contribuer à la sécurité et au bien-être de la population tout en développant mes compétences en gestion du stress, en communication, et en esprit d'équipe.
                        <br>
                        Je suis également étudiant en deuxième année de BUT Informatique à l'Université de Lille, où j'apprends les bases de la programmation, de la conception, et de l'analyse de systèmes informatiques. J'ai réalisé plusieurs projets en utilisant des méthodes agiles et des outils comme JavaFX, et j'ai obtenu de bons résultats. Je suis motivé par l'envie d'approfondir mes connaissances et de découvrir de nouvelles technologies. Je recherche actuellement un contrat en stage afin de mettre en pratique mes acquis et de renforcer mon expérience professionnelle. Je suis disponible pour échanger avec vous sur vos besoins et mes objectifs.
                    </p>
                </div>
            </div>
        </section>

        <section id="projects" data-aos="fade-up" data-aos-once="false">
            <h2>Projets</h2>
            <div class="project-list">
                <%
                    ProjetDao projetDao = new ProjetDao();
                    Projet[] projets = projetDao.findAll();

                    for(Projet p : projets) { %>
                        <div class="project-item" data-aos="fade-up" data-aos-delay="100" data-aos-once="false">
                            <img src="<%= p.getImageURL() %>" alt="<%= p.getName() %> Image">
                            <h3><%= p.getName() %></h3>
                            <p><%= p.getDescription() %></p>
                            <a href="#">En savoir plus</a>
                        </div>
                    <%}
                %>
            </div>
        </section>

        <section id="experience" data-aos="fade-up" data-aos-once="false">
            <h2>Expériences professionnelles</h2>
            <div class="experience-list">
                <%
                    JobsDao jobsdao = new JobsDao();
                    Jobs[] jobs = jobsdao.findAll();

                    for(Jobs j : jobs) { %>
                        <div class="experience-item" data-aos="fade-up" data-aos-delay="100" data-aos-once="false">
                            <h3><%= j.getName() %></h3>
                            <p><%= j.getDescription() %></p>
                            <p><em><%= j.getDates() %></em></p>
                        </div>
                    <% }
                %>
            </div>
        </section>

        <section id="skills" data-aos="fade-up" data-aos-once="false">
            <h2>Compétences et Centres d'intérêt</h2>
            <div class="skills-interests">
                <div class="skills" data-aos="fade-right" data-aos-once="false">
                    <h3>Compétences</h3>
                    <ul>
                        <%
                            CompetenceDao competenceDao = new CompetenceDao();
                            Competence[] competences = competenceDao.findAll();

                            for(Competence c : competences) { %>
                                <li><%= c.getName() %></li>
                            <% }
                        %>
                    </ul>
                </div>
                <div class="interests" data-aos="fade-left" data-aos-once="false">
                    <h3>Centres d'intérêt</h3>
                    <ul>
                        <li>Développement</li>
                        <li>Sport</li>
                        <li>Musique</li>
                        <li>Sauvetage</li>
                    </ul>
                </div>
            </div>
        </section>

        <section id="contact" data-aos="fade-up" data-aos-once="false">
            <h2>Contact</h2>
            <form action="contact" method="get">
                <label for="name">Nom</label>
                <input type="text" id="name" name="name" required>
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
                <label for="message">Message</label>
                <textarea id="message" name="message" required></textarea>
                <button type="submit">Envoyer</button>
            </form>
            <div class="social-links">
                <a href="https://www.linkedin.com/in/luxferrevt/">LinkedIn</a>
                <a href="https://github.com/luxferre-code/">GitHub</a>
            </div>
        </section>

        <footer>
            <div class="footer-content">
                <p>Made by Valentin THUILLIER</p>
                <p>&copy; <%= Calendar.getInstance().get(Calendar.YEAR) %> Valentin THUILLIER. Tous droits réservés.</p>
                <p>Made with <span class="heart">&hearts;</span></p>
                <a href="https://www.flaticon.com/fr/stickers-gratuites/lettre-v" title="lettre v stickers">Icones par ukeicon - Flaticon</a>
            </div>
        </footer>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js"></script>
    </body>
</html>
