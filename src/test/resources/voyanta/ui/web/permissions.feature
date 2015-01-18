Feature: To check the permissions at all level

  Scenario Outline: A user with viewer permissions can only view the objects
    Given A user exists with 'view' permissions for an object 'Building' and logs in
    And go to permission page
    Then should see the data with Type as '<Type>',Object as '<Object Name>' - '<Object Reference>' and Level of Access as '<Level of Access>'
    And user successfully logs out

  Examples:

    | Type     | Object Name                  | Object Reference | Level of Access |
    | Building | Amsterdam Museumstraat Plaza | BLG017           | VIEWER          |

#    Given A user exists with submit permissions
#    And the permission is set for 'top level object' only
#    When user logs in with his credentials
#    And go to permission page
#    Then should see the data with Type as '<Type>',Object as '<Object Name>' - <Object Reference>' and Level of Access as '<Level of Access>'

 # @permissions
  Scenario: A user with viewer permissions can only view the UI objects (viewer1@automation.com/password1!)

    Given A user exists with 'view' permissions for an object 'Building' and logs in
    And user goes to 'Building' Tab
    And opens the object 'Amsterdam Kalverstraat Office Building'
    Then should see the 'Asset Reference' as 'BLG021'
    And cannot open the child level object type 'Asset Transaction' ,object name 'Asset Transaction 1898' with 'Owner Entity Reference' as 'FUND004'
    And can download the DST
    And cannot attach a file
    And cannot edit the data
    And cannot delete the data
    And cannot see the share option
    When user goes to data manager page
    Then should not see the upload option

 # @permissions
  Scenario: A user with viewer permissions on top and child level can only view the objects

    Given A user exists with 'view on child' permissions for an object 'Building' and logs in
    And user goes to 'Building' Tab
    And opens the object 'Amsterdam Kalverstraat Office Building'
    Then should see the 'Asset Reference' as 'BLG021'
    And can download the DST
    And cannot attach a file
    And cannot edit the data
    And cannot delete the data
    And cannot see the share option
    And can open the child level object type 'Asset Transaction' ,object name 'Asset Transaction 1898' with 'Owner Entity Reference' as 'FUND004'
    Then can download the DST
    And cannot attach a file
    And cannot edit the data
    And cannot delete the data
    And cannot see the share option
    When user goes to data manager page
    Then should not see the upload option

 # @submitter
  Scenario: A user with submitter permission can submit data for the objects which he have permission as top level object only (submit1@automation.com/password1!)

    Given A user exists with 'submitter' permissions for an object 'Building' and logs in
    And user goes to 'Building' Tab
    And opens the object 'Amsterdam Museumstraat Plaza'
    Then should see the 'Asset Reference' as 'BLG017'
    And can download the DST
    And cannot open the child level object type 'Asset Transaction' ,object name 'Asset Transaction 1898' with 'Owner Entity Reference' as 'FUND004'
    And can attach a file
   # When user deletes the data for top level object
   # Then should see a record with 'Pending' status in 'Approval' column
    And cannot see the share option for top level object
    And can edit the data for top level object
    Then should see a record with 'Pending' status in 'Approval' column
    When user modifies the 'Provider Reference' for top level object to 'PROV002'
    When user goes to data manager page
#    And should see the upload option
#    When user upload DST with the object which he have permission for
#    Then should see a DST with 'Pending' status in 'Approval' column
#    When Admin rejects all the changes requested by user

#  @submitter
  Scenario: A user with submitter permission can submit data for the objects which he have permission as child level object only(submitchild@automation.com/password1!)
    Given A user exists with 'submitchild' permissions for an object 'Building' and logs in
    And go to permission page
    Then should see the data with Type as 'Building',Object as 'Amsterdam Museumstraat Plaza' - 'BLG017' and Level of Access as 'SUBMITTER'
    And user go to homepage
    When user goes to 'Building' Tab
    And opens the object 'Amsterdam Museumstraat Plaza'
    Then should see the 'Asset Reference' as 'BLG017'
    And should download the DST for top level object
    And cannot attach a file for top level object
    And cannot edit the data for top level object
    And cannot delete the data for top level object
    And cannot see the share option for top level object
    When can open the child level object type 'Asset Transaction' ,object name 'Asset Transaction 1894' with 'Owner Entity Reference' as 'FUND004'
    And should download the DST for child level object
#    And can attach a file for child level object
#    When user deletes the data for child level object
#    Then should see a record with 'Pending' status in 'Approval' column
    And cannot see the share option for child level object
    And can edit the data for child level object
#    Then should see a record with 'Pending' status in 'Approval' column
    When user modifies the 'Provider Reference' for top level object to 'PROV002'
    When user goes to data manager page
    And should see the upload option
#    When user upload DST with the object which he have permission for
#    Then should see a DST with 'Pending' status in 'Approval' column
#    When Admin rejects all the changes requested by user

  @submitter
  Scenario: A user with submitter permission can submit data for the objects which he have permission as top level and child level objects (submit2@automation.com/password1!)

    Given A user exists with 'submitboth' permissions for an object 'Building' and logs in
    And go to permission page
    Then should see the data with Type as 'Building',Object as 'Amsterdam Museumstraat Plaza' - 'BLG017' and Level of Access as 'SUBMITTER'
    And user go to homepage
    When user goes to 'Building' Tab
    And opens the object 'Amsterdam Museumstraat Plaza'
    Then should see the 'Asset Reference' as 'BLG017'
    And should download the DST for top level object
#    And can attach a file for top level object
#    When can delete the data for top level object
#    Then should see a record with 'Pending' status in 'Approval' column
#    And can see the share option for top level object
    And can edit the data for top level object
    When user modifies the 'Provider Reference' for top level object to 'PROV002'
#    When user upload DST with the object which he have permission top level object
#    Then should see a DST with 'Pending' status in 'Approval' column
    When can open the child level object type 'Asset Transaction' ,object name 'Asset Transaction 1894' with 'Owner Entity Reference' as 'FUND004'
    And should download the DST for child level object
    And can attach a file for child level object
#    When user deletes the data for child level object
#    Then should see a record with 'Pending' status in 'Approval' column
#    And can see the share option for child level object
    And can edit the data for child level object
#    Then should see a record with 'Pending' status in 'Approval' column
    When  user modifies the 'Provider Reference' for top level object to 'PROV002'
    When user goes to data manager page
    And should see the upload option
#    When user upload DST with the object which he have permission for low level object
#    Then should see a DST with 'Pending' status in 'Approval' column
#    When Admin rejects all the changes requested by user







