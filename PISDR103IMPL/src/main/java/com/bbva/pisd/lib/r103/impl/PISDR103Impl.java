package com.bbva.pisd.lib.r103.impl;

import com.bbva.apx.exception.db.NoResultException;
import com.bbva.pisd.lib.r103.PISDR103;
import com.bbva.pisd.lib.r103.impl.utils.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

	@Override
	public List<Map<String, Object>> executeGetRoyalPolicyDetail(String contractNumber) {
		LOGGER.info("***** PISDR103Impl - executeGetRoyalPolicyDetail START  *****  contractNumber: {}", contractNumber);
		Map<String, Object> arguments = mapContractNumber(contractNumber);
		if (arguments == null) { return new ArrayList<>(); }

		List<Map<String, Object>> response = new ArrayList<>();
		try {
			response = this.jdbcUtils.queryForList(Properties.QUERY_SELECT_INSRC_CANCELLATION_DETAIL.getValue(), arguments);
		} catch(NoResultException ex) {
			LOGGER.info("PISDR103Impl - executeGetRoyalPolicyDetail - QUERY EMPTY RESULT [PISD.SELECT_INSURANCE_CANCELLATION_DETAIL]");
		}
		LOGGER.info("***** PISDR103Impl - executeGetCancellationDetail END ***** response : {}", response);
		return response;
	}

	private boolean parametersEvaluation(Map<String, Object> arguments, String... keys) {
		return Arrays.stream(keys).allMatch(key -> Objects.nonNull(arguments.get(key)));
	}

	private Map<String, Object> mapContractNumber (String contractNumber) {
		if (contractNumber == null || contractNumber.length() != 20) {
			LOGGER.info("PISDR100Impl - mapContractNumber - Número de contrato no válido ------ contractNumber: {}", contractNumber);
			return null;
		}
		Map<String, Object> arguments = new HashMap<>();
		arguments.put(PISDR103.Fields.ENTITY_ID.name(), contractNumber.substring(0, 4));
		arguments.put(PISDR103.Fields.BRANCH_ID.name(), contractNumber.substring(4, 8));
		arguments.put(PISDR103.Fields.FIRST_VERFN_DIGIT.name(), contractNumber.substring(8, 9));
		arguments.put(PISDR103.Fields.SECOND_VERFN_DIGIT.name(), contractNumber.substring(9, 10));
		arguments.put(PISDR103.Fields.ACCOUNT_ID.name(), contractNumber.substring(10, 20));
		return arguments;
	}

}
