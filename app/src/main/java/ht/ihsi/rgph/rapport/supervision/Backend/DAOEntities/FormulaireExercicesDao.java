package ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import ht.ihsi.rgph.rapport.supervision.Backend.DAOEntities.FormulaireExercices;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "Tbl_FormulaireExercices".
*/
public class FormulaireExercicesDao extends AbstractDao<FormulaireExercices, Long> {

    public static final String TABLENAME = "Tbl_FormulaireExercices";

    /**
     * Properties of entity FormulaireExercices.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property CodeExercice = new Property(0, Long.class, "codeExercice", true, "codeExercice");
        public final static Property LibelleExercice = new Property(1, String.class, "libelleExercice", false, "libelleExercice");
        public final static Property Descriptions = new Property(2, String.class, "descriptions", false, "descriptions");
        public final static Property Instructions = new Property(3, String.class, "instructions", false, "instructions");
        public final static Property RappelExercice = new Property(4, String.class, "rappelExercice", false, "rappelExercice");
        public final static Property TypeEvaluation = new Property(5, String.class, "typeEvaluation", false, "typeEvaluation");
        public final static Property Statut = new Property(6, String.class, "statut", false, "statut");
        public final static Property DureeEnSeconde = new Property(7, String.class, "dureeEnSeconde", false, "dureeEnSeconde");
    };


    public FormulaireExercicesDao(DaoConfig config) {
        super(config);
    }
    
    public FormulaireExercicesDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"Tbl_FormulaireExercices\" (" + //
                "\"codeExercice\" INTEGER PRIMARY KEY ," + // 0: codeExercice
                "\"libelleExercice\" TEXT," + // 1: libelleExercice
                "\"descriptions\" TEXT," + // 2: descriptions
                "\"instructions\" TEXT," + // 3: instructions
                "\"rappelExercice\" TEXT," + // 4: rappelExercice
                "\"typeEvaluation\" TEXT," + // 5: typeEvaluation
                "\"statut\" TEXT," + // 6: statut
                "\"dureeEnSeconde\" TEXT);"); // 7: dureeEnSeconde
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"Tbl_FormulaireExercices\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, FormulaireExercices entity) {
        stmt.clearBindings();
 
        Long codeExercice = entity.getCodeExercice();
        if (codeExercice != null) {
            stmt.bindLong(1, codeExercice);
        }
 
        String libelleExercice = entity.getLibelleExercice();
        if (libelleExercice != null) {
            stmt.bindString(2, libelleExercice);
        }
 
        String descriptions = entity.getDescriptions();
        if (descriptions != null) {
            stmt.bindString(3, descriptions);
        }
 
        String instructions = entity.getInstructions();
        if (instructions != null) {
            stmt.bindString(4, instructions);
        }
 
        String rappelExercice = entity.getRappelExercice();
        if (rappelExercice != null) {
            stmt.bindString(5, rappelExercice);
        }
 
        String typeEvaluation = entity.getTypeEvaluation();
        if (typeEvaluation != null) {
            stmt.bindString(6, typeEvaluation);
        }
 
        String statut = entity.getStatut();
        if (statut != null) {
            stmt.bindString(7, statut);
        }
 
        String dureeEnSeconde = entity.getDureeEnSeconde();
        if (dureeEnSeconde != null) {
            stmt.bindString(8, dureeEnSeconde);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public FormulaireExercices readEntity(Cursor cursor, int offset) {
        FormulaireExercices entity = new FormulaireExercices( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // codeExercice
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // libelleExercice
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // descriptions
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // instructions
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // rappelExercice
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // typeEvaluation
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // statut
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // dureeEnSeconde
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, FormulaireExercices entity, int offset) {
        entity.setCodeExercice(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLibelleExercice(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDescriptions(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setInstructions(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setRappelExercice(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTypeEvaluation(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setStatut(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setDureeEnSeconde(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(FormulaireExercices entity, long rowId) {
        entity.setCodeExercice(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(FormulaireExercices entity) {
        if(entity != null) {
            return entity.getCodeExercice();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
