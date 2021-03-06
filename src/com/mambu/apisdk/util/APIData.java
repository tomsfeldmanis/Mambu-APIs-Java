package com.mambu.apisdk.util;

//
// This class defines string constants and other constants for Mambu API services
//
//
public class APIData {

	public final static String APPLICATION_KEY = "appkey";

	// Users API

	public static final String USERS = "users";
	// Custom Views. Added in Mambu 3.7
	public static final String VIEWS = "views";
	public static final String FOR = "for";
	public static final String VIEW_FILTER = "viewfilter";

	// Loans and Savings API
	public static final String LOANS = "loans";
	public static final String SAVINGS = "savings";
	public static final String CLIENTS = "clients";
	public static final String GROUPS = "groups";
	public static final String FULL_DETAILS = "fullDetails";
	public static final String ACTIVE = "ACTIVE";
	public static final String INACTIVE = "INACTIVE";
	// Client Types. Added in 3.9
	public static final String CLIENT_TYPES = "clienttypes";
	// Group Role Names. Added in 3.9
	public static final String GROUP_ROLE_NAMES = "grouprolenames";

	public static final String DATE = "date";
	public static final String FIRST_REPAYMENT_DATE = "firstRepaymentDate";

	// Transaction Channels endpoint
	public static final String TRANSACTION_CHANNELS = "transactionchannels";

	public static final String TYPE = "type";
	// Transaction Channel Fields
	public static final String BANK_NUMBER = "bankNumber";
	public static final String RECEIPT_NUMBER = "receiptNumber";
	public static final String CHECK_NUMBER = "checkNumber";
	public static final String BANK_ACCOUNT_NUMBER = "bankAccountNumber";
	public static final String BANK_ROUTING_NUMBER = "bankRoutingNumber";
	public static final String ACCOUNT_NAME = "accountName";
	public static final String IDENTIFIER = "identifier";

	public static final String NOTES = "notes";
	//
	public static final String TRANSACTIONS = "transactions";
	public static final String TYPE_REPAYMENT = "REPAYMENT";
	public static final String TYPE_DISBURSMENT = "DISBURSMENT";
	public static final String DISBURSMENT_ADJUSTMENT = "DISBURSMENT_ADJUSTMENT"; // added in 3.9
	public static final String TYPE_APPROVAL = "APPROVAL";
	public static final String TYPE_UNDO_APPROVAL = "UNDO_APPROVAL";
	public static final String TYPE_REJECT = "REJECT";
	public static final String TYPE_FEE = "FEE";
	public static final String TYPE_DEPOSIT = "DEPOSIT";
	public static final String TYPE_WITHDRAWAL = "WITHDRAWAL";
	public static final String TYPE_TRANSFER = "TRANSFER";
	public static final String TYPE_LOCK = "LOCK";
	public static final String TYPE_UNLOCK = "UNLOCK";

	// Type of account closer transaction
	public static enum CLOSER_TYPE {
		REJECT, WITHDRAW
	};

	public static final String AMOUNT = "amount";

	// The PAYMENT_METHOD indicates the ID of the Transaction Channel used for the account transaction
	public static final String PAYMENT_METHOD = "method";

	public static final String REPAYMENT_NUMBER = "repayment";

	public static final String TO_SAVINGS = "toSavingsAccount";
	public static final String TO_LOAN = "toLoanAccount";

	// Filters
	public static final String BRANCH_ID = "branchId";
	public static final String CREDIT_OFFICER_USER_NAME = "creditOfficerUsername";
	public static final String ACCOUNT_STATE = "accountState";
	public static final String CLIENT_STATE = "state";

	public static final String OFFSET = "offset";
	public static final String LIMIT = "limit";

	// Products
	public static final String LOANPRODUCTS = "loanproducts";
	public static final String SAVINGSRODUCTS = "savingsproducts";

	// Tasks
	public static final String TITLE = "title";
	public static final String USERNAME = "username";
	public static final String DESCRIPTION = "description";
	public static final String DUE_DATE = "duedate";
	public static final String CLIENT_ID = "clientid";
	public static final String GROUP_ID = "groupid";

