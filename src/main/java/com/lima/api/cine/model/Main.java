package com.lima.api.cine.model;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        Conta conta = new Conta(new BigDecimal("400.00"));

        var threadJoao = Thread.ofPlatform().unstarted(() -> {
            conta.sacar(new BigDecimal("200.00"));
        });

        var threadMaria = Thread.ofPlatform().unstarted(() -> {
            conta.sacar(new BigDecimal("400.00"));
        });

        threadJoao.start();
        threadMaria.start();

        try {
            threadJoao.join();
            threadMaria.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(conta.getSaldo());
    }

    static class Conta {

        BigDecimal saldo;

        public Conta(BigDecimal saldoInicial) {
            this.saldo = saldoInicial;
        }

        public BigDecimal getSaldo() {
            return saldo;
        }

        public void depositar(BigDecimal valorDepositado){
            this.saldo = this.saldo.add(valorDepositado);
        }

        public void sacar(BigDecimal valorSacado){

            if(valorSacado.compareTo(this.saldo) > 0){
                throw new IllegalArgumentException("O valor do saque é maior do que o saldo disponivel");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.saldo = this.saldo.subtract(valorSacado);
            System.out.println(Thread.currentThread().getName() + " sacou " + valorSacado);
        }

        @Override
        public String toString() {
            return "Conta{" +
                    "saldo='" + saldo + '\'' +
                    '}';
        }
    }

}
