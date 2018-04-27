package ht.ihsi.rgph.rapport.supervision.Models;

/**
 * Created by JFDuverseau on 4/26/2018.
 */

public class AgentRapportModel {

    private Long codeAgent;
    private String nomCompletAgent;
    private String commentairesGeneraux ;
    private String scoreFinalAtteint;
    private Long ScoreOui1;
    private Long ScoreNon2;
    private Long ScoreMoyennement3;
    private Long ScoreHorsObservation4;
    private Long ScoreUneFois1;
    private Long ScoreAuMoins2Fois2;
    private Long ScoreNon3;
    private String createdBy;
    private String dateCreated;

    public AgentRapportModel() {
    }

    public AgentRapportModel(Long codeAgent) {
        this.codeAgent = codeAgent;
    }

    public AgentRapportModel(Long codeAgent, String nomCompletAgent, String commentairesGeneraux , String scoreFinalAtteint, Long ScoreOui1, Long ScoreNon2, Long ScoreMoyennement3, Long ScoreHorsObservation4, Long ScoreUneFois1, Long ScoreAuMoins2Fois2, Long ScoreNon3, String createdBy, String dateCreated) {
        this.codeAgent = codeAgent;
        this.nomCompletAgent = nomCompletAgent;
        this.commentairesGeneraux  = commentairesGeneraux ;
        this.scoreFinalAtteint = scoreFinalAtteint;
        this.ScoreOui1 = ScoreOui1;
        this.ScoreNon2 = ScoreNon2;
        this.ScoreMoyennement3 = ScoreMoyennement3;
        this.ScoreHorsObservation4 = ScoreHorsObservation4;
        this.ScoreUneFois1 = ScoreUneFois1;
        this.ScoreAuMoins2Fois2 = ScoreAuMoins2Fois2;
        this.ScoreNon3 = ScoreNon3;
        this.createdBy = createdBy;
        this.dateCreated = dateCreated;
    }

    public Long getCodeAgent() {
        return codeAgent;
    }

    public void setCodeAgent(Long codeAgent) {
        this.codeAgent = codeAgent;
    }

    public String getNomCompletAgent() {
        return nomCompletAgent;
    }

    public void setNomCompletAgent(String nomCompletAgent) {
        this.nomCompletAgent = nomCompletAgent;
    }

    public String getCommentairesGeneraux () {
        return commentairesGeneraux ;
    }

    public void setCommentairesGeneraux (String commentairesGeneraux ) {
        this.commentairesGeneraux  = commentairesGeneraux ;
    }

    public String getScoreFinalAtteint() {
        return scoreFinalAtteint;
    }

    public void setScoreFinalAtteint(String scoreFinalAtteint) {
        this.scoreFinalAtteint = scoreFinalAtteint;
    }

    public Long getScoreOui1() {
        return ScoreOui1;
    }

    public void setScoreOui1(Long ScoreOui1) {
        this.ScoreOui1 = ScoreOui1;
    }

    public Long getScoreNon2() {
        return ScoreNon2;
    }

    public void setScoreNon2(Long ScoreNon2) {
        this.ScoreNon2 = ScoreNon2;
    }

    public Long getScoreMoyennement3() {
        return ScoreMoyennement3;
    }

    public void setScoreMoyennement3(Long ScoreMoyennement3) {
        this.ScoreMoyennement3 = ScoreMoyennement3;
    }

    public Long getScoreHorsObservation4() {
        return ScoreHorsObservation4;
    }

    public void setScoreHorsObservation4(Long ScoreHorsObservation4) {
        this.ScoreHorsObservation4 = ScoreHorsObservation4;
    }

    public Long getScoreUneFois1() {
        return ScoreUneFois1;
    }

    public void setScoreUneFois1(Long ScoreUneFois1) {
        this.ScoreUneFois1 = ScoreUneFois1;
    }

    public Long getScoreAuMoins2Fois2() {
        return ScoreAuMoins2Fois2;
    }

    public void setScoreAuMoins2Fois2(Long ScoreAuMoins2Fois2) {
        this.ScoreAuMoins2Fois2 = ScoreAuMoins2Fois2;
    }

    public Long getScoreNon3() {
        return ScoreNon3;
    }

    public void setScoreNon3(Long ScoreNon3) {
        this.ScoreNon3 = ScoreNon3;
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
