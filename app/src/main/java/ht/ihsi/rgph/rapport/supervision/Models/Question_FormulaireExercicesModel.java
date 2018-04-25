package ht.ihsi.rgph.rapport.supervision.Models;

/**
 * Created by JFDuverseau on 8/26/2017.
 */

public class Question_FormulaireExercicesModel  extends BaseModel {
    private Long codeFormulaireExercice;
    private Long codeQuestion;
    private String ordreQuestion;
    private Boolean estDebutQuestion;
    private String createdBy;
    private String dateCreated;



    public Long getCodeFormulaireExercice() {
        return codeFormulaireExercice;
    }

    public void setCodeFormulaireExercice(Long codeFormulaireExercice) {
        this.codeFormulaireExercice = codeFormulaireExercice;
    }

    public Long getCodeQuestion() {
        return codeQuestion;
    }

    public void setCodeQuestion(Long codeQuestion) {
        this.codeQuestion = codeQuestion;
    }

    public String getOrdreQuestion() {
        return ordreQuestion;
    }

    public void setOrdreQuestion(String ordreQuestion) {
        this.ordreQuestion = ordreQuestion;
    }

    public Boolean getEstDebutQuestion() {
        return estDebutQuestion;
    }

    public void setEstDebutQuestion(Boolean estDebutQuestion) {
        this.estDebutQuestion = estDebutQuestion;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }


}
