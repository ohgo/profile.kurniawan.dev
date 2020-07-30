# profile.kurniawan.dev

Aggregates Goodreads latest reads and Spotify recently played songs for my personal website (https://kurniawan.dev)

## Upstream dependencies

* [Spotify API](https://developer.spotify.com/documentation/web-api/reference/player/get-recently-played/)
* [Goodreads API](https://www.goodreads.com/api)

## Downstream dependencies

* [kurniawan.dev](https://kurniawan.dev)

## Build

Run `docker build -t ohgo/ku-profile:latest .`

## Push

For production use behind nginx.

1. Login `docker login`
2. Push `docker push ohgo/ku-profile:latest`

## Run locally

```shell script
docker run -p 8080:8080 \
    -e SPOTIFY_TOKEN_PASSWORD=<SPOTIFY_TOKEN_PASSWORD> \
    -e SPOTIFY_TOKEN_REFRESH=<SPOTIFY_TOKEN_REFRESH> \
    -e GOODREADS_KEY=<GOODREADS_KEY> \
    --name ku-profile \
    ohgo/ku-profile:latest
```

Try [GET profile](http://127.0.0.1:8080/profile).