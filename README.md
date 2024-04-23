# Projeto Culinart API
Este repositório contém a API do Projeto Culinart, que utiliza as seguintes tecnologias:

- Java
- Spring Boot com JPA
- Design Patterns: Observer, Strategy, Adapter
- Banco de Dados MySQL

# Sobre o Projeto
O Projeto Culinart tem como objetivo fornecer um sistema que permite aos usuários se inscreverem em um serviço de assinatura personalizado para obterem refeições de acordo com suas preferências alimentares e necessidades diárias.

# Funcionalidades
A API oferece endpoints para:

### Receitas
  - Ingredientes
  - Preferencias
  - ModosPreparos

### Usuários
  - Endereco
  - Assinatura
  - Plano
  - Pedido

# Arquitetura
O projeto utiliza uma arquitetura com 2 subredes na AWS, uma privada e outra pública, configuradas através de uma VPN.
![image](https://github.com/Culinart/API/assets/110927310/d7679c00-6d60-442c-8701-5663f39b66aa)


# Padrões de Projetos 

Dos diversos padrões existentes, os utilizados foram: 

- _Observer_: Padrão comportamental utilizado para envio de notificações ao usuário através do e-mail; 

- _Adapter_: Padrão estrutural utilizado para criação e utilização de DTO’S, evitando assim a recursividade e trazendo mais segurança durante a transferência de dados entre as diferentes camadas da aplicação; 

- _Strategy_: Padrão comportamental utilizado para toda a composição da estrutura do código Back-End, com ele separamos as camadas a aplicação em camadas facilitando sua organização e manutenção; 
