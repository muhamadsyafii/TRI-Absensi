package dev.syafii.triabsensi.model;

import com.google.gson.annotations.SerializedName;

public class DataResponse {

	@SerializedName("nik")
	private String nik;

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("nama")
	private String nama;

	@SerializedName("id_absensi")
	private String idAbsensi;

	@SerializedName("waktu_in")
	private String waktuIn;

	@SerializedName("tgl_absensi")
	private String tglAbsensi;

	@SerializedName("waktu_out")
	private String waktuOut;

	public void setNik(String nik){
		this.nik = nik;
	}

	public String getNik(){
		return nik;
	}

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setIdAbsensi(String idAbsensi){
		this.idAbsensi = idAbsensi;
	}

	public String getIdAbsensi(){
		return idAbsensi;
	}

	public void setWaktuIn(String waktuIn){
		this.waktuIn = waktuIn;
	}

	public String getWaktuIn(){
		return waktuIn;
	}

	public void setTglAbsensi(String tglAbsensi){
		this.tglAbsensi = tglAbsensi;
	}

	public String getTglAbsensi(){
		return tglAbsensi;
	}

	public void setWaktuOut(String waktuOut){
		this.waktuOut = waktuOut;
	}

	public String getWaktuOut(){
		return waktuOut;
	}
}