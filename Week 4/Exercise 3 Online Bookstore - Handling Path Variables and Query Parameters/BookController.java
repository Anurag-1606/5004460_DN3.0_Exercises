@GetMapping("/{id}")
public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    Book book = bookService.getBookById(id);
    if (book != null) {
        return ResponseEntity.ok(book);
    } else {
        return ResponseEntity.notFound().build();
    }
}
@GetMapping("/search")
public ResponseEntity<List<Book>> searchBooks(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String author) {
    
    List<Book> books = bookService.searchBooks(title, author);
    return ResponseEntity.ok(books);
}
