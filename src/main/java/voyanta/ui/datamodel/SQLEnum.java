package voyanta.ui.datamodel;

/**
 * Created by sriramangajala on 08/07/2014.
 */
public enum SQLEnum {



    ACCOUNT("SELECT \n" +
            "\t\tAccount.Active,\n" +
            "\t\tCAST(Account.AccountNumberReference AS CHAR) AS accountNumber,\n" +
            "\t\tCAST(Account.SubAccountNumberReference AS CHAR) AS subAccountNumber, \n" +
            "\t\tCAST(Account.ChartOfAccount AS CHAR) AS chartOfAccountsName, \n" +
            "\t\tCAST(dbolive.LookupVal(Account.AccountCategoryKey) AS CHAR) AS accountCategory, \n" +
            "\t\tCAST(Account.AccountDescription AS CHAR) AS accountDescription, \n" +
            "\t\tCAST(Account.AccountName AS CHAR) AS accountName, \n" +
            "\t\tCAST(dboliveLookupVal(Account.AccountSubcategoryKey) AS CHAR) AS subCategory, \n" +
            "\t\tCAST(Account.SubAccountName AS CHAR) AS subAccountName, \n" +
            "\t\tCAST(Account.SubAccountNumberDescription AS CHAR) AS subAccountDescription, \n" +
            "\t\tAccount.AccountKey AS objectId \n" +
            "FROM dbolive.Account \n" +
            "LEFT JOIN dbolive.Client Client_Account ON Client_Account.ClientKey = Account.ClientKey \n" +
            "WHERE Client_Account.ClientKey = Account.ClientKey AND Account.Active = 'Y' LIMIT 0,10",""),
    LEASE("SELECT \n" +
//            "Lease.lastUPdated,\n" +
            "Lease.Active as Active,\n" +
            "CAST(Provider_Lease.ProviderReference AS CHAR) AS providerReference, \n" +
            "CAST(Asset_Lease.AssetReference AS CHAR) AS buildingReference, \n" +
            "CAST(Lease.LeaseReference AS CHAR) AS leaseReference, \n" +
            "CAST(LegalEntity_Tenant.LegalEntityReference AS CHAR) AS tenantReference, \n" +
            "CAST(Lease.TenantDBAName AS CHAR) AS tenantDBAName,\n" +
            " CAST(Lease.LeaseCommencementDate AS CHAR) AS commencementDate, \n" +
            "CAST(Lease.TerminationDate AS CHAR) AS terminationDate, \n" +
            "CAST(Lease.LeaseExpirationDate AS CHAR) AS expirationDate, \n" +
            "CAST(Lease.LeaseExecutionDate AS CHAR) AS executionDate, \n" +
            "CAST(Lease.RenewalDate AS CHAR) AS renewalDate, \n" +
            "CAST(Lease.GuarantorName AS CHAR) AS guarantorName, \n" +
            "CAST(dbolive.LookupVal(Lease.LeaseClassificationKey) AS CHAR) AS leaseClassification, \n" +
            "CAST(dbolive.LookupVal(Lease.LeaseStatusKey) AS CHAR) AS leaseStatus, \n" +
            "CAST(dbolive.LookupVal(Lease.RetailActivityKey) AS CHAR) AS leaseUse, \n" +
            "CAST(dbolive.LookupVal(Lease.ContractTypeKey) AS CHAR) AS contractType, \n" +
            "CAST(LeasedArea_Lease.Area AS DECIMAL(18,5)) AS leasedArea, \n" +
            "CAST(Lease.LeaseCommissionInside AS DECIMAL(18,5)) AS leasingCommissionsInside, \n" +
            "CAST(Lease.LeaseCommissionOutside AS DECIMAL(18,5)) AS leasingCommissionsOutside,\n" +
            " CAST(MasterLease_Lease.LeaseReference AS CHAR) AS masterLeaseReference, \n" +
            "CAST(Lease.NetEffectiveRentActual AS DECIMAL(18,5)) AS netEffectiveRentActual, \n" +
            "CAST(Lease.ParkingSpaceCount AS SIGNED) AS parkingSpaceCount, \n" +
            "CAST(SecurityDeposit_Lease.DepositAmountCurrent AS DECIMAL(18,5)) AS securityDepositAmountCurrent, \n" +
            "CAST(dbolive.LookupVal(SecurityDeposit_Lease.SecurityDepositTypeKey) AS CHAR) AS securityDepositType, \n" +
            "CAST(Lease.VATOpted AS CHAR) AS vatOpted, \n" +
            "CAST(SecurityDeposit_Lease.DepositAmountContracted AS DECIMAL(18,5)) AS securityDepositAmountContracted\n" +
            "FROM dbolive.Lease \n" +
            "LEFT JOIN dbolive.Provider Provider_Lease ON Provider_Lease.ProviderKey = Lease.ProviderKey \n" +
            "LEFT JOIN dbolive.Client Client_Lease ON Client_Lease.ClientKey = Lease.ClientKey \n" +
            "LEFT JOIN dbolive.Asset Asset_Lease ON Asset_Lease.AssetKey = Lease.AssetKey \n" +
            "LEFT JOIN dbolive.LegalEntityTenant Tenant_Lease ON Tenant_Lease.LegalEntityKey = Lease.TenantKey \n" +
            "LEFT JOIN dbolive.LeaseArea LeasedArea_Lease ON LeasedArea_Lease.LeaseKey = Lease.LeaseKey \n" +
            "AND LeasedArea_Lease.AreaTypeKey = dbolive.LookupKey((SELECT Client.ClientID FROM \n" +
            "dbolive.Client WHERE Client.ClientKey = 767), \n" +
            "'AreaType', 'Leased') LEFT JOIN dbolive.Lease MasterLease_Lease ON MasterLease_Lease.LeaseKey = Lease.MasterLeaseKey \n" +
            "LEFT JOIN dbolive.SecurityDeposit SecurityDeposit_Lease ON SecurityDeposit_Lease.LeaseKey = Lease.LeaseKey \n" +
            "LEFT JOIN dbolive.LegalEntity LegalEntity_Tenant ON LegalEntity_Tenant.LegalEntityKey = Tenant_Lease.LegalEntityKey\n" +
            " WHERE Client_Lease.ClientKey = 767 order by Lease.lastUpdated desc","Lease.sql"),

