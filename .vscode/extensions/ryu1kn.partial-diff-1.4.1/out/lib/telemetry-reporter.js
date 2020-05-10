"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const fs = require("fs");
class TelemetryReporterLocator {
    static load(packageConfPath, reporterCreator) {
        const packageInfo = JSON.parse(fs.readFileSync(packageConfPath, 'utf8'));
        const extensionId = `${packageInfo.publisher}.${packageInfo.name}`;
        const extensionVersion = packageInfo.version;
        const key = packageInfo.telemetryKey;
        const vsTelemetryReporter = reporterCreator(extensionId, extensionVersion, key);
        TelemetryReporterLocator.telemetryReporter = new TelemetryReporter(vsTelemetryReporter);
    }
    static getReporter() {
        return TelemetryReporterLocator.telemetryReporter;
    }
}
exports.TelemetryReporterLocator = TelemetryReporterLocator;
class TelemetryReporter {
    constructor(reporter) {
        this.reporter = reporter;
    }
    logCommandTrigger(commandName) {
        this.reporter.sendTelemetryEvent('commandTriggered', { commandName });
    }
    logCommandErrored(commandName) {
        this.reporter.sendTelemetryEvent('commandErrored', { commandName });
    }
    dispose() {
        return this.reporter.dispose();
    }
}
exports.TelemetryReporter = TelemetryReporter;
class NullVsTelemetryReporter {
    sendTelemetryEvent(_eventName, _properties, _measurements) {
    }
    dispose() {
        return Promise.resolve();
    }
}
exports.NullVsTelemetryReporter = NullVsTelemetryReporter;
//# sourceMappingURL=telemetry-reporter.js.map