## Support API at 9000
start with
```
mvn clean install
cd target
java -jar running.... 
```
```
POST http://localhost:9000/running (with JSON file)
GET http://localhost:9000/running/STOPPED?page=1&size=1 (page and size is not necessary but return null if empty)
DELETE http://localhost:9000/purge (clean all posted data, get will return default value result)
```

## hal browser
```
scan all your valid api
and build frontend support

powerful than postman
```

## 7.11 model impl
Dependencies,  
Java object - db table(JPA annotation),
embedded -> when code java firstly, we focus on object: ez, and db table for relational more,  
lombok: for getter/setter/info (@Data, @RequiredArgsConstructor),  
location repo Interface, Pagination from spring data domin

## 7.14 
location service -> impl -> controller
### @Controller vs @RestController
```
RestCon means controller return rest response
normal Controller, return string may not be rest response 
String will be parsed by spring into ViewResolver/ModelResolver, which is the name of page
```
### @Service
```
Mark the class as service class, then spring IOC container start,
will auto scan the @Service class and generate bean 
```

### start
```
cd target
java -jar running....jar [--server.port=[-1 means unoccupied port]]
```

## 7.16

### WebSocket
```
a computer communications protocol like http
full-deplex channel over a single TCP connection

why need?
http is pull only, ws can push
single url for initial http handshake
asynchronous, event-driven, messaging

when?
client and server need to exchange event at high frequency and low latency

Spring websocket

Compare with Rest
REST:
    Many urls, handful verb, links, stateless,
WebSocket,
    Single url to build http handshake, 
    all message go through same tcp connections

low frequency and low latency -> rest
high ...      and high ... -> websocker
```

### Spring Data Rest

### Sql vs NoSql
```
Sql
more transactional(finicial)
structure data
data volume not so large

NoSql
good scalbility
non-structure data(key-value, document, graph)
```

### RabbitMQ
```
Pros:
decouple
asychronous communication, producer/consumer pattern
```

### Maven
```
Project management and comprehension tool
dep manage / build cycle 

target fold contains distributable jar nad complied byte code

pom.xml file, project object model

version: 1.1.0
         major.minor.build
         1.2.3 -> 1.2.4 -> build changes
         1.2.0 -> 1.3.0 -> bug fixed + new feature
         1.0.0 -> 2.0.0 -> huge update, breaking changes
         
build life cycle
a lifecycle contains dif phases
a phase contains dif goals
a goal represent a specific task
```
