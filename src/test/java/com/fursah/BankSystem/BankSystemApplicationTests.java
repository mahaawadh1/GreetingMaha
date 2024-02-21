package com.fursah.BankSystem;

import com.fursah.BankSystem.entity.GuestSuggestionEntity;
import com.fursah.BankSystem.entity.UserEntity;
import com.fursah.BankSystem.reposatriy.GuestSuggestionRepository;
import com.fursah.BankSystem.reposatriy.UserRepository;
import com.fursah.BankSystem.service.suggestion.GuestSuggestionService;
import com.fursah.BankSystem.service.user.UserService;
import com.fursah.BankSystem.util.enums.SuggestionStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SecureBankSystemApplicationTests {

		@Test
	void contextLoads() {
	}
	@MockBean
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@Mock
	private GuestSuggestionRepository suggestionRepository;

	@Autowired
	private GuestSuggestionService suggestionService;

	@Test
	public void whenUsersPasswordIsLargerThan8Digits_thenSuccess(){
		//Arrange phase
		UserEntity userEntity = new UserEntity();
		userEntity.setName("Awdhah");
		userEntity.setPassword("12345678");

		UserEntity userEntity2 = new UserEntity();
		userEntity2.setName("Amal");
		userEntity2.setPassword("1248");


		List<UserEntity> mockUsersEntities= Arrays.asList(
				userEntity,userEntity2);
		when(userRepository.findAll()).thenReturn(mockUsersEntities);

		//Act phase
		List<String> userWithStrongPassword= userService.getALlUsersWithStrongPassword();

		//Assert phase
		List<String> expectedUsersWithStrongPassword= Arrays.asList("Awdhah","Amal");
		assertEquals(expectedUsersWithStrongPassword,userWithStrongPassword);

	}

	public void SuggestionService() {
		MockitoAnnotations.openMocks(this);
		suggestionService = new GuestSuggestionService(suggestionRepository);
	}

	@Test
	public void whenGetCreateStatusSuggestions_thenSuccess() {
		//Arrange
		GuestSuggestionEntity suggestionEntity = new GuestSuggestionEntity();
		suggestionEntity.setSuggestionText("1");
		suggestionEntity.setSuggestionsStatus(SuggestionStatus.CREATE);

		GuestSuggestionEntity suggestionEntity1 = new GuestSuggestionEntity();
		suggestionEntity1.setSuggestionText("2");
		suggestionEntity1.setSuggestionsStatus(SuggestionStatus.CREATE);

		GuestSuggestionEntity suggestionEntity2 = new GuestSuggestionEntity();
		suggestionEntity2.setSuggestionText("3");
		suggestionEntity2.setSuggestionsStatus(SuggestionStatus.REMOVE);

		List<GuestSuggestionEntity> mockGustSuggestion= Arrays.asList(
				suggestionEntity1,suggestionEntity2,suggestionEntity);
		when(suggestionRepository.findAll()).thenReturn(mockGustSuggestion);

		//Act
		List<GuestSuggestionEntity> suggestionsWithCreatedStatus= suggestionService.getCreateStatusSuggestions();

		//Assert
		List<GuestSuggestionEntity> expectedSuggestionsWithCreatedStatus= Arrays.asList(suggestionEntity1,suggestionEntity);
		assertEquals(expectedSuggestionsWithCreatedStatus,suggestionsWithCreatedStatus);
	}
	@Test
	public void whenGetRemoveStatusSuggestions_thenSuccess() {

		GuestSuggestionEntity suggestionEntity = new GuestSuggestionEntity();
		suggestionEntity.setSuggestionText("any thing1");
		suggestionEntity.setSuggestionsStatus(SuggestionStatus.REMOVE);

		GuestSuggestionEntity suggestionEntity1 = new GuestSuggestionEntity();
		suggestionEntity1.setSuggestionText("any thing2");
		suggestionEntity1.setSuggestionsStatus(SuggestionStatus.REMOVE);

		GuestSuggestionEntity suggestionEntity2 = new GuestSuggestionEntity();
		suggestionEntity2.setSuggestionText("any thing3");
		suggestionEntity2.setSuggestionsStatus(SuggestionStatus.CREATE);

		List<GuestSuggestionEntity> mockGustSuggestion= Arrays.asList(
				suggestionEntity1,suggestionEntity2,suggestionEntity);
		when(suggestionRepository.findAll()).thenReturn(mockGustSuggestion);

		//Act
		List<GuestSuggestionEntity> suggestionsWithRemoveStatus= suggestionService.getRemoveStatusSuggestions();

		//Assert
		List<GuestSuggestionEntity> expectedSuggestionsWithCreatedStatus= Arrays.asList(suggestionEntity,suggestionEntity2);
		assertEquals(expectedSuggestionsWithCreatedStatus,suggestionsWithRemoveStatus);
		assertEquals(expectedSuggestionsWithCreatedStatus,suggestionsWithRemoveStatus);
	}
}