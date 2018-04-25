package ht.ihsi.rgph.rapport.supervision.Models;

/**
 * Created by JFDuverseau on 8/26/2017.
 */

public class QuestionsModel extends BaseModel {

    private Long codeQuestion;
    private String libelle;
    private String detailsQuestion;
    private String indicationsQuestion;
    private Boolean avoirJustificationYN;
    private Integer typeQuestion;
    private Integer scoreTotal;
    private Integer caratereAccepte;
    private Integer nbreValeurMinimal;
    private Integer nbreCaratereMaximal;
    private String qPrecedent;
    private String qSuivant;


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

    public String getDetailsQuestion() {
        return detailsQuestion;
    }

    public void setDetailsQuestion(String detailsQuestion) {
        this.detailsQuestion = detailsQuestion;
    }

    public String getIndicationsQuestion() {
        return indicationsQuestion;
    }

    public void setIndicationsQuestion(String indicationsQuestion) {
        this.indicationsQuestion = indicationsQuestion;
    }

    public Boolean getAvoirJustificationYN() {
        return avoirJustificationYN;
    }

    public void setAvoirJustificationYN(Boolean avoirJustificationYN) {
        this.avoirJustificationYN = avoirJustificationYN;
    }

    public Integer getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(Integer typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    public Integer getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Integer scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public Integer getCaratereAccepte() {
        return caratereAccepte;
    }

    public void setCaratereAccepte(Integer caratereAccepte) {
        this.caratereAccepte = caratereAccepte;
    }

    public Integer getNbreValeurMinimal() {
        return nbreValeurMinimal;
    }

    public void setNbreValeurMinimal(Integer nbreValeurMinimal) {
        this.nbreValeurMinimal = nbreValeurMinimal;
    }

    public Integer getNbreCaratereMaximal() {
        return nbreCaratereMaximal;
    }

    public void setNbreCaratereMaximal(Integer nbreCaratereMaximal) {
        this.nbreCaratereMaximal = nbreCaratereMaximal;
    }

    public String getQPrecedent() {
        return qPrecedent;
    }

    public void setQPrecedent(String qPrecedent) {
        this.qPrecedent = qPrecedent;
    }

    public String getQSuivant() {
        return qSuivant;
    }

    public void setQSuivant(String qSuivant) {
        this.qSuivant = qSuivant;
    }


    @Override
    public String toString() {
        return this.libelle.toString();
    }
}
