@string_functions
Feature: String Functions

  Scenario: A String can be capitalised
    Given a String "string"
    When the string is capitalised
    Then the string becomes upper-case "STRING"

  Scenario: A String can be un-capitalised
    Given a String "STRING"
    When the string is uncapitalised
    Then the string becomes upper-case "string"

  @deliberate_fail
  Scenario: A String can be capitalised
    Given a String "stringz"
    When the string is capitalised
    Then the string becomes upper-case "STRING"

