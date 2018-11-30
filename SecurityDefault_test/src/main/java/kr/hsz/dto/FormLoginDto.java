package kr.hsz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormLoginDto {

	@JsonProperty("userid")
	private String id;
	
	@JsonProperty("password")
	private String password;
	
}
