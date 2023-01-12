package com.bbva.pisd.lib.r103;

import java.util.Map;

/**
 * The  interface PISDR103 class...
 */
public interface PISDR103 {

	int executeSaveInsuranceRequestCancellation(Map<String, Object> arguments);

	Map<String, Object> executeGetRequestCancellationId();
}
