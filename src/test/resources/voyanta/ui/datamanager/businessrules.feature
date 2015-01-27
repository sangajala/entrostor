@businessRules
Feature: Checking the functionality of business rules
#  for your reference
  Background:
    Given user arrives on Business Rules Page


#    Scenario: Sample
#
#      Given Sample test


  Scenario Outline: user can create business rules with different types

    Given user creates the business rules
    Then user should see a page with header 'Create New Rule: Step 1'
    When user provides below data and continues
      | RuleName       | <RuleName>       |
      | ObjectType     | <ObjectType>     |
      | Field          | <Field>          |
      | ProviderOption | <ProviderOption> |
      | Provider       | <Provider>       |
    Then user should see a page with header 'Create New Rule: Step 2'
    Then user drag and drop '<DataValueType>' with '<ComparedObject>'
  When user provides below data in step 2 and saves the rule
  |Field|<Field>|
  |FieldName|<FieldName>|
  |Operator |<Operator> |
  |Value    |<Value>    |
  |BRType   |<BRType>   |
  |MessageType|<MessageType>|
  |Message    |<Message>    |
#  Then user should see 'Business rule created.' on the page
  And '<RuleName>' shows on the list
#

  Examples:
    | RuleName      | ObjectType | Field         | ProviderOption | Provider | DataValueType   | ComparedObject | FieldName     | Operator    | Value        | BRType  | MessageType | Message       |
    | CBR-Error-001 | Building   | Building Name | include        | PROV001  | Submitted Value | Text           | Investment | is equal to | CR-Error-001 | Trigger a warning with a following message | Text        | CBR-Error |
#   |CBR-Error-002|Lease     |Building Reference|include        |PROV001   |Current Value  |Number       |Building Name|is equal to| CR-Error-001|Alert  |Number     |CBR-Error-001|
 # |CBR-Error-003|Lease Unit|Building Name|Include        |PROV001   |Previous Value |Date         |Building Name|is equal to| CR-Error-001|Error  |Date          |CBR-Error-001|
 #  |CBR-Error-004|Sales     |Building Name|Include        |PROV001  |Submitted Value|Text         |Building Name|is equal to| CR-Error-001|Replace||CBR-Error-001|


  Scenario Outline:: user deletes business rules

    Given a record exists in Database with name '<BRName>'
#    When user deletes the business rules with '<BRName>'
#    Then the pop up box 'Confirm deletion' should appear
#    When user clicks 'Yes'
#    Then '<BRName>' should be deleted from the list

  Examples:
    | BRName          |
    | 007-Delete Test |


  Scenario Outline:: user edits business rules

    Given user edits the business rules with '<BRName>'
    Then user goes to page 'Edit Rule '<BRName>': Step 1'
    When user select '<RuleName>','<ObjectType>','<Field>','<ProviderObtion>','<Provider>' and click 'Continue'
    Then user goes to page 'Edit Rule '<RuleName>': Step 2'
    When user selects <FieldName> <Operator><Value><BRType><Message> and click 'Save New Rule'
    Then user should see 'Business rule is updated message' on the page
    And <RuleName> shows on the list

  Examples:

    | RuleName      | ObjectType | Field         | ProviderObtion | Provider | DataValueType   | ComparedObject | FieldName     | Operator    | Value        | BRType  | Message       |
    | CBR-Error-001 | Building   | Building Name | Include        | PROV001  | Submitted Value | Text           | Building Name | is equal to | CR-Error-001 | Warning | CBR-Error-001 |


  Scenario Outline: user can create mapping rules

    Given User goes to 'Mapping Rules' page
    And user creates the mapping rules
    Then user goes to page 'Create New Rule: Step 1'
    When user select '<RuleName>','<ObjectType>','<Field>','<ProviderObtion>','<Provider>' and click 'Continue'
    Then user goes to page 'Create New Rule: Step 2'
    When user defines <Frome> <To> <Notes> and click 'Save New Rule'
    Then user should see 'Mapping rule is updated message' on the page
    And <RuleName> shows on the list

  Examples:
    | RuleName      | ObjectType | Field         | ProviderObtion | Provider | From            | To   | Notes |
    | CMR-Error-001 | Building   | Building Name | Include        | PROV001  | Submitted Value | Text |       |
 #  |CMR-Error-002|Lease     |Building Name|Include        |PROV001   |Submitted Value|Text    |        |
  # |CMR-Error-003|Lease Unit|Building Name|Include        |PROV001   |Submitted Value|Text    |        |

  Scenario: user can go back to Mapping rule Page
    Given User goes to 'Mapping Rules' page
    And user creates the mapping rules
    When user clicks button 'Back to Mapping Rules'
    Then user is back on Mapping Rules page

  Scenario: user can go back to Step 1 page
    Given user creates the business rules
    Then user goes to page 'Create New Rule: Step 1'
    When user select '<RuleName>','<ObjectType>','<Field>','<ProviderObtion>','<Provider>' and click 'Continue'
    And user goes clicks button 'Back to Step1'