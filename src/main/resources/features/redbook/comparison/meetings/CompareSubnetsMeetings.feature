@redbook @compare_prod_ppw
Feature: Meetings data feeds between PROD and non-PROD systems should be equivalent.

  Scenario: Compare the meetings data between PROD and PPW (Sunbets)
    Given the meetings data from "prod"
    And the meetings data from "ppw"
    When the two are compared
    Then they are equivalent

  Scenario: Compare the meetings data between PROD and PPW (Sunbets)
    Given the meetings data is acquired from "pre-api.test.sunbets.co.uk"
    And the meetings data is acquired from "api.sunbets.co.uk"
    When the two are compared
    Then they are equivalent
