package ht.ihsi.rgph.rapport.supervision.Managers;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.FormulaireExercices;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.FormulaireExercicesDao;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.JustificationReponses;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.JustificationReponsesDao;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Personnel;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.PersonnelDao;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Question_FormulaireExercices;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Question_FormulaireExercicesDao;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Questions;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.QuestionsDao;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Reponses;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.ReponsesDao;
import ht.ihsi.rgph.rapport.supervision.Exceptions.ManagerException;
import ht.ihsi.rgph.rapport.supervision.Mappers.ModelMapper;
import ht.ihsi.rgph.rapport.supervision.Models.FormulaireExercicesModel;
import ht.ihsi.rgph.rapport.supervision.Models.JustificationReponsesModel;
import ht.ihsi.rgph.rapport.supervision.Models.PersonnelModel;
import ht.ihsi.rgph.rapport.supervision.Models.QuestionsModel;
import ht.ihsi.rgph.rapport.supervision.Models.ReponsesModel;
import ht.ihsi.rgph.rapport.supervision.Utilities.Tools;

/**
 * Created by ajordany on 3/21/2016.
 */
public class FormDataMngrImpl extends AbstractDatabaseManager implements FormDataMngr, Serializable {

    private static FormDataMngrImpl instance;

    public FormDataMngrImpl(final Context context) {
        super(context);
    }

    //region required methods
    public static FormDataMngrImpl getInstance(Context context){

        if (instance == null) {
            instance = new FormDataMngrImpl(context);
        }

        return instance;
    }

    @Override
    public void closeDbConnections(){
      closeConnections();
        if (instance != null) {
            instance = null;
        }
    }

    //endregion

    //region additional database managers

    @Override
    public synchronized QuestionsModel getFirstQuestionOfModule(String moduleId)
            throws ManagerException {
        Log.i(MANAGERS, "Inside of getFirstQuestionOfModule! : moduleId:"+moduleId);
        QuestionsModel result=null;

        return result;
    }

    @Override
    public synchronized QuestionsModel getNextQuestionByCode(String id)
            throws ManagerException {
        Log.i(MANAGERS, "Inside of getNextQuestionByCode! id:" + id);
        QuestionsModel result=null;
        try {

        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable to get the next question of this module : " + ex.getMessage());
            throw new ManagerException("unable to get the first question of this module : ",ex);
        }
        return result;
    }


    /**
     * @param NomUtilisateur
     * @param MotDePasse
     * @return PersonnelModel
     * @throws ManagerException
     */
    @Override
    public PersonnelModel getPersonnelInfo(String NomUtilisateur, String MotDePasse) throws ManagerException {
        //Log.i(MANAGERS, "Inside of getPersonnelInfo!");
        PersonnelModel result = null;
        try {
            openReadableDb();
            Personnel personnel = daoSession.getPersonnelDao().queryBuilder()
                    .where(PersonnelDao.Properties.NomUtilisateur.eq(NomUtilisateur))
                    .where(PersonnelDao.Properties.MotDePasse.eq(MotDePasse)).unique();
            if( personnel != null ){
                result = ModelMapper.MapTo(personnel);
            }
            daoSession.clear();
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable to get Personne lInfo : " + ex.getMessage());
            throw  new ManagerException("<> unable to get Personnel Info : ",ex);
        }
        return result;
    }

    @Override
    public FormulaireExercicesModel getFormulaireExercices_ByID(long codeExercice) throws ManagerException {
        FormulaireExercicesModel result = null;
        try {
            openReadableDb();
            FormulaireExercices personnel = daoSession.getFormulaireExercicesDao().queryBuilder()
                    .where(FormulaireExercicesDao.Properties.CodeExercice.eq(codeExercice)).unique();
            if( personnel != null ){
                result = ModelMapper.MapTo(personnel);
            }
            daoSession.clear();
        }catch(Exception ex){
            //Log.e(MANAGERS, "Exception <> unable to get Personne lInfo : " + ex.getMessage());
            throw  new ManagerException("Unable to get Formulaire Exercices ByID : ",ex);
        }
        return result;
    }

    @Override
    public QuestionsModel getQuestions_Byid(long codeQuestions) throws ManagerException {
        Log.i(MANAGERS, "Inside of getQuestions_Byid! codeQuestions:" + codeQuestions);
        QuestionsModel result = null;
        try {
            openReadableDb();
            Questions questions = daoSession.getQuestionsDao().queryBuilder()
                    .where(QuestionsDao.Properties.CodeQuestion.eq(codeQuestions)).unique();
            if( questions != null ){
                result = ModelMapper.MapToQuestionsModel(questions);
            }
            daoSession.clear();
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable to get Personne lInfo : " + ex.getMessage());
            throw  new ManagerException("<> unable to get Personnel Info : ",ex);
        }
        return result;
    }

