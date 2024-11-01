## Build
### Update repo 
`docker build --build-arg UPDATE_REPO=true --network host -t stub-app .`

### Build from cache
`docker build --network host -t stub-app .` or `docker build --build-arg UPDATE_REPO=false --network host -t stub-app .`

## Run
`docker run --network host stub-app`

## Test
### Find user
`curl http://localhost/8081/api/getUser/{login}`

### Add user
`curl -X POST -H "Content-Type: aplication/json" -d '{ "login": "new-user", "password": "new-password", "email": "new-user@example.com" }' http://localhost/8081/api/postUser -v`

`curl -X POST -H "Content-Type: aplication/json" -d '{ "login": "new-user", "password": "new-password", "email": "new-user@example.com" }' http://localhost/8081/api/postUser2 -v`
