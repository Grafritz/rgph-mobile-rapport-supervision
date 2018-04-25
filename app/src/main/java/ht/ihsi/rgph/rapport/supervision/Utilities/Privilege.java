package ht.ihsi.rgph.rapport.supervision.Utilities;


import ht.ihsi.rgph.rapport.supervision.Constant.Constant;

/**
 * Created by JFDuverseau on 11/4/2016.
 */
public class Privilege {
    public static boolean getAccessControl(int idGroup, int action, int statutListing){
        try{
            boolean resultat = false;
            if( idGroup == Constant.PRIVILEGE_ASTIC) {
                resultat = true;
            }else if ( idGroup == Constant.PRIVILEGE_SUPERVISEUR ) {
                resultat = true;
            }else if ( idGroup == Constant.PRIVILEGE_DEVELOPPEUR ) {
                resultat = true;
            }else if ( idGroup == Constant.PRIVILEGE_AGENT ) {
                if( action == Constant.ACTION_RAPPORT ){
                    resultat = false;
                }else{
                    resultat = statutListing != Constant.STATUT_MODULE_KI_FINI_1;
                }
            }
            return  resultat;
        }catch (Exception ex) {
            return false;
        }
    }
}
