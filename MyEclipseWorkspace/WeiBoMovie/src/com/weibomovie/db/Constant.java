package com.weibomovie.db;

public class Constant {
	private static boolean isOpenSyso = true;
	private static boolean isOpenDownloadData  = false ;
	
	

	public static boolean isOpenSyso() {
		return isOpenSyso;
	}

	public static void setOpenSyso(boolean isOpenSyso) {
		Constant.isOpenSyso = isOpenSyso;
	}

	public static boolean isOpenDownloadData() {
		return isOpenDownloadData;
	}

	public static void setOpenDownloadData(boolean isOpenDownloadData) {
		Constant.isOpenDownloadData = isOpenDownloadData;
	}
}