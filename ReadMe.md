# MyNotes (Capstone Project)


## Description
MyNotes  is an application that allows users to keep important records or create tasks.
The record created can be modified, tasks can also be checked as done.
MyNotes helps users to keep details of things, events or tasks.


## Intended User
The intended user is everybody, that needs to be able to pen down his/her thought process at any point in time.
Or needs to schedule specific tasks, use sub-tasks to measure task progress\completion level.


## Features
- Create, edit and delete notes
- Create, edit and delete tasks
- Appropriate ordering of notes and tasks
- App has two flavors; free and paid; free contains ads while paid version is free of ads.
- cleApp has a widget


## Technical Steps
- App is written solely in Java Programming Language
- App uses stable versions of gradle and libraries, as shown below
- App keeps all strings in a strings.xml file
- App uses Room to manage data, Livedata and ViewModel are used with no unnecessary call to the database
- App pulls/sends note and task data to/from the api,  on a per request basis, using an intentservice.
This only occurs when user wants to sync his data.


## User Interface Mocks