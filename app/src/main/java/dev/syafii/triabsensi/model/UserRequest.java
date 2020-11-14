package dev.syafii.triabsensi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UserRequest {

	@SerializedName("profil")
	private List<UserResponse> result;

	@SerializedName("status")
	private boolean status;

	public List<UserResponse> getResult(){
		return result;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}