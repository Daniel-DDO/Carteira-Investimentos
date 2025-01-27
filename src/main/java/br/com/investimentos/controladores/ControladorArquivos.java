package br.com.investimentos.controladores;

import br.com.investimentos.financas.AtivosFinanceiros;
import br.com.investimentos.usuarios.CarteiraUsuario;
import br.com.investimentos.usuarios.ContaUsuario;

import java.io.*;

public class ControladorArquivos {

    private static final String CONTAS_ARQUIVO = "Contas.dat";
    private static final String CARTEIRAS_ARQUIVO = "Carteiras.dat";
    private static final String ATIVOS_ARQUIVO = "Ativos.dat";
    private static int tamanho = 100;

    //Métodos de contas de usuários

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


    //Métodos de carteiras

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
                oos.writeObject(carteiras);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Array das carteiras está cheio.");
        }
    }

    public static CarteiraUsuario[] lerCarteira() {
        File carteiraArquivo = new File(CARTEIRAS_ARQUIVO);
        if (!carteiraArquivo.exists()) {
            return new CarteiraUsuario[tamanho];
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(carteiraArquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof CarteiraUsuario[]) {
                return (CarteiraUsuario[]) obj;
            } else {
                return new CarteiraUsuario[tamanho];
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new CarteiraUsuario[tamanho];
        }
    }

    public static void atualizarCarteiras(CarteiraUsuario[] carteiras) {
        CarteiraUsuario[] carteirasAtualizadas = new CarteiraUsuario[carteiras.length];
        int posicaoLivre = 0;

        for (int i = 0; i < carteiras.length; i++) {
            if (carteiras[i] != null) {
                carteirasAtualizadas[posicaoLivre] =  carteiras[i];
                posicaoLivre++;
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CARTEIRAS_ARQUIVO))) {
            oos.writeObject(carteirasAtualizadas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Métodos de ativos

    public static void escreverAtivos(AtivosFinanceiros ativo) {
        AtivosFinanceiros[] ativosFinanceiros = lerAtivos();

        if (ativosFinanceiros == null) {
            ativosFinanceiros = new AtivosFinanceiros[tamanho];
        }

        int posicaoLivre = -1;
        for (int i = 0; i < ativosFinanceiros.length; i++) {
            if (ativosFinanceiros[i] == null) {
                posicaoLivre = i;
                break;
            }
        }

        if (posicaoLivre != -1) {
            ativosFinanceiros[posicaoLivre] = ativo;

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ATIVOS_ARQUIVO))) {
                oos.writeObject(ativosFinanceiros);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Array dos ativos está cheio.");
        }
    }

    public static AtivosFinanceiros[] lerAtivos() {
        File ativosArquivo = new File(ATIVOS_ARQUIVO);
        if (!ativosArquivo.exists()) {
            return new AtivosFinanceiros[tamanho];
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ativosArquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof CarteiraUsuario[]) {
                return (AtivosFinanceiros[]) obj;
            } else {
                return new AtivosFinanceiros[tamanho];
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new AtivosFinanceiros[tamanho];
        }
    }

    public static void atualizarAtivos(AtivosFinanceiros[] ativos) {
        AtivosFinanceiros[] ativosAtualizados = new AtivosFinanceiros[ativos.length];
        int posicaoLivre = 0;

        for (int i = 0; i < ativos.length; i++) {
            if (ativos[i] != null) {
                ativosAtualizados[posicaoLivre] =  ativos[i];
                posicaoLivre++;
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ATIVOS_ARQUIVO))) {
            oos.writeObject(ativosAtualizados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //Outros

    public static int getTamanho() {
        return tamanho;
    }

    public static void setTamanho(int tamanho) {
        ControladorArquivos.tamanho = tamanho;
    }



}
