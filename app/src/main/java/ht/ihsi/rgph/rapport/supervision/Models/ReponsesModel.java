package ht.ihsi.rgph.rapport.supervision.Models;

import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by JFDuverseau on 8/26/2017.
 */

public class ReponsesModel extends BaseModel  {

    private Long codeReponse;
    private Long codeQuestion;
    private String libelleReponse;
    private Boolean isCorrect;
    private Boolean estEnfant;
    private Boolean avoirEnfant;
    private String codeParent;
    private String createdBy;
    private String dateCreated;


    //region CONTROLS
    public RadioButton radioButton;
    public TextView tReponse;
    //endregion

    //region [ CONSTRUCTEUR ]
    public ReponsesModel() {
    }

    public ReponsesModel(Long codeReponse, Long codeQuestion, String libelleReponse) {
        this.codeReponse = codeReponse;
        this.codeQuestion = codeQuestion;
        this.libelleReponse = libelleReponse;
        this.isCorrect = false;
        this.estEnfant = false;
        this.avoirEnfant = false;
        this.codeParent = "";
    }
    //endregion

    //endregion
    public Long getCodeReponse() {
        return codeReponse;
    }

    public void setCodeReponse(Long codeReponse) {
        this.codeReponse = codeReponse;
    }

    public Long getCodeQuestion() {
        return codeQuestion;
    }

    public void setCodeQuestion(Long codeQuestion) {
        this.codeQuestion = codeQuestion;
    }

    public String getLibelleReponse() {
        return libelleReponse;
    }

    public void setLibelleReponse(String libelleReponse) {
        this.libelleReponse = libelleReponse;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Boolean getEstEnfant() {
        return estEnfant;
    }

    public void setEstEnfant(Boolean estEnfant) {
        this.estEnfant = estEnfant;
    }

    public Boolean getAvoirEnfant() {
        return avoirEnfant;
    }

    public void setAvoirEnfant(Boolean avoirEnfant) {
        this.avoirEnfant = avoirEnfant;
    }

    public String getCodeParent() {
        return codeParent;
    }

    public void setCodeParent(String codeParent) {
        this.codeParent = codeParent;
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

    @Override
    public String toString() {
        return this.libelleReponse.toString();
    }
}
