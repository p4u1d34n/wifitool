package com.mycompany.plugins.example;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;


import com.getcapacitor.JSArray;


@CapacitorPlugin(name = "wifitool")
public class wifitoolPlugin extends Plugin {

    private WifiTool wifiTool;

    @Override
    public void load() {
        wifiTool = new WifiTool(getContext());
    }

    @PluginMethod
    public void listAvailableNetworks(PluginCall call) {
        JSObject result = new JSObject();
        JSArray networkList = new JSArray(wifiTool.listAvailableNetworks());
        if (networkList != null) {
            result.put("networks", networkList);
            call.resolve(result);
        } else {
            call.reject("Failed to retrieve available networks.");
        }
    }

}
