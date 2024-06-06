FROM openjdk:17 AS build

WORKDIR /app

RUN apt-get update && \
    apt-get install -y git &&\ 
    rm -rf /var/lib/apt/lists/*

RUN git clone https://github.com/luxferre-code/MyPortfolio.git /app

WORKDIR /app

RUN javac -d ./bin -d /out WEB-INF/src/**/*.java

FROM tomcat:latest

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/WEB-INF/views /usr/local/tomcat/webapps/ROOT/WEB-INF/views
COPY --from=build /app/css /usr/local/tomcat/webapps/ROOT/css
COPY --from=build /app/js /usr/local/tomcat/webapps/ROOT/js
COPY --from=build /app/contents /usr/local/tomcat/webapps/ROOT/contents
COPY --from=build /app/index.html /usr/local/tomcat/webapps/ROOT/index.html
COPY --from=build /app/install.sql /usr/local/tomcat/webapps/ROOT/install.sql
COPY --from=build /out /usr/local/tomcat/webapps/ROOT/WEB-INF/classes
COPY --from=build /app/META-INF /usr/local/tomcat/webapps/ROOT/META-INF
COPY --from=build /app/lib /usr/local/tomcat/lib

VOLUME [ "/app/contents/" ]

EXPOSE 8080