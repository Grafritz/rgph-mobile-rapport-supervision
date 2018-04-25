package ht.ihsi.rgph.rapport.supervision.Managers;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.greenrobot.dao.async.AsyncOperation;
import de.greenrobot.dao.async.AsyncOperationListener;
import de.greenrobot.dao.async.AsyncSession;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Commune;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.DaoMaster;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.DaoSession;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Departement;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.FormulaireExercices;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.JustificationReponses;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Personnel;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Question_FormulaireExercices;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Questions;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Reponses;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Vqse;
import ht.ihsi.rgph.rapport.supervision.Constant.Constant;
import ht.ihsi.rgph.rapport.supervision.Utilities.Tools;

/**
 * Created by ajordany on 3/23/2016.
 */
public class LoadStaticDataMngr extends Activity implements AsyncOperationListener {

    protected SQLiteDatabase database;
    protected DaoMaster daoMaster;
    protected DaoSession daoSession;
    protected AsyncSession asyncSession;
    protected List<AsyncOperation> completedOperations;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAsyncOperationCompleted(AsyncOperation operation) {
        completedOperations.add(operation);

    }

    public  void loadData(Context context, SQLiteDatabase db) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();


            Log.w(Tools.TAG + "MainActivity", "loadData method is called!");

            /*  insert COMPTE */
            is = assetManager.open(Constant.DATA_TBL_PERSONNEL);
            String result = "Conpte Utilisateur: \n";
            collectionType = new TypeToken<List<Personnel>>(){}.getType();
            List<Personnel> personnels = gson.fromJson(getStringJson(is), collectionType);
            result += "\t -Lecture du fichier JSON";
            result += "\n \t -Données Utilisateur" + ((personnels != null) ? " ["+personnels.size() +"]":"") +"\n";
            bulkInsertDaTa(Personnel.class, personnels);
            Log.w(Tools.TAG + "MainActivity", result);

