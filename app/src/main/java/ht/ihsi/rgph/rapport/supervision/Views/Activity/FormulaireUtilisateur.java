package ht.ihsi.rgph.rapport.supervision.Views.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import ht.ihsi.rgph.rapport.supervision.Constant.Constant;
import ht.ihsi.rgph.rapport.supervision.Exceptions.TextEmptyException;
import ht.ihsi.rgph.rapport.supervision.Models.KeyValueModel;
import ht.ihsi.rgph.rapport.supervision.Models.PersonnelModel;
import ht.ihsi.rgph.rapport.supervision.Models.RowDataListModel;
import ht.ihsi.rgph.rapport.supervision.R;
import ht.ihsi.rgph.rapport.supervision.Utilities.Shared_Preferences;
import ht.ihsi.rgph.rapport.supervision.Utilities.Tools;
import ht.ihsi.rgph.rapport.supervision.Utilities.Tools;

public class FormulaireUtilisateur extends BaseActivity implements View.OnClickListener {

    //region ATTRIBUTS
    Intent intent;
    public EditText et_Prenom;
    public EditText et_Nom;

    public Spinner sp_GroupeUtilisateur;
    public CheckBox cb_estActif;
    public EditText et_UserName;
    public EditText et_Password;
    public EditText et_ConfirmationPassword;

    public Button btnEnregistrer;
    public Button btnAfficherMotDePasse;

    long ID_UTILISATEUR = 0;

    private Toolbar toolbar;

    Shared_Preferences SPref =null;

