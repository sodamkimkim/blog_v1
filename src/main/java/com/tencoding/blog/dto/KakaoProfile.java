package com.tencoding.blog.dto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoProfile {
// https://www.jsonschema2pojo.org/ 쓰기
	private Boolean profileNicknameNeedsAgreement;
	private Profile profile;
	private Boolean hasEmail;
	private Boolean emailNeedsAgreement;
	private Boolean isEmailValid;
	private Boolean isEmailVerified;
	private String email;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
