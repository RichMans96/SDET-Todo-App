Coverage 88.7%
# SDET-Todo-App

This is the second project that was assigned to us whilst at the QA academy. The goal of this project was to create a todo application, this included developing the API and the frontend that interacts with the API.
I also had to create unit, integration and acceptance tests for the app, aiming for 80% test coverage.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

In order to get going you will need:
Eclipise or another IDE capable of openeing Java projects.
Spring Boot Suite
Maven.
Java 14 or any stable version of Java.
And finally Git needs to be installed.


### Installing
Fork a copy and then run git clone `url to the forked repo`.

## Running the app
right click the folder where you have the jar file located and show in terminal the type java -jar {name of jar}

## Running the tests

To run the tests, right click on the project > coverage as > junit test. 

### Tests 

Under src/test/main you will be able to find all of the tests for the project, apart from selenium as I had problems adding selenium to my project. They will be available in a different project.
The example below is an integration test of the controller. This test allows us to mock http requests to out api, allowing us to find out if the application breaks/doesn't work as expected when receiving data.

 ``
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql",
		"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class TodoControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private TodoMapper todoMapper;

	@Autowired
	private ObjectMapper objectMapper;

	private TodoList todoList = new TodoList(1, "Morning");
	private Todo todo = new Todo(1, "Get Milk", true, todoList);
	private TodoDTO todoDTO = new TodoDTO(1, "Get Milk", true);

	private List<Todo> todos = List.of(todo);
	private List<TodoDTO> todoDTOs = List.of(todoDTO);

	@Test
	public void createTodoTest() throws Exception {
		Todo testTodo = new Todo("Get dressed", true, todoList);
		TodoDTO testTodoDTO = new TodoDTO(2, "Get dressed", true);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/todo");
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(testTodo));

		mockRequest.accept(MediaType.APPLICATION_JSON);

		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(testTodoDTO));
		ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location", "2");

		mvc.perform(mockRequest).andExpect(headerMatcher).andExpect(contentMatcher).andExpect(statusMatcher);
	}
``

Below is an example of a basic unit test. Here I am testing that the equals method works as expected.
	``public class TodoTest {

	TodoList todoList = new TodoList(1, "Shopping");

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Todo.class).withPrefabValues(Todo.class,
				new Todo(1, "Buy milk", true, todoList), new Todo(2, "Buy beans", false, todoList)).verify();
	}``


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors
* **Richard Mansworth** - *inital work and finished application* [RichMans96](https://github.com/RichMans96)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 
