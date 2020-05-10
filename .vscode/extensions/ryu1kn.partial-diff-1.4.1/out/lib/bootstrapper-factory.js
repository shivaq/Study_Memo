"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const bootstrapper_1 = require("./bootstrapper");
const command_factory_1 = require("./command-factory");
const workspace_1 = require("./adaptors/workspace");
const content_provider_1 = require("./content-provider");
const normalisation_rule_store_1 = require("./normalisation-rule-store");
const selection_info_registry_1 = require("./selection-info-registry");
const vscode = require("vscode");
const command_1 = require("./adaptors/command");
const window_1 = require("./adaptors/window");
const telemetry_reporter_1 = require("./telemetry-reporter");
const vscode_extension_telemetry_1 = require("vscode-extension-telemetry");
class BootstrapperFactory {
    create() {
        const logger = console;
        const selectionInfoRegistry = new selection_info_registry_1.default();
        const workspaceAdaptor = this.getWorkspaceAdaptor();
        const commandAdaptor = new command_1.default(vscode.commands, vscode.Uri.parse, logger);
        const normalisationRuleStore = new normalisation_rule_store_1.default(workspaceAdaptor);
        const commandFactory = new command_factory_1.default(selectionInfoRegistry, normalisationRuleStore, commandAdaptor, new window_1.default(vscode.window), vscode.env.clipboard, () => new Date());
        const contentProvider = new content_provider_1.default(selectionInfoRegistry, normalisationRuleStore);
        return new bootstrapper_1.default(commandFactory, contentProvider, workspaceAdaptor, commandAdaptor);
    }
    getWorkspaceAdaptor() {
        this.workspaceAdaptor = this.workspaceAdaptor || new workspace_1.default(vscode.workspace);
        return this.workspaceAdaptor;
    }
    getVsTelemetryReporterCreator() {
        const enableTelemetry = this.getWorkspaceAdaptor().get('enableTelemetry');
        if (enableTelemetry) {
            return (id, version, telemetryKey) => new vscode_extension_telemetry_1.default(id, version, telemetryKey);
        }
        else {
            return () => new telemetry_reporter_1.NullVsTelemetryReporter();
        }
    }
}
exports.default = BootstrapperFactory;
//# sourceMappingURL=bootstrapper-factory.js.map