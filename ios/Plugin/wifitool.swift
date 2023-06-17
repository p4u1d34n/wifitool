import Foundation

@objc public class wifitool: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
