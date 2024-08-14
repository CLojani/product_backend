package com.efuture.assignment.exception;

import java.sql.SQLException;

public class SqlExceptionUtil {

	    public static String handleSQLException(SQLException ex) {
	        String message = "An error occurred while processing your request.";
	        switch (ex.getSQLState()) {
	            case "22001": // Data too long
	                message = "Data too long - " + ex.getMessage();
	                break;
	            case "23505": // Unique constraint violation - duplicate key)
	                message = "Unique constraint violation -  " + ex.getMessage();
	                break;
	            default:
	                message = "SQL Error Code -  " + ex.getErrorCode() + ", SQL State: " + ex.getSQLState() + ", Error Message: " + ex.getMessage();
	                break;
	        }
	        return message;
	    }
	}

