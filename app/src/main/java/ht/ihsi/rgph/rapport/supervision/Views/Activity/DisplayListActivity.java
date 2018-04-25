package ht.ihsi.rgph.rapport.supervision.Views.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import ht.ihsi.rgph.rapport.supervision.Constant.Constant;
import ht.ihsi.rgph.rapport.supervision.Exceptions.ManagerException;
import ht.ihsi.rgph.rapport.supervision.Exceptions.TextEmptyException;
import ht.ihsi.rgph.rapport.supervision.Models.Agent_Evaluation_ExercicesModel;
import ht.ihsi.rgph.rapport.supervision.Models.FormulaireExercicesModel;
import ht.ihsi.rgph.rapport.supervision.Models.KeyValueModel;
import ht.ihsi.rgph.rapport.supervision.Models.Question_FormulaireExercicesModel;
import ht.ihsi.rgph.rapport.supervision.Models.RowDataListModel;
import ht.ihsi.rgph.rapport.supervision.R;
import ht.ihsi.rgph.rapport.supervision.Utilities.QuestionnaireFormulaireUtility;
import ht.ihsi.rgph.rapport.supervision.Utilities.Shared_Preferences;
import ht.ihsi.rgph.rapport.supervision.Utilities.Tools;
import ht.ihsi.rgph.rapport.supervision.Views.Adapters.DisplayListAdapter;
import ht.ihsi.rgph.rapport.supervision.Views.decorator.SimpleDividerItemDecoration;

/**
 * Created by JFDuverseau on 31/8/2017.
 */
public class DisplayListActivity extends BaseActivity {

