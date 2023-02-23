package com.bbva.pisd.lib.r103.impl;

import com.bbva.apx.exception.db.NoResultException;
import com.bbva.pisd.lib.r103.PISDR103;
import com.bbva.pisd.lib.r103.impl.utils.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The  interface PISDR103Impl class...
 */
public class PISDR103Impl extends PISDR103Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(PISDR103Impl.class);

	@Override
	public int executeSaveInsuranceRequestCancellation(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR103Impl - executeSaveInsuranceRequestCancellation START *****");
		int affectedRow = 0;
		if (parametersEvaluation(arguments, PISDR103.Fields.REQUEST_SEQUENCE_ID.toString(), PISDR103.Fields.CHANNEL_ID.toString(), PISDR103.Fields.INSURANCE_PRODUCT_ID.toString(), PISDR103.Fields.USER_AUDIT_ID.toString())) {
			LOGGER.info("***** PISDR103Impl - executeSaveInsuranceRequestCancellation - PARAMETERS OK ... EXECUTING *****");
			affectedRow = this.jdbcUtils.update(Properties.QUERY_INSERT_INSURANCE_REQUEST_CNCL.getValue(), arguments);
		} else {
			LOGGER.debug("executeSaveInsuranceRequestCancellation - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSURANCE_REQUEST_CNCL]");
		}
		LOGGER.info("***** PISDR103Impl - executeSaveInsuranceRequestCancellation END *****");
		return affectedRow;
	}

	@Override
	public int executeSaveInsuranceRequestCancellationMov(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR103Impl - executeSaveInsuranceRequestCancellationMov START *****");
		int affectedRow = 0;
		if (parametersEvaluation(arguments, PISDR103.Fields.REQUEST_SEQUENCE_ID.toString(), PISDR103.Fields.CHANNEL_ID.toString(), PISDR103.Fields.INSURANCE_PRODUCT_ID.toString(), PISDR103.Fields.USER_AUDIT_ID.toString())) {
			LOGGER.info("***** PISDR103Impl - executeSaveInsuranceRequestCancellationMov - PARAMETERS OK ... EXECUTING *****");
			affectedRow = this.jdbcUtils.update(Properties.QUERY_INSERT_INSURANCE_REQ_CNCL_MOV.getValue(), arguments);
		} else {
			LOGGER.debug("executeSaveInsuranceRequestCancellationMov - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSURANCE_REQ_CNCL_MOV]");
		}
		LOGGER.info("***** PISDR103Impl - executeSaveInsuranceRequestCancellationMov END *****");
		return affectedRow;
	}

	@Override
	public Map<String, Object> executeGetRequestCancellationId() {
		LOGGER.info("***** PISDR103Impl - executeGetRequestCancellationId START *****");
		Map<String, Object> response = this.jdbcUtils
				.queryForMap(Properties.QUERY_SELECT_REQUEST_SEQUENCE_ID.getValue());
		response.forEach((key, value) -> LOGGER
				.info("[PISD.SELECT_REQUEST_SEQUENCE_ID] Result -> Key {} with value: {}", key, value));
		LOGGER.info("***** PISDR103Impl - executeGetRequestCancellationId END *****");
		return response;
	}

	@Override
	public List<Map<String, Object>> executeGetCancellationRequests(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR103Impl - executeGetCancellationRequests START *****");

		List<Map<String, Object>> response = new ArrayList<>();
		if (!arguments.isEmpty()) {
			LOGGER.info("***** PISDR103Impl - executeGetCancellationRequests - PARAMETERS OK ... EXECUTING *****");
			try {
				arguments.forEach((key, value) -> LOGGER.info("[PISD.SELECT_INSURANCE_CANCELLATION_REQUEST] Result -> Key {} with value: {}", key, value));
				LOGGER.info("Properties.QUERY_SELECT_INSURANCE_CNCL_REQUEST.getValue() ==> {}", Properties.QUERY_SELECT_INSURANCE_CNCL_REQUEST.getValue());
				response = this.jdbcUtils.queryForList(Properties.QUERY_SELECT_INSURANCE_CNCL_REQUEST.getValue(), arguments);
				response.forEach(map -> map.forEach((key, value) -> LOGGER.info("[PISD.SELECT_INSURANCE_CANCELLATION_REQUEST] Result -> Key {} with value: {}", key, value)));
			} catch (NoResultException ex) {
				LOGGER.debug("executeGetCancellationRequests - MISSING MANDATORY PARAMETERS [PISD.SELECT_INSURANCE_CANCELLATION_REQUEST]");
				this.addAdvice(Errors.NO_DATA_FOUND.getAdviceCode());
			}
		} else {
			LOGGER.info("executeGetCancellationRequests - MISSING MANDATORY PARAMETERS [PISD.SELECT_INSURANCE_CANCELLATION_REQUEST]");
		}
		LOGGER.info("***** PISDR103Impl - executeGetCancellationRequests END *****");
		return response;
	}

	private boolean parametersEvaluation(Map<String, Object> arguments, String... keys) {
		return Arrays.stream(keys).allMatch(key -> Objects.nonNull(arguments.get(key)));
	}

}
