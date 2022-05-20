package com.arcsapt.sms.dto;

public class ResponseDTO {
	private Boolean success;
	private Integer code;
	private Object data;
	private String status;
	private String link;

	public ResponseDTO(Boolean success, Integer code, Object data,
			String status, String link) {
		this.success = success;
		this.code = code;
		this.data = data;
		this.status = status;
		this.setLink(link);
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
