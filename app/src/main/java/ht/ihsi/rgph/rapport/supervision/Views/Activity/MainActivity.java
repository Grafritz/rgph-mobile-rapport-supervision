package ht.ihsi.rgph.rapport.supervision.Views.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import ht.ihsi.rgph.rapport.supervision.Constant.Constant;
import ht.ihsi.rgph.rapport.supervision.Models.AgentRapportModel;
import ht.ihsi.rgph.rapport.supervision.Models.FormulaireExercicesModel;
import ht.ihsi.rgph.rapport.supervision.Models.PersonnelModel;
import ht.ihsi.rgph.rapport.supervision.R;
import ht.ihsi.rgph.rapport.supervision.Utilities.QuestionnaireFormulaireUtility;
import ht.ihsi.rgph.rapport.supervision.Utilities.Shared_Preferences;
import ht.ihsi.rgph.rapport.supervision.Utilities.Tools;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    //region [ ATTRIBUTS ]
    public TextView tv_message;
    public TextView tv_Utilisateur;
    public TextView tv_GestionCompteUtilisateur;

    public Button btn_QuitterApplication;
    public Button btn_ConsulterResultat, btn_RedigerRapport;
    public Button btn_GestionCompteUtilisateur;
    public Button btn_Connexion;

    public LinearLayout LL_Fomulaire_MAIN;
    public LinearLayout LL_FomulaireSDE;
    public Dialog dialog;
    private QuestionnaireFormulaireUtility QF;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            if(savedInstanceState==null) {
                startActivity(new Intent(this, SplashScreen.class));
            }

            infoUser = Tools.SharedPreferences(this);
            if (infoUser != null && infoUser.getProfileId() != null) {
                profilId = infoUser.getProfileId();
                nomUtilisateur = infoUser.getNomUtilisateur();
            }
            // Toolbar creation
            //toolbar = (Toolbar) findViewById(R.id.toolbar);
            //toolbar.setTitle(Html.fromHtml("<b>" + getString(R.string.app_name) + "</b>"));
            //setSupportActionBar(toolbar);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            tv_Utilisateur = (TextView)this.findViewById(R.id.tv_Utilisateur);

            tv_message = (TextView)this.findViewById(R.id.tv_message);
            tv_message.setVisibility(View.GONE);

            btn_QuitterApplication = (Button)this.findViewById(R.id.btn_QuitterApplication);
            btn_QuitterApplication.setOnClickListener(this);

            btn_ConsulterResultat = (Button)this.findViewById(R.id.btn_ConsulterResultat);
            btn_ConsulterResultat.setOnClickListener(this);

            btn_RedigerRapport = (Button)this.findViewById(R.id.btn_RedigerRapport);
            btn_RedigerRapport.setOnClickListener(this);

            btn_Connexion = (Button)this.findViewById(R.id.btn_Connexion);
            btn_Connexion.setOnClickListener(this);

            tv_GestionCompteUtilisateur = (TextView)this.findViewById(R.id.tv_GestionCompteUtilisateur);
            btn_GestionCompteUtilisateur = (Button)this.findViewById(R.id.btn_GestionCompteUtilisateur);
            btn_GestionCompteUtilisateur.setOnClickListener(this);

            LL_Fomulaire_MAIN = (LinearLayout)this.findViewById(R.id.LL_Fomulaire_MAIN);
            LL_FomulaireSDE = (LinearLayout)this.findViewById(R.id.LL_FomulaireSDE);

            init(Constant.FORM_DATA_MANAGER);
            init(Constant.QUERY_RECORD_MANAGER);
            init(Constant.CU_RECORD_MANAGER);

            CheckPrefIsUseConnected(false);
        }catch (Exception ex) {
            Tools.LogCat(this, ex);
        }
    }

    //region [  ]
    @Override
    protected void onResume() {
        Tools.LogCat("W", "onResume");
        CheckPrivilegeUser();
        super.onResume();
    }

    public void CheckPrefIsUseConnected(boolean forConnexion) {
        try {
            Shared_Preferences SPref = Tools.SharedPreferences(this);
            Boolean checkPrefIsUseConnected =  Tools.CheckPrefIsUseConnected(this);
            if(!checkPrefIsUseConnected){
                cancel=true;
                btn_Connexion.setVisibility(View.VISIBLE);
                LL_Fomulaire_MAIN.setVisibility(View.GONE);
                btn_QuitterApplication.setText(getString(R.string.label_Quitter));
                if(forConnexion) {
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
            }else {
                cancel=false;
                btn_QuitterApplication.setText(getString(R.string.label_Deconnecter));
                btn_Connexion.setVisibility(View.GONE);
                LL_Fomulaire_MAIN.setVisibility(View.VISIBLE);
                //CheckPrivilegeUser();
                message = "Bienvenue: " + SPref.getPrenom() + " " + SPref.getNom();
                tv_Utilisateur.setText(message);
            }

            if(SPref != null ) {

            }
        }catch (Exception ex) {
            message = "Erreur :";
            Tools.LogCat("Exception: CheckPrefIsUseConnected :", ex);
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ex.printStackTrace();
        }
    }
    //endregion

    //region [ EVENTS ]
    @Override
    public void onClick(View v) {
        try {
            Shared_Preferences SPref = Tools.SharedPreferences(this);
            switch (v.getId()) {
                case R.id.btn_RedigerRapport:
                    CheckPrefIsUseConnected(true);
                    if (!cancel) {
                        ShowFormulaireSaisieNomComplet();
                    }
                    break;
                case R.id.btn_ConsulterResultat:
                    CheckPrefIsUseConnected(true);
                    if (!cancel) {
                        intent = new Intent(this, DisplayListActivity.class);
                        intent.putExtra(Constant.PARAM_ACTION_BAR_TITLE, ""+getString(R.string.msg_RapportDeSupervisionDirecte) );
                        intent.putExtra(Constant.PARAM_GRAND_TITRE_HEADER_ONE,  "" + getString(R.string.msg_RapportDeSupervisionDirecte) );
                        intent.putExtra(Constant.PARAM_SOUS_TITRE_HEADER_TWO, "" + getString(R.string.label_Liste_ResultatRapportDesAgents) );
                        intent.putExtra(Constant.PARAM_TYPE_FORMULAIRE, "" + Constant.LIST_RESULTAT_RAPPORT_AGENT);
                        intent.putExtra(Constant.PARAM_MODULE_STATUT, "" );
                        intent.putExtra(Constant.PARAM_ID, "" );
                        startActivity(intent);
                    }
                    break;
                case R.id.btn_Connexion:
                    CheckPrefIsUseConnected(true);
                    break;
                case R.id.btn_GestionCompteUtilisateur:
                    // On affiche la liste des
                    showListView("Liste Compte Utilisateur", Constant.LIST_COMPTE_UTILISATEUR, 0, 0);
                    break;
                case R.id.btn_QuitterApplication:
                    Disconnected();
                    //finish();
                    break;
                default:
            }
        } catch (Exception ex) {
            Tools.LogCat("Exception-onClick(): getMessage: ", ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    //endregion


    //region PopUp Agent Rapport
    private void ShowFormulaireSaisieNomComplet() {
        try {
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.form_agent);
            dialog.setCancelable(false);
            scrollView2 = (ScrollView) dialog.findViewById(R.id.scrollView2);

            //QF.context = QuestionnaireBatimentActivity.this;

            tv_GrandTitre2 = (TextView) dialog.findViewById(R.id.tv_GrandTitre);

            tv_NomCompletAgent = (TextView) dialog.findViewById(R.id.tv_NomCompletAgent);
            et_NomCompletAgent = (EditText) dialog.findViewById(R.id.et_NomCompletAgent);
            et_NomCompletAgent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if( actionId == Constant.imeActionId_EtReponse_6){
                        SaveNomCompletAgent();
                        return true;
                    }
                    return false;
                }
            });
            tv_CommentairesGeneraux = (TextView) dialog.findViewById(R.id.tv_CommentairesGeneraux);
            tv_CommentairesGeneraux.setVisibility(View.GONE);
            et_CommentairesGeneraux = (EditText) dialog.findViewById(R.id.et_CommentairesGeneraux);
            et_CommentairesGeneraux.setVisibility(View.GONE);
            //endregion

            sharedPreferences = Tools.SharedPreferences(this);
            if ( sharedPreferences != null ) {
                //sdeCode = sharedPreferences.getSdeId();
            }

            dialog.setTitle(Html.fromHtml( getString(R.string.msg_RapportDeSupervisionDirecte)) );
            tv_GrandTitre2.setText( getString(R.string.msg_EvaluationQuantitative) );

            //region Buttons btnQuitter
            Button btnQuitter_Bat = (Button) dialog.findViewById(R.id.btnQuitter_Bat);
            btnQuitter_Bat.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    // On ferme l'activity
                    //finish();
                }
            });
            //endregion

            //region Buttons btnContinuer
            Button btnContinuer = (Button) dialog.findViewById(R.id.btnContinuer);
            btnContinuer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SaveNomCompletAgent();
                }
            });
            //endregion

            dialog.show();

        } catch (Exception ex) {
            message = "Erreur :";
            //ToastUtility.LogCat("Exception: ShowFormulaireBatiment :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message + "\n" + ex.getMessage());
            ex.printStackTrace();
        }
    }
    //endregion

    private void SaveNomCompletAgent() {
        try {
            et_NomCompletAgent.setError(null);
            String etNomCompletAgent="" + et_NomCompletAgent.getText(), codeAgentRecenceur="";
            //QF.context = QuestionnaireBatimentActivity.this;
            if ( sharedPreferences != null ){
                codeAgentRecenceur = sharedPreferences.getNomUtilisateur();
            }
            //QF.CheckValueBefore_Batiment( queryRecordMngr, et_NomCompletAgent );
            if (TextUtils.isEmpty(etNomCompletAgent)) {
                et_NomCompletAgent.setError(getString(R.string.msg_NomComplet_Obligatoire));
            }else{
                //String dateFinEvaluation = ;
                AgentRapportModel aee = new AgentRapportModel();
                aee.setNomCompletAgent(etNomCompletAgent);
                //aee.setCommentairesGeneraux(getPersId);
                //aee.setScoreFinalAtteint("");
                aee.setCreatedBy(codeAgentRecenceur);
                aee.setDateCreated(Tools.getDateString_MMddyyyy_HHmmss());

                aee = cuRecordMngr.InsertAgentRapport(aee);
                if( aee.getCodeAgent() > 0 ){
                    goToForm(aee.getCodeAgent(), aee.getNomCompletAgent());
                }
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
            //QF.SaveInfoDefinitivement(cuRecordMngr, false);

            //Suivant_Click();
        }catch (Exception ex) {
            Tools.LogCat("Exception: ShowFormulaireBatiment() :" + ex.getMessage() + " / toString: " + ex.toString());
            ex.printStackTrace();
        }
    }

    public void goToForm(long codeAgent, String nomCompletAgent) {
        try {
            QuestionnaireFormulaireUtility QF = null;
            dateString = Tools.getDateString_MMddyyyy_HHmmss();
            FormulaireExercicesModel formExercicesModel = formDataMngr.getFormulaireExercices_ByID(1);
            //On regarde pour voir si la personne a deja passe cette evaluation
            QF = new QuestionnaireFormulaireUtility(formExercicesModel, formDataMngr);
            QF.setDateDebutCollecte(dateString);
            QF.codeAgent = codeAgent;
            QF.nomCompletAgent = nomCompletAgent;

            intent = new Intent(this, QuestionnaireExerciceActivity.class);
            intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QF);
            intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, ""+ getString(R.string.msg_RapportDeSupervisionDirecte));;
            intent.putExtra(Constant.PARAM_NOM_AGENT, " [ " + nomCompletAgent +" ] ");
            intent.putExtra(Constant.PARAM_ID, ""+codeAgent);

            startActivity(intent);
        } catch (Exception ex) {
            Tools.LogCat(this, "Exception:goToForm() - getMessage:", ex);
            ex.printStackTrace();
        }
    }

    // endregion
    //region [ METHODES UTILES ]
    private void Disconnected() {
        PersonnelModel personnelModel = new PersonnelModel();
        personnelModel.setIsConnected(false);
        Tools.StoreInfoPresonnel_PreferenceManager(MainActivity.this, personnelModel);
        CheckPrivilegeUser();
    }

    public void showListView(String actionBarTitle, int typeFormulaire, int statut, long idMere) {
        intent = new Intent(this, DisplayListActivity.class);
        intent.putExtra(Constant.PARAM_ACTION_BAR_TITLE, actionBarTitle);
        intent.putExtra(Constant.PARAM_TYPE_FORMULAIRE, "" + typeFormulaire);
        intent.putExtra(Constant.PARAM_GRAND_TITRE_HEADER_ONE, "" + actionBarTitle);
        intent.putExtra(Constant.PARAM_SOUS_TITRE_HEADER_TWO, "");// + txtHeaderTwo.getText());
        intent.putExtra(Constant.PARAM_MODULE_STATUT, "" + statut);
        intent.putExtra(Constant.PARAM_ID, "" + idMere);
        startActivity(intent);
    }
    //endregion

    //region CONFIGURATION
    private void CheckPrivilegeUser() {
        try {
            message = "";
            SetVisibleControls(View.GONE);
            Shared_Preferences SPref = Tools.SharedPreferences(this);
            // On verifie si l'appareil est configure par l'ASTIC ou le superviseur
            tv_GestionCompteUtilisateur.setVisibility(View.GONE);
            btn_GestionCompteUtilisateur.setVisibility(View.GONE);

            if (SPref.getIsConnected()) {
                btn_Connexion.setVisibility(View.GONE);
                LL_Fomulaire_MAIN.setVisibility(View.VISIBLE);

                SetVisibleControls(View.VISIBLE);
                //tv_GestionCompteUtilisateur.setVisibility(View.VISIBLE);
                //btn_GestionCompteUtilisateur.setVisibility(View.VISIBLE);

                if (SPref.getProfileId() != null && Constant.PRIVILEGE_DEVELOPPEUR == SPref.getProfileId()) {
                    tv_GestionCompteUtilisateur.setVisibility(View.VISIBLE);
                    btn_GestionCompteUtilisateur.setVisibility(View.VISIBLE);

                } /*else if (SPref.getProfileId() != null && Constant.PRIVILEGE_SUPERVISEUR == SPref.getProfileId()) {
                    SetVisibleControls(View.VISIBLE);

                } else if (SPref.getProfileId() != null && Constant.PRIVILEGE_AGENT == SPref.getProfileId()) {

                }*/
                tv_Utilisateur.setVisibility(View.VISIBLE);
                message = "Bienvenue: " + SPref.getPrenom() + " " + SPref.getNom();
                tv_Utilisateur.setText(message);
            } else {
                btn_Connexion.setVisibility(View.VISIBLE);
                LL_Fomulaire_MAIN.setVisibility(View.GONE);
                tv_Utilisateur.setVisibility(View.GONE);
            }


        } catch (Exception ex) {
            Tools.LogCat("Exception-CheckPrivilegeUser(): getMessage: ", ex);
            ex.printStackTrace();
        }
    }

    private void SetVisibleControls(int visible) {
        btn_ConsulterResultat.setVisibility(visible);
        btn_RedigerRapport.setVisibility(visible);
        LL_FomulaireSDE.setVisibility(visible);
    }

    //endregion
}
