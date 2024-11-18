
# WRM - Gestion des Files d’Attentes 🏥

## Table of Contents 📚
- 📄 [Aperçu du Projet](#aperçu-du-projet)
- 🏗️ [Architecture de l'Application](#architecture-de-lapplication)
- ⚙️ [Fonctionnalités](#fonctionnalités)
- 🚀 [Getting Started](#prise-en-main-getting-started)
- 🧪 [Tests](#tests)
- 📜 [Documentation de l'API](#documentation-de-lapi)

---

## Aperçu du Projet 📄
**WRM** est une API REST pour la gestion des salles d'attente, développée pour organiser et gérer les flux de visiteurs dans divers types d'établissements tels que des cabinets médicaux, des services publics ou des entreprises. Cette application permet de gérer dynamiquement les visiteurs, d'appliquer divers algorithmes d'ordonnancement et de générer des statistiques pour améliorer la gestion des salles d'attente.

Les utilisateurs peuvent configurer des horaires de service, spécifier des priorités de traitement pour les visiteurs et obtenir des indicateurs de performance pour optimiser le flux.

---

## Architecture de l'Application 🏗️
L'architecture de **WRM** suit une approche modulaire, permettant une gestion flexible et évolutive des visiteurs et des salles d’attente.

- **Contrôleurs** : Gèrent les requêtes HTTP, orchestrent les appels aux services et retournent les réponses aux clients.
- **Services** : Contiennent la logique métier, y compris les règles de gestion des algorithmes d'ordonnancement (FIFO, HPF, SJF).
- **Repositories** : Interfaces d’accès aux données, utilisant Spring Data JPA pour interagir avec la base de données MySQL.
- **DTOs et Mappers** : Objets de transfert de données et utilisation de MapStruct ou ModelMapper pour la conversion entre les entités et les DTOs.

---

## Fonctionnalités ⚙️

- **Gestion des Visiteurs** : Ajouter, mettre à jour et supprimer des visiteurs, et gérer leur état (en attente, en cours, terminé, annulé).
- **Algorithmes d'Ordonnancement** : Implémentation des algorithmes FIFO, HPF et SJF pour la gestion des priorités.
- **Configuration des Horaires et Capacité** : Définir les horaires d'ouverture et les capacités maximales des salles d'attente pour chaque jour de la semaine.
- **Statistiques et Performance** : Générer des rapports de performance incluant le taux de satisfaction, le temps moyen d'attente, et la rotation des visiteurs.

---

## Getting Started 🚀

### Prérequis
- **Java 17 ou supérieur** ☕
- **Maven** pour la gestion des dépendances (Maven 3.9+)
- **MySQL** pour la base de données (exécuté en conteneur Docker)
- **Postman** pour tester l'API

### Installation
1. Clonez le dépôt du projet :
   ```bash
   [git clone https://github.com/username/wrm.git](https://github.com/J-Maryam/WaitingRoom.git)
   cd wrm
   ```

2. Compilez le projet avec Maven :
   ```bash
   mvn clean install
   ```

### Configuration de la Base de Données
1. **Créez une base de données** (par exemple `wrm_db`) dans MySQL.

2. **Modifiez le fichier de configuration** dans `src/main/resources/application.yml` pour les informations de connexion :
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/wrm_db
       username: <username>
       password: <password>
     jpa:
       hibernate:
         ddl-auto: update
   ```

### Démarrage de l'Application
1. Démarrez l’application :
   ```bash
   mvn spring-boot:run
   ```
---

## Tests 🧪

### Tests Unitaires
Les tests unitaires sont réalisés en utilisant **JUnit** et **Mockito** pour tester les services, contrôleurs, et la logique métier.

Pour exécuter les tests unitaires :
```bash
mvn test
```

---

## Documentation de l'API 📖

L'API est documentée avec **Swagger**. Une fois l'application lancée, vous pouvez accéder à la documentation interactive via l'URL suivante :
```
http://localhost:8080/swagger-ui.html
```

---

## Contribution 🤝
Les contributions sont les bienvenues. Pour contribuer :
1. Forkez le dépôt.
2. Créez une branche pour votre fonctionnalité (`feature/ma-fonctionnalité`).
3. Créez une pull request avec une description détaillée de votre travail.

---

## Licence 📜
Ce projet est sous licence MIT. Consultez le fichier `LICENSE` pour plus d’informations.
