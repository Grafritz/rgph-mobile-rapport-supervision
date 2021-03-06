package ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "Tbl_JustificationReponses".
 */
public class JustificationReponses {

    private Long codeJustification;
    private Long codeQuestion;
    private String libelle;
    private Boolean isCorrect;
    private String createdBy;
    private String dateCreated;

    public JustificationReponses() {
    }

    public JustificationReponses(Long codeJustification) {
        this.codeJustification = codeJustification;
    }

    public JustificationReponses(Long codeJustification, Long codeQuestion, String libelle, Boolean isCorrect, String createdBy, String dateCreated) {
        this.codeJustification = codeJustification;
        this.codeQuestion = codeQuestion;
        this.libelle = libelle;
        this.isCorrect = isCorrect;
        this.createdBy = createdBy;
        this.dateCreated = dateCreated;
    }

    public Long getCodeJustification() {
        return codeJustification;
    }

    public void setCodeJustification(Long codeJustification) {
        this.codeJustification = codeJustification;
    }

    public Long getCodeQuestion() {
        return codeQuestion;
    }

    public void setCodeQuestion(Long codeQuestion) {
        this.codeQuestion = codeQuestion;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
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
