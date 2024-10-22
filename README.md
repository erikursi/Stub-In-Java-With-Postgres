## Build
### Update repo 
`docker build --build-arg UPDATE_REPO=true --network host -t stub-app .`

### Build from cache
`docker build --network host -t stub-app .` or `docker build --build-arg UPDATE_REPO=false --network host -t stub-app .`

## Run
`docker run --network host stub-app`
