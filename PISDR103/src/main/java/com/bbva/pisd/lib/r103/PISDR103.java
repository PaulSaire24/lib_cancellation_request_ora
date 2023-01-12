package com.bbva.pisd.lib.r103;

import java.util.List;
import java.util.Map;

/**
 * The  interface PISDR103 class...
 */
public interface PISDR103 {

	enum Fields {
		REQUEST_SEQUENCE_ID,
		INSURANCE_CONTRACT_ENTITY_ID,
		INSURANCE_CONTRACT_BRANCH_ID,
		INSRC_CONTRACT_INT_ACCOUNT_ID,
		CONTRACT_FIRST_VERFN_DIGIT_ID,
		CONTRACT_SECOND_VERFN_DIGIT_ID,
		ADDITIONAL_DATA_DESC,
		CONTACT_EMAIL_DESC,
		CUSTOMER_PHONE_DESC,
		SEQ_MOV_NUMBER,
		INSURANCE_PRODUCT_ID,
		INSURANCE_MODALITY_TYPE,
		CONTRACT_STATUS_ID,
		CONTRACT_STATUS_DATE,
		CHANNEL_ID,
		CHANNEL_NAME,
		PREMIUM_AMOUNT,
		PREMIUM_CURRENCY_ID,
		CUSTOMER_ID,
		POLICY_QUOTA_INTERNAL_ID,
		INSURANCE_COMPANY_ID,
		POLICY_ID,
		CREATION_ID,
		CREATION_USER_ID,
		CREATION_DATE,
		USER_AUDIT_ID,
		AUDIT_DATE,
		REQUEST_CHANNEL_ID;
	}

	enum Errors {
		NO_DATA_FOUND("PISD00120000");
		private final String adviceCode;
		Errors(String adviceCode) {this.adviceCode = adviceCode;}
		public String getAdviceCode() { return adviceCode; }
	}

	int executeSaveInsuranceCancellationRequest(Map<String, Object> arguments);

	List<Map<String, Object>> executeGetCancellationRequests(Map<String, Object> arguments);

	Map<String, Object> executeGetRequestCancellationId();
}
