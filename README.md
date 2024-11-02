## Build
### Build from cache
`docker build -t stub-image .`

## Run
`docker run --name stub-app -p 8081:8081 -d stub-image`

## Test
### Find user
`curl http://localhost/8081/api/getUser/{login}`

### Add user
`curl -X POST -H "Content-Type: aplication/json" -d '{ "login": "new-user", "password": "new-password", "email": "new-user@example.com" }' http://localhost/8081/api/postUser -v`

`curl -X POST -H "Content-Type: aplication/json" -d '{ "login": "new-user", "password": "new-password", "email": "new-user@example.com" }' http://localhost/8081/api/postUser2 -v`

### Get random user
`curl localhost:8081/api/getRandomUser`
