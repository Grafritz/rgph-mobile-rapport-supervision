package ht.ihsi.rgph.rapport.supervision.Mappers;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.AgentRapport;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Agent_Evaluation_Exercices;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Commune;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Departement;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.FormulaireExercices;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.JustificationReponses;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Personnel;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.PersonnelDao;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Questions;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.ReponseEntree;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Reponses;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Vqse;
import ht.ihsi.rgph.rapport.supervision.Constant.Constant;
import ht.ihsi.rgph.rapport.supervision.Models.AgentRapportModel;
import ht.ihsi.rgph.rapport.supervision.Models.Agent_Evaluation_ExercicesModel;
import ht.ihsi.rgph.rapport.supervision.Models.CommuneModel;
import ht.ihsi.rgph.rapport.supervision.Models.DepartementModel;
import ht.ihsi.rgph.rapport.supervision.Models.FormulaireExercicesModel;
import ht.ihsi.rgph.rapport.supervision.Models.JustificationReponsesModel;
import ht.ihsi.rgph.rapport.supervision.Models.KeyValueModel;
import ht.ihsi.rgph.rapport.supervision.Models.PersonnelModel;
import ht.ihsi.rgph.rapport.supervision.Models.QuestionsModel;
import ht.ihsi.rgph.rapport.supervision.Models.ReponseEntreeModel;
import ht.ihsi.rgph.rapport.supervision.Models.ReponsesModel;
import ht.ihsi.rgph.rapport.supervision.Models.RowDataListModel;
import ht.ihsi.rgph.rapport.supervision.Models.VqseModel;
import ht.ihsi.rgph.rapport.supervision.Utilities.Shared_Preferences;
import ht.ihsi.rgph.rapport.supervision.Utilities.Tools;

/**
 * Created by jadme on 3/23/2016.
 */
public class ModelMapper {
    //region Map To BaseModel

    public static KeyValueModel MapTo(String key, String value) {
        KeyValueModel kvm = new KeyValueModel(key, value);
        return kvm;
    }

    public static List<CommuneModel> MapToCommune(List<Commune> coms) {
        List<CommuneModel> result = new ArrayList<>() ;
        if(coms!=null && coms.size()>0) {
            for (Commune com : coms) {
                CommuneModel r = new CommuneModel();
                r.setComId(com.getComId());
                r.setComNom(com.getComNom());
                r.setDeptId(com.getDeptId());
                result.add(r);
            }
        }
        return result;
    }

    public static List<VqseModel> MapToVqse(List<Vqse> vqses) {
        List<VqseModel> result = new ArrayList<>() ;
        if(vqses!=null && vqses.size()>0) {
            for (Vqse com : vqses) {
                VqseModel r = new VqseModel();
                r.setVqseId(com.getVqseId());
                r.setVqseNom(com.getVqseNom());
                r.setComId(com.getComId());
                result.add(r);
            }
        }
        return result;
    }

    public static AgentRapport MapTo(AgentRapportModel entity) {
        AgentRapport m = new AgentRapport();
        m.setCodeAgent(entity.getCodeAgent()) ;
        m.setNomCompletAgent(entity.getNomCompletAgent());
        m.setCommentairesGeneraux(entity.getCommentairesGeneraux());
        m.setScoreFinalAtteint(entity.getScoreFinalAtteint());

        m.setScoreOui1(entity.getScoreOui1());
        m.setScoreNon2(entity.getScoreNon2());
        m.setScoreMoyennement3(entity.getScoreMoyennement3());
        m.setScoreHorsObservation4(entity.getScoreHorsObservation4());

        m.setScoreUneFois1(entity.getScoreUneFois1());
        m.setScoreAuMoins2Fois2(entity.getScoreAuMoins2Fois2());
        m.setScoreNon3(entity.getScoreNon3());

        m.setCreatedBy(entity.getCreatedBy());
        m.setDateCreated(entity.getDateCreated());
        return m;
    }
    public static Agent_Evaluation_ExercicesModel MapTo(Agent_Evaluation_Exercices entity) {
        Agent_Evaluation_ExercicesModel m = new Agent_Evaluation_ExercicesModel();
        m.setCodeExercice(entity.getCodeExercice()) ;
        m.setPersonnelId(entity.getCodeExercice());
        m.setDureeEvaluationEnSeconde(entity.getDureeEvaluationEnSeconde());
        m.setDureeDuRepondantEnSeconde(entity.getDureeDuRepondantEnSeconde());
        m.setDateDebutEvaluationDuRepondant(entity.getDateDebutEvaluationDuRepondant());
        m.setDateFinEvaluationDuRepondant(entity.getDateFinEvaluationDuRepondant());
        return m;
    }

