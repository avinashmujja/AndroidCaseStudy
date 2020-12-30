# MVVM Clean Architecture, Dagger2, Databinding, room db using kotlin

Github apis are limited to use for daily use, please take a note on this

Presentation: Activities,Fragment and Viewmodels comes under this layer, Handles data to the UI and user interactions
Domain(Usecases): Handles business logic,returns the value to Viewmodels. These Usecases follows  single responsibility principle.
Data: Provides data for our app. It is responsible for obtaining data from external sources like localDB, remoteAPI.

