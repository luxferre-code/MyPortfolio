# Étape 1 : Récupérer le dépôt Git et préparer l'environnement
FROM debian:latest AS git

# Installer git et certificats CA
RUN apt-get update && \
    apt-get install -y --no-install-recommends git ca-certificates && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Désactiver la vérification SSL (Non recommandé pour la production)
RUN git config --global http.sslVerify false

# Définir le répertoire de travail
WORKDIR /app

# Cloner le dépôt Git
RUN git clone https://github.com/luxferre-code/MyPortfolio.git .

# Étape 2 : Compiler le code Java
FROM debian:latest AS build

# Installer JDK et findutils
RUN apt-get update && \
    apt-get install -y --no-install-recommends openjdk-17-jdk findutils && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Définir le répertoire de travail
WORKDIR /app

# Copier le code source depuis l'étape git
COPY --from=git /app /app

# Compiler le code Java avec encodage UTF-8
RUN javac -encoding UTF-8 -cp "lib/*" -d /out $(find WEB-INF/src -name "*.java")

# Étape 3 : Préparer l'image Tomcat
FROM tomcat:latest

# Supprimer les applications par défaut de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copier les fichiers statiques et le code compilé dans Tomcat
COPY --from=build /app/WEB-INF/views /usr/local/tomcat/webapps/ROOT/WEB-INF/views
COPY --from=build /app/css /usr/local/tomcat/webapps/ROOT/css
COPY --from=build /app/js /usr/local/tomcat/webapps/ROOT/js
COPY --from=build /app/contents /usr/local/tomcat/webapps/ROOT/contents
COPY --from=build /app/index.html /usr/local/tomcat/webapps/ROOT/index.html
COPY --from=build /app/install.sql /usr/local/tomcat/webapps/ROOT/install.sql
COPY --from=build /out /usr/local/tomcat/webapps/ROOT/WEB-INF/classes
COPY --from=build /app/META-INF /usr/local/tomcat/webapps/ROOT/META-INF
COPY --from=build /app/lib /usr/local/tomcat/webapps/ROOT/WEB-INF/lib

VOLUME [ "/app/contents/" ]

# Exposer le port 8080
EXPOSE 8080

# Démarrer Tomcat
CMD ["catalina.sh", "run"]
