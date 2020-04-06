package UnitTestPackage;

import MainPackage.PrinterClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrinterClassTest {
    @Test
    public void printerTest(){
        assertEquals("sdsd", PrinterClass.printer());
    }
}
