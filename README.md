# API de Cadastro de Usuários com Validações

Este é um projeto de exemplo que demonstra a criação de uma API Java com Spring para o cadastro de usuários com validações. A API permite que os usuários forneçam informações, como nome, e-mail, data de nascimento, endereço e uma lista de habilidades. Além disso, inclui validações rigorosas para garantir que os dados fornecidos estejam corretos e atendam a critérios específicos.

## Requisitos

Para executar e trabalhar com este projeto, você precisará dos seguintes requisitos:

- Java 11
- Spring Boot
- Spring Data JPA
- Um banco de dados (H2 Database é usado como exemplo)
- Gerenciador de pacotes: Maven ou Gradle (escolha o de sua preferência)

## Como Executar

Siga as etapas abaixo para executar o aplicativo:

1. Clone este repositório:

   ```shell
   git clone https://github.com/MateusAleSilv/api-cadastro-usuarios.git

2. Acesse o diretório do projeto:
   
   ```shell
    cd api-cadastro-usuarios

3. Execute o aplicativo Spring Boot:

    Usando Maven:

    ```shell
    ./mvnw spring-boot:run

Usando Gradle:

    ./gradlew bootRun

A API estará disponível em http://localhost:8080.

Endpoints
A API oferece os seguintes endpoints:

- `POST /api/usuarios`: Cria um novo usuário com as informações fornecidas no corpo da requisição. Todas as informações são validadas para garantir que atendam aos critérios especificados.
- `GET /api/usuarios`: Retorna a lista de usuários cadastrados.

Exemplos de Requisições

### POST /api/usuarios
    Aqui está um exemplo de uma requisição de criação de usuário:

    {
        "nome": "João da Silva",
        "email": "joao.silva@gmail.com",
        "dataNascimento": "1990-01-15",
        "endereco": "123 Rua da Amostra",
        "habilidades": ["Java", "Spring Boot", "APIs"]
    }


### GET /api/usuarios
Realize uma requisição GET para obter a lista de usuários cadastrados.

### Contribuições
Você é bem-vindo(a) para contribuir com melhorias, correções de bugs ou recursos adicionais para este projeto. Basta criar um fork deste repositório, fazer as alterações necessárias e enviar uma solicitação pull.

### Licença

    Este projeto está licenciado sob a MIT License.
