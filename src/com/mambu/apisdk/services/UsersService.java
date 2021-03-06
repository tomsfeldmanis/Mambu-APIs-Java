package com.mambu.apisdk.services;

import java.util.HashMap;
import java.util.List;

import com.google.inject.Inject;
import com.mambu.api.server.handler.customviews.model.CustomViewApiType;
import com.mambu.apisdk.MambuAPIService;
import com.mambu.apisdk.exception.MambuApiException;
import com.mambu.apisdk.util.APIData;
import com.mambu.apisdk.util.ApiDefinition;
import com.mambu.apisdk.util.ApiDefinition.ApiType;
import com.mambu.apisdk.util.ParamsMap;
import com.mambu.apisdk.util.ServiceExecutor;
import com.mambu.apisdk.util.ServiceHelper;
import com.mambu.core.shared.data.DataViewType;
import com.mambu.core.shared.model.CustomFieldValue;
import com.mambu.core.shared.model.CustomView;
import com.mambu.core.shared.model.User;

/**
 * Service class which handles API operations like getting and creating users. When getting users, for safety reasons
 * the API call will have the response stripped of some fields: - transactionLimits - password - apiAppId - apiAppKey -
 * preferences - permissions
 * 
 * @author ipenciuc
 * 
 */
public class UsersService {

	private static String OFFSET = APIData.OFFSET;
	private static String LIMIT = APIData.LIMIT;
	private static String BRANCH_ID = APIData.BRANCH_ID;

	// Service Executor
	private ServiceExecutor serviceExecutor;
	// API definitions
	private final static ApiDefinition getUsers = new ApiDefinition(ApiType.GET_LIST, User.class);
	private final static ApiDefinition getUser = new ApiDefinition(ApiType.GET_ENTITY_DETAILS, User.class);
	private final static ApiDefinition getCustomViews = new ApiDefinition(ApiType.GET_OWNED_ENTITIES, User.class,
			CustomView.class);
	// Update Custom Field value for a User
	private final static ApiDefinition updateUserCustomField = new ApiDefinition(ApiType.PATCH_OWNED_ENTITY,
			User.class, CustomFieldValue.class);
	// Delete Custom Field for a User
	private final static ApiDefinition deleteUserCustomField = new ApiDefinition(ApiType.DELETE_OWNED_ENTITY,
			User.class, CustomFieldValue.class);

	/***
	 * Create a new users service
	 * 
	 * @param mambuAPIService
	 *            the service responsible with the connection to the server
	 */
	@Inject
	public UsersService(MambuAPIService mambuAPIService) {
		this.serviceExecutor = new ServiceExecutor(mambuAPIService);
	}

	/**
	 * Get all the users with offset and limit
	 * 
	 * @param offset
	 *            the offset of the response. If not set a value of 0 is used by default
	 * @param limit
	 *            the maximum number of response entries. If not set a value of 50 is used by default
	 * 
	 * @return List of all Users
	 * 
	 * @throws MambuApiException
	 */
	public List<User> getUsers(String offset, String limit) throws MambuApiException {

		ParamsMap params = new ParamsMap();
		params.put(OFFSET, offset);
		params.put(LIMIT, limit);

		return serviceExecutor.execute(getUsers, params);
	}

	/**
	 * Get users (first 50 per default)
	 * 
	 * @return List of Users
	 * 
	 * @throws MambuApiException
	 */
	public List<User> getUsers() throws MambuApiException {
		return getUsers(null, null);
	}

	/**
	 * Get a paginated list of users filtered by branch
	 * 
	 * @param branchId
	 *            the id of the branch to filter with
	 * @param offset
	 *            the offset of the response. If not set a value of 0 is used by default
	 * @param limit
	 *            the maximum number of response entries. If not set a value of 50 is used by default
	 * 
	 * @return list of Users
	 * 
	 * @throws MambuApiException
	 */
	public List<User> getUsers(String branchId, String offset, String limit) throws MambuApiException {

		ParamsMap params = new ParamsMap();
		params.put(BRANCH_ID, branchId);
		params.put(OFFSET, offset);
		params.put(LIMIT, limit);

		return serviceExecutor.execute(getUsers, params);
	}

	/**
	 * Get User by its userID
	 * 
	 * @param userId
	 *            the id of the user to filter.
	 * 
	 * @return User - with full details
	 * 
	 * @throws MambuApiException
	 */

	public User getUserById(String userId) throws MambuApiException {
		return serviceExecutor.execute(getUser, userId);
	}

	/**
	 * Get User by it's userName.
	 * 
	 * NOTE: This is just a convenience method, it uses the getById() API. One can use getById() directly too.
	 * 
	 * @param userName
	 *            the username of the user to filter
	 * 
	 * @return User - with full details
	 * 
	 * @throws MambuApiException
	 */

