package org.muks.redis.jedis.work.license;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MsftTeamsSubscribedSkusHttp {
  private final String authToken = "Bearer eyJ0eXAiOiJKV1QiLCJub25jZSI6IjdxYWdvcUQySW5XMk9sdG0wakdadzBnOFVWUERoUnlmUEc1X3V2MjYwWmMiLCJhbGciOiJSUzI1NiIsIng1dCI6IjJaUXBKM1VwYmpBWVhZR2FYRUpsOGxWMFRPSSIsImtpZCI6IjJaUXBKM1VwYmpBWVhZR2FYRUpsOGxWMFRPSSJ9.eyJhdWQiOiJodHRwczovL2dyYXBoLm1pY3Jvc29mdC5jb20iLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC81OTNhZWE3Zi00ZmJmLTQyZmUtOTNlZi03YWMxMTQ4NDhmNjYvIiwiaWF0IjoxNjU1ODk3NDI1LCJuYmYiOjE2NTU4OTc0MjUsImV4cCI6MTY1NTkwMTMyNSwiYWlvIjoiRTJaZ1lIQ2UwMVVoNUpmcnEzejJTOVB4bXhhYkFBPT0iLCJhcHBfZGlzcGxheW5hbWUiOiJNY0FmZWUgVGVhbXMgQXBwIiwiYXBwaWQiOiI3Y2ZkMWM3Ni04YmM4LTQ3NTgtOTAyZi01YWFkNDgzMjE1NGIiLCJhcHBpZGFjciI6IjIiLCJpZHAiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC81OTNhZWE3Zi00ZmJmLTQyZmUtOTNlZi03YWMxMTQ4NDhmNjYvIiwiaWR0eXAiOiJhcHAiLCJvaWQiOiI4OGE2YTI5Zi1mN2U2LTQ4YTMtOWZkNy1jNThiOGEyZGEyODgiLCJyaCI6IjAuQVNrQWYtbzZXYjlQX2tLVDczckJGSVNQWmdNQUFBQUFBQUFBd0FBQUFBQUFBQUFwQUFBLiIsInJvbGVzIjpbIkNoYXQuVXBkYXRlUG9saWN5VmlvbGF0aW9uLkFsbCIsIkNoYXRNZW1iZXIuUmVhZFdyaXRlLkFsbCIsIkRpcmVjdG9yeS5SZWFkLkFsbCIsIlRlYW1NZW1iZXIuUmVhZFdyaXRlLkFsbCIsIkNoYXQuUmVhZC5BbGwiLCJDaGFubmVsTWVzc2FnZS5SZWFkLkFsbCIsIkNoYW5uZWxNZXNzYWdlLlVwZGF0ZVBvbGljeVZpb2xhdGlvbi5BbGwiLCJDaGFubmVsTWVtYmVyLlJlYWRXcml0ZS5BbGwiXSwic3ViIjoiODhhNmEyOWYtZjdlNi00OGEzLTlmZDctYzU4YjhhMmRhMjg4IiwidGVuYW50X3JlZ2lvbl9zY29wZSI6Ik5BIiwidGlkIjoiNTkzYWVhN2YtNGZiZi00MmZlLTkzZWYtN2FjMTE0ODQ4ZjY2IiwidXRpIjoibnczRUFrRWFoRWltVUtOdkRQSWJBQSIsInZlciI6IjEuMCIsIndpZHMiOlsiODhkOGUzZTMtOGY1NS00YTFlLTk1M2EtOWI5ODk4Yjg4NzZiIiwiMDk5N2ExZDAtMGQxZC00YWNiLWI0MDgtZDVjYTczMTIxZTkwIl0sInhtc190Y2R0IjoxNDk1NjU4OTE0fQ.Z6dvqXxX1CXJsiR3b1cpfwC8lt7qZtNAxmbP7txnS6YbZw49AO89LsQywxB3kqK53ZqMijHlfPDddfLj5NUhmHEzfnTsQZLUFfogAQgR1fgq0UWjkY1Ffy6q_DSCBAzGPdv79U3jWwez7E1eENopImkt6k4TpCRywjWyrfNtMoLUOvO42fCqGDCA8I2SLqtzUZcOTZeh74Uw26RdHLQxbW0kpUwQflxnvid_uK_HgbTGuMbAjMSPbck3py53yOqAFIBo-1USJYPRwXj6_hDmloiDJfv13wMH00H9SUllLX0T2DPBOw8uhN-IbVQNyKdmBzARCfCqt8MhztsFwa-9jg";
  String msftSubscribedSkuUrl = "https://graph.microsoft.com/v1.0/subscribedSkus";


  public static void main(String[] args) {
    MsftTeamsSubscribedSkusHttp msftTeamsSubscribedSkusHttp = new MsftTeamsSubscribedSkusHttp();
    msftTeamsSubscribedSkusHttp.getSubscribedSkus();
  }

  private void getSubscribedSkus() {
    URL url = null;
    HttpURLConnection con = null;
    StringBuffer response = new StringBuffer();

    try {
      url = new URL(msftSubscribedSkuUrl);
      con = (HttpURLConnection) url.openConnection();

      con.setRequestMethod("GET");

      //con.setRequestProperty("Content-Type", "application/json");
      con.setRequestProperty("Authorization", this.authToken);
      con.setRequestProperty("Accept", "application/json;odata.metadata=full");

      int responseCode = con.getResponseCode();
      System.out.println("GET Response Code :: " + responseCode);


      if (responseCode == HttpURLConnection.HTTP_OK) { // success
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();
      } else {
        System.out.println("GET request not worked");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("response: " + response);
  }
}
