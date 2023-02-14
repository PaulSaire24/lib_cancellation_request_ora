package com.bbva.pisd.lib.r103;

import com.bbva.apx.exception.db.NoResultException;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import com.bbva.elara.utility.jdbc.JdbcUtils;
import com.bbva.pisd.lib.r103.impl.PISDR103Impl;
import com.bbva.pisd.lib.r103.impl.utils.Properties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/PISDR103-app.xml",
		"classpath:/META-INF/spring/PISDR103-app-test.xml",
		"classpath:/META-INF/spring/PISDR103-arc.xml",
		"classpath:/META-INF/spring/PISDR103-arc-test.xml" })
public class PISDR103Test {
	private static final Logger LOGGER = LoggerFactory.getLogger(PISDR103Test.class);
	private final PISDR103Impl pisdr103 = new PISDR103Impl();
	private JdbcUtils jdbcUtils;

	@Mock
	private Map<String, Object> arguments;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ThreadContext.set(new Context());

		jdbcUtils = mock(JdbcUtils.class);
		pisdr103.setJdbcUtils(jdbcUtils);

		when(arguments.get(PISDR103.Fields.REQUEST_SEQUENCE_ID.toString())).thenReturn("123456789");
		when(arguments.get(PISDR103.Fields.INSURANCE_CONTRACT_ENTITY_ID.toString())).thenReturn("11");
		when(arguments.get(PISDR103.Fields.INSURANCE_CONTRACT_BRANCH_ID.toString())).thenReturn("01");
		when(arguments.get(PISDR103.Fields.INSURANCE_PRODUCT_ID.toString())).thenReturn("830");
		when(arguments.get(PISDR103.Fields.CHANNEL_ID.toString())).thenReturn("PC");
		when(arguments.get(PISDR103.Fields.USER_AUDIT_ID.toString())).thenReturn("USER");
	}

	@Test
	public void executeSaveInsuranceCancellationRequestOK(){
		LOGGER.info("PISDR103Test - Executing executeSaveInsuranceCancellationRequestOK...");
		when(jdbcUtils.update(Properties.QUERY_INSERT_INSURANCE_REQUEST_CNCL.getValue(), arguments)).thenReturn(1);
		int validation = pisdr103.executeSaveInsuranceRequestCancellation(arguments);
		assertEquals(1, validation);
	}

	@Test
	public void executeGetRequestCancellationIdOK(){
		LOGGER.info("PISDR103Test - Executing executeGetRequestCancellationIdOK...");
		Map<String, Object> requestCancellationId = new HashMap<String, Object>() {{
			put("key", new Object());
		}};
		when(jdbcUtils.queryForMap(Properties.QUERY_SELECT_REQUEST_SEQUENCE_ID.getValue(), arguments)).thenReturn(requestCancellationId);
		Map<String, Object> validation = pisdr103.executeGetRequestCancellationId();
		assertNotNull(validation);
	}

	@Test
	public void executeGetCancellationRequestsOK(){
		LOGGER.info("PISDR103Test - Executing executeGetCancellationRequestsOK...");
		when(jdbcUtils.queryForList(Properties.QUERY_SELECT_INSURANCE_CNCL_REQUEST.getValue(), arguments)).thenReturn(new ArrayList<>());
		List<Map<String, Object>> validation = pisdr103.executeGetCancellationRequests(arguments);
		assertNotNull(validation);
	}

	@Test
	public void executeGetCancellationRequestsWithNoResultException(){
		LOGGER.info("PISDR103Test - Executing executeGetCancellationRequestsWithNoResultException...");
		when(jdbcUtils.queryForList(Properties.QUERY_SELECT_INSURANCE_CNCL_REQUEST.getValue(), arguments)).thenThrow(new NoResultException("NO RESULT"));
		List<Map<String, Object>> validation = pisdr103.executeGetCancellationRequests(arguments);
		assertEquals(0, validation.size());
	}

	@Test
	public void executeGetCancellationRequestsWithEmpty(){
		LOGGER.info("PISDR103Test - Executing executeGetCancellationRequestsWithNull...");
		List<Map<String, Object>> validation = pisdr103.executeGetCancellationRequests(Collections.emptyMap());
		assertEquals(0, validation.size());
	}

	@Test
	public void executeEnumTest(){
		LOGGER.info("PISDR103Test - Executing executeEnumTest...");
		assertNotNull(PISDR103.Errors.NO_DATA_FOUND);
	}

	@Test
	public void executeGetRoyalPolicyDetailTestNoResultException(){
		LOGGER.info("***** PISDR103Test - executeGetRoyalPolicyDetailTestNoResultException START *****");
		List<Map<String, Object>> valid = pisdr103.executeGetRoyalPolicyDetail(null);
		assertEquals(0, valid.size());

		this.pisdr103.getAdviceList().clear();
		when(jdbcUtils.queryForList(anyString(), anyMap())).thenThrow(new NoResultException(PISDR103.Errors.NO_DATA_FOUND.name()));
		List<Map<String, Object>> resp = pisdr103.executeGetRoyalPolicyDetail("00110241341111111111");
		assertEquals(0, resp.size());
	}

	@Test
	public void executeGetRoyalPolicyDetailTestNullEmpty(){
		LOGGER.info("***** PISDR103Test - executeGetRoyalPolicyDetailTestNullEmpty START *****");
		List<Map<String, Object>> resp = pisdr103.executeGetRoyalPolicyDetail(null);
		assertEquals(0, resp.size());

		resp = pisdr103.executeGetRoyalPolicyDetail("");
		assertEquals(0, resp.size());
	}

	@Test
	public void executeGetRoyalPolicyDetailTestOK(){
		LOGGER.info("***** PISDR103Test - executeGetRoyalPolicyDetailTestOK START *****");
		when(jdbcUtils.queryForMap(anyString(), anyMap())).thenReturn(new HashMap<>());
		List<Map<String, Object>> validation = pisdr103.executeGetRoyalPolicyDetail("11111111111111111111");
		assertNotNull(validation);

		validation = pisdr103.executeGetRoyalPolicyDetail("11111111111111111111");
		assertNotNull(validation);

		validation = pisdr103.executeGetRoyalPolicyDetail("11111111111111111111");
		assertNotNull(validation);
	}
}
