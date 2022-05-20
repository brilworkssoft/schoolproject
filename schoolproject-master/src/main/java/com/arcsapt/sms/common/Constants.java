package com.arcsapt.sms.common;

public interface Constants {
	String STATUS_ERROR = "ERROR";
	String STATUS_SUCCESS = "SUCCESS";
	String STATUS_FAIL = "FAIL";
	// ACCESS TOKEN
	String ACCESS_TOKEN_URL = "http://localhost:8080/sms/oauth/token";
	String ACCESS_TOKEN_CLIENT_ID = "hr";
	String ACCESS_TOKEN_CLIENT_SECRET = "hr";
	// ACCESS TOKEN
	// link
	String LINK_FIELD_ERROR = "";
	String LINK_SUCCESS = "";
	String LINK_ERROR_ACCESS_TOEKN_GENERATION = "";
	String LINK_ERROR_USER_NOT_FOUND = "";
	// TODO DAO start
	String DAO_COMMON = "commonDao";
	// TODO DAO end

	// TODO SERVICE start
	String SERVICE_COMMON = "commonService";
	String SERVICE_USER = "userService";
	// TODO SERVICE end

	// TODO Dao start
	String DAO_FIELD_TARGET = "target";
	// TODO Dao end

	// TODO COntroller Start
	// Menu
	String CONTROLLER_MENU = "menu.html";
	String CONTROLLER_MENU_ADD = "add.html";
	String CONTROLLER_MENU_SHOW = "show.html";
	// TODO COntroller end

	// TODO View start
	// Menu
	String VIEW_MENU_ADD = "menu/add";
	String VIEW_MENU_SHOW = "menu/show";
	// TODO View end

	// TODO Fields Start
	// COmmon
	String FIELD_ACTIVE = "active";
	String FIELD_CREATED_DATE = "createdDate";
	String FIELD_CREATED_BY = "createdBy";
	String FIELD_MODIFY_DATE = "modifyDate";
	String FIELD_MODIFY_BY = "modifyby";
	// MenuMaster
	String FIELD_MENU_MASTER_MENU_ID = "menuId";
	String FIELD_MENU_MASTER_MENU_NAME = "menuName";
	String FIELD_MENU_MASTER_MENU_PARENT = "parent";
	String FIELD_MENU_MASTER_MENU_ORDER = "menuOrder";
	String FIELD_MENU_MASTER_MENU_URL = "menuURL";
	String FIELD_MENU_MASTER_MENU_ICON = "menu_icon";
	String FIELD_MENU_MASTER_MENU_ROLE_MASTERS = "roleMasters";
	String FIELD_MENU_MASTER_MENU_CHILDS = "childs";
	// TODO Fields END

	// TODO String start
	String STRING_DOT = ".";
	String STRING_COLON = ":";
	String STRING_SPACE = " ";
	String STRING_SLASH = "/";
	String STRING_QUESTION = "?";
	String STRING_AND = "&";
	String STRING_EQUAL = "=";

	// TODO String End

	String REDIRECT = "redirect";

	// TODO Entity Class start
	String ENTITY_MENU_MASTER = "menuMaster";
	String ENTITY_ROLE_MASTER = "roleMaster";
	String ENTITY_STUDENT_MASTER = "studentMaster";
	String ENTITY_USER_MASTER = "userMaster";
	// TODO Entity Class end

	// TODO MAP Property start
	String MAP_MENU_MASTER_LIST = "menuList";
	String MAP_MENU_MASTER = "menuMaster";
	// TODO MAP Property end

	// TODO request param start
	String REQUEST_PARAM_FILE = "file";
	// TODO request param end
}
