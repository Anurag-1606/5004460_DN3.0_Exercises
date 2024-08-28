@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void testGetBookById() throws Exception {
        Book book = new Book(1L, "Effective Java", "Joshua Bloch");

        Mockito.when(bookService.getBookById(1L)).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Effective Java"))
                .andExpect(jsonPath("$.author").value("Joshua Bloch"));
    }

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book(1L, "Clean Code", "Robert C. Martin");

        Mockito.when(bookService.saveBook(Mockito.any(Book.class))).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Clean Code\", \"author\": \"Robert C. Martin\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Clean Code"))
                .andExpect(jsonPath("$.author").value("Robert C. Martin"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Mockito.doNothing().when(bookService).deleteBook(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
