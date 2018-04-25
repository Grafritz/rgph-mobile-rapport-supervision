package ht.ihsi.rgph.rapport.supervision.Models;

/**
 * Created by JFDuverseau on 9/23/2017.
 */

public class Agent_Evaluation_ExercicesModel extends BaseModel {

    private Long codeExercice;
    private Long personnelId;
    private String dureeEvaluationEnSeconde;
    private String dureeDuRepondantEnSeconde;
    private String dateDebutEvaluationDuRepondant;
    private String dateFinEvaluationDuRepondant;


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
