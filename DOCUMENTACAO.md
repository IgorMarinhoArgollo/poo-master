# Documentação do Sistema Mestre das Fichas

## Sumário
- [Visão Geral](#visão-geral)
- [Entidades Principais](#entidades-principais)
  - [Criaturas](#criaturas)
  - [Personagem](#personagem)
  - [Guerreiro](#guerreiro)
  - [Mago](#mago)
- [Sistema de Itens](#sistema-de-itens)
  - [Item](#item)
  - [Consumível](#consumível)
  - [Equipamento](#equipamento)
  - [Moeda](#moeda)
  - [Empilhável](#empilhável)
  - [Slots](#slots)
- [Menu e Comandos](#menu-e-comandos)
- [Fluxo de Batalha](#fluxo-de-batalha)
- [Utilitários](#utilitários)
- [Constantes Globais](#constantes-globais)

---

## Visão Geral
O sistema simula um jogo de RPG de fichas, permitindo criar personagens (guerreiros, magos), equipar itens, usar habilidades, enfrentar inimigos e gerenciar inventário. O design modular facilita a expansão para novos tipos de personagens, itens, comandos e mecânicas de jogo.

---

## Entidades Principais

### Criaturas
Classe abstrata base para todos os seres do jogo (personagens e inimigos). Possui atributos como vida, força, destreza, constituição, inteligência, percepção e agilidade. Define métodos para ataque, defesa, cálculo de dano, buffs e verificação de vida.

- **Atributos:** nome, vida máxima/atual, força, destreza, constituição, inteligência, percepção, agilidade.
- **Métodos principais:**
  - `recebeDano(int amount)`: Aplica dano físico considerando defesa.
  - `recebeDano(int amount, boolean magico)`: Aplica dano mágico considerando defesa.
  - `ataque(Criaturas alvo)`: Realiza ataque, com rolagem de acerto, crítico e cálculo de dano.
  - Métodos auxiliares para cálculo de dano, defesa, buffs, getters/setters.

### Personagem
Classe abstrata que herda de Criaturas. Adiciona inventário, equipamentos, experiência, nível e capacidade de carregar itens. Gerencia subida de nível, buffs, uso de poções, equipar/desequipar itens e listagem de inventário/equipamentos.

- **Inventário:** Lista de itens, com suporte a itens empilháveis.
- **Equipamentos:** Mapeados por slot (mão direita, esquerda, armadura).
- **Experiência e nível:** Ganha experiência e sobe de nível automaticamente.
- **Métodos principais:**
  - `adicionarItem(Item item)`, `removerItem(String nome, int quantidade)`
  - `equiparItem(String nomeItem)`, `desequiparItem(String slot)`
  - `usarPocao(String nomePocao)`
  - `listarInventario()`, `listarEquipamentos()`

### Guerreiro
Especialização de Personagem focada em força, constituição e destreza. Possui habilidade especial de postura defensiva, reduzindo dano recebido por alguns turnos. O cálculo de dano e defesa leva em conta equipamentos e buffs.

- **Habilidade:** `posturaDefensiva()`
- **Defesa:** Mitigação baseada em armadura e constituição.

### Mago
Especialização de Personagem focada em inteligência e percepção. Possui habilidade especial de miragem arcana, criando ilusões que podem evitar ataques. Ataques mágicos usam inteligência para ataque e defesa.

- **Habilidade:** `miragemArcana()`
- **Defesa:** Mitigação baseada em armadura e inteligência. Não pode equipar itens na mão esquerda.

---

## Sistema de Itens

### Item
Classe abstrata base para todos os itens. Possui nome e valor. Define o método `isEmpilhavel()` para distinguir itens empilháveis (como moedas e poções).

### Consumível
Item que pode ser consumido (ex: poções). Implementa a interface Empilhável, permitindo múltiplas unidades. Possui atributos de cura e quantidade.
- Métodos para aumentar/diminuir quantidade.

### Equipamento
Item que pode ser equipado em um slot específico (mão direita, esquerda, armadura). Possui atributos de ataque, defesa e estado (equipado ou não).
- Valida o slot no construtor.

### Moeda
Item empilhável que representa dinheiro. Possui quantidade e métodos para manipulação.

### Empilhável
Interface para itens que podem ser agrupados (moedas, poções). Define métodos para obter, aumentar e diminuir quantidade.

### Slots
Enumeração dos slots de equipamento disponíveis: mão direita, mão esquerda, armadura. Fornece métodos para validação e formatação.

---

## Menu e Comandos
O menu principal (`MenuPrincipal`) gerencia todos os comandos disponíveis para o usuário, como criar personagens, itens, equipar, listar inventário, iniciar batalhas, etc. Cada comando é uma classe que implementa a interface `Comando` e encapsula a lógica da ação.

- **Exemplos de comandos:**
  - CriarGuerreiroCommand, CriarMagoCommand, CriarInimigoCommand
  - CriarMoedaCommand, CriarConsumivelCommand, CriarEquipamentoCommand
  - AtribuirItemCommand, EquiparItemCommand, ListarEquipamentosCommand, ListarInventarioCommand
  - RemoverItemCommand, AtribuirExperienciaCommand, ExibirStatusCommand, ExibirPersonagensCommand, ExibirInimigosCommand
  - IniciarBatalhaCommand (inicia o fluxo de combate)

---

## Fluxo de Batalha
O comando `IniciarBatalhaCommand` gerencia o combate entre aliados e inimigos:
- Usuário seleciona personagens e inimigos participantes.
- A ordem dos turnos é definida pela agilidade.
- Cada personagem pode atacar, usar habilidade ou consumível.
- Inimigos atacam aleatoriamente.
- O combate segue até que todos de um lado sejam derrotados.
- Personagens mortos são removidos das listas principais.

Ações de batalha:
- `AcaoAtacar`: Permite ao personagem atacar um inimigo.
- `AcaoUsarHabilidade`: Usa a habilidade especial do personagem (postura defensiva ou miragem arcana).
- `AcaoUsarConsumivel`: Permite usar poções durante a batalha.

---

## Utilitários

### LeitorUtils
Funções auxiliares para leitura de dados do usuário, como ler inteiros positivos, slots válidos e strings, sempre permitindo cancelar com "voltar".

### Dado
Funções para rolagem de dados de diferentes faces (d4, d6, d8, d10, d12, d20, d100), essenciais para o sistema de combate e aleatoriedade.

### BuscaUtils
Funções para buscar personagens e inimigos por nome nas listas.

### CenarioTeste
Carrega um cenário de teste completo, populando listas de guerreiros, magos, inimigos e itens para facilitar testes e demonstrações.

---

## Constantes Globais
Arquivo `Constantes.java` centraliza valores importantes do sistema, como multiplicadores de atributos, bônus por nível, valores de crítico, duração de buffs, etc. Permite fácil ajuste do balanceamento do jogo.

---

## Exemplos de Uso

### 1. Criação de Personagens

- **Criar um guerreiro:**
  - Comando: `criar guerreiro`
  - Fluxo: O sistema solicita o nome. Exemplo: `Thorin`.
  - Saída: Guerreiro Thorin criado e adicionado à lista de guerreiros.

- **Criar um mago:**
  - Comando: `criar mago`
  - Fluxo: O sistema solicita o nome. Exemplo: `Merlin`.
  - Saída: Mago Merlin criado e adicionado à lista de magos.

### 2. Criação e Manipulação de Itens

- **Criar um equipamento:**
  - Comando: `criar equipamento`
  - Fluxo: O sistema solicita nome, ataque, defesa, valor e slot. Exemplo: `Espada Longa, 5, 0, 100, mao_direita`.
  - Saída: Equipamento criado e adicionado à lista de itens.

- **Criar uma poção:**
  - Comando: `criar consumivel`
  - Fluxo: O sistema solicita nome, cura, valor e quantidade. Exemplo: `Poção Pequena, 5, 10, 2`.
  - Saída: Poção criada e adicionada à lista de itens.

### 3. Inventário e Equipamentos

- **Atribuir item a personagem:**
  - Comando: `atribuir item`
  - Fluxo: O sistema solicita nome do personagem e do item.
  - Saída: Item transferido para o inventário do personagem.

- **Equipar item:**
  - Comando: `equipar item`
  - Fluxo: O sistema solicita nome do personagem e do item.
  - Saída: Item equipado no slot correspondente.

- **Listar inventário:**
  - Comando: `listar inventario`
  - Fluxo: O sistema solicita nome do personagem.
  - Saída: Lista de itens no inventário.

### 4. Batalha

- **Iniciar batalha:**
  - Comando: `iniciar batalha`
  - Fluxo: O sistema solicita nomes dos aliados e inimigos participantes.
  - Durante a batalha, cada personagem pode:
    - Atacar (`1`)
    - Usar habilidade (`2`)
    - Usar consumível (`3`)
  - O combate segue em turnos até um dos lados ser derrotado.

### 5. Experiência e Nível

- **Atribuir experiência:**
  - Comando: `atribuir experiencia`
  - Fluxo: O sistema solicita nome do personagem e quantidade de experiência.
  - Saída: Personagem ganha experiência e pode subir de nível automaticamente.

---

## Áreas para Expansão

- **Novos Tipos de Personagem:**
  - Adicionar classes como Arqueiro, Clérigo, etc., com habilidades e atributos próprios.

- **Novos Tipos de Itens:**
  - Itens mágicos, artefatos, armas de duas mãos, acessórios.

- **Sistema de Missões:**
  - Implementar comandos para criar, listar e concluir missões.

- **Sistema de Magias:**
  - Permitir que magos aprendam e lancem magias variadas.

- **Interface Gráfica:**
  - Criar uma interface visual para facilitar a interação.

- **Persistência de Dados:**
  - Salvar e carregar o progresso do jogo em arquivos.

- **Logs e Relatórios:**
  - Gerar logs detalhados das batalhas e ações dos personagens.


---

**Esta documentação cobre as principais áreas do sistema. Para detalhes de implementação, consulte os comentários nos próprios arquivos de código.** 