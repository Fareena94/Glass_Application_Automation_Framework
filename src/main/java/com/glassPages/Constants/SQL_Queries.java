package com.glassPages.Constants;

import com.glassPages.Utility.DatabaseUtility;

public class SQL_Queries {
    public static final String SELECT_QUERY_WITH_WHERE_CLAUSE = "Select * from "+ DatabaseEnums.TableFields.TABLE_NAME + "where" + DatabaseEnums.TableFields.COLUMN_NAME + " = " + DatabaseEnums.TableFields.VALUE;
}
