@report
Feature: Checking the reports and its data

  Background:
    Given user is in reports page


  Scenario Outline: Checking the reports on dashboard page with different filters
    Given user can see reports
    And clears the existing filters
    When selects the filters as below

      | Currency      | <Currency>      |
      | Year          | <Year>          |
      | Sector        | <Sector>        |
      | Region        | <Region>        |
      | Asset Manager | <Asset Manager> |
      | Asset         | <Asset>         |
      | Asset Tags    | <Asset Tags>    |
      | Investment    | <Investment>    |
      | Quarter       | <Quarter>       |
      | Month         | <Month>         |
      | Area Measure  | <Area Measure>  |
      | Toggle        | <Toggle>        |
      | report name   | <report name>   |
    And export the '<report name>' report
    Then the report is exported successfully
    And the saved report should match with expected report with name '<export file name>'
@AssetDiversification
  Examples: Asset Diversification

    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment| Quarter | Month | report name           | Toggle                  | export file name       |
    | GBP      | 2013 | SF           | Office |        |               |       |            |           |         |       | Asset Diversification | Asset Value             | Asset_Value.xls        |
    | GBP      | 2013 | SF           | Office |        |               |       |            |           |         |       | Asset Diversification | Gross Rental Income    | Gross_Rental_Income.xls|
    | GBP      | 2013 | SF           | Office |        |               |       |            |           |         |       | Asset Diversification | Net Rental Income      | Net_Rental_Income.xls  |
    | GBP      | 2013 | SF           | Office |        |               |       |            |           |         |       | Asset Diversification | Leasable Area          | Leased_Area.xls      |
    | GBP      | 2013 | SF           | Office |        |               |       |            |           |         |       | Asset Diversification | Number of Assets       | Number_of_Assets.xls   |
@Top10
  Examples: For Top 10 Assets
    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset   | Asset Tags | Investment | Quarter | Month | report name   | Toggle              | export file name           |
    | AUD      | 2013 | SF           | Retail |        |               |         |            |            |         |       | Top 10 Assets | Asset Value         | Top10_Asset_Value.xls       |
    | AUD      | 2013 | SF           | Retail |        |               |         |            |            |         |       | Top 10 Assets | Net Rental Income   | Top10_Net_Rental_Income.xls |
    | AUD      | 2013 | SF           | Retail |        |               |         |            |            |         |       | Top 10 Assets | Gross Rental Income | Top10_Gross_Rental_Income.xls |
    | AUD      | 2013 | SF           | Retail |        |               |         |            |            |         |       | Top 10 Assets | Rental Arrears      | Top10_Rental_Arrears.xls |
    | AUD      | 2013 | SF           | Retail |        |               |         |            |            |         |       | Top 10 Assets | Leasable Area       | Top10_Leasable_Area.xls |

@NOITrend
  Examples: For Valuation / NOI Trend
    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment | Quarter | Month | report name           | Toggle    | export file name                        |
    | USD      | 2013 | SF           |        |        |               |       |            |            |         |       | Valuation / NOI Trend | NOI Trend | Valuation NOI Trend.xls                 |
    | USD      | 2013 | SF           |        |        |               |       |            |            |         |       | Valuation / NOI Trend | Asset Value | Valuation NOI Trend_Asset_Valuation.xls |

  Examples: For Asset Map - Values
    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset  | Asset Tags | Investment | Quarter | Month | report name        | Toggle | export file name       |
    | USD      | 2013 | SF           |        |        |               |        |            |            |         |       | Asset Map - Values |        | Asset Map - Values.xls |