    String idDeptLast = "";
    private RowDataListModel rowDada;
    PersonnelModel personnelModel = null;
    Boolean IsShowPassword = false;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_utilisateur);

        init(Constant.FORM_DATA_MANAGER);
        init(Constant.CU_RECORD_MANAGER);
        init(Constant.QUERY_RECORD_MANAGER);

        initControls();
    }

    //region initControls
    private void initControls() {
        try {
            SPref = Tools.SharedPreferences(this);

            Intent intent = getIntent();
            ID_UTILISATEUR = intent.getIntExtra(Constant.PARAM_ID, 0);

            String formulaire = getString(R.string.msg_Configuration_SDE) ;//+ " -|-"+ Tools.GetStringTypeInventaire(TYPE_FORMULAIRE, getString(R.string.app_name));

            // Toolbar creation
            //toolbar = (Toolbar) findViewById(R.id.toolbar);
            //toolbar.setTitle(formulaire);

            et_Prenom = (EditText) this.findViewById(R.id.et_Prenom);
            et_Nom = (EditText) this.findViewById(R.id.et_Nom);

            sp_GroupeUtilisateur = (Spinner) this.findViewById(R.id.sp_GroupeUtilisateur);
            cb_estActif = (CheckBox) this.findViewById(R.id.cb_estActif);

            et_UserName = (EditText) this.findViewById(R.id.et_UserName);
            et_Password = (EditText) this.findViewById(R.id.et_Password);
            et_ConfirmationPassword = (EditText) this.findViewById(R.id.et_ConfirmationPassword);

            // Buttons
            btnEnregistrer = (Button) this.findViewById(R.id.btnEnregistrer);
            btnEnregistrer.setOnClickListener(this);

            ((Button) this.findViewById(R.id.btnAfficherMotDePasse)).setOnClickListener(this);
            ((Button) this.findViewById(R.id.btnQuitter)).setOnClickListener(this);

            // LOAD DATA
            Load_GroupeUtilisateur(sp_GroupeUtilisateur);

            SetDataFromListe();
        } catch (Exception ex) {
            Tools.LogCat("Exception-initControls(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
            ex.printStackTrace();
        }
    }    //
    //endregion

    //region EVENTS
    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btnEnregistrer:
                    SaveCompteUtilisateur();
                    break;
                case R.id.btnAfficherMotDePasse:
                    if( IsShowPassword ) {
                        et_Password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        et_ConfirmationPassword.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);
                        IsShowPassword = false;
                    }else{
                        et_Password.setInputType(InputType.TYPE_CLASS_TEXT);
                        et_ConfirmationPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                        IsShowPassword = true;
                    }
                    break;
                case R.id.btnQuitter:
                    finish();
                    break;
                default:
            }
        } catch (Exception ex) {
            Tools.LogCat("Exception-onClick(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
            ex.printStackTrace();
        }
    }
    //endregion

    //region LOAD DATA
    public void Load_GroupeUtilisateur(Spinner spinner){
        try {
            ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            //keyValueModels.add(new KeyValueModel("", "- " + this.getResources().getString(R.string.label_Selectionnez_TypeFormulaire) + " -"));
            keyValueModels.add(new KeyValueModel("" + Constant.COMPTE_AGENT, " Agent"));

            if( SPref!= null && SPref.getProfileId() == Constant.COMPTE_ASTIC ) {
                keyValueModels.add(new KeyValueModel("" + Constant.COMPTE_SUPERVISEUR, " Superviseur / Controleur"));
                keyValueModels.add(new KeyValueModel("" + Constant.COMPTE_ASTIC, " ASTIC / DEVELOPPEUR"));
            }
            if( SPref!= null && SPref.getProfileId() == Constant.COMPTE_SUPERVISEUR ) {
                keyValueModels.add(new KeyValueModel("" + Constant.COMPTE_SUPERVISEUR, " Superviseur / Controleur"));
            }
            // Creating adapter for spinner
            ArrayAdapter<KeyValueModel> dataAdapter = new ArrayAdapter<KeyValueModel>(this, android.R.layout.simple_list_item_1, keyValueModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        }catch (Exception ex)        {
            Tools.LogCat("Exception-Load_GroupeUtilisateur(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
            ex.printStackTrace();
        }
    }//
    //endregion

    //region SAVE DATA
    private void SaveCompteUtilisateur() {
        try {
            boolean isValide = true;
            KeyValueModel keyValueModel = null;

            keyValueModel = ((KeyValueModel) sp_GroupeUtilisateur.getSelectedItem());
            String id_GroupeUtilisateur = keyValueModel.getKey();

            et_Nom.setError(null);
            String nom = et_Nom.getText().toString();

            et_Prenom.setError(null);
            String prenom = et_Prenom.getText().toString();

            et_UserName.setError(null);
            String nomUtilisateur = et_UserName.getText().toString();

            et_Password.setError(null);
            String _Password = et_Password.getText().toString();

            et_ConfirmationPassword.setError(null);
            String _ConfirmationPassword = et_ConfirmationPassword.getText().toString();

            boolean estActif = cb_estActif.isChecked();

            if (TextUtils.isEmpty(prenom)) {
                isValide = false;
                message =  "Prénom Obligatoire!";
                Tools.SetErrorField(this, et_Prenom, message);
            } else if (TextUtils.isEmpty(nom)) {
                isValide = false;
                message =  "Nom Obligatoire!";
                Tools.SetErrorField(this, et_Nom, message);
            } else if (TextUtils.isEmpty(nomUtilisateur)) {
                isValide = false;
                message =  "Compte Utilisateur Obligatoire!";
                Tools.SetErrorField(this, et_UserName, message);
            } else if (TextUtils.isEmpty(_Password)) {
                isValide = false;
                message =  "Mot de passe Obligatoire!";
                Tools.SetErrorField(this, et_Password, message);
            } else if (TextUtils.isEmpty(_ConfirmationPassword)) {
                isValide = false;
                message =  "Confirmation Mot de passe Obligatoire!";
                Tools.SetErrorField(this, et_ConfirmationPassword, message);
            } else if ( !_Password.equals(_ConfirmationPassword) ) {
                isValide = false;
                message =  "Confirmation Mot de passe Incorrect!";
                Tools.SetErrorField(this, et_ConfirmationPassword, message);
            }
            if (isValide) {
                PersonnelModel pM = new PersonnelModel();
                pM.setPrenom(prenom);
                pM.setNom(nom);
                pM.setNomUtilisateur(nomUtilisateur);
                pM.setMotDePasse(_Password);
                pM.setProfileId(Integer.parseInt(id_GroupeUtilisateur));
                pM.setEstActif(estActif);

                PersonnelModel personnelModel = null;
                personnelModel = cuRecordMngr.SavePersonnel(ID_UTILISATEUR, pM);

                message =  "Compte Utilisateur Enregistrer avec succès.";
                Tools.ToastMessage(this, message);
                Tools.AlertDialogMsg(this, message,"E");
                finish();
            }
        } catch (TextEmptyException ex) {
            Tools.LogCat("TextEmptyException-SaveCompteUtilisateur(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
            Tools.ToastMessage(this, ex.getMessage(), Constant.GravityCenter);
            //ex.printStackTrace();
        } catch (Exception ex) {
            Tools.LogCat("Exception-SaveCompteUtilisateur(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
            ex.printStackTrace();
        }
    }//
    //endregion
    //region SET VALUES
    private void SetDataFromListe() {
        try {
            Intent intent = getIntent();
            rowDada = (RowDataListModel) intent.getSerializableExtra(Constant.PARAM_MODEL);
            if (rowDada != null) {
                personnelModel = (PersonnelModel) rowDada.getModel();
            }
            if (personnelModel != null) {
                ID_UTILISATEUR = personnelModel.getPersId();
                SetValues(sp_GroupeUtilisateur, ""+personnelModel.getProfileId());

                et_Nom.setText(personnelModel.getNom());
                et_Prenom.setText(personnelModel.getPrenom());
                cb_estActif.setChecked(personnelModel.getEstActif());

                et_UserName.setText(personnelModel.getNomUtilisateur());
                et_Password.setText(personnelModel.getMotDePasse());
                btnEnregistrer.setText("Modifier");

                if( SPref!= null && SPref.getPersId() == ID_UTILISATEUR ) {
                    cb_estActif.setEnabled(false);
                }
            }
        } catch (Exception ex) {
            Tools.LogCat("Exception-SetDataFromListe(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
            ex.printStackTrace();
        }
    }

    private void SetValues(Spinner spinner, String codeReponse) {
        try{
            ArrayAdapter<KeyValueModel> reponsModel = (ArrayAdapter<KeyValueModel>) spinner.getAdapter();
            for (int i=0; i<reponsModel.getCount(); i++){
                if( reponsModel.getItem(i).getKey().equalsIgnoreCase(codeReponse)){
                    spinner.setSelection(i);
                    break;
                }
            }
        }catch (Exception ex) {
            Tools.LogCat("Exception-SetValues(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
            ex.printStackTrace();
        }
    }
    //endregion
}