    public static Agent_Evaluation_Exercices MapTo(Agent_Evaluation_ExercicesModel entity) {
        Agent_Evaluation_Exercices m = new Agent_Evaluation_Exercices();
        m.setCodeExercice(entity.getCodeExercice()) ;
        m.setPersonnelId(entity.getPersonnelId());
        m.setDureeEvaluationEnSeconde(entity.getDureeEvaluationEnSeconde());
        m.setDureeDuRepondantEnSeconde(entity.getDureeDuRepondantEnSeconde());
        m.setDateDebutEvaluationDuRepondant(entity.getDateDebutEvaluationDuRepondant());
        m.setDateFinEvaluationDuRepondant(entity.getDateFinEvaluationDuRepondant());
        return m;
    }

    public static PersonnelModel MapTo(Personnel entity) {
        PersonnelModel m = new PersonnelModel();
        m.setPersId(entity.getPersId());
        m.setSdeId(entity.getSdeId());
        m.setNom(entity.getNom());
        m.setPrenom(entity.getPrenom());
        m.setSexe(entity.getSexe());
        m.setNomUtilisateur(entity.getNomUtilisateur());
        m.setMotDePasse(entity.getMotDePasse());
        m.setEmail(entity.getEmail());
        m.setDeptId(entity.getDeptId());
        m.setComId(entity.getComId());
        m.setVqseId(entity.getVqseId());
        m.setZone(entity.getZone());
        m.setCodeDistrict(entity.getCodeDistrict());
        m.setEstActif(entity.getEstActif());
        m.setProfileId(entity.getProfileId());
        return m;
    }

    public static Personnel MapTo(PersonnelModel entity) {
        Personnel m = new Personnel();
        //m.setPersId(entity.getPersId());
        m.setSdeId(entity.getSdeId());
        m.setNom(entity.getNom());
        m.setPrenom(entity.getPrenom());
        m.setSexe(entity.getSexe());
        m.setNomUtilisateur(entity.getNomUtilisateur());
        m.setMotDePasse(entity.getMotDePasse());
        m.setEmail(entity.getEmail());
        m.setDeptId(entity.getDeptId());
        m.setComId(entity.getComId());
        m.setVqseId(entity.getVqseId());
        m.setZone(entity.getZone());
        m.setCodeDistrict(entity.getCodeDistrict());
        m.setEstActif(entity.getEstActif());
        m.setProfileId(entity.getProfileId());
        return m;
    }

    public static DepartementModel MapTo(Departement entity){
       DepartementModel m = new DepartementModel();
       m.setDeptId(entity.getDeptId());
       m.setDeptNom(entity.getDeptNom());
       return m;
    }

    public static CommuneModel MapTo(Commune entity) {
        CommuneModel m = new CommuneModel();
        m.setComId(entity.getComId());
        m.setComNom(entity.getComNom());
        m.setDeptId(entity.getDeptId());
        return m;
    }

     public static VqseModel MapTo(Vqse entity) {
         VqseModel m = new VqseModel();
         m.setVqseId(entity.getVqseId());
         m.setVqseNom(entity.getVqseNom());
         m.setComId(entity.getComId());
         return m;
     }

    /*public static DomainEtudeModel MapTo(DomainEtude entitiy){
        m.setcode(entity.getcode());
        m.setnomDomaine(entity.getnomDomaine());
    }

    public static PaysModel MapTo(Pays entitiy){
        m.setcodePays(entity.getcodePays());
        m.setnomPays(entity.getnomPays());
    }*/

    public static ReponsesModel MapTo(Reponses entity) {
        ReponsesModel m = new ReponsesModel();
        m.setCodeQuestion(entity.getCodeQuestion());
        m.setCodeReponse(entity.getCodeReponse());
        m.setCodeReponseManuel(entity.getCodeReponseManuel());
        m.setLibelleReponse(entity.getLibelleReponse());
        m.setIsCorrect(entity.getIsCorrect());
        m.setScoreTotal(entity.getScoreTotal());
        m.setEstEnfant(entity.getEstEnfant());
        m.setAvoirEnfant(entity.getAvoirEnfant());
        m.setCodeParent(entity.getCodeParent());
        return m;
    }

    public static JustificationReponsesModel MapTo(JustificationReponses entity) {
        JustificationReponsesModel m = new JustificationReponsesModel();
        m.setCodeQuestion(entity.getCodeQuestion());
        m.setCodeJustification(entity.getCodeJustification());
        m.setLibelle(entity.getLibelle());
        m.setIsCorrect(entity.getIsCorrect());
        return m;
    }

