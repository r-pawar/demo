As part of this task, following changes are made.
1. The standalone application is converted into spring boot application.
2. Following two endpoints are exposed
   1. POST - /customers/{name}/rentals - create customer and add rentals
   2. GET - /customers/{name}/rentals/statements - get statement for added rentals for customer
3. Logging is added
4. Unit and integration tests are added.
5. The rental calculation from customer entity is refactored. Individual calculator calculating rent and reward points for each movie type is added. That way, adding new rule for another movie types will follow open-closed principle.

Following assumptions are made while implementing rental calculator.
1. Keeping the existing logic, Customer is uniquely identified by name. This is unrealistic in real world.
2. A customer can have one rental request at a time. [One rental can have one or more rentals] Adding new rentals with same customer name will override existing records.

Improvements/changes that could be done.
1. Instead of name, email address can be used to uniquely identify customer.
2. Customer can have more than one rental request at a time. This can be done by removing rentals list from customer entity and add as one-many relationship. THen, rental request lifecycle can be managed separately.
3. Database can be introduced for persistence storage.

Application can be simply started locally. Once application is started following command can be used to test endpoints.
1. To create customer and add rentals
```
curl -X POST http://localhost:8080/customers/<name>/rentals -d '[{"movie":{"title":"Die Hard", "movieType":"CLASSIC"}, "daysRented":3}]' -H "content-type:application/json"
```

2. To get statement for the customer
```
curl -X GET http://localhost:8080/customers/<name>/rentals/statements
```