    private static final String TAG = "DisplayListActivity";
    private List<RowDataListModel> targetList=new ArrayList<RowDataListModel>();
    private RecyclerView recyclerView;
    private DisplayListAdapter mAdapter;
    private ProgressBar progressBar;
    private MaterialSearchView searchView;
    private Toolbar toolbar;
    private String title;
    private int listType=0,listTypeUse=0;
    //private short moduleStatut=0;
    //private String moduleStatutString="";
    private long id=0;
    int profilId = 0;
    long getPersId = 0;
    private String nomUtilisateur="";
    private TextView list_header_1;
    private TextView list_header_2;
    String StringHeaderTwo;
    private RowDataListModel rowDataListModel = null;
    public Dialog dialog;
    public KeyValueModel keyValueModel=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);
        try {
            infoUser = Tools.SharedPreferences(this);
            if (infoUser != null && infoUser.getProfileId() != null) {
                getPersId = infoUser.getPersId();
                profilId = infoUser.getProfileId();
                nomUtilisateur = infoUser.getNomUtilisateur();
            }
            Intent intent = getIntent();
            title = intent.getStringExtra(Constant.PARAM_ACTION_BAR_TITLE);
            StringHeaderTwo = intent.getStringExtra(Constant.PARAM_SOUS_TITRE_HEADER_TWO).toString();
            listType = listTypeUse = Integer.valueOf(intent.getStringExtra(Constant.PARAM_TYPE_FORMULAIRE)).intValue();

            if( listType == Constant.LIST_TYPE_EXERCICE ){
                //keyValueModel = (KeyValueModel) rowDataListModel.getModel();

            }else  if( listType == Constant.LIST_MODULE_EXERCICES ){
                //rowDataListModel = (RowDataListModel) intent.getSerializableExtra(Constant.PARAM_MODEL);
                id = Long.valueOf(intent.getStringExtra(Constant.PARAM_ID)).longValue();

            }
            //moduleStatut = Short.valueOf(intent.getStringExtra(Constant.PARAM_MODULE_STATUT));
            //moduleStatutString = Tools.getStringStatut(moduleStatut);
            /*if (listType != Constant.LIST_MODULE_BATIMENT
                    || listType != Constant.LIST_MODULE_COMPTE_UTILISATEUR_16) {
                id = Long.valueOf(intent.getStringExtra(Constant.PARAM_MODULE_ID)).longValue();
            }
            if( listType == Constant.LIST_MODULE_LOGEMENT_INDIVIDUEL || listType == Constant.LIST_MODULE_LOGEMENT_COLLECTIF ){
                batimentModel = (BatimentModel) rowDataListModel.getModel();
            }else if( listType == Constant.LIST_MODULE_MENAGE  || listType == Constant.LIST_MODULE_INDIVIDU_LOGEMENT_COLLECTIF ){
                logementModel = (LogementModel)  rowDataListModel.getModel();
            }else if( listType == Constant.LIST_MODULE_EMIGRE || listType == Constant.LIST_MODULE_DECES || listType == Constant.LIST_MODULE_INDIVIDU_MENAGE ){
                menageModel = (MenageModel)  rowDataListModel.getModel();
            }*/


            // Toolbar creation
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(Html.fromHtml("<b>" + getString(R.string.app_name) + "</b>"));
            list_header_1 = (TextView) findViewById(R.id.list_header_1);
            list_header_1.setText(title);
            list_header_2 = (TextView) findViewById(R.id.list_header_2);
            list_header_2.setText(StringHeaderTwo);

            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            createSearchView(); //create the search view

            init(Constant.FORM_DATA_MANAGER);
            init(Constant.QUERY_RECORD_MANAGER);
            init(Constant.CU_RECORD_MANAGER);

            //initialize the recycle view
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            // First param is number of columns and second param is orientation i.e Vertical or Horizontal
            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            // Attach the layout manager to the recycler view
            gridLayoutManager.setAutoMeasureEnabled(true);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
            recyclerView.setHasFixedSize(true);

            //  initialize the adapter
            mAdapter = new DisplayListAdapter(DisplayListActivity.this, targetList, listType, getPersId);//, profilId, moduleStatut );
            mAdapter.setOnItemClickListener(getItemClickListener());
            //inject the adapter into the recycle view
            recyclerView.setAdapter(mAdapter);
            //initialize the progress bar
            //initialize the progress bar
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            //load asynchronously the list of the data
            //AsynDisplayDataListTask task= new AsynDisplayDataListTask();
            //task.execute();
        } catch (Exception ex) {
            Tools.LogCat(this, "Exception:onCreate() - getMessage:", ex);
            Tools.ToastMessage(this, "Erreur:"+ ex.getMessage());
            ex.printStackTrace();
        }

    }

    //region "EVENTS"
    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    public void createSearchView(){
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setCursorDrawable(R.drawable.custom_cursor);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                onTextSearchEvent(newText);
                return true;
            }


        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        //Tools.LogCat("W", "onResume");
        new AsynDisplayDataListTask().execute();
        //ReloadDataListTask();

        super.onResume();
    }

    private void ReloadDataListTask() {
        try {
            if (targetList != null && targetList.size() <= 0) {
                //
                if (listType == Constant.LIST_MODULE_EXERCICES) {
                    //
                    /*if (batimentModel != null && batimentModel.getQb8NbreLogeIndividuel() != null && batimentModel.getQb8NbreLogeIndividuel() > 0) {
                        long NbreTotalLogeIndividuel_KiPaFini = queryRecordMngr.countLogement_ByBatiment_byTypeLog_ByStatus(batimentModel.getBatimentId(), Constant.LOJ_ENDIVIDYEL, moduleStatut);
                        if (NbreTotalLogeIndividuel_KiPaFini > 0) {
                            listType = Constant.LIST_MODULE_LOGEMENT_INDIVIDUEL;
                            list_header_1.setText(getString(R.string.label_lojmanEndividyel) + " "+ moduleStatutString);
                            new AsynDisplayDataListTask().execute();
                        }
                    }*/
                }else if (listType == Constant.LIST_TYPE_EXERCICE) {

                }
            }
        } catch (Exception ex) {
            Tools.LogCat(this, "Exception: ReloadDataListTask - getMessage:", ex);
            ex.printStackTrace();
        }
    }

    public boolean onTextSearchEvent(String newText) {
        if(targetList!=null && targetList.size()>0) {
            final List<RowDataListModel> filteredList = filter(targetList, newText);
            mAdapter.setFilter(filteredList);
        }
        return true;
    }

    private List<RowDataListModel> filter(List<RowDataListModel> rows, String query){
        query=query.toLowerCase();
        final List<RowDataListModel> filteredList=new ArrayList<>();
        if(rows!=null) {
            for (RowDataListModel row : rows) {
                final String text = row.getTitle().toLowerCase();
                if (text.contains(query)) {
                    filteredList.add(row);
                }
            }
        }
        return filteredList;
    }

    DisplayListAdapter.OnItemClickListener getItemClickListener(){
        return new DisplayListAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(RowDataListModel row) {
                //boolean privilege = Privilege.getAccessControl(profilId, Constant.ACTION_AFFICHER, moduleStatut);
                //if( privilege ){
                    goToForm(row, Constant.ACTION_AFFICHER);
                //}else{
                //    Tools.AlertDialogMsg(DisplayListActivity.this, "Opsyon [ Modifye ] sa pa fèt pou ou, \nWè avèk sipèvisè ou...");
                //}
            }
        };
    }

    //endregion

    //region "OPTIONS METHODES"
    public void goToForm(RowDataListModel row, int typeEvenement){
        try {
            List<Question_FormulaireExercicesModel> quesFormExercicesModelList = null;
            QuestionnaireFormulaireUtility QF = null;
            short numOrdre =1;
            /*String*/ dateString = Tools.getDateString_MMddyyyy_HHmmss();
            if(listType == Constant.LIST_MODULE_EXERCICES){
                if( row != null ) {
                    FormulaireExercicesModel formExercicesModel = (FormulaireExercicesModel) row.getModel();
                    //On regarde pour voir si la personne a deja passe cette evaluation

                    //if( formExercicesModel.getStatut() == Constant.OUI_1){
                        getAgentCanGoToEvaluation(formExercicesModel);

                        QF = new QuestionnaireFormulaireUtility(formExercicesModel, formDataMngr);

                        QF.setDateDebutCollecte(dateString);

                        intent = new Intent(this, QuestionnaireExerciceActivity.class);
                        intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QF);
                        //intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, " " + formExercicesModel.ge());
                        //intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, "SDE: " + batimentModel.getSdeId());
                        startActivity(intent);
                    /*}else{
                        message ="Impossible d'acceder a cette exercice car elle est vérouillée par un participant. ";
                        Tools.AlertDialogMsg(this, message);
                    }*/
                }
            }else if (listType == Constant.LIST_TYPE_EXERCICE) {
                if( row != null ) {
                    KeyValueModel keyValueModel = (KeyValueModel) row.getModel();
                    intent = new Intent(this, DisplayListActivity.class);
                    intent.putExtra(Constant.PARAM_ACTION_BAR_TITLE, "" + getString(R.string.label_Exercices) + " " + Tools.getString_TypeExercice(keyValueModel.getKey()));
                    intent.putExtra(Constant.PARAM_GRAND_TITRE_HEADER_ONE, "");
                    intent.putExtra(Constant.PARAM_SOUS_TITRE_HEADER_TWO, "");
                    intent.putExtra(Constant.PARAM_TYPE_FORMULAIRE, "" + Constant.LIST_MODULE_EXERCICES);
                    //intent.putExtra(Constant.PARAM_MODULE_STATUT, keyValueModel);
                    intent.putExtra(Constant.PARAM_ID, ""+keyValueModel.getKey());
                    startActivity(intent);
                }
            }
        }catch (TextEmptyException ex) {
            Tools.AlertDialogMsg(this, ex.getMessage());
            Tools.LogCat("TextEmptyException: Suivant_Click() :" + ex);
        } catch (Exception ex) {
            Tools.LogCat(this, "Exception:goToForm() - getMessage:" , ex);
            ex.printStackTrace();
        }
    }

    public void getAgentCanGoToEvaluation(FormulaireExercicesModel formExercicesModel) throws TextEmptyException, ManagerException {
        try{
            Agent_Evaluation_ExercicesModel aee = queryRecordMngr.getAgent_Evaluation_Exercices_ByIdAgent(formExercicesModel.getCodeExercice(), getPersId);
            if( aee!=null && aee.getCodeExercice()!=null ){
                message="Désolé, " + nomUtilisateur + " Vous avez déjà subi cette évaluation.";
                throw new TextEmptyException(message);
            }
        } catch (TextEmptyException ex) {
            Tools.LogCat("TextEmptyException-getAgentCanGoToEvaluation():  ", ex);
            throw ex;
        } catch (ManagerException ex) {
            Tools.LogCat("ManagerException-getAgentCanGoToEvaluation():  ", ex);
            throw ex;
        } catch (Exception ex) {
            Tools.LogCat("Exception-getAgentCanGoToEvaluation():  ", ex);
            throw ex;
        }
    }
    //endregion

    //region "Asyn Display Data List Task"
    public class AsynDisplayDataListTask extends AsyncTask<String, Void, Integer>{
        List<RowDataListModel> rowDataList = null;
        Shared_Preferences SPref = Tools.SharedPreferences(DisplayListActivity.this);

        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            try {
                if( targetList != null ) {
                    targetList.clear();
                }
                //if ( listType == Constant.LIST_MODULE_EXERCICES ) {
                    //rowDataList = queryRecordMngr.searchListProfilUser(moduleStatut);
                    //message = "Pa gen Batiman nan sistèm nan.";
                //}
                if ( listType == Constant.LIST_MODULE_EXERCICES ) {
                    //rowDataList = queryRecordMngr.searchList_FormulaireExercice();
                    rowDataList = queryRecordMngr.searchList_FormulaireExercice_ByType(id);
                    message = "Pas d'exercice dans la Base de données.";

                }else if (listType == Constant.LIST_TYPE_EXERCICE) {
                    rowDataList = queryRecordMngr.searchList_TypeExercice();
                }
                if( rowDataList != null ){
                    targetList.addAll(rowDataList);
                }else{
                    //Tools.ToastMessage(DisplayListActivity.this, message, Constant.GravityCenter);
                    Tools.LogCat(message);
                }
            }catch (Exception ex) {
                Tools.LogCat( "Exception: doInBackground - getMessage:" , ex);
                ex.printStackTrace();
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer result) {
            try {
                progressBar.setVisibility(View.GONE);
                if (result == 1) {
                    //toolbar.setTitle(title+"("+targetList.size()+")");
                    mAdapter.setFilter(targetList);

                    ReloadDataListTask();
                    setTitleHeader();
                } else {
                    Log.e(TAG, "Failed to fetch data!");
                }
            }catch (Exception ex) {
                Tools.LogCat( "Exception: onPostExecute - getMessage:", ex);
                ex.printStackTrace();
            }
        }
    }

    private void setTitleHeader() {
        int nbrsave=0,nbrTotal=0;
        if( targetList != null )
            nbrsave = targetList.size();

        if (listType == Constant.LIST_MODULE_EXERCICES) {
            list_header_1.setText(title + " [" + nbrsave + "]");

        //} else if(listType == Constant.LIST_MODULE_COMPTE_UTILISATEUR_16){
        //    list_header_1.setText(getString(R.string.label_Kont_Itilizate) + " "+ moduleStatutString + " [" + nbrsave + "]");
        }
    }
    //endregion
}
