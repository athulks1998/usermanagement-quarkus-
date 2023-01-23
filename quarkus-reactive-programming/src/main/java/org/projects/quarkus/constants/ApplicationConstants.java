package org.projects.quarkus.constants;

/**
 * @author athul
 * INFO : To store the constant Data 
 */
public class ApplicationConstants {
	
	ApplicationConstants(){}

	//////////////////////////////Common constants/////////////////////////////
	
	 public static final String SUCCESS= "Success";
	 
	 public static final String FAILURE ="Failure";
	 
	 public static final Long SUCCESS_CODE = 200l;
	 
	 public static final Long INTERNAL_SERVER_ERROR_CODE = 500l;
	 
	 public static final Long UNAUTHORIZED_CODE = 401l;
	 
	 ///////////////////////////User Service Messages ///////////////////////////
	 
	 public static final String REGISTER_USER_SUCCESS= "User registered successfully";
	 
	 public static final String REGISTER_USER_FAILURE= "Failed to register user";
	 
	 public static final String VIEW_USER_DETAILS_SUCCESS ="User details fetched successfully";
	 
	 public static final String VIEW_USER_DETAILS_FAILURE= "Failed to fetch user details";
	 
	 /////////////////////////////User Group Service //////////////////////////////
	 
	 public static final String CREATE_GROUP_SUCCESS="User group created successfully";
	 
	 
	 //////////////////////////////Not Applicable////////////////////////////
	 
	 public static final String NOT_APPLICABLE="N/A";
	 
	 
     ///////////////////////////Access Service Messages ///////////////////////////
	 
	 public static final String USER_LOGIN_FAILURE="An Error occured while validating user credentials";
	 
	 public static final String USER_LOGIN_SUCCESS="User logged in successfully!";
	 
}
