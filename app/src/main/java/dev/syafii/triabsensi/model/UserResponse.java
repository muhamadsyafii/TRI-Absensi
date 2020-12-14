package dev.syafii.triabsensi.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserResponse {

	@SerializedName("NIK")
	private String nIK;
	@SerializedName("Nama")
	private String nama;
	@SerializedName("Jenis Kelamin")
	private String jenisKelamin;
	@SerializedName("Departemen")
	private String departemen;
	@SerializedName("Kategori")
	private String kategori;
	@SerializedName("Foto Profil")
	private String fotoProfil;

	public static UserResponse objectFromData(String str) {

		return new Gson().fromJson(str, UserResponse.class);
	}

	public static List<UserResponse> arrayCityFromData(String str) {

		Type listType = new TypeToken<ArrayList<UserResponse>>() {
		}.getType();

		return new Gson().fromJson(str, listType);
	}

	public String getnIK() {
		return nIK;
	}

	public void setnIK(String nIK) {
		this.nIK = nIK;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}

	public String getDepartemen() {
		return departemen;
	}

	public void setDepartemen(String departemen) {
		this.departemen = departemen;
	}

	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public String getFotoProfil() {
		return fotoProfil;
	}

	public void setFotoProfil(String fotoProfil) {
		this.fotoProfil = fotoProfil;
	}
}