@OperatingPerformanceDASH @run
  Scenario Outline: Checking the reports on operating performance page with different filters
    Given user navigates to Operating performance page
    And user can see reports
    And clears the existing filters
    When selects the filters as below
      | Currency      | <Currency>      |
      | Year          | <Year>          |
      | Sector        | <Sector>        |
      | Region        | <Region>        |
      | Asset Manager | <Asset Manager> |
      | Asset         | <Asset>         |
      | Asset Tags    | <Asset Tags>    |
      | Investment    | <Investment>    |
      | Quarter       | <Quarter>       |
      | Month         | <Month>         |
      | Area Measure  | <Area Measure>  |
      | Toggle        | <Toggle>        |
      | report name        | <report name>        |
    And export the '<report name>' report
    Then the report is exported successfully
    And the saved report should match with expected report with name '<export file name>'

  Examples:  Occupancy Trend
    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset    | Asset Tags | Investment | Quarter | Month | report name     | Toggle       | export file name         |
    | EUR      | 2013 | SF           |        |        |               |          |            | Camden REIT|         |       | Occupancy Trend | Leased Area  |Occupancy_Leased_Area.xls |
    | EUR      | 2013 | SF           |        |        |               |          |            | Camden REIT|         |       | Occupancy Trend | Unit Area    |Occupancy_Unit_Area.xls |
@rentalArrears
  Examples: Rental Arrears As Of - 31/12/2013
    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment | Quarter | Month | report name                       | Toggle| export file name           |
    | EUR      | 2013 | SF           | Europe |        |               |       |            |            |         |       | Arrears As Of - 31/12/2013 |      | Rental Arrears As Of_1.xls |

  Examples: Operating Statistics
    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment | Quarter | Month | report name                            | Toggle| export file name   |
    | EUR      | 2013 | SF           | Europe |        |               |       |            |            |         |       |Operating Statistics As Of - 31/12/2013 |  |DASH_Operating Statistics.xls |

  Examples: Leasing Activity
    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment | Quarter | Month | report name     | Toggle| export file name   |
    | EUR      | 2013 | SF           | Europe |        |               |       |            |            |         |       |Leasing Activity |       |DASH_Leasing Activity.xls |

  Examples: Lease Expiry Schedule
    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment | Quarter | Month | report name | Toggle           | export file name   |
    | EUR      | 2013 | SF           | Europe |        |               |       |            |            |         |       | Lease Expiry Schedule | Expiring Area(SF)|Expiring Area(SF).xls |
    | EUR      | 2013 | SF           | Europe |        |               |       |            |            |         |       | Lease Expiry Schedule |Expiring & Terminating % of Total|Expiring&Terminating_Percentage.xls |
    | EUR      | 2013 | SF           | Europe |        |               |       |            |            |         |       | Lease Expiry Schedule |Expiring % of Total              |Expiring_%_of_Total.xls |
    | EUR      | 2013 | SF           | Europe |        |               |       |            |            |         |       | Lease Expiry Schedule |Expiring & Termination Area (SF) |Expiring_&_Termination_Area.xls |

 @FinancePDASH 
  Scenario Outline: Checking the Dashbaord on Financial performance page with different filters
    Given user navigates to Financial performance page
    And user can see reports
    And clears the existing filters
    When selects the filters as below
      | Currency      | <Currency>      |
      | Year          | <Year>          |
      | Sector        | <Sector>        |
      | Region        | <Region>        |
      | Asset Manager | <Asset Manager> |
      | Asset         | <Asset>         |
      | Asset Tags    | <Asset Tags>    |
      | Investment    | <Investment>    |
      | Quarter       | <Quarter>       |
      | Month         | <Month>         |
      | Area Measure  | <Area Measure>  |
      | Tab           | <Tab>           |
      | report name   | <report name>   |
    And export the '<report name>' report
    Then the report is exported successfully
    And the saved report should match with expected report with name '<export file name>'

    Examples: Budget vs Actual
    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment | Quarter | Month   | report name        | Tab        | export file name         |
    | EUR      | 2013 |  SM          |        |        |               |       |            | Camden REIT|         |         | Budget vs Actual   | Revenue    | BudgetActual_Revenue.xls |
    | EUR      | 2013 |  SM          |        |        |               |       |            | Camden REIT|  Q2-2013|         | Budget vs Actual   | Expense    | BudgetActual_Expense.xls |
    | EUR      | 2013 |  SM          |        |        |               |       |            | Camden REIT|         |May/2013 | Budget vs Actual   | NOI        | BudgetActual_NOI.xls |

    Examples: Revenue and Expense Breakdown
    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment | Quarter | Month   | report name                     | Tab        | export file name      |
    | AUD      | 2013 |  SM          |        |        |               |       |            | Camden REIT|         |         | Revenue and Expense Breakdown   | Revenue    | BreakDown_Revenue.xls |
    | AUD      | 2013 |  SM          |        |        |               |       |            | Camden REIT|  Q2-2013|         | Revenue and Expense Breakdown   | Expense    | BreakDown_Expense.xls |

