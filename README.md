<h1>🏨 Hotel Reservation System</h1>

<h3>📄 Show <strong>Print All</strong></h3>
<img width="400px" src="https://github.com/user-attachments/assets/30e64ca6-efc5-4022-ac79-3bd0e02b9808" alt="Print All"/>

<h3>👤 Show <strong>Print All Users</strong></h3>
<img width="400px" src="https://github.com/user-attachments/assets/f232595e-bde8-420f-b4ba-ebb1a10d294f" alt="Print All Users"/>

---

<h3>🧠 Design Questions (Bonus)</h3>

<p><strong>Q1 :</strong> "Un seul service pour tout, c'est bien ?"</p>

<div>
  <strong>R1 :</strong> Non, c’est une mauvaise pratique. Un gros service qui fait tout devient vite ingérable.
  La bonne méthode consiste à créer des services séparés, chacun avec une responsabilité claire.
</div>

<p>Par exemple :</p>
<ul>
  <li><strong>RoomService</strong> pour les chambres</li>
  <li><strong>BookingService</strong> pour les réservations</li>
  <li><strong>UserService</strong> pour les utilisateurs</li>
</ul>

<div>
  Cette approche facilite les tests, permet de modifier un service sans impacter les autres, et permet à plusieurs équipes de travailler en parallèle.
  Si je dois changer la logique des réservations, je n’ai besoin de modifier que <strong>BookingService</strong>, sans risquer de provoquer des bugs dans la gestion des chambres.
</div>

---

<p><strong>Q2 :</strong> "Nous avons choisi d’avoir une fonction setRoom(..) qui ne doit pas impacter les réservations précédentes. Quelle autre solution existe ?"</p>

<div>
  <strong>R2 :</strong> Non, car cela créerait des incohérences dans les factures, et ce serait dangereux pour la traçabilité et la confiance client.
</div>

<p>Il existe plusieurs solutions possibles :</p>

<p><strong>1 – Appliquer les modifications uniquement aux réservations futures</strong></p>

<p>Idéal si l’on veut rester simple tout en évitant les conflits :</p>
<ul>
  <li><code>setRoom(..)</code> modifie bien la chambre, mais uniquement pour les réservations futures.</li>
  <li>Les réservations passées conservent une copie locale des informations (prix, équipements, etc.).</li>
</ul>

<p><em>Exemple :</em></p>
<p>La chambre passe à 1500 MAD, mais une réservation faite hier conserve les données d’hier.</p>

<p><strong>2 – Cloner la chambre pour créer une nouvelle version</strong></p>

<p>Idéal pour gérer des évolutions importantes (rénovations, changement de prix, etc.) :</p>
<ul>
  <li>À chaque modification majeure, on crée une nouvelle version de la chambre.</li>
  <li>Les anciennes réservations pointent vers l’ancienne version ; les futures vers la nouvelle.</li>
</ul>

<p><em>Exemple :</em></p>
<p>La chambre #101 devient #101_v2 avec un nouveau prix, un balcon, etc.</p>
