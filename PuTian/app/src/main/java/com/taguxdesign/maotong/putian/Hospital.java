package com.taguxdesign.maotong.putian;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MaoTong on 2016/5/6.
 * QQ:974291433
 */
public class Hospital implements Parcelable {
    private String hospitalName;
    private String phone;
    private String webSite;
    private String location;
    private String qq;
    private String wx;
    private String wxN; //微信公众号
    private String evidence;

    public Hospital(String hospitalName, String phone, String webSite, String location, String
            qq, String wx, String wxN, String evidence) {
        this.hospitalName = hospitalName;
        this.phone = phone;
        this.webSite = webSite;
        this.location = location;
        this.qq = qq;
        this.wx = wx;
        this.wxN = wxN;
        this.evidence = evidence;
    }

    protected Hospital(Parcel in) {
        hospitalName = in.readString();
        phone = in.readString();
        webSite = in.readString();
        location = in.readString();
        qq = in.readString();
        wx = in.readString();
        wxN = in.readString();
        evidence = in.readString();
    }

    public static final Creator<Hospital> CREATOR = new Creator<Hospital>() {
        @Override
        public Hospital createFromParcel(Parcel in) {
            return new Hospital(in);
        }

        @Override
        public Hospital[] newArray(int size) {
            return new Hospital[size];
        }
    };

    public String getWxN() {
        return wxN;
    }

    public void setWxN(String wxN) {
        this.wxN = wxN;
    }

    public Hospital() {

    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "hospitalName='" + hospitalName + '\'' +
                ", phone='" + phone + '\'' +
                ", webSite='" + webSite + '\'' +
                ", location='" + location + '\'' +
                ", qq='" + qq + '\'' +
                ", wx='" + wx + '\'' +
                ", wxN='" + wxN + '\'' +
                ", evidence='" + evidence + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hospitalName);
        dest.writeString(phone);
        dest.writeString(webSite);
        dest.writeString(location);
        dest.writeString(qq);
        dest.writeString(wx);
        dest.writeString(wxN);
        dest.writeString(evidence);
    }
}
