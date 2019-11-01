FROM java:8
VOLUME /tmp
# Copia o .jar para dentro do container
ADD ./target/casafacilimoveis-0.0.1-SNAPSHOT.jar app.jar
# Expõe a porta 8080
EXPOSE 8080
# Executa
RUN bash -c 'touch /app.jar'
# Passa os comandos para o docker logo quando subir
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]