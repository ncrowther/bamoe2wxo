package converter.config;

public class ApiConfig {
	String decisionId;
	String decisionFolder;
	String processId;
	String localUrl;
	String remoteUrl;
	
	public String getDecisionFolder() {
		return decisionFolder;
	}
	public void setDecisionFolder(String decisionFolder) {
		this.decisionFolder = decisionFolder;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getDecisionId() {
		return decisionId;
	}
	public void setDecisionId(String decisionId) {
		this.decisionId = decisionId;
	}
	public String getLocalUrl() {
		return localUrl;
	}
	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}
	public String getRemoteUrl() {
		return remoteUrl;
	}
	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}
}
	
