@map_functions
Feature: Maps and Datatables

  Scenario: Can convert from Datatable to HashMap
    Given a datatable with the following data:
      | name | FISH  |
      | type | OCEAN |
      | size | SMALL |
    When a hashmap is created from that data
    Then the hashmap data is equivalent