@FPOperatingStatement
    Scenario Outline: Checking the Operating Statement Report on Financial performance page with different filters
    Given user navigates to Report Operating Statement page
    And user can see reports
    And clears the existing filters
    When selects the filters as below
      | Currency      | <Currency>      |
      | Year          | <Year>          |
      | Sector        | <Sector>        |
      | Region        | <Region>        |
      | Asset Manager | <Asset Manager> |
      | Asset         | <Asset>         |
      | Asset Tags    | <Asset Tags>    |
      | Investment    | <Investment>    |
      | Quarter       | <Quarter>       |
      | Month         | <Month>         |
      | Area Measure  | <Area Measure>  |
      | Tab           | <Tab>           |
      | CheckBox      | <CheckBox>      |
      | report name   | <report name>   |
    And export the '<report name>' report
    Then the report is exported successfully
    And the saved report should match with expected report with name '<export file name>'
  
    Examples: Operating Statement
    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment | Quarter | Month   | report name        | Tab                  |CheckBox      | export file name     |
    | EUR      | 2013 |  SM          |        |        |               |       |            | Camden REIT|         |         | Operating Statement| Net Operating Income |Activity      | NOI_Activity.xls     |
    | EUR      | 2013 |  SM          |        |        |               |       |            | Camden REIT|  Q2-2013|         | Operating Statement| Net Operating Income |YTD Reported  | NOI_YTD_Reported.xls |
    | EUR      | 2013 |  SM          |        |        |               |       |            | Camden REIT|         |May/2013 | Operating Statement| Net Operating Income |YTD Calculated| NOI_YTD_Calculated.xls |
    | USD      | 2013 |  SM          |        |        |               |       |            | Camden REIT|         |         | Operating Statement| Net Income           |Activity      | NI_Activity.xls     |
    | USD      | 2013 |  SM          |        |        |               |       |            | Camden REIT|  Q2-2013|         | Operating Statement| Net Income           |YTD Reported  | NI_YTD_Reported.xls |
    | USD      | 2013 |  SM          |        |        |               |       |            | Camden REIT|         |May/2013 | Operating Statement| Net Income           |YTD Calculated| NI_YTD_Calculated.xls |

@FPBalanceSheet 
    Scenario Outline: Checking the Balance Sheet Report on Financial performance page with different filters
    Given user navigates to Report Balance Sheet page
    And user can see reports
    And clears the existing filters
    When selects the filters as below
      | Currency      | <Currency>      |
      | Year          | <Year>          |
      | Sector        | <Sector>        |
      | Region        | <Region>        |
      | Asset Manager | <Asset Manager> |
      | Asset         | <Asset>         |
      | Asset Tags    | <Asset Tags>    |
      | Investment    | <Investment>    |
      | Quarter       | <Quarter>       |
      | Month         | <Month>         |
      | Area Measure  | <Area Measure>  |
      | Tab           | <Tab>           |  
      | report name   | <report name>   |
    And export the '<report name>' report
    Then the report is exported successfully
    And the saved report should match with expected report with name '<export file name>'

    Examples: Balance Sheet
    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment | Quarter | Month   | report name        | Tab                    |export file name     |
    | EUR      | 2013 |  SM          |        |        |               |       |            | Camden REIT|  Q2-2013|         | Balance Sheet      | Previous Year End      |PreviousYearEnd.xls     |
    | EUR      | 2013 |  SM          |        |        |               |       |            | Camden REIT|         | May/2013| Balance Sheet      | Same Period Prior Year |Same_Period_Prior_Year.xls |

