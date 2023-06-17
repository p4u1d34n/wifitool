import Foundation
import Capacitor

import CoreWLAN

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(wifitoolPlugin)
public class wifitoolPlugin: CAPPlugin {
    private let implementation = wifitool()

    @objc func listAvailableNetworks(_ call: CAPPluginCall) {
        if let interface = CWWiFiClient.shared().interface(), let networks = interface.scanForNetworks(withSSID: nil) {
            var networkList: [[String: Any]] = []
            for network in networks {
                guard let ssid = network.ssid(), !ssid.isEmpty else {
                    continue
                }
                var networkInfo: [String: Any] = [:]
                networkInfo["SSID"] = ssid
                if let bssid = network.bssid() {
                    networkInfo["BSSID"] = bssid
                }
                if let rssi = network.rssiValue() {
                    networkInfo["RSSI"] = rssi
                }
                networkList.append(networkInfo)
            }
            call.resolve(["networks": networkList])
        } else {
            call.reject("Failed to retrieve available networks.")
        }
    }
    
}
