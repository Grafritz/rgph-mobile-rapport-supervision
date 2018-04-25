package ht.ihsi.rgph.rapport.supervision.Utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.PersonnelDao;
import ht.ihsi.rgph.rapport.supervision.Constant.Constant;
import ht.ihsi.rgph.rapport.supervision.Mappers.ModelMapper;
import ht.ihsi.rgph.rapport.supervision.Models.KeyValueModel;
import ht.ihsi.rgph.rapport.supervision.Models.PersonnelModel;
import ht.ihsi.rgph.rapport.supervision.R;

/**
 * Created by JeanFritz on 3/30/2016.
 */
public class Tools {
    public static String TAG = "TAG-IHSI:";
    private static Toast toast;
    private static ProgressDialog progressDialog;

    public static void ToastMessage(Context context, String Message, int _Gravity) {
        toast = Toast.makeText(context, Message, Toast.LENGTH_LONG);
        if(_Gravity == Constant.GravityCenter)
            toast.setGravity(Constant.GravityCenter, 0, 0);
        else if(_Gravity == Constant.GravityBottom)
            toast.setGravity(Constant.GravityBottom, 0, 0);
        else if(_Gravity == Constant.GravityTop)
            toast.setGravity(Constant.GravityTop, 0, 0);
        else if(_Gravity == Constant.GravityEnd)
            toast.setGravity(Constant.GravityEnd, 0, 0);

        toast.show();
    }

    public static void ToastMessage(Context context, String Message) {
        toast = Toast.makeText(context, Message, Toast.LENGTH_LONG);
        toast.setGravity(Constant.GravityBottom, 0, 0);
        toast.show();
    }

