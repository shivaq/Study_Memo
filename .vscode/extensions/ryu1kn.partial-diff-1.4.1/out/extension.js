"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const bootstrapper_factory_1 = require("./lib/bootstrapper-factory");
const telemetry_reporter_1 = require("./lib/telemetry-reporter");
const path_1 = require("path");
const bootstrapperFactory = new bootstrapper_factory_1.default();
const reporterCreator = bootstrapperFactory.getVsTelemetryReporterCreator();
const packageJsonPath = path_1.join(__dirname, '..', 'package.json');
telemetry_reporter_1.TelemetryReporterLocator.load(packageJsonPath, reporterCreator);
const telemetryReporter = telemetry_reporter_1.TelemetryReporterLocator.getReporter();
exports.activate = (context) => {
    const bootstrapper = bootstrapperFactory.create();
    bootstrapper.initiate(context);
    context.subscriptions.push(telemetryReporter);
};
exports.deactivate = () => {
    telemetryReporter.dispose();
};
//# sourceMappingURL=extension.js.map