# Mindex Java Challenge Solution Notes and Documentation

## Task 1
Implemented new endpoint `/reportingStructure/{id}` to retrieve the fully filled out reporting structure for an employee. The endpoint could have been named anything but kept the same format as the employee endpoint to maintain consistency.

I noticed that when reading from the `/employee/{id}` endpoint, the direct reports did not include all of the employee information- just the employee ids. I would suggest updating the endpoint to pull this information recursivly so it is always availible if needed.

There seem to be numerous incorrect Logging messages in the Employee Controller and Employee Service, this can cause issues when debugging. Would be an easy fix but not specified in the challenge- would most likely be an issue to be resolved with a patch.

## Task 2
Implemented new endpoints `/compensation` (to create) and `/compensation/{id}` for read operations. New entities are persisted to MongoDB and can be retrieved.

## Tests
Tests for both tasks are included.