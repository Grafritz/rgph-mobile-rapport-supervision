package ht.ihsi.rgph.dao.generator;

import de.greenrobot.daogenerator.Entity;

/**
 * Created by JFDuverseau on 8/12/2017.
 */

public class GenericEntityDao {


    public static void createPersonnelEntity(Entity entity){
        entity.addLongProperty("persId").columnName("persId").primaryKey();
        entity.addStringProperty("sdeId").columnName("sdeId");
        entity.addStringProperty("nom").columnName("nom");
        entity.addStringProperty("prenom").columnName("prenom");
        entity.addStringProperty("sexe").columnName("sexe");
        entity.addStringProperty("nomUtilisateur").columnName("nomUtilisateur");
        entity.addStringProperty("motDePasse").columnName("motDePasse");
        entity.addStringProperty("email").columnName("email");
        entity.addStringProperty("deptId").columnName("deptId");
        entity.addStringProperty("comId").columnName("comId");
        entity.addStringProperty("vqseId").columnName("vqseId");
        entity.addStringProperty("zone").columnName("zone");
        entity.addStringProperty("codeDistrict").columnName("codeDistrict");
        entity.addBooleanProperty("estActif").columnName("estActif");
        entity.addIntProperty("ProfileId").columnName("ProfileId");
        entity.addStringProperty("dateDebutCollecte").columnName("dateDebutCollecte");
        entity.addStringProperty("dateFinCollecte").columnName("dateFinCollecte");
    }

    public static void createFormulaireExercicesEntity(Entity entity){
        entity.addLongProperty("codeExercice").columnName("codeExercice").primaryKey();
        entity.addStringProperty("libelleExercice").columnName("libelleExercice");
        entity.addStringProperty("descriptions").columnName("descriptions");
        entity.addStringProperty("instructions").columnName("instructions");
        entity.addStringProperty("rappelExercice").columnName("rappelExercice");
        entity.addStringProperty("typeEvaluation").columnName("typeEvaluation");
        entity.addStringProperty("statut").columnName("statut");
        entity.addStringProperty("dureeEnSeconde").columnName("dureeEnSeconde");
        //entity.addStringProperty("createdBy").columnName("createdBy");
        //entity.addStringProperty("dateCreated").columnName("dateCreated");
    }

    public static void createAgentEvaluationExercicesEntity(Entity entity){
        entity.addLongProperty("codeExercice").columnName("codeExercice");
        entity.addLongProperty("personnelId").columnName("personnelId");
        entity.addStringProperty("dureeEvaluationEnSeconde").columnName("dureeEvaluationEnSeconde");
        entity.addStringProperty("dureeDuRepondantEnSeconde").columnName("dureeDuRepondantEnSeconde");
        entity.addStringProperty("dateDebutEvaluationDuRepondant").columnName("dateDebutEvaluationDuRepondant");
        entity.addStringProperty("dateFinEvaluationDuRepondant").columnName("dateFinEvaluationDuRepondant");
    }

    public static void createQuestion_FormulaireExercicesEntity(Entity entity){
        entity.addLongProperty("codeFormulaireExercice").columnName("codeFormulaireExercice");//.primaryKey();
        entity.addLongProperty("codeQuestion").columnName("codeQuestion");
        entity.addStringProperty("ordreQuestion").columnName("ordreQuestion");
        entity.addBooleanProperty("estDebutQuestion").columnName("estDebutQuestion");
        //entity.addStringProperty("createdBy").columnName("createdBy");
        //entity.addStringProperty("dateCreated").columnName("dateCreated");
    }

    public static void createQuestionsEntity(Entity entity){
        entity.addLongProperty("codeQuestion").columnName("codeQuestion").primaryKey();
        entity.addStringProperty("libelle").columnName("libelle");
        entity.addStringProperty("detailsQuestion").columnName("detailsQuestion");
        entity.addStringProperty("indicationsQuestion").columnName("indicationsQuestion");
        entity.addBooleanProperty("avoirJustificationYN").columnName("avoirJustificationYN");
        entity.addIntProperty("typeQuestion").columnName("typeQuestion");
        entity.addIntProperty("scoreTotal").columnName("scoreTotal");
        entity.addIntProperty("caratereAccepte").columnName("caratereAccepte");
        entity.addIntProperty("nbreValeurMinimal").columnName("nbreValeurMinimal");
        entity.addIntProperty("nbreCaratereMaximal").columnName("nbreCaratereMaximal");
        entity.addStringProperty("qPrecedent").columnName("qPrecedent");
        entity.addStringProperty("qSuivant").columnName("qSuivant");
        //entity.addStringProperty("createdBy").columnName("createdBy");
        //entity.addStringProperty("dateCreated").columnName("dateCreated");
    }

