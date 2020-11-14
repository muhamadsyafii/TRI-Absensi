package dev.syafii.triabsensi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataRequest {

	@SerializedName("result")
	private List<DataResponse> result;

	@SerializedName("status")
	private boolean status;

	public void setResult(List<DataResponse> result){
		this.result = result;
	}

	public List<DataResponse> getResult(){
		return result;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}