    public static void Log_and_Toast(Context context, String Message) {
        String TAG2 = TAG + context.getClass().getSimpleName();
        Log.e(TAG2, Message);
        toast = Toast.makeText(context, Message, Toast.LENGTH_LONG);
        toast.setGravity(Constant.GravityBottom, 0, 0);
        toast.show();
    }
    public static void Log_and_Toast(Context context, Exception ex) {
        String TAG2 = TAG + context.getClass().getSimpleName();
        Log.w(TAG2, ex);
        toast = Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG);
        toast.setGravity(Constant.GravityBottom, 0, 0);
        toast.show();
    }
    public static void Log_and_Toast(Context context, String Message, Exception ex) {
        String TAG2 = TAG + context.getClass().getSimpleName();
        Log.e(TAG2, Message +": " + ex);
        toast = Toast.makeText(context, Message, Toast.LENGTH_LONG);
        toast.setGravity(Constant.GravityBottom, 0, 0);
        toast.show();
    }

    public static void LogCat(Context context, String msg) {
        String TAG2 = TAG + context.getClass().getSimpleName();
        Log.e(TAG2, msg);
    }
    public static void LogCat(String log, String msg) {
        if(log.equalsIgnoreCase("E"))
            Log.e(TAG, msg);
        if(log.equalsIgnoreCase("I"))
            Log.i(TAG, msg);
        if(log.equalsIgnoreCase("D"))
            Log.d(TAG, msg);
        if(log.equalsIgnoreCase("W"))
            Log.w(TAG, msg);
        if(log.equalsIgnoreCase("V"))
            Log.v(TAG, msg);
    }
    public static void LogCat( String msg) {
        Log.e(TAG, msg);
    }
    public static void LogCat(Context context, String msg, Exception ex) {
        String TAG2 = TAG + context.getClass().getSimpleName();
        Log.w(TAG2, msg, ex);
    }
    public static void LogCat(Context context, Exception ex) {
        String TAG2 = TAG + context.getClass().getSimpleName();
        Log.w(TAG2, ex);
    }
    public static void LogCat(String msg, Exception ex) {
        Log.w(TAG, msg, ex);
    }
    public static void LogCat(Exception ex) {
        Log.w(TAG, ex);
    }
    //

    public static void AlertDialogMsg(Context context, String msg){
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(context);
        String titleMsg = context.getResources().getString(R.string.msg_title_succesInformation);
        aBuilder.setTitle(titleMsg);
        aBuilder.setMessage(msg);
        aBuilder.setCancelable(false);
        aBuilder.setIcon(R.mipmap.ic_launcher);

        //set Positive Button 1
        aBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }//
        );
        aBuilder.show();
    }//
    //
    public static void AlertDialogMsg(Context context, String msg, String E_or_S){
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(context);
        String titleMsg = context.getResources().getString(R.string.msg_title_succesInformation);
        if (E_or_S.equalsIgnoreCase("")) E_or_S = "E";
        if (E_or_S.equalsIgnoreCase("E")) titleMsg = context.getResources().getString(R.string.msg_title_erreurInformation);

        aBuilder.setTitle(titleMsg);
        aBuilder.setMessage(msg);
        aBuilder.setCancelable(false);
        aBuilder.setIcon(R.mipmap.ic_launcher);

        //set Positive Button 1
        aBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }//
        );
        //
        /*aBuilder.setOnCancelListener(
                new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {

                    }
                }
        );*/
        aBuilder.show();
    }//

    public static boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    public static Shared_Preferences SharedPreferences(Context context){ return new Shared_Preferences(context); }

    public static void StoreInfoPresonnel_PreferenceManager(Context context,  PersonnelModel personnelModel){
        ModelMapper.MapToPreferences( context, personnelModel);
    }

    public static boolean CheckPrefIsUseConnected(Context context) {
        return SharedPreferences(context).getIsConnected();
    }//



    //region REPONSE STRING QUESTION
    public static String getString_Reponse(String codeReponse,  String nomChamps) {
        try{
            String result="";
            ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            //BATIMENT
            if (nomChamps.equalsIgnoreCase(PersonnelDao.Properties.ProfileId.columnName)) {
                keyValueModels.add(new KeyValueModel("" + Constant.PRIVILEGE_DEVELOPPEUR, "Devlopè"));
                keyValueModels.add(new KeyValueModel("" + Constant.PRIVILEGE_ASTIC, "ACTIC"));
                keyValueModels.add(new KeyValueModel("" + Constant.PRIVILEGE_SUPERVISEUR, "Sipèvizè"));
                keyValueModels.add(new KeyValueModel("" + Constant.PRIVILEGE_AGENT, "Ajan"));
            }else if (nomChamps.equalsIgnoreCase(PersonnelDao.Properties.EstActif.columnName)){
                keyValueModels.add(new KeyValueModel("1", "Aktif"));
                keyValueModels.add(new KeyValueModel("0", "Pa aktif"));
            }

            if ( keyValueModels != null  && keyValueModels.size()>0){
                for(KeyValueModel key : keyValueModels){
                    if( key.getKey().equalsIgnoreCase(codeReponse)){
                        result = key.getValue();
                        break;
                    }
                }
            }
            return result;
        }catch (Exception ex) {
            LogCat("Exception-getString_Reponse("+ codeReponse +"): getMessage: ", ex);
            ex.printStackTrace();
        }
        return "";
    }
    public static List<KeyValueModel> getList_TypeExercice() {
        try{
            ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            keyValueModels.add(new KeyValueModel("" + Constant.EXERCICE_ENTRAINEMENT_1, "Entrainement"));
            keyValueModels.add(new KeyValueModel("" + Constant.EXERCICE_FORMATIVE_2, "Formative"));
            keyValueModels.add(new KeyValueModel("" + Constant.EXERCICE_SOMMATIVE_3, "Sommative"));
            keyValueModels.add(new KeyValueModel("" + Constant.EXERCICE_OBSERVATION_4, "Observation"));

            return keyValueModels;
        }catch (Exception ex) {
            LogCat("Exception-getList_TypeExercice(): ", ex);
            ex.printStackTrace();
        }
        return null;
    }
    public static String getString_TypeExercice(String key) {
        try{
            String result="";
            List<KeyValueModel> keyValueModels = getList_TypeExercice();

            if ( keyValueModels != null  && keyValueModels.size()>0){
                for(KeyValueModel ke : keyValueModels){
                    if( ke.getKey().equalsIgnoreCase(key)){
                        result = ke.getValue();
                        break;
                    }
                }
            }
            return result;
        }catch (Exception ex) {
            LogCat("Exception-getString_TypeExercice: ", ex);
            ex.printStackTrace();
        }
        return "";
    }

    public static String getStringStatut(short moduleStatut) {
        String result = "";
        if ( moduleStatut == Constant.STATUT_MODULE_KI_FINI_1 ) {
            return result = " Ki Fini";
        }else if ( moduleStatut == Constant.STATUT_MODULE_KI_MAL_RANPLI_2 ) {
            return result = " Ki Mal Rampli";
        }else if ( moduleStatut == Constant.STATUT_MODULE_KI_PA_FINI_3 ) {
            return result = " Ki Pa Fini";
        }
        return result;
    }

    public static String  getDateString_MMddyyyy_HHmmss() {
        Calendar mydate = new GregorianCalendar();
        String dateFormated = "";
        try {
            //  MM/dd/yyyy HH:mm:ss
            int MONTH = (1 + mydate.get(Calendar.MONTH));
            dateFormated = "" + MONTH + "/" + mydate.get(Calendar.DAY_OF_MONTH) + "/" + mydate.get(Calendar.YEAR) + " "
                    + mydate.get(Calendar.HOUR) + ":" + mydate.get(Calendar.MINUTE) + ":" + mydate.get(Calendar.SECOND) + "";

            LogCat( "dateFormated    -> " + dateFormated);
        } catch (Exception e) {
            LogCat( "getDateString_MMddyyyy_HHmmss() - Exception    -> ", e);
        }
        return dateFormated;
    }

    //endregion
}
