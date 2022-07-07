//package org.muks.redis.jedis.work.rollup;
//
//
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * @author snehal
// * @author ankit
// * @author vishnu
// */
//public enum CloudServiceProvider {
//
//  BOX("Box", "Box", 4080, ServiceType.COLLABORATION_SAAS),
//  DRIVE("Drive", "Google Drive", 2483, ServiceType.COLLABORATION_SAAS),
//  FORCE("Force", "Salesforce", 2941, ServiceType.STRUCTURED_SAAS),
//  ONEDRIVE("OneDrive", "OneDrive", 3210, ServiceType.COLLABORATION_SAAS),
//  SHAREPOINT("SharePoint", "SharePoint", 16131, ServiceType.COLLABORATION_SAAS),
//  DROPBOX("Dropbox", "Dropbox for Business", 12672, ServiceType.CASB_CONNECT),
//  EXCHANGEONLINE("ExchangeOnline", "Microsoft Exchange Online", 2799, ServiceType.EMAIL),
//  SHAREFILE("ShareFile", "ShareFile", 7008, ServiceType.CASB_CONNECT),
//  SLACK("Slack", "Slack", 10692, ServiceType.MESSAGING),
//  AWS("Aws", "Amazon Web Services", 2049, ServiceType.IAAS, false),
//  EGNYTE("Egnyte", "Egnyte", 4191, ServiceType.CASB_CONNECT),
//  WEBEXTEAMS("Cisco Webex Teams", "Cisco Webex Teams", 16121, ServiceType.CASB_CONNECT),
//  MICROSOFTTEAMS("MicrosoftTeams", "Microsoft Teams", 25680, ServiceType.MESSAGING),
//  S3("Amazon S3", "Amazon S3", 2048, ServiceType.IAAS, false),
//  AZURE("Azure", "Microsoft Azure", 4366, ServiceType.IAAS, "skyhigh-for-azure"),
//  GMAIL("Gmail", "Gmail", 2467, ServiceType.EMAIL),
//  WORKPLACE("WorkPlace", "Workplace", 17411, ServiceType.CASB_CONNECT),
//  GITHUB("GitHub", "GitHub For Business", 21423, ServiceType.CASB_CONNECT),
//  OKTA("Okta", "Okta", 4404, ServiceType.CASB_CONNECT),
//  JIRA("Jira", "Atlassian JIRA", 12802, ServiceType.CASB_CONNECT),
//  BITBUCKET("Bitbucket", "Bitbucket", 2134, ServiceType.CASB_CONNECT),
//  SMARTSHEET("Smartsheet", "Smartsheet", 3007, ServiceType.CASB_CONNECT),
//  WORKDAY("Workday", "Workday", 3669, ServiceType.CASB_CONNECT),
//  ONELOGIN("OneLogin", "OneLogin", 3427, ServiceType.CASB_CONNECT),
//  ZENDESK("Zendesk", "Zendesk", 3258, ServiceType.CASB_CONNECT),
//  INTRALINKS("Intralinks", "Intralinks", 18057, ServiceType.CASB_CONNECT),
//  CENTRIFY("Centrify", "Centrify", 10934, ServiceType.CASB_CONNECT),
//  JIVE("Jive", "Jive", 2586, ServiceType.CASB_CONNECT),
//  GCP("Gcp", "Google Cloud Platform", 13465, ServiceType.IAAS, false, "skyhigh-for-gcp"),
//  CLARIZEN("Clarizen", "Clarizen", 2207, ServiceType.CASB_CONNECT),
//  SERVICENOW("ServiceNow", "Service Now", 3487, ServiceType.STRUCTURED_SAAS),
//  INTROHIVE("IntroHive", "IntroHive", 28111, ServiceType.CASB_CONNECT),
//  ATLASSIANACCESS("AtlassianAccess", "Atlassian Access", 35040, ServiceType.CASB_CONNECT),
//  DOCUSIGN("DocuSign", "DocuSign", 2308, ServiceType.CASB_CONNECT),
//  FILECLOUD("FileCloud", "FileCloud", 17406, ServiceType.CASB_CONNECT),
//  CONFLUENCE("Confluence", "Atlassian Confluence", 12800, ServiceType.CASB_CONNECT),
//  DOMO("Domo", "Domo", 7154, ServiceType.CASB_CONNECT, false),
//  DYNAMICS("Dynamics", "Microsoft Dynamics 365", 3545, ServiceType.STRUCTURED_SAAS),
//  ZOOM("Zoom", "Zoom", 16000, ServiceType.MESSAGING),
//  AIRTABLE("Airtable", "Airtable", 24718, ServiceType.CASB_CONNECT,false),
//  /* ARIBA, ASANA, AXWAY, YAMMER, SAPS4HANA, MARKETO, ORACLEFINANCIAL : these csps will not have
//   any cloudservice implementations, it is only for displaying them in the service management page
//   */
//  ARIBA("Ariba", "Ariba", 3695, ServiceType.CASB_CONNECT),
//  ASANA("Asana", "Asana", 2075, ServiceType.CASB_CONNECT),
//  AXWAY("Axway", "Axway", 12953, ServiceType.CASB_CONNECT),
//  YAMMER("Yammer", "Yammer", 3245, ServiceType.CASB_CONNECT),
//  SAPS4HANA("SAPS4HANA", "SAP S/4HANA", 32666, ServiceType.CASB_CONNECT),
//  MARKETO("Marketo", "Marketo", 2672, ServiceType.CASB_CONNECT),
//  ORACLEFINANCIALS("OracleFinancials", "Oracle Financials Cloud", 13434, ServiceType.CASB_CONNECT),
//  POWERBI("PowerBI", "Microsoft - Power BI", 19050, ServiceType.CASB_CONNECT),
//  ADOBESIGN("AdobeSign", "Adobe Sign", 2337, ServiceType.CASB_CONNECT),
//  VMWARE_VCENTER("VMware vCenter","VMware vCenter",38412, ServiceType.IAAS,false)
//  ;
//
//  enum ServiceType {
//    COLLABORATION_SAAS,
//    STRUCTURED_SAAS,
//    IAAS,
//    CASB_CONNECT,
//    EMAIL,
//    MESSAGING
//  }
//
//  private final String id;
//  private final String displayName;
//  private final Integer dbId;
//  private final ServiceType serviceType;
//  private final boolean supportsOAuth;
//  private final String launchDarklyFeatureFlag;
//
//  public static final List<Integer> IAAS_CSPS =
//      Arrays.stream(
//              CloudServiceProvider.values()).filter(p -> p.serviceType == ServiceType.IAAS)
//          .map(CloudServiceProvider::getDbId).collect(Collectors.toList());
//
//  public static final List<Integer> EMAIL_CSPS =
//      Arrays.stream(
//              CloudServiceProvider.values()).filter(p -> p.serviceType == ServiceType.EMAIL)
//          .map(CloudServiceProvider::getDbId).collect(Collectors.toList());
//
//  public static final List<Integer> STRUCTURED_CSPS =
//      Arrays.stream(CloudServiceProvider.values()).filter(
//              p -> p.serviceType == ServiceType.STRUCTURED_SAAS).map(
//              CloudServiceProvider::getDbId)
//          .collect(Collectors.toList());
//
//  public static final List<Integer> MESSAGE_CSPS =
//      Arrays.stream(CloudServiceProvider.values()).filter(cloudServiceProvider -> cloudServiceProvider.serviceType == ServiceType.MESSAGING)
//          .map(CloudServiceProvider::getDbId).collect(
//              Collectors.toList());
//
//
//  public static final List<Integer> CASB_CONNECT_CSPS =
//      Arrays.stream(CloudServiceProvider.values()).filter(
//              p -> p.serviceType == ServiceType.CASB_CONNECT).map(
//              CloudServiceProvider::getDbId)
//          .collect(Collectors.toList());
//
//  public static final Set<CloudServiceProvider> NON_OAUTH_V2_CSPS =
//      Arrays.stream(CloudServiceProvider.values()).filter(
//          p -> p.supportsOAuth == false).collect(Collectors.toSet());
//
//  CloudServiceProvider(String id, String displayName, Integer dbId, ServiceType serviceType) {
//    this(id, displayName, dbId, serviceType, true, null);
//  }
//
//  CloudServiceProvider(String id, String displayName, Integer dbId, ServiceType serviceType,
//      String launchDarklyFeatureFlag) {
//    this(id, displayName, dbId, serviceType, true, launchDarklyFeatureFlag);
//  }
//
//  CloudServiceProvider(String id, String displayName, Integer dbId, ServiceType serviceType,
//      boolean supportsOAuth) {
//    this(id, displayName, dbId, serviceType, supportsOAuth, null);
//  }
//
//  CloudServiceProvider(String id, String displayName, Integer dbId, ServiceType serviceType,
//      boolean supportsOAuth, String launchDarklyFeatureFlag) {
//    this.id = id;
//    this.displayName = displayName;
//    this.dbId = dbId;
//    this.serviceType = serviceType;
//    this.supportsOAuth = supportsOAuth;
//    this.launchDarklyFeatureFlag = launchDarklyFeatureFlag;
//  }
//
//  public String getId() {
//    return id;
//  }
//
//  public Integer getDbId() {
//    return dbId;
//  }
//
//  public String getDisplayName() {
//    return displayName;
//  }
//
//  public String getLaunchDarklyFeatureFlag() {
//    return launchDarklyFeatureFlag;
//  }
//
//  public static List<Integer> getCspIds() {
//    CloudServiceProvider[] cloudServiceProviders = CloudServiceProvider.values();
//    List<Integer> cspIds = new ArrayList<>();
//    for (CloudServiceProvider cloudServiceProvider : cloudServiceProviders) {
//      cspIds.add(cloudServiceProvider.getDbId());
//    }
//    return cspIds;
//  }
//
//  public static List<Integer> getSpecCspIds() {
//    return CASB_CONNECT_CSPS;
//  }
//
//  public static boolean isSpecCsp(CloudServiceProvider cloudServiceProvider) {
//    return CASB_CONNECT_CSPS.contains(cloudServiceProvider.getDbId());
//  }
//
//  public static boolean isSpecCsp(Integer cspId) {
//    return CASB_CONNECT_CSPS.contains(cspId);
//  }
//
//  public static Set<CloudServiceProvider> getNonOAuthV2FlowCsps() {
//    return NON_OAUTH_V2_CSPS;
//  }
//
//  public static Boolean isNonOAuthV2FlowCsp(Integer cspId) {
//    return getNonOAuthV2FlowCsps().contains(CloudServiceProvider.fromDbId(cspId));
//  }
//
//  public static CloudServiceProvider fromId(String id) {
//    for (CloudServiceProvider provider : CloudServiceProvider.values()) {
//      if (provider.getId().toLowerCase().equals(id.toLowerCase())) {
//        return provider;
//      }
//    }
//    throw new IllegalArgumentException("Invalid id=" + id + " for CloudServiceProvider");
//  }
//
//  public static CloudServiceProvider fromDbId(Integer dbId) {
//    for (CloudServiceProvider provider : CloudServiceProvider.values()) {
//      if (provider.getDbId().equals(dbId)) {
//        return provider;
//      }
//    }
//    throw new IllegalArgumentException("Invalid dbId=" + dbId + " for CloudServiceProvider");
//  }
//
//  public static CloudServiceProvider fromDisplayName(String displayName) {
//    for (CloudServiceProvider provider : CloudServiceProvider.values()) {
//      if (provider.getDisplayName().equalsIgnoreCase(displayName)) {
//        return provider;
//      }
//    }
//    throw new IllegalArgumentException(String.format("Invalid display name= %s for CloudServiceProvider", displayName));
//  }
//
//  public static Set<CloudServiceProvider> getCspsWithDlpCapability(){
//    Set<CloudServiceProvider> dlpCsps = new HashSet<>();
//    for(CloudServiceProvider csp : CloudServiceProvider.values()){
//      CspCapabilities capabilities = CspCapabilities.fromCspId(csp.getDbId());
//      if(capabilities != null &&
//          (capabilities.getRealTimeDlpCapability() == DlpStatus.SUPPORTED
//              || capabilities.getActivityMonitoring() == DlpStatus.SUPPORTED
//              || capabilities.getODSCapability() == DlpStatus.SUPPORTED
//              || capabilities.getEmailDlpCapability() == DlpStatus.SUPPORTED)){
//        dlpCsps.add(csp);
//      }
//    }
//    return dlpCsps;
//  }
//
//  public static boolean isStructuredCsp(int cspId) {
//    return STRUCTURED_CSPS.contains(cspId);
//  }
//
//  public static boolean isMessageCsp(int cspId) {
//    return MESSAGE_CSPS.contains(cspId);
//  }
//
//  public static boolean isEmailCsp(int cspId) {
//    return EMAIL_CSPS.contains(cspId);
//  }
//}
