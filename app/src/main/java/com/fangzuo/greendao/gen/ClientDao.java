package com.fangzuo.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.fangzuo.assist.cloud.Dao.Client;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CLIENT".
*/
public class ClientDao extends AbstractDao<Client, Void> {

    public static final String TABLENAME = "CLIENT";

    /**
     * Properties of entity Client.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property FItemID = new Property(0, String.class, "FItemID", false, "FITEM_ID");
        public final static Property FName = new Property(1, String.class, "FName", false, "FNAME");
        public final static Property FNumber = new Property(2, String.class, "FNumber", false, "FNUMBER");
        public final static Property FOrg = new Property(3, String.class, "FOrg", false, "FORG");
    }


    public ClientDao(DaoConfig config) {
        super(config);
    }
    
    public ClientDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CLIENT\" (" + //
                "\"FITEM_ID\" TEXT," + // 0: FItemID
                "\"FNAME\" TEXT," + // 1: FName
                "\"FNUMBER\" TEXT," + // 2: FNumber
                "\"FORG\" TEXT);"); // 3: FOrg
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CLIENT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Client entity) {
        stmt.clearBindings();
 
        String FItemID = entity.getFItemID();
        if (FItemID != null) {
            stmt.bindString(1, FItemID);
        }
 
        String FName = entity.getFName();
        if (FName != null) {
            stmt.bindString(2, FName);
        }
 
        String FNumber = entity.getFNumber();
        if (FNumber != null) {
            stmt.bindString(3, FNumber);
        }
 
        String FOrg = entity.getFOrg();
        if (FOrg != null) {
            stmt.bindString(4, FOrg);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Client entity) {
        stmt.clearBindings();
 
        String FItemID = entity.getFItemID();
        if (FItemID != null) {
            stmt.bindString(1, FItemID);
        }
 
        String FName = entity.getFName();
        if (FName != null) {
            stmt.bindString(2, FName);
        }
 
        String FNumber = entity.getFNumber();
        if (FNumber != null) {
            stmt.bindString(3, FNumber);
        }
 
        String FOrg = entity.getFOrg();
        if (FOrg != null) {
            stmt.bindString(4, FOrg);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Client readEntity(Cursor cursor, int offset) {
        Client entity = new Client( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // FItemID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // FName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // FNumber
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // FOrg
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Client entity, int offset) {
        entity.setFItemID(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setFName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFNumber(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFOrg(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Client entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Client entity) {
        return null;
    }

    @Override
    public boolean hasKey(Client entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
