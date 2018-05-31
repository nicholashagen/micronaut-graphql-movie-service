# Movie Service

This repository demonstrates using the [Micronaut](http://micronaut.io/) framework to
create a [GraphQL](http://graphql-java.readthedocs.io/en/latest/) implementation of a 
simple movie service.  The backend is powered by a very simple JDBC H2 database.

## Getting Started

### Command Line

- Run `./gradlew run`

### IDE Setup

- Import the Gradle project into your IDE of choice 
(_note that your IDE must support annotation processing...
see [Micronaut IDE Setup](http://docs.micronaut.io/snapshot/guide/index.html#ideSetup) 
for more information).

- Run the `com.znetdevelopment.movies.Application` main class

### Execute Query

- Load GraphiQL editor @ http://localhost:8080/static/graphiql.html

- Use the following example

```
query {
  movies {
    name
    category { name }
    duration
    description
    director {
      firstName
      lastName
      directedMovies {
        name
      }
    }
    actors {
      firstName
      lastName
      actedInMovies { name }
    }
  }
}
```
