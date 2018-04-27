package ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.PersonnelDao;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.FormulaireExercicesDao;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Agent_Evaluation_ExercicesDao;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.Question_FormulaireExercicesDao;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.QuestionsDao;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.ReponsesDao;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.JustificationReponsesDao;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.ReponseEntreeDao;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.AgentRapportDao;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.DepartementDao;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.CommuneDao;
import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.VqseDao;
import ht.ihsi.rgph.rapport.supervision.Utilities.Tools;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * Master of DAO (schema version 1): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        PersonnelDao.createTable(db, ifNotExists);
        FormulaireExercicesDao.createTable(db, ifNotExists);
        Agent_Evaluation_ExercicesDao.createTable(db, ifNotExists);
        Question_FormulaireExercicesDao.createTable(db, ifNotExists);
        QuestionsDao.createTable(db, ifNotExists);
        ReponsesDao.createTable(db, ifNotExists);
        JustificationReponsesDao.createTable(db, ifNotExists);
        ReponseEntreeDao.createTable(db, ifNotExists);
        AgentRapportDao.createTable(db, ifNotExists);
        DepartementDao.createTable(db, ifNotExists);
        CommuneDao.createTable(db, ifNotExists);
        VqseDao.createTable(db, ifNotExists);
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        PersonnelDao.dropTable(db, ifExists);
        FormulaireExercicesDao.dropTable(db, ifExists);
        Agent_Evaluation_ExercicesDao.dropTable(db, ifExists);
        Question_FormulaireExercicesDao.dropTable(db, ifExists);
        QuestionsDao.dropTable(db, ifExists);
        ReponsesDao.dropTable(db, ifExists);
        JustificationReponsesDao.dropTable(db, ifExists);
        ReponseEntreeDao.dropTable(db, ifExists);
        AgentRapportDao.dropTable(db, ifExists);
        DepartementDao.dropTable(db, ifExists);
        CommuneDao.dropTable(db, ifExists);
        VqseDao.dropTable(db, ifExists);
    }
    
    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i(Tools.TAG + "greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(new DatabaseContext(context), name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Tools.LogCat("Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

    public static class DatabaseContext extends ContextWrapper {

        private static final String DIRECTORY_DATA="Data";
        private static final String DATABASE_DIR="rgph_rapport_supervision_db";

        public DatabaseContext(Context base) {
            super(base);
        }

        @Override
        public File getDatabasePath(String name) {

            File dir = new File(Environment.getExternalStoragePublicDirectory(
                    DIRECTORY_DATA), DATABASE_DIR);
            if (!dir.mkdirs()) {}
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    DIRECTORY_DATA), DATABASE_DIR + File.separator + name+".db");
            return file;
        }

        @Override
        public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory) {
            SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(this.getDatabasePath(name), null);
            return  result;
        }

        @Override
        public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory, DatabaseErrorHandler errorHandler) {
            return openOrCreateDatabase(name, mode, factory);
        }
    }
    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(PersonnelDao.class);
        registerDaoClass(FormulaireExercicesDao.class);
        registerDaoClass(Agent_Evaluation_ExercicesDao.class);
        registerDaoClass(Question_FormulaireExercicesDao.class);
        registerDaoClass(QuestionsDao.class);
        registerDaoClass(ReponsesDao.class);
        registerDaoClass(JustificationReponsesDao.class);
        registerDaoClass(ReponseEntreeDao.class);
        registerDaoClass(AgentRapportDao.class);
        registerDaoClass(DepartementDao.class);
        registerDaoClass(CommuneDao.class);
        registerDaoClass(VqseDao.class);
    }
    
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
    
}