	public User getUserByUsername(String userName) throws MambuApiException {
		return getUserById(userName);
	}

	/**
	 * Get Custom Views for the user by user's userName and apiViewType.
	 * 
	 * See more in {@link MBU-4607 @ https://mambucom.jira.com/browse/MBU-4607 } and in MBU-6306 {@link https
	 * ://mambucom.jira.com/browse/MBU-6306}
	 * 
	 * @param username
	 *            the username of the user. Mandatory field
	 * @param apiViewType
	 *            view filter type. If null, all custom views are returned
	 * 
	 * @return List of Custom Views for this user
	 * 
	 * @throws MambuApiException
	 */
	public List<CustomView> getCustomViews(String username, CustomViewApiType apiViewType) throws MambuApiException {
		// GET /api/users/<USERNAME>/views
		// Allow also for filtering for the CLIENTS/GROUPS/LOANS/DEPOSITS views (ex: GET
		// /api/users/<USERNAME>/views?for=CLIENTS)

		if (username == null) {
			throw new IllegalArgumentException("Username must not be NULL");
		}

		ParamsMap params = new ParamsMap();
		if (apiViewType != null) {
			params.put(APIData.FOR, apiViewType.name());
		}

		return serviceExecutor.execute(getCustomViews, username, params);
	}

	/**
	 * Convenience method to get all Custom Views for the user by user's userName.
	 * 
	 * @param userName
	 *            the username of the user
	 * 
	 * @return List of Custom Views for this user
	 * 
	 * @throws MambuApiException
	 */
	public List<CustomView> getCustomViews(String userName) throws MambuApiException {
		CustomViewApiType apiViewType = null;
		return getCustomViews(userName, apiViewType);
	}

	/**
	 * Map to convert custom view's DataViewType to the CustomViewApiType (required by Custom View API)
	 */
	public static HashMap<DataViewType, CustomViewApiType> supportedDataViewTypes;
	static {
		supportedDataViewTypes = new HashMap<DataViewType, CustomViewApiType>();

		supportedDataViewTypes.put(DataViewType.CLIENT, CustomViewApiType.CLIENTS);
		supportedDataViewTypes.put(DataViewType.GROUP, CustomViewApiType.GROUPS);

		supportedDataViewTypes.put(DataViewType.LOANS, CustomViewApiType.LOANS);
		supportedDataViewTypes.put(DataViewType.SAVINGS, CustomViewApiType.DEPOSITS);

		supportedDataViewTypes.put(DataViewType.LOAN_TRANSACTIONS_LOOKUP, CustomViewApiType.LOAN_TRANSACTIONS);
		supportedDataViewTypes.put(DataViewType.SAVINGS_TRANSACTIONS_LOOKUP, CustomViewApiType.DEPOSIT_TRANSACTIONS);

		supportedDataViewTypes.put(DataViewType.ACTIVITIES_LOOKUP, CustomViewApiType.SYSTEM_ACTIVITIES);

	}

	/***
	 * Update custom field value for a User. This method allows to set new value for a specific custom field
	 * 
	 * @param userName
	 *            the userName, user id or the encoded key of the Mambu User for which the custom field is updated.
	 * @param customFieldId
	 *            the encoded key or id of the custom field to be updated
	 * @param fieldValue
	 *            the new value of the custom field
	 * 
	 * @throws MambuApiException
	 */
	public boolean updateUserCustomField(String userName, String customFieldId, String fieldValue)
			throws MambuApiException {
		// Execute request for PATCH API to update custom field value for a User. See MBU-6661
		// e.g. PATCH "{ "value": "10" }" /host/api/users/userName/custominformation/customFieldId

		// Make ParamsMap with JSON request for Update API
		ParamsMap params = ServiceHelper.makeParamsForUpdateCustomField(customFieldId, fieldValue);
		return serviceExecutor.execute(updateUserCustomField, userName, customFieldId, params);

	}

	/***
	 * Delete custom field for a User
	 * 
	 * @param username
	 *            the userName or the encoded key of the Mambu User for which the custom field is updated.
	 * @param customFieldId
	 *            the encoded key or id of the custom field to be deleted
	 * 
	 * @throws MambuApiException
	 */
	public boolean deleteUserCustomField(String username, String customFieldId) throws MambuApiException {
		// Execute request for DELETE API to delete custom field for a User. See MBU-6661
		// e.g. DELETE /host/api/users/username/custominformation/customFieldId

		return serviceExecutor.execute(deleteUserCustomField, username, customFieldId, null);

	}
}
