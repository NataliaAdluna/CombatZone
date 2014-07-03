package pl.adluna.combatzone.model.network;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * Created by Natalia Stawowy on 02.07.14.
 */
public class GetTrainingTask {
    private static final String url = "jdbc:mysql://192.168.1.118/adluna_combatzone";
    private static final String user = "adluna_combatzon";
    private static final String pass = "combatzone2014";
    public void testDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, user, pass);
            
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //ResultSet rs = st.executeQuery("select * from tableTest");
           // ResultSetMetaData rsmd = rs.getMetaData();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
}
