package cz.itnetwork.models;

public class Insurance {

    private int insuranceId;
    private String typeOfInsurance;
    private int personId;
    private int amount;
    private String subjectOfInsurance;
    private String validFrom;
    private String validUntil;

    // complete constructor
    public Insurance(int insuranceId, String typeOfInsurance, int personId, int amount, String subjectOfInsurance, String validFrom, String validUntil) {
        this.insuranceId = insuranceId;
        this.typeOfInsurance = typeOfInsurance;
        this.personId = personId;
        this.amount = amount;
        this.subjectOfInsurance = subjectOfInsurance;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }

    // constructor without insuranceId
    public Insurance(String typeOfInsurance, int personId, int amount, String subjectOfInsurance, String validFrom, String validUntil) {
        this.typeOfInsurance = typeOfInsurance;
        this.personId = personId;
        this.amount = amount;
        this.subjectOfInsurance = subjectOfInsurance;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }

    // empty constructor
    public Insurance() {

    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getTypeOfInsurance() {
        return typeOfInsurance;
    }

    public void setTypeOfInsurance(String typeOfInsurance) {
        this.typeOfInsurance = typeOfInsurance;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSubjectOfInsurance() {
        return subjectOfInsurance;
    }

    public void setSubjectOfInsurance(String subjectOfInsurance) {
        this.subjectOfInsurance = subjectOfInsurance;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "insuranceId=" + insuranceId +
                ", typeOfInsurance='" + typeOfInsurance + '\'' +
                ", personId=" + personId +
                ", amount=" + amount +
                ", subjectOfInsurance='" + subjectOfInsurance + '\'' +
                ", validFrom=" + validFrom +
                ", validUntil=" + validUntil +
                '}';
    }
}
