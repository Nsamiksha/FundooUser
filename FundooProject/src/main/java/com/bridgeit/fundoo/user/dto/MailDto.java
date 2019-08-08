package com.bridgeit.fundoo.user.dto;

public class MailDto {
	private String email;
	private String subject;
	private String body;
	public String getEmail() {
		return email;
	}
	public String getSubject() {
		return subject;
	}
	public String getBody() {
		return body;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "MailDto [email=" + email + ", subject=" + subject + ", body=" + body + "]";
	}
}
