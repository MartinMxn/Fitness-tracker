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