# 🧩 Descrição do Projeto – Sudoku Terminal Game

Este projeto é uma implementação completa do jogo **Sudoku** para terminal, construída com **Java** e estruturada com base no padrão de arquitetura **MVC (Model-View-Controller)**. Ele simula o jogo tradicional, onde o usuário interage com o tabuleiro via linha de comando, inserindo e removendo números, validando jogadas e acompanhando o status do jogo em tempo real.

## 🚀 Funcionalidades

- Início de jogo com tabuleiro configurável
- Inserção e remoção de números nas células
- Verificação automática de erros
- Exibição do status do jogo (não iniciado, incompleto, completo)
- Limpeza e reinicialização do tabuleiro
- Interface textual rica com apresentação visual do Sudoku

## 🧱 Arquitetura
O projeto segue o padrão Model-View-Controller, dividido em camadas:

- Model: Contém as classes que representam a estrutura de dados do jogo, como Board (tabuleiro), Space (célula) e o GameStatusEnum (enumeração de status do jogo).

- View: Responsável por exibir o tabuleiro e o menu no terminal. Utiliza o template estético da classe BoardView para apresentar a matriz de forma visualmente agradável.

- Controller: Centraliza o fluxo da aplicação. A classe Main (ou GameController, em uma versão mais separada) gerencia as opções escolhidas pelo usuário e delega as ações para a lógica.

- Service (opcional): Em uma organização mais robusta, os métodos como inputNumber, clearValue e checkGameStatus podem ser extraídos para uma camada de serviço para separar regras de negócio da entrada de dados.

### 📁 Estrutura de pastas 

O projeto segue uma estrutura de pastas modular com separação clara de responsabilidades:
```
game_sudoku/
├── .vscode/
├── bin/
├── lib/
├── src/
|   ├── application/
|   |  └── Main.java
|   └── sudoku/
|       ├── controller/
|       │   └── GameController.java
|       ├── model/
|       │   ├── Board.java
|       │   ├── Space.java
|       │   └── GameStatusEnum.java
|       ├── view/
|       │   ├── GameView.java
|       |   └── BoardView.java
|       └── service/
|           └── GameService.java
├── .gitignore
└── README.md
```

## 📦 Classes principais

- **`Board`**: Representa o tabuleiro e centraliza a lógica de status e validação do jogo.
- **`Space`**: Define uma célula do jogo, indicando valor atual, valor esperado e se é fixa.
- **`GameStatusEnum`**: Enumeração que indica o estado atual da partida.
- **`BoardTemplate`**: Armazena um template formatado para imprimir o tabuleiro com estética agradável.
- **`Main`**: Executa o menu interativo no terminal e orquestra o ciclo principal do jogo.

## 🧬 Diagrama UML Mermaid

````
classDiagram
    direction TB

    class App {
        +main(String[]): void
    }

    class GameController {
        -GameService service
        -GameView view
        -boolean running
        +run(): void
    }

    class GameService {
        -Board board
        +startGame(String[]): void
        +inputNumber(int, int, int): void
        +removeNumber(int, int): void
        +clearBoard(): void
        +finishGame(): boolean
        +getBoard(): Board
    }

    class GameView {
        +showMenu(): void
        +readOption(): int
        +readRow(): int
        +readCol(): int
        +readValue(): int
        +readInitialBoard(): String[]
        +showBoard(Board): void
        +showStatus(Board): void
        +showSuccess(): void
        +showInvalidOption(): void
        +sayGoodbye(): void
    }

    class BoardView {
        <<utility>>
        +format(Board): String
    }

    class Board {
        -List<List<Space>> spaces
        +getSpaces(): List<List<Space>>
        +getStatus(): GameStatusEnum
        +hasErrors(): boolean
        +changeValue(int, int, int): boolean
        +clearValue(int, int): boolean
        +reset(): void
        +gameIsFinished(): boolean
    }

    class Space {
        -Integer actual
        -int expected
        -boolean fixed
        +getActual(): Integer
        +setActual(Integer): void
        +clearSpace(): void
        +getExpected(): int
        +isFixed(): boolean
    }

    class GameStatusEnum {
        <<enum>>
        NON_STARTED
        INCOMPLETE
        COMPLETE
    }

    %% Relações
    App --> GameController
    GameController --> GameService
    GameController --> GameView
    GameView --> BoardView
    GameService --> Board
    Board --> Space
    Board --> GameStatusEnum
````

## 💻 Como executar

1. **Clone o repositório**:

```bash
git clone https://github.com/seu-usuario/sudoku-game-terminal-java.git
cd sudoku_game/bin
```
2. **Execute o jogo**:

```bash
java application.App 0,0:5,true 0,1:3,true 0,4:7,true ...
```

> Você pode passar os valores iniciais do tabuleiro como argumentos na forma linha,coluna:valor,fixo.
````
0,0:9,true 0,1:5,true 0,2:8,true 0,3:0,false 0,4:0,false 0,5:0,false 0,6:0,false 0,7:2,true 0,8:0,false 1,0:0,false 1,1:0,false 1,2:0,false 1,3:2,true 1,4:5,true 1,5:6,true 1,6:0,false 1,7:4,true 1,8:0,false 2,0:0,false 2,1:0,false 2,2:6,true 2,3:0,false 2,4:0,false 2,5:0,false 2,6:5,true 2,7:1,true 2,8:7,true 3,0:6,true 3,1:0,false 3,2:0,false 3,3:3,true 3,4:7,true 3,5:8,true 3,6:0,false 3,7:0,false 3,8:0,false 4,0:7,true 4,1:8,true 4,2:4,true 4,3:0,false 4,4:0,false 4,5:0,false 4,6:9,true 4,7:3,true 4,8:2,true 5,0:0,false 5,1:0,false 5,2:0,false 5,3:4,true 5,4:2,true 5,5:9,true 5,6:0,false 5,7:0,false 5,8:8,true 6,0:4,true 6,1:9,true 6,2:2,true 6,3:0,false 6,4:0,false 6,5:0,false 6,6:1,true 6,7:0,false 6,8:0,false 7,0:0,false 7,1:6,true 7,2:0,false 7,3:5,true 7,4:8,true 7,5:1,true 7,6:0,false 7,7:0,false 7,8:0,false 8,0:0,false 8,1:1,true 8,2:0,false 8,3:0,false 8,4:0,false 8,5:0,false 8,6:7,true 8,7:6,true 8,8:3,true
````
## 🧠 Exemplo de uso no terminal

1 - Iniciar um novo Jogo<br>
2 - Colocar um novo número<br>
3 - Remover um número<br>
4 - Visualizar jogo atual<br>
5 - Verificar status do jogo<br>
6 - Limpar jogo<br>
7 - Finalizar jogo<br>
8 - Sair<br>


## 🎯 Objetivo educacional

Este projeto tem como propósito:

- Exercitar lógica de programação e validações

- Aprimorar conceitos de POO (classes, atributos, encapsulamento)

- Aplicar práticas de arquitetura limpa em Java

- Desenvolver habilidades com entrada/saída no terminal


## 🛠️ Tecnologias

- Java 21
- VS Code
- Paradigma de Programação Orientada a Objetos
- Padrão MVC
- Diagramação com Mermaid Markdown


## 👤 Autor

Desenvolvido por **raesbyt**, com apoio educacional da [DIO](https://dio.me).  
Se quiser me acompanhar ou contribuir, fique à vontade para visitar meu GitHub!

📄 Licença
Este projeto está licenciado sob a MIT License.

---
