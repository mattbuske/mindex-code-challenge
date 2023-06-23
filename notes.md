# Mindex Java Challenge Solution Notes and Documentation

## Task 1
Implemented new endpoint `/reportingStructure/{id}` to retrieve the fully filled out reporting structure for an employee. The endpoint could have been named anything but kept the same format as the employee endpoint to maintain consistency.

I noticed that when reading from the `/employee/{id}` endpoint, the direct reports did not include all of the employee information- just the employee ids. I would suggest updating the endpoint to pull this information recursivly so it is always availible if needed.

There seem to be numerous incorrect Logging messages in the Employee Controller and Employee Service, this can cause issues when debugging. Would be an easy fix but not specified in the challenge- would most likely be an issue to be resolved with a patch.

## Task 2
Implemented new endpoints `/compensation` (to create) and `/compensation/{id}` for read operations. New entities are persisted to MongoDB and can be retrieved.

## Tests
Tests for both tasks are included.

## Enhancements
I have enhanced this project beyond the scope (for fun) to allow for docker containerization and included documentation and re-created the spec of the rest API in Open API 3. This list is a living list and is subject to change!

- [X] Add the ability to build the final jar file into a docker container
- [X] Bump Gradle Version to 7.3
- [ ] Add the OpenAPI 3.0 Spec
- [ ] Enhance and optimize code
- [ ] Seperate out the MondoDB to a seperate container and utilize docker compose to run both in a small cluster
- [ ] Implement MkDocs documentation to improve readability and accessibility for the project
- [ ] Add github actions to automate certain aspects of the repository
    - [X] Add Automatic releases based on tags
    - [ ] Add Automatic unit tests
    - [ ] Add Automatic build of the JAR file if the tests pass
    - [ ] Add Automatic build of Docker Image if the tests pass