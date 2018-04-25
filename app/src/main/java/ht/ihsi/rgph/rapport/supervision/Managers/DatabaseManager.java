package ht.ihsi.rgph.rapport.supervision.Managers;

/**
 * Created by ajordany on 3/22/2016.
 */
public interface DatabaseManager {

    /**
     * Closing available connections
     */
    void closeConnections();

    /**
     * Delete all tables and content from our database
     */
    void dropDatabase();
}
