package eu.alonia.aloniabeta.util;

import co.plocki.mysql.*;
import eu.alonia.util.APILoader;

import java.sql.SQLException;
import java.util.UUID;

public class KeyHandler {

    private final MySQLTable.fin fin;

    public KeyHandler() {
        MySQLTable table = new MySQLTable();
        table.prepare("betakeys", "keyA", "uuid");
        fin = table.build();
    }

    public String generateKey() {
        MySQLInsert insert = new MySQLInsert();

        String key = UUID.randomUUID().toString();

        insert.prepare(fin, key, "null");
        insert.execute();

        return key;
    }

    public boolean isUsed(String key) {
        MySQLRequest request = new MySQLRequest();
        request.prepare("uuid", "betakeys");
        request.addRequirement("keyA", key);
        MySQLResponse response = request.execute();
        if(response == null || response.isEmpty()) {
            return false;
        }
        return !response.get("uuid").equals("null");
    }

    public boolean setOwner(String key, String uuid) {
        if(!isUsed(key)) {

            try {
                APILoader.driver.getDataSource().getConnection()
                        .prepareStatement("UPDATE betakeys SET uuid = '" + uuid + "' WHERE keyA = '" + key + "'")
                        .executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return true;
        }
        return false;
    }

    public boolean hasKey(String uuid) {

        MySQLRequest request = new MySQLRequest();
        request.prepare("keyA", "betakeys");
        request.addRequirement("uuid", uuid);
        MySQLResponse response = request.execute();
        return response != null && !response.isEmpty();

    }

}
