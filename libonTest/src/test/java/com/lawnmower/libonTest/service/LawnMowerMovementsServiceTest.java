package com.lawnmower.libonTest.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.lawnmower.libonTest.dto.LawnMowerJsonObject;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class LawnMowerMovementsServiceTest {
	
	@Autowired
	private LawnMowerMovementsService lawnMowerMovementsService;

	@Test
	void testExecuteOrdersFromPayload() throws Exception{
		LawnMowerJsonObject lawnMowerJsonObjectTest = new LawnMowerJsonObject("5 5\r\n1 2 N\r\nGAGAGAGAA\r\n3 3 E\r\nAADAADADDA");
		LawnMowerJsonObject lawnMowerJsonObjectExpected = new LawnMowerJsonObject("1 3 N\r\n5 1 E");
		LawnMowerJsonObject lawnMowerJsonObjectReturnedByService = lawnMowerMovementsService.executeOrdersFromPayload(lawnMowerJsonObjectTest);
		assertEquals(lawnMowerJsonObjectExpected.getPayLoad(), lawnMowerJsonObjectReturnedByService.getPayLoad());
	}
	
	@Test
	void testExecuteOrdersFromPayloadShowsIKnowMockito() throws Exception{
		LawnMowerJsonObject lawnMowerJsonObjectTest = new LawnMowerJsonObject("5 5\r\n1 2 N\r\nGAGAGAGAA\r\n3 3 E\r\nAADAADADDA");
		LawnMowerJsonObject lawnMowerJsonObjectExpected = new LawnMowerJsonObject("1 3 N\r\n5 1 E");
		LawnMowerMovementsService lawnMowerMovementsServiceForMock = Mockito.mock(LawnMowerMovementsService.class);
		when(lawnMowerMovementsServiceForMock.executeOrdersFromPayload(lawnMowerJsonObjectTest)).thenReturn(lawnMowerJsonObjectExpected);
		LawnMowerJsonObject lawnMowerJsonObjectReturnedByService = lawnMowerMovementsServiceForMock.executeOrdersFromPayload(lawnMowerJsonObjectTest);
		assertEquals(lawnMowerJsonObjectExpected.getPayLoad(), lawnMowerJsonObjectReturnedByService.getPayLoad());
	}

}
