package Clinic;

// Medical record of a pet
public class MedicalRecord{
    // Attributes 
    private String visitSummary;
    private String diagnosis;
    private String recordId;
    private String treatment;

    // Constructor 
    public MedicalRecord(String visitSummary, String diagnosis, String recordId, String treatment){
        this.visitSummary = visitSummary;
        this.diagnosis = diagnosis;
        this.recordId = recordId;
        this.treatment = treatment;
    }
    // Getter
    public String getVisitSummary() {
        return this.visitSummary;
    }
    public String getDiagnosis(){
        return this.diagnosis;
    }
    public String getRecordId(){
        return this.recordId;
    }
    public String getTreatment(){
        return this.treatment;
    }

}

