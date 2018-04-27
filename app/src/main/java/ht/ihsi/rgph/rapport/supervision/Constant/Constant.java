package ht.ihsi.rgph.rapport.supervision.Constant;

import android.view.Gravity;

/**
 * Created by ajordany on 3/23/2016.
 */
 public interface Constant {

    String DATABASE_NAME="rgph_rapport_supervision";

    //region STATIC DATA FILE NAME
    String TBL_QUESTIONS = "data_questions.json";
    String TBL_REPONSES = "data_reponses.json";
    String TBL_JUSTIFICATION_REPONSE = "data_justification.json";
    String TBL_FORMULAIRE_EXERCICES = "data_formulaire_exercices.json";
    String TBL_QUESTIONS_FORMULAIRE_EXERCICES = "data_question_formulaire_exercices.json";
    String TBL_DEPARTEMENT = "tbl_departement.json";
    String TBL_COMMUNE= "tbl_commune.json";
    String TBL_VQSE= "tbl_vqse.json";
    String TBL_PAYS= "tbl_pays.json";

    String DATA_TBL_PERSONNEL = "data_personnel.json";

    String TBL_DOMAINE_ETUDE= "tbl_domaine_etude.json";
    //endregion

    //region LIST MODULE PARAM
    String PARAM_TYPE_FORMULAIRE = "PARAM_TYPE_FORMULAIRE";
    String PARAM_LIST_TYPE ="PARAM_LIST_TYPE";
    String PARAM_ACTION_BAR_TITLE ="PARAM_ACTION_BAR_TITLE";
    String PARAM_MODULE_STATUT ="PARAM_MODULE_STATUT";
    String PARAM_MODULE_ID ="PARAM_MODULE_ID";
    String PARAM_MODEL="PARAM_MODEL";
    String PARAM_QUESTIONNAIRE_FORMULAIRE="PARAM_QUESTION";
    String PARAM_GRAND_TITRE_HEADER_ONE ="PARAM_HEADER_LIST_ONE";
    String PARAM_SOUS_TITRE_HEADER_TWO ="PARAM_HEADER_LIST_TWO";
    String PARAM_ID = "PARAM_ID";

    String PARAM_FORM_HEADER_ONE="HEADER_FORM_ONE";
    String PARAM_FORM_HEADER_TWO="HEADER_FORM_TWO";
    String PARAM_NUMERO_ORDRE_LOG_INDIVIDUEL="PARAM_NUMERO_ORDRE_LOG_INDIVIDUEL";
    String PARAM_NOM_AGENT="PARAM_NOM_AGENT";

    //endregion

    //region MODULE NAME
    int LIST_MODULE_EXERCICES = 1;
    int LIST_COMPTE_UTILISATEUR = 2;
    int LIST_TYPE_EXERCICE = 3;
    //endregion
    int EXERCICE_ENTRAINEMENT_1 = 1;
    int EXERCICE_FORMATIVE_2 = 2;
    int EXERCICE_SOMMATIVE_3 = 3;
    int EXERCICE_OBSERVATION_4 = 4;


    //region []
    //endregion

    //region FORMULAIRE - MODULE
    int FORMULAIRE_BATIMENT = 1;
    int FORMULAIRE_LOGEMENT_COLLECTIF = 2;
    int FORMULAIRE_LOGEMENT_INDIVIDUEL = 3;
    int FORMULAIRE_MENAGE = 4;
    int FORMULAIRE_INDIVIDUS = 5;
    int FORMULAIRE_DECES = 6;
    int FORMULAIRE_EMIGRE = 7;
    int FORMULAIRE_EVALUATION_MENAGE = 8;

    String NON_0 = "false";
    String OUI_1 = "true";

    int Non_0 = 0;
    int Oui_1 = 1;
    int Non_2 = 2;
    int Moyennement_3 = 3;
    int HorsObservation_4 = 4;
    int UneFois_5 = 5;
    int AuMoins2Fois_6 = 6;
    int Non_7 = 7;

    int ACTIF = 1;
    int DESACTIVE = 0;
    int PRIVILEGE_DEVELOPPEUR = 99;
    int PRIVILEGE_SUPERVISEUR = 7;
    int PRIVILEGE_AGENT = 8;
    int PRIVILEGE_ASTIC = 9;

    int ACTION_NOUVEAU = 0;
    int ACTION_AFFICHER = 1;
    int ACTION_MOFIDIER = 2;
    int ACTION_KONTINYE = 3;
    int ACTION_LISTER = 4;
    int ACTION_RAPPORT = 5;

    int imeActionId_EtReponse_6 = 6;

    //endregion
    String MODULE_BATIMENT = "MODULE_BATIMENT";
    String CODE_MODULE = "CODE_MODULE";

    //region STATUT MODULE
    int STATUT_MODULE_KI_FINI_1 = 1;
    int STATUT_MODULE_KI_MAL_RANPLI_2 = 2;
    int STATUT_MODULE_KI_PA_FINI_3 = 3;

    int STATUT_RANPLI_NET_11 = 11;
    int STATUT_PA_FIN_RANPLI_22 = 22;
    int STATUT_PA_RANPLI_DITOU_33 = 33;
    //endregion

    //region CONTRAINTE REPONSE
    int CHIFFRE = 1;
    int LETTRE = 2;
    int CHIFFRE_LETTRE = 3;
    //endregion

    //region TYPE QUESTIONS
    int TYPE_QUESTION_CHOIX_1 = 1;
    int TYPE_QUESTION_SAISIE_2 = 2;
    int TYPE_QUESTION_DATE_MM_AAAA_3 = 3;
    int TYPE_QUESTION_DEUX_CHOIX_4 = 4;
    int TYPE_QUESTION_CHOIX_PAYS_5 = 5;
    int TYPE_QUESTION_CHOIX_COMMUNE_6 = 6;
    int TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7 = 7;
    int TYPE_QUESTION_CHOIX_DOMAINE_ETUDE_8 = 8;
    int TYPE_QUESTION_CHOIX_INDIVIDU_9 = 9;
    int TYPE_QUESTION_CHOIX_ADD_INDIVIDU_10 = 10;
    int TYPE_QUESTION_DATE_JJ_MM_AAAA_11 = 11;
    int TYPE_QUESTION_NBR_APPAREIL_ET_ANIMAUX_12 = 12;
    int TYPE_QUESTION_NBR_GARCON_ET_FILLE_13 = 13;
    int TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_14 = 14;
    int TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_LIER_15 = 15;
    int TYPE_QUESTION_CHOIX_AGE_16 = 16;
    int TYPE_QUESTION_CHOIX_LISTE_BOX_17 = 17;
    int TYPE_QUESTION_CHOIX_LISTE_BOX_PAYS_18 = 18;
    int TYPE_QUESTION_CHOIX_LISTE_BOX_AGE_19 = 19;
    int TYPE_QUESTION_CHOIX_LISTE_BOX_DOMAINE_ETUDE_20 = 20;
    int TYPE_QUESTION_CHOIX_COMBO1_ET_LISTE_BOX_LIER_21 = 21;
    int TYPE_QUESTION_CHOIX_NUMBER_LISTE_BOX_22 = 22;

    //endregion

    //region MANAGER TYPE
        int FORM_DATA_MANAGER =1 ;
        int CU_RECORD_MANAGER =2 ;
        int QUERY_RECORD_MANAGER =3 ;
    //endregion

    int GravityCenter = Gravity.CENTER;
    int GravityTop = Gravity.TOP;
    int GravityBottom = Gravity.BOTTOM;
    int GravityEnd = Gravity.END;

    // KEY DATA FOR PREFERENCE USE FOR LOGIN USER
    String prefKeyUserName = "keyUserName";
    String prefKeyIdGroupeUser = "KeyIdGroupeUser";

    // BATIMANT
    //region "VARIABLES BATIMENT"
    int BATIMAN_Pa_konnen_paske_li_pa_sou_je_5 = 5;
    int PA_GEN_LOT_ITILIZASYON = 4;
    int BATIMAN_OBSERVABLE_NON_5 = 5;
    int BATIMAN_OKIPE_1 = 1;
    int BATIMAN_TOUJOU_VID_2 = 2;
    int BATIMAN_PA_KA_WE_5 = 5;
    //endregion

    int LOJMAN_OKIPE_TOUTAN_MEN_MOUN_YO_LA_1 = 1;
    int LOJMAN_OKIPE_TOUTAN_MEN_MOUN_YO_PA_LA_2 = 2;
    int LOJMAN_OKIPE_YON_LE_KONSA_3 = 3;
    int LOJMAN_PA_OKIPE_4 = 4;
    int LCOL_Pansyon_Fanmi_1 = 1;
    int LCOL_Kouvan_Monaste_2 = 2;
    int LCOL_Presbite_3 = 3;
    int LCOL_Ofelina_4 = 4;
    int LCOL_Lazil_oswa_kote_granmoun_rete_oswa_kay_retrèt_6 = 6;

   // LOGEMENT
    int LOJ_KOLEKTIF = 0;
    int LOJ_ENDIVIDYEL = 1;

    // FORMULAIRE
    String STATUT_MODULE = "STATUT_MODULE";


    String DEBUT = "DEBUT";
    String FIN = "FIN";
    String STRING_VIDE = "";

    int LOAD_ALL_PARENT = 0;
    int LOAD_ALL_ENFANT = 1;




    int HOMME_1 = 1;
    int FEMME_2 = 2;

    //------ REPONSE / QUESTIONS -----//
    int REPONS_WI_1 = 1;
    int REPONS_NON_2 = 2;
    int REPONS_PA_KONNEN_3 = 3;
    int REPONS_PA_KONNEN_99 = 99;

    int LAJ_GRANMOUN_6 = 6;

    int NAN_PEYI_LETRANJE_3 = 3;
    int NIVO_INIVESITE = 7;

    int BATIMAN_SAN_ETAJ_AK_APATMAN_04 = 4;
    int BATIMAN_AK_ETAJ_AK_APATMAN_05 = 5;
    int BATIMAN_CHELTE_10 = 10;

    String Msg_Ou_Dwe_Ekri_Yon_Repons = "Ou dwe ekri yon repons";
    String Msg_Ou_Dwe_Chwazi_Yon_Repons = "Ou dwe chwazi yon repons";

    //region VARIABLES PREFERENCE
    String prefPersId = "prefPersId";
    String prefSdeId = "prefSdeId";
    String prefPrenomEtNom = "prefPrenomEtNom";
    String prefNom = "prefNom";
    String prefPrenom = "prefPrenom";
    String prefSexe = "prefSexe";
    String prefNomUtilisateur = "prefNomUtilisateur";
    String prefEmail = "prefEmail";
    String prefDeptId = "prefDeptId";
    String prefComId = "prefComId";
    String prefVqseId = "prefVqseId";
    String prefEstActif = "prefEstActif";
    String prefProfileId = "prefProfileId";
    String prefIsConnected = "prefIsConnected";
    String prefLastLogin = "prefLastLogin";
    int CLASSE_REPONSE_MODEL = 1;
    int CLASSE_KEY_VALUE_MODEL = 2;

    //endregion

}
