#!/bin/bash
echo "Adding movies:"
printf "\n"
curl -X POST -H "Content-Type:application/json" -d '{  "title" : "Mortal Kombat 2" }' http://localhost:8080/movies
curl -X POST -H "Content-Type:application/json" -d '{  "title" : "Zombie" }' http://localhost:8080/movies
curl -X POST -H "Content-Type:application/json" -d '{  "title" : "Sin City" }' http://localhost:8080/movies

printf "\nAdding Rooms:"
printf "\n"
curl -X POST -H "Content-Type:application/json" -d '{  "rowsAmount" : 3, "seatsPerRow" : 6 }' http://localhost:8080/rooms
curl -X POST -H "Content-Type:application/json" -d '{  "rowsAmount" : 3, "seatsPerRow" : 6 }' http://localhost:8080/rooms
curl -X POST -H "Content-Type:application/json" -d '{  "rowsAmount" : 3, "seatsPerRow" : 6 }' http://localhost:8080/rooms

printf "\nAdding Screenings:"
printf "\n"
curl -X POST -H "Content-Type:application/json" -d '{ "movieId": 1, "roomId" : 4, "date": "26-08-2023 14:30:00"}' http://localhost:8080/screenings
curl -X POST -H "Content-Type:application/json" -d '{ "movieId": 2, "roomId" : 4, "date": "26-08-2023 15:30:00"}' http://localhost:8080/screenings
curl -X POST -H "Content-Type:application/json" -d '{ "movieId": 2, "roomId" : 5, "date": "26-08-2023 14:10:00"}' http://localhost:8080/screenings
curl -X POST -H "Content-Type:application/json" -d '{ "movieId": 2, "roomId" : 5, "date": "26-08-2023 11:06:00"}' http://localhost:8080/screenings
curl -X POST -H "Content-Type:application/json" -d '{ "movieId": 2, "roomId" : 6, "date": "26-08-2023 14:30:00"}' http://localhost:8080/screenings
curl -X POST -H "Content-Type:application/json" -d '{ "movieId": 3, "roomId" : 6, "date": "26-08-2023 22:50:00"}' http://localhost:8080/screenings

printf "\nAdding Reservation:"
printf "\n"
curl -X POST -H "Content-Type:application/json" -d '{  "person": {"name": "Michał", "surname": "Ruszkowski-Test"},"screeningId" : 7,
    "tickets": [{"rowNum": 1, "seatNum": 2, "ticketType": "ADULT"}, {"rowNum": 2, "seatNum": 3, "ticketType": "STUDENT"}] }' http://localhost:8080/reservations

printf "\nConfirming Reservation:"
printf "\n"
curl -X POST -H "Content-Type:application/json" http://localhost:8080/reservations/13/confirm