    @Override
    public synchronized List<QuestionsModel> getListAllQuestions_ByidFormExercices(long idFormExercice) throws ManagerException {
        Tools.LogCat(MANAGERS, "Inside of getListAllQuestions_ByidFormExercices! : idFormExercice:" + idFormExercice);
        List<QuestionsModel> result = null;
        try {
            openReadableDb();
            QueryBuilder<Question_FormulaireExercices> qb = daoSession.getQuestion_FormulaireExercicesDao().queryBuilder()
                    .where(Question_FormulaireExercicesDao.Properties.CodeFormulaireExercice.eq(idFormExercice));
            qb.orderAsc(Question_FormulaireExercicesDao.Properties.OrdreQuestion);

            List<Question_FormulaireExercices> questionsList = qb.list();

            if(questionsList!=null){
                result = new ArrayList<QuestionsModel>();
                for (Question_FormulaireExercices qfe : questionsList) {
                    QuestionsModel qm = getQuestions_Byid(qfe.getCodeQuestion());

                    result.add(qm);
                    //result= ModelMapper.MapToQuestionsModel(questionsList);
                }
            }else{
                Tools.LogCat(MANAGERS, "getListAllQuestions_ByidFormExercices - NULL:");
            }
            daoSession.clear();
        }catch(Exception ex){
            Tools.LogCat( "Exception <> unable to getListAllQuestions_ByidFormExercices: ", ex);
            throw new ManagerException("unable to get the first question of this module",ex);
        }
        return result;
    }

   /* @Override
    public synchronized List<QuestionsModel> getListAllQuestions_ByidFormExercicesaaaaaaaaaaaaaaa(long idFormExercice) throws ManagerException {
        Tools.LogCat(MANAGERS, "Inside of getListAllQuestions_ByidFormExercices! : idFormExercice:" + idFormExercice);
        List<QuestionsModel> result=null;
        try {
            openReadableDb();
            QueryBuilder<Questions> qb=daoSession.getQuestionsDao().queryBuilder();
            qb.join(QuestionsDao.Properties.CodeQuestion, Question_FormulaireExercices.class, Question_FormulaireExercicesDao.Properties.CodeQuestion)
                    .where(Question_FormulaireExercicesDao.Properties.CodeFormulaireExercice.eq(idFormExercice));
            //qb.orderAsc(Question_FormulaireExercicesDao.Properties.OrdreQuestion);
                    //.where(Question_FormulaireExercicesDao.Properties.EstDebut.eq(true));
            List<Questions> questionsList = qb.list();
            if(questionsList!=null){
                result= ModelMapper.MapToQuestionsModel(questionsList);
            }else{
                Tools.LogCat(MANAGERS, "getListAllQuestions_ByidFormExercices - NULL:");
            }
            daoSession.clear();
        }catch(Exception ex){
            Tools.LogCat( "Exception <> unable to getListAllQuestions_ByidFormExercices: ", ex);
            throw new ManagerException("unable to get the first question of this module",ex);
        }
        return result;
    }*/

    @Override
    public List<ReponsesModel> getListAllReponsesByQuestion(long codeQuestion) throws ManagerException {
        //Log.i(MANAGERS, "Inside of getListAllReponsesByQuestion! / FOR codeQuestion:" + idQuestion );
        List<ReponsesModel> result=null;
        try {
            openReadableDb();
            QueryBuilder<Reponses> qr = daoSession.getReponsesDao().queryBuilder()
                    .where(ReponsesDao.Properties.EstEnfant.eq(false))
                    .where(ReponsesDao.Properties.CodeQuestion.eq(codeQuestion));

            List<Reponses> reponsesList = qr.list();
            if(reponsesList!=null && reponsesList.size()>0){
                Log.i(MANAGERS, "getListAllReponsesByQuestion () reponsesList.size():" + reponsesList.size() + " / FOR codeQuestion:" + codeQuestion);
            }else{
                Log.e(MANAGERS, "getListAllReponsesByQuestion () QuestionReponses IS NULL / FOR codeQuestion:" + codeQuestion);
            }
            daoSession.clear();
            result = ModelMapper.MapTo(reponsesList);
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable search reponse by question" + ex.toString());
            throw new ManagerException("Une erreur est survenue lors de la recherche",ex);
        }
        return result;
    }

    @Override
    public List<JustificationReponsesModel> getListAllJustificationReponsesByQuestion(long codeQuestion) throws ManagerException {
        //Log.i(MANAGERS, "Inside of getListAllReponsesByQuestion! / FOR codeQuestion:" + idQuestion );
        List<JustificationReponsesModel> result=null;
        try {
            openReadableDb();
            QueryBuilder<JustificationReponses> qr = daoSession.getJustificationReponsesDao().queryBuilder()
                    .where(JustificationReponsesDao.Properties.CodeQuestion.eq(codeQuestion));

            List<JustificationReponses> reponsesList = qr.list();
            if(reponsesList!=null && reponsesList.size()>0){
                Log.i(MANAGERS, "getListAllJustificationReponsesByQuestion () size():" + reponsesList.size() + " / FOR codeQuestion:" + codeQuestion);
            }else{
                Log.e(MANAGERS, "getListAllJustificationReponsesByQuestion () IS NULL / FOR codeQuestion:" + codeQuestion);
            }
            daoSession.clear();
            result = ModelMapper.MapToJustification(reponsesList);
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable search reponse by question" + ex.toString());
            throw new ManagerException("Une erreur est survenue lors de la recherche",ex);
        }
        return result;
    }

    //endregion
}
