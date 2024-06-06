document.addEventListener('DOMContentLoaded', () => {
    // Initialisation de AOS
    AOS.init({
        duration: 1000, // Durée de l'animation en millisecondes
        once: false // L'animation se déclenchera à chaque fois qu'on fait défiler la section dans le viewport
    });

    const sections = document.querySelectorAll('header, section'); // Ajout du header
    const navLinks = document.querySelectorAll('nav ul li a');

    const observerOptions = {
        root: null,
        rootMargin: '0px',
        threshold: 0.6 // Déclenchement quand 60% de la section est visible
    };

    const observer = new IntersectionObserver((entries, observer) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                navLinks.forEach(link => {
                    link.classList.remove('active');
                    if (link.getAttribute('href').substring(1) === entry.target.id) {
                        link.classList.add('active');
                    }
                });
            }
        });
    }, observerOptions);

    sections.forEach(section => {
        observer.observe(section);
    });

    navLinks.forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            const targetId = link.getAttribute('href').substring(1);
            const targetSection = document.getElementById(targetId);
            targetSection.scrollIntoView({ behavior: 'smooth' });
        });
    });
});

document.querySelector('form[method="get"]').addEventListener('submit', (e) => {
    e.preventDefault();

    const form = e.target;
    const submitButton = form.querySelector('button[type="submit"]');
    
    submitButton.disabled = true;

    const formData = new FormData(form);
    const params = new URLSearchParams(formData);
    const url = `${form.action}?${params.toString()}`;

    fetch(url, {
        method: form.method
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            form.reset();
            alert('Votre message a bien été envoyé !');
        } else {
            alert(data.message);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Une erreur est survenue lors de l\'envoi du message.');
    })
    .finally(() => {
        submitButton.disabled = false;
    });
});
