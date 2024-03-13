/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import java.awt.Image;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luis Fernando
 */
public class metodosTest {
    
    public metodosTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cambiarImgBoton method, of class metodos.
     */
    @Test
    public void testCambiarImgBoton_JButton_String() {
        metodos miClase = new metodos();

        // Crear un botón y establecer una imagen inicial (si es necesario)
        JButton boton = new JButton();
        boton.setBounds(0, 0, 500, 100);
        String imagenInicial = "Fondo10.jpg";
        miClase.cambiarImgBoton(boton, imagenInicial);

        // Obtener la imagen actual del botón
        ImageIcon iconoInicial = (ImageIcon) boton.getIcon();
        Image imagenInicialObtenida = iconoInicial.getImage();

        // Llamar al método para cambiar la imagen del botón
        String nuevaImagen = "Fondo10.jpg";
        miClase.cambiarImgBoton(boton, nuevaImagen);

        // Obtener la imagen después de cambiarla
        ImageIcon nuevoIcono = (ImageIcon) boton.getIcon();
        Image nuevaImagenObtenida = nuevoIcono.getImage();

        // Verificar que la imagen haya cambiado
        assertNotEquals("Las imágenes deberían ser diferentes", imagenInicialObtenida, nuevaImagenObtenida);
    }

    private static class MockJButton extends JButton {
        boolean setBorderCalled = false;
        boolean setContentAreaFilledCalled = false;

        @Override
        public void setBorder(javax.swing.border.Border border) {
            setBorderCalled = true;
        }

        @Override
        public void setContentAreaFilled(boolean b) {
            setContentAreaFilledCalled = true;
        }
    }

    @Test
    public void testBotonInvisible() {
        // Crear una instancia de JButton simulado
        MockJButton mockBoton = new MockJButton();
        // Crear una instancia de la clase que contiene el método a probar
        metodos metodo = new metodos();
        // Llamar al método que queremos probar
        metodo.botonInvisible(mockBoton);
        // Verificar que setBorder se llamó
        assertTrue(mockBoton.setBorderCalled);
        // Verificar que setContentAreaFilled se llamó
        assertTrue(mockBoton.setContentAreaFilledCalled);
    }
    
    @Test
    public void testComprobarClave() {
        metodos metodo = new metodos();

        // Prueba con contraseña que cumple con los requisitos
        assertTrue(metodo.comprobarClave("Abc123"));

        // Prueba con contraseña que falta la mayúscula
        assertFalse(metodo.comprobarClave("abc123"));

        // Prueba con contraseña que falta la minúscula
        assertFalse(metodo.comprobarClave("ABC123"));

        // Prueba con contraseña que falta el número
        assertFalse(metodo.comprobarClave("Abcdef"));

        // Prueba con contraseña vacía
        assertFalse(metodo.comprobarClave(""));

        // Prueba con contraseña que no contiene letras ni números
        assertFalse(metodo.comprobarClave("!@#$%^&*()"));
    }
    
    @Test
    public void testLimpiarTexto() {
        metodos metodo = new metodos();
        // Crear un array de JTextField simulados
        JTextField[] camposTexto = new JTextField[3];
        for (int i = 0; i < camposTexto.length; i++) {
            camposTexto[i] = new JTextField("Texto de prueba " + i);
        }
        // Llamar al método que queremos probar
        metodo.limpiarTexto(camposTexto);

        // Verificar que todos los campos de texto están vacíos después de llamar al método
        for (JTextField campoTexto : camposTexto) {
            assertEquals("", campoTexto.getText());
        }
    }
    
    @Test
    public void testIsDoubleConCadenaValida() {
        metodos metodo = new metodos();

        // Prueba con una cadena que representa un número double válido
        assertTrue(metodo.isDouble("123.45"));
    }

    @Test
    public void testIsDoubleConCadenaNoValida() {
        metodos metodo = new metodos();

        // Prueba con una cadena no válida
        assertFalse(metodo.isDouble("abc"));
    }

    @Test
    public void testIsDoubleConCadenaVacia() {
        metodos metodo = new metodos();

        // Prueba con una cadena vacía
        assertFalse(metodo.isDouble(""));
    }