            /*  insert TBL_QUESTIONS */
            is = assetManager.open(Constant.TBL_QUESTIONS);
            collectionType = new TypeToken<List<Questions>>(){}.getType();
            List<Questions> questions = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Questions.class,questions);
            Log.w(Tools.TAG + "MainActivity", "TBL_QUESTIONS Data inserted");

            /*  insert TBL_REPONSES */
            is = assetManager.open(Constant.TBL_REPONSES);
            collectionType = new TypeToken<List<Reponses>>(){}.getType();
            List<Reponses> questionReponses = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Reponses.class,questionReponses);
            Log.w(Tools.TAG + "MainActivity", "TBL_REPONSES Data inserted");

             /*  insert TBL_JUSTIFICATION */
            is = assetManager.open(Constant.TBL_JUSTIFICATION_REPONSE);
            collectionType = new TypeToken<List<JustificationReponses>>(){}.getType();
            List<JustificationReponses> modules = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(JustificationReponses.class,modules);
            Log.w(Tools.TAG + "MainActivity", "TBL_JUSTIFICATION_REPONSE Data inserted");

             /*  insert TBL_FORMULAIRE_EXERCICES */
            is = assetManager.open(Constant.TBL_FORMULAIRE_EXERCICES);
            collectionType = new TypeToken<List<FormulaireExercices>>(){}.getType();
            List<FormulaireExercices> questionModules = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(FormulaireExercices.class,questionModules);
            Log.w(Tools.TAG + "MainActivity", "TBL_FORMULAIRE_EXERCICES Data inserted");

             /*  insert TBL_QUESTIONS_FORMULAIRE_EXERCICES */
            is = assetManager.open(Constant.TBL_QUESTIONS_FORMULAIRE_EXERCICES);
            collectionType = new TypeToken<List<Question_FormulaireExercices>>(){}.getType();
            List<Question_FormulaireExercices> question_FormulaireExercices = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Question_FormulaireExercices.class, question_FormulaireExercices);
            Log.w(Tools.TAG + "MainActivity", "TBL_QUESTIONS_FORMULAIRE_EXERCICES Data inserted");

            /*  insert TBL_PAYS */
            /*is = assetManager.open(Constant.TBL_PAYS);
            collectionType = new TypeToken<List<Pays>>(){}.getType();
            List<Pays> payses = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Pays.class, payses);
            Log.w(Tools.TAG + "MainActivity", "TBL_PAYS Data inserted");

             *//*  insert TBL_DEPARTEMENT *//*
            is = assetManager.open(Constant.TBL_DEPARTEMENT);
            collectionType = new TypeToken<List<Departement>>(){}.getType();
            List<Departement> departements = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Departement.class,departements);
            Log.w(Tools.TAG + "MainActivity", "TBL_DEPARTEMENT Data inserted");

            *//*  insert TBL_COMMUNE *//*
            is = assetManager.open(Constant.TBL_COMMUNE);
            collectionType = new TypeToken<List<Commune>>(){}.getType();
            List<Commune> communes = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Commune.class,communes);
            Log.w(Tools.TAG + "MainActivity", "TBL_COMMUNE Data inserted");

            *//*  insert TBL_VQSE *//*
            is = assetManager.open(Constant.TBL_VQSE);
            collectionType = new TypeToken<List<Vqse>>(){}.getType();
            List<Vqse> vqses = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Vqse.class,vqses);
            Log.w(Tools.TAG + "MainActivity", "TBL_VQSE Data inserted");*/

        } catch (Exception ex) {
            Log.e(Tools.TAG , "error loade data: ", ex);
            ex.printStackTrace();
        }
    }

    //region LOAD DATA ALL
    String result = "";
    public synchronized String loadData_PERSONNEL(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();
            Log.i(Tools.TAG + "MainActivity", "loadData_PERSONNEL method is called!");

             /*  insert COMPTE */
            is = assetManager.open(Constant.DATA_TBL_PERSONNEL);
            result = "\nCompte Utilisateur: \n";
            textView.setText(result);
            collectionType = new TypeToken<List<Personnel>>(){}.getType();
            List<Personnel> personnels = gson.fromJson(getStringJson(is), collectionType);
            result += "\t -Lecture du fichier JSON";
            textView.setText(result);
            result += "\n \t -Données Utilisateur" + ((personnels != null) ? " ["+personnels.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(Personnel.class, personnels);
            Log.w(Tools.TAG , "DATA_TBL_PERSONNEL"+ result);
        } catch (Exception ex) {
            result += "Erreur:"+ ex.toString();
            textView.setText(result);
            Tools.LogCat("DATA_TBL_PERSONNEL", ex);
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_DEPARTEMENT(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();
            Log.i(Tools.TAG + "MainActivity", "loadData_DEPARTEMENT method is called!");

             /*  insert TBL_DEPARTEMENT */
            is = assetManager.open(Constant.TBL_DEPARTEMENT);
            result = " \n Département:";
            textView.setText(result);
            collectionType = new TypeToken<List<Departement>>(){}.getType();
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            List<Departement> departements = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Données " + ((departements != null) ? " ["+departements.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(Departement.class, departements);
            Log.w(Tools.TAG , result);
        } catch (Exception ex) {
            result += " \n \t * Erreur:"+ ex.toString();
            textView.setText(result);
            Log.e(Tools.TAG + "MainActivity", "error load data: " + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_COMMUNE(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();
            Log.i(Tools.TAG + "MainActivity", "loadData_COMMUNE method is called!");

            /*  insert TBL_COMMUNE */
            is = assetManager.open(Constant.TBL_COMMUNE);
            result = " \n Commune:";
            textView.setText(result);
            collectionType = new TypeToken<List<Commune>>(){}.getType();
            List<Commune> communes = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            result += " \n \t -Données " + ((communes != null) ? " ["+communes.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(Commune.class, communes);
            Log.w(Tools.TAG + "MainActivity", result);
        } catch (Exception ex) {
            result += " \n \t * Erreur:"+ ex.toString();
            Log.e(Tools.TAG + "MainActivity", "error loade data: " + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_VQSE(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();
            Log.i(Tools.TAG + "MainActivity", "loadData_VQSE method is called!");

            /*  insert TBL_VQSE */
            is = assetManager.open(Constant.TBL_VQSE);
            result = " \n VQSE:";
            textView.setText(result);
            collectionType = new TypeToken<List<Vqse>>(){}.getType();
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            List<Vqse> vqses = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Données " + ((vqses != null) ? " ["+vqses.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(Vqse.class, vqses);
            Log.w(Tools.TAG + "MainActivity", result);
        } catch (Exception ex) {
            Log.e(Tools.TAG + "MainActivity", "error loade data: " + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_QUESTIONS(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();

            is = assetManager.open(Constant.TBL_QUESTIONS);
            result = " TBL_QUESTIONS \n :";
            textView.setText(result);
            collectionType = new TypeToken<List<Questions>>(){}.getType();
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            List<Questions> questions = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Données " + ((questions != null) ? " ["+questions.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(Questions.class, questions);
            Log.w(Tools.TAG , result);
        } catch (Exception ex) {
            Tools.LogCat("error loadData: ", ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_REPONSES(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();

            String Val = "TBL_REPONSES";
            is = assetManager.open(Constant.TBL_REPONSES);
            result = " \n TBL_REPONSES :";
            textView.setText(result);
            collectionType = new TypeToken<List<Reponses>>(){}.getType();
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            List<Reponses> reponses = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Données " + ((reponses != null) ? " ["+reponses.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(Reponses.class, reponses);
            Log.w(Tools.TAG , result);
        } catch (Exception ex) {
            Tools.LogCat("error loadData: ", ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_QUESTIONS_JUSTIFICATION_REPONSE(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();

            String Val = "TBL_JUSTIFICATION_REPONSE";
            is = assetManager.open(Constant.TBL_JUSTIFICATION_REPONSE);
            result = " \n "+ Val +":";
            textView.setText(result);
            collectionType = new TypeToken<List<JustificationReponses>>(){}.getType();
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            List<JustificationReponses> obj = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Données " + ((obj != null) ? " ["+obj.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(JustificationReponses.class, obj);
            Log.w(Tools.TAG , result);
        } catch (Exception ex) {
            Tools.LogCat("error loadData: ", ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_FORMULAIRE_EXERCICES(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();

            String Val = "TBL_FORMULAIRE_EXERCICES";
            is = assetManager.open(Constant.TBL_FORMULAIRE_EXERCICES);
            result = " \n "+ Val +":";
            textView.setText(result);
            collectionType = new TypeToken<List<FormulaireExercices>>(){}.getType();
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            List<FormulaireExercices> reponses = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Données " + ((reponses != null) ? " ["+reponses.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(FormulaireExercices.class, reponses);
            Log.w(Tools.TAG , result);
        } catch (Exception ex) {
            Tools.LogCat("error loadData: ", ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_QUESTIONS_FORMULAIRE_EXERCICES(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();

            String Val = "TBL_QUESTIONS_FORMULAIRE_EXERCICES";
            is = assetManager.open(Constant.TBL_QUESTIONS_FORMULAIRE_EXERCICES);
            result = " \n "+ Val +":";
            textView.setText(result);
            collectionType = new TypeToken<List<Question_FormulaireExercices>>(){}.getType();
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            List<Question_FormulaireExercices> reponses = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Données " + ((reponses != null) ? " ["+reponses.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(Question_FormulaireExercices.class, reponses);
            Log.w(Tools.TAG , result);
        } catch (Exception ex) {
            Tools.LogCat("error loadData: ", ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }
    //endregion

    private  void bulkInsertDaTa(Class cl, List<?> entities){
        try {
            if (entities != null && entities.size() > 0) {

                Log.w(Tools.TAG + "MainActivity", " Classe:" + cl.getSimpleName().toUpperCase() + " / size(): " + entities.size());
                openWritableDb();
                asyncSession.insertOrReplaceInTx(cl, entities);
                assertWaitForCompletion1Sec();
                daoSession.clear();
                //Log.e(Tools.TAG + "MainActivity", "bulkInsertDaTa : DATA INSERT = " + entities.size());
            }else{
                Log.e(Tools.TAG + "MainActivity", "bulkInsertDaTa : entities.size() = 0 " );
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(Tools.TAG + "MainActivity", "Exception - bulkInsertDaTa error insertind data: " + e.getMessage());
        }
    }

    private void assertWaitForCompletion1Sec() {
        asyncSession.waitForCompletion(2000);
        asyncSession.isCompleted();
    }

    /**
     * Query for writable DB
     */
    public void openWritableDb() throws SQLiteException {
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
        asyncSession = daoSession.startAsyncSession();
        asyncSession.setListener(this);
    }

    public static String getStringJson(InputStream is) {
        String json = "";
        try {
            //InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            //Log.w(Tools.TAG + "MainActivity", "Load Json data json :" + json);
        } catch (Exception ex) {
            Log.e(Tools.TAG + "MainActivity", "Load Json data failed!!" + ex.getMessage());
        }
        //Log.i(Tools.TAG + "MainActivity", "getStringJson(): json=" + json);
        return json;
    }
}
