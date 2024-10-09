//package domain;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.time.Month;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class InvoiceTest {
//
//    Invoice invoice1;
//
//    @BeforeEach
//    void setUp() {
//        this.invoice1 = new Invoice(1,
//                LocalDateTime.of(2019, Month.SEPTEMBER, 1, 0, 0),
//                LocalDateTime.of(2019, Month.SEPTEMBER, 11, 0, 0), null);
//    }
//
//    @Test
//    void copyConstructor() {
//        Invoice invoice2 = new Invoice(this.invoice1);
//        assertEquals(this.invoice1.getInvoiceId(), invoice2.getInvoiceId());
//        assertEquals(this.invoice1.getStatus(), invoice2.getStatus());
////        assertEquals(this.invoice1.getInvoiceDate().julianDay(), invoice2.getInvoiceDate().julianDay());
////        assertEquals(this.invoice1.getDueDate().julianDay(), invoice2.getDueDate().julianDay());
////        assertNotEquals(this.invoice1.getInvoiceDate(), invoice2.getInvoiceDate());
////        assertNotEquals(this.invoice1.getDueDate(), invoice2.getDueDate());
//        assertTrue(this.invoice1.getInvoiceDate().equals(invoice2.getInvoiceDate()));
//        assertTrue(this.invoice1.getDueDate().equals(invoice2.getDueDate()));
//    }
//
//    @Test
//    void copy() {
//        Invoice invoice2 = this.invoice1.copy();
//        assertEquals(this.invoice1.getInvoiceId(), invoice2.getInvoiceId());
//        assertEquals(this.invoice1.getStatus(), invoice2.getStatus());
////        assertEquals(this.invoice1.getInvoiceDate().julianDay(), invoice2.getInvoiceDate().julianDay());
////        assertNotEquals(this.invoice1.getInvoiceDate(), invoice2.getInvoiceDate());
////        assertNotEquals(this.invoice1.getDueDate(), invoice2.getDueDate());
//        assertTrue(this.invoice1.getInvoiceDate().equals(invoice2.getInvoiceDate()));
//        assertTrue(this.invoice1.getDueDate().equals(invoice2.getDueDate()));
//    }
//
//    @Test
//    void addLineItem() {
//        LineItem lineItem1 = new LineItem(1.0, "description1");
//
//        this.invoice1.addLineItem(lineItem1);
//        assertNotEquals(this.invoice1.getLineItems().get(this.invoice1.getLineItems().size() - 1),  null);
//    }
//
//    @Test
//    void removeLineItem() {
//        LineItem lineItem1 = new LineItem(1.0, "description1");
//
//        // test Invoice.removeLineItem(int index)
//        this.invoice1.addLineItem(lineItem1);
//        LineItem removedLineItem = this.invoice1.removeLineItem(0);
//        assertEquals(lineItem1, removedLineItem);
//        assertFalse(lineItem1 == removedLineItem);
//        // test removing from empty ArrayList
//        removedLineItem = this.invoice1.removeLineItem(0);
//        assertEquals(null, removedLineItem);
//
//        // test Invoice.removeLineItem(LineItem lineItem)
//        this.invoice1.addLineItem(lineItem1);
//        removedLineItem = this.invoice1.removeLineItem(lineItem1);
//        assertEquals(lineItem1, removedLineItem);
//        assertFalse(lineItem1 == removedLineItem);
//        // test removing from empty ArrayList
//        removedLineItem = this.invoice1.removeLineItem(lineItem1);
//        assertEquals(null, removedLineItem);
//    }
//
//    @Test
//    void total() {
//        assertNotEquals(
//                this.invoice1.total(), null
//        );
//    }
//}