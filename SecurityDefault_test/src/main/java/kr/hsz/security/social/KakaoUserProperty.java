package kr.hsz.security.social;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KakaoUserProperty implements SocialUserProperty {

	@JsonProperty("kaccount_email")
	private String userEmail;
	
	@JsonProperty("kaccount_email_verified")
	private Boolean verified;
	
	@JsonProperty("id")
	private Long userUniqueId; 
	
	@JsonProperty("properties")
	private Map<String, String> userProperties;

	
	@Override
	public String getUserId() {
		return userUniqueId.toString();
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
		return userEmail;
	}
	
	private String getPropertie(String key) {
		
		for(Map.Entry<String, String> entry : userProperties.entrySet()) {
			if(key.equals(entry.getKey())) return entry.getValue();
		}
		
		return null;
		
	}

}
