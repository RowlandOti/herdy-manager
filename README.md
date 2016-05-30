Herdy-Manager for the Herdy platform

# Requirements 
-Java 8 - set java 8 home

# Architecture

## Organisation by layer and then by feature
We have three layers i.e domain, data and presenter. 

### Domain/Business Layer
- Domain layer contains the company logic and interactors(use cases) e.g how to retrieve models, create models, show model details e.t.c. It contains only java dependencies. Try as much as possible to keep Android logic away from this layer. This layer also defines interfaces to be implemented by other layers which restricts them to company logic. 
- Rule of thumb Business layer is core and it should be like cross-platform, all you need to do on new platform is to implement required interfaces. But Business layer will be the same. Business layer is just coordinator. 

### Data/Infrastructure Layer
- Data layer is an abstraction over the domain layer. It hides the access to the data in such a manner as to allow a variety of data sources without the presenter caring about it. The layer contains implementation of repository interfaces, mappers, api services, payloads, data sources e.t.c

### Presenter Layer
- Presenter layer contains Android view logic such as presenter, views, adapters, layoutmanagers

In general, your idea of data flow seems correct, so stick with that. From an architectural standpoint, keep the following in mind :

- Presentation layer just cares about the button being pressed and a response, it doesn't care where the action goes
- Domain/Business Logic layer only cares about what needs to happen, not how. So, "perform auth logout" calls "clear local auth details", "close connections" and "delete auth files", but i don't care how it happens.
- Data/Ifrastructure layer only cares about how stuff happens. So there is functionality to do the above 3 functions, and here we care on how we implement it.


# Guide to Creating New Feature
- Create new Xxxfeature package across layers
- Create feature component in components package i.e XxxComponent.java
- Create feature module in modules package i.e XxxModule.java 
- Create feature presenters, repository, datasources, models and mapper packages in relevent layers
- Create feature view package (activities, fragments, adapters, layoutmanagers) in the presenter layer.