package se.adi.floggit.api;

public enum ResponseType
{
	USER_CREATED,
	USER_NOT_CREATED,
	USER_UPDATED,
	USER_DELETED,
	USER_NOT_DELETED,
	
	USER_EMAIL_DUPLICATE,
	USER_EMAIL_NOT_FOUND,
	
	LOGIN_SUCCESSFUL,
	LOGIN_FAILED,
	
	CATEGROY_CREATED,
	CATEGORY_NOT_CREATED,
	CATEGORY_UPDATED, 
	CATEGORY_NOT_FOUND,
	CATEGORY_DELETED, 
	CATEGORY_ALREADY_IN_DB, 
	STAFF_NOT_FOUND,
	
	SERVER_CONNECTION_SUCCESSFUL,
	SERVER_CONNECTION_FAILED
}
