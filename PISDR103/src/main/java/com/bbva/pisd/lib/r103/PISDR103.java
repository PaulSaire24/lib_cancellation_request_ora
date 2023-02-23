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
		REQUEST_CHANNEL_ID,
		ENTITY_ID,
		BRANCH_ID,
		FIRST_VERFN_DIGIT,
		SECOND_VERFN_DIGIT,
		ACCOUNT_ID,
        POLICYID,
        PRODUCTID,
        INSURANCE_CONTRACT_START_DATE,
        INSURANCE_CONTRACT_END_DATE,
        POLICY_ANNULATION_DATE,
        INSURANCE_CANCEL_DATE,
        INSURED_AMOUNT,
        CURRENCY_ID,
        CONTRACT_INCEPTION_DATE,
        PERIOD_NEXT_PAYMENT_DATE,
        PAYMENT_FREQUENCY_ID,
        AUTOMATIC_DEBIT_INDICATOR_TYPE,
        RENEWAL_NUMBER,
        NEXT_RENEWAL_START_DATE,
        INSURANCE_COMPANY_DESC,
        INSRNC_CO_CONTACT_CHANNEL_DESC,
        INSURANCE_PRODUCT_TYPE,
        INSURANCE_PRODUCT_DESC,
        INSUR_MODALITY_DESC,
        PAYMENT_FREQUENCY_NAME,
        INSURANCE_CONSIDERATION_TYPE,
        INSURANCE_CONSIDERATION_ID,
        INSURANCE_CONSIDERATION_DESC,
        INSURANCE_CONSIDERATION_PER,
        INSURANCE_CONSIDERATION_AMOUNT,
        CONSIDERATION_CURRENCY_ID,
        INSRNC_CO_CONTACT_CHANNEL_TYPE,
        INSRC_CNSD_DATA_VALUE_TYPE,
        POLICY_PAYMENT_FREQUENCY_TYPE,
        RECEIPT_START_DATE,
        RECEIPT_END_DATE,
		RECEIPT_STATUS_TYPE;
	}

	enum ReceiptStatusType {
		COB,INC;
	}

	enum Errors {
		NO_DATA_FOUND("PISD00120000");
		private final String adviceCode;
		Errors(String adviceCode) {this.adviceCode = adviceCode;}
		public String getAdviceCode() { return adviceCode; }
	}

	int executeSaveInsuranceRequestCancellation(Map<String, Object> arguments);

	int executeSaveInsuranceRequestCancellationMov(Map<String, Object> arguments);

	Map<String, Object> executeGetRequestCancellationId();

	List<Map<String, Object>> executeGetCancellationRequests(Map<String, Object> arguments);

	List<Map<String, Object>> executeGetRoyalPolicyDetail(String contractNumber);
}
