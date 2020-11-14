package dev.syafii.triabsensi.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class AbsentResponse {

	@SerializedName("result")
	private String result;

	@SerializedName("status")
	private boolean status;

	public static AbsentResponse objectFromData(String str) {

		return new Gson().fromJson(str, AbsentResponse.class);
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}