	// Accounting API
	public static final String GLACCOUNTS = "glaccounts";
	public static final String GLJOURNALENTRIES = "gljournalentries";

	// Intelligence API
	public static final String INDICATORS = "indicators";

	// Organization services
	public static String BRANCHES = "branches";
	public static String CENTRES = "centres";
	public static String CURRENCIES = "currencies";

	// Custom fields and Custom Field Sets
	public static String CUSTOM_FIELDS = "customfields";
	public static String CUSTOM_FIELD_SETS = "customfieldsets";
	public static String CUSTOM_INFORMATION = "custominformation";
	public static String CUSTOM_FIELD_SETS_TYPE = "type";

	// Repayments
	public static final String REPAYMENTS = "repayments";

	// Schedule for Loan Products endpoint. Added in 3.9
	public static final String SCHEDULE = "schedule";
	// Parameters supported by the loan product schedule API (MBU-6789) and loan terms patch API (MBU-7758)
	public static final String LOAN_AMOUNT = "loanAmount";
	public static final String ANTICIPATE_DISBURSEMENT = "anticipatedDisbursement";
	public static final String EXPECTED_DISBURSEMENT = "expectedDisbursementDate";
	// "firstRepaymentDate" - is already defined as FIRST_REPAYMENT_DATE
	public static final String INTEREST_RATE = "interestRate";
	public static final String INTEREST_RATE_SPREAD = "interestSpread";
	public static final String REPAYMENT_INSTALLMENTS = "repaymentInstallments";
	public static final String GRACE_PERIOD = "gracePeriod";
	public static final String REPAYMENT_PERIOD_UNIT = "repaymentPeriodUnit";
	public static final String REPAYMENT_PERIOD_COUNT = "repaymentPeriodCount";
	public static final String PRNICIPAL_REPAYMENT_INTERVAL = "principalRepaymentInterval";
	public static final String PERIODIC_PAYMENT = "periodicPayment";
	public static final String PENALTY_RATE = "penaltyRate";

	public static final String DUE_FROM = "dueFrom";
	public static final String DUE_TO = "dueTo";

	// Search API
	// API's endpoint
	public static String SEARCH = "search";
	public static String QUERY = "query";
	public static String SEARCH_TYPES = "type";

	// Tasks API
	// API's endpoint
	public static String TASKS = "tasks";
	public static String STATUS = "status";

	// Documents API
	// API's endpoint
	public static String DOCUMENTS = "documents";

	// API endpoints for uploading client profile picture and signature. Available since 3.9
	public static String PROFILE_PICTURE = "PROFILE_PICTURE";
	public static String SIGNATURE = "SIGNATURE";
	// Image downloads
	// API end point
	public static String IMAGES = "images";
	// Params for Image downloads
	public static String SIZE = "size";

	public enum IMAGE_SIZE_TYPE {
		LARGE, MEDIUM, SMALL_THUMB, TINY_THUMB
	}

	// Activities API
	public static final String ACTIVITIES = "activities";
	public static final String FROM = "from";
	public static final String TO = "to";
	public static final String CENTRE_ID = "centreID";
	public static final String SAVINGS_ACCOUNT_ID = "savingsAccountID";
	public static final String LOAN_ACCOUNT_ID = "loanAccountID";
	public static final String LOAN_PRODUCT_ID = "loanProductID";
	public static final String SAVINGS_PRODUCT_ID = "savingsProductID";
	public static final String USER_ID = "userID";

	// Client fields
	public static String FIRST_NAME = "firstName";
	public static String LAST_NAME = "lastName";
	public static String BIRTH_DATE = "birthdate";
	public static String ID_DOCUMENT = "idDocument";

	// Added to support Json object creation API
	public static String yyyyMmddFormat = DateUtils.DATE_FORMAT;// ISO_8601_FORMAT_DATE "yyyy-MM-dd";
	public static String JSON_OBJECT = "JSON";

	// Base64 encoded strings header's terminator in API responses
	public static String BASE64_ENCODING_INDICATOR = ";base64,";

}
