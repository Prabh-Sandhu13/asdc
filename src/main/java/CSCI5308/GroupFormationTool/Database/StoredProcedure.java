package CSCI5308.GroupFormationTool.Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoredProcedure {

    private String storedProcedureName;
    private Connection connection;
    private CallableStatement statement;

    public StoredProcedure(String storedProcedureName) throws SQLException {
        this.storedProcedureName = storedProcedureName;
        connection = null;
        statement = null;
        openConnection();
        createStatement();
    }

    private void createStatement() throws SQLException {
        statement = connection.prepareCall("{call " + storedProcedureName + "}");
    }

    private void openConnection() throws SQLException {
        connection = ConnectionManager.instance().getDBConnection();
    }

    public void removeConnections() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                if (!connection.isClosed()) {
                    connection.close();
                }
            }

        } catch (Exception e) {

        }
    }

    public void setInputStringParameter(int paramIndex, String value) throws SQLException {
        statement.setString(paramIndex, value);
    }

    public void setInputIntParameter(int paramIndex, long value) throws SQLException {
        statement.setLong(paramIndex, value);
    }

    public void registerOutputParameterLong(int paramIndex) throws SQLException {
        statement.registerOutParameter(paramIndex, java.sql.Types.BIGINT);
    }

    public void registerOutputParameterBoolean(int paramIndex) throws SQLException {
        statement.registerOutParameter(paramIndex, java.sql.Types.BOOLEAN);
    }

    public boolean getParameter(int paramIndex) throws SQLException {
        return statement.getBoolean(paramIndex);
    }

    public long getParameterLong(int paramIndex) throws SQLException {
        return statement.getLong(paramIndex);
    }

    public ResultSet executeWithResults() throws SQLException {
        if (statement.execute()) {
            return statement.getResultSet();
        }
        return null;
    }

    public void execute() throws SQLException {
        statement.execute();
    }
}