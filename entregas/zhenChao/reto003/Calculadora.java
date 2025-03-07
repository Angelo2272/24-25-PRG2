package zhenChao.reto003;

import java.util.Scanner;

public class Calculadora {

    private double[] numeros;
    private int posicionActual;
    private boolean error;
    private String mensajeError;
    static final private int CAPACIDAD_POR_DEFECTO = 10;

    public Calculadora(int capacidad) {

        numeros = new double[capacidad];
        posicionActual = 0;
        error = false;
        mensajeError = "";
    }

    public Calculadora(double valorInicial) {
            this(CAPACIDAD_POR_DEFECTO);
            ingresarNumero(valorInicial);
    }

    public Calculadora(double[] valoresIniciales) {
        this(valoresIniciales.length > 0 ? valoresIniciales.length : CAPACIDAD_POR_DEFECTO);
        for (double valor : valoresIniciales) {
            ingresarNumero(valor);
        }
    }

    public void ingresarNumero(double valor) {
        if (posicionActual < numeros.length) {
            numeros[posicionActual] = valor;
            posicionActual++;
        } else {
            error = true;
            mensajeError = "MEMORIA LLENA!!!";
        }
    }

    public String mostrar() {
        if (error) {
            return mensajeError;
        } else if (posicionActual > 0) {
            return "" + numeros[posicionActual - 1];
        } else {
            return "-";
        }
    }

    public String mostrarTodo() {
        String resultado = "";
        for (int i = 0; i < posicionActual; i = i + 1) {
            resultado = resultado + "[" + i + "] " + numeros[i] + "\n";
        }
        resultado = resultado + "-".repeat(10);
        return error ? mensajeError : resultado;
    }

    public void limpiar() {
        posicionActual = 0;
        error = false;
        mensajeError = "";
    }

    public void sumar() {
        if (verificarOperandos(2)) {
            double[] operandos = extraerOperandos(2);
            ingresarNumero(operandos[0] + operandos[1]);
        }
    }

    private double[] extraerOperandos(int numeroOperandos) {
        double[] operandos = new double[numeroOperandos];
        for (int i = 0; i < numeroOperandos; i++) {
            operandos[i] = extraerOperando();
        }
        return operandos;
    }

    private double extraerOperando() {
        posicionActual--;
        return numeros[posicionActual];
    }

    private boolean verificarOperandos(int numeroOperandos) {
        if (posicionActual >= numeroOperandos) {
            return true;
        } else {
            error = true;
            mensajeError = "Faltan operandos!";
            return false;
        }
    }

    public void invertir() {
        if (verificarOperandos(1)) {
            double[] operadores = extraerOperandos(1);
            ingresarNumero(-operadores[0]);
        }
    }

    public void restar() {
        if (verificarOperandos(2)) {
            double[] operandos = extraerOperandos(2);
            ingresarNumero(operandos[1] - operandos[0]);
        }
    }

    public void dividir() {
        if (verificarOperandos(2)) {
            double[] operandos = extraerOperandos(2);
            ingresarNumero(operandos[1] / operandos[0]);
        }
    }

    public void multiplicar() {
        if (verificarOperandos(2)) {
            double[] operandos = extraerOperandos(2);
            ingresarNumero(operandos[1] * operandos[0]);
        }
    }

    public void calcularMedia() {
        int numeroDeOperandos = posicionActual;
        calcularSumatoria();
        ingresarNumero(numeroDeOperandos);
        dividir();
    }

    public void calcularSumatoria() {
        int numeroDeOperandos = posicionActual;
        for (int i = 0; i < numeroDeOperandos - 1; i++) {
            sumar();
        }
    }

    public void calcularPorcentajes() {
        if (verificarOperandos(2)) {
            double[] operandos = extraerOperandos(2);
            double porcentaje = (operandos[0] / operandos[1]) * 100;
            ingresarNumero(porcentaje);
        }
    }

    public void calcularMaximo() {
        if (posicionActual > 0) {
            double maximo = numeros[0];
            for (int i = 1; i < posicionActual; i++) {
                if (numeros[i] > maximo) {
                    maximo = numeros[i];
                }
            }
            limpiar();
        ingresarNumero(maximo);
        } else {
            error = true;
            mensajeError = "ERROR para calcular el máximo!";
        }
    }

    public void calcularMinimo() {

        if (posicionActual > 0) {
            double minimo = numeros[0];
            for (int i = 1; i < posicionActual; i++) {
                if (numeros[i] < minimo) {
                    minimo = numeros[i];
                }
            }
            limpiar();
        ingresarNumero(minimo);
        } else {
            error = true;
            mensajeError = "ERROR AL CALCULAR EL MINIMO!";
        }
    }

    public void calcularFactorial() {
        if (verificarOperandos(1)) {
            double[] operandos = extraerOperandos(1);
            int numero = (int) operandos[0];
            long resultado = 1;
            for (int i = 1; i <= numero; i++) {
                resultado *= i;
            }
            ingresarNumero(resultado);
        }
    }

    public double pedirValorUsuario(Scanner entrada) {
        System.out.println("Digite un valor");
        double valorUsuario = entrada.nextDouble();
        return valorUsuario;
    }

    public void sumarConEntradaUsuario(double valorUsuario) {
        pedirValorUsuario(null);
        ingresarNumero(valorUsuario);
        sumar();
    }

    public void restarConEntradaUsuario(double valorUsuario) {
        pedirValorUsuario(null);
        ingresarNumero(valorUsuario);
        restar();
    }

    public void multiplicarConEntradaUsuario(double valorUsuario) {
        pedirValorUsuario(null);
        ingresarNumero(valorUsuario);
        multiplicar();
    }

    public void dividirConEntradaUsuario(double valorUsuario) {
        pedirValorUsuario(null);
        ingresarNumero(valorUsuario);
        dividir();
    }

    public void calcularPorcentajesConEntradaUsuario(double valorUsuario) {
        pedirValorUsuario(null);
        ingresarNumero(valorUsuario);
        calcularPorcentajes();
    }

    public void intercambiar() {
        if (verificarOperandos(2)) {
            double ultimoValor = numeros[posicionActual - 1];
            numeros[posicionActual - 1] = numeros[posicionActual - 2];
            numeros[posicionActual - 2] = ultimoValor;
        }
    }

    public void duplicarNumero() {
        if (verificarOperandos(1)) {
            double[] operandos = extraerOperandos(1);
            ingresarNumero(operandos[0]);
            ingresarNumero(operandos[0]);
        }
    }
    
    public void calcularRaizCuadrada() {
        if (verificarOperandos(1)) {
            double[] operandos = extraerOperandos(1);
            ingresarNumero(Math.sqrt(operandos[0]));
        }
    }

    public void calcularPotencia() {
        if (verificarOperandos(2)) {
            double[] operandos = extraerOperandos(2);
            ingresarNumero(Math.pow(operandos[1], operandos[0]));
        }
    }

    public void calcularPotencia(double valor) {
        ingresarNumero(valor);
        calcularPotencia();
    }
}


