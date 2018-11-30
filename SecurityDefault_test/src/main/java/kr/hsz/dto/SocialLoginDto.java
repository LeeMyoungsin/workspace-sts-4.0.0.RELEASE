package kr.hsz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import kr.hsz.enums.SocialProviders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialLoginDto {

	@JsonProperty("provider")
	private SocialProviders provider;

    @JsonProperty("token")
    String token;
    
}
