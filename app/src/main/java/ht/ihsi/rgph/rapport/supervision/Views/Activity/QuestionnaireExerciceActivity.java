package ht.ihsi.rgph.rapport.supervision.Views.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;

import ht.ihsi.rgph.rapport.supervision.Constant.Constant;
import ht.ihsi.rgph.rapport.supervision.Exceptions.TextEmptyException;
import ht.ihsi.rgph.rapport.supervision.Models.Agent_Evaluation_ExercicesModel;
import ht.ihsi.rgph.rapport.supervision.Models.JustificationReponsesModel;
import ht.ihsi.rgph.rapport.supervision.Models.KeyValueModel;
import ht.ihsi.rgph.rapport.supervision.Models.ReponsesModel;
import ht.ihsi.rgph.rapport.supervision.R;
import ht.ihsi.rgph.rapport.supervision.Utilities.QuestionnaireFormulaireUtility;
import ht.ihsi.rgph.rapport.supervision.Utilities.Tools;
import ht.ihsi.rgph.rapport.supervision.Views.Adapters.RadioListAdapterKeyValue;
import ht.ihsi.rgph.rapport.supervision.Views.Adapters.RadioListJustificationReponsesAdapter;
import ht.ihsi.rgph.rapport.supervision.Views.Adapters.RadioListReponsesAdapter;

public class QuestionnaireExerciceActivity extends BaseActivity implements Serializable, View.OnClickListener {

    //region [ ATTRIBUTS ]
    private QuestionnaireFormulaireUtility QF, QFD;

    private TextView tv_GrandTitre_LibelleExercice, tv_DescriptionsExercice, tv_InstructionsExercice, tv_LibelleQuestion;
    private TextView tv_Reponse2, tv_Reponse3, tv_RappelExercice, tv_DetailQuestion;

    private EditText et_Reponse;
    private Spinner sp_Reponse;

    private RelativeLayout RelativeLayout_Reponse;
    private RelativeLayout RelativeLayout_Reponse2;
    private RelativeLayout RelativeLayout_Reponse3;
    private RelativeLayout RL_Jour;
    private LinearLayout LinearLDate;

    private Spinner sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee;


    LinearLayout LL_GaconEtFille, LL_AppareilEtAnimaux;
    TextView tv_Reponse, tv_Gason, tv_Fi;
    EditText et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet;
    EditText et_VwatiMachin, et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok;

