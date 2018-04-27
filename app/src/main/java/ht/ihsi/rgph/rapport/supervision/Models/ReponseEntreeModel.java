package ht.ihsi.rgph.rapport.supervision.Models;

/**
 * Created by JFDuverseau on 8/26/2017.
 */

public class ReponseEntreeModel extends BaseModel  {
    private Long codeReponseEntree;
    private Long codeAgent;
    private Long codeFormulaireExercice;
    private Long codeQuestion;
    private Long codeReponse;
    private Long ScoreReponse;
    private String createdBy;
    private String dateCreated;
    private String modifBy;
    private String dateModif;

    public ReponseEntreeModel() {
    }

    public ReponseEntreeModel(Long codeReponseEntree) {
        this.codeReponseEntree = codeReponseEntree;
    }

    public ReponseEntreeModel(Long codeReponseEntree, Long codeAgent, Long codeFormulaireExercice, Long codeQuestion, Long codeReponse, Long ScoreReponse, String createdBy, String dateCreated, String modifBy, String dateModif) {
        this.codeReponseEntree = codeReponseEntree;
        this.codeAgent = codeAgent;
        this.codeFormulaireExercice = codeFormulaireExercice;
        this.codeQuestion = codeQuestion;
        this.codeReponse = codeReponse;
        this.ScoreReponse = ScoreReponse;
        this.createdBy = createdBy;
        this.dateCreated = dateCreated;
        this.modifBy = modifBy;
        this.dateModif = dateModif;
    }

    public Long getCodeReponseEntree() {
        return codeReponseEntree;
    }

    public void setCodeReponseEntree(Long codeReponseEntree) {
        this.codeReponseEntree = codeReponseEntree;
    }

    public Long getCodeAgent() {
        return codeAgent;
    }

    public void setCodeAgent(Long codeAgent) {
        this.codeAgent = codeAgent;
    }

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

    public Long getCodeReponse() {
        return codeReponse;
    }

    public void setCodeReponse(Long codeReponse) {
        this.codeReponse = codeReponse;
    }

    public Long getScoreReponse() {
        return ScoreReponse;
    }

    public void setScoreReponse(Long ScoreReponse) {
        this.ScoreReponse = ScoreReponse;
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

    public String getModifBy() {
        return modifBy;
    }

    public void setModifBy(String modifBy) {
        this.modifBy = modifBy;
    }

    public String getDateModif() {
        return dateModif;
    }

    public void setDateModif(String dateModif) {
        this.dateModif = dateModif;
    }

}
