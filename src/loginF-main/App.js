import React, { useState } from "react";
import StudentApp from '../studentF-main/App';
import CourseApp from '../courseF-main/App';
import ChatbotApp from '../chatbotF-main/App';
import "./App.css";

function App() {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: ""
  });
  const [isAdmin, setIsAdmin] = useState(false);
  const [activeTab, setActiveTab] = useState("dashboard");
  const [selectedDate, setSelectedDate] = useState(new Date());

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.type === "text" ? 
        (e.target.placeholder === "First Name" ? "firstName" : "lastName") : 
        (e.target.type === "email" ? "email" : "password")]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    
    const adminCredentials = {
      email: "Bensaid@gmail.com",
      password: "Ys2025",
      firstName: "Youssef",
      lastName: "Bensaid"
    };

    if (
      formData.email === adminCredentials.email &&
      formData.password === adminCredentials.password &&
      formData.firstName === adminCredentials.firstName &&
      formData.lastName === adminCredentials.lastName
    ) {
      setIsAdmin(true);
    } else {
      alert("Informations de connexion incorrectes");
    }
  };

  // Données simulées
  const usersData = [
    { id: 1, name: "Jean Dupont", email: "jean@email.com", status: "active", joinDate: "2024-01-15" },
    { id: 2, name: "Marie Martin", email: "marie@email.com", status: "active", joinDate: "2024-02-20" },
    { id: 3, name: "Pierre Lambert", email: "pierre@email.com", status: "inactive", joinDate: "2024-01-08" },
    { id: 4, name: "Sophie Bernard", email: "sophie@email.com", status: "active", joinDate: "2024-03-05" }
  ];

  const studentsData = [
    { id: 1, name: "Alice Dubois", email: "alice@email.com", class: "Master 1", status: "active", enrollmentDate: "2024-01-10" },
    { id: 2, name: "Thomas Moreau", email: "thomas@email.com", class: "Licence 3", status: "active", enrollmentDate: "2024-02-15" },
    { id: 3, name: "Emma Laurent", email: "emma@email.com", class: "Master 2", status: "active", enrollmentDate: "2024-01-20" },
    { id: 4, name: "Lucas Petit", email: "lucas@email.com", class: "Licence 2", status: "inactive", enrollmentDate: "2024-03-01" }
  ];

  const coursesData = [
    { id: 1, title: "Développement Web", instructor: "Dr. Martin", schedule: "Lundi 09:00-11:00", students: 25, capacity: 30, status: "active" },
    { id: 2, title: "Base de Données", instructor: "Prof. Leroy", schedule: "Mardi 14:00-16:00", students: 20, capacity: 25, status: "active" },
    { id: 3, title: "Intelligence Artificielle", instructor: "Dr. Sanchez", schedule: "Jeudi 10:00-12:00", students: 18, capacity: 20, status: "active" },
    { id: 4, title: "Réseaux Informatiques", instructor: "Prof. Dubois", schedule: "Vendredi 16:00-18:00", students: 15, capacity: 20, status: "inactive" }
  ];

  const calendarEvents = [
    { id: 1, title: "Examen Développement Web", date: "2024-03-15", time: "09:00-12:00", type: "exam", course: "Développement Web" },
    { id: 2, title: "Réunion des enseignants", date: "2024-03-18", time: "14:00-16:00", type: "meeting", course: "" },
    { id: 3, title: "TP Base de Données", date: "2024-03-20", time: "14:00-16:00", type: "lab", course: "Base de Données" },
    { id: 4, title: "Conférence IA", date: "2024-03-22", time: "10:00-12:00", type: "conference", course: "Intelligence Artificielle" },
    { id: 5, title: "Rendu de projet", date: "2024-03-25", time: "23:59", type: "deadline", course: "Tous les cours" }
  ];

  const recentActivities = [
    { id: 1, type: "login", user: "Jean Dupont", time: "Il y a 5 min", description: "Connexion utilisateur" },
    { id: 2, type: "update", user: "Marie Martin", time: "Il y a 15 min", description: "Profil mis à jour" },
    { id: 3, type: "payment", user: "Pierre Lambert", time: "Il y a 1 heure", description: "Paiement effectué" },
    { id: 4, type: "support", user: "Sophie Bernard", time: "Il y a 2 heures", description: "Ticket support créé" }
  ];

  // Fonctions pour le calendrier
  const getDaysInMonth = (date) => {
    return new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();
  };

  const getFirstDayOfMonth = (date) => {
    return new Date(date.getFullYear(), date.getMonth(), 1).getDay();
  };

  const navigateMonth = (direction) => {
    setSelectedDate(prev => new Date(prev.getFullYear(), prev.getMonth() + direction, 1));
  };

  const getEventsForDay = (day) => {
    const dateStr = `${selectedDate.getFullYear()}-${String(selectedDate.getMonth() + 1).padStart(2, '0')}-${String(day).padStart(2, '0')}`;
    return calendarEvents.filter(event => event.date === dateStr);
  };

  const getEventTypeIcon = (type) => {
    switch(type) {
      case 'exam': return '📝';
      case 'meeting': return '👥';
      case 'lab': return '🔬';
      case 'conference': return '🎤';
      case 'deadline': return '⏰';
      default: return '📅';
    }
  };

  // Fonctions de navigation
  const handleBackToDashboard = () => {
    setActiveTab('dashboard');
  };

  const handleLogout = () => {
    setIsAdmin(false);
    setFormData({
      firstName: "",
      lastName: "",
      email: "",
      password: ""
    });
  };

  // Si l'utilisateur est admin, afficher la page admin
  if (isAdmin) {
    // SI ON EST DANS L'APP ÉTUDIANTS
    if (activeTab === 'students') {
      return (
        <div className="full-app-container">
          <div className="app-header">
            <button className="back-btn" onClick={handleBackToDashboard}>
              ← Retour au Dashboard
            </button>
            <h1>Application de Gestion des Étudiants</h1>
            <button className="logout-btn" onClick={handleLogout}>
              Déconnexion
            </button>
          </div>
          <StudentApp />
        </div>
      );
    }

    // SI ON EST DANS L'APP COURS
    if (activeTab === 'courses') {
      return (
        <div className="full-app-container">
          <div className="app-header">
            <button className="back-btn" onClick={handleBackToDashboard}>
              ← Retour au Dashboard
            </button>
            <h1>Application de Gestion des Cours</h1>
            <button className="logout-btn" onClick={handleLogout}>
              Déconnexion
            </button>
          </div>
          <CourseApp />
        </div>
      );
    }

    // SI ON EST DANS L'APP CHATBOT
    if (activeTab === 'chatbot') {
      return (
        <div className="full-app-container">
          <ChatbotApp 
            onBackToDashboard={handleBackToDashboard}
            onLogout={handleLogout}
          />
        </div>
      );
    }

    // SINON, AFFICHER LE DASHBOARD ADMIN NORMAL
    return (
      <div className="admin-container">
        <div className="admin-header">
          <h1>Panel Administrateur • Anywhere App</h1>
          <div>
            <button 
              className="logout-btn"
              onClick={handleLogout}
            >
              Déconnexion
            </button>
          </div>
        </div>
        
        {/* Navigation des onglets */}
        <div className="admin-card">
          <div className="tabs-navigation">
            {['dashboard', 'students', 'courses', 'chatbot', 'calendar', 'reports', 'settings'].map(tab => (
              <button
                key={tab}
                className={`tab-btn ${activeTab === tab ? 'active' : ''}`}
                onClick={() => setActiveTab(tab)}
              >
                {tab === 'dashboard' && '📊 Tableau de bord'}
                {tab === 'students' && '👨‍🎓 Étudiants'}
                {tab === 'courses' && '📚 Cours'}
                {tab === 'chatbot' && '🤖 Chatbot'}
                {tab === 'calendar' && '📅 Calendrier'}
                {tab === 'reports' && '📈 Rapports'}
                {tab === 'settings' && '⚙️ Paramètres'}
              </button>
            ))}
          </div>

          {/* Contenu des onglets */}
          {activeTab === 'dashboard' && (
            <div className="admin-content">
              <div className="admin-card">
                <h2>Tableau de Bord</h2>
                <div className="stats">
                  <div className="stat-item">
                    <h3>{usersData.length}</h3>
                    <p>Utilisateurs total</p>
                  </div>
                  <div className="stat-item">
                    <h3>{studentsData.length}</h3>
                    <p>Étudiants inscrits</p>
                  </div>
                  <div className="stat-item">
                    <h3>{coursesData.length}</h3>
                    <p>Cours actifs</p>
                  </div>
                  <div className="stat-item">
                    <h3>89%</h3>
                    <p>Taux d'engagement</p>
                  </div>
                </div>
                
                <div className="chart-container">
                  Graphique des performances - Intégration future
                </div>
              </div>

              <div className="admin-card">
                <h2>Activité Récente</h2>
                <div className="recent-activity">
                  {recentActivities.map(activity => (
                    <div key={activity.id} className="activity-item">
                      <div className="activity-icon">
                        {activity.type === 'login' && '🔐'}
                        {activity.type === 'update' && '✏️'}
                        {activity.type === 'payment' && '💳'}
                        {activity.type === 'support' && '🎫'}
                      </div>
                      <div className="activity-content">
                        <h4>{activity.user}</h4>
                        <p>{activity.description} • {activity.time}</p>
                      </div>
                    </div>
                  ))}
                </div>
              </div>
            </div>
          )}

          {activeTab === 'students' && (
            <div className="admin-card">
              <div className="section-header">
                <h2>Gestion des Étudiants</h2>
                <button className="add-btn">+ Ajouter un étudiant</button>
              </div>
              <table className="data-table">
                <thead>
                  <tr>
                    <th>Nom</th>
                    <th>Email</th>
                    <th>Classe</th>
                    <th>Date d'inscription</th>
                    <th>Statut</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {studentsData.map(student => (
                    <tr key={student.id}>
                      <td>{student.name}</td>
                      <td>{student.email}</td>
                      <td>{student.class}</td>
                      <td>{student.enrollmentDate}</td>
                      <td>
                        <span className={student.status === 'active' ? 'status-active' : 'status-inactive'}>
                          {student.status === 'active' ? 'Actif' : 'Inactif'}
                        </span>
                      </td>
                      <td>
                        <div className="action-buttons-small">
                          <button className="edit-btn">Modifier</button>
                          <button className="delete-btn">Supprimer</button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}

          {activeTab === 'courses' && (
            <div className="admin-card">
              <div className="section-header">
                <h2>Gestion des Cours</h2>
                <button className="add-btn">+ Créer un cours</button>
              </div>
              <div className="courses-grid">
                {coursesData.map(course => (
                  <div key={course.id} className="course-card">
                    <div className="course-header">
                      <h3>{course.title}</h3>
                      <span className={`course-status ${course.status}`}>
                        {course.status === 'active' ? 'Actif' : 'Inactif'}
                      </span>
                    </div>
                    <div className="course-info">
                      <p><strong>Enseignant:</strong> {course.instructor}</p>
                      <p><strong>Horaire:</strong> {course.schedule}</p>
                      <p><strong>Étudiants:</strong> {course.students}/{course.capacity}</p>
                    </div>
                    <div className="course-progress">
                      <div className="progress-bar">
                        <div 
                          className="progress-fill" 
                          style={{ width: `${(course.students / course.capacity) * 100}%` }}
                        ></div>
                      </div>
                      <span>{Math.round((course.students / course.capacity) * 100)}%</span>
                    </div>
                    <div className="course-actions">
                      <button className="action-btn-small edit">Modifier</button>
                      <button className="action-btn-small view">Voir détails</button>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          )}

          {activeTab === 'chatbot' && (
            <div className="admin-card">
              <div className="section-header">
                <h2>Assistant Virtuel Chatbot</h2>
                <p className="chatbot-description">
                  Interface de gestion et de configuration de l'assistant virtuel pour répondre aux questions des étudiants.
                </p>
              </div>
              <div className="chatbot-interface">
                <ChatbotApp 
                  onBackToDashboard={handleBackToDashboard}
                  onLogout={handleLogout}
                />
              </div>
            </div>
          )}

          {activeTab === 'calendar' && (
            <div className="admin-card">
              <div className="calendar-header">
                <h2>Calendrier Académique</h2>
                <div className="calendar-controls">
                  <button onClick={() => navigateMonth(-1)}>‹ Mois précédent</button>
                  <span className="current-month">
                    {selectedDate.toLocaleDateString('fr-FR', { month: 'long', year: 'numeric' })}
                  </span>
                  <button onClick={() => navigateMonth(1)}>Mois suivant ›</button>
                </div>
              </div>
              
              <div className="calendar-grid">
                <div className="calendar-weekdays">
                  {['Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam'].map(day => (
                    <div key={day} className="weekday">{day}</div>
                  ))}
                </div>
                
                <div className="calendar-days">
                  {Array.from({ length: getFirstDayOfMonth(selectedDate) }, (_, i) => (
                    <div key={`empty-${i}`} className="calendar-day empty"></div>
                  ))}
                  
                  {Array.from({ length: getDaysInMonth(selectedDate) }, (_, i) => {
                    const day = i + 1;
                    const dayEvents = getEventsForDay(day);
                    return (
                      <div key={day} className="calendar-day">
                        <span className="day-number">{day}</span>
                        <div className="day-events">
                          {dayEvents.slice(0, 2).map(event => (
                            <div key={event.id} className={`calendar-event ${event.type}`}>
                              <span className="event-icon">{getEventTypeIcon(event.type)}</span>
                              <span className="event-title">{event.title}</span>
                            </div>
                          ))}
                          {dayEvents.length > 2 && (
                            <div className="more-events">+{dayEvents.length - 2} plus</div>
                          )}
                        </div>
                      </div>
                    );
                  })}
                </div>
              </div>
              
              <div className="calendar-legend">
                <div className="legend-item">
                  <span className="legend-color exam"></span>
                  <span>Examen</span>
                </div>
                <div className="legend-item">
                  <span className="legend-color meeting"></span>
                  <span>Réunion</span>
                </div>
                <div className="legend-item">
                  <span className="legend-color lab"></span>
                  <span>TP/Lab</span>
                </div>
                <div className="legend-item">
                  <span className="legend-color conference"></span>
                  <span>Conférence</span>
                </div>
                <div className="legend-item">
                  <span className="legend-color deadline"></span>
                  <span>Deadline</span>
                </div>
              </div>
            </div>
          )}

          {activeTab === 'reports' && (
            <div className="admin-card">
              <h2>Rapports et Analytics</h2>
              <div className="system-metrics">
                <div className="metric">
                  <div className="metric-value">98.5%</div>
                  <div className="metric-label">Uptime Serveur</div>
                </div>
                <div className="metric">
                  <div className="metric-value">2.3s</div>
                  <div className="metric-label">Temps de réponse</div>
                </div>
                <div className="metric">
                  <div className="metric-value">45GB</div>
                  <div className="metric-label">Stockage utilisé</div>
                </div>
                <div className="metric">
                  <div className="metric-value">1.2K</div>
                  <div className="metric-label">Requêtes/heure</div>
                </div>
              </div>
              
              <div className="admin-hint">
                <p>📊 Rapports détaillés disponibles en export CSV/PDF</p>
                <p>📈 Analytics en temps réel avec métriques avancées</p>
              </div>
            </div>
          )}

          {activeTab === 'settings' && (
            <div className="admin-card">
              <h2>Paramètres Système</h2>
              <div className="action-buttons">
                <button className="action-btn">⚙️ Paramètres Généraux</button>
                <button className="action-btn">🔐 Sécurité et Accès</button>
                <button className="action-btn">📧 Notifications</button>
                <button className="action-btn">💾 Sauvegarde des données</button>
                <button className="action-btn">🔧 Maintenance</button>
              </div>
              
              <div className="admin-hint">
                <p>⚠️ Les modifications des paramètres système nécessitent des privilèges élevés</p>
                <p>💡 Sauvegardez toujours vos configurations avant de modifier</p>
              </div>
            </div>
          )}
        </div>
      </div>
    );
  }

  // Page de login normale
  return (
    <div className="container">
      <div className="left-section">
        <h3 className="logo">• Anywhere app.</h3>

        <div className="form-box">
          <p className="subtitle">START FOR FREE</p>
          <h1>
            Create new <br /> account<span className="dot">.</span>
          </h1>
          
          <form onSubmit={handleSubmit}>
            <input 
              type="text" 
              placeholder="First Name" 
              value={formData.firstName}
              onChange={handleChange}
            />
            <input 
              type="text" 
              placeholder="Last Name" 
              value={formData.lastName}
              onChange={handleChange}
            />
            <input 
              type="email" 
              placeholder="Email" 
              value={formData.email}
              onChange={handleChange}
            />
            <input 
              type="password" 
              placeholder="Password" 
              value={formData.password}
              onChange={handleChange}
            />
            <div className="btn-group">
              <button type="submit" className="btn-primary">
                Login
              </button>
            </div>
          </form>
        </div>
      </div>

      <div className="right-section"></div>
    </div>
  );
}

export default App;