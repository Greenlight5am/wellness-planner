Wellness Planner API

Progetto TEST Wellness Planner API, un set di API RESTful per la gestione di attività e utenti, sviluppato in Java con Spring Boot.

Funzionalità Principali

Attività:
Creazione, modifica ed eliminazione di attività.
Visualizzazione di attività singole o lista completa.
Filtraggio per nome, giorno e disponibilità.
Caricamento di media associati alle attività.


Utenti:
Registrazione di nuovi utenti.
Iscrizione e cancellazione da attività.
Visualizzazione delle attività a cui l'utente è iscritto.


Tecnologie Utilizzate
Linguaggio: Java 21
Framework: Spring Boot 3.x (Web, Data JPA, Security)
Database: Postgresql (ambiente di sviluppo)
Autenticazione: JWT (JSON Web Tokens)

Build Tool: Maven

Prerequisiti

Java 21 o superiore
Maven

Clona il Repository:
git clone  https://github.com/Greenlight5am/wellness-planner.git
cd wellness-planner-api

Avvia l'Applicazione:
mvn spring-boot:run

Oppure esegui la classe WellnessPlannerApplication dal tuo IDE.

L'applicazione sarà disponibile su http://localhost:8080.

Utilizzo delle API

Autenticazione

L'applicazione utilizza JWT per l'autenticazione. Per accedere alle API protette, è necessario autenticarsi.

Registrazione:

Endpoint: POST /api/users/register
Descrizione: Registra un nuovo utente fornendo nome, cognome, email e password.
Parametri Richiesti:
nome: Nome dell'utente.
cognome: Cognome dell'utente.
email: Email dell'utente.
password: Password dell'utente.
Login:

Endpoint: POST /api/auth/authenticate
Descrizione: Effettua il login con email e password e ricevi un token JWT.
Parametri Richiesti:
email: Email dell'utente.
password: Password dell'utente.
Richieste Autenticate
Per effettuare richieste autenticate, includi l'header Authorization con il token JWT ricevuto dopo il login:


Authorization: Bearer <token>
Endpoints Principali
Attività
Creazione di un'Attività

Endpoint: POST /api/activities
Descrizione: Crea una nuova attività fornendo i dettagli necessari.
Autenticazione: Richiesta
Parametri Richiesti:
nome: Nome dell'attività.
luogo: Luogo dove si svolge l'attività.
dataOraInizio: Data e ora di inizio dell'attività.
dataOraFine: Data e ora di fine dell'attività.
postiDisponibili: Numero totale di posti disponibili.
categorie: Lista di categorie associate all'attività.
mediaId: ID del media associato (se presente).


Modifica di un'Attività

Endpoint: PUT /api/activities/{id}
Descrizione: Modifica un'attività esistente identificata da {id}.
Autenticazione: Richiesta
Parametri Richiesti: Gli stessi della creazione, con i valori aggiornati.

Eliminazione di un'Attività

Endpoint: DELETE /api/activities/{id}
Descrizione: Elimina un'attività esistente identificata da {id}.
Autenticazione: Richiesta

Recupero di un'Attività per ID

Endpoint: GET /api/activities/{id}
Descrizione: Ottiene i dettagli di un'attività specifica.
Autenticazione: Richiesta

Recupero di Tutte le Attività

Endpoint: GET /api/activities
Descrizione: Ottiene la lista di tutte le attività disponibili.
Autenticazione: Richiesta

Ricerca di Attività con Filtri

Endpoint: GET /api/activities/search
Descrizione: Filtra le attività in base ai parametri opzionali.
Parametri Query (opzionali):
nome: Filtra per nome dell'attività.
giorno: Filtra per giorno specifico (formato YYYY-MM-DD).
disponibilita: Filtra per disponibilità (true o false).
Autenticazione: Richiesta

Caricamento di un Media per un'Attività

Endpoint: POST /api/activities/{id}/media
Descrizione: Carica un file media e lo associa all'attività specificata.
Autenticazione: Richiesta

Note: Il file deve essere inviato come multipart/form-data nel campo file.

Utenti

Registrazione di un Utente

Endpoint: POST /api/users/register
Descrizione: Registra un nuovo utente.
Autenticazione: Non richiesta

Iscrizione a un'Attività

Endpoint: POST /api/users/subscribe/{activityId}
Descrizione: Iscrive l'utente autenticato all'attività con ID specificato.
Autenticazione: Richiesta

Cancellazione da un'Attività

Endpoint: POST /api/users/unsubscribe/{activityId}
Descrizione: Cancella l'iscrizione dell'utente autenticato dall'attività specificata.
Autenticazione: Richiesta

Recupero delle Attività a cui l'Utente è Iscritto

Endpoint: GET /api/users/activities
Descrizione: Ottiene la lista delle attività a cui l'utente autenticato è iscritto.
Autenticazione: Richiesta
