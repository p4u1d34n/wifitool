package com.mycompany.plugins.example;

import android.util.Log;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class wifitool {

    private Context context;
    private WifiManager wifiManager;

    public WifiTool(Context context) {
        this.context = context;
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    public JSONArray listAvailableNetworks() {
        if (wifiManager != null) {
            if (wifiManager.isWifiEnabled()) {
                List<ScanResult> scanResults = wifiManager.getScanResults();
                if (scanResults != null && !scanResults.isEmpty()) {
                    JSONArray networkList = new JSONArray();
                    for (ScanResult scanResult : scanResults) {
                        JSONObject networkInfo = new JSONObject();
                        try {
                            String ssid = scanResult.SSID;
                            if (ssid != null && !ssid.isEmpty()) {
                                networkInfo.put("SSID", ssid);
                                String bssid = scanResult.BSSID;
                                if (bssid != null) {
                                    networkInfo.put("BSSID", bssid);
                                }
                                int rssi = scanResult.level;
                                networkInfo.put("RSSI", rssi);
                                networkList.put(networkInfo);
                            }
                        } catch (JSONException e) {
                            Log.e("WifiTool", "Error creating network JSON object", e);
                        }
                    }
                    return networkList;
                }
            }
        }
        return null;
    }

}
