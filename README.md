# SeniorHotel - Full Stack Application

## Descrição
Este projeto é uma aplicação full stack para gerenciamento de reservas e hóspedes em um hotel. A aplicação é composta por um back-end desenvolvido em **Spring Boot** e um front-end desenvolvido em **Angular**. O banco de dados utilizado é o **PostgreSQL**.

## Pré-requisitos

### Ferramentas Necessárias
Antes de iniciar, certifique-se de que os seguintes softwares estão instalados na sua máquina:

- **PostgreSQL**: [Instalar PostgreSQL](https://www.postgresql.org/download/)
- **JDK 17** ou superior: [Instalar JDK](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- **Maven**: [Instalar Maven](https://maven.apache.org/install.html)
- **Node.js e NPM**: [Instalar Node.js](https://nodejs.org/)
- **Angular CLI**: Instale via NPM com o comando:
  ```bash
  npm install -g @angular/cli
Configurações do Banco de Dados
Instalar PostgreSQL:

Após a instalação, crie um banco de dados com o nome db_hotel (ou o nome de sua preferência).
Crie um usuário com permissões para esse banco de dados.
Configurar o arquivo application.properties:

Abra o arquivo src/main/resources/application.properties no diretório do back-end e configure as credenciais do PostgreSQL:
properties
Copy code
spring.datasource.url=jdbc:postgresql://localhost:5432/senior_hotel
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
Substitua **seu_usuario** e **sua_senha** pelas credenciais do PostgreSQL que você configurou.

### Iniciando o Back-End (Spring Boot)
Certifique-se de que o PostgreSQL está rodando.
Navegue até a pasta do projeto back-end (onde o arquivo pom.xml está localizado):
**cd /caminho/para/back-end**

Execute o projeto usando o Maven:
**mvn spring-boot:run**

O servidor Spring Boot estará rodando no endereço: http://localhost:8080.

### Iniciando o Front-End (Angular)
Navegue até a pasta do projeto front-end (onde o projeto Angular está localizado):
cd /caminho/para/front-end

Instale as dependências do projeto Angular:
**npm install**

Inicie o servidor de desenvolvimento Angular:
**npm start**

O front-end estará acessível em http://localhost:4200.
