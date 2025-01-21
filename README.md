# Projeto 5: Carteira de Investimentos (com simulação)

## Descrição
O sistema será uma plataforma de simulação de carteira de investimentos, onde os usuários poderão montar e gerenciar uma carteira de ativos financeiros, como ações, fundos imobiliários, títulos de renda fixa, criptomoedas, entre outros. O objetivo principal será permitir que os usuários testem suas estratégias de investimento sem risco financeiro, usando dados históricos e simuladores.

Os usuários poderão adicionar ativos à sua carteira, visualizar o desempenho de sua carteira ao longo do tempo, realizar compras e vendas de ativos, e verificar as métricas relacionadas a cada ativo (ex.: preço de compra, preço atual, variação percentual, etc.). O sistema também permitirá que o usuário configure metas de rentabilidade e acompanhe o progresso de sua carteira em direção a essas metas.

Além disso, o sistema deve oferecer funcionalidades para gerar relatórios com o histórico da carteira e projeções de performance futura. O administrador do sistema poderá cadastrar os ativos disponíveis para compra, monitorar o mercado e realizar ajustes.

**Exemplo de sistemas reais**: Existem várias plataformas de simuladores de investimentos, como **Investing.com**, **TradingView** e **Rico**, que permitem que investidores testem suas estratégias e visualizem o desempenho dos ativos em tempo real.

## Requisitos Funcionais

### Gerenciamento de Entidades
- **REQ01**: Gerenciamento de administradores, que podem cadastrar, editar ou remover ativos financeiros do sistema.
- **REQ02**: Gerenciamento de usuários, que podem criar contaUsuarios, realizar login, visualizar e gerenciar suas carteiras de investimentos.
- **REQ03**: Gerenciamento da carteira de investimentos do usuário, permitindo a inclusão, edição e remoção de ativos.

### Operações de Investimentos
- **REQ04**: Adição de ativos à carteira do usuário, com informações como tipo de ativo, quantidade, preço de compra, etc.
- **REQ05**: Realização de compras e vendas de ativos, incluindo a atualização da carteira e do saldo financeiro do usuário.
- **REQ06**: Simulação de performance da carteira, incluindo a variação do valor de cada ativo ao longo do tempo e o impacto disso no valor total da carteira.
- **REQ07**: Exibição de gráficos e indicadores financeiros que mostrem a evolução dos ativos ao longo do tempo (ex.: preços, variações diárias, rentabilidade).

### Consultas e Relatórios
- **REQ08**: Relatórios detalhados da carteira de investimentos, incluindo compras e vendas realizadas, valor total investido, e rentabilidade atual.
- **REQ09**: Geração de relatórios comparativos entre o desempenho da carteira do usuário e o mercado (ex.: comparando a carteira com um índice de referência como o Ibovespa).

### Meta de Rentabilidade e Acompanhamento
- **REQ10**: Definição de metas de rentabilidade por parte do usuário, com indicadores de progresso.
- **REQ11**: Exibição de alertas para o usuário caso sua carteira atinja ou fique distante das metas estabelecidas.

### Validações e Controle
- **REQ12**: Validação de saldo do usuário para a realização de compras de ativos, com verificação de que o usuário tem fundos suficientes para a transação.
- **REQ13**: Atualização automática dos preços dos ativos com base em dados históricos ou em tempo real, conforme a implementação.

### Projeções de Rentabilidade e Acompanhamento
- **REQ14**: Simulação do impacto de aportes mensais na carteira do usuário, com base em uma taxa de retorno esperada, utilizando índices como IPCA, SELIC ou dólar. A projeção deve considerar os aportes mensais ao longo do tempo e o crescimento composto dos investimentos.
- **REQ15**: Projeção de rentabilidade futura considerando a valorização dos ativos e a inflação (IPCA), taxas de juros (SELIC), ou variação cambial (dólar), de acordo com as condições econômicas para estimar o valor futuro da carteira.
