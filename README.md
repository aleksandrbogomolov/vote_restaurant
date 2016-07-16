# vote_restaurant

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e19b70a1a840492abd2ce1539a1deb54)](https://www.codacy.com/app/ksandr-ua/vote_restaurant?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=aleksandrbogomolov/vote_restaurant&amp;utm_campaign=Badge_Grade)

Solution in process

Goal:

Design and implement a JSON API using Hibernate/Spring/SpringMVC (or Spring-Boot).

The task is:

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we assume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.
