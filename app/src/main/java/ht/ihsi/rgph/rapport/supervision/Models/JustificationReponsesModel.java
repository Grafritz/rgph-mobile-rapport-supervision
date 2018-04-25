package ht.ihsi.rgph.rapport.supervision.Models;

import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by JFDuverseau on 8/26/2017.
 */

public class JustificationReponsesModel extends BaseModel  {

    private Long codeJustification;
    private Long codeQuestion;
    private String libelle;
    private Boolean isCorrect;
    private String createdBy;
    private String dateCreated;
    private String modifBy;
    private String dateModif;


    //region CONTROLS
    public RadioButton radioButton;
    public TextView tReponse;
    //endregion

    public JustificationReponsesModel() {
    }

    public JustificationReponsesModel(Long codeJustification, Long codeQuestion, String libelle) {
        this.codeJustification = codeJustification;
        this.codeQuestion = codeQuestion;
        this.libelle = libelle;
        this.isCorrect = false;
        this.createdBy = "";
        this.dateCreated = "";
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


    @Override
    public String toString() {
        return this.libelle.toString();
    }
}