    @Test
    public void testIsDoubleConCadenaNula() {
        metodos metodo = new metodos();

        // Prueba con una cadena nula
        assertFalse(metodo.isDouble(null));
    }
    
    @Test
    public void testIsNumericConCadenaNumerica() {
        metodos metodo = new metodos();

        // Prueba con una cadena que representa un número entero válido
        assertTrue(metodo.isNumeric("123"));
    }

    @Test
    public void testIsNumericConCadenaNoNumerica() {
        metodos metodo = new metodos();

        // Prueba con una cadena no válida
        assertFalse(metodo.isNumeric("abc"));
    }

    @Test
    public void testIsNumericConCadenaVacia() {
        metodos metodo = new metodos();

        // Prueba con una cadena vacía
        assertFalse(metodo.isNumeric(""));
    }

    @Test
    public void testIsNumericConCadenaNula() {
        metodos metodo = new metodos();

        // Prueba con una cadena nula
        assertFalse(metodo.isNumeric(null));
    }
    
    @Test
    public void testConvertirADoubleConCadenaValida() {
        metodos metodo = new metodos();

        // Prueba con una cadena que representa un número double válido
        String resultado = metodo.convertirADouble("123.45");

        // Verificar que el resultado es igual a la cadena convertida a double
        assertEquals("123.45", resultado);
    }

    @Test
    public void testConvertirADoubleConCadenaNoValida() {
        metodos metodo = new metodos();

        // Prueba con una cadena no válida
        String resultado = metodo.convertirADouble("abc");

        // Verificar que el resultado es una cadena vacía
        assertEquals("", resultado);
    }

    @Test
    public void testConvertirADoubleConCadenaVacia() {
        metodos metodo = new metodos();

        // Prueba con una cadena vacía
        String resultado = metodo.convertirADouble("");

        // Verificar que el resultado es una cadena vacía
        assertEquals("", resultado);
    }

    @Test
    public void testConvertirADoubleConCadenaNula() {
        metodos metodo = new metodos();

        // Prueba con una cadena nula
        String resultado = metodo.convertirADouble(null);

        // Verificar que el resultado es una cadena vacía
        assertEquals("", resultado);
    }
    
    @Test
    public void testDateAStringConFechaValida() {
        metodos metodo = new metodos();

        // Crear una fecha simulada (por ejemplo, 1 de enero de 2022)
        Date fecha = new Date(122, 0, 1);

        // Llamar al método que queremos probar
        String resultado = metodo.DateAString(fecha);

        // Verificar que el resultado es igual a la cadena esperada
        assertEquals("1/1/2022", resultado);
    }

    @Test
    public void testDateAStringConFechaNula() {
        metodos metodo = new metodos();

        // Llamar al método con una fecha nula
        String resultado = metodo.DateAString(null);

        // Verificar que el resultado es una cadena vacía
        assertEquals("", resultado);
    }
    
    @Test
    public void testHoraFormatoCorrecto() {
        metodos metodo = new metodos();

        // Llamar al método que queremos probar
        String resultado = metodo.hora();

        // Verificar que el resultado tiene el formato correcto (hh:mm:ss am/pm)
        assertTrue(resultado.matches("\\d{2}:\\d{2}:\\d{2} (AM|PM)"));
    }
    
    @Test
    public void testRedondearConDecimalPositivo() {
        metodos metodo = new metodos();

        // Llamar al método que queremos probar con un número decimal positivo
        double resultado = metodo.redondear(12.34567, 2);

        // Verificar que el resultado es igual al número redondeado
        assertEquals(12.35, resultado, 0.001); // Utilizamos delta para manejar pequeñas variaciones en números de punto flotante
    }

    @Test
    public void testRedondearConDecimalNegativo() {
        metodos metodo = new metodos();

        // Llamar al método que queremos probar con un número decimal negativo
        double resultado = metodo.redondear(-8.7654321, 3);

        // Verificar que el resultado es igual al número redondeado
        assertEquals(-8.765, resultado, 0.001); // Utilizamos delta para manejar pequeñas variaciones en números de punto flotante
    }
    
        @Test
    public void testValidadorDeCedulaCedulaCorrecta() {
        metodos metodo = new metodos();

        // Llamar al método que queremos probar con una cédula correcta
        boolean resultado = metodo.validadorDeCedula("0706022480");

        // Verificar que el resultado es true, ya que la cédula es válida
        assertTrue(resultado);
    }