    private Button btn_Precedent,  btn_Suivant;
    public long codeAgent=0;
    public String nomAgent="";
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_exercice);

        try{
            infoUser = Tools.SharedPreferences(this);
            if (infoUser != null && infoUser.getProfileId() != null) {
                getPersId = infoUser.getPersId();
                profilId = infoUser.getProfileId();
                nomUtilisateur = infoUser.getNomUtilisateur();
            }

            init(Constant.FORM_DATA_MANAGER);
            init(Constant.QUERY_RECORD_MANAGER);
            init(Constant.CU_RECORD_MANAGER);

            InitControls();

            Intent intent= getIntent();
            QF = new QuestionnaireFormulaireUtility();
            QF = (QuestionnaireFormulaireUtility) intent.getSerializableExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE);
            QF.context = QuestionnaireExerciceActivity.this;
            //QF.formDataMngr = formDataMngr;
            //QF.cuRecordMngr = cuRecordMngr;
            GetFieldValuesQuestionInfo(QF);

        }catch (Exception ex){
            Tools.LogCat(this, "Exception: onCreate(): ", ex);
            Tools.ToastMessage(this, "Erreur:"+ ex.getMessage());
            ex.printStackTrace();
        }

    }


    //region [ InitControls ]
    private void InitControls() {
        try {
            toolbar =  (Toolbar)findViewById(R.id.my_toolbar);
            toolbar.setTitle(Html.fromHtml("<b>" + getString(R.string.label_app_name) + "</b>"));
            setSupportActionBar(toolbar);

            tv_GrandTitre_LibelleExercice = (TextView) this.findViewById(R.id.tv_GrandTitre_LibelleExercice);
            tv_DescriptionsExercice = (TextView) this.findViewById(R.id.tv_DescriptionsExercice);
            tv_InstructionsExercice = (TextView) this.findViewById(R.id.tv_InstructionsExercice);
            tv_LibelleQuestion = (TextView) this.findViewById(R.id.tv_LibelleQuestion);
            tv_DetailQuestion = (TextView) this.findViewById(R.id.tv_DetailQuestion);
            tv_RappelExercice = (TextView) this.findViewById(R.id.tv_RappelExercice);

            tv_Reponse2 = (TextView) this.findViewById(R.id.tv_Reponse2);
            tv_Reponse3 = (TextView) this.findViewById(R.id.tv_Reponse3);
            recyclerViewReponse = (RecyclerView) findViewById(R.id.recyclerViewReponse);
            LL_RecyclerView = (LinearLayout) this.findViewById(R.id.LL_RecyclerView);
            et_Reponse = (EditText) this.findViewById(R.id.et_Reponse);
            //et_Reponse.setOnEditorActionListener(this);

            sp_Reponse = (Spinner) this.findViewById(R.id.sp_Reponse);
            RelativeLayout_Reponse = (RelativeLayout) this.findViewById(R.id.RelativeLayout_Reponse);
            sp_Reponse.setOnItemSelectedListener(getSpinnerReponseSelectedListener);

            sp_Reponse2 = (Spinner) this.findViewById(R.id.sp_Reponse2);
            RelativeLayout_Reponse2 = (RelativeLayout) this.findViewById(R.id.RelativeLayout_Reponse2);
            sp_Reponse2.setOnItemSelectedListener(getSpinnerReponse2SelectedListener);

            sp_Reponse3 = (Spinner) this.findViewById(R.id.sp_Reponse3);
            RelativeLayout_Reponse3 = (RelativeLayout) this.findViewById(R.id.RelativeLayout_Reponse3);

            sp_Jour = (Spinner) this.findViewById(R.id.sp_Jour);
            sp_Mois = (Spinner) this.findViewById(R.id.sp_Mois);
            sp_Annee = (Spinner) this.findViewById(R.id.sp_Annee);
            RL_Jour = (RelativeLayout) this.findViewById(R.id.RL_Jour);
            LinearLDate = (LinearLayout) this.findViewById(R.id.LinearLDate);

            tv_Reponse = (TextView) this.findViewById(R.id.tv_Reponse);
            LL_GaconEtFille = (LinearLayout) this.findViewById(R.id.LL_GaconEtFille);
            tv_Gason = (TextView) this.findViewById(R.id.tv_Gason);
            et_Gason = (EditText) this.findViewById(R.id.et_Gason);
            tv_Fi = (TextView) this.findViewById(R.id.tv_Fi);
            et_Fi = (EditText) this.findViewById(R.id.et_Fi);
            LL_AppareilEtAnimaux = (LinearLayout) this.findViewById(R.id.LL_AppareilEtAnimaux);
            et_ApareyRadyo = (EditText) this.findViewById(R.id.et_ApareyRadyo);
            et_Televizyon = (EditText) this.findViewById(R.id.et_Televizyon);
            et_FrijideFrize = (EditText) this.findViewById(R.id.et_FrijideFrize);
            et_FouElektrikFouAkGaz = (EditText) this.findViewById(R.id.et_FouElektrikFouAkGaz);
            et_OdinatePCLaptopTabletNimerik = (EditText) this.findViewById(R.id.et_OdinatePCLaptopTabletNimerik);
            et_BisikletMotosiklet = (EditText) this.findViewById(R.id.et_BisikletMotosiklet);
            et_VwatiMachin = (EditText) this.findViewById(R.id.et_VwatiMachin);
            et_KannotBato = (EditText) this.findViewById(R.id.et_KannotBato);
            et_InvetePanoSoleJeneratrisDelko = (EditText) this.findViewById(R.id.et_InvetePanoSoleJeneratrisDelko);
            et_MiletChwalBourik = (EditText) this.findViewById(R.id.et_MiletChwalBourik);
            et_BefVach = (EditText) this.findViewById(R.id.et_BefVach);
            et_KochonKabrit = (EditText) this.findViewById(R.id.et_KochonKabrit);
            et_BetVolayPoulKok = (EditText) this.findViewById(R.id.et_BetVolayPoulKok);

            btn_Precedent = (Button) this.findViewById(R.id.btn_Precedent);
            btn_Precedent.setOnClickListener(this);

            btn_Suivant = (Button) this.findViewById(R.id.btn_Suivant);
            btn_Suivant.setOnClickListener(this);

        }catch (Exception ex){
            Tools.LogCat(this, "Exception: InitControls(): ", ex);
        }
    }
    //endregion

    //region SET DATA : GetFieldValuesQuestionInfo
    private void GetFieldValuesQuestionInfo(QuestionnaireFormulaireUtility qf) {
        try {
            SetFieldValuesInfoExercices(qf);
            QuestionnaireFormulaireUtility.SetFieldValuesQuestionInfo( this, formDataMngr,  qf
                    , tv_GrandTitre_LibelleExercice, tv_DescriptionsExercice , tv_InstructionsExercice
                    , tv_LibelleQuestion, tv_DetailQuestion, tv_Reponse2, tv_Reponse3
                    , recyclerViewReponse, LL_RecyclerView
                    , radioListReponsesAdapter,  getItemReponsesClickListener()
                    , radioListJustificationReponsesAdapter,  getItemJustificationReponsesClickListener()
                    , radioListAdapterKeyValue,  getItemClickListenerKeyValue()
                    , et_Reponse , sp_Reponse , RelativeLayout_Reponse , sp_Reponse2 , RelativeLayout_Reponse2 , sp_Reponse3 , RelativeLayout_Reponse3
                    , LinearLDate , RL_Jour , sp_Jour , sp_Mois , sp_Annee
                    , LL_GaconEtFille, LL_AppareilEtAnimaux,tv_Reponse, tv_Gason, tv_Fi,et_Gason, et_Fi
                    , et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                    , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok, btn_Precedent, btn_Suivant);

            //QF.Load_PossibiliteReponse(this, formDataMngr, recyclerViewReponse, radioListAdapter,  getItemClickListener());
            //* Dialog pupUp Formulaire liste individu dans le menage *//*
            //SetReponseValue_DataBase(qf);

            //* Add Logement Col et Ind. dans Batiment *//*
            //ShowPopUp_AddLogement();
        } catch (Exception ex) {
            Tools.LogCat(this, "Exception: GetFieldValuesQuestionInfo(): ", ex);
            ex.printStackTrace();
        }
    }//
    //endregion

    //region [ EVENT CONTROLS ]
    @Override
    public void onClick(View view) {
        try {
            et_Reponse.setEnabled(true);
            sp_Reponse.setEnabled(true);
            sp_Reponse2.setEnabled(true);
            sp_Reponse3.setEnabled(true);
            sp_Jour.setEnabled(true);
            sp_Mois.setEnabled(true);
            sp_Annee.setEnabled(true);

            switch (view.getId()){
                case R.id.btn_Suivant:
                    Suivant_Click();
                    break;
                case R.id.btn_Precedent:
                    //Precedent_Click(QF);
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            Tools.LogCat(this, "Exception: InitControls(): ", ex);
        }

    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            final int _keyCode=keyCode;
            final KeyEvent _event=event;
            //e = event;
            //k = keyCode;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("" + getString(R.string.msg_Confirmation_Abandonner))
                    .setCancelable(false)
                    .setPositiveButton(""+ getString(R.string.label_Non), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    })
                    .setNegativeButton("" + getString(R.string.label_WI), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            QuestionnaireExerciceActivity.super.onKeyDown(_keyCode, _event);

                            // Enregistrement des informations pour empecher que l'utilisateur passe a nouveau l'evaluation
                            SaveAgent_Evaluation();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            return true;
        }else  if (keyCode == KeyEvent.KEYCODE_HOME){
            return true;
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }//
    //endregion

    //region [ EVENT SPINNER ]
    public AdapterView.OnItemSelectedListener getSpinnerReponseSelectedListener = new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
    public AdapterView.OnItemSelectedListener getSpinnerReponse2SelectedListener = new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    //endregion

    //region [ EVENTS RADIO BUTTON ]
    public RadioListReponsesAdapter.OnItemReponsesClickListener getItemReponsesClickListener(){
        return new RadioListReponsesAdapter.OnItemReponsesClickListener(){
            @Override
            public void onItemClick(ReponsesModel entity) {

            }
        };
    }
    public RadioListJustificationReponsesAdapter.OnItemClickListener getItemJustificationReponsesClickListener(){
        return new RadioListJustificationReponsesAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(JustificationReponsesModel entity) {

            }
        };
    }

    public RadioListAdapterKeyValue.OnItemClickListenerKeyValue getItemClickListenerKeyValue(){
        return new RadioListAdapterKeyValue.OnItemClickListenerKeyValue(){
            @Override
            public void onItemClick(KeyValueModel keyValueModel) {

            }
        };
    }
    //endregion

    //region [ Set Field Values InfoExercices ]
    public  void SetFieldValuesInfoExercices(QuestionnaireFormulaireUtility qf){
        try{

            tv_GrandTitre_LibelleExercice.setText(qf.formExercicesModel.getLibelleExercice());

            tv_DescriptionsExercice.setVisibility(View.GONE);
            tv_InstructionsExercice.setVisibility(View.GONE);
            tv_RappelExercice.setVisibility(View.GONE);

            tv_DescriptionsExercice.setText( Html.fromHtml( qf.formExercicesModel.getDescriptions() + " " + qf.nomCompletAgent));
            if(!qf.formExercicesModel.getDescriptions().toString().trim().equalsIgnoreCase("")){
                tv_DescriptionsExercice.setVisibility(View.VISIBLE);
            }
            tv_InstructionsExercice.setText( Html.fromHtml("<b>" + getString(R.string.label_InstructionsExercice) +"</b><br />"+ qf.formExercicesModel.getInstructions()));
            if(!qf.formExercicesModel.getInstructions().toString().trim().equalsIgnoreCase("")){
                tv_InstructionsExercice.setVisibility(View.VISIBLE);
            }
            tv_RappelExercice.setText( Html.fromHtml("<b>" + getString(R.string.label_RappelExercice) +"</b><br />"+ qf.formExercicesModel.getRappelExercice()));
            if(!qf.formExercicesModel.getRappelExercice().toString().trim().equalsIgnoreCase("")){
                tv_RappelExercice.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex) {
            Tools.LogCat(this, "Exception: SetFieldValuesInfoExercices(): ", ex);
            ex.printStackTrace();
        }
    }
    //endregion

    //region " [ Goto_Question Suivante ]"
    public void Suivant_Click() {
        try {
            isRetour = false;
            QF.context = QuestionnaireExerciceActivity.this;
            //QF.formDataMngr = formDataMngr;
            //QF.cuRecordMngr = cuRecordMngr;
            QF.CheckValueBefore(cuRecordMngr, et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                    ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                    , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                    , recyclerViewReponse, codeReponseRecyclerView, codeReponseKeyValueModel );

            if ( QF.getQuestionsModelList().size() > QuestionnaireFormulaireUtility.countRowIndex+1) {
                Goto_QuestionSuivante(QF);
            }else{
                // Enregistrement des informations pour empecher que l'utilisateur passe a nouveau l'evaluation
                SaveAgent_Evaluation();
            }
        }catch (TextEmptyException ex) {
            Tools.AlertDialogMsg(this, ex.getMessage());
            Tools.LogCat("TextEmptyException: Suivant_Click() :" + ex);
        } catch (Exception ex) {
            Tools.LogCat("Exception: Suivant_Click() :" + ex);
            Tools.AlertDialogMsg(this, ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void SaveAgent_Evaluation() {
        try{
            String dateFinEvaluation = Tools.getDateString_MMddyyyy_HHmmss();

            Agent_Evaluation_ExercicesModel aee = new Agent_Evaluation_ExercicesModel();
            aee.setCodeExercice(QF.formExercicesModel.getCodeExercice());
            aee.setPersonnelId(getPersId);
            aee.setDureeEvaluationEnSeconde(QF.formExercicesModel.getDureeEnSeconde());
            aee.setDateDebutEvaluationDuRepondant(QF.getDateDebutCollecte());
            aee.setDateFinEvaluationDuRepondant(dateFinEvaluation);
            aee.setDureeDuRepondantEnSeconde(""+QF.getDureeSaisie(dateFinEvaluation));

            QF.InsertAgent_Evaluation(cuRecordMngr, aee);

            ShowAlertDialogMsg("Evaluation terminée avec succès...");
        } catch (Exception ex) {
            Tools.LogCat("Exception: SaveAgent_Evaluation() :" + ex);
            Tools.AlertDialogMsg(this, ex.getMessage());
            ex.printStackTrace();
        }
    }
    private void SaveNomCompletAgent() {
        try{
            String dateFinEvaluation = Tools.getDateString_MMddyyyy_HHmmss();

            Agent_Evaluation_ExercicesModel aee = new Agent_Evaluation_ExercicesModel();
            aee.setCodeExercice(QF.formExercicesModel.getCodeExercice());
            aee.setPersonnelId(getPersId);
            aee.setDureeEvaluationEnSeconde(QF.formExercicesModel.getDureeEnSeconde());
            aee.setDateDebutEvaluationDuRepondant(QF.getDateDebutCollecte());
            aee.setDateFinEvaluationDuRepondant(dateFinEvaluation);
            aee.setDureeDuRepondantEnSeconde(""+QF.getDureeSaisie(dateFinEvaluation));

            QF.InsertAgent_Evaluation(cuRecordMngr, aee);

            ShowAlertDialogMsg("Evaluation terminée avec succès...");
        } catch (Exception ex) {
            Tools.LogCat("Exception: SaveAgent_Evaluation() :" + ex);
            Tools.AlertDialogMsg(this, ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void ShowAlertDialogMsg(String msg){
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
        String titleMsg = this.getString(R.string.msg_title_succesInformation);
        aBuilder.setTitle(titleMsg);
        aBuilder.setMessage(msg);
        aBuilder.setCancelable(false);
        aBuilder.setIcon(R.mipmap.ic_launcher);

        //set Positive Button 1
        aBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }//
        );
        aBuilder.show();
    }//

    public void Goto_QuestionSuivante(QuestionnaireFormulaireUtility QF){
        Goto_Question(QF);
    }

    public void Goto_Question( QuestionnaireFormulaireUtility QF ) {
        try {
            QF.Goto_QuestionSuivante();
            QF.context = QuestionnaireExerciceActivity.this;
            GetFieldValuesQuestionInfo(QF);
        } catch (Exception ex){
            Tools.LogCat(this,"Exception-Goto_Question() : ", ex);
            throw ex;
        }
    }
    //endregion
}
