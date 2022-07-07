//package org.muks.redis.jedis.work.rollup;
//
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
//public enum CspCapabilities {
//
//  BOX(4080, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.SUPPORTED),
//  DRIVE(2483, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.SUPPORTED),
//  FORCE(2941, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.SUPPORTED),
//  ONEDRIVE(3210, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.SUPPORTED),
//  SHAREPOINT(16131, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.SUPPORTED),
//  DROPBOX(12672, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED),
//  EXCHANGEONLINE(2799, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  SHAREFILE(7008, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED),
//  SLACK(10692, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED),
//  AWS(2049, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED),
//  WEBEXTEAMS(16121, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED),
//  EGNYTE(4191, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED),
//  S3(2048, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED),
//  AZURE(4366, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED),
//  GMAIL(2467, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  WORKPLACE(17411, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED),
//  GITHUB(21423, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED),
//  OKTA(4404, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  JIRA(12802, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED),
//  BITBUCKET(2134, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED),
//  SMARTSHEET(3007, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED),
//  WORKDAY(3669, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED),
//  ONELOGIN(3427, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  ZENDESK(3258, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  INTRALINKS(18057, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED),
//  CENTRIFY(10934, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  JIVE(2586, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED),
//  GCP(13465, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED),
//  MICROSOFTTEAMS(25680, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED),
//  CLARIZEN(2207, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED),
//  SERVICENOW(CloudServiceProvider.SERVICENOW.getDbId(), DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED),
//  INTROHIVE(
//      CloudServiceProvider.INTROHIVE.getDbId(), DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED),
//  ATLASSIANACCESS(CloudServiceProvider.ATLASSIANACCESS.getDbId(), DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  DOCUSIGN(
//      CloudServiceProvider.DOCUSIGN.getDbId(), DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  FILECLOUD(
//      CloudServiceProvider.FILECLOUD.getDbId(), DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  CONFLUENCE(CloudServiceProvider.CONFLUENCE.getDbId(), DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  DOMO(
//      CloudServiceProvider.DOMO.getDbId(), DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  DYNAMICS(
//      CloudServiceProvider.DYNAMICS.getDbId(), DlpStatus.SUPPORTED, DlpStatus.SUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED,
//      DlpStatus.UNSUPPORTED),
//  ZOOM(
//      CloudServiceProvider.ZOOM.getDbId(), DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  ASANA(
//      CloudServiceProvider.ASANA.getDbId(), DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  ARIBA(
//      CloudServiceProvider.ARIBA.getDbId(), DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  AXWAY(
//      CloudServiceProvider.AXWAY.getDbId(), DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  YAMMER(
//      CloudServiceProvider.YAMMER.getDbId(), DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  SAPS4HANA(CloudServiceProvider.SAPS4HANA.getDbId(), DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  MARKETO(
//      CloudServiceProvider.MARKETO.getDbId(), DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  ORACLEFINANCIALS(CloudServiceProvider.ORACLEFINANCIALS.getDbId(), DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  POWERBI(
//      CloudServiceProvider.POWERBI.getDbId(), DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  ADOBESIGN(
//      CloudServiceProvider.ADOBESIGN.getDbId(), DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  VMWARE_VCENTER(CloudServiceProvider.VMWARE_VCENTER.getDbId(), DlpStatus.UNSUPPORTED,
//      DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED),
//  AIRTABLE(
//      CloudServiceProvider.AIRTABLE.getDbId(), DlpStatus.SUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED,
//      DlpStatus.UNSUPPORTED, DlpStatus.UNSUPPORTED)
//  ;
//
//  private static final List<CloudServiceProvider> ODS_SUPPORTED_CSPS =
//      Arrays.stream(CspCapabilities.values())
//          .filter(CspCapabilities::isOdsCapabilitySupported)
//          .map(CspCapabilities::getCspId)
//          .map(CloudServiceProvider::fromDbId)
//          .collect(Collectors.toList());
//
//  private final int cspId;
//  private final DlpStatus realTimeDlpCapability;
//  private final DlpStatus ODSCapability;
//  private final DlpStatus activityMonitoring;
//  private final DlpStatus emailDlpCapability;
//  private final DlpStatus malwareScanCapability;
//  private final DlpStatus realTimeMalwareCapability;
//  private final DlpStatus dataClassificationCapability;
//  private final DlpStatus containerScanCapability;
//
//  CspCapabilities(
//      int cspId,
//      DlpStatus realTimeDlpCapability,
//      DlpStatus ODSCapability,
//      DlpStatus activityMonitorig,
//      DlpStatus emailDlpCapability,
//      DlpStatus malwareScanCapability,
//      DlpStatus realTimeMalwareCapability,
//      DlpStatus dataClassificationCapability,
//      DlpStatus containerScanCapability) {
//    this.cspId = cspId;
//    this.realTimeDlpCapability = realTimeDlpCapability;
//    this.ODSCapability = ODSCapability;
//    this.activityMonitoring = activityMonitorig;
//    this.emailDlpCapability = emailDlpCapability;
//    this.malwareScanCapability = malwareScanCapability;
//    this.realTimeMalwareCapability = realTimeMalwareCapability;
//    this.dataClassificationCapability = dataClassificationCapability;
//    this.containerScanCapability = containerScanCapability;
//  }
//
//  CspCapabilities(
//      int cspId,
//      DlpStatus realTimeDlpCapability,
//      DlpStatus ODSCapability,
//      DlpStatus activityMonitorig,
//      DlpStatus emailDlpCapability,
//      DlpStatus malwareScanCapability,
//      DlpStatus realTimeMalwareCapability,
//      DlpStatus dataClassificationCapability) {
//    this(
//        cspId,
//        realTimeDlpCapability,
//        ODSCapability,
//        activityMonitorig,
//        emailDlpCapability,
//        malwareScanCapability,
//        realTimeMalwareCapability,
//        dataClassificationCapability,
//        DlpStatus.UNSUPPORTED);
//  }
//
//  public static CspCapabilities fromCspId(int cspId) {
//    for (CspCapabilities cspCapability : CspCapabilities
//        .values()) {
//      if (cspCapability.cspId == cspId) {
//        return cspCapability;
//      }
//    }
//    return null;
//  }
//
//  public DlpStatus getRealTimeDlpCapability() {
//    return realTimeDlpCapability;
//  }
//
//  public int getCspId() {
//    return cspId;
//  }
//
//  public DlpStatus getODSCapability() {
//    return ODSCapability;
//  }
//
//  public DlpStatus getActivityMonitoring() {
//    return activityMonitoring;
//  }
//
//  public DlpStatus getEmailDlpCapability() {
//    return emailDlpCapability;
//  }
//
//  public DlpStatus getMalwareScanCapability() {
//    return malwareScanCapability;
//  }
//
//  public DlpStatus getContainerScanCapability() {
//    return containerScanCapability;
//  }
//
//  public DlpStatus getRealTimeMalwareCapability() {
//    return realTimeMalwareCapability;
//  }
//
//  public DlpStatus getDataClassificationCapability() {
//    return dataClassificationCapability;
//  }
//
//  public boolean isOdsCapabilitySupported(){
//    return ODSCapability == DlpStatus.SUPPORTED;
//  }
//
//  public static List<CloudServiceProvider> getCspsWithOdsCapability(){
//    return ODS_SUPPORTED_CSPS;
//  }
//
//}
//
//
