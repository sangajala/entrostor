@businessRules
Feature: Checking the functionality of business rules
#  for your reference
  Background:
    Given user arrives on Business Rules Page


  Scenario Outline: user can create business rules with different types

    Given user creates the business rule
    Then user should see a page with header Create New Rule: Step 1
    When user provides below data and continues
      | RuleName       | <RuleName>       |
      | ObjectType     | <ObjectType>     |
      | Field          | <Field>          |
      | ProviderOption | <ProviderOption> |
      | Provider       | <Provider>       |
    Then user should see a page with header Create New Rule: Step 2
    Then user drag and drop '<DataValueType>' with '<ComparedObject>'
    When user provides below data in step 2 and saves the rule
      | Field       | <Field>       |
      | FieldName   | <FieldName>   |
      | Operator    | <Operator>    |
      | Value       | <Value>       |
      | BRType      | <BRType>      |
      | MessageType | <MessageType> |
      | Message     | <Message>     |
  Then user should see 'Business rule created' on the page
  And '<RuleName>' shows on the list
#

  Examples:
    | RuleName      | ObjectType | Field         | ProviderOption | Provider | DataValueType   | ComparedObject | FieldName  | Operator    | Value        | BRType                                     | MessageType | Message   |
    | CBR-Error-001 | Building   | Building Name | include        | PROV001  | Submitted Value | Current Value  | Investment | is equal to | Current Asset Custom Classification 1 | Trigger a warning with a following message | Current Value        | Current Asset Custom Classification 2 |
#   |CBR-Error-002|Lease     |Building Reference|include        |PROV001   |Current Value  |Number       |Building Name|is equal to| CR-Error-001|Alert  |Number     |CBR-Error-001|
 # |CBR-Error-003|Lease Unit|Building Name|Include        |PROV001   |Previous Value |Date         |Building Name|is equal to| CR-Error-001|Error  |Date          |CBR-Error-001|
 #  |CBR-Error-004|Sales     |Building Name|Include        |PROV001  |Submitted Value|Text         |Building Name|is equal to| CR-Error-001|Replace||CBR-Error-001|


  @db
  Scenario Outline: user deletes business rules

    Given a record exists in Database with name '<BRName>'
    And user arrives on Business Rules Page
    When user deletes the business rules with '<BRName>'
    Then the pop up box 'Confirm deletion' should appear
    When user clicks 'Yes'
    Then '<BRName>' should be deleted from the list

  Examples:
    | BRName       |
    | AB-ForDelete |

@db
  Scenario Outline: user edits business rules

    Given a record exists in Database with name '<RuleName>'
    And user arrives on Business Rules Page
    And user edits the business rules with '<RuleName>'
    Then user should see a page with header Edit Rule <RuleName>: Step 1
    When user provides below data and continues
      | RuleName       | <RuleName>       |
      | ObjectType     | <ObjectType>     |
      | Field          | <Field>          |
      | ProviderOption | <ProviderOption> |
      | Provider       | <Provider>       |
    Then user should see a page with header Edit Rule <RuleName>: Step 2
#    Then user drag and drop '<DataValueType>' with '<ComparedObject>'
    When user modified the rule with below data in step 2 and saves the rule
      | Field       | <Field>       |
      | FieldName   | <FieldName>   |
      | Operator    | <Operator>    |
      | Value       | <Value>       |
      | BRType      | <BRType>      |
      | MessageType | <MessageType> |
      | Message     | <Message>     |
    Then user should see 'Business rule is updated message' on the page
    And '<RuleName>' shows on the list

  Examples:

    | RuleName      | ObjectType | Field         | ProviderOption | Provider | DataValueType   | ComparedObject | FieldName     | Operator    | Value                        | BRType  | MessageType | Message       |
    | CBR-Error-001 | Building   | Building Name | Include        | PROV001  | Submitted Value | Current Value  | Building Name | is equal to | Asset Custom Classification 1 | Trigger a warning with a following message | Current Value       | Current Asset Custom Classification 2 |



  Scenario Outline: user can go back to Step 1 page
    Given user creates the business rule
    Then user should see a page with header Create New Rule: Step 1
    When user provides below data and continues
      | RuleName       | <RuleName>       |
      | ObjectType     | <ObjectType>     |
      | Field          | <Field>          |
      | ProviderOption | <ProviderOption> |
      | Provider       | <Provider>       |
    Then user should see a page with header Create New Rule: Step 2
    And user goes back to previous page
    Then user should see a page with header Create New Rule: Step 1

  Examples:
  | RuleName      | ObjectType | Field         | ProviderOption | Provider | DataValueType   | ComparedObject | FieldName  | Operator    | Value        | BRType                                     | MessageType | Message   |
  | CBR-Error-001 | Building   | Building Name | include        | PROV001  | Submitted Value | Current Value  | Investment | is equal to | Current Asset Custom Classification 1 | Trigger a warning with a following message | Current Value        | Current Asset Custom Classification 2 |
