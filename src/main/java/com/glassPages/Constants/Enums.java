package com.glassPages.Constants;

public class Enums {
    public enum Testing_glass_DB_Names{
        USERS,
        WORK_PRODUCTS,
        WORK_REQUEST_STAKE_HOLDERS,
        WORK_REQUESTS;

        private final String value;
        Testing_glass_DB_Names(){
            this.value = "Testing_glass.."+ this.name();}
            public String getValue(){
                return value;
            }
        }
        public enum SQL_DB_ColumnNames{
        ID, CREATE_AT, EMAIL, FIRST_NAME,LAST_NAME, ROLE, USERNAME
        }


}
