# Theater-Seat-Reservation-System

<b>Requirement:</b> http://www.cs.ubbcluj.ro/~vladi/Teaching/Didactic/ISS%202018-2019/info%20romana/lab/iss_cerinte_lab_info_romana.pdf

<i>O institutie teatrala pune la dispozitia spectatorilor un sistem pentru rezervarea locurilor la
spectacole. În fiecare zi, institutia are o singura reprezentatie, la care spectatorii pot rezerva locuri
începand de dimineata. Pentru un loc în sala, sunt memorate urmatoarele informatii: pozitie (rândul
x, loja y etc.), numar si pret. Terminalele puse la dispozitia spectatorilor afiseaza întreaga
configuratie a salii, precizând pentru fiecare loc pozitia, numarul, pretul si starea (liber sau
rezervat). Folosind un astfel de terminal, spectatorul îsi poate introduce datele personale, poate
selecta unul sau mai multe locuri si poate declansa un buton pentru rezervarea lor. După fiecare
rezervare, toate terminalele vor afisa situația actualizata referitor la ocuparea sălii.</i>

<h2>Description</h2>
This project is compound of distinct two parts: server side and client side.
The server side is build with Spring Boot. I also used:
  <ul>
  <li>Lombok: a Java library which facilitates many tedious tasks and reduces Java source code verbosity
  </li>
  <li>Hibernate: an object-relational mapping tool 
  </li>
  <li>SSE (Server Side Event): a server push technology enabling a browser to receive automatic updates from a server via HTTP connection
  </li>
  </ul>
The client side is build with Anguar. I also used Bootstrap to design the components.

<h2>Usage</h2>
As the name suggests, Theater-Seat-Reservation-System is an application which helps clients to reserve theater tickets.
When a client visits the website, the system will show him a page this:

![Screenshot](https://github.com/teofanaenachioiu/Theater-Seat-Reservation-System/blob/master/screenshots/main.PNG)

An unauthenticated client can also see all the theater shows:

![Screenshot](https://github.com/teofanaenachioiu/Theater-Seat-Reservation-System/blob/master/screenshots/shows.PNG)

To reserve a seat, a client have to log in to the system:
![Screenshot](https://github.com/teofanaenachioiu/Theater-Seat-Reservation-System/blob/master/screenshots/login.PNG)

If the client doesn't have an account, he can create one using the following form:
![Screenshot](https://github.com/teofanaenachioiu/Theater-Seat-Reservation-System/blob/master/screenshots/signup.PNG)

Once entered in the system, the client can see all his tickets:
![Screenshot](https://github.com/teofanaenachioiu/Theater-Seat-Reservation-System/blob/master/screenshots/tickets.PNG)

To make a reservation, the client have to click on the seats he wants and then click on the "Reserve" button. All the exceptional cases have been taken into account!
![Screenshot](https://github.com/teofanaenachioiu/Theater-Seat-Reservation-System/blob/master/screenshots/reserve.PNG)

The list of theater performances is managed by one "manager". His tasks are:
<br>
adding shows
![Screenshot](https://github.com/teofanaenachioiu/Theater-Seat-Reservation-System/blob/master/screenshots/add.PNG)
<br>
deleting shows
![Screenshot](https://github.com/teofanaenachioiu/Theater-Seat-Reservation-System/blob/master/screenshots/delete.PNG)
<br>
editing shows
![Screenshot](https://github.com/teofanaenachioiu/Theater-Seat-Reservation-System/blob/master/screenshots/edit.PNG)
<br>
adding today show
![Screenshot](https://github.com/teofanaenachioiu/Theater-Seat-Reservation-System/blob/master/screenshots/today.PNG)
![Screenshot](https://github.com/teofanaenachioiu/Theater-Seat-Reservation-System/blob/master/screenshots/addCrrShow.PNG)
![Screenshot](https://github.com/teofanaenachioiu/Theater-Seat-Reservation-System/blob/master/screenshots/timer.PNG)
