package dev.syafii.triabsensi.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserResponse {

	@SerializedName("nik")
	private String nik;
	@SerializedName("je_kel")
	private String jeKel;
	@SerializedName("nama")
	private String nama;
	@SerializedName("nam_kateg")
	private String category;
	@SerializedName("foto")
	private String foto;

	public static UserResponse objectFromData(String str) {

		return new Gson().fromJson(str, UserResponse.class);
	}

	public static List<UserResponse> arrayCityFromData(String str) {

		Type listType = new TypeToken<ArrayList<UserResponse>>() {
		}.getType();

		return new Gson().fromJson(str, listType);
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getJeKel() {
		return jeKel;
	}

	public void setJeKel(String jeKel) {
		this.jeKel = jeKel;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
}