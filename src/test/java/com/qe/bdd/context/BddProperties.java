package com.qe.bdd.context;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bdd")
public class BddProperties {

	private String trelloEndpoint;
	
	public void setTrelloEndpoint(String trelloEndpoint) {
		this.trelloEndpoint = trelloEndpoint;
	}
	
	public String getTrelloEndpoint() {
		return trelloEndpoint;
	}
}
