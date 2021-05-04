package com.suresh.navigationretrofit;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Payments implements Serializable {
    @SerializedName("completedDateTime")
    private String completedDateTime;

    @SerializedName("chitNo")
    private String ChitNo;

    @SerializedName("branchName")
    private String branchName;

    @SerializedName("dueAmount")
    private String dueAmount;

    @SerializedName("paidAmount")
    private String paidAmount;

    @SerializedName("transId")
    private String transId;

    public Payments(String completedDateTime, String chitNo, String branchName,
                    String dueAmount, String paidAmount, String transId) {
        this.completedDateTime = completedDateTime;
        this.branchName = branchName;
        this.ChitNo = chitNo;
        this.dueAmount = dueAmount;
        this.paidAmount = paidAmount;
        this.transId = transId;
    }

    public String getCompletedDateTime() {
        return completedDateTime;
    }

    public String getChitNo() {
        return ChitNo;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getDueAmount() {
        return dueAmount;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public String getTransId() {
        return transId;
    }
}