    @Test
    public void testValidadorDeCedulaCedulaIncorrectaLongitud() {
        metodos metodo = new metodos();

        // Llamar al método que queremos probar con una cédula de longitud incorrecta
        boolean resultado = metodo.validadorDeCedula("12345");

        // Verificar que el resultado es false, ya que la longitud de la cédula es incorrecta
        assertFalse(resultado);
    }

    @Test
    public void testValidadorDeCedulaCedulaIncorrectaDigitoVerificador() {
        metodos metodo = new metodos();

        // Llamar al método que queremos probar con una cédula que tiene un dígito verificador incorrecto
        boolean resultado = metodo.validadorDeCedula("1725037084");

        // Verificar que el resultado es false, ya que el dígito verificador es incorrecto
        assertFalse(resultado);
    }

    @Test
    public void testValidadorDeCedulaCedulaIncorrectaFormato() {
        metodos metodo = new metodos();

        // Llamar al método que queremos probar con una cédula que no es un número
        boolean resultado = metodo.validadorDeCedula("ABC123");

        // Verificar que el resultado es false, ya que la cédula no es un número válido
        assertFalse(resultado);
    }
    
    @Test
    public void testObtenerDatosSumadosJTable() {
        metodos metodo = new metodos();

        // Crear un JTable simulado con datos
        JTable tablaSimulada = crearTablaSimulada();

        // Llamar al método que queremos probar con el JTable simulado
        String resultado = metodo.obtenerDatosSumadosJTable(tablaSimulada, 1);

        // Verificar que el resultado es igual a la suma de los datos en la columna 1
        assertEquals("7.0", resultado);
    }

    @Test
    public void testObtenerDatosSumadosJTableColumnaInexistente() {
        metodos metodo = new metodos();

        // Crear un JTable simulado con datos
        JTable tablaSimulada = crearTablaSimulada();

        // Llamar al método que queremos probar con una columna inexistente
        String resultado = metodo.obtenerDatosSumadosJTable(tablaSimulada, 3);

        // Verificar que el resultado es una cadena vacía, ya que la columna no existe
        assertEquals("", resultado);
    }

    private JTable crearTablaSimulada() {
        DefaultTableModel modeloSimulado = new DefaultTableModel(
                new Object[][]{{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}},
                new Object[]{"Columna1", "Columna2", "Columna3"});

        return new JTable(modeloSimulado);
    }
    
    @Test
    public void testBtnDefecto() {
        metodos metodo = new metodos();

        // Crear un JFrame simulado y un JButton simulado
        JFrame frameSimulado = new JFrame();
        JButton btnSimulado = new JButton();

        // Llamar al método que queremos probar
        metodo.BtnDefecto(frameSimulado, btnSimulado);

        // Verificar que el botón predeterminado del JFrame es el JButton simulado
        assertEquals(btnSimulado, frameSimulado.getRootPane().getDefaultButton());
    }
    
    @Test
    public void testCompararEntreFechasFechaDentroDelRango() {
        metodos metodo = new metodos();

        // Crear fechas simuladas
        Date fecha = new Date();
        Date fechaIng = new Date(System.currentTimeMillis() - 86400000); // Hace un día
        Date fechaSal = new Date(System.currentTimeMillis() + 86400000); // Dentro de un día

        // Llamar al método que queremos probar
        boolean resultado = metodo.compararEntreFechas(fecha, fechaIng, fechaSal);

        // Verificar que el resultado es true, ya que la fecha está dentro del rango
        assertTrue(resultado);
    }

    @Test
    public void testCompararEntreFechasFechaFueraDelRango() {
        metodos metodo = new metodos();

        // Crear fechas simuladas
        Date fecha = new Date();
        Date fechaIng = new Date(System.currentTimeMillis() + 86400000); // Dentro de un día
        Date fechaSal = new Date(System.currentTimeMillis() + 172800000); // Dentro de dos días

        // Llamar al método que queremos probar
        boolean resultado = metodo.compararEntreFechas(fecha, fechaIng, fechaSal);

        // Verificar que el resultado es false, ya que la fecha está fuera del rango
        assertFalse(resultado);
    }
}
