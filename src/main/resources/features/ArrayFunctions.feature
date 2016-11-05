@array_functions
Feature: Array Functions

  Scenario: Create two arrays and merge them into one
    Given an array of Strings and an ArrayList of Strings
    When the two arrays are merged
    Then the resultant array contains all elements from the source arrays

  @wip
  Scenario: Show the merged arrays
    Given two arrays of strings:
      | one | two | three | four | five |
      | aaa | bbb | ccc   | ddd  | eee  |
    When the two arrays are merged
    Then the resultant array contains all elements from the source arrays
