# Student Management System - Spring Boot

## 📋 Description
Application de gestion d'étudiants et d'universités avec API REST, déployée sur Render.

## 🚀 Déploiement sur Render

### Prérequis
- Compte GitHub
- Compte Render
- Base de données PostgreSQL sur Render

### Configuration Render

1. **Web Service**
   - Build Command: `mvn clean package -DskipTests`
   - Start Command: `java -jar target/demo-0.0.1-SNAPSHOT.jar`

2. **Base de données PostgreSQL**
   - Créer une instance PostgreSQL sur Render
   - Récupérer les informations de connexion

3. **Variables d'environnement sur Render:**