package kr.hsz.security.social;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NaverUserProperty implements SocialUserProperty {

	@JsonProperty("resultcode")
	private String resultcode;

	@JsonProperty("message")
	private String message;

	@JsonProperty("response")
	private Map<String, String> properties;

	
	@Override
	public String getUserId() {
		
		return getPropertie("id");
	}

	@Override
	public String getUserNickname() {
		return getPropertie("nickname");
	}

	@Override
	public String getProfileHref() {
		return getPropertie("profile_image");
	}

	@Override
	public String getEmail() {
		return getPropertie("email");
	}
	
	private String getPropertie(String key) {
		
		for(Map.Entry<String, String> entry : properties.entrySet()) {
			if(key.equals(entry.getKey())) return entry.getValue();
		}
		
		return null;
		
	}

}
