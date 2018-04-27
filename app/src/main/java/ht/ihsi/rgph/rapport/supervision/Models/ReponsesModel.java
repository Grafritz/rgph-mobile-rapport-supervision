package ht.ihsi.rgph.rapport.supervision.Models;

import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by JFDuverseau on 8/26/2017.
 */

public class ReponsesModel extends BaseModel  {

    private Long codeReponse;
    private Long codeReponseManuel;
    private Long codeQuestion;
    private String libelleReponse;
    private Boolean isCorrect;
    private Integer scoreTotal;
    private Boolean estEnfant;
    private Boolean avoirEnfant;
    private String codeParent;


    //region CONTROLS
    public RadioButton radioButton;
    public TextView tReponse;
    //endregion

    //region [ CONSTRUCTEUR ]
    public ReponsesModel() {
    }

    public ReponsesModel(Long codeReponse, Long codeQuestion, String libelleReponse) {
        this.codeReponse = codeReponse;
        this.codeReponseManuel = Long.valueOf(0);
        this.codeQuestion = codeQuestion;
        this.libelleReponse = libelleReponse;
        this.isCorrect = false;
        this.scoreTotal = 0;
        this.estEnfant = false;
        this.avoirEnfant = false;
        this.codeParent = "";
    }
    //endregion

    public ReponsesModel(Long codeReponse) {
        this.codeReponse = codeReponse;
    }

    public ReponsesModel(Long codeReponse, Long codeReponseManuel, Long codeQuestion, String libelleReponse, Boolean isCorrect, Integer scoreTotal, Boolean estEnfant, Boolean avoirEnfant, String codeParent) {
        this.codeReponse = codeReponse;
        this.codeReponseManuel = codeReponseManuel;
        this.codeQuestion = codeQuestion;
        this.libelleReponse = libelleReponse;
        this.isCorrect = isCorrect;
        this.scoreTotal = scoreTotal;
        this.estEnfant = estEnfant;
        this.avoirEnfant = avoirEnfant;
        this.codeParent = codeParent;
    }

    public Long getCodeReponse() {
        return codeReponse;
    }

    public void setCodeReponse(Long codeReponse) {
        this.codeReponse = codeReponse;
    }

    public Long getCodeReponseManuel() {
        return codeReponseManuel;
    }

    public void setCodeReponseManuel(Long codeReponseManuel) {
        this.codeReponseManuel = codeReponseManuel;
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

    public Integer getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Integer scoreTotal) {
        this.scoreTotal = scoreTotal;
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


    //region [ TOSTRING ]
    @Override
    public String toString() {
        return this.libelleReponse.toString()+" [ " +this.scoreTotal +" pts ]";
    }
    //endregion
}
