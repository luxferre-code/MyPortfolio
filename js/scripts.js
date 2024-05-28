// scripts.js
document.addEventListener('DOMContentLoaded', () => {
    // Initialisation de AOS
    AOS.init({
        duration: 1000, // Durée de l'animation en millisecondes
        once: false // L'animation se déclenchera à chaque fois qu'on fait défiler la section dans le viewport
    });

    const navLinks = document.querySelectorAll('nav ul li a');
    navLinks.forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            const targetId = link.getAttribute('href').substring(1);
            const targetSection = document.getElementById(targetId);
            targetSection.scrollIntoView({ behavior: 'smooth' });
        });
    });
});
