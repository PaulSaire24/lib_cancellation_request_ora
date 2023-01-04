package com.bbva.pisd.lib.r103.impl;

import com.bbva.apx.exception.db.NoResultException;
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
	public int executeSaveInsuranceCancellationRequest(Map<String, Object> arguments) {
		LOGGER.info("***** PISDR103Impl - executeSaveInsuranceCancellationRequest START *****");
		int affectedRow = 0;

		if(parametersEvaluation(arguments, Fields.INSURANCE_CONTRACT_ENTITY_ID.toString(),
				Fields.INSURANCE_CONTRACT_BRANCH_ID.toString())) {
			LOGGER.info("***** PISDR103Impl - executeSaveInsuranceCancellationRequest - PARAMETERS OK ... EXECUTING *****");
			affectedRow = this.jdbcUtils.update(Properties.QUERY_INSERT_INSURANCE_CNCL_REQUEST.getValue(), arguments);
		} else {
			LOGGER.debug("executeSaveInsuranceCancellationRequest - MISSING MANDATORY PARAMETERS [PISD.INSERT_INSURANCE_CANCELLATION_REQUEST]");
		}
		LOGGER.info("***** PISDR103Impl - executeSaveInsuranceCancellationRequest END *****");
		return affectedRow;
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
				this.addAdvice(Properties.ERROR_QUERY_EMPTY_RESULT.getValue());
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
