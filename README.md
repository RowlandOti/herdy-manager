Herdy-Manager for the Herdy platform

#Requirements 
-Java 8 - set java 8 home

# Architecture

## Organisation by layer and then by feature
We have three layers i.e domain, data and presenter. 

### Domain Layer
Domain layer contains the company logic and interactors(use cases) e.g how to retrieve models, create models, show model details e.t.c. It contains only java dependencies. Try as much as possible to keep Android logic away from this layer. This layer also defines interfaces to be implemented by other layers which restricts them to company logic.
### Data Layer
Data layer is an abstraction over the domain layer. It hides the access to the data in such a manner as to allow a variety of data sources without the presenter caring about it. The layer contains implementation of repository interfaces, mappers, api services, payloads, data sources e.t.c
### Presenter Layer
Presenter layer contains Android view logic such as presenter, views, adapters, layoutmanagers


# Guide to Creating New Feature
- Create new Xxxfeature package across layers
- Create feature component in components package i.e XxxComponent.java
- Create feature module in modules package i.e XxxModule.java 
- Create feature presenters, repository, datasources, models and mapper packages in relevent layers
- Create feature view package (activities, fragments, adapters, layoutmanagers) in the presenter layer.