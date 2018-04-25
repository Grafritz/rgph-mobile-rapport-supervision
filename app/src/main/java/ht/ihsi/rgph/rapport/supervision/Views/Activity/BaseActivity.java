package ht.ihsi.rgph.rapport.supervision.Views.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import ht.ihsi.rgph.rapport.supervision.Constant.Constant;
import ht.ihsi.rgph.rapport.supervision.Managers.CURecordMngr;
import ht.ihsi.rgph.rapport.supervision.Managers.CURecordMngrImpl;
import ht.ihsi.rgph.rapport.supervision.Managers.FormDataMngr;
import ht.ihsi.rgph.rapport.supervision.Managers.FormDataMngrImpl;
import ht.ihsi.rgph.rapport.supervision.Managers.QueryRecordMngr;
import ht.ihsi.rgph.rapport.supervision.Managers.QueryRecordMngrImpl;
import ht.ihsi.rgph.rapport.supervision.Models.KeyValueModel;
import ht.ihsi.rgph.rapport.supervision.Models.ReponsesModel;
import ht.ihsi.rgph.rapport.supervision.Utilities.Shared_Preferences;
import ht.ihsi.rgph.rapport.supervision.Utilities.Tools;
import ht.ihsi.rgph.rapport.supervision.Views.Adapters.RadioListAdapterKeyValue;
import ht.ihsi.rgph.rapport.supervision.Views.Adapters.RadioListJustificationReponsesAdapter;
import ht.ihsi.rgph.rapport.supervision.Views.Adapters.RadioListReponsesAdapter;


/**
 * Created by ajordany on 3/22/2016.
 */
public class BaseActivity extends AppCompatActivity {

    protected FormDataMngr formDataMngr;
    protected CURecordMngr cuRecordMngr;
    protected QueryRecordMngr queryRecordMngr;
    protected Intent intent;
    public Boolean cancel = true;
    public String message = "";
    public Toolbar toolbar;
    public RecyclerView recyclerViewReponse;
    public LinearLayout LL_RecyclerView;
    public RadioListReponsesAdapter radioListReponsesAdapter;
    public RadioListJustificationReponsesAdapter radioListJustificationReponsesAdapter;
    public RadioListAdapterKeyValue radioListAdapterKeyValue;
    public ReponsesModel codeReponseRecyclerView = null;
    public KeyValueModel codeReponseKeyValueModel = null;
    public Boolean isRetour = false;
    public Shared_Preferences infoUser = null;
    public int profilId = 0;
    public String nomUtilisateur="";
    public long getPersId = 0;

    //region JODA TIME// Format for input
    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("MM/dd/YYYY HH:mm:ss");
    //DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("EEE, dd MMM YYYY HH:mm:ss zzz");
    //public DateTime getDateDebutCollecte;
    public String dateString ="";// Tools.getDateString_MMddyyyy_HHmmss();
    //endregion

    @Override
    protected void onDestroy() {
       /* if (formDataMngr != null)
            formDataMngr.closeDbConnections();

        if(cuRecordMngr!=null){
            cuRecordMngr.closeDbConnections();
        }

        if(queryRecordMngr!=null){
            queryRecordMngr.closeDbConnections();
        }*/

        Tools.LogCat(this, "onDestroy : No closeDbConnections()");
        super.onDestroy();
    }

    public void init(int type){
        if(Constant.FORM_DATA_MANAGER==type){
            formDataMngr = new FormDataMngrImpl(this);
        }else if(Constant.CU_RECORD_MANAGER==type){
            cuRecordMngr=new CURecordMngrImpl(this);
        }else if(Constant.QUERY_RECORD_MANAGER==type){
            queryRecordMngr=new QueryRecordMngrImpl(this);
        }
    }

    public void CheckPrefIsUseConnected() {
        Boolean checkPrefIsUseConnected =  Tools.CheckPrefIsUseConnected(this);
        if(!checkPrefIsUseConnected){
            cancel=true;
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else {
            cancel=false;
        }
    }

}