    public static ReponseEntree MapTo(ReponseEntreeModel entity) {
        ReponseEntree m = new ReponseEntree();
        m.setCodeAgent(entity.getCodeAgent());
        m.setCodeFormulaireExercice(entity.getCodeFormulaireExercice());
        m.setCodeQuestion(entity.getCodeQuestion());
        m.setCodeReponse(entity.getCodeReponse());
        m.setCodeReponseEntree(entity.getCodeReponseEntree());
        m.setScoreReponse(entity.getScoreReponse());
        m.setCreatedBy(entity.getCreatedBy());
        m.setDateCreated(entity.getDateCreated());
        m.setModifBy(entity.getModifBy());
        m.setDateModif(entity.getDateModif());
        return m;
    }

    public static FormulaireExercicesModel MapTo(FormulaireExercices entity) {
        FormulaireExercicesModel m = new FormulaireExercicesModel();
        m.setCodeExercice(entity.getCodeExercice());
        m.setLibelleExercice(entity.getLibelleExercice());
        m.setDescriptions(entity.getDescriptions());
        m.setInstructions(entity.getInstructions());
        m.setRappelExercice(entity.getRappelExercice());
        m.setTypeEvaluation(entity.getTypeEvaluation());
        m.setStatut(entity.getTypeEvaluation());
        m.setStatut(entity.getStatut());
        m.setDureeEnSeconde(entity.getDureeEnSeconde());
        return m;
    }
    //endregion

    //region Map List<Entity>
    public static List<RowDataListModel> MapToRows(Shared_Preferences SPref, List<Personnel> personnelList) {
        List<RowDataListModel> result=new ArrayList<>() ;
        if(personnelList != null && personnelList.size() > 0) {
            for (Personnel personnel : personnelList) {
                RowDataListModel r = new RowDataListModel();
                r.setId(personnel.getPersId());
                r.setTitle("" + personnel.getPrenom() + " " + personnel.getNom().toUpperCase());
                String desc = " <b>SDE :</b> " + personnel.getSdeId()
                        + "<br /><b>Kont:</b> " + personnel.getNomUtilisateur()
                        + " | <b>Pòs:</b> " + Tools.getString_Reponse("" + personnel.getProfileId(), PersonnelDao.Properties.ProfileId.columnName)
                        + " | <b>Estati :</b> " + Tools.getString_Reponse((personnel.getEstActif() ? "1" : "0"), PersonnelDao.Properties.EstActif.columnName);
                r.setDesc(desc);

                r.setIsComplete(true);
                r.setIsModuleMenu(false);
                r.setModel(ModelMapper.MapTo(personnel));

                if (SPref != null) {
                    if (SPref.getProfileId() == Constant.PRIVILEGE_DEVELOPPEUR) {
                        result.add(r);
                    } else {
                        if (SPref.getPersId() == personnel.getPersId()) {
                            result.add(r);
                        } else if (personnel.getProfileId() != 201) {
                            result.add(r);
                        }
                    }
                }
            }
        }else{
        }
        return result;
    }

    public static List<QuestionsModel> MapToQuestionsModel(List<Questions> questionsList) {
        List<QuestionsModel> result = new ArrayList<>() ;
        if(questionsList!=null && questionsList.size()>0) {
            for (Questions questions : questionsList) {
                result.add(MapToQuestionsModel(questions));
            }
        }
        return result;
    }

    public static List<ReponsesModel> MapTo(List<Reponses> questionReponse) {
        List<ReponsesModel> result = new ArrayList<ReponsesModel>();
        for (Reponses qReponseModel : questionReponse) {
            result.add(MapTo(qReponseModel));
        }
        return result;
    }

    public static List<JustificationReponsesModel> MapToJustification(List<JustificationReponses> questionReponse) {
        List<JustificationReponsesModel> result = new ArrayList<JustificationReponsesModel>();
        for (JustificationReponses qReponseModel : questionReponse) {
            result.add(MapTo(qReponseModel));
        }
        return result;
    }

    public static List<Agent_Evaluation_ExercicesModel> MapToRows_Agent_EvaluationExercices(List<Agent_Evaluation_Exercices> questionReponse) {
        List<Agent_Evaluation_ExercicesModel> result = new ArrayList<Agent_Evaluation_ExercicesModel>();
        for (Agent_Evaluation_Exercices qReponseModel : questionReponse) {
            result.add(MapTo(qReponseModel));
        }
        return result;
    }
    //endregion

