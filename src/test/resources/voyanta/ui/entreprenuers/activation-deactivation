Feature: Activation and deactivation of objects who have parent child relation



  Scenario Outline: If parent's active status is changed to inactive then the child status is updated to inactive

    Given a '<Parent DST>' DST is uploaded with active status as '<Parent DST initial Status>'
    And a related '<Child DSTs>' is also uploaded with active status as '<Child DSTs Status>'
    When the '<Parent Updated DST>' DST is updated with active status to '<Parent updated Status>'
    Then active status of '<Parent Table>','<Child Tables>' tables should be '<Parent Table Active Status>','<Child Tables Active Status> correspondingly

    Examples:

    |Parent DST|Parent DST initial Status|Child DSTs|Child DSTs Status|Parent Updated DST| Parent updated Status|Parent Table|Child Tables| Parent Table Active Status|Child Tables Active Status|
    |Building  |      Y                  |Lease     |N                |Building-Inactive |N                     |Building    |Lease,Asset |N                          |N,N                       |








