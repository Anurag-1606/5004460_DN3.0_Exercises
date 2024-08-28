import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class BookMetrics {

    private final MeterRegistry meterRegistry;

    public BookMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void incrementBookView(String bookId) {
        meterRegistry.counter("book.views", "bookId", bookId).increment();
    }
}
