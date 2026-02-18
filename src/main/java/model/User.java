package model;

import enums.IdProofType;
import enums.UserType;
import enums.WorkingStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class User {

    private String name;
    private int phone;
    private final int id;
    private String govtIdProof;
    private IdProofType idProofType;
    private String password;
    private final UserType userType;
    private WorkingStatus workingStatus;

    @JsonCreator
    public User(
            @JsonProperty("name") String name,
            @JsonProperty("phone") int phone,
            @JsonProperty("id") int id,
            @JsonProperty("govtIdProof") String govtIdProof,
            @JsonProperty("idProofType") IdProofType idProofType,
            @JsonProperty("password") String password,
            @JsonProperty("userType") UserType userType,
            @JsonProperty("workingStatus") WorkingStatus workingStatus
    ) {
        this.name = name;
        this.phone = phone;
        this.id = id;
        this.govtIdProof = govtIdProof;
        this.idProofType = idProofType;
        this.password = password;
        this.userType = userType;
        this.workingStatus = workingStatus;
    }

    public User(UserType userType) {
        this.id = -1;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public int getId() {
        return id;
    }

    public String getGovtIdProof() {
        return govtIdProof;
    }

    public IdProofType getIdProofType() {
        return idProofType;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public WorkingStatus getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(WorkingStatus workingStatus) {
        this.workingStatus = workingStatus;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setGovtIdProof(String govtIdProof) {
        this.govtIdProof = govtIdProof;
    }

    public void setIdProofType(IdProofType idProofType) {
        this.idProofType = idProofType;
    }


}
