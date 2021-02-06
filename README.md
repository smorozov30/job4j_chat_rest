[![Build Status](https://travis-ci.org/smorozov30/job4j_chat_rest.svg?branch=master)](https://travis-ci.org/smorozov30/job4j_chat_rest)
[![codecov](https://codecov.io/gh/smorozov30/job4j_chat_rest/branch/master/graph/badge.svg?token=79XM545YOI)](https://codecov.io/gh/smorozov30/job4j_chat_rest)
# job4j_chat_rest

### Методы API:

#### Message:
- Получить все сообщения для комнаты. ***GET: /room/{roomId}/message/***
- Получить все сообщения пользователя. ***GET: /person/{personId}/message/***
- Получить сообщение по id. ***GET: /message/{id}***
- Создать сообщение. ***POST: /room/{roomId}/person/{personId}/message/***
- Обновить сообщение. ***PUT: /message/***
- Удалить сообщение. ***DELETE: /message/{id}***

#### Person:
- Получить всех пользователей. ***GET: /person/***
- Получить пользователя по id. ***GET: /person/{id}***
- Создать пользователя. ***POST: /person/***
- Обновить пользователя. ***PUT: /person/***
- Удалить пользователя. ***DELETE: /person/{id}***

#### Role:
- Получить все роли. ***GET: /role/***
- Получить роль по id. ***GET: /role/{id}***
- Создать роль. ***POST: /role/***
- Обновить роль. ***PUT: /role/***
- Удалить роль. ***DELETE: /role/{id}***

#### Room:
- Получить все комнаты. ***GET: /room/***
- Получить комнату по id. **GET: /room/{id}***
- Создать комнату. ***POST: /room/***
- Обновить комнату. ***PUT: /room/***
- Удалить комнату. **DELETE: /room/{id}***