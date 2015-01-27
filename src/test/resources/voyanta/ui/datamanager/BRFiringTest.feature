@businessRulesfiring
Feature: Checking the firing of business rules
#  for your reference
Background:
Given user arrives on Upload page

@replace
 Scenario Outline: Check the firing of replace and mapping rules 
Given user upload DST '<fileNameList>' 
And user goes to submission page
Then user see '<BRResult>' is displayed for the submission on '<Page>'
And user check the 'Review' page
Then user check the result for '<ObjectType>' for '<Column>' in row '<Row>' with value '<Value>'


 Examples:
    |fileNameList                  |Page   |BRResult  |ObjectType          |Column             | Row   |Value         |
 #    1. replace a non LoV with LoV, no error msg should be triggered
    | 137-RepaceNonLoVWithLoV.xlsx |History| Correct| Asset Valuation   |Valuation Method |1| 	Comparable Sales |
  #   2. replace AA.YTD amount with previous value+submitted amount, 4 rows in the DST, with one row having empty transactionDate
    | 395.4-Replace-previousValue.xlsx |History |Correct |  Account Activity  |YTD Balance |1,2,3,4|5000,9950,14850,19700|
 #    3. mapping rule and replace rule is fired Sequentially based on alphabetical order
    | 244 - Replace-Mappingcreate_a_mapping_rule_and_replacement_which_could_be_fired_sequencially.csv |History|Correct |Investment|Street Address|1|Mapping244B|
  #   4. replace salesBreakPoint(grandChild) column with Lease CommencementDate (grandParent)
   | 566_Replace-Repace_SalesBreakPoint_start_date_with_LeaseCommencementdate.xlsx |History|Correct |Sales Breakpoint|Breakpoint Start Date|1|2012-06-15|
  #   5. replace salesBreakPoint(grandChild) column with Lease CommencementDate (grandParent)
    | LeaseUnitReplaceReference.xlsx,replaceLeaseReference.xlsx,ReplaceUnitReference.xlsx|History|Correct |Lease|Leased Area|1|600|
    #6. Blank value handling
    
@warning    
Scenario Outline: Check the firing of warning and error 
Given user upload DST '<fileNameList>' 
And user goes to submission page
Then user see '<BRResult>' is displayed for the submission on '<Page>'
And user check the 'Review' page
Then user check the BR fired for the right '<ObjectType>' right '<Column>' in correct '<Row>' with correct '<Sentinel>' and correct '<MSG>' and sentinel column also shows correct '<BRValue>'

 Examples:
    |fileNameList                  |Page        |BRResult      |ObjectType          |Column             | Row      |   Sentinel          |MSG| BRValue|
    #compare the Unit(GrandParent) against Recovery(GrandChild), and warning should be triggered on both object
    | 387_CompareUnitAgainstRecovery_Recovery.xlsx,387_CompareUnitAgainstRecovery_Unit.xlsx|MySubmission| 2  warnings  | Unit,Recovery |Unit Start Date,Effective From Date   |1,1|warning,warning | Field: Unit Start Date Reason: 387 Create rule for Unit against Recovery 2002-01-02,Field: Effective From Date Reason: 387 Create rule for Unit against Recovery 2002-01-02|1 warning,1 warning|
    # dummy lease Unit shouldn't be counted 
    | 464-Lease.xlsx               |MySubmission| 1  warning   | Lease              |Lease Reference  |1|warning   | Warning Field: Lease Reference Reason: 464 There are 2 Lease Unit|1 warning|
    # compare Rent Escalation(GrandChild) against Lease(grandParent), bulk upload warning should be triggered on both object
    | 420-Warning-Lease.xlsx,420-RentEscalation_fired.xlsx |MySubmission| 2  warnings   | Lease,Rent Escalation |Commencement Date,Review Date|1,1|warning,warning|Field: Commencement Date Reason: 420-Review Date is less than Commencement Date1999-09-1,Field: Review DateReason: 420-Review Date is less than Commencement Date1999-09-15 |1 warning,1 warning|
   
