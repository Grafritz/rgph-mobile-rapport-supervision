package ht.ihsi.rgph.rapport.supervision.Utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ht.ihsi.rgph.rapport.supervision.Constant.Constant;
import ht.ihsi.rgph.rapport.supervision.Exceptions.TextEmptyException;
import ht.ihsi.rgph.rapport.supervision.Managers.CURecordMngr;
import ht.ihsi.rgph.rapport.supervision.Managers.FormDataMngr;
import ht.ihsi.rgph.rapport.supervision.Models.Agent_Evaluation_ExercicesModel;
import ht.ihsi.rgph.rapport.supervision.Models.BaseModel;
import ht.ihsi.rgph.rapport.supervision.Models.FormulaireExercicesModel;
import ht.ihsi.rgph.rapport.supervision.Models.JustificationReponsesModel;
import ht.ihsi.rgph.rapport.supervision.Models.KeyValueModel;
import ht.ihsi.rgph.rapport.supervision.Models.QuestionsModel;
import ht.ihsi.rgph.rapport.supervision.Models.ReponseEntreeModel;
import ht.ihsi.rgph.rapport.supervision.Models.ReponsesModel;
import ht.ihsi.rgph.rapport.supervision.R;
import ht.ihsi.rgph.rapport.supervision.Views.Adapters.RadioListAdapterKeyValue;
import ht.ihsi.rgph.rapport.supervision.Views.Adapters.RadioListJustificationReponsesAdapter;
import ht.ihsi.rgph.rapport.supervision.Views.Adapters.RadioListReponsesAdapter;

/**
 * Created by JFDuverseau on 9/2/2017.
 */

public class QuestionnaireFormulaireUtility extends BaseModel {

    //region ATTRIBUT
    //public FormDataMngr formDataMngr;
    //public CURecordMngr cuRecordMngr;
    public FormulaireExercicesModel formExercicesModel;
    private List<QuestionsModel> questionsModelList;
    public static List<ReponseEntreeModel> tempReponseEntreeModel;
    public Context context;
    public static int countRowIndex=0;

    //region [ For Questions ]
    private Long codeQuestion;

    public String LibelleQuestion;
    public String DetailsQuestion;
    public String indicationsQuestion;

    private boolean avoirJustificationYN;
    private int typeQuestion;
    private int contrainteQuestion;
    private int scoreTotal;
    private int caratereAccepte;
    private int nbreValeurMinimal;
    private int nbreCaratereMaximal;
    private String contrainteSautChampsValeur;
    private String qPrecedent;
    private String qSuivant;

    public int NbChar;