    //region "Map To EntityModel"
    public static QuestionsModel MapToQuestionsModel(Questions questions) {
        QuestionsModel qModel = new QuestionsModel();
        qModel.setCodeQuestion(questions.getCodeQuestion());
        qModel.setLibelle(questions.getLibelle());
        qModel.setDetailsQuestion(questions.getDetailsQuestion());
        qModel.setIndicationsQuestion(questions.getIndicationsQuestion());
        qModel.setAvoirJustificationYN(questions.getAvoirJustificationYN());
        qModel.setTypeQuestion(questions.getTypeQuestion());
        qModel.setScoreTotal(questions.getScoreTotal());
        qModel.setCaratereAccepte(questions.getCaratereAccepte());
        qModel.setNbreValeurMinimal(questions.getNbreValeurMinimal());
        qModel.setNbreCaratereMaximal(questions.getNbreCaratereMaximal());
        qModel.setQPrecedent(questions.getQPrecedent());
        qModel.setQSuivant(questions.getQSuivant());
        return qModel;
    }
    //endregion

    //region Map To Entity
   /* public static Batiment MapToBatiment(BatimentModel batiment) {
        Batiment bat = new Batiment();
        //bat.setBatimentId(batiment.getBatimentId());
        bat.setDeptId(batiment.getDeptId());
        bat.setComId(batiment.getComId());
        bat.setVqseId(batiment.getVqseId());
        bat.setSdeId(batiment.getSdeId());
        bat.setZone(batiment.getZone());
        bat.setDisctrictId(batiment.getDisctrictId());
        bat.setQhabitation(batiment.getQhabitation());
        bat.setQlocalite(batiment.getQlocalite());
        bat.setQadresse(batiment.getQadresse());
        bat.setQrec(batiment.getQrec());
        bat.setQrgph(batiment.getQrgph());
        bat.setQb1Etat(batiment.getQb1Etat());
        bat.setQb2Type(batiment.getQb2Type());
        bat.setQb3NombreEtage(batiment.getQb3NombreEtage());
        bat.setQb4MateriauMur(batiment.getQb4MateriauMur());
        bat.setQb5MateriauToit(batiment.getQb5MateriauToit());
        bat.setQb6StatutOccupation(batiment.getQb6StatutOccupation());
        bat.setQb7Utilisation1(batiment.getQb7Utilisation1());
        bat.setQb7Utilisation2(batiment.getQb7Utilisation2());
        bat.setQb8NbreLogeCollectif(batiment.getQb8NbreLogeCollectif());
        bat.setQb8NbreLogeIndividuel(batiment.getQb8NbreLogeIndividuel());
        bat.setStatut(batiment.getStatut());
        bat.setDateEnvoi(batiment.getDateEnvoi());
        bat.setIsValidated(batiment.getIsValidated());
        bat.setIsSynchroToAppSup(batiment.getIsSynchroToAppSup());
        bat.setIsSynchroToCentrale(batiment.getIsSynchroToCentrale());
        bat.setDateDebutCollecte(batiment.getDateDebutCollecte());
        bat.setDateFinCollecte(batiment.getDateFinCollecte());
        bat.setDureeSaisie(batiment.getDureeSaisie());
        bat.setIsFieldAllFilled(batiment.getIsFieldAllFilled());
        bat.setIsContreEnqueteMade(batiment.getIsContreEnqueteMade());
        bat.setLatitude(batiment.getLatitude());
        bat.setLongitude(batiment.getLongitude());
        bat.setCodeAgentRecenceur(batiment.getCodeAgentRecenceur());
        return bat;
    }*/
    //endregion

    public static Shared_Preferences MapToPreferences(Context context, PersonnelModel entity) {
        Shared_Preferences sharedPreferences = new Shared_Preferences(context);
        sharedPreferences.setPersId(entity.getPersId());
        sharedPreferences.setSdeId(entity.getSdeId());
        sharedPreferences.setNom(entity.getNom());
        sharedPreferences.setPrenom(entity.getPrenom());
        sharedPreferences.setPrenomEtNom();
        sharedPreferences.setSexe(entity.getSexe());
        sharedPreferences.setNomUtilisateur(entity.getNomUtilisateur());
        sharedPreferences.setMotDePasse(entity.getMotDePasse());
        sharedPreferences.setEmail(entity.getEmail());
        sharedPreferences.setEstActif(entity.getEstActif());
        sharedPreferences.setProfileId(entity.getProfileId());
        sharedPreferences.setDeptId(entity.getDeptId());
        sharedPreferences.setComId(entity.getComId());
        sharedPreferences.setVqseId(entity.getVqseId());

        sharedPreferences.setZone(entity.getZone());
        sharedPreferences.setCodeDsitri(entity.getCodeDistrict());

        sharedPreferences.setIsConnected(entity.getIsConnected());
        sharedPreferences.setprefLastLogin("");
        return sharedPreferences;
    }//

    //
}// END CLASS
//