    public static void createReponsesEntity(Entity entity){
        entity.addLongProperty("codeReponse").columnName("codeReponse").primaryKey();
        entity.addLongProperty("codeReponseManuel").columnName("codeReponseManuel");
        entity.addLongProperty("codeQuestion").columnName("codeQuestion");
        entity.addStringProperty("libelleReponse").columnName("libelleReponse");
        entity.addBooleanProperty("isCorrect").columnName("isCorrect");
        entity.addIntProperty("scoreTotal").columnName("scoreTotal");
        entity.addBooleanProperty("estEnfant").columnName("estEnfant");
        entity.addBooleanProperty("avoirEnfant").columnName("avoirEnfant");
        entity.addStringProperty("codeParent").columnName("codeParent");
        //entity.addStringProperty("createdBy").columnName("createdBy");
        //entity.addStringProperty("dateCreated").columnName("dateCreated");
    }

    public static void createJustificationReponsesEntity(Entity entity){
        entity.addLongProperty("codeJustification").columnName("codeJustification").primaryKey();
        entity.addLongProperty("codeQuestion").columnName("codeQuestion");
        entity.addStringProperty("libelle").columnName("libelle");
        entity.addBooleanProperty("isCorrect").columnName("isCorrect");
        entity.addStringProperty("createdBy").columnName("createdBy");
        entity.addStringProperty("dateCreated").columnName("dateCreated");
        //entity.addStringProperty("modifBy").columnName("modifBy");
        //entity.addStringProperty("dateModif").columnName("dateModif");
    }

    public static void createReponseEntreeEntity(Entity entity){
        entity.addLongProperty("codeReponseEntree").columnName("codeReponseEntree").primaryKey();
        entity.addLongProperty("codeAgent").columnName("codeAgent");
        entity.addLongProperty("codeFormulaireExercice").columnName("codeFormulaireExercice");
        entity.addLongProperty("codeQuestion").columnName("codeQuestion");
        entity.addLongProperty("codeReponse").columnName("codeReponse");
        entity.addLongProperty("ScoreReponse").columnName("ScoreReponse");
        entity.addStringProperty("createdBy").columnName("createdBy");
        entity.addStringProperty("dateCreated").columnName("dateCreated");
        entity.addStringProperty("modifBy").columnName("modifBy");
        entity.addStringProperty("dateModif").columnName("dateModif");
    }


    public static void createAgentRapportEntity(Entity entity){
        entity.addLongProperty("codeAgent").columnName("codeAgent").primaryKey().autoincrement();
        entity.addStringProperty("nomCompletAgent").columnName("nomCompletAgent");
        entity.addStringProperty("commentairesGeneraux ").columnName("commentairesGeneraux");
        entity.addStringProperty("scoreFinalAtteint").columnName("scoreFinalAtteint");
        entity.addLongProperty("ScoreOui1").columnName("ScoreOui1");
        entity.addLongProperty("ScoreNon2").columnName("ScoreNon2");
        entity.addLongProperty("ScoreMoyennement3").columnName("ScoreMoyennement3");
        entity.addLongProperty("ScoreHorsObservation4").columnName("ScoreHorsObservation4");
        entity.addLongProperty("ScoreUneFois1").columnName("ScoreUneFois1");
        entity.addLongProperty("ScoreAuMoins2Fois2").columnName("ScoreAuMoins2Fois2");
        entity.addLongProperty("ScoreNon3").columnName("ScoreNon3");
        entity.addStringProperty("createdBy").columnName("createdBy");
        entity.addStringProperty("dateCreated").columnName("dateCreated");
    }

    //region [ Localisation ]

    public static void createDepartementEntity(Entity entity){
        entity.addStringProperty("DeptId").columnName("DeptId").unique().notNull();
        entity.addStringProperty("DeptNom").columnName("DeptNom");
    }

    public static void createCommuneEntity(Entity entity){
        entity.addStringProperty("ComId").columnName("ComId").unique().notNull();
        entity.addStringProperty("ComNom").columnName("ComNom");
        entity.addStringProperty("DeptId").columnName("DeptId").notNull();
    }

    public static void createVqseEntity(Entity entity){
        entity.addStringProperty("VqseId").columnName("VqseId").unique().notNull();
        entity.addStringProperty("VqseNom").columnName("VqseNom");
        entity.addStringProperty("ComId").columnName("ComId").notNull();
    }
    //endregion
}