    public long getDureeSaisie(String dateFinCollecte) {
        // Joda Time
        if( dateFinCollecte.equalsIgnoreCase("") ) {
            dateFinCollecte = Tools.getDateString_MMddyyyy_HHmmss();
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("MM/dd/YYYY HH:mm:ss");
        DateTime start = dateTimeFormatter.parseDateTime(dateDebutCollecte);
        DateTime end = dateTimeFormatter.parseDateTime(dateFinCollecte);
        // duration in ms between two instants
        Duration dur = new Duration(start, end);

        Tools.LogCat("W", "dateDebutCollecte:"+ dateDebutCollecte + " | dateFinCollecte():" + dateFinCollecte + "\\ngetDureeSaisie():" + dur.getStandardSeconds() + " Sec");
        return dur.getStandardSeconds();
    }

    public String dateDebutCollecte;

    public String getDateDebutCollecte() {
        return dateDebutCollecte;
    }

    public void setDateDebutCollecte(String dateDebutCollecte) {
        this.dateDebutCollecte = dateDebutCollecte;
    }
    //endregion

    //endregion

    //region GETTER - SETTER

    public List<QuestionsModel> getQuestionsModelList() {
        return questionsModelList;
    }

    public void setQuestionsModelList(List<QuestionsModel> questionsModelList) {
        this.questionsModelList = questionsModelList;
    }

    public Long getCodeQuestion() {
        return codeQuestion;
    }

    public void setCodeQuestion(Long codeQuestion) {
        this.codeQuestion = codeQuestion;
    }

    public int getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(int typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    ContrainteReponse contrainte = new ContrainteReponse();
    public ContrainteReponse getContrainte() {
        return contrainte;
    }

    //endregion

    //region CONSTRUCTEURS
    public QuestionnaireFormulaireUtility() {
    }

    public QuestionnaireFormulaireUtility(FormulaireExercicesModel formExercicesModel,  FormDataMngr formDataMngr) {
        try {
            //this.formDataMngr = formDataMngr;
            this.formExercicesModel = formExercicesModel;

            tempReponseEntreeModel = new ArrayList<ReponseEntreeModel>();

            // Rechercher de la liste des question pour un Formulaire bien specifique
            this.questionsModelList = formDataMngr.getListAllQuestions_ByidFormExercices(formExercicesModel.getCodeExercice());

            this.LoadFirst_Question();

        } catch (Exception ex) {
            Tools.LogCat("Exception : QuestionnaireFormulaireUtility(BatimentModel) : ", ex);
        }
    }//

    //endregion

    //region "SET INFO - CONTROLS"
    public void GetQuestionInfo(QuestionsModel Q) {
        try {
            //if (Q.getCodeQuestion() == null && Q.getCodeQuestion().equals("")) {
            //    ToastUtility.LogCat(context, "Gen yon ti problèm nan fòmilè a... \n Eseye ankò tanpri");
            //    ToastUtility.ToastMessage(context, "Gen yon ti problèm nan fòmilè a... \n Eseye ankò tanpri");
            //finish();
            //} else {
            this.codeQuestion = Q.getCodeQuestion();
            this.avoirJustificationYN = Q.getAvoirJustificationYN();
            this.typeQuestion = Q.getTypeQuestion();
            this.scoreTotal = Q.getScoreTotal();
            this.caratereAccepte = Q.getCaratereAccepte();
            this.qPrecedent = Q.getQPrecedent();
            this.qSuivant = Q.getQSuivant();

            this.LibelleQuestion = Q.getLibelle();
            this.DetailsQuestion = Q.getDetailsQuestion();
            this.indicationsQuestion = Q.getIndicationsQuestion();

            // CONTRAINTES REPONSE
            contrainte.setCaractereAccepte(Q.getCaratereAccepte());
            contrainte.setNbreValeurMinimal(Q.getNbreValeurMinimal());

            contrainte.setNombreCaratereMinimal(Q.getNbreCaratereMaximal());
            contrainte.setNombreCaratere(Q.getNbreCaratereMaximal());
            contrainte.setNombreCaratereMaximal(Q.getNbreCaratereMaximal());

            if (Q.getNbreCaratereMaximal() > 0) this.NbChar = contrainte.getNombreCaratere();
            else this.NbChar = 0;
            //}
        } catch (Exception ex) {
            Tools.LogCat("Exception : GetQuestionInfo() : ", ex);
            ex.printStackTrace();
        }
    }
    //endregion

    //region "METHODE QUESTION SUIVANTE ET PRECEDENTE"
    public void LoadFirst_Question() {
        try {
            if( this.questionsModelList != null ){
                countRowIndex = 0;
                QuestionsModel questionsModel = this.questionsModelList.get(countRowIndex);
                if( questionsModel != null ){
                    GetQuestionInfo(questionsModel);
                }
            }
        } catch (Exception ex) {
            Tools.LogCat("Exception :LoadFirst_Question() : ", ex);
            ex.printStackTrace();
        }
    }//

    //
    public void Goto_QuestionSuivante() {
        try {
            if( this.questionsModelList != null ){
                countRowIndex += 1;
                QuestionsModel questionsModel = this.questionsModelList.get(countRowIndex);
                if( questionsModel != null ){
                    GetQuestionInfo(questionsModel);
                }
            }
        } catch (Exception ex) {
            Tools.LogCat(context, "Exception-Goto_QuestionSuivante() : onClick : ", ex);
            ex.printStackTrace();
        }
    }//
    //endregion

    //region SET VALUES
    public static  void SetFieldValuesQuestionInfo(Context context, FormDataMngr formDataMngr, QuestionnaireFormulaireUtility qf
            , TextView tv_GrandTitre_LibelleExercice
            , TextView tv_DescriptionsExercice
            , TextView tv_InstructionsExercice
            , TextView tv_LibelleQuestion
            , TextView tv_DetailQuestion
            , TextView tv_Reponse2
            , TextView tv_Reponse3
            , RecyclerView recyclerViewReponse
            , LinearLayout LL_RecyclerView
            , RadioListReponsesAdapter radioListReponsesAdapter, RadioListReponsesAdapter.OnItemReponsesClickListener getItemReponsesClickListener
            , RadioListJustificationReponsesAdapter radioListAdapterJustificationReponses, RadioListJustificationReponsesAdapter.OnItemClickListener getItemJustificationReponsesClickListener
            , RadioListAdapterKeyValue radioListAdapterKeyValue, RadioListAdapterKeyValue.OnItemClickListenerKeyValue getItemClickListenerKeyValue
            , EditText et_Reponse
            , Spinner sp_Reponse
            , RelativeLayout RelativeLayout_Reponse
            , Spinner sp_Reponse2
            , RelativeLayout RelativeLayout_Reponse2
            , Spinner sp_Reponse3
            , RelativeLayout RelativeLayout_Reponse3
            , LinearLayout LinearLDate
            , RelativeLayout RL_Jour
            , Spinner sp_Jour
            , Spinner sp_Mois
            , Spinner sp_Annee
            , LinearLayout LL_GaconEtFille, LinearLayout LL_AppareilEtAnimaux
            , TextView tv_Reponse, TextView tv_Gason, TextView tv_Fi
            , EditText et_Gason, EditText et_Fi , EditText et_ApareyRadyo, EditText et_Televizyon, EditText et_FrijideFrize
            , EditText et_FouElektrikFouAkGaz, EditText et_OdinatePCLaptopTabletNimerik, EditText et_BisikletMotosiklet, EditText et_VwatiMachin
            , EditText et_KannotBato, EditText et_InvetePanoSoleJeneratrisDelko, EditText et_MiletChwalBourik, EditText et_BefVach, EditText et_KochonKabrit
            , EditText et_BetVolayPoulKok
            , Button btn_Precedent
            , Button btn_Suivant) {
        try {
            //if (qf.getCodeQuestion() == null && qf.getCodeQuestion().equals("")) {
            //    ToastUtility.ToastMessage(context, "Gen yon ti problèm nan fòmilè a..." + (char) 13 + " Eseye ankò tanpri");
            //}

            //if (qf.getqPrecedent().toString().equals(Constant.DEBUT)) {
            //    btn_Precedent.setVisibility(View.INVISIBLE);
            //    if( qf.getTbl_TableName() == Constant.FORMULAIRE_BATIMENT ){
            //        btn_Precedent.setVisibility(View.VISIBLE);
            //    }
            //}else {
            //     btn_Precedent.setText(context.getString(R.string.label_QuestionPrecedente));
            //    btn_Precedent.setVisibility(View.VISIBLE);
            //}

            btn_Precedent.setVisibility(View.GONE);
            //btn_Suivant.setVisibility(View.GONE);

            String result = "";

            tv_Reponse2.setVisibility(View.GONE);
            tv_Reponse3.setVisibility(View.GONE);
            et_Reponse.setVisibility(View.GONE);
            sp_Reponse.setVisibility(View.GONE);
            RelativeLayout_Reponse.setVisibility(View.GONE);
            sp_Reponse2.setVisibility(View.GONE);
            RelativeLayout_Reponse2.setVisibility(View.GONE);
            sp_Reponse3.setVisibility(View.GONE);
            RelativeLayout_Reponse3.setVisibility(View.GONE);
            LinearLDate.setVisibility(View.GONE);
            RL_Jour.setVisibility(View.GONE);
            tv_DetailQuestion.setVisibility(View.GONE);

            LL_GaconEtFille.setVisibility(View.GONE);
            LL_AppareilEtAnimaux.setVisibility(View.GONE);
            tv_Reponse.setVisibility(View.GONE);
            LL_RecyclerView.setVisibility(View.GONE);

            et_Reponse.setInputType(InputType.TYPE_CLASS_TEXT);

            tv_LibelleQuestion.setText(  qf.LibelleQuestion );
            tv_DetailQuestion.setText( Html.fromHtml( qf.DetailsQuestion ));
            if(!qf.DetailsQuestion.toString().trim().equalsIgnoreCase("")){
                tv_DetailQuestion.setVisibility(View.VISIBLE);
            }
            ContrainteReponse contrainte = new ContrainteReponse();
            contrainte = qf.getContrainte();

            if ( qf.getQuestionsModelList().size() == QuestionnaireFormulaireUtility.countRowIndex+1) {
                btn_Suivant.setText(context.getString(R.string.label_Fin));
            }
            //if( qf.TypeEvenement == Constant.ACTION_NOUVEAU ){
            //btn_Suivant.setVisibility(View.VISIBLE);
            //}
            //if( qf.TypeEvenement == Constant.ACTION_AFFICHER ){
            //    btn_Suivant.setVisibility(View.VISIBLE);
            //}
            et_Reponse.setFilters(new InputFilter[]{new InputFilter.LengthFilter(500000000)});
            //Afficher le clavier
            //final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            //inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            //inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            //inputMethodManager.hideSoftInputFromInputMethod(getWindow().getDecorView().getRootView().getWindowToken(), 0);

            //final InputMethodManager imm2 = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            //imm.hideSoftInputFromInputMethod(view.getWindowToken(), 0);

            //codeReponseRecyclerView=null; codeReponseKeyValueModel=null;
            String msgTypeQuestion = "Ce type de question n'existe pas dans l'application...";
            switch (qf.getTypeQuestion()) {
                //region TYPE_QUESTION_CHOIX_1
                case Constant.TYPE_QUESTION_CHOIX_1:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    sp_Reponse.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();

                    // LISTE DES POSIBILITES DE REPONSES
                    qf.Load_PossibiliteReponse(context, formDataMngr, sp_Reponse);
                    if( qf.avoirJustificationYN  ){
                        // LISTE DES POSIBILITES DE REPONSES 2
                        tv_Reponse2.setVisibility(View.VISIBLE);
                        RelativeLayout_Reponse2.setVisibility(View.VISIBLE);
                        sp_Reponse2.setVisibility(View.VISIBLE);

                        qf.Load_PossibiliteJustificationReponse(context, formDataMngr, sp_Reponse2);
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_SAISIE_2
                case Constant.TYPE_QUESTION_SAISIE_2:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    if (contrainte.getCaractereAccepte() == Constant.CHIFFRE) {
                        et_Reponse.setInputType(InputType.TYPE_CLASS_NUMBER);
                    } else if (contrainte.getCaractereAccepte() == Constant.CHIFFRE_LETTRE) {
                        et_Reponse.setInputType(InputType.TYPE_CLASS_TEXT);
                    }
                    if (contrainte.getNombreCaratereMaximal() > 0) {
                        et_Reponse.setFilters(new InputFilter[]{new InputFilter.LengthFilter(contrainte.getNombreCaratereMaximal())});
                    }
                    et_Reponse.setVisibility(View.VISIBLE);
                    et_Reponse.requestFocus();
                    break;
                //endregion
                //region TYPE_QUESTION_DATE_MM_AAAA_3
                case Constant.TYPE_QUESTION_DATE_MM_AAAA_3:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    LinearLDate.setVisibility(View.VISIBLE);
                    qf.Load_Mois(context, sp_Mois);
                    qf.Load_Annee(context, sp_Annee);
                    //et_Reponse.setInputType(InputType.TYPE_CLASS_DATETIME);
                    //sp_Mois.requestFocus();
                    break;
                //endregion
                //region TYPE_QUESTION_DEUX_CHOIX_4
                case Constant.TYPE_QUESTION_DEUX_CHOIX_4:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();
                    // LISTE DES POSIBILITES DE REPONSES
                    qf.Load_PossibiliteReponse(context, formDataMngr, sp_Reponse);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_PAYS_5
                case Constant.TYPE_QUESTION_CHOIX_PAYS_5:
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();
                    // LISTE DES PAYS
                    //qf.Load_Pays( formDataMngr, sp_Reponse);
                    //qf.Load_Pays(context, formDataMngr, recyclerViewReponse, radioListAdapterKeyValue,  getItemClickListenerKeyValue);
                    Tools.ToastMessage(context, msgTypeQuestion, Gravity.CENTER);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_COMMUNE_6
                case Constant.TYPE_QUESTION_CHOIX_COMMUNE_6:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    tv_Reponse.setVisibility(View.VISIBLE);
                    tv_Reponse.setText(context.getString(R.string.label_Depatman));
                    sp_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();
                    tv_Reponse2.setVisibility(View.VISIBLE);
                    tv_Reponse2.setText(context.getString(R.string.label_Komin));
                    RelativeLayout_Reponse2.setVisibility(View.VISIBLE);
                    sp_Reponse2.setVisibility(View.VISIBLE);

                    // LISTE DES DEPARTEMENTS
                    //qf.Load_Departement( formDataMngr, sp_Reponse);
                    Tools.ToastMessage(context, msgTypeQuestion, Gravity.CENTER);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7
                case Constant.TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    tv_Reponse.setVisibility(View.VISIBLE);
                    tv_Reponse.setText(context.getString(R.string.label_Depatman));
                    sp_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();
                    tv_Reponse2.setVisibility(View.VISIBLE);
                    sp_Reponse2.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse2.setVisibility(View.VISIBLE);
                    tv_Reponse3.setVisibility(View.VISIBLE);
                    sp_Reponse3.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse3.setVisibility(View.VISIBLE);

                    // LISTE DES DEPARTEMENTS
                    //qf.Load_Departement( formDataMngr, sp_Reponse);
                    //qf.Load_ClearSpinner(sp_Reponse2, Constant.TYPE_QUESTION_CHOIX_COMMUNE_6);
                    //qf.Load_ClearSpinner(sp_Reponse3, Constant.TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7);
                    Tools.ToastMessage(context, msgTypeQuestion, Gravity.CENTER);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_DOMAINE_ETUDE_8
                case Constant.TYPE_QUESTION_CHOIX_DOMAINE_ETUDE_8:
                    sp_Reponse.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();

                    //qf.Load_DomaineEtude( formDataMngr, sp_Reponse);
                    Tools.ToastMessage(context, msgTypeQuestion, Gravity.CENTER);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_INDIVIDU_9
                case Constant.TYPE_QUESTION_CHOIX_INDIVIDU_9:
                    sp_Reponse.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();

                    //if (Constant.FORMULAIRE_EVALUATION_MENAGE == qf.getTbl_TableName()){
                    //Load_IndividuEvaluation(sp_Reponse);
                    //}else{
                    //Load_Individu(sp_Reponse);
                    //}
                    Tools.ToastMessage(context, msgTypeQuestion, Gravity.CENTER);
                    break;
                //endregion
                //region TYPE_QUESTION_DATE_JJ_MM_AAAA_11
                case Constant.TYPE_QUESTION_DATE_JJ_MM_AAAA_11:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    RL_Jour.setVisibility(View.VISIBLE);
                    LinearLDate.setVisibility(View.VISIBLE);
                    qf.Load_Jour(context, sp_Jour);
                    qf.Load_Mois(context, sp_Mois);
                    qf.Load_Annee(context, sp_Annee);
                    //et_Reponse.setInputType(InputType.TYPE_CLASS_DATETIME);
                    //sp_Mois.requestFocus();
                    break;
                //endregion
                //region TYPE_QUESTION_NBR_APPAREIL_ET_ANIMAUX_12
                case Constant.TYPE_QUESTION_NBR_APPAREIL_ET_ANIMAUX_12:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    LL_AppareilEtAnimaux.setVisibility(View.VISIBLE);

                    break;
                //endregion
                //region TYPE_QUESTION_NBR_GARCON_ET_FILLE_13
                case Constant.TYPE_QUESTION_NBR_GARCON_ET_FILLE_13:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    LL_GaconEtFille.setVisibility(View.VISIBLE);

                    if (contrainte.getNombreCaratereMaximal() > 0) {
                        et_Gason.setFilters(new InputFilter[]{new InputFilter.LengthFilter(contrainte.getNombreCaratereMaximal())});
                        et_Fi.setFilters(new InputFilter[]{new InputFilter.LengthFilter(contrainte.getNombreCaratereMaximal())});
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_14
                case Constant.TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_14:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    tv_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();
                    tv_Reponse2.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse2.setVisibility(View.VISIBLE);
                    sp_Reponse2.setVisibility(View.VISIBLE);

                    // LISTE DES POSIBILITES DE REPONSES 1
                    qf.Load_PossibiliteReponse(context, formDataMngr, sp_Reponse);
                    if( qf.avoirJustificationYN  ){
                        // LISTE DES POSIBILITES DE REPONSES 2
                        qf.Load_PossibiliteJustificationReponse(context, formDataMngr, sp_Reponse2);
                    }
                    //tv_Reponse.setText(context.getString(R.string.label_1erSousEnejiPouKwitManje));
                    //tv_Reponse2.setText(context.getString(R.string.label_2emSousEnejiPouKwitManje));

                   break;
                //endregion
                //region TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_LIER_15
                case Constant.TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_LIER_15:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();

                    //tv_Reponse.setVisibility(View.VISIBLE);
                    //tv_Reponse.setText(context.getString(R.string.label_E4B_Nivo));
                    //tv_Reponse2.setVisibility(View.VISIBLE);
                    //tv_Reponse2.setText(context.getString(R.string.label_E4B_KlasAneDetid));

                    // LISTE DES POSIBILITES DE REPONSES
                    qf.Load_PossibiliteReponse(context, formDataMngr, sp_Reponse);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_AGE_16
                case Constant.TYPE_QUESTION_CHOIX_AGE_16:
                    sp_Reponse.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();

                    //qf.Load_Age(context, sp_Reponse);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_LISTE_BOX_17
                case Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_17:
                    LL_RecyclerView.setVisibility(View.VISIBLE);

                    // LISTE DES POSIBILITES DE REPONSES
                    //qf.Load_PossibiliteReponse(context, formDataMngr, recyclerViewReponse, radioListAdapter, getItemClickListener);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_LISTE_BOX_PAYS_18
                case Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_PAYS_18:
                    LL_RecyclerView.setVisibility(View.VISIBLE);
                    // LISTE DES PAYS
                    //qf.Load_Pays(context, formDataMngr, recyclerViewReponse, radioListAdapterKeyValue, getItemClickListenerKeyValue);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_LISTE_BOX_AGE_19
                case Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_AGE_19:
                    LL_RecyclerView.setVisibility(View.VISIBLE);

                    //qf.Load_Age(context, recyclerViewReponse, radioListAdapterKeyValue, getItemClickListenerKeyValue);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_LISTE_BOX_DOMAINE_ETUDE_20
                case Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_DOMAINE_ETUDE_20:
                    LL_RecyclerView.setVisibility(View.VISIBLE);

                    //qf.Load_DomaineEtude(context, formDataMngr, recyclerViewReponse, radioListAdapterKeyValue, getItemClickListenerKeyValue);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_COMBO1_ET_LISTE_BOX_LIER_21
                case Constant.TYPE_QUESTION_CHOIX_COMBO1_ET_LISTE_BOX_LIER_21:
                    sp_Reponse.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();

                    // LISTE DES POSIBILITES DE REPONSES
                    qf.Load_PossibiliteReponse(context, formDataMngr, sp_Reponse);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_NUMBER_LISTE_BOX_22
                case Constant.TYPE_QUESTION_CHOIX_NUMBER_LISTE_BOX_22:
                    LL_RecyclerView.setVisibility(View.VISIBLE);

                    int chiffeDeDepart = 1;
                    int nombreLimite = contrainte.getNombreCaratereMaximal();
                    if (nombreLimite == 1) {
                        nombreLimite = 9;
                    } else if (nombreLimite == 2) {
                        nombreLimite = 99;
                    } else if (nombreLimite == 3) {
                        nombreLimite = 999;
                    } else if (nombreLimite == 4) {
                        nombreLimite = 9999;
                    }
                    String textAdditionnel = "";

                    //qf.Load_Quantite(context, chiffeDeDepart, nombreLimite, textAdditionnel, recyclerViewReponse, radioListAdapterKeyValue, getItemClickListenerKeyValue);
                    break;
                //endregion
                default:
            }
            //qf.SetButtonLabel(context, "", qf, btn_Suivant);

        } catch (Exception ex) {
            Tools.LogCat("Exception:INSIDE - SetFieldValuesQuestionInfo", ex);
            ex.printStackTrace();
        }
    }
    //endregion

    //region [ METHODES LOAD DATA ]
    public void Load_PossibiliteReponse(Context context, FormDataMngr formDataMngr, Spinner spinner) {
        try {
            List<ReponsesModel> reponseModelList1 = formDataMngr.getListAllReponsesByQuestion(this.getCodeQuestion());
            ArrayList<ReponsesModel> reponseModelList = new ArrayList<ReponsesModel>();
            reponseModelList.add(new ReponsesModel((long)0, (long) 0, "- " + context.getString(R.string.label_Chwazi_yon_Repons).toString() + " - "));
            if (reponseModelList1 != null) {
                reponseModelList.addAll(reponseModelList1);
            }
            // Creating adapter for spinner
            ArrayAdapter<ReponsesModel> dataAdapter = new ArrayAdapter<ReponsesModel>(context, R.layout.simple_list_item_1, reponseModelList);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            Tools.LogCat("Exception-Load_PossibiliteReponse(): getMessage: ", ex);
            Tools.AlertDialogMsg(context, ex.getMessage(), "E");
            ex.printStackTrace();
        }
    }//

    public void Load_PossibiliteJustificationReponse(Context context, FormDataMngr formDataMngr, Spinner spinner) {
        try {
            List<JustificationReponsesModel> reponseModelList1 = formDataMngr.getListAllJustificationReponsesByQuestion(this.getCodeQuestion());
            ArrayList<JustificationReponsesModel> reponseModelList = new ArrayList<JustificationReponsesModel>();
            reponseModelList.add(new JustificationReponsesModel((long)0, (long) 0, "- " + context.getString(R.string.label_Chwazi_yon_Justification).toString() + " - "));
            if (reponseModelList1 != null) {
                reponseModelList.addAll(reponseModelList1);
            }
            // Creating adapter for spinner
            ArrayAdapter<JustificationReponsesModel> dataAdapter = new ArrayAdapter<JustificationReponsesModel>(context, R.layout.simple_list_item_1, reponseModelList);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            Tools.LogCat("Exception-Load_PossibiliteReponse(): getMessage: ", ex);
            Tools.AlertDialogMsg(context, ex.getMessage(), "E");
            ex.printStackTrace();
        }
    }//

    public void Load_Jour(Context context, Spinner spinner) {
        try {
            ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Jour) + " -"));
            keyValueModels.add(new KeyValueModel("99", "- " + context.getResources().getString(R.string.msg_Pakonnen) + " [99] -"));
            for (int i = 1; i <= 31; i++) {
                if (i > 9) {
                    keyValueModels.add(new KeyValueModel("" + i, "" + i));
                } else {
                    keyValueModels.add(new KeyValueModel("" + i, "0" + i));
                }
            }
            // Creating adapter for spinner
            ArrayAdapter<KeyValueModel> dataAdapter = new ArrayAdapter<KeyValueModel>(context, R.layout.simple_list_item_1, keyValueModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            Tools.LogCat( "getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_Mois(Context context, Spinner spinner) {
        try {
            final KeyValueModel[] keyValueModels = {
                    new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Mois) + " -"),
                    new KeyValueModel("99", "- " + context.getResources().getString(R.string.msg_Pakonnen) + " [99] -"),
                    new KeyValueModel("1", "Janvier"),
                    new KeyValueModel("2", "Fevrie"),
                    new KeyValueModel("3", "Mars"),
                    new KeyValueModel("4", "Avril"),
                    new KeyValueModel("5", "Mai"),
                    new KeyValueModel("6", "Jen"),
                    new KeyValueModel("7", "Jiyè"),
                    new KeyValueModel("8", "Out"),
                    new KeyValueModel("9", "Septanm"),
                    new KeyValueModel("10", "Oktòb"),
                    new KeyValueModel("11", "Novanm"),
                    new KeyValueModel("12", "Desanm"),
            };
            // Creating adapter for spinner
            ArrayAdapter<KeyValueModel> dataAdapter = new ArrayAdapter<KeyValueModel>(context, R.layout.simple_list_item_1, keyValueModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            Tools.LogCat( "getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_Annee(Context context, Spinner spinner) {
        try {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Annee) + " -"));
            keyValueModels.add(new KeyValueModel("9999", "- " + context.getResources().getString(R.string.msg_Pakonnen) + " [9999] -"));
            for (int i = year; i >= 1900; i--) {
                keyValueModels.add(new KeyValueModel("" + i, "" + i));
            }
            // Creating adapter for spinner
            ArrayAdapter<KeyValueModel> dataAdapter = new ArrayAdapter<KeyValueModel>(context, R.layout.simple_list_item_1, keyValueModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            Tools.LogCat( "getMessage: ", ex);
            ex.printStackTrace();
        }
    }//
    //endregion

    //region "METHODE VALIDATION CHAMPS"
    public void CheckValueBefore(CURecordMngr cuRecordMngr, EditText et_Reponse
            , Spinner sp_Reponse, Spinner sp_Reponse2, Spinner sp_Reponse3, Spinner sp_Jour, Spinner sp_Mois, Spinner sp_Annee
            , EditText et_Gason, EditText et_Fi , EditText et_ApareyRadyo, EditText et_Televizyon, EditText et_FrijideFrize
            , EditText et_FouElektrikFouAkGaz, EditText et_OdinatePCLaptopTabletNimerik, EditText et_BisikletMotosiklet, EditText et_VwatiMachin
            , EditText et_KannotBato, EditText et_InvetePanoSoleJeneratrisDelko, EditText et_MiletChwalBourik, EditText et_BefVach, EditText et_KochonKabrit, EditText et_BetVolayPoulKok
            , RecyclerView recyclerViewReponse, ReponsesModel codeReponseRecyclerView, KeyValueModel codeReponseKeyValueModel
    ) throws TextEmptyException {
        try {
            String _message = "", ValReponseSaisie="";
            long ValReponse = 0, ValJustificationReponse = 0;
            ReponsesModel reponseModel = null, reponseModel2 = null;
            JustificationReponsesModel justificationReponsesModel = null;
            KeyValueModel keyValueModel = null;

            if ( this.typeQuestion == Constant.TYPE_QUESTION_CHOIX_1 ) {
                reponseModel = ((ReponsesModel) sp_Reponse.getSelectedItem());
                ValReponse = reponseModel.getCodeReponse();
                if ( ValReponse <= 0 ) {
                    throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons));
                }
                if( this.avoirJustificationYN  ){
                    justificationReponsesModel = ((JustificationReponsesModel) sp_Reponse2.getSelectedItem());
                    ValJustificationReponse = justificationReponsesModel.getCodeJustification();
                    if ( ValJustificationReponse <= 0 ) {
                        throw new TextEmptyException(context.getString(R.string.msg_Ou_Dwe_Chwazi_Yon_JustificationRepons));
                    }
                }
            } else if ( typeQuestion == Constant.TYPE_QUESTION_SAISIE_2 ) {
                this.Check_DataField_EditText(et_Reponse);
                ValReponseSaisie = et_Reponse.getText().toString();

            } else if (typeQuestion == Constant.TYPE_QUESTION_DATE_MM_AAAA_3) {

            } else if ( typeQuestion == Constant.TYPE_QUESTION_DEUX_CHOIX_4 ) {

            } else if (typeQuestion == Constant.TYPE_QUESTION_CHOIX_PAYS_5) {

            } else if (typeQuestion == Constant.TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7) {

            } else if (typeQuestion == Constant.TYPE_QUESTION_CHOIX_DOMAINE_ETUDE_8) {

            } else if (typeQuestion == Constant.TYPE_QUESTION_NBR_APPAREIL_ET_ANIMAUX_12) {

            } else if (typeQuestion == Constant.TYPE_QUESTION_NBR_GARCON_ET_FILLE_13) {

            } else if (typeQuestion == Constant.TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_14) {

            } else if (typeQuestion == Constant.TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_LIER_15) {

            }else if ( typeQuestion == Constant.TYPE_QUESTION_CHOIX_AGE_16 ) {

            }else if ( this.typeQuestion == Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_PAYS_18
                    || this.typeQuestion == Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_AGE_19
                    || this.typeQuestion == Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_DOMAINE_ETUDE_20
                    || this.typeQuestion == Constant.TYPE_QUESTION_CHOIX_NUMBER_LISTE_BOX_22 ) {

            }
            //endregion

            Shared_Preferences spreferences = Tools.SharedPreferences(context);

            long personnelId = spreferences.getPersId();
            long codeFormulaireExercice = this.formExercicesModel.getCodeExercice();
            long codeQuestion = this.codeQuestion;
            long codeReponse = ValReponse;
            long codeJustificationReponse = ValJustificationReponse;
            String reponseSaisie = ValReponseSaisie;

            ReponseEntreeModel temp = new ReponseEntreeModel();
            temp.setPersonnelId(personnelId);
            temp.setCodeFormulaireExercice(codeFormulaireExercice);
            temp.setCodeQuestion(codeQuestion);
            temp.setCodeReponse(codeReponse);
            temp.setCodeJustificationReponse(codeJustificationReponse);
            temp.setReponseSaisie(reponseSaisie);
            temp.setCreatedBy(spreferences.getNomUtilisateur());
            temp.setDateCreated(Tools.getDateString_MMddyyyy_HHmmss());
            tempReponseEntreeModel.add(temp);

            InsertReponseEntree(cuRecordMngr, temp);

        } catch (Exception ex) {
            Tools.LogCat("Exception-CheckValueBefore(): getMessage: ", ex);
            throw ex;
        }
    }

    private void InsertReponseEntree(CURecordMngr cuRecordMngr, ReponseEntreeModel reponseEntreeModel) {
        try{
            cuRecordMngr.InsertReponseEntree(reponseEntreeModel);
        } catch (Exception ex) {
            Tools.LogCat("Exception-InsertReponseEntree(): ", ex);
        }
    }

    public void InsertAgent_Evaluation(CURecordMngr cuRecordMngr, Agent_Evaluation_ExercicesModel reponseEntreeModel) {
        try{
            cuRecordMngr.InsertAgent_Evaluation_Exercices(reponseEntreeModel);
        } catch (Exception ex) {
            Tools.LogCat("Exception-InsertAgent_Evaluation(): ", ex);
        }
    }

    public void Check_DataField_EditText(EditText EditTextRep) throws TextEmptyException {
        try {
            //ToastUtility.LogCat( "TOP:INSIDE - Check_DataField_CHIFFRE_LETTRE");
            String EditText_Reponse = EditTextRep.getText().toString();
            Pattern pattern;// = Pattern.compile("^[A-Za-z0-9]");
            Matcher matcher;// = pattern.matcher(EditText_Reponse);
            if (!EditText_Reponse.trim().equals("")) {
                if (contrainte.getCaractereAccepte() == Constant.CHIFFRE) {
                    pattern = Pattern.compile("^([0-9]+)$");
                    matcher = pattern.matcher(EditText_Reponse);
                    if (!matcher.matches()) {
                        EditTextRep.setError(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Chif_Selman));
                        EditTextRep.requestFocus();
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Chif_Selman));
                    }
                }

                if (contrainte.getCaractereAccepte() == Constant.CHIFFRE_LETTRE) {
                    pattern = Pattern.compile("^([A-Za-z0-9]*)$");
                    matcher = pattern.matcher(EditText_Reponse);
                    if (!matcher.matches()) {
                        EditTextRep.setError(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Let_Oubye_Chif_Selman));
                        EditTextRep.requestFocus();
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Let_Oubye_Chif_Selman));
                    }
                }

                if (contrainte.getCaractereAccepte() == Constant.LETTRE) {
                    pattern = Pattern.compile("^([A-Za-z\\s]*)$");
                    matcher = pattern.matcher(EditText_Reponse);
                    //Match match = Regex.Match(_Txt_Reponse.Text, @"^([A-Za-z\s]*)$", RegexOptions.IgnoreCase);
                    if (!matcher.matches()) {
                        EditTextRep.setError(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Let_Selman));
                        EditTextRep.requestFocus();
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Let_Selman));
                    }
                }
            }
            //ToastUtility.LogCat( "TOP:BOTTOM - Check_DataField_CHIFFRE_LETTRE");
        } catch (Exception ex) {
            Tools.LogCat("Exception-Check_DataField_CHIFFRE_LETTRE(): getMessage: ", ex);
            throw ex;
        }
    }//

    //endregion

    //region [ SAVE ]
    public void SaveInfoDefinitivement() throws Exception {
        try {
            long idUser = 0;
            String nomUtilisateur = "";
            String dateFinCollecte = "";

            Shared_Preferences infoUser = Tools.SharedPreferences(context);
            if (infoUser != null && infoUser.getProfileId() != null) {
                idUser = infoUser.getPersId();
                nomUtilisateur = infoUser.getNomUtilisateur();
            }
            dateFinCollecte = Tools.getDateString_MMddyyyy_HHmmss();


        }catch (Exception ex) {
            Tools.LogCat( "Exception: SaveInfoDefinitivement(): ", ex );
            throw ex;
        }
    }//
    //endregion
}
