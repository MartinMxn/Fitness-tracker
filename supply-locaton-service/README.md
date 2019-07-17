## supply-location-service

### docker
docker-compose.yml file
```
>docker-compose up 

to pull all the part needed for this image and consolidate together

rabbitmq: 3-management  # 3 is UI version
5672 is default

data will stroe in local docker container
```

### @GeospatialIndexed
```
mongodb could deal with geospatical index
could create a class contains longtitude and latitude
```

### @JsonProperty
```
Java run time is java obj, but store locally is json obj,
to notify which key is to which value
```

### RabbitMQ
```
guest:guest as default name:password
will use when distribution
spring cloud stream

```