package com.librarymanagement.ms.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SearchCriteriaTest {

	@Test
	public void successSearchCriteriaRequest() {
		SearchCriteria sb = new SearchCriteria("isbn", ":", "456789012");
		sb.setOperation(">");
		assertEquals("456789012", sb.getValue());
	}

}
