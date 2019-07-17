# Fitness Tracker
```
A totally backend location info detect & analysis service
Stack includes so far: Spring Boot/Data JPA, H2, MySQL.
```

### Monolithic Application
```
Pros:
simple at first 
in-process latencies
single codebase, deployment unit
resource-efficient at small scale

Cons:
Communication gap and coordination overhead as application grows
Poor enforcement of modularity
Only vertically scaling
Downtime, deploy failures
Long build times
Hard to manage
```

## Spring Cloud
```
Spring Cloud Provide a way to turn SpringBoot into
distributed applications

reduce boilerplate code
quickly setup services and applications
same code, work anywhere
```