# Square Users API

API REST Spring Boot dédiée à la gestion des utilisateurs du projet **Square Games**.

Cette application constitue un service séparé de `square-games-api`. Elle permet de créer, consulter, supprimer et valider des utilisateurs. Le service de jeux l'utilise notamment pour vérifier qu'un identifiant reçu dans l'en-tête `X-UserId` correspond à un utilisateur existant.

## Fonctionnalités

- Création d'un utilisateur avec génération automatique d'un UUID
- Consultation d'un utilisateur par son identifiant
- Suppression d'un utilisateur
- Vérification de l'existence d'un utilisateur
- Persistance avec Spring Data JPA et H2
- Documentation interactive avec OpenAPI / Swagger UI
- Architecture multicouche avec injection par constructeur

## Stack technique

- Java 21
- Spring Boot 4.2.0-SNAPSHOT
- Spring Web MVC
- Spring Data JPA
- H2 Database
- springdoc-openapi / Swagger UI
- Maven

## Architecture

```text
HTTP Request
     ↓
UserController
     ↓
UserService
     ↓
UserDao
     ↓
JpaUserDao
     ↓
UserRepository (JpaRepository)
     ↓
H2 Database
```

Le projet applique une séparation des responsabilités entre les couches Controller, Service et Persistence.

## Prérequis

- JDK 21
- Git
- Une connexion Internet lors du premier build Maven, notamment pour récupérer les dépendances et les snapshots Spring Boot

Maven n'a pas besoin d'être installé globalement : le projet contient le Maven Wrapper.

## Démarrage de l'application

Cloner le repository :

```bash
git clone https://github.com/gheisariyeh/square-users-api.git
cd square-users-api
```

Sous Windows :

```powershell
.\mvnw.cmd spring-boot:run
```

Sous Linux / macOS :

```bash
./mvnw spring-boot:run
```

L'application démarre sur :

```text
http://localhost:8081
```

Le port est configuré dans `src/main/resources/application.properties` :

```properties
server.port=8081
```

## Base de données

L'application utilise actuellement une base H2 persistée dans un fichier local :

```properties
spring.datasource.url=jdbc:h2:file:./data/users_db
spring.datasource.username=sa
spring.datasource.password=
```

Les données sont donc conservées entre deux redémarrages de l'application.

Le dossier `data/` est local et ne doit pas être versionné dans Git.

### Console H2

Lorsque l'application est démarrée :

```text
http://localhost:8081/h2-console
```

Paramètres de connexion :

```text
JDBC URL : jdbc:h2:file:./data/users_db
User     : sa
Password : laisser vide
```

## Endpoints REST

| Méthode | Endpoint | Description |
|---|---|---|
| `POST` | `/users` | Crée un nouvel utilisateur et génère son UUID |
| `GET` | `/users/{id}` | Récupère un utilisateur par son identifiant |
| `DELETE` | `/users/{id}` | Supprime un utilisateur |
| `GET` | `/users/{id}/valid` | Retourne `true` si l'utilisateur existe, sinon `false` |

### Créer un utilisateur

```http
POST /users
```

Exemple de réponse :

```json
{
  "id": "e522d61b-8f0c-4556-976a-a3837b9ccb74"
}
```

### Récupérer un utilisateur

```http
GET /users/e522d61b-8f0c-4556-976a-a3837b9ccb74
```

### Vérifier qu'un utilisateur existe

```http
GET /users/e522d61b-8f0c-4556-976a-a3837b9ccb74/valid
```

Réponse si l'utilisateur existe :

```json
true
```

Réponse s'il n'existe pas :

```json
false
```

### Supprimer un utilisateur

```http
DELETE /users/e522d61b-8f0c-4556-976a-a3837b9ccb74
```

## Swagger / OpenAPI

La documentation interactive est disponible lorsque l'application est lancée :

```text
http://localhost:8081/swagger-ui.html
```

La spécification OpenAPI au format JSON est disponible ici :

```text
http://localhost:8081/v3/api-docs
```

Swagger UI permet de consulter les endpoints et d'envoyer des requêtes directement depuis le navigateur.

## Communication avec Square Games API

`square-games-api` utilise ce service comme client HTTP afin de valider les identifiants transmis dans l'en-tête `X-UserId`.

```text
Client / Postman
       ↓
square-games-api :8080
       ↓  HTTP GET /users/{id}/valid
square-users-api :8081
       ↓
H2 Database
```

Les deux applications doivent donc être démarrées simultanément pour tester le scénario multi-utilisateurs complet.

## Sécurité

Dans l'itération actuelle, l'identité du joueur est transmise par un simple identifiant `X-UserId`. Ce mécanisme est volontairement pédagogique et ne constitue pas une authentification sécurisée.

La sécurisation par JWT est prévue dans l'itération suivante.

## Tests manuels

Les endpoints peuvent être testés avec :

- Swagger UI
- Postman
- Bruno

Un scénario d'intégration typique consiste à créer deux utilisateurs, stocker leurs identifiants, puis utiliser ces identifiants dans `square-games-api` pour créer et jouer une partie multi-utilisateurs.
