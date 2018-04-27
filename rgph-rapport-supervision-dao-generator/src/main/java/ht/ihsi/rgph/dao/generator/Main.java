package ht.ihsi.rgph.dao.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by JFDuverseau on 8/12/2017.
 */
public class Main {

    //region DYNAMIC QUESTION
    public static final int DATABASE_VERSION = 1;
    public static final String DESTINATION_PACKAGE_NAME="ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities";

    public static final String PERSONNEL_OBJECT="Personnel";
    public static final String FORMULAIRE_EXERCICE_OBJECT="FormulaireExercices";
    public static final String AGENT_EVALUATION_EXERCICE_OBJECT="Agent_Evaluation_Exercices";
    public static final String QUESTION_FORMULAIREEXERCICE_OBJECT="Question_FormulaireExercices";
    public static final String QUESTIONS_OBJECT="Questions";
    public static final String REPONSES_OBJECT="Reponses";
    public static final String JUSTIFICATION_REPONSES_OBJECT="JustificationReponses";
    public static final String REPONSE_ENTREE_OBJECT="ReponseEntree";
    public static final String AGENT_RAPPORT_ENTREE_OBJECT="AgentRapport";
    public static final String DEPARTEMENT_OBJECT ="Departement";
    public static final String COMMUNE_OBJECT ="Commune";
    public static final String VQSE_OBJECT ="Vqse";

    public static final String TBL_PERSONNEL_OBJECT="Tbl_Personnel";
    public static final String TBL_FORMULAIRE_EXERCICE_OBJECT="Tbl_FormulaireExercices";
    public static final String TBL_QUESTION_FORMULAIREEXERCICE_OBJECT="Tbl_Question_FormulaireExercices";
    public static final String TBL_AGENT_EVALUATION_EXERCICE_OBJECT="Tbl_Agent_Evaluation_Exercices";
    public static final String TBL_QUESTIONS_OBJECT="Tbl_Questions";
    public static final String TBL_REPONSES_OBJECT="Tbl_Reponses";
    public static final String TBL_JUSTIFICATION_REPONSES_OBJECT="Tbl_JustificationReponses";
    public static final String TBL_REPONSE_ENTREE_OBJECT="Tbl_ReponseEntree";
    public static final String TBL_AGENT_RAPPORT_ENTREE_OBJECT="Tbl_AgentRapport";

    public static final String TBL_DEPARTEMENT ="tbl_departement";
    public static final String TBL_COMMUNE ="tbl_commune";
    public static final String TBL_VQSE ="tbl_vqse";
    //endregion

    public static void main(String args[]) throws Exception {

        Schema schema= new Schema(DATABASE_VERSION, DESTINATION_PACKAGE_NAME);

        //region DYNAMIC QUESTION
        Entity personnel= schema.addEntity(PERSONNEL_OBJECT);
        personnel.setTableName(TBL_PERSONNEL_OBJECT);
        GenericEntityDao.createPersonnelEntity(personnel);

        Entity formulaireExercices= schema.addEntity(FORMULAIRE_EXERCICE_OBJECT);
        formulaireExercices.setTableName(TBL_FORMULAIRE_EXERCICE_OBJECT);
        GenericEntityDao.createFormulaireExercicesEntity(formulaireExercices);

        /* Agent_Evaluation_Exercices */
        Entity Agent_Evaluation_Exercices= schema.addEntity(AGENT_EVALUATION_EXERCICE_OBJECT);
        Agent_Evaluation_Exercices.setTableName(TBL_AGENT_EVALUATION_EXERCICE_OBJECT);
        GenericEntityDao.createAgentEvaluationExercicesEntity(Agent_Evaluation_Exercices);

        /* Question_Formulaire Exercices */
        Entity Question_FormulaireExercices= schema.addEntity(QUESTION_FORMULAIREEXERCICE_OBJECT);
        Question_FormulaireExercices.setTableName(TBL_QUESTION_FORMULAIREEXERCICE_OBJECT);
        GenericEntityDao.createQuestion_FormulaireExercicesEntity(Question_FormulaireExercices);

        Entity Questions= schema.addEntity(QUESTIONS_OBJECT);
        Questions.setTableName(TBL_QUESTIONS_OBJECT);
        GenericEntityDao.createQuestionsEntity(Questions);

        Entity Reponses= schema.addEntity(REPONSES_OBJECT);
        Reponses.setTableName(TBL_REPONSES_OBJECT);
        GenericEntityDao.createReponsesEntity(Reponses);

        Entity JustificationReponses= schema.addEntity(JUSTIFICATION_REPONSES_OBJECT);
        JustificationReponses.setTableName(TBL_JUSTIFICATION_REPONSES_OBJECT);
        GenericEntityDao.createJustificationReponsesEntity(JustificationReponses);

        Entity ReponseEntree= schema.addEntity(REPONSE_ENTREE_OBJECT);
        ReponseEntree.setTableName(TBL_REPONSE_ENTREE_OBJECT);
        GenericEntityDao.createReponseEntreeEntity(ReponseEntree);

        Entity agentRapport= schema.addEntity(AGENT_RAPPORT_ENTREE_OBJECT);
        ReponseEntree.setTableName(TBL_AGENT_RAPPORT_ENTREE_OBJECT);
        GenericEntityDao.createAgentRapportEntity(agentRapport);


        Entity departement=schema.addEntity(DEPARTEMENT_OBJECT);
        departement.setTableName(TBL_DEPARTEMENT);
        GenericEntityDao.createDepartementEntity(departement);

        Entity commune=schema.addEntity(COMMUNE_OBJECT);
        commune.setTableName(TBL_COMMUNE);
        GenericEntityDao.createCommuneEntity(commune);

        Entity vqse=schema.addEntity(VQSE_OBJECT);
        vqse.setTableName(TBL_VQSE);
        GenericEntityDao.createVqseEntity(vqse);

        new DaoGenerator().generateAll(schema, "../app/src/main/java");
    }
}