@OperatingStatementReport
  Scenario Outline: Checking the Repport on Operating Statement page with different filters
    Given user navigates to Report Operating Performance page
    And user goes to '<report name>'
    And user can see reports
    And clears the existing filters
    When selects the filters as below
      | Currency      | <Currency>      |
      | Year          | <Year>          |
      | Sector        | <Sector>        |
      | Region        | <Region>        |
      | Asset Manager | <Asset Manager> |
      | Asset         | <Asset>         |
      | Asset Tags    | <Asset Tags>    |
      | Investment    | <Investment>    |
      | Quarter       | <Quarter>       |
      | Month         | <Month>         |
      | Area Measure  | <Area Measure>  |
      | Checkbox           | <Checkbox>           |
      | report name      | <report name>           |
    And export the '<report name>' report
    Then the report is exported successfully
    And the saved report should match with expected report with name '<export file name>'
@RentalArrear
  Examples: Operation Performance Report
    | report name                | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment | Quarter | Month     | Checkbox                    |export file name     |
    | Rental Arrears Report      | USD      | 2013 |  SM          |        |        |               |       |            | Camden REIT|  Q2-2013|           |                        | OPR_RentalArrear.xls |

Examples: OSO
    | report name                | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment | Quarter | Month     | Checkbox                    |export file name     |
    | Operating Statistics Overview | AUD   | 2013 |  SM          |        |        |               |       |            | Camden REIT|  Q2-2013| May/2013  |                        | OperatingStatistics.xls |
    | Building Unit Inventory    | GBP      | 2013 |  SF          |        |        |               |       |            | Camden REIT|  Q2-2013| May/2013  |                        | Building_Unit_Inventory.xls |

