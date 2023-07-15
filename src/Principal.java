import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Principal {
    public static void main(String[] args) {

        // 3.1 - Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1992, 1, 5), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        //3.2 - Remover João da lista
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        //3.3 – Imprimir todos os funcionários com todas suas informações
        //• informação de data deve ser exibido no formato dd/mm/aaaa;
        //• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
        System.out.println("3.3 – Imprimir todos os funcionários com todas suas informações");

        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat decimalFormatado = new DecimalFormat("#,##0.00");

        for (Funcionario funcionario : funcionarios) {
            String dataNascimentoFormatada = funcionario.getDataNascimento().format(dataFormatada);
            String salarioFormatado = decimalFormatado.format(funcionario.getSalario());

            System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: R$ %s, Função: %s%n", funcionario.getNome(), dataNascimentoFormatada, salarioFormatado, funcionario.getFuncao());
        }

        // 3.4 - Aumentar os salários dos funcionários em 10%
        System.out.println("3.4 - Aumentar os salários dos funcionários em 10%");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salarioAtualizado = funcionario.getSalario().multiply(new BigDecimal("1.10"));
            funcionario.setSalario(salarioAtualizado);
        }

        // 3.5 - Agrupando os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();

        for (Funcionario funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();

            if (funcionariosPorFuncao.containsKey(funcao)) {
                funcionariosPorFuncao.get(funcao).add(funcionario);
            } else {
                List<Funcionario> listaFuncionarios = new ArrayList<>();
                listaFuncionarios.add(funcionario);
                funcionariosPorFuncao.put(funcao, listaFuncionarios);
            }
        }

        // 3.6 - Imprimir os funcionários agrupados por função
        System.out.println("\n3.6 - Imprimir os funcionários agrupados por função");

        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            String funcao = entry.getKey();
            List<Funcionario> listaFuncionarios = entry.getValue();

            System.out.println("Função: " + funcao);
            for (Funcionario funcionario : listaFuncionarios) {
                String dataNascimentoFormatado = funcionario.getDataNascimento().format(dataFormatada);
                String salarioFormatado = decimalFormatado.format(funcionario.getSalario());

                System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: R$ %s%n",
                        funcionario.getNome(), dataNascimentoFormatado, salarioFormatado);
            }
            System.out.println();
        }

        // 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12
        System.out.println("\n// 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12");

        for (Funcionario funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                String dataNascimentoFormatada = funcionario.getDataNascimento().format(dataFormatada);
                System.out.printf("Nome: %s, Data de Nascimento: %s%n", funcionario.getNome(), dataNascimentoFormatada);
            }
        }
        // 3.9 - Imprimir o funcionário com a maior idade
        System.out.println("\n// 3.9 - Imprimir o funcionário com a maior idade");

        Funcionario funcionarioMaisVelho = Collections.max(funcionarios, Comparator.comparing(
                funcionario -> funcionario.getDataNascimento().toEpochDay()));
        int idade = LocalDate.now().getYear() - funcionarioMaisVelho.getDataNascimento().getYear();
        System.out.printf("Nome: %s, Idade: %d%n", funcionarioMaisVelho.getNome(), idade);

        // 3.10 - Imprimir a lista de funcionários em ordem alfabética
        System.out.println("\n// 3.10 - Imprimir a lista de funcionários em ordem alfabética:");
        Collections.sort(funcionarios, Comparator.comparing(Funcionario::getNome));
        for (Funcionario funcionario : funcionarios) {
            String dataNascimentoFormatada = funcionario.getDataNascimento().format(dataFormatada);
            String salarioFormatado = decimalFormatado.format(funcionario.getSalario());

            System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: R$ %s%n",
                    funcionario.getNome(), dataNascimentoFormatada, salarioFormatado);
        }
        // 3.11 - Imprimir o total dos salários dos funcionários
        System.out.println("\n// 3.11 - Imprimir o total dos salários dos funcionários");
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        System.out.println("\nTotal dos salários dos funcionários: R$ " + decimalFormatado.format(totalSalarios));

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\n// 3.12 - Imprimir quantos salários mínimos ganha cada funcionário");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal quantidadeSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
            System.out.printf("Nome: %s, Quantidade de salários mínimos: %.2f%n",
                    funcionario.getNome(), quantidadeSalariosMinimos);
        }


    }
}

