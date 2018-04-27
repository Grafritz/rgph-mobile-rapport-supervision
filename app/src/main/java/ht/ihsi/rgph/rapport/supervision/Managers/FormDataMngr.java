package ht.ihsi.rgph.rapport.supervision.Managers;

import java.util.List;

import ht.ihsi.rgph.rapport.supervision.Exceptions.ManagerException;
import ht.ihsi.rgph.rapport.supervision.Models.FormulaireExercicesModel;
import ht.ihsi.rgph.rapport.supervision.Models.JustificationReponsesModel;
import ht.ihsi.rgph.rapport.supervision.Models.PersonnelModel;
import ht.ihsi.rgph.rapport.supervision.Models.QuestionsModel;
import ht.ihsi.rgph.rapport.supervision.Models.ReponsesModel;

/**
 * FormDataMngr - this interface provides
 *
 * @author Jordany Arnaud
 * @version 1.0
 * @copyright (C) E-rgph 2015
 * @date 22/01/2016
 */
public  interface FormDataMngr{

     void closeDbConnections();

    /**
     * Get the first question on a module
     *
     * @param moduleId the id of the module
     * @return QuestionsModel
     * @throws ManagerException
     */
     QuestionsModel getFirstQuestionOfModule(String moduleId) throws ManagerException;

    /**
     * Get the next question
     *
     * @param id the id of question
     * @return QuestionsModel
     * @throws ManagerException
     */
     QuestionsModel getNextQuestionByCode(String id) throws ManagerException;

    /**
     *
     * @param NomUtilisateur
     * @param MotDePasse
     * @return PersonnelModel
     * @throws ManagerException
     */
    PersonnelModel getPersonnelInfo(String NomUtilisateur, String MotDePasse) throws ManagerException;

    FormulaireExercicesModel getFormulaireExercices_ByID(long codeFormulaire) throws ManagerException;
    //List<Question_FormulaireExercicesModel> getListAllQuestion_FormExercices(long idFormExercice) throws ManagerException;

    QuestionsModel getQuestions_Byid(long idQuestion) throws ManagerException;

    //List<Question_FormulaireExercices> getListAllQuestion_FormulaireExercices_ByidFormExercices(long idFormExercice) throws ManagerException;

    List<QuestionsModel> getListAllQuestions_ByidFormExercices(long idFormExercice) throws ManagerException;

    List<ReponsesModel> getListAllReponsesByQuestion(long codeQuestion) throws ManagerException;

    List<JustificationReponsesModel> getListAllJustificationReponsesByQuestion(long codeQuestion) throws ManagerException;

}
