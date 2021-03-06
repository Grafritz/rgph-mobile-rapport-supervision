package ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "Tbl_Agent_Evaluation_Exercices".
 */
public class Agent_Evaluation_Exercices {

    private Long codeExercice;
    private Long personnelId;
    private String dureeEvaluationEnSeconde;
    private String dureeDuRepondantEnSeconde;
    private String dateDebutEvaluationDuRepondant;
    private String dateFinEvaluationDuRepondant;

    public Agent_Evaluation_Exercices() {
    }

    public Agent_Evaluation_Exercices(Long codeExercice, Long personnelId, String dureeEvaluationEnSeconde, String dureeDuRepondantEnSeconde, String dateDebutEvaluationDuRepondant, String dateFinEvaluationDuRepondant) {
        this.codeExercice = codeExercice;
        this.personnelId = personnelId;
        this.dureeEvaluationEnSeconde = dureeEvaluationEnSeconde;
        this.dureeDuRepondantEnSeconde = dureeDuRepondantEnSeconde;
        this.dateDebutEvaluationDuRepondant = dateDebutEvaluationDuRepondant;
        this.dateFinEvaluationDuRepondant = dateFinEvaluationDuRepondant;
    }

    public Long getCodeExercice() {
        return codeExercice;
    }

    public void setCodeExercice(Long codeExercice) {
        this.codeExercice = codeExercice;
    }

    public Long getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Long personnelId) {
        this.personnelId = personnelId;
    }

    public String getDureeEvaluationEnSeconde() {
        return dureeEvaluationEnSeconde;
    }

    public void setDureeEvaluationEnSeconde(String dureeEvaluationEnSeconde) {
        this.dureeEvaluationEnSeconde = dureeEvaluationEnSeconde;
    }

    public String getDureeDuRepondantEnSeconde() {
        return dureeDuRepondantEnSeconde;
    }

    public void setDureeDuRepondantEnSeconde(String dureeDuRepondantEnSeconde) {
        this.dureeDuRepondantEnSeconde = dureeDuRepondantEnSeconde;
    }

    public String getDateDebutEvaluationDuRepondant() {
        return dateDebutEvaluationDuRepondant;
    }

    public void setDateDebutEvaluationDuRepondant(String dateDebutEvaluationDuRepondant) {
        this.dateDebutEvaluationDuRepondant = dateDebutEvaluationDuRepondant;
    }

    public String getDateFinEvaluationDuRepondant() {
        return dateFinEvaluationDuRepondant;
    }

    public void setDateFinEvaluationDuRepondant(String dateFinEvaluationDuRepondant) {
        this.dateFinEvaluationDuRepondant = dateFinEvaluationDuRepondant;
    }

}
