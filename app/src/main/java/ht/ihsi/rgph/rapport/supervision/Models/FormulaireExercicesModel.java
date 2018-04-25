package ht.ihsi.rgph.rapport.supervision.Models;

/**
 * Created by JFDuverseau on 8/26/2017.
 */

public class FormulaireExercicesModel extends BaseModel  {

    private Long codeExercice;
    private String libelleExercice;
    private String descriptions;
    private String instructions;
    private String rappelExercice;
    private String typeEvaluation;
    private String statut;
    private String dureeEnSeconde;
    private String createdBy;
    private String dateCreated;

    private boolean isReadyToEvaluate=false;

    public boolean getIsReadyToEvaluate() {
        return isReadyToEvaluate;
    }

    public void setIsReadyToEvaluate(boolean readyToEvaluate) {
        isReadyToEvaluate = readyToEvaluate;
    }

    public Long getCodeExercice() {
        return codeExercice;
    }

    public void setCodeExercice(Long codeExercice) {
        this.codeExercice = codeExercice;
    }

    public String getLibelleExercice() {
        return libelleExercice;
    }

    public void setLibelleExercice(String libelleExercice) {
        this.libelleExercice = libelleExercice;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getRappelExercice() {
        return rappelExercice;
    }

    public void setRappelExercice(String rappelExercice) {
        this.rappelExercice = rappelExercice;
    }

    public String getTypeEvaluation() {
        return typeEvaluation;
    }

    public void setTypeEvaluation(String typeEvaluation) {
        this.typeEvaluation = typeEvaluation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDureeEnSeconde() {
        return dureeEnSeconde;
    }

    public void setDureeEnSeconde(String dureeEnSeconde) {
        this.dureeEnSeconde = dureeEnSeconde;
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
