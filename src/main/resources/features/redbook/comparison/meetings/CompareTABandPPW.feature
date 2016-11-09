@redbook @compare_tab
Feature: Meetings data between PPW and TAB should be equivalent

  Scenario: Compare the meetings data between PPW and TAB
    Given the meetings data is acquired from:
      | server              | api              | jurisdiction | location                  | method   |
      | api.sunbets.co.uk   | info-service     |              | AUS                       | PASSTHRU |
      | api.beta.tab.com.au | tab-info-service | VIC          | VIC,NSW,QLD,TAS,WA,SA,ACT | PASSTHRU |
    When the data is compared
    Then they are equivalent
