@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = new Book(null, "Effective Java", "Joshua Bloch");
        book = bookRepository.save(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/" + book.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(book.getId()))
                .andExpect(jsonPath("$.title").value("Effective Java"))
                .andExpect(jsonPath("$.author").value("Joshua Bloch"));
    }

    @Test
    public void testAddBook() throws Exception {
        String bookJson = "{\"title\": \"Clean Code\", \"author\": \"Robert C. Martin\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("Clean Code"))
                .andExpect(jsonPath("$.author").value("Robert C. Martin"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Book book = new Book(null, "The Pragmatic Programmer", "Andy Hunt");
        book = bookRepository.save(book);

        mockMvc.perform(MockMvcRequestBuilders.delete("/books/" + book.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertFalse(bookRepository.findById(book.getId()).isPresent());
    }
}
