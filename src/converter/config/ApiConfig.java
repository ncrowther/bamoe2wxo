package converter.config;

public class ApiConfig {
	String decisionId;
	String localUrl;
	String remoteUrl;
	
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
	
	@Override
	public String toString() {
		return "ApiConfig [decisionId=" + decisionId + ", localUrl=" + localUrl + ", remoteUrl=" + remoteUrl + "]";
	}
}