@Tenant
  Scenario Outline: Checking the Dashboard on Tenant page with different filters
    Given user navigates to Dashboard Tenant page
    And user can see reports
    And clears the existing filters
    When selects the filters as below
      | Currency      | <Currency>      |
      | Year          | <Year>          |
      | Sector        | <Sector>        |
      | Region        | <Region>        |
      | Asset Manager | <Asset Manager> |
      | Asset         | <Asset>         |
      | Asset Tags    | <Asset Tags>    |
      | Investment    | <Investment>    |
      | Quarter       | <Quarter>       |
      | Month         | <Month>         |
      | Area Measure  | <Area Measure>  |
      | Toggle        | <Toggle>           |
      | report name      | <report name>           |
    And export the '<report name>' report
    Then the report is exported successfully
    And the saved report should match with expected report with name '<export file name>'

    Examples: Dashboard Tenant
   Examples: Top 10 Tenants
    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment | Quarter | Month    | report name    | Toggle                | export file name          |
    | EUR      | 2013 |  SF          |        |        |               |       |            |            |         |          | Top 10 Tenants | Net Rental Income pcm | Net_Rental_Income_pcm.xls |
    | EUR      | 2013 |  SF          |        |        |               |       |            |            |         | May/2013 | Top 10 Tenants | Gross Rental Income pcm| Gross_Rental_Income_pcm.xls |
    | EUR      | 2013 |  SF          |        |        |               |       |            |            | Q2-2013 |          | Top 10 Tenants | Rental Arrears        | Rental_Arrears_Tenant.xls |
    | EUR      | 2013 |  SF          |        |        |               |       |            |            | Q2-2013 |          | Top 10 Tenants | Leased Area           | Leased_Area_Tenant.xls |
    | EUR      | 2013 |  SF          |        |        |               |       |            |            |         |          | Top 10 Tenants | Retail Sales          | Retail_Sales.xls |
    
     Examples: Rental Arrears as Of
    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment        | Quarter | Month    | report name                       | Toggle| export file name          |
    | EUR      | 2013 | SF           |        |        |               |       |            | Investor Two Ltd. | Q3-2013 |          | Arrears As Of - 30/09/2013 |       | Rental_Arrears_As_Of_Tenant.xls |

     Examples: Tenant Mix
    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment        | Quarter | Month    | report name| Toggle           | export file name  |
    | EUR      | 2013 | SF           |        |        |               |       |            | Investor Two Ltd. | Q3-2013 |          | Tenant Mix | Leased Area      | Leased_Area_TenantMix.xls |
    | EUR      | 2013 | SF           |        |        |               |       |            | Investor Two Ltd. | Q3-2013 |          | Tenant Mix | No. of Tenants   | No_of_Tenants_TenantMix.xls |
    | EUR      | 2013 | SF           |        |        |               |       |            |                   | Q3-2013 |          | Tenant Mix | Sales Volume     | Sales_Volume_TenantMix.xls |



  @Tenancy
  Scenario Outline: Checking the Report on Tenant page with different filters
    Given user navigates to Report Tenancy page
    And user goes to '<report name>'
    And user can see reports
    And clears the existing filters
    When selects the filters as below
      | Currency      | <Currency>      |
      | Year          | <Year>          |
      | Sector        | <Sector>        |
      | Region        | <Region>        |
      | Asset Manager | <Asset Manager> |
      | Asset         | <Asset>         |
      | Asset Tags    | <Asset Tags>    |
      | Investment    | <Investment>    |
      | Quarter       | <Quarter>       |
      | Month         | <Month>         |
      | Area Measure  | <Area Measure>  |
    And export the '<report name>' report
    Then the report is exported successfully
    And the saved report should match with expected report with name '<export file name>'

  Examples: Tenancy Schedule
    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment | Quarter | Month    | report name            | export file name          |
    | EUR      | 2013 |              |        |        |               |       |            |            |         |          | Tenancy Schedule       | Tenancy_Schedule.xls |
    | EUR      | 2013 |              |        |        |               |       |            |            |Q3-2013  |          | Rental Analysis Report | Rental_Analysis_Report.xls |


  @TenancyScheduleAll
  Scenario Outline: Checking the Report on Tenant page with different filters after selecting all values
    Given user navigates to Report Tenancy page
    And user goes to '<report name>'
    And user can see reports
    And clears the existing filters
    When selects the filters as below
      | Currency      | <Currency>      |
      | Year          | <Year>          |
      | Sector        | <Sector>        |
      | Region        | <Region>        |
      | Asset Manager | <Asset Manager> |
      | Asset         | <Asset>         |
      | Asset Tags    | <Asset Tags>    |
      | Investment    | <Investment>    |
      | Quarter       | <Quarter>       |
      | Month         | <Month>         |
      | Area Measure  | <Area Measure>  |
    And select all the values in report
    And export the '<report name>' report
    Then the report is exported successfully
    And the saved report should match with expected report with name '<export file name>'

  Examples: Tenancy Schedule
    | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment | Quarter | Month    | report name            | export file name          |
    | EUR      | 2013 |              |        |        |               |       |            | Camden REIT|         |          | Tenancy Schedule       | Tenancy_Schedule_All.xls |
    
    
    Scenario Outline: Checking the Lease expiring profil report
    Given user navigates to Report Operating Performance page
    And user goes to '<report name>'
    And user can see reports
    And clears the existing filters
    When selects the filters as below
      | Currency      | <Currency>      |
      | Year          | <Year>          |
      | Sector        | <Sector>        |
      | Region        | <Region>        |
      | Asset Manager | <Asset Manager> |
      | Asset         | <Asset>         |
      | Asset Tags    | <Asset Tags>    |
      | Investment    | <Investment>    |
      | Quarter       | <Quarter>       |
      | Month         | <Month>         |
      | Area Measure  | <Area Measure>  |
      | Checkbox           | <Checkbox>           |
      | report name      | <report name>           |
    And export the '<report name>' report
    Then the report is exported successfully
    And the saved report should match with expected report with name '<export file name>' using cell comparism

  Examples: Lease expiring profile report
    | report name                | Currency | Year | Area Measure | Sector | Region | Asset Manager | Asset | Asset Tags | Investment | Quarter | Month     | Checkbox                    |export file name     |
    | Lease Expiry Profile Report| EUR      | 2013 |  SM          |        |        |               |       |            | Camden REIT|  Q2-2013|           | Expiring               | ExpritingProfile_Expiring.xls    |
    | Lease Expiry Profile Report| EUR      | 2013 |  SM          |        |        |               |       |            | Camden REIT|         |           | Expiring & Terminating | ExpritingProfile_Terminating.xls |  