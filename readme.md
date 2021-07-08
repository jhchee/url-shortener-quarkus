# url.shortener.quarkus
This project presents a simple way to build URL shortener using Quarkus.

## Endpoints
Two endpoints are exposed to shorten and expand a given url.
1. To shorten a url
```
POST /shorten

Request:
{
    "url": {YOUR_URL}
}

Response:
{
    "url": {SHORTENED_URL}
}
```
2. To expand the shortened URL
```
GET /{SHORTENED_URL}

Redirect to YOUR_URL
```

## Building
```
./gradlew clean build -Dnative
```
## Deploying
It can be deployed to any serverless platform but in this case I choose to deploy on AWS Lambda.
Run this command, and you will be guided with steps to deploy to AWS lambda
```
sam deploy --template-file build/sam.jvm.yaml --guided
```
It is crucial to include this dependency `implementation ("io.quarkus:quarkus-amazon-lambda-http")` if you're deploying to AWS.
## Test locally
If you wish to test AWS lambda function locally, run this command
```
sam local start-api --template build/sam.jvm.yaml
```

## Environment variables
This project requires you to define 2 environment variables
```
SERVER_BASE_URL = {{YOUR_SERVER_URL}}
MONGO_DB_SOURCE = {{MONGO_DB_URL}}
```