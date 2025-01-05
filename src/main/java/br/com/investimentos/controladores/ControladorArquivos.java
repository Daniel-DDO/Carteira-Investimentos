package br.com.investimentos.controladores;

import br.com.investimentos.usuarios.CarteiraUsuario;
import br.com.investimentos.usuarios.Conta;

import java.io.*;

public class ControladorArquivos {

    private static final String CONTAS_ARQUIVO = "Contas.dat";
    private static final String CARTEIRAS_ARQUIVO = "Carteiras.dat";
    private static int tamanho = 100;

    public static void escreverNoArquivo(Conta conta) {
        Conta[] contas = lerDoArquivo();

        if (contas == null) {
            contas = new Conta[tamanho];
        }

        int posicaoLivre = -1;
        for (int i = 0; i < contas.length; i++) {
            if (contas[i] == null) {
                posicaoLivre = i;
                break;
            }
        }

        if (posicaoLivre != -1) {
            contas[posicaoLivre] = conta;

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONTAS_ARQUIVO))) {
                oos.writeObject(contas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Array de contas está cheio.");
        }

    }

    public static void escreverCarteira(CarteiraUsuario carteira) {
        CarteiraUsuario[] carteiras = lerCarteira();

        if (carteiras == null) {
            carteiras = new CarteiraUsuario[tamanho];
        }


        int posicaoLivre = -1;
        for (int i = 0; i < carteiras.length; i++) {
            if (carteiras[i] == null) {
                posicaoLivre = i;
                break;
            }
        }

        if (posicaoLivre != -1) {
            carteiras[posicaoLivre] = carteira;

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CARTEIRAS_ARQUIVO))) {
                oos.writeObject(carteira);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Array das carteiras está cheio.");
        }
    }

    public static Conta[] lerDoArquivo() {
        File arquivo = new File(CONTAS_ARQUIVO);
        if (!arquivo.exists()) {
            return new Conta[tamanho];
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (Conta[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Conta[tamanho];
        }
    }

    public static CarteiraUsuario[] lerCarteira() {
        File carteiraArquivo = new File(CARTEIRAS_ARQUIVO);
        if (!carteiraArquivo.exists()) {
            return new CarteiraUsuario[tamanho];
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(carteiraArquivo))) {
            return (CarteiraUsuario[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new CarteiraUsuario[tamanho];
        }
    }

    public static int getTamanho() {
        return tamanho;
    }

    public static void setTamanho(int tamanho) {
        ControladorArquivos.tamanho = tamanho;
    }



}
