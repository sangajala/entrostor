@DataSubmission
Feature: Data Submission GUI Test
 #  for your reference

Background:
Given user arrives on Upload page


Scenario Outline: Upload DST and check the returned message
Given user upload DST '<fileNameList>' 
Then correct message '<MSG>' and status '<UploadStatus>' is showed on the upload page 

 Examples:
 | fileNameList | MSG |UploadStatus|
 | 2.05 - Technical Validation - Invalid Header - File 1.csv  |Invalid header |	1  File was not uploaded |
 | 2.06 - Technical Validation - Incorrect file format - File 3.pdf |Incorrect file format  |	1  File was not uploaded |
 | 2.05 - Technical Validation - Invalid Header - File 1.xlsx |Invalid header |	1  File was not uploaded |
 | 2.05 - Technical Validation - Invalid Header - File 2.csv  |Invalid header |	1  File was not uploaded |
 | 2.06 - Technical Validation - Incorrect file format - File 1.xls |Incorrect file format  |	1  File was not uploaded |
 | 2.06 - Technical Validation - Incorrect file format - File 2.csv.exe |Incorrect file format  |	1  File was not uploaded |
 | 2.07 - Technical Validation - Blank data file.csv           |No data to process found in file 2.07 - Technical Validation - Blank data file.csv  |	1  File was not uploaded |
 | 2.07 - Technical Validation - Blank data file.xlsx          |No data to process found in file 2.07 - Technical Validation - Blank data file.xlsx |	1  File was not uploaded |

Scenario Outline: Review Page interaction-all rows contain error
Given user upload DST '<fileNameList>'
And user goes to submission page
Then user see '<ValidationResult>' is displayed for the submission on '<Page>'
And user check the 'Review' page
Then user see '<number>' delete single row button
And user '<exist>' see delete all error button
And user '<exist2>' see delete all warning and error button

 Examples:
| fileNameList | ValidationResult |Page|number|exist|exist2|
| 2.12 - Remove single row - All rows with Errors.xlsx |28  errors |My Submission |0|cannot|cannot|
#| 2.13 - Remove single row - Mixed.xlsx               |28  errors |My Submission ||


  Scenario Outline: Review Page interaction-delete single row
    Given user upload DST '<fileNameList>'
    And user goes to submission page
    Then user see '<ValidationResult>' is displayed for the submission on '<Page>'
    And user check the 'Review' page
    Then user see '<number>' delete single row button
    When user delete row '<rowNumber>'
    And user confirm '<confirmation>' delete
    Then user see '<leftRows>' delete single row button

  Examples:
    | fileNameList                                         | ValidationResult|Page         |number|rowNumber|confirmation|leftRows|
    | 2.13 - Remove single row - Mixed.xlsx                |28  errors       |My Submission|3     |1        |Yes         |2       |
    #| 2.12 - Remove single row - All rows with Errors.xlsx |28  errors      |My Submission|0     |1        |2       |

  @ErrorRows
  Scenario Outline: Review Page interaction-delete all error row
    Given user upload DST '<fileNameList>'
    And user goes to submission page
    Then user see '<ValidationResult>' is displayed for the submission on '<Page>'
    And user check the 'Review' page
    When user click delete all '<errorsORWarning>'
    And user confirm '<confirmation>' delete all
    Then user see error/warning successfully deleted msg
    Then user see '<ValidationResult2>' is displayed for the submission on '<Page2>'

  Examples:
    | fileNameList                           | ValidationResult|Page         |errorsORWarning|confirmation|ValidationResult2|Page2|
    | 2.13 - Remove single row - Mixed.xlsx  |28  errors       |My Submission| Error         |yes          |Correct         |History|
  #  |                                        |28  errors       |My Submission| Error         |No           |     |can   |can   |
 #   |                                        |28  errors       |My Submission| WarningAndError|Yes         |
  #  |                                        |28  errors       |My Submission| WarningAndError|No          |     |can   |can   |
  #  |                                        |28  errors       |My Submission| Error          |Yes         |


@technicalvalidation
Scenario Outline: technical validation of data
Given user upload DST '<fileNameList>' 
And user goes to submission page
Then user see '<ValidationResult>' is displayed for the submission on '<Page>'
And user check the 'Review' page
Then user check the BR fired for the right '<ObjectType>' right '<Column>' in correct '<Row>' with correct '<Sentinel>' and correct '<MSG>' and sentinel column also shows correct '<Value>'

 Examples:
    |fileNameList                                              |Page        |ValidationResult  |ObjectType    |Row | Column          |Sentinel |MSG| Value|
    |2.03.4 - Technical Validation - Incorrect format error.csv|MySubmission|1 error           |Debt Facility |1   |Execution Date   s|error    |Field: Debt Seniority Reason: Invalid lookup value.  |1  error|
#   |2.03.1 - Technical Validation - Incorrect format error.xlsx|MySubmission|1 error           |Debt Facility |1   |Actual Principal Balance At Issue Date|error    |Field:
 #  |Debt Facility |1   |Committed Principal Balance At Issue Date |error    |Field: Committed Principal Balance At Issue Date Reason: This number is smaller than the minimum value allowed.   |1  error|
    |3.0 LocalizedLookUpValue.xlsx                              |MySubmission|1 error           |Building      |1   |Asset Custom Classification 1 |error    |Field: Asset Custom Classification 2 Reason: Invalid lookup value.  |1  error|
    |2.04 - Technical Validation - Invalid foreign key.csv      |MySubmission|3 errors          |Lease         |1   |                              |         |Reason: The Asset reference you have supplied does not exist. Please recheck this reference or see the DST Guide for more support. ;Reason: The LegalEntity reference you have supplied does not exist. Please recheck this reference or see the DST Guide for more support. ;Field:Reason: The Lease reference you have supplied does not exist. Please recheck this reference or see the DST Guide for more support. |3 errors|



