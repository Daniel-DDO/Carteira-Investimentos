package br.com.investimentos.controladores.gui;

import br.com.investimentos.usuarios.CarteiraUsuario;
import br.com.investimentos.usuarios.ContaUsuario;

import java.io.*;

public class ControladorArquivos {

    private static final String CONTAS_ARQUIVO = "Contas.dat";
    private static final String CARTEIRAS_ARQUIVO = "Carteiras.dat";
    private static int tamanho = 100;

    public static void escreverNoArquivo(ContaUsuario contaUsuario) {
        ContaUsuario[] contaUsuarios = lerDoArquivo();

        if (contaUsuarios == null) {
            contaUsuarios = new ContaUsuario[tamanho];
        }

        int posicaoLivre = -1;
        for (int i = 0; i < contaUsuarios.length; i++) {
            if (contaUsuarios[i] == null) {
                posicaoLivre = i;
                break;
            }
        }

        if (posicaoLivre != -1) {
            contaUsuarios[posicaoLivre] = contaUsuario;

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONTAS_ARQUIVO))) {
                oos.writeObject(contaUsuarios);
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

    public static void atualizarContas(ContaUsuario[] contaUsuarios) {
        ContaUsuario[] contasAtualizadas = new ContaUsuario[contaUsuarios.length];
        int posicaoLivre = 0;

        for (int i = 0; i < contaUsuarios.length; i++) {
            if (contaUsuarios[i] != null) {
                contasAtualizadas[posicaoLivre] = contaUsuarios[i];
                posicaoLivre++;
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONTAS_ARQUIVO))) {
            oos.writeObject(contasAtualizadas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ContaUsuario[] lerDoArquivo() {
        File arquivo = new File(CONTAS_ARQUIVO);
        if (!arquivo.exists()) {
            return new ContaUsuario[tamanho];
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ContaUsuario[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ContaUsuario[tamanho];
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