    BUILDINGS("SELECT \n" +
            "CAST(Building.Active  AS CHAR) AS Active,\n" +
//            "CAST(Asset_Building.Active AS CHAR) AS AssetActive,\n" +
            "CAST(Provider_Asset.ProviderReference AS CHAR) AS providerReference, \n" +
            "CAST(Building.AssetReference AS CHAR) AS assetReference, \n" +
            "CAST(Asset_Building.BuildingName AS CHAR) AS buildingName, \n" +
            "CAST(dbolive.LookupVal(Asset_Building.AreaMeasurementUnitKey) AS CHAR) AS areaMeasurementUnit, \n" +
            "CAST(dbolive.LookupVal(Asset_Building.CurrencyKey) AS CHAR) AS currency, \n" +
            "CAST(GrossBuildingArea_Building.Area AS DECIMAL(18,5)) AS grossBuildingArea, \n" +
            "CAST(GrossLeasableArea_Building.Area AS DECIMAL(18,5)) AS grossLeasableArea, \n" +
            "CAST(NetLeasableArea_Building.Area AS DECIMAL(18,5)) AS netLeasableArea, \n" +
            "CAST(BuildableArea_Building.Area AS DECIMAL(18,5)) AS buildableArea, \n" +
            "CAST(Building.NumberOfFloors AS SIGNED) AS numberOfFloors, \n" +
            "CAST(Building.NumberOfUnits AS SIGNED) AS numberOfUnits, \n" +
            "CAST(Building.NumberOfParkingSpaces AS SIGNED) AS numberOfParkingSpaces, \n" +
            "CAST(ParkingArea_Building.Area AS DECIMAL(18,5)) AS parkingArea, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.PropertyFormOfTitleKey) AS CHAR) AS propertyFormOfTitle, \n" +
            "CAST(Building.ComplexName AS CHAR) AS complexName, \n" +
            "CAST(ParentBuilding_Building.AssetReference AS CHAR) AS buildingParentReference, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.BusinessUnitKey) AS CHAR) AS businessUnitName, \n" +
            "CAST(Address_BuildingAddress.StreetAddress1 AS CHAR) AS streetAddress1, \n" +
            "CAST(Address_BuildingAddress.StreetAddress2 AS CHAR) AS streetAddress2, \n" +
            "CAST(Address_BuildingAddress.City AS CHAR) AS city, \n" +
            "CAST(Address_BuildingAddress.StateProvince AS CHAR) AS state, \n" +
            "CAST(dbolive .LookupVal(Address_BuildingAddress.CountryKey) AS CHAR) AS country, \n" +
            "CAST(Address_BuildingAddress.PostalCode AS CHAR) AS postalCode, \n" +
            "CAST(dbolive .LookupVal(Building.BuildingConditionKey) AS CHAR) AS buildingCondition, \n" +
            "CAST(dbolive .LookupVal(Building.AssetQualityKey) AS CHAR) AS assetQuality, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.BenchmarkRegionKey) AS CHAR) AS benchmarkRegion, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.BenchmarkSubRegionKey) AS CHAR) AS benchmarkSubRegion, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.SectorKey) AS CHAR) AS sector, \n" +
            "CAST(dbolive .LookupVal(Building.BuildingUseKey) AS CHAR) AS buildingUse, \n" +
            "CAST(Asset_Building.AssetManager AS CHAR) AS assetManager, \n" +
            "CAST(Building.BuildingManager AS CHAR) AS buildingManager, \n" +
            "CAST(Asset_Building.PropertyManagementCompany AS CHAR) AS propertyManagementCompany, \n" +
            "CAST(Asset_Building.DateOfLastPropertyInspection AS CHAR) AS dateOfLastPropertyInspection, \n" +
            "CAST(Building.MarketCoreLossPercentage AS DECIMAL(18,5)) AS marketCoreLossFactorPercentage, \n" +
            "CAST(Building.LeasedPercentage AS DECIMAL(18,5)) AS leasedPercentage, \n" +
            "CAST(Asset_Building.ExternalGroundLease AS CHAR) AS externalGroundLease, \n" +
            "CAST(Building.YearBuilt AS SIGNED) AS yearBuilt, \n" +
            "CAST(Building.YearOfLastRenovation AS SIGNED) AS yearOfLastRenovation, \n" +
            "CAST(Asset_Building.InsuranceCoverageAmount AS DECIMAL(18,5)) AS insuranceCoverageAmount, \n" +
            "CAST(Asset_Building.InsurancePolicyExpiryDate AS CHAR) AS insurancePolicyExpiryDate, \n" +
            "CAST(Asset_Building.InsurancePolicyPremium AS DECIMAL(18,5)) AS insurancePolicyPremium, \n" +
            "CAST(dbolive .LookupVal(Building.CustomClassification1Key) AS CHAR) AS customClassification1, \n" +
            "CAST(dbolive .LookupVal(Building.CustomClassification2Key) AS CHAR) AS customClassification2, \n" +
            "CAST(dbolive .LookupVal(Building.CustomClassification3Key) AS CHAR) AS customClassification3, \n" +
            "CAST(dbolive .LookupVal(Building.CustomClassification4Key) AS CHAR) AS customClassification4, \n" +
            "CAST(dbolive .LookupVal(Building.CustomClassification5Key) AS CHAR) AS customClassification5, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.StatisticalDivisionKey) AS CHAR) AS statisticalDivision, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.RiskProfileKey) AS CHAR) AS riskProfile, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.AssetCustomClassification1Key) AS CHAR) AS assetCustomClassification1, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.AssetCustomClassification2Key) AS CHAR) AS assetCustomClassification2, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.AssetCustomClassification3Key) AS CHAR) AS assetCustomClassification3, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.AssetCustomClassification4Key) AS CHAR) AS assetCustomClassification4, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.AssetCustomClassification5Key) AS CHAR) AS assetCustomClassification5, \n" +
            "CAST(Asset_Building.Latitude AS DECIMAL(8,6)) AS latitude, \n" +
            "CAST(Asset_Building.Longitude AS DECIMAL(9,6)) AS longitude \n" +
            "FROM dbolive .Building \n" +
            "LEFT JOIN dbolive .Client Client_Building ON Client_Building.ClientKey = Building.ClientKey \n" +
            "LEFT JOIN dbolive .Asset Asset_Building ON Asset_Building.AssetKey = Building.AssetKey \n" +
            "LEFT JOIN dbolive .Provider Provider_Asset ON Provider_Asset.ProviderKey = Asset_Building.ProviderKey \n" +
            "LEFT JOIN dbolive .BuildingArea GrossBuildingArea_Building \n" +
            "ON GrossBuildingArea_Building.BuildingKey = Building.BuildingKey \n" +
            "AND GrossBuildingArea_Building.AreaTypeKey = dbolive .LookupKey\n" +
            "((SELECT Client.ClientID FROM dbolive .Client WHERE Client.ClientKey = 767), 'AreaType', 'GrossBuilding') \n" +
            "LEFT JOIN dbolive .BuildingArea GrossLeasableArea_Building ON GrossLeasableArea_Building.BuildingKey = Building.BuildingKey \n" +
            "AND GrossLeasableArea_Building.AreaTypeKey = dbolive .LookupKey((SELECT Client.ClientID FROM dbolive .Client \n" +
            "WHERE Client.ClientKey = 767), 'AreaType', 'GrossLeasable') \n" +
            "LEFT JOIN dbolive .BuildingArea NetLeasableArea_Building ON NetLeasableArea_Building.BuildingKey = Building.BuildingKey \n" +
            "AND NetLeasableArea_Building.AreaTypeKey = dbolive .LookupKey((SELECT Client.ClientID FROM dbolive .Client \n" +
            "WHERE Client.ClientKey = 767), 'AreaType', 'NetLeasable') \n" +
            "LEFT JOIN dbolive .BuildingArea BuildableArea_Building ON BuildableArea_Building.BuildingKey = Building.BuildingKey\n" +
            " AND BuildableArea_Building.AreaTypeKey = dbolive .LookupKey\n" +
            "((SELECT Client.ClientID FROM dbolive .Client WHERE \n" +
            "Client.ClientKey = 767), 'AreaType', 'Buildable') LEFT JOIN dbolive .BuildingArea ParkingArea_Building ON \n" +
            "ParkingArea_Building.BuildingKey = Building.BuildingKey AND ParkingArea_Building.AreaTypeKey = dbolive .LookupKey\n" +
            "((SELECT Client.ClientID FROM dbolive .Client WHERE Client.ClientKey = 767), 'AreaType', 'Parking')\n" +
            " LEFT JOIN dbolive .Building ParentBuilding_Building ON ParentBuilding_Building.BuildingKey = Building.ParentBuildingKey \n" +
            "LEFT JOIN dbolive .BuildingAddress BuildingAddress_Building ON BuildingAddress_Building.BuildingKey = Building.BuildingKey \n" +
            "LEFT JOIN dbolive .Address Address_BuildingAddress ON Address_BuildingAddress.AddressKey = BuildingAddress_Building.AddressKey \n" +
            "WHERE Client_Building.ClientKey = 767 order by assetReference,Building.lastUpdated desc","Building.sql"),
    BUILDINGS1("SELECT \n" +
            "CAST(Building.Active  AS CHAR) AS Active,\n" +
            "CAST(Asset_Building.Active AS CHAR) AS AssetActive,\n" +
            "CAST(Provider_Asset.ProviderReference AS CHAR) AS providerReference, \n" +
            "CAST(Building.AssetReference AS CHAR) AS assetReference, \n" +
            "CAST(Asset_Building.BuildingName AS CHAR) AS buildingName, \n" +
            "CAST(dbolive.LookupVal(Asset_Building.AreaMeasurementUnitKey) AS CHAR) AS areaMeasurementUnit, \n" +
            "CAST(dbolive.LookupVal(Asset_Building.CurrencyKey) AS CHAR) AS currency, \n" +
            "CAST(GrossBuildingArea_Building.Area AS DECIMAL(18,5)) AS grossBuildingArea, \n" +
            "CAST(GrossLeasableArea_Building.Area AS DECIMAL(18,5)) AS grossLeasableArea, \n" +
            "CAST(NetLeasableArea_Building.Area AS DECIMAL(18,5)) AS netLeasableArea, \n" +
            "CAST(BuildableArea_Building.Area AS DECIMAL(18,5)) AS buildableArea, \n" +
            "CAST(Building.NumberOfFloors AS SIGNED) AS numberOfFloors, \n" +
            "CAST(Building.NumberOfUnits AS SIGNED) AS numberOfUnits, \n" +
            "CAST(Building.NumberOfParkingSpaces AS SIGNED) AS numberOfParkingSpaces, \n" +
            "CAST(ParkingArea_Building.Area AS DECIMAL(18,5)) AS parkingArea, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.PropertyFormOfTitleKey) AS CHAR) AS propertyFormOfTitle, \n" +
            "CAST(Building.ComplexName AS CHAR) AS complexName, \n" +
            "CAST(ParentBuilding_Building.AssetReference AS CHAR) AS buildingParentReference, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.BusinessUnitKey) AS CHAR) AS businessUnitName, \n" +
            "CAST(Address_BuildingAddress.StreetAddress1 AS CHAR) AS streetAddress1, \n" +
            "CAST(Address_BuildingAddress.StreetAddress2 AS CHAR) AS streetAddress2, \n" +
            "CAST(Address_BuildingAddress.City AS CHAR) AS city, \n" +
            "CAST(Address_BuildingAddress.StateProvince AS CHAR) AS state, \n" +
            "CAST(dbolive .LookupVal(Address_BuildingAddress.CountryKey) AS CHAR) AS country, \n" +
            "CAST(Address_BuildingAddress.PostalCode AS CHAR) AS postalCode, \n" +
            "CAST(dbolive .LookupVal(Building.BuildingConditionKey) AS CHAR) AS buildingCondition, \n" +
            "CAST(dbolive .LookupVal(Building.AssetQualityKey) AS CHAR) AS assetQuality, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.BenchmarkRegionKey) AS CHAR) AS benchmarkRegion, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.BenchmarkSubRegionKey) AS CHAR) AS benchmarkSubRegion, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.SectorKey) AS CHAR) AS sector, \n" +
            "CAST(dbolive .LookupVal(Building.BuildingUseKey) AS CHAR) AS buildingUse, \n" +
            "CAST(Asset_Building.AssetManager AS CHAR) AS assetManager, \n" +
            "CAST(Building.BuildingManager AS CHAR) AS buildingManager, \n" +
            "CAST(Asset_Building.PropertyManagementCompany AS CHAR) AS propertyManagementCompany, \n" +
            "CAST(Asset_Building.DateOfLastPropertyInspection AS CHAR) AS dateOfLastPropertyInspection, \n" +
            "CAST(Building.MarketCoreLossPercentage AS DECIMAL(18,5)) AS marketCoreLossFactorPercentage, \n" +
            "CAST(Building.LeasedPercentage AS DECIMAL(18,5)) AS leasedPercentage, \n" +
            "CAST(Asset_Building.ExternalGroundLease AS CHAR) AS externalGroundLease, \n" +
            "CAST(Building.YearBuilt AS SIGNED) AS yearBuilt, \n" +
            "CAST(Building.YearOfLastRenovation AS SIGNED) AS yearOfLastRenovation, \n" +
            "CAST(Asset_Building.InsuranceCoverageAmount AS DECIMAL(18,5)) AS insuranceCoverageAmount, \n" +
            "CAST(Asset_Building.InsurancePolicyExpiryDate AS CHAR) AS insurancePolicyExpiryDate, \n" +
            "CAST(Asset_Building.InsurancePolicyPremium AS DECIMAL(18,5)) AS insurancePolicyPremium, \n" +
            "CAST(dbolive .LookupVal(Building.CustomClassification1Key) AS CHAR) AS customClassification1, \n" +
            "CAST(dbolive .LookupVal(Building.CustomClassification2Key) AS CHAR) AS customClassification2, \n" +
            "CAST(dbolive .LookupVal(Building.CustomClassification3Key) AS CHAR) AS customClassification3, \n" +
            "CAST(dbolive .LookupVal(Building.CustomClassification4Key) AS CHAR) AS customClassification4, \n" +
            "CAST(dbolive .LookupVal(Building.CustomClassification5Key) AS CHAR) AS customClassification5, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.StatisticalDivisionKey) AS CHAR) AS statisticalDivision, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.RiskProfileKey) AS CHAR) AS riskProfile, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.AssetCustomClassification1Key) AS CHAR) AS assetCustomClassification1, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.AssetCustomClassification2Key) AS CHAR) AS assetCustomClassification2, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.AssetCustomClassification3Key) AS CHAR) AS assetCustomClassification3, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.AssetCustomClassification4Key) AS CHAR) AS assetCustomClassification4, \n" +
            "CAST(dbolive .LookupVal(Asset_Building.AssetCustomClassification5Key) AS CHAR) AS assetCustomClassification5, \n" +
            "CAST(Asset_Building.Latitude AS DECIMAL(8,6)) AS latitude, \n" +
            "CAST(Asset_Building.Longitude AS DECIMAL(9,6)) AS longitude \n" +
            "FROM dbolive .Building \n" +
            "LEFT JOIN dbolive .Client Client_Building ON Client_Building.ClientKey = Building.ClientKey \n" +
            "LEFT JOIN dbolive .Asset Asset_Building ON Asset_Building.AssetKey = Building.AssetKey \n" +
            "LEFT JOIN dbolive .Provider Provider_Asset ON Provider_Asset.ProviderKey = Asset_Building.ProviderKey \n" +
            "LEFT JOIN dbolive .BuildingArea GrossBuildingArea_Building \n" +
            "ON GrossBuildingArea_Building.BuildingKey = Building.BuildingKey \n" +
            "AND GrossBuildingArea_Building.AreaTypeKey = dbolive .LookupKey\n" +
            "((SELECT Client.ClientID FROM dbolive .Client WHERE Client.ClientKey = 767), 'AreaType', 'GrossBuilding') \n" +
            "LEFT JOIN dbolive .BuildingArea GrossLeasableArea_Building ON GrossLeasableArea_Building.BuildingKey = Building.BuildingKey \n" +
            "AND GrossLeasableArea_Building.AreaTypeKey = dbolive .LookupKey((SELECT Client.ClientID FROM dbolive .Client \n" +
            "WHERE Client.ClientKey = 767), 'AreaType', 'GrossLeasable') \n" +
            "LEFT JOIN dbolive .BuildingArea NetLeasableArea_Building ON NetLeasableArea_Building.BuildingKey = Building.BuildingKey \n" +
            "AND NetLeasableArea_Building.AreaTypeKey = dbolive .LookupKey((SELECT Client.ClientID FROM dbolive .Client \n" +
            "WHERE Client.ClientKey = 767), 'AreaType', 'NetLeasable') \n" +
            "LEFT JOIN dbolive .BuildingArea BuildableArea_Building ON BuildableArea_Building.BuildingKey = Building.BuildingKey\n" +
            " AND BuildableArea_Building.AreaTypeKey = dbolive .LookupKey\n" +
            "((SELECT Client.ClientID FROM dbolive .Client WHERE \n" +
            "Client.ClientKey = 767), 'AreaType', 'Buildable') LEFT JOIN dbolive .BuildingArea ParkingArea_Building ON \n" +
            "ParkingArea_Building.BuildingKey = Building.BuildingKey AND ParkingArea_Building.AreaTypeKey = dbolive .LookupKey\n" +
            "((SELECT Client.ClientID FROM dbolive .Client WHERE Client.ClientKey = 767), 'AreaType', 'Parking')\n" +
            " LEFT JOIN dbolive .Building ParentBuilding_Building ON ParentBuilding_Building.BuildingKey = Building.ParentBuildingKey \n" +
            "LEFT JOIN dbolive .BuildingAddress BuildingAddress_Building ON BuildingAddress_Building.BuildingKey = Building.BuildingKey \n" +
            "LEFT JOIN dbolive .Address Address_BuildingAddress ON Address_BuildingAddress.AddressKey = BuildingAddress_Building.AddressKey \n" +
            "WHERE Client_Building.ClientKey = 767 order by assetReference,Building.lastUpdated desc","Building1.sql");


    private String SQL;



    private String sqlFileName;

    private SQLEnum(String SQL,String sqlFileName)
    {
        this.SQL = SQL;
        this.sqlFileName = sqlFileName;
    }

    public String getSQL()
    {
        return SQL;
    }

    public String getSqlFileName() {
        return sqlFileName;
    }

    public static SQLEnum getEnum(String value) {
        for (SQLEnum re : SQLEnum.values()) {
            if (re.getSqlFileName().compareToIgnoreCase(value) == 0) {
                return re;
            }
        }
        throw new IllegalArgumentException("Invalid RandomEnum value: " + value);
    }




}
