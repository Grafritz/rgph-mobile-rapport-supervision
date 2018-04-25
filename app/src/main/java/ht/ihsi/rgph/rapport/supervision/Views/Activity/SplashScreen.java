package ht.ihsi.rgph.rapport.supervision.Views.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.List;

import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.DaoMaster;
import ht.ihsi.rgph.rapport.supervision.Constant.Constant;
import ht.ihsi.rgph.rapport.supervision.Managers.LoadStaticDataMngr;
import ht.ihsi.rgph.rapport.supervision.Models.RowDataListModel;
import ht.ihsi.rgph.rapport.supervision.R;
import ht.ihsi.rgph.rapport.supervision.Utilities.Tools;


/**
 * Created by JFDuverseau on 9/1/2016.
 */
public class SplashScreen extends BaseActivity {

    protected boolean _active = true;
    protected int _splashTime = 3000;
    private TextView tv_MsgSplashScreen; // time to display the splash screen in ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        Tools.LogCat( "onCreate() : SplashScreen");

        tv_MsgSplashScreen = (TextView)this.findViewById(R.id.tv_MsgSplashScreen);

        init(Constant.QUERY_RECORD_MANAGER);
        init(Constant.FORM_DATA_MANAGER);
        /*Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) {
                            waited += 100;
                        }
                        tv_MsgSplashScreen.setText("Chargement ..." + waited);
                        Tools.LogCat("Chargement ..." + waited);
                    }
                } catch (Exception e) {

                } finally {
                    //startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    //finish();
                }
            };
        };*/

        //splashTread.start();
        //new AsynDataTask().execute();
        //LoadData();
    }

    public void LoadData(){
        try {
            DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(this, Constant.DATABASE_NAME, null);
            SQLiteDatabase database = mHelper.getReadableDatabase();
            LoadStaticDataMngr loadStaticDataMngr=new LoadStaticDataMngr();

            int countDept = queryRecordMngr.countAllDepartement();
            if( countDept <= 0 ){
                //loadStaticDataMngr.loadData(this, database);
                message = loadStaticDataMngr.loadData_DEPARTEMENT(this, database, tv_MsgSplashScreen);
                tv_MsgSplashScreen.setText( message );
                message += loadStaticDataMngr.loadData_PERSONNEL(this, database, tv_MsgSplashScreen);
                tv_MsgSplashScreen.setText( message );
                message += loadStaticDataMngr.loadData_QUESTIONS(this, database, tv_MsgSplashScreen);
                tv_MsgSplashScreen.setText( message );
                message += loadStaticDataMngr.loadData_REPONSES(this, database, tv_MsgSplashScreen);
                tv_MsgSplashScreen.setText( message );
                message += loadStaticDataMngr.loadData_QUESTIONS_JUSTIFICATION_REPONSE(this, database, tv_MsgSplashScreen);
                tv_MsgSplashScreen.setText( message );
                message += loadStaticDataMngr.loadData_FORMULAIRE_EXERCICES(this, database, tv_MsgSplashScreen);
                tv_MsgSplashScreen.setText( message );
                message += loadStaticDataMngr.loadData_QUESTIONS_FORMULAIRE_EXERCICES(this, database, tv_MsgSplashScreen);

                tv_MsgSplashScreen.setText( message );

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        CheckPrefIsUseConnected();
                    }
                }, 1000);
            }else{
                CheckPrefIsUseConnected();
            }
        }catch (Exception ex) {
            Tools.LogCat( "Exception:LoadData() - getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
    }

    public void CheckPrefIsUseConnected() {
        Boolean checkPrefIsUseConnected =  Tools.CheckPrefIsUseConnected(this);
        if(!checkPrefIsUseConnected){
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else {
            finish();
        }
    }//

    @Override
    protected void onResume() {
        Tools.LogCat( "onResume() : SplashScreen");
        new Handler().postDelayed(new Runnable() {
                public void run() {
                    LoadData();
               }
            }, 1000);
        //LoadData();
        super.onResume();
    }

    public class AsynDataTask extends AsyncTask<String, Void, Boolean> {
        List<RowDataListModel> targetList = null;
        @Override
        protected void onPreExecute() {
            //setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                LoadData();
                //int countDept = queryRecordMngr.countAllDepartement();
                return true;
            }catch (Exception ex) {
                Tools.LogCat( "Exception:AsynDataTask-doInBackground() - getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
                ex.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            //progressBar.setVisibility(View.GONE);
            if ( result ) {
                /*message = "DATA_TBL_PERSONNEL  inserted";
                message += "\n" + "TBL_DEPARTEMENT  inserted";
                message += "\n" + "TBL_COMMUNE  inserted";
                message += "\n" + "TBL_VQSE  inserted";
                message += "\n" + "TBL_CODE_SDE  inserted";
                tv_MsgSplashScreen.setText("Chargement ... \n" + message );*/
            } else {
            }

        }
    }
}
