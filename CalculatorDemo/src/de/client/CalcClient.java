package de.client;

import math.Calculator;

public class CalcClient {

    private final Calculator calculator;

    public CalcClient(Calculator calculator) {
        this.calculator = calculator;
    }

    public void go() {
        System.out.println(calculator.sub(3,4));
    }
}