#


  @issues
  Scenario Outline: user can create mapping rules

    Given user goes to Mapping Rules page
    And user creates the mapping rule
    Then user should see a page with header Create New Rule: Step 1
    When user provides below data and continues
      | RuleName       | <RuleName>       |
      | ObjectType     | <ObjectType>     |
      | Field          | <Field>          |
      | ProviderOption | <ProviderOption> |
      | Provider       | <Provider>       |
    Then user should see a page with header Create New Rule: Step 2
    When user defines '<From>', '<To>' and '<Notes>' and saves the rule
    Then user should see 'Mapping rule is updated message' on the page
    And '<RuleName>' shows on the list

  Examples:
    | RuleName      | ObjectType | Field         | ProviderOption | Provider | From            | To   | Notes |
    | CMR-Error-001 | Building   | Building Name | Include        | PROV001  | Submitted Value | Text |   notes    |
 #  |CMR-Error-002|Lease     |Building Name|Include        |PROV001   |Submitted Value|Text    |        |
  # |CMR-Error-003|Lease Unit|Building Name|Include        |PROV001   |Submitted Value|Text    |        |


  Scenario Outline: user can go back to Mapping rule Page
    Given user goes to Mapping Rules page
    And user creates the mapping rule
    Then user should see a page with header Create New Rule: Step 1
    When user provides below data and continues
      | RuleName       | <RuleName>       |
      | ObjectType     | <ObjectType>     |
      | Field          | <Field>          |
      | ProviderOption | <ProviderOption> |
      | Provider       | <Provider>       |
    Then user should see a page with header Create New Rule: Step 2
    And user goes back to previous page
    Then user should see a page with header Create New Rule: Step 1
  Examples:
  | RuleName      | ObjectType | Field         | ProviderOption | Provider | From            | To   | Notes |
  | CMR-Error-001 | Building   | Building Name | Include        | PROV001  | Submitted Value | Text |   notes    |



  @db
  Scenario Outline: user deletes mapping rules

    Given a Mapping rule record exists in Database with name '<MPRName>'
    And user goes to Mapping Rules page
    When user deletes the business rules with '<MPRName>'
    Then the pop up box 'Confirm deletion' should appear
    When user clicks 'Yes'
    Then '<MPRName>' should be deleted from the list

  Examples:
    | MPRName       |
    | ABC-ForDelete |

  @db@issues
  Scenario Outline: user edits mapping rules

    Given a Mapping rule record exists in Database with name '<MPRuleName>'
    And user goes to Mapping Rules page
    And user edits the business rules with '<MPRuleName>'
    Then user should see a page with header Edit Rule <MPRuleName>: Step 1
    When user provides below data and continues
      | RuleName       | <MPRuleName>       |
      | ObjectType     | <ObjectType>     |
      | Field          | <Field>          |
      | ProviderOption | <ProviderOption> |
      | Provider       | <Provider>       |
    Then user should see a page with header Edit Rule <MPRuleName>: Step 2
    When user defines '<From>', '<To>' and '<Notes>' and saves the rule
    Then user should see 'Mapping rule Created' on the page
    And '<MPRuleName>' shows on the list

  Examples:
    | MPRuleName      | ObjectType | Field         | ProviderOption | Provider | From            | To   | Notes |
    | CMR-Error-001 | Building   | Building Name | Include        | PROV001  | Modified Value | Modified Text | Modified notes  |
 #  |CMR-Error-002|Lease     |Building Name|Include        |PROV001   |Submitted Value|Text    |        |
  # |CMR-Error-003|Lease Unit|Building Name|Include        |PROV001   |Submitted Value|Text    |        |