package kr.hsz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempUser {

	private String userId;
	private String userPassword;
	private String userName;
	
}
