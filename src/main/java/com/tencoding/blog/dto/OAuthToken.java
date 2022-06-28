package com.tencoding.blog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OAuthToken {
	// access_token
	// 제이슨파서 이용
//	http://json.parser.online.fr/
	private String accessToken;
    private String tokenType; 
    private String refreshToken;
    private int expiresIn;
    private String scope; 
    private String refreshTokenExpiresIn;
}
