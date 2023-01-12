package com.bbva.pisd.lib.r103.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * The  interface PISDR103Impl class...
 */
public class PISDR103Impl extends PISDR103Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(PISDR103Impl.class);

	public static final String FIELD_REQUEST_SEQUENCE_ID = "REQUEST_SEQUENCE_ID";
	public static final String FIELD_CHANNEL_ID = "CHANNEL_ID";
	public static final String FIELD_INSURANCE_PRODUCT_ID = "INSURANCE_PRODUCT_ID";
	public static final String FIELD_USER_AUDIT_ID = "USER_AUDIT_ID";

	private static final String QUERY_INSERT_INSURANCE_REQUEST_CNCL = "PISD.INSERT_INSURANCE_REQUEST_CNCL";
	private static final String QUERY_SELECT_REQUEST_SEQUENCE_ID = "PISD.SELECT_REQUEST_SEQUENCE_ID";

	@Override
	public int executeSaveInsuranceRequestCancellation(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR103Impl - executeSaveInsuranceRequestCancellation START *****");
		int affectedRow = 0;
		if (parametersEvaluation(arguments, FIELD_REQUEST_SEQUENCE_ID, FIELD_CHANNEL_ID, FIELD_INSURANCE_PRODUCT_ID, FIELD_USER_AUDIT_ID)) {
			LOGGER.info("***** PISDR103Impl - executeSaveInsuranceRequestCancellation - PARAMETERS OK ... EXECUTING *****");
			affectedRow = this.jdbcUtils.update(QUERY_INSERT_INSURANCE_REQUEST_CNCL, arguments);
		} else {
			LOGGER.debug("executeSaveInsuranceRequestCancellation - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSURANCE_REQUEST_CNCL]");
		}
		LOGGER.info("***** PISDR103Impl - executeSaveInsuranceRequestCancellation END *****");
		return affectedRow;
	}

	@Override
	public Map<String, Object> executeGetRequestCancellationId() {
		LOGGER.info("***** PISDR103Impl - executeGetRequestCancellationId START *****");
		Map<String, Object> response = this.jdbcUtils
				.queryForMap(QUERY_SELECT_REQUEST_SEQUENCE_ID);
		response.forEach((key, value) -> LOGGER
				.info("[PISD.SELECT_REQUEST_SEQUENCE_ID] Result -> Key {} with value: {}", key, value));
		LOGGER.info("***** PISDR103Impl - executeGetRequestCancellationId END *****");
		return response;
	}

	private boolean parametersEvaluation(Map<String, Object> arguments, String... keys) {
		return Arrays.stream(keys).allMatch(key -> Objects.nonNull(arguments.get(key)));
	}
}
