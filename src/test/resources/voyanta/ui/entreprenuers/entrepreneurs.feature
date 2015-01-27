@entro
Feature: Dashboard checks for entrepreneurs

  Verify that option 'Proposals' is displayed in the menu-done
  Verify that proposal list is displayed in 'My Proposals' page by selecting Proposals option
  Verify that each proposal in proposal list has Edit(Pencil icon) option
  Verify that each proposal in proposal list has Delete (X) option-done
  Verify that each proposal in proposal list has Activate/Deactivate options
  Verify that Edit proposal form is displayed from Edit icon
  Verify Edit Proposal Form is opened on the right side of proposal list in My proposal page
  Verify that a fully funded proposal can not be edited
  Verify that Success message is displayed on saving the proposal-done
  Verify that if a field value is changed and then saved, changes should be reflected in view mode (Repeat with each individual field and combination of fields)
  Verify that Warning message is displayed if user tries to navigate to another page without saving proposal after changes are made
  Verify that user can not save proposal without filling mandatory fields
  Verify that user can not upload image other than .png, .jpg, .bmp format
  Verify that user can not enter more than 500 characters in field: "What problem do you solve and how"
  Verify that fields Raising , Equity for sales, Minimum Investment , Previously invested, Percentage funded does not accept non-numeric values
  Verify fields Raising , Equity for sales, Minimum Investment , Previously invested, Percentage funded does not accept negative values
  Verify that "Percentage funded" field does not accept value more than 100
  Verify that user gets notification message when Percentage funded value is 100
  Verify all old values should be retained if user edit field values but does not save the changes made
  Verify updated proposal name gets saved in My Proposal List
  Verify after proposal is updated it should be visible at top of the proposal list.
  Verify user stays on the same page after saving proposal
  Verify user can Activate the Inactive proposal
  Verify user can Deactivate the active proposal
  Verify user can Delete proposal-done
  Verify user can not delete Fully funded proposal


#  @ej-12
  Scenario: User can login

    Given User is in login page of entrostor
    When he tries to login with valid credentials
    Then user is in dashboard page



    Scenario: User can create Proposal

      Given user is in dashboard page
      When navigates to 'Proposal' Page
      And creates proposal with default data
      Then proposal should be created successfully
      And should be visible in the list of proposals
#      And delete the created proposal

  @ej-12
  Scenario: user can delete a proposal

    Given user is in dashboard page
    And a proposal exists in the list
    When he delete the existing proposal
    Then the proposal should be deleted






