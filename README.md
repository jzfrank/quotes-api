# Quotes API
A minimal Spring API showing proof of concept. 

## Run project
```bash
$ ./mvnw clean spring-boot:run
```
visit `http://localhost:8083/swagger-ui/index.html` to see swagger documentation. 

### Get all quotes
```bash
curl -X 'GET' \
  'http://localhost:8083/api/quotes' \
  -H 'accept: */*'
```

### Get a quote by Id

```bash
curl -X 'GET' \
  'http://localhost:8083/api/quotes/7146063b-1b44-4bd4-bd29-72a6636a8fc0' \
  -H 'accept: */*'
```

### Add a quote
```bash
curl -X 'POST' \
  'http://localhost:8083/api/quotes' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "quote": "Pre Optimization is evil of everything",
  "authorName": "Donald Knuth"
}'
```

### Change a quote
```bash
curl -X 'PUT' \
  'http://localhost:8083/api/quotes/7146063b-1b44-4bd4-bd29-72a6636a8fc0' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "quote": "string",
  "authorName": "string"
}'
```

### Delete a quote by Id
```bash
curl -X 'DELETE' \
  'http://localhost:8083/api/quotes/7146063b-1b44-4bd4-bd29-72a6636a8fc0' \
  -H 'accept: */*'
```


## Dependency
Uses:
- Web
- JPA
- H2
- Lombok 
- springdoc open api
