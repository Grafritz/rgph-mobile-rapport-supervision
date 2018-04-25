package ht.ihsi.rgph.rapport.supervision.Models;

/**
 * Created by JFDuverseau on 8/26/2017.
 */

public class ReponseEntreeModel extends BaseModel  {
    private Long codeReponseEntree;
    private Long personnelId;
    private Long codeFormulaireExercice;
    private Long codeQuestion;
    private Long codeReponse;
    private Long CodeJustificationReponse;
    private String reponseSaisie;
    private String createdBy;
    private String dateCreated;
    private String modifBy;
    private String dateModif;

    public Long getCodeReponseEntree() {
        return codeReponseEntree;
    }

    public void setCodeReponseEntree(Long codeReponseEntree) {
        this.codeReponseEntree = codeReponseEntree;
    }

    public Long getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Long personnelId) {
        this.personnelId = personnelId;
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

    public Long getCodeJustificationReponse() {
        return CodeJustificationReponse;
    }

    public void setCodeJustificationReponse(Long CodeJustificationReponse) {
        this.CodeJustificationReponse = CodeJustificationReponse;
    }

    public String getReponseSaisie() {
        return reponseSaisie;
    }

    public void setReponseSaisie(String reponseSaisie) {
        this.reponseSaisie = reponseSaisie;
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
