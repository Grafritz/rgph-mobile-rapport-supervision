package ht.ihsi.rgph.rapport.supervision.Managers;


import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.AgentRapport;
import ht.ihsi.rgph.rapport.supervision.Exceptions.ManagerException;
import ht.ihsi.rgph.rapport.supervision.Exceptions.TextEmptyException;
import ht.ihsi.rgph.rapport.supervision.Models.AgentRapportModel;
import ht.ihsi.rgph.rapport.supervision.Models.Agent_Evaluation_ExercicesModel;
import ht.ihsi.rgph.rapport.supervision.Models.PersonnelModel;
import ht.ihsi.rgph.rapport.supervision.Models.ReponseEntreeModel;

/**
 * Created by jadme on 3/22/2016.
 */
public interface CURecordMngr {


    PersonnelModel savePersonnel(PersonnelModel personnelModel) throws ManagerException;

    PersonnelModel SavePersonnel(long id, PersonnelModel personnelModel) throws ManagerException, TextEmptyException;
    /**
     * Save a new entity
     *
     * @param entite
     * @param <T>    the type of the entity
     * @return
     */
    <T> T saveEntity(T entite) throws ManagerException;

    PersonnelModel updatePersonnel(PersonnelModel personnelModel) throws ManagerException;

    PersonnelModel updateAllPersonnel(long persId, String userCode) throws ManagerException;
    /**
     * Update an entity
     *
     * @param entite
     * @param <T>    the entity type.
     * @return T entity.
     */
    <T> T updateEntity(T entite) throws ManagerException;

    void closeDbConnections();

    ReponseEntreeModel InsertReponseEntree(ReponseEntreeModel reponseEntreeModel) throws ManagerException, TextEmptyException;

    Agent_Evaluation_ExercicesModel InsertAgent_Evaluation_Exercices(Agent_Evaluation_ExercicesModel agent_evaluation_exercicesModel) throws ManagerException;

    AgentRapportModel InsertAgentRapport(AgentRapportModel agentRapport) throws ManagerException;

    AgentRapportModel updateAgentRapport(AgentRapportModel agentRapportModel) throws ManagerException